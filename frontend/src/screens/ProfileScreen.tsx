import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  Alert,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient } from 'expo-linear-gradient';
import { Card, Button } from '../components/ui';
import { theme } from '../theme';
import { useAuthStore } from '../store';

export default function ProfileScreen() {
  const { user, logout } = useAuthStore();

  const handleLogout = () => {
    Alert.alert(
      'Cerrar Sesión',
      '¿Estás seguro de que quieres cerrar sesión?',
      [
        { text: 'Cancelar', style: 'cancel' },
        {
          text: 'Cerrar Sesión',
          style: 'destructive',
          onPress: logout,
        },
      ]
    );
  };

  const menuItems = [
    {
      title: 'Editar Perfil',
      icon: 'person-outline',
      onPress: () => {},
    },
    {
      title: 'Configuración',
      icon: 'settings-outline',
      onPress: () => {},
    },
    {
      title: 'Notificaciones',
      icon: 'notifications-outline',
      onPress: () => {},
    },
    {
      title: 'Ayuda y Soporte',
      icon: 'help-circle-outline',
      onPress: () => {},
    },
    {
      title: 'Acerca de',
      icon: 'information-circle-outline',
      onPress: () => {},
    },
  ];

  const renderMenuItem = (item: any, index: number) => (
    <TouchableOpacity
      key={index}
      style={styles.menuItem}
      onPress={item.onPress}
      activeOpacity={0.7}
    >
      <View style={styles.menuItemContent}>
        <View style={styles.menuItemIcon}>
          <Ionicons name={item.icon} size={24} color={theme.colors.primary[600]} />
        </View>
        <Text style={styles.menuItemText}>{item.title}</Text>
        <Ionicons name="chevron-forward" size={20} color={theme.colors.neutral[400]} />
      </View>
    </TouchableOpacity>
  );

  return (
    <ScrollView style={styles.container}>
      {/* Profile Header */}
      <LinearGradient
        colors={theme.gradients.primary}
        style={styles.header}
      >
        <View style={styles.profileInfo}>
          <View style={styles.avatarContainer}>
            <Ionicons name="person" size={40} color={theme.colors.text.light} />
          </View>
          <Text style={styles.userName}>
            {user?.firstName} {user?.lastName}
          </Text>
          <Text style={styles.userEmail}>{user?.email}</Text>
          <View style={styles.roleBadge}>
            <Text style={styles.roleText}>{user?.role}</Text>
          </View>
        </View>
      </LinearGradient>

      {/* Stats Cards */}
      <View style={styles.statsContainer}>
        <Card style={styles.statCard}>
          <View style={styles.statContent}>
            <Ionicons name="calendar" size={24} color={theme.colors.primary[600]} />
            <Text style={styles.statNumber}>156</Text>
            <Text style={styles.statLabel}>Días activo</Text>
          </View>
        </Card>
        
        <Card style={styles.statCard}>
          <View style={styles.statContent}>
            <Ionicons name="people" size={24} color={theme.colors.success[600]} />
            <Text style={styles.statNumber}>42</Text>
            <Text style={styles.statLabel}>Miembros gestionados</Text>
          </View>
        </Card>
      </View>

      {/* Menu Items */}
      <View style={styles.menuContainer}>
        <Text style={styles.sectionTitle}>Configuración</Text>
        <Card style={styles.menuCard}>
          {menuItems.map((item, index) => renderMenuItem(item, index))}
        </Card>
      </View>

      {/* Logout Button */}
      <View style={styles.logoutContainer}>
        <Button
          title="Cerrar Sesión"
          onPress={handleLogout}
          variant="danger"
          icon="log-out-outline"
          fullWidth
        />
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: theme.colors.background.light,
  },
  header: {
    paddingTop: theme.spacing[12],
    paddingBottom: theme.spacing[8],
    paddingHorizontal: theme.spacing[6],
    alignItems: 'center',
  },
  profileInfo: {
    alignItems: 'center',
  },
  avatarContainer: {
    width: 80,
    height: 80,
    borderRadius: 40,
    backgroundColor: 'rgba(255, 255, 255, 0.2)',
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: theme.spacing[4],
  },
  userName: {
    ...theme.textStyles.h3,
    color: theme.colors.text.light,
    marginBottom: theme.spacing[1],
  },
  userEmail: {
    ...theme.textStyles.body,
    color: theme.colors.text.light,
    opacity: 0.9,
    marginBottom: theme.spacing[3],
  },
  roleBadge: {
    backgroundColor: 'rgba(255, 255, 255, 0.2)',
    paddingHorizontal: theme.spacing[3],
    paddingVertical: theme.spacing[1],
    borderRadius: theme.borderRadius.full,
  },
  roleText: {
    ...theme.textStyles.caption,
    color: theme.colors.text.light,
    fontWeight: '600',
  },
  statsContainer: {
    flexDirection: 'row',
    paddingHorizontal: theme.spacing[6],
    marginTop: -theme.spacing[4],
    marginBottom: theme.spacing[6],
  },
  statCard: {
    flex: 1,
    marginHorizontal: theme.spacing[2],
  },
  statContent: {
    alignItems: 'center',
    padding: theme.spacing[4],
  },
  statNumber: {
    ...theme.textStyles.h3,
    color: theme.colors.text.primary,
    marginTop: theme.spacing[2],
    marginBottom: theme.spacing[1],
  },
  statLabel: {
    ...theme.textStyles.caption,
    color: theme.colors.text.secondary,
    textAlign: 'center',
  },
  menuContainer: {
    paddingHorizontal: theme.spacing[6],
    marginBottom: theme.spacing[6],
  },
  sectionTitle: {
    ...theme.textStyles.h4,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[4],
  },
  menuCard: {
    padding: 0,
  },
  menuItem: {
    borderBottomWidth: 1,
    borderBottomColor: theme.colors.border.light,
  },
  menuItemContent: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: theme.spacing[4],
  },
  menuItemIcon: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: theme.colors.primary[50],
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: theme.spacing[3],
  },
  menuItemText: {
    ...theme.textStyles.body,
    color: theme.colors.text.primary,
    flex: 1,
  },
  logoutContainer: {
    paddingHorizontal: theme.spacing[6],
    paddingBottom: theme.spacing[8],
  },
});
