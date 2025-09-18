# FitHub Gym - Frontend

Frontend moderno para el sistema de gestión de gimnasio FitHub, construido con React Native y Expo. Funciona tanto en dispositivos móviles (iOS/Android) como en web.

## 🚀 Características

- **Multiplataforma**: Funciona en iOS, Android y Web
- **Diseño Moderno**: UI/UX moderna con tema personalizable
- **Gestión de Estado**: Zustand para manejo de estado global
- **Navegación**: React Navigation con tabs y stack navigation
- **Componentes Reutilizables**: Sistema de componentes UI consistente
- **Autenticación**: Sistema de login seguro con tokens
- **Responsive**: Adaptable a diferentes tamaños de pantalla

## 📱 Pantallas Incluidas

- **Login**: Pantalla de autenticación con diseño moderno
- **Dashboard**: Resumen general con estadísticas y acciones rápidas
- **Miembros**: Gestión completa de miembros del gimnasio
- **Clases**: Administración de clases y horarios
- **Equipos**: Control de equipos y mantenimiento
- **Perfil**: Configuración de usuario y logout

## 🛠️ Tecnologías Utilizadas

- **React Native**: Framework principal
- **Expo**: Herramientas de desarrollo y deployment
- **TypeScript**: Tipado estático
- **React Navigation**: Navegación entre pantallas
- **Zustand**: Gestión de estado
- **Expo Linear Gradient**: Gradientes para UI
- **React Native Vector Icons**: Iconografía
- **Date-fns**: Manipulación de fechas

## 📦 Instalación

### Prerrequisitos

- Node.js (versión 16 o superior)
- npm o yarn
- Expo CLI: `npm install -g @expo/cli`
- Para desarrollo móvil: Expo Go app en tu dispositivo

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd frontend
   ```

2. **Instalar dependencias**
   ```bash
   npm install
   # o
   yarn install
   ```

3. **Configurar variables de entorno**
   ```bash
   # Crear archivo .env en la raíz del proyecto
   echo "API_BASE_URL=http://localhost:8080/api" > .env
   ```

4. **Iniciar el servidor de desarrollo**
   ```bash
   # Para desarrollo móvil
   npm start
   
   # Para desarrollo web
   npm run web
   
   # Para Android
   npm run android
   
   # Para iOS
   npm run ios
   ```

## 🔧 Configuración

### Backend API

Asegúrate de que tu backend esté ejecutándose en `http://localhost:8080` o actualiza la URL en:
- `src/services/api.ts` (línea 17)

### Temas y Colores

Los temas se pueden personalizar en:
- `src/theme/colors.ts` - Paleta de colores
- `src/theme/typography.ts` - Tipografía
- `src/theme/spacing.ts` - Espaciado y dimensiones

## 📱 Uso

### Desarrollo Móvil

1. Instala Expo Go en tu dispositivo móvil
2. Ejecuta `npm start`
3. Escanea el código QR con Expo Go
4. La app se cargará en tu dispositivo

### Desarrollo Web

1. Ejecuta `npm run web`
2. Abre tu navegador en `http://localhost:19006`
3. La app se ejecutará en el navegador

### Build para Producción

```bash
# Build para web
npm run build:web

# Build para móvil (requiere EAS CLI)
npx eas build --platform all
```

## 🎨 Personalización

### Colores

Modifica los colores en `src/theme/colors.ts`:

```typescript
export const colors = {
  primary: {
    600: '#0ea5e9', // Color principal
    // ... más variaciones
  },
  // ... otros colores
};
```

### Componentes

Los componentes UI están en `src/components/ui/` y pueden ser personalizados según tus necesidades.

### Navegación

La navegación se configura en `src/navigation/AppNavigator.tsx`.

## 🔐 Autenticación

El sistema de autenticación utiliza:
- Tokens JWT almacenados de forma segura
- Interceptores de Axios para requests automáticos
- Gestión de estado con Zustand
- Logout automático en caso de token expirado

## 📊 Gestión de Estado

Se utiliza Zustand para el manejo de estado global:
- `authStore`: Autenticación y usuario
- `memberStore`: Gestión de miembros
- Más stores pueden ser agregados según necesidad

## 🚀 Deployment

### Web

```bash
npm run build:web
# Los archivos se generarán en la carpeta web-build/
```

### Móvil

```bash
# Instalar EAS CLI
npm install -g @expo/eas-cli

# Configurar proyecto
eas build:configure

# Build para producción
eas build --platform all
```

## 🐛 Solución de Problemas

### Problemas Comunes

1. **Error de conexión con el backend**
   - Verifica que el backend esté ejecutándose
   - Revisa la URL en `src/services/api.ts`

2. **Problemas con dependencias**
   ```bash
   rm -rf node_modules package-lock.json
   npm install
   ```

3. **Errores de TypeScript**
   ```bash
   npx tsc --noEmit
   ```

## 📝 Scripts Disponibles

- `npm start` - Inicia el servidor de desarrollo
- `npm run web` - Ejecuta en navegador web
- `npm run android` - Ejecuta en Android
- `npm run ios` - Ejecuta en iOS
- `npm run build:web` - Build para web
- `npm test` - Ejecuta tests
- `npm run lint` - Ejecuta linter

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 📞 Soporte

Para soporte técnico o preguntas:
- Crear un issue en el repositorio
- Contactar al equipo de desarrollo

---

**¡Disfruta desarrollando con FitHub Gym! 💪**
