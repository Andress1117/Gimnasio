export interface User {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber?: string;
  role: 'ADMIN' | 'TRAINER' | 'MEMBER';
  active: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  username: string;
  role: 'ADMIN' | 'TRAINER' | 'MEMBER';
  firstName: string;
  lastName: string;
}

export interface Member {
  id: number;
  user: User;
  membershipType: string;
  startDate: string;
  endDate: string;
  active: boolean;
  emergencyContact?: string;
  medicalInfo?: string;
}

export interface Trainer {
  id: number;
  user: User;
  specialization: string;
  experience: number;
  certifications: string[];
  hourlyRate: number;
  active: boolean;
}

export interface Class {
  id: number;
  name: string;
  description: string;
  trainer: Trainer;
  maxCapacity: number;
  duration: number;
  price: number;
  active: boolean;
  schedule: Schedule[];
}

export interface Schedule {
  id: number;
  classId: number;
  dayOfWeek: string;
  startTime: string;
  endTime: string;
  active: boolean;
}

export interface Equipment {
  id: number;
  name: string;
  description: string;
  category: string;
  status: 'AVAILABLE' | 'IN_USE' | 'MAINTENANCE';
  lastMaintenance?: string;
  nextMaintenance?: string;
}

export interface Payment {
  id: number;
  member: Member;
  amount: number;
  paymentType: 'MEMBERSHIP' | 'CLASS' | 'PERSONAL_TRAINING';
  status: 'PENDING' | 'COMPLETED' | 'FAILED';
  paymentDate: string;
  dueDate?: string;
}

export interface Attendance {
  id: number;
  member: Member;
  checkInTime: string;
  checkOutTime?: string;
  date: string;
}

export interface Workout {
  id: number;
  member: Member;
  trainer?: Trainer;
  name: string;
  description: string;
  exercises: Exercise[];
  date: string;
  duration: number;
}

export interface Exercise {
  id: number;
  name: string;
  sets: number;
  reps: number;
  weight?: number;
  duration?: number;
  notes?: string;
}

export interface AuthContextType {
  user: User | null;
  token: string | null;
  login: (credentials: LoginRequest) => Promise<boolean>;
  logout: () => void;
  isLoading: boolean;
}

export interface ApiResponse<T> {
  data: T;
  message?: string;
  success: boolean;
}
