import React from 'react';
import {
  View,
  StyleSheet,
  ViewStyle,
  TouchableOpacity,
} from 'react-native';
import { theme } from '../../theme';

export interface CardProps {
  children: React.ReactNode;
  onPress?: () => void;
  variant?: 'elevated' | 'outlined' | 'filled';
  size?: 'sm' | 'md' | 'lg';
  style?: ViewStyle;
  disabled?: boolean;
}

export const Card: React.FC<CardProps> = ({
  children,
  onPress,
  variant = 'elevated',
  size = 'md',
  style,
  disabled = false,
}) => {
  const cardStyles = [
    styles.base,
    styles[variant],
    styles[size],
    disabled && styles.disabled,
    style,
  ];

  if (onPress) {
    return (
      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
        style={cardStyles}
        activeOpacity={0.8}
      >
        {children}
      </TouchableOpacity>
    );
  }

  return (
    <View style={cardStyles}>
      {children}
    </View>
  );
};

const styles = StyleSheet.create({
  base: {
    borderRadius: theme.borderRadius.lg,
    backgroundColor: theme.colors.background.card,
  },
  
  // Variants
  elevated: {
    ...theme.shadows.md,
    backgroundColor: theme.colors.background.card,
  },
  outlined: {
    borderWidth: theme.borderWidth[1],
    borderColor: theme.colors.border.light,
    backgroundColor: theme.colors.background.card,
  },
  filled: {
    backgroundColor: theme.colors.neutral[50],
  },
  
  // Sizes
  sm: {
    padding: theme.dimensions.card.sm.padding,
    borderRadius: theme.dimensions.card.sm.borderRadius,
  },
  md: {
    padding: theme.dimensions.card.md.padding,
    borderRadius: theme.dimensions.card.md.borderRadius,
  },
  lg: {
    padding: theme.dimensions.card.lg.padding,
    borderRadius: theme.dimensions.card.lg.borderRadius,
  },
  
  // States
  disabled: {
    opacity: 0.6,
  },
});
