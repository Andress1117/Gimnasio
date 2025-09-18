import React from 'react';
import {
  TouchableOpacity,
  Text,
  StyleSheet,
  ViewStyle,
  TextStyle,
  ActivityIndicator,
  View,
} from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { theme } from '../../theme';

export interface ButtonProps {
  title: string;
  onPress: () => void;
  variant?: 'primary' | 'secondary' | 'outline' | 'ghost' | 'danger';
  size?: 'sm' | 'md' | 'lg' | 'xl';
  disabled?: boolean;
  loading?: boolean;
  icon?: React.ReactNode;
  iconPosition?: 'left' | 'right';
  fullWidth?: boolean;
  style?: ViewStyle;
  textStyle?: TextStyle;
}

export const Button: React.FC<ButtonProps> = ({
  title,
  onPress,
  variant = 'primary',
  size = 'md',
  disabled = false,
  loading = false,
  icon,
  iconPosition = 'left',
  fullWidth = false,
  style,
  textStyle,
}) => {
  const buttonStyles = [
    styles.base,
    styles[variant],
    styles[size],
    fullWidth && styles.fullWidth,
    disabled && styles.disabled,
    style,
  ];

  const textStyles = [
    styles.text,
    styles[`${variant}Text`],
    styles[`${size}Text`],
    disabled && styles.disabledText,
    textStyle,
  ];

  const renderContent = () => (
    <View style={styles.content}>
      {loading && (
        <ActivityIndicator
          size="small"
          color={variant === 'primary' ? theme.colors.text.light : theme.colors.primary[600]}
          style={styles.loader}
        />
      )}
      {icon && iconPosition === 'left' && !loading && (
        <View style={styles.iconLeft}>{icon}</View>
      )}
      <Text style={textStyles}>{title}</Text>
      {icon && iconPosition === 'right' && !loading && (
        <View style={styles.iconRight}>{icon}</View>
      )}
    </View>
  );

  if (variant === 'primary') {
    return (
      <TouchableOpacity
        onPress={onPress}
        disabled={disabled || loading}
        style={buttonStyles}
        activeOpacity={0.8}
      >
        <LinearGradient
          colors={theme.gradients.primary}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 0 }}
          style={styles.gradient}
        >
          {renderContent()}
        </LinearGradient>
      </TouchableOpacity>
    );
  }

  return (
    <TouchableOpacity
      onPress={onPress}
      disabled={disabled || loading}
      style={buttonStyles}
      activeOpacity={0.8}
    >
      {renderContent()}
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  base: {
    borderRadius: 16,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    shadowColor: theme.colors.shadow.light,
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 8,
    elevation: 6,
  },
  
  // Variants
  primary: {
    backgroundColor: theme.colors.primary[600],
  },
  secondary: {
    backgroundColor: theme.colors.secondary[600],
  },
  outline: {
    backgroundColor: 'transparent',
    borderWidth: theme.borderWidth[2],
    borderColor: theme.colors.primary[600],
  },
  ghost: {
    backgroundColor: 'transparent',
  },
  danger: {
    backgroundColor: theme.colors.error[600],
  },
  
  // Sizes
  sm: {
    height: theme.dimensions.button.sm.height,
    paddingHorizontal: theme.dimensions.button.sm.paddingHorizontal,
    paddingVertical: theme.dimensions.button.sm.paddingVertical,
  },
  md: {
    height: theme.dimensions.button.md.height,
    paddingHorizontal: theme.dimensions.button.md.paddingHorizontal,
    paddingVertical: theme.dimensions.button.md.paddingVertical,
  },
  lg: {
    height: theme.dimensions.button.lg.height,
    paddingHorizontal: theme.dimensions.button.lg.paddingHorizontal,
    paddingVertical: theme.dimensions.button.lg.paddingVertical,
  },
  xl: {
    height: theme.dimensions.button.xl.height,
    paddingHorizontal: theme.dimensions.button.xl.paddingHorizontal,
    paddingVertical: theme.dimensions.button.xl.paddingVertical,
  },
  
  // States
  disabled: {
    opacity: 0.5,
  },
  fullWidth: {
    width: '100%',
  },
  
  // Text styles
  text: {
    ...theme.textStyles.button,
    textAlign: 'center',
  },
  primaryText: {
    color: theme.colors.text.light,
  },
  secondaryText: {
    color: theme.colors.text.light,
  },
  outlineText: {
    color: theme.colors.primary[600],
  },
  ghostText: {
    color: theme.colors.primary[600],
  },
  dangerText: {
    color: theme.colors.text.light,
  },
  
  // Text sizes
  smText: {
    ...theme.textStyles.buttonSmall,
  },
  mdText: {
    ...theme.textStyles.button,
  },
  lgText: {
    ...theme.textStyles.buttonLarge,
  },
  xlText: {
    ...theme.textStyles.buttonLarge,
    fontSize: theme.typography.fontSize.xl,
  },
  
  disabledText: {
    opacity: 0.7,
  },
  
  // Content
  content: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
  },
  gradient: {
    flex: 1,
    borderRadius: theme.borderRadius.lg,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
  },
  
  // Icons
  iconLeft: {
    marginRight: theme.spacing[2],
  },
  iconRight: {
    marginLeft: theme.spacing[2],
  },
  loader: {
    marginRight: theme.spacing[2],
  },
});
