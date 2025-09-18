import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  KeyboardAvoidingView,
  Platform,
  Alert,
  Dimensions,
} from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { Ionicons } from '@expo/vector-icons';
import { Button, Input, LoadingSpinner } from '../components/ui';
import { theme } from '../theme';
import { useAuthStore } from '../store';
import { LoginRequest } from '../types';

const { width, height } = Dimensions.get('window');

export default function LoginScreen() {
  const [formData, setFormData] = useState<LoginRequest>({
    username: '',
    password: '',
  });
  const [showPassword, setShowPassword] = useState(false);
  
  const { login, isLoading, error, clearError } = useAuthStore();

  const handleLogin = async () => {
    if (!formData.username || !formData.password) {
      Alert.alert('Error', 'Por favor completa todos los campos');
      return;
    }

    clearError();
    const success = await login(formData);
    
    if (!success) {
      Alert.alert('Error', 'Credenciales inválidas');
    }
  };

  const handleInputChange = (field: keyof LoginRequest, value: string) => {
    setFormData(prev => ({ ...prev, [field]: value }));
  };

  return (
    <KeyboardAvoidingView 
      style={styles.container}
      behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
    >
      <LinearGradient
        colors={['#1e3a8a', '#1e40af', '#3b82f6']}
        start={{ x: 0, y: 0 }}
        end={{ x: 1, y: 1 }}
        style={styles.gradient}
      >
        {/* Background decorative elements */}
        <View style={styles.backgroundElements}>
          <View style={[styles.circle, styles.circle1]} />
          <View style={[styles.circle, styles.circle2]} />
          <View style={[styles.circle, styles.circle3]} />
        </View>

        <ScrollView 
          contentContainerStyle={styles.scrollContent}
          showsVerticalScrollIndicator={false}
        >
          {/* Header with logo */}
          <View style={styles.header}>
            <View style={styles.logoContainer}>
              <LinearGradient
                colors={['#ffffff', '#f8fafc']}
                style={styles.logoGradient}
              >
                <Ionicons name="business" size={48} color="#1e40af" />
              </LinearGradient>
            </View>
            <Text style={styles.title}>FitHub Enterprise</Text>
            <Text style={styles.subtitle}>Professional Gym Management</Text>
            <View style={styles.titleUnderline} />
          </View>

          {/* Login Form */}
          <View style={styles.formContainer}>
            <View style={styles.form}>
              <View style={styles.formHeader}>
                <Text style={styles.formTitle}>Secure Access</Text>
                <Text style={styles.formSubtitle}>Enter your credentials to continue</Text>
              </View>
              
              <View style={styles.inputContainer}>
                <Input
                  label="Username"
                  placeholder="Enter your username"
                  value={formData.username}
                  onChangeText={(value) => handleInputChange('username', value)}
                  leftIcon="person-outline"
                  size="lg"
                  autoCapitalize="none"
                  autoCorrect={false}
                  required
                  style={styles.input}
                />

                <Input
                  label="Password"
                  placeholder="Enter your password"
                  value={formData.password}
                  onChangeText={(value) => handleInputChange('password', value)}
                  secureTextEntry={!showPassword}
                  leftIcon="lock-closed-outline"
                  rightIcon={showPassword ? 'eye-off' : 'eye'}
                  onRightIconPress={() => setShowPassword(!showPassword)}
                  size="lg"
                  required
                  style={styles.input}
                />
              </View>

              {error && (
                <View style={styles.errorContainer}>
                  <Ionicons name="alert-circle" size={20} color={theme.colors.error[600]} />
                  <Text style={styles.errorText}>{error}</Text>
                </View>
              )}

              <Button
                title="Access Dashboard"
                onPress={handleLogin}
                variant="primary"
                size="lg"
                fullWidth
                loading={isLoading}
                style={styles.loginButton}
                icon="shield-checkmark"
                iconPosition="left"
              />

              <View style={styles.footer}>
                <Text style={styles.footerText}>
                  Having trouble? Contact your administrator
                </Text>
              </View>
            </View>
          </View>
        </ScrollView>
      </LinearGradient>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  gradient: {
    flex: 1,
  },
  backgroundElements: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
  },
  circle: {
    position: 'absolute',
    borderRadius: 1000,
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
  },
  circle1: {
    width: 200,
    height: 200,
    top: -100,
    right: -100,
  },
  circle2: {
    width: 150,
    height: 150,
    bottom: 100,
    left: -75,
  },
  circle3: {
    width: 100,
    height: 100,
    top: height * 0.3,
    right: 50,
  },
  scrollContent: {
    flexGrow: 1,
    justifyContent: 'center',
    paddingHorizontal: theme.spacing[6],
    paddingVertical: theme.spacing[8],
    minHeight: height,
  },
  header: {
    alignItems: 'center',
    marginBottom: theme.spacing[8],
    paddingTop: theme.spacing[8],
  },
  logoContainer: {
    width: 100,
    height: 100,
    borderRadius: 50,
    marginBottom: theme.spacing[6],
    ...theme.shadows.xl,
  },
  logoGradient: {
    width: '100%',
    height: '100%',
    borderRadius: 50,
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 42,
    fontWeight: '800',
    color: theme.colors.text.light,
    textAlign: 'center',
    marginBottom: theme.spacing[2],
    letterSpacing: 2,
  },
  subtitle: {
    fontSize: 16,
    color: theme.colors.text.light,
    opacity: 0.9,
    textAlign: 'center',
    marginBottom: theme.spacing[4],
    fontWeight: '300',
  },
  titleUnderline: {
    width: 60,
    height: 3,
    backgroundColor: theme.colors.text.light,
    borderRadius: 2,
    opacity: 0.8,
  },
  formContainer: {
    backgroundColor: 'rgba(255, 255, 255, 0.98)',
    borderRadius: 20,
    padding: theme.spacing[8],
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 20 },
    shadowOpacity: 0.15,
    shadowRadius: 25,
    elevation: 10,
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.3)',
  },
  form: {
    width: '100%',
  },
  formHeader: {
    alignItems: 'center',
    marginBottom: theme.spacing[8],
  },
  formTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: '#1e40af',
    textAlign: 'center',
    marginBottom: theme.spacing[2],
  },
  formSubtitle: {
    fontSize: 14,
    color: '#64748b',
    textAlign: 'center',
    fontWeight: '400',
  },
  inputContainer: {
    marginBottom: theme.spacing[6],
  },
  input: {
    marginBottom: theme.spacing[4],
  },
  errorContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: theme.colors.error[50],
    padding: theme.spacing[4],
    borderRadius: 12,
    marginBottom: theme.spacing[4],
    borderWidth: 1,
    borderColor: theme.colors.error[200],
  },
  errorText: {
    fontSize: 14,
    color: theme.colors.error[600],
    marginLeft: theme.spacing[2],
    flex: 1,
    fontWeight: '500',
  },
  loginButton: {
    marginTop: theme.spacing[2],
    height: 56,
    borderRadius: 16,
  },
  footer: {
    marginTop: theme.spacing[8],
    alignItems: 'center',
  },
  footerText: {
    fontSize: 14,
    color: theme.colors.text.secondary,
    textAlign: 'center',
    fontWeight: '400',
  },
});