import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  RefreshControl,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient } from 'expo-linear-gradient';
import { Card, LoadingSpinner } from '../components/ui';
import { theme } from '../theme';
import { useAuthStore } from '../store';
import { format } from 'date-fns';
import { es } from 'date-fns/locale';

interface DashboardStats {
  totalMembers: number;
  activeMembers: number;
  todayCheckIns: number;
  monthlyRevenue: number;
}

export default function DashboardScreen() {
  const { user, logout } = useAuthStore();
  const [stats, setStats] = useState<DashboardStats>({
    totalMembers: 0,
    activeMembers: 0,
    todayCheckIns: 0,
    monthlyRevenue: 0,
  });
  const [isLoading, setIsLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);

  const loadDashboardData = async () => {
    try {
      // Simular carga de datos - aquí conectarías con tu API
      await new Promise(resolve => setTimeout(resolve, 1000));
      
      setStats({
        totalMembers: 156,
        activeMembers: 142,
        todayCheckIns: 23,
        monthlyRevenue: 12500,
      });
    } catch (error) {
      console.error('Error loading dashboard data:', error);
    } finally {
      setIsLoading(false);
      setRefreshing(false);
    }
  };

  useEffect(() => {
    loadDashboardData();
  }, []);

  const onRefresh = () => {
    setRefreshing(true);
    loadDashboardData();
  };

  const StatCard = ({ 
    title, 
    value, 
    icon, 
    color, 
    onPress 
  }: {
    title: string;
    value: string | number;
    icon: keyof typeof Ionicons.glyphMap;
    color: string;
    onPress?: () => void;
  }) => (
    <TouchableOpacity onPress={onPress} activeOpacity={0.8}>
      <Card style={[styles.statCard, { borderLeftColor: color }]}>
        <View style={styles.statContent}>
          <View style={styles.statInfo}>
            <Text style={styles.statValue}>{value}</Text>
            <Text style={styles.statTitle}>{title}</Text>
          </View>
          <View style={[styles.statIcon, { backgroundColor: color }]}>
            <Ionicons name={icon} size={24} color={theme.colors.text.light} />
          </View>
        </View>
      </Card>
    </TouchableOpacity>
  );

  const QuickAction = ({ 
    title, 
    icon, 
    color, 
    onPress 
  }: {
    title: string;
    icon: keyof typeof Ionicons.glyphMap;
    color: string;
    onPress: () => void;
  }) => (
    <TouchableOpacity onPress={onPress} style={styles.quickAction} activeOpacity={0.8}>
      <LinearGradient
        colors={[color, color + '80']}
        style={styles.quickActionGradient}
      >
        <Ionicons name={icon} size={32} color={theme.colors.text.light} />
        <Text style={styles.quickActionText}>{title}</Text>
      </LinearGradient>
    </TouchableOpacity>
  );

  if (isLoading) {
    return (
      <View style={styles.loadingContainer}>
        <LoadingSpinner text="Cargando dashboard..." />
      </View>
    );
  }

  return (
    <ScrollView 
      style={styles.container}
      refreshControl={
        <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
      }
    >
      {/* Header */}
      <LinearGradient
        colors={theme.gradients.primary}
        style={styles.header}
      >
        <View style={styles.headerContent}>
          <View>
            <Text style={styles.greeting}>¡Hola, {user?.firstName}!</Text>
            <Text style={styles.date}>
              {format(new Date(), 'EEEE, d MMMM', { locale: es })}
            </Text>
          </View>
          <TouchableOpacity onPress={logout} style={styles.logoutButton}>
            <Ionicons name="log-out-outline" size={24} color={theme.colors.text.light} />
          </TouchableOpacity>
        </View>
      </LinearGradient>

      {/* Stats Cards */}
      <View style={styles.statsContainer}>
        <Text style={styles.sectionTitle}>Resumen General</Text>
        <View style={styles.statsGrid}>
          <StatCard
            title="Miembros Totales"
            value={stats.totalMembers}
            icon="people"
            color={theme.colors.primary[600]}
          />
          <StatCard
            title="Miembros Activos"
            value={stats.activeMembers}
            icon="checkmark-circle"
            color={theme.colors.success[600]}
          />
          <StatCard
            title="Check-ins Hoy"
            value={stats.todayCheckIns}
            icon="time"
            color={theme.colors.warning[600]}
          />
          <StatCard
            title="Ingresos del Mes"
            value={`$${stats.monthlyRevenue.toLocaleString()}`}
            icon="cash"
            color={theme.colors.secondary[600]}
          />
        </View>
      </View>

      {/* Quick Actions */}
      <View style={styles.actionsContainer}>
        <Text style={styles.sectionTitle}>Acciones Rápidas</Text>
        <View style={styles.actionsGrid}>
          <QuickAction
            title="Nuevo Miembro"
            icon="person-add"
            color={theme.colors.primary[600]}
            onPress={() => {/* Navigate to add member */}}
          />
          <QuickAction
            title="Check-in"
            icon="scan"
            color={theme.colors.success[600]}
            onPress={() => {/* Navigate to check-in */}}
          />
          <QuickAction
            title="Clases"
            icon="calendar"
            color={theme.colors.warning[600]}
            onPress={() => {/* Navigate to classes */}}
          />
          <QuickAction
            title="Equipos"
            icon="fitness"
            color={theme.colors.secondary[600]}
            onPress={() => {/* Navigate to equipment */}}
          />
        </View>
      </View>

      {/* Recent Activity */}
      <View style={styles.activityContainer}>
        <Text style={styles.sectionTitle}>Actividad Reciente</Text>
        <Card style={styles.activityCard}>
          <View style={styles.activityItem}>
            <View style={[styles.activityIcon, { backgroundColor: theme.colors.success[100] }]}>
              <Ionicons name="checkmark-circle" size={20} color={theme.colors.success[600]} />
            </View>
            <View style={styles.activityContent}>
              <Text style={styles.activityTitle}>Juan Pérez hizo check-in</Text>
              <Text style={styles.activityTime}>Hace 5 minutos</Text>
            </View>
          </View>
          
          <View style={styles.activityItem}>
            <View style={[styles.activityIcon, { backgroundColor: theme.colors.primary[100] }]}>
              <Ionicons name="person-add" size={20} color={theme.colors.primary[600]} />
            </View>
            <View style={styles.activityContent}>
              <Text style={styles.activityTitle}>Nuevo miembro registrado</Text>
              <Text style={styles.activityTime}>Hace 15 minutos</Text>
            </View>
          </View>
          
          <View style={styles.activityItem}>
            <View style={[styles.activityIcon, { backgroundColor: theme.colors.warning[100] }]}>
              <Ionicons name="calendar" size={20} color={theme.colors.warning[600]} />
            </View>
            <View style={styles.activityContent}>
              <Text style={styles.activityTitle}>Clase de Yoga programada</Text>
              <Text style={styles.activityTime}>Hace 1 hora</Text>
            </View>
          </View>
        </Card>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: theme.colors.background.light,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  header: {
    paddingTop: theme.spacing[12],
    paddingBottom: theme.spacing[6],
    paddingHorizontal: theme.spacing[6],
  },
  headerContent: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  greeting: {
    ...theme.textStyles.h2,
    color: theme.colors.text.light,
    marginBottom: theme.spacing[1],
  },
  date: {
    ...theme.textStyles.body,
    color: theme.colors.text.light,
    opacity: 0.9,
  },
  logoutButton: {
    padding: theme.spacing[2],
  },
  statsContainer: {
    padding: theme.spacing[6],
  },
  sectionTitle: {
    ...theme.textStyles.h4,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[4],
  },
  statsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  statCard: {
    width: '48%',
    marginBottom: theme.spacing[4],
    borderLeftWidth: 4,
  },
  statContent: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  statInfo: {
    flex: 1,
  },
  statValue: {
    ...theme.textStyles.h3,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[1],
  },
  statTitle: {
    ...theme.textStyles.caption,
    color: theme.colors.text.secondary,
  },
  statIcon: {
    width: 48,
    height: 48,
    borderRadius: 24,
    alignItems: 'center',
    justifyContent: 'center',
  },
  actionsContainer: {
    paddingHorizontal: theme.spacing[6],
    paddingBottom: theme.spacing[6],
  },
  actionsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  quickAction: {
    width: '48%',
    marginBottom: theme.spacing[4],
    borderRadius: theme.borderRadius.lg,
    overflow: 'hidden',
  },
  quickActionGradient: {
    padding: theme.spacing[4],
    alignItems: 'center',
    justifyContent: 'center',
    minHeight: 100,
  },
  quickActionText: {
    ...theme.textStyles.label,
    color: theme.colors.text.light,
    marginTop: theme.spacing[2],
    textAlign: 'center',
  },
  activityContainer: {
    paddingHorizontal: theme.spacing[6],
    paddingBottom: theme.spacing[8],
  },
  activityCard: {
    padding: theme.spacing[4],
  },
  activityItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: theme.spacing[4],
  },
  activityIcon: {
    width: 40,
    height: 40,
    borderRadius: 20,
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: theme.spacing[3],
  },
  activityContent: {
    flex: 1,
  },
  activityTitle: {
    ...theme.textStyles.body,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[1],
  },
  activityTime: {
    ...theme.textStyles.caption,
    color: theme.colors.text.secondary,
  },
});