import { colors, gradients, shadows } from './colors';
import { typography, textStyles } from './typography';
import { spacing, borderRadius, borderWidth, dimensions } from './spacing';

export const theme = {
  colors,
  gradients,
  shadows,
  typography,
  textStyles,
  spacing,
  borderRadius,
  borderWidth,
  dimensions,
};

export type Theme = typeof theme;

// Light theme
export const lightTheme = {
  ...theme,
  colors: {
    ...colors,
    background: {
      ...colors.background,
      primary: colors.background.light,
      secondary: colors.neutral[50],
      tertiary: colors.neutral[100],
    },
    text: {
      ...colors.text,
      primary: colors.text.primary,
      secondary: colors.text.secondary,
      tertiary: colors.text.muted,
    },
    border: {
      ...colors.border,
      primary: colors.border.light,
      secondary: colors.neutral[200],
    },
  },
};

// Dark theme
export const darkTheme = {
  ...theme,
  colors: {
    ...colors,
    background: {
      ...colors.background,
      primary: colors.background.dark,
      secondary: colors.dark[800],
      tertiary: colors.dark[700],
    },
    text: {
      ...colors.text,
      primary: colors.text.light,
      secondary: colors.dark[300],
      tertiary: colors.dark[400],
    },
    border: {
      ...colors.border,
      primary: colors.border.dark,
      secondary: colors.dark[600],
    },
  },
};

export default theme;
