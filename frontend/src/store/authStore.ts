import { create } from 'zustand';
import { User, LoginRequest } from '../types';
import { apiService } from '../services/api';

interface AuthState {
  user: User | null;
  token: string | null;
  isLoading: boolean;
  isAuthenticated: boolean;
  error: string | null;
}

interface AuthActions {
  login: (credentials: LoginRequest) => Promise<boolean>;
  logout: () => void;
  setUser: (user: User) => void;
  setToken: (token: string) => void;
  setLoading: (loading: boolean) => void;
  setError: (error: string | null) => void;
  clearError: () => void;
  checkAuth: () => Promise<void>;
}

type AuthStore = AuthState & AuthActions;

// Simple storage for web compatibility
const storage = {
  getItem: (key: string): string | null => {
    if (typeof window !== 'undefined') {
      return localStorage.getItem(key);
    }
    return null;
  },
  setItem: (key: string, value: string): void => {
    if (typeof window !== 'undefined') {
      localStorage.setItem(key, value);
    }
  },
  removeItem: (key: string): void => {
    if (typeof window !== 'undefined') {
      localStorage.removeItem(key);
    }
  },
};

export const useAuthStore = create<AuthStore>((set, get) => ({
  // State
  user: null,
  token: null,
  isLoading: false,
  isAuthenticated: false,
  error: null,

  // Actions
  login: async (credentials: LoginRequest) => {
    set({ isLoading: true, error: null });
    
    try {
      const response = await apiService.login(credentials);
      
      // Store token
      storage.setItem('auth_token', response.token);
      
      // Get user details
      const user = await apiService.getCurrentUser();
      
      set({
        user,
        token: response.token,
        isAuthenticated: true,
        isLoading: false,
        error: null,
      });
      
      return true;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al iniciar sesión';
      set({
        user: null,
        token: null,
        isAuthenticated: false,
        isLoading: false,
        error: errorMessage,
      });
      return false;
    }
  },

  logout: async () => {
    try {
      storage.removeItem('auth_token');
    } catch {
      // Handle error silently
    }
    
    set({
      user: null,
      token: null,
      isAuthenticated: false,
      error: null,
    });
  },

  setUser: (user: User) => {
    set({ user });
  },

  setToken: (token: string) => {
    set({ token, isAuthenticated: true });
  },

  setLoading: (isLoading: boolean) => {
    set({ isLoading });
  },

  setError: (error: string | null) => {
    set({ error });
  },

  clearError: () => {
    set({ error: null });
  },

  checkAuth: async () => {
    const { token } = get();
    
    if (!token) {
      set({ isAuthenticated: false, user: null });
      return;
    }

    set({ isLoading: true });
    
    try {
      const user = await apiService.getCurrentUser();
      set({
        user,
        isAuthenticated: true,
        isLoading: false,
        error: null,
      });
    } catch (error) {
      // Token is invalid, logout
      await get().logout();
      set({ isLoading: false });
    }
  },
}));