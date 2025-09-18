import { create } from 'zustand';
import { Member } from '../types';
import { apiService } from '../services/api';

interface MemberState {
  members: Member[];
  selectedMember: Member | null;
  isLoading: boolean;
  error: string | null;
}

interface MemberActions {
  fetchMembers: () => Promise<void>;
  getMember: (id: number) => Promise<Member | null>;
  createMember: (memberData: any) => Promise<Member | null>;
  updateMember: (id: number, memberData: any) => Promise<Member | null>;
  deleteMember: (id: number) => Promise<boolean>;
  setSelectedMember: (member: Member | null) => void;
  setLoading: (loading: boolean) => void;
  setError: (error: string | null) => void;
  clearError: () => void;
}

type MemberStore = MemberState & MemberActions;

export const useMemberStore = create<MemberStore>((set, get) => ({
  // State
  members: [],
  selectedMember: null,
  isLoading: false,
  error: null,

  // Actions
  fetchMembers: async () => {
    set({ isLoading: true, error: null });
    
    try {
      const members = await apiService.getMembers();
      set({ members, isLoading: false, error: null });
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al cargar miembros';
      set({ isLoading: false, error: errorMessage });
    }
  },

  getMember: async (id: number) => {
    set({ isLoading: true, error: null });
    
    try {
      const member = await apiService.getMember(id);
      set({ selectedMember: member, isLoading: false, error: null });
      return member;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al cargar miembro';
      set({ isLoading: false, error: errorMessage });
      return null;
    }
  },

  createMember: async (memberData: any) => {
    set({ isLoading: true, error: null });
    
    try {
      const newMember = await apiService.createMember(memberData);
      const { members } = get();
      set({ 
        members: [...members, newMember], 
        isLoading: false, 
        error: null 
      });
      return newMember;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al crear miembro';
      set({ isLoading: false, error: errorMessage });
      return null;
    }
  },

  updateMember: async (id: number, memberData: any) => {
    set({ isLoading: true, error: null });
    
    try {
      const updatedMember = await apiService.updateMember(id, memberData);
      const { members } = get();
      const updatedMembers = members.map(member => 
        member.id === id ? updatedMember : member
      );
      set({ 
        members: updatedMembers,
        selectedMember: updatedMember,
        isLoading: false, 
        error: null 
      });
      return updatedMember;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al actualizar miembro';
      set({ isLoading: false, error: errorMessage });
      return null;
    }
  },

  deleteMember: async (id: number) => {
    set({ isLoading: true, error: null });
    
    try {
      await apiService.deleteMember(id);
      const { members } = get();
      const filteredMembers = members.filter(member => member.id !== id);
      set({ 
        members: filteredMembers,
        selectedMember: null,
        isLoading: false, 
        error: null 
      });
      return true;
    } catch (error: any) {
      const errorMessage = error.response?.data?.message || 'Error al eliminar miembro';
      set({ isLoading: false, error: errorMessage });
      return false;
    }
  },

  setSelectedMember: (member: Member | null) => {
    set({ selectedMember: member });
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
}));
