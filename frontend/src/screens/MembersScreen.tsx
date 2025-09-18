import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  RefreshControl,
  Alert,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { Card, Button, LoadingSpinner } from '../components/ui';
import { theme } from '../theme';
import { useMemberStore } from '../store';
import { Member } from '../types';
import { format } from 'date-fns';
import { es } from 'date-fns/locale';

export default function MembersScreen() {
  const { 
    members, 
    isLoading, 
    error, 
    fetchMembers, 
    deleteMember 
  } = useMemberStore();
  
  const [refreshing, setRefreshing] = useState(false);

  useEffect(() => {
    fetchMembers();
  }, [fetchMembers]);

  const onRefresh = async () => {
    setRefreshing(true);
    await fetchMembers();
    setRefreshing(false);
  };

  const handleDeleteMember = (member: Member) => {
    Alert.alert(
      'Eliminar Miembro',
      `¿Estás seguro de que quieres eliminar a ${member.user.firstName} ${member.user.lastName}?`,
      [
        { text: 'Cancelar', style: 'cancel' },
        {
          text: 'Eliminar',
          style: 'destructive',
          onPress: async () => {
            const success = await deleteMember(member.id);
            if (success) {
              Alert.alert('Éxito', 'Miembro eliminado correctamente');
            } else {
              Alert.alert('Error', 'No se pudo eliminar el miembro');
            }
          },
        },
      ]
    );
  };

  const getMembershipStatusColor = (active: boolean) => {
    return active ? theme.colors.success[600] : theme.colors.error[600];
  };

  const getMembershipStatusText = (active: boolean) => {
    return active ? 'Activo' : 'Inactivo';
  };

  const renderMemberItem = ({ item }: { item: Member }) => (
    <Card style={styles.memberCard}>
      <View style={styles.memberHeader}>
        <View style={styles.memberInfo}>
          <Text style={styles.memberName}>
            {item.user.firstName} {item.user.lastName}
          </Text>
          <Text style={styles.memberEmail}>{item.user.email}</Text>
          <Text style={styles.memberPhone}>{item.user.phoneNumber}</Text>
        </View>
        <View style={styles.memberActions}>
          <TouchableOpacity
            style={[styles.statusBadge, { backgroundColor: getMembershipStatusColor(item.active) }]}
          >
            <Text style={styles.statusText}>
              {getMembershipStatusText(item.active)}
            </Text>
          </TouchableOpacity>
        </View>
      </View>
      
      <View style={styles.memberDetails}>
        <View style={styles.detailItem}>
          <Ionicons name="card" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>{item.membershipType}</Text>
        </View>
        <View style={styles.detailItem}>
          <Ionicons name="calendar" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>
            Inicio: {format(new Date(item.startDate), 'dd/MM/yyyy', { locale: es })}
          </Text>
        </View>
        <View style={styles.detailItem}>
          <Ionicons name="calendar-outline" size={16} color={theme.colors.neutral[500]} />
          <Text style={styles.detailText}>
            Fin: {format(new Date(item.endDate), 'dd/MM/yyyy', { locale: es })}
          </Text>
        </View>
      </View>

      <View style={styles.memberActions}>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="eye" size={20} color={theme.colors.primary[600]} />
          <Text style={styles.actionText}>Ver</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.actionButton}>
          <Ionicons name="create" size={20} color={theme.colors.warning[600]} />
          <Text style={styles.actionText}>Editar</Text>
        </TouchableOpacity>
        <TouchableOpacity 
          style={styles.actionButton}
          onPress={() => handleDeleteMember(item)}
        >
          <Ionicons name="trash" size={20} color={theme.colors.error[600]} />
          <Text style={styles.actionText}>Eliminar</Text>
        </TouchableOpacity>
      </View>
    </Card>
  );

  const renderEmptyState = () => (
    <View style={styles.emptyState}>
      <Ionicons name="people-outline" size={64} color={theme.colors.neutral[300]} />
      <Text style={styles.emptyTitle}>No hay miembros</Text>
      <Text style={styles.emptySubtitle}>
        Agrega tu primer miembro para comenzar
      </Text>
      <Button
        title="Agregar Miembro"
        onPress={() => {/* Navigate to add member */}}
        style={styles.emptyButton}
      />
    </View>
  );

  if (isLoading && members.length === 0) {
    return (
      <View style={styles.loadingContainer}>
        <LoadingSpinner text="Cargando miembros..." />
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Miembros</Text>
        <Button
          title="Agregar"
          onPress={() => {/* Navigate to add member */}}
          icon="add"
          size="sm"
        />
      </View>

      {error && (
        <View style={styles.errorContainer}>
          <Ionicons name="alert-circle" size={20} color={theme.colors.error[600]} />
          <Text style={styles.errorText}>{error}</Text>
        </View>
      )}

      <FlatList
        data={members}
        renderItem={renderMemberItem}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={styles.listContainer}
        showsVerticalScrollIndicator={false}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        ListEmptyComponent={renderEmptyState}
      />
    </View>
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
  errorContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: theme.colors.error[50],
    padding: theme.spacing[3],
    marginHorizontal: theme.spacing[6],
    marginTop: theme.spacing[4],
    borderRadius: theme.borderRadius.lg,
    borderWidth: 1,
    borderColor: theme.colors.error[200],
  },
  errorText: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.error[600],
    marginLeft: theme.spacing[2],
    flex: 1,
  },
  listContainer: {
    padding: theme.spacing[6],
  },
  memberCard: {
    marginBottom: theme.spacing[4],
  },
  memberHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    marginBottom: theme.spacing[3],
  },
  memberInfo: {
    flex: 1,
  },
  memberName: {
    ...theme.textStyles.h5,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[1],
  },
  memberEmail: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
    marginBottom: theme.spacing[1],
  },
  memberPhone: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
  },
  memberActions: {
    flexDirection: 'row',
    alignItems: 'center',
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
  memberDetails: {
    marginBottom: theme.spacing[4],
  },
  detailItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: theme.spacing[2],
  },
  detailText: {
    ...theme.textStyles.bodySmall,
    color: theme.colors.text.secondary,
    marginLeft: theme.spacing[2],
  },
  memberActions: {
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
  emptyState: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: theme.spacing[12],
  },
  emptyTitle: {
    ...theme.textStyles.h4,
    color: theme.colors.text.primary,
    marginTop: theme.spacing[4],
    marginBottom: theme.spacing[2],
  },
  emptySubtitle: {
    ...theme.textStyles.body,
    color: theme.colors.text.secondary,
    textAlign: 'center',
    marginBottom: theme.spacing[6],
  },
  emptyButton: {
    marginTop: theme.spacing[4],
  },
});
