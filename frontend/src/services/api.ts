import axios, { AxiosInstance, AxiosResponse } from 'axios';
import { 
  LoginRequest, 
  LoginResponse, 
  User, 
  Member, 
  Trainer, 
  Class, 
  Equipment, 
  Payment, 
  Attendance, 
  Workout,
  ApiResponse 
} from '../types';

const BASE_URL = 'http://localhost:8080/api';

class ApiService {
  private api: AxiosInstance;

  constructor() {
    this.api = axios.create({
      baseURL: BASE_URL,
      timeout: 10000,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    // Interceptor para agregar token a las peticiones
    this.api.interceptors.request.use(
      async (config) => {
        const token = typeof window !== 'undefined' ? localStorage.getItem('auth_token') : null;
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );

    // Interceptor para manejar respuestas
    this.api.interceptors.response.use(
      (response) => response,
      async (error) => {
        if (error.response?.status === 401) {
          // Token expirado o inválido
          if (typeof window !== 'undefined') {
            localStorage.removeItem('auth_token');
          }
          // Aquí podrías redirigir al login
        }
        return Promise.reject(error);
      }
    );
  }

  // Auth endpoints
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    const response: AxiosResponse<LoginResponse> = await this.api.post('/auth/login', credentials);
    return response.data;
  }

  async register(userData: any): Promise<User> {
    const response: AxiosResponse<User> = await this.api.post('/users', userData);
    return response.data;
  }

  // User endpoints
  async getCurrentUser(): Promise<User> {
    const response: AxiosResponse<User> = await this.api.get('/users/me');
    return response.data;
  }

  async updateUser(id: number, userData: any): Promise<User> {
    const response: AxiosResponse<User> = await this.api.put(`/users/${id}`, userData);
    return response.data;
  }

  // Member endpoints
  async getMembers(): Promise<Member[]> {
    const response: AxiosResponse<Member[]> = await this.api.get('/members');
    return response.data;
  }

  async getMember(id: number): Promise<Member> {
    const response: AxiosResponse<Member> = await this.api.get(`/members/${id}`);
    return response.data;
  }

  async createMember(memberData: any): Promise<Member> {
    const response: AxiosResponse<Member> = await this.api.post('/members', memberData);
    return response.data;
  }

  async updateMember(id: number, memberData: any): Promise<Member> {
    const response: AxiosResponse<Member> = await this.api.put(`/members/${id}`, memberData);
    return response.data;
  }

  async deleteMember(id: number): Promise<void> {
    await this.api.delete(`/members/${id}`);
  }

  // Trainer endpoints
  async getTrainers(): Promise<Trainer[]> {
    const response: AxiosResponse<Trainer[]> = await this.api.get('/trainers');
    return response.data;
  }

  async getTrainer(id: number): Promise<Trainer> {
    const response: AxiosResponse<Trainer> = await this.api.get(`/trainers/${id}`);
    return response.data;
  }

  async createTrainer(trainerData: any): Promise<Trainer> {
    const response: AxiosResponse<Trainer> = await this.api.post('/trainers', trainerData);
    return response.data;
  }

  async updateTrainer(id: number, trainerData: any): Promise<Trainer> {
    const response: AxiosResponse<Trainer> = await this.api.put(`/trainers/${id}`, trainerData);
    return response.data;
  }

  // Class endpoints
  async getClasses(): Promise<Class[]> {
    const response: AxiosResponse<Class[]> = await this.api.get('/classes');
    return response.data;
  }

  async getClass(id: number): Promise<Class> {
    const response: AxiosResponse<Class> = await this.api.get(`/classes/${id}`);
    return response.data;
  }

  async createClass(classData: any): Promise<Class> {
    const response: AxiosResponse<Class> = await this.api.post('/classes', classData);
    return response.data;
  }

  async updateClass(id: number, classData: any): Promise<Class> {
    const response: AxiosResponse<Class> = await this.api.put(`/classes/${id}`, classData);
    return response.data;
  }

  async enrollInClass(classId: number, memberId: number): Promise<any> {
    const response = await this.api.post(`/classes/${classId}/enroll`, { memberId });
    return response.data;
  }

  // Equipment endpoints
  async getEquipment(): Promise<Equipment[]> {
    const response: AxiosResponse<Equipment[]> = await this.api.get('/equipment');
    return response.data;
  }

  async getEquipmentById(id: number): Promise<Equipment> {
    const response: AxiosResponse<Equipment> = await this.api.get(`/equipment/${id}`);
    return response.data;
  }

  async createEquipment(equipmentData: any): Promise<Equipment> {
    const response: AxiosResponse<Equipment> = await this.api.post('/equipment', equipmentData);
    return response.data;
  }

  async updateEquipment(id: number, equipmentData: any): Promise<Equipment> {
    const response: AxiosResponse<Equipment> = await this.api.put(`/equipment/${id}`, equipmentData);
    return response.data;
  }

  // Payment endpoints
  async getPayments(): Promise<Payment[]> {
    const response: AxiosResponse<Payment[]> = await this.api.get('/payments');
    return response.data;
  }

  async getPayment(id: number): Promise<Payment> {
    const response: AxiosResponse<Payment> = await this.api.get(`/payments/${id}`);
    return response.data;
  }

  async createPayment(paymentData: any): Promise<Payment> {
    const response: AxiosResponse<Payment> = await this.api.post('/payments', paymentData);
    return response.data;
  }

  async updatePayment(id: number, paymentData: any): Promise<Payment> {
    const response: AxiosResponse<Payment> = await this.api.put(`/payments/${id}`, paymentData);
    return response.data;
  }

  // Attendance endpoints
  async getAttendance(): Promise<Attendance[]> {
    const response: AxiosResponse<Attendance[]> = await this.api.get('/attendance');
    return response.data;
  }

  async checkIn(memberId: number): Promise<Attendance> {
    const response: AxiosResponse<Attendance> = await this.api.post('/attendance/checkin', { memberId });
    return response.data;
  }

  async checkOut(attendanceId: number): Promise<Attendance> {
    const response: AxiosResponse<Attendance> = await this.api.post(`/attendance/${attendanceId}/checkout`);
    return response.data;
  }

  // Workout endpoints
  async getWorkouts(): Promise<Workout[]> {
    const response: AxiosResponse<Workout[]> = await this.api.get('/workouts');
    return response.data;
  }

  async getWorkout(id: number): Promise<Workout> {
    const response: AxiosResponse<Workout> = await this.api.get(`/workouts/${id}`);
    return response.data;
  }

  async createWorkout(workoutData: any): Promise<Workout> {
    const response: AxiosResponse<Workout> = await this.api.post('/workouts', workoutData);
    return response.data;
  }

  async updateWorkout(id: number, workoutData: any): Promise<Workout> {
    const response: AxiosResponse<Workout> = await this.api.put(`/workouts/${id}`, workoutData);
    return response.data;
  }
}

export const apiService = new ApiService();
export default apiService;
