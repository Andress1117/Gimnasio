import React, { createContext, useContext, useEffect, useState, ReactNode } from 'react';
import * as SecureStore from 'expo-secure-store';
import { User, LoginRequest, AuthContextType } from '../types';
import { apiService } from '../services/api';

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    checkAuthState();
  }, []);

  const checkAuthState = async () => {
    try {
      const storedToken = await SecureStore.getItemAsync('auth_token');
      if (storedToken) {
        setToken(storedToken);
        // Verificar si el token es válido obteniendo el usuario actual
        try {
          const currentUser = await apiService.getCurrentUser();
          setUser(currentUser);
        } catch (error) {
          // Token inválido, limpiar almacenamiento
          await SecureStore.deleteItemAsync('auth_token');
          setToken(null);
        }
      }
    } catch (error) {
      console.error('Error checking auth state:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const login = async (credentials: LoginRequest): Promise<boolean> => {
    try {
      setIsLoading(true);
      const response = await apiService.login(credentials);
      
      // Guardar token en almacenamiento seguro
      await SecureStore.setItemAsync('auth_token', response.token);
      
      // Crear objeto usuario con la información del login
      const userData: User = {
        id: 0, // Se obtendrá del endpoint getCurrentUser
        username: response.username,
        email: '',
        firstName: response.firstName,
        lastName: response.lastName,
        role: response.role,
        active: true,
        createdAt: '',
        updatedAt: ''
      };
      
      setToken(response.token);
      setUser(userData);
      
      // Obtener información completa del usuario
      try {
        const fullUserData = await apiService.getCurrentUser();
        setUser(fullUserData);
      } catch (error) {
        console.error('Error getting full user data:', error);
      }
      
      return true;
    } catch (error) {
      console.error('Login error:', error);
      return false;
    } finally {
      setIsLoading(false);
    }
  };

  const logout = async () => {
    try {
      await SecureStore.deleteItemAsync('auth_token');
      setToken(null);
      setUser(null);
    } catch (error) {
      console.error('Logout error:', error);
    }
  };

  const value: AuthContextType = {
    user,
    token,
    login,
    logout,
    isLoading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
