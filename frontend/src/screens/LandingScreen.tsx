import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  Dimensions,
  Linking,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { LinearGradient } from 'expo-linear-gradient';
import { Ionicons } from '@expo/vector-icons';
import { Button } from '../components/ui';
import { theme } from '../theme';

const { width, height } = Dimensions.get('window');

export default function LandingScreen() {
  const navigation = useNavigation();

  const handleGetStarted = () => {
    navigation.navigate('Login' as never);
  };

  const handleLearnMore = () => {
    // Scroll to features section
    console.log('Learn more');
  };

  return (
    <ScrollView style={styles.container} showsVerticalScrollIndicator={false}>
      {/* Hero Section */}
      <LinearGradient
        colors={['#667eea', '#764ba2', '#f093fb']}
        start={{ x: 0, y: 0 }}
        end={{ x: 1, y: 1 }}
        style={styles.heroSection}
      >
        <View style={styles.heroContent}>
          {/* Navigation */}
          <View style={styles.navigation}>
            <View style={styles.logo}>
              <Ionicons name="fitness" size={32} color="white" />
              <Text style={styles.logoText}>FitHub</Text>
            </View>
            <View style={styles.navLinks}>
              <TouchableOpacity style={styles.navLink}>
                <Text style={styles.navLinkText}>Features</Text>
              </TouchableOpacity>
              <TouchableOpacity style={styles.navLink}>
                <Text style={styles.navLinkText}>Pricing</Text>
              </TouchableOpacity>
              <TouchableOpacity style={styles.navLink}>
                <Text style={styles.navLinkText}>Contact</Text>
              </TouchableOpacity>
              <Button
                title="Sign In"
                onPress={handleGetStarted}
                variant="outline"
                size="sm"
                style={styles.signInButton}
              />
            </View>
          </View>

          {/* Hero Content */}
          <View style={styles.heroMain}>
            <Text style={styles.heroTitle}>
              Transform Your Gym Management
            </Text>
            <Text style={styles.heroSubtitle}>
              The most comprehensive gym management system designed for modern fitness businesses. 
              Streamline operations, boost member engagement, and grow your business.
            </Text>
            <View style={styles.heroButtons}>
              <Button
                title="Get Started Free"
                onPress={handleGetStarted}
                variant="primary"
                size="lg"
                style={styles.primaryButton}
                icon="arrow-forward"
                iconPosition="right"
              />
              <Button
                title="Watch Demo"
                onPress={handleLearnMore}
                variant="ghost"
                size="lg"
                style={styles.secondaryButton}
                icon="play-circle"
                iconPosition="left"
              />
            </View>
          </View>

          {/* Hero Image/Stats */}
          <View style={styles.heroStats}>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>10K+</Text>
              <Text style={styles.statLabel}>Active Members</Text>
            </View>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>500+</Text>
              <Text style={styles.statLabel}>Gyms Worldwide</Text>
            </View>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>99.9%</Text>
              <Text style={styles.statLabel}>Uptime</Text>
            </View>
          </View>
        </View>
      </LinearGradient>

      {/* Features Section */}
      <View style={styles.featuresSection}>
        <View style={styles.sectionHeader}>
          <Text style={styles.sectionTitle}>Why Choose FitHub?</Text>
          <Text style={styles.sectionSubtitle}>
            Everything you need to run a successful gym business
          </Text>
        </View>

        <View style={styles.featuresGrid}>
          <View style={styles.featureCard}>
            <View style={styles.featureIcon}>
              <Ionicons name="people" size={32} color="#667eea" />
            </View>
            <Text style={styles.featureTitle}>Member Management</Text>
            <Text style={styles.featureDescription}>
              Complete member profiles, check-ins, membership tracking, and automated renewals.
            </Text>
          </View>

          <View style={styles.featureCard}>
            <View style={styles.featureIcon}>
              <Ionicons name="calendar" size={32} color="#667eea" />
            </View>
            <Text style={styles.featureTitle}>Class Scheduling</Text>
            <Text style={styles.featureDescription}>
              Advanced scheduling system with instructor management and capacity tracking.
            </Text>
          </View>

          <View style={styles.featureCard}>
            <View style={styles.featureIcon}>
              <Ionicons name="card" size={32} color="#667eea" />
            </View>
            <Text style={styles.featureTitle}>Payment Processing</Text>
            <Text style={styles.featureDescription}>
              Secure payment processing with multiple payment methods and automated billing.
            </Text>
          </View>

          <View style={styles.featureCard}>
            <View style={styles.featureIcon}>
              <Ionicons name="analytics" size={32} color="#667eea" />
            </View>
            <Text style={styles.featureTitle}>Analytics & Reports</Text>
            <Text style={styles.featureDescription}>
              Comprehensive analytics and reporting to help you make data-driven decisions.
            </Text>
          </View>

          <View style={styles.featureCard}>
            <View style={styles.featureIcon}>
              <Ionicons name="fitness" size={32} color="#667eea" />
            </View>
            <Text style={styles.featureTitle}>Equipment Tracking</Text>
            <Text style={styles.featureDescription}>
              Monitor equipment usage, maintenance schedules, and availability in real-time.
            </Text>
          </View>

          <View style={styles.featureCard}>
            <View style={styles.featureIcon}>
              <Ionicons name="phone-portrait" size={32} color="#667eea" />
            </View>
            <Text style={styles.featureTitle}>Mobile App</Text>
            <Text style={styles.featureDescription}>
              Native mobile apps for iOS and Android with member self-service features.
            </Text>
          </View>
        </View>
      </View>

      {/* CTA Section */}
      <LinearGradient
        colors={['#667eea', '#764ba2']}
        style={styles.ctaSection}
      >
        <View style={styles.ctaContent}>
          <Text style={styles.ctaTitle}>Ready to Transform Your Gym?</Text>
          <Text style={styles.ctaSubtitle}>
            Join thousands of gym owners who trust FitHub to manage their business.
          </Text>
          <Button
            title="Start Free Trial"
            onPress={handleGetStarted}
            variant="secondary"
            size="lg"
            style={styles.ctaButton}
            icon="rocket"
            iconPosition="left"
          />
        </View>
      </LinearGradient>

      {/* Footer */}
      <View style={styles.footer}>
        <View style={styles.footerContent}>
          <View style={styles.footerSection}>
            <View style={styles.footerLogo}>
              <Ionicons name="fitness" size={24} color="#667eea" />
              <Text style={styles.footerLogoText}>FitHub</Text>
            </View>
            <Text style={styles.footerDescription}>
              The complete gym management solution for modern fitness businesses.
            </Text>
          </View>

          <View style={styles.footerSection}>
            <Text style={styles.footerSectionTitle}>Product</Text>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Features</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Pricing</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>API</Text>
            </TouchableOpacity>
          </View>

          <View style={styles.footerSection}>
            <Text style={styles.footerSectionTitle}>Company</Text>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>About</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Blog</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Careers</Text>
            </TouchableOpacity>
          </View>

          <View style={styles.footerSection}>
            <Text style={styles.footerSectionTitle}>Support</Text>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Help Center</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Contact</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.footerLink}>
              <Text style={styles.footerLinkText}>Status</Text>
            </TouchableOpacity>
          </View>
        </View>

        <View style={styles.footerBottom}>
          <Text style={styles.footerCopyright}>
            © 2024 FitHub. All rights reserved.
          </Text>
          <View style={styles.footerSocial}>
            <TouchableOpacity style={styles.socialLink}>
              <Ionicons name="logo-facebook" size={20} color="#667eea" />
            </TouchableOpacity>
            <TouchableOpacity style={styles.socialLink}>
              <Ionicons name="logo-twitter" size={20} color="#667eea" />
            </TouchableOpacity>
            <TouchableOpacity style={styles.socialLink}>
              <Ionicons name="logo-linkedin" size={20} color="#667eea" />
            </TouchableOpacity>
            <TouchableOpacity style={styles.socialLink}>
              <Ionicons name="logo-instagram" size={20} color="#667eea" />
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#ffffff',
  },
  
  // Hero Section
  heroSection: {
    minHeight: height,
    paddingTop: 60,
  },
  heroContent: {
    flex: 1,
    paddingHorizontal: 24,
  },
  navigation: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 60,
  },
  logo: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  logoText: {
    fontSize: 24,
    fontWeight: '700',
    color: 'white',
    marginLeft: 8,
  },
  navLinks: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  navLink: {
    marginHorizontal: 16,
  },
  navLinkText: {
    fontSize: 16,
    color: 'white',
    fontWeight: '500',
  },
  signInButton: {
    marginLeft: 16,
  },
  heroMain: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 20,
  },
  heroTitle: {
    fontSize: 48,
    fontWeight: '800',
    color: 'white',
    textAlign: 'center',
    marginBottom: 24,
    lineHeight: 56,
  },
  heroSubtitle: {
    fontSize: 20,
    color: 'rgba(255, 255, 255, 0.9)',
    textAlign: 'center',
    marginBottom: 40,
    lineHeight: 28,
    maxWidth: 600,
  },
  heroButtons: {
    flexDirection: 'row',
    gap: 16,
  },
  primaryButton: {
    minWidth: 200,
  },
  secondaryButton: {
    minWidth: 160,
  },
  heroStats: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    paddingVertical: 40,
    borderTopWidth: 1,
    borderTopColor: 'rgba(255, 255, 255, 0.2)',
  },
  statItem: {
    alignItems: 'center',
  },
  statNumber: {
    fontSize: 32,
    fontWeight: '700',
    color: 'white',
    marginBottom: 4,
  },
  statLabel: {
    fontSize: 14,
    color: 'rgba(255, 255, 255, 0.8)',
    fontWeight: '500',
  },

  // Features Section
  featuresSection: {
    paddingVertical: 80,
    paddingHorizontal: 24,
    backgroundColor: '#f8fafc',
  },
  sectionHeader: {
    alignItems: 'center',
    marginBottom: 60,
  },
  sectionTitle: {
    fontSize: 36,
    fontWeight: '700',
    color: '#1a202c',
    textAlign: 'center',
    marginBottom: 16,
  },
  sectionSubtitle: {
    fontSize: 18,
    color: '#64748b',
    textAlign: 'center',
    maxWidth: 600,
  },
  featuresGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  featureCard: {
    width: '30%',
    backgroundColor: 'white',
    padding: 32,
    borderRadius: 16,
    marginBottom: 32,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 12,
    elevation: 5,
  },
  featureIcon: {
    width: 64,
    height: 64,
    borderRadius: 32,
    backgroundColor: '#f0f4ff',
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 24,
  },
  featureTitle: {
    fontSize: 20,
    fontWeight: '600',
    color: '#1a202c',
    marginBottom: 12,
  },
  featureDescription: {
    fontSize: 16,
    color: '#64748b',
    lineHeight: 24,
  },

  // CTA Section
  ctaSection: {
    paddingVertical: 80,
    paddingHorizontal: 24,
  },
  ctaContent: {
    alignItems: 'center',
  },
  ctaTitle: {
    fontSize: 36,
    fontWeight: '700',
    color: 'white',
    textAlign: 'center',
    marginBottom: 16,
  },
  ctaSubtitle: {
    fontSize: 18,
    color: 'rgba(255, 255, 255, 0.9)',
    textAlign: 'center',
    marginBottom: 32,
    maxWidth: 500,
  },
  ctaButton: {
    minWidth: 200,
  },

  // Footer
  footer: {
    backgroundColor: '#1a202c',
    paddingVertical: 60,
    paddingHorizontal: 24,
  },
  footerContent: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 40,
  },
  footerSection: {
    flex: 1,
    marginRight: 32,
  },
  footerLogo: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 16,
  },
  footerLogoText: {
    fontSize: 20,
    fontWeight: '700',
    color: 'white',
    marginLeft: 8,
  },
  footerDescription: {
    fontSize: 16,
    color: '#a0aec0',
    lineHeight: 24,
  },
  footerSectionTitle: {
    fontSize: 18,
    fontWeight: '600',
    color: 'white',
    marginBottom: 16,
  },
  footerLink: {
    marginBottom: 8,
  },
  footerLinkText: {
    fontSize: 16,
    color: '#a0aec0',
  },
  footerBottom: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingTop: 32,
    borderTopWidth: 1,
    borderTopColor: '#2d3748',
  },
  footerCopyright: {
    fontSize: 14,
    color: '#a0aec0',
  },
  footerSocial: {
    flexDirection: 'row',
    gap: 16,
  },
  socialLink: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: '#2d3748',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
