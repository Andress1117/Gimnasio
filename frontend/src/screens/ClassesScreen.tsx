import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  RefreshControl,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { Card, Button } from '../components/ui';
import { theme } from '../theme';

export default function ClassesScreen() {
  const [refreshing, setRefreshing] = useState(false);

  // Mock data - replace with real data from your API
  const classes = [
    {
      id: 1,
      name: 'Yoga Matutino',
      description: 'Clase de yoga para principiantes',
      trainer: 'María García',
      maxCapacity: 20,
      currentEnrolled: 15,
      duration: 60,
      price: 25,
      schedule: 'Lunes, Miércoles, Viernes - 7:00 AM',
      active: true,
    },
    {
      id: 2,
      name: 'CrossFit Intenso',
      description: 'Entrenamiento de alta intensidad',
      trainer: 'Carlos López',
      maxCapacity: 15,
      currentEnrolled: 12,
      duration: 45,
      price: 30,
      schedule: 'Martes, Jueves - 6:00 PM',
      active: true,
    },
    {
      id: 3,
      name: 'Pilates',
      description: 'Fortalecimiento y flexibilidad',
      trainer: 'Ana Martínez',
      maxCapacity: 12,
      currentEnrolled: 8,
      duration: 50,
      price: 20,
      schedule: 'Lunes, Miércoles - 5:00 PM',
      active: true,
    },
  ];

  const onRefresh = () => {
    setRefreshing(true);
    // Simulate API call
    setTimeout(() => setRefreshing(false), 1000);
  };

  const renderClassItem = ({ item }: { item: any }) => (
    <Card style={styles.classCard}>
      <View style={styles.classHeader}>
        <View style={styles.classInfo}>
          <Text style={styles.className}>{item.name}</Text>
          <Text style={styles.classDescription}>{item.description}</Text>
          <Text style={styles.classTrainer}>Instructor: {item.trainer}</Text>
        </View>
        <View style={[styles.statusBadge, { backgroundColor: item.active ? theme.colors.success[600] : theme.colors.error[600] }]}>
          <Text style={styles.statusText}>{item.active ? 'Activa' : 'Inactiva'}</Text>
        </View>
      </View>
      
      <View style={styles.classDetails}>
        <View style={styles.detailRow}>
          <Ionicons name="people" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>{item.currentEnrolled}/{item.maxCapacity} miembros</Text>
        </View>
        <View style={styles.detailRow}>
          <Ionicons name="time" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>{item.duration} minutos</Text>
        </View>
        <View style={styles.detailRow}>
          <Ionicons name="cash" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>${item.price}</Text>
        </View>
        <View style={styles.detailRow}>
          <Ionicons name="calendar" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>{item.schedule}</Text>
        </View>
      </View>

      <View style={styles.classActions}>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="eye" size={20} color={theme.colors.primary[600]} />
          <Text style={styles.actionText}>Ver</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="create" size={20} color={theme.colors.warning[600]} />
          <Text style={styles.actionText}>Editar</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="people" size={20} color={theme.colors.success[600]} />
          <Text style={styles.actionText}>Inscritos</Text>
        </TouchableOpacity>
      </View>
    </Card>
  );

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Clases</Text>
        <Button
          title="Nueva Clase"
          onPress={() => {}}
          icon="add"
          size="sm"
        />
      </View>

      <FlatList
        data={classes}
        renderItem={renderClassItem}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={styles.listContainer}
        showsVerticalScrollIndicator={false}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: theme.colors.background.light,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: theme.spacing[6],
    paddingVertical: theme.spacing[4],
    backgroundColor: theme.colors.background.light,
    borderBottomWidth: 1,
    borderBottomColor: theme.colors.border.light,
  },
  title: {
    ...theme.textStyles.h3,
    color: theme.colors.text.primary,
  },
  listContainer: {
    padding: theme.spacing[6],
  },
  classCard: {
    marginBottom: theme.spacing[4],
  },
  classHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    marginBottom: theme.spacing[3],
  },
  classInfo: {
    flex: 1,
  },
  className: {
    ...theme.textStyles.h5,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[1],
  },
  classDescription: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
    marginBottom: theme.spacing[1],
  },
  classTrainer: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
  },
  statusBadge: {
    paddingHorizontal: theme.spacing[2],
    paddingVertical: theme.spacing[1],
    borderRadius: theme.borderRadius.full,
  },
  statusText: {
    ...theme.textStyles.caption,
    color: theme.colors.text.light,
    fontWeight: '600',
  },
  classDetails: {
    marginBottom: theme.spacing[4],
  },
  detailRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: theme.spacing[2],
  },
  detailText: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
    marginLeft: theme.spacing[2],
  },
  classActions: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    borderTopWidth: 1,
    borderTopColor: theme.colors.border.light,
    paddingTop: theme.spacing[3],
  },
  actionButton: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: theme.spacing[2],
  },
  actionText: {
    ...theme.textStyles.caption,
    color: theme.colors.text.secondary,
    marginLeft: theme.spacing[1],
  },
});
