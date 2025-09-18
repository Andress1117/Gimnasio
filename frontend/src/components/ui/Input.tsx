import React, { useState } from 'react';
import {
  View,
  TextInput,
  Text,
  StyleSheet,
  ViewStyle,
  TextStyle,
  TouchableOpacity,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { theme } from '../../theme';

export interface InputProps {
  label?: string;
  placeholder?: string;
  value: string;
  onChangeText: (text: string) => void;
  error?: string;
  helperText?: string;
  disabled?: boolean;
  secureTextEntry?: boolean;
  keyboardType?: 'default' | 'email-address' | 'numeric' | 'phone-pad';
  autoCapitalize?: 'none' | 'sentences' | 'words' | 'characters';
  autoCorrect?: boolean;
  multiline?: boolean;
  numberOfLines?: number;
  maxLength?: number;
  leftIcon?: keyof typeof Ionicons.glyphMap;
  rightIcon?: keyof typeof Ionicons.glyphMap;
  onRightIconPress?: () => void;
  size?: 'sm' | 'md' | 'lg';
  variant?: 'outlined' | 'filled' | 'underlined';
  style?: ViewStyle;
  inputStyle?: TextStyle;
  required?: boolean;
}

export const Input: React.FC<InputProps> = ({
  label,
  placeholder,
  value,
  onChangeText,
  error,
  helperText,
  disabled = false,
  secureTextEntry = false,
  keyboardType = 'default',
  autoCapitalize = 'sentences',
  autoCorrect = true,
  multiline = false,
  numberOfLines = 1,
  maxLength,
  leftIcon,
  rightIcon,
  onRightIconPress,
  size = 'md',
  variant = 'outlined',
  style,
  inputStyle,
  required = false,
}) => {
  const [isFocused, setIsFocused] = useState(false);
  const [isSecure, setIsSecure] = useState(secureTextEntry);

  const containerStyles = [
    styles.container,
    style,
  ];

  const inputContainerStyles = [
    styles.inputContainer,
    styles[variant],
    styles[size],
    isFocused && styles.focused,
    error && styles.error,
    disabled && styles.disabled,
  ];

  const inputStyles = [
    styles.input,
    styles[`${size}Input`],
    multiline && styles.multilineInput,
    inputStyle,
  ];

  const labelStyles = [
    styles.label,
    error && styles.errorLabel,
    disabled && styles.disabledLabel,
  ];

  const handleToggleSecure = () => {
    setIsSecure(!isSecure);
  };

  const renderLeftIcon = () => {
    if (leftIcon) {
      return (
        <Ionicons
          name={leftIcon}
          size={20}
          color={error ? theme.colors.error[600] : theme.colors.neutral[500]}
          style={styles.leftIcon}
        />
      );
    }
    return null;
  };

  const renderRightIcon = () => {
    if (secureTextEntry) {
      return (
        <TouchableOpacity onPress={handleToggleSecure} style={styles.rightIcon}>
          <Ionicons
            name={isSecure ? 'eye-off' : 'eye'}
            size={20}
            color={theme.colors.neutral[500]}
          />
        </TouchableOpacity>
      );
    }

    if (rightIcon) {
      return (
        <TouchableOpacity onPress={onRightIconPress} style={styles.rightIcon}>
          <Ionicons
            name={rightIcon}
            size={20}
            color={error ? theme.colors.error[600] : theme.colors.neutral[500]}
          />
        </TouchableOpacity>
      );
    }

    return null;
  };

  return (
    <View style={containerStyles}>
      {label && (
        <Text style={labelStyles}>
          {label}
          {required && <Text style={styles.required}> *</Text>}
        </Text>
      )}
      
      <View style={inputContainerStyles}>
        {renderLeftIcon()}
        
        <TextInput
          style={inputStyles}
          placeholder={placeholder}
          placeholderTextColor="#94a3b8"
          value={value}
          onChangeText={onChangeText}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
          editable={!disabled}
          secureTextEntry={isSecure}
          keyboardType={keyboardType}
          autoCapitalize={autoCapitalize}
          autoCorrect={autoCorrect}
          multiline={multiline}
          numberOfLines={numberOfLines}
          maxLength={maxLength}
        />
        
        {renderRightIcon()}
      </View>
      
      {(error || helperText) && (
        <Text style={[styles.helperText, error && styles.errorText]}>
          {error || helperText}
        </Text>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    marginBottom: theme.spacing[4],
  },
  
  label: {
    ...theme.textStyles.label,
    color: theme.colors.text.primary,
    marginBottom: theme.spacing[2],
  },
  errorLabel: {
    color: theme.colors.error[600],
  },
  disabledLabel: {
    color: theme.colors.neutral[400],
  },
  required: {
    color: theme.colors.error[600],
  },
  
  inputContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    borderRadius: 16,
    borderWidth: 2,
    borderColor: theme.colors.neutral[200],
    backgroundColor: theme.colors.background.light,
    shadowColor: theme.colors.shadow.light,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
    elevation: 3,
  },
  
  // Variants
  outlined: {
    borderWidth: 2,
    borderColor: '#e2e8f0',
    backgroundColor: '#ffffff',
  },
  filled: {
    borderWidth: 0,
    backgroundColor: '#f8fafc',
  },
  underlined: {
    borderWidth: 0,
    borderBottomWidth: 2,
    borderBottomColor: '#e2e8f0',
    backgroundColor: 'transparent',
    borderRadius: 0,
  },
  
  // Sizes
  sm: {
    height: theme.dimensions.input.sm.height,
    paddingHorizontal: theme.dimensions.input.sm.paddingHorizontal,
  },
  md: {
    height: theme.dimensions.input.md.height,
    paddingHorizontal: theme.dimensions.input.md.paddingHorizontal,
  },
  lg: {
    height: theme.dimensions.input.lg.height,
    paddingHorizontal: theme.dimensions.input.lg.paddingHorizontal,
  },
  
  // States
  focused: {
    borderColor: '#3b82f6',
    shadowColor: '#3b82f6',
    shadowOffset: { width: 0, height: 0 },
    shadowOpacity: 0.2,
    shadowRadius: 8,
    elevation: 5,
  },
  error: {
    borderColor: '#ef4444',
  },
  disabled: {
    backgroundColor: '#f1f5f9',
    opacity: 0.6,
  },
  
  input: {
    flex: 1,
    fontSize: 16,
    color: '#1e293b',
    paddingVertical: 0,
    fontWeight: '400',
    backgroundColor: 'transparent',
  },
  
  // Input sizes
  smInput: {
    fontSize: theme.typography.fontSize.sm,
  },
  mdInput: {
    fontSize: theme.typography.fontSize.base,
  },
  lgInput: {
    fontSize: theme.typography.fontSize.lg,
  },
  
  multilineInput: {
    textAlignVertical: 'top',
    paddingTop: theme.spacing[2],
  },
  
  leftIcon: {
    marginRight: theme.spacing[2],
  },
  rightIcon: {
    marginLeft: theme.spacing[2],
    padding: theme.spacing[1],
  },
  
  helperText: {
    ...theme.textStyles.caption,
    color: theme.colors.text.secondary,
    marginTop: theme.spacing[1],
  },
  errorText: {
    color: theme.colors.error[600],
  },
});
