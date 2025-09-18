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

export default function EquipmentScreen() {
  const [refreshing, setRefreshing] = useState(false);

  // Mock data - replace with real data from your API
  const equipment = [
    {
      id: 1,
      name: 'Cinta de Correr Pro',
      description: 'Cinta de correr profesional con inclinación automática',
      category: 'Cardio',
      status: 'AVAILABLE',
      lastMaintenance: '2024-01-15',
      nextMaintenance: '2024-04-15',
    },
    {
      id: 2,
      name: 'Mancuernas 20kg',
      description: 'Par de mancuernas de 20kg cada una',
      category: 'Pesas',
      status: 'IN_USE',
      lastMaintenance: '2024-01-10',
      nextMaintenance: '2024-07-10',
    },
    {
      id: 3,
      name: 'Bicicleta Estática',
      description: 'Bicicleta estática con monitor de frecuencia cardíaca',
      category: 'Cardio',
      status: 'MAINTENANCE',
      lastMaintenance: '2024-01-20',
      nextMaintenance: '2024-02-20',
    },
  ];

  const onRefresh = () => {
    setRefreshing(true);
    setTimeout(() => setRefreshing(false), 1000);
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'AVAILABLE':
        return theme.colors.success[600];
      case 'IN_USE':
        return theme.colors.warning[600];
      case 'MAINTENANCE':
        return theme.colors.error[600];
      default:
        return theme.colors.neutral[500];
    }
  };

  const getStatusText = (status: string) => {
    switch (status) {
      case 'AVAILABLE':
        return 'Disponible';
      case 'IN_USE':
        return 'En Uso';
      case 'MAINTENANCE':
        return 'Mantenimiento';
      default:
        return 'Desconocido';
    }
  };

  const renderEquipmentItem = ({ item }: { item: any }) => (
    <Card style={styles.equipmentCard}>
      <View style={styles.equipmentHeader}>
        <View style={styles.equipmentInfo}>
          <Text style={styles.equipmentName}>{item.name}</Text>
          <Text style={styles.equipmentDescription}>{item.description}</Text>
          <Text style={styles.equipmentCategory}>Categoría: {item.category}</Text>
        </View>
        <View style={[styles.statusBadge, { backgroundColor: getStatusColor(item.status) }]}>
          <Text style={styles.statusText}>{getStatusText(item.status)}</Text>
        </View>
      </View>
      
      <View style={styles.equipmentDetails}>
        <View style={styles.detailRow}>
          <Ionicons name="calendar" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>
            Último mantenimiento: {new Date(item.lastMaintenance).toLocaleDateString()}
          </Text>
        </View>
        <View style={styles.detailRow}>
          <Ionicons name="calendar-outline" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>
            Próximo mantenimiento: {new Date(item.nextMaintenance).toLocaleDateString()}
          </Text>
        </View>
      </View>

      <View style={styles.equipmentActions}>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="eye" size={20} color={theme.colors.primary[600]} />
          <Text style={styles.actionText}>Ver</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="create" size={20} color={theme.colors.warning[600]} />
          <Text style={styles.actionText}>Editar</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="construct" size={20} color={theme.colors.secondary[600]} />
          <Text style={styles.actionText}>Mantenimiento</Text>
        </TouchableOpacity>
      </View>
    </Card>
  );

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Equipos</Text>
        <Button
          title="Agregar Equipo"
          onPress={() => {}}
          icon="add"
          size="sm"
        />
      </View>

      <FlatList
        data={equipment}
        renderItem={renderEquipmentItem}
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
  equipmentCard: {
    marginBottom: theme.spacing[4],
  },
  equipmentHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    marginBottom: theme.spacing[3],
  },
  equipmentInfo: {
    flex: 1,
  },
  equipmentName: {
    ...theme.textStyles.h5,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[1],
  },
  equipmentDescription: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
    marginBottom: theme.spacing[1],
  },
  equipmentCategory: {
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
  equipmentDetails: {
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
  equipmentActions: {
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
