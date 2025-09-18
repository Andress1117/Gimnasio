# Gym Management System - Backend API

A comprehensive Spring Boot backend application for managing a gym with 10 core entities and their complete CRUD operations.

## 🏗️ Architecture

The backend follows a layered architecture with:
- **Entities**: JPA entities representing database tables
- **Repositories**: Data access layer using Spring Data JPA
- **DTOs**: Data Transfer Objects for API requests/responses
- **Services**: Business logic layer
- **Controllers**: REST API endpoints

## 📊 Entities

### 1. User
- **Purpose**: Base user entity with authentication
- **Key Fields**: username, email, password, firstName, lastName, role
- **Roles**: ADMIN, TRAINER, MEMBER

### 2. Member
- **Purpose**: Gym members with detailed profiles
- **Key Fields**: memberNumber, dateOfBirth, fitnessGoals, medicalConditions
- **Status**: ACTIVE, INACTIVE, SUSPENDED, EXPIRED

### 3. Trainer
- **Purpose**: Gym trainers with specializations
- **Key Fields**: employeeId, specialization, certifications, hourlyRate
- **Status**: ACTIVE, INACTIVE, ON_LEAVE, TERMINATED

### 4. Equipment
- **Purpose**: Gym equipment management
- **Key Fields**: equipmentName, brand, model, serialNumber, maintenance
- **Types**: CARDIO, STRENGTH, FUNCTIONAL, FREE_WEIGHTS, ACCESSORIES

### 5. Membership
- **Purpose**: Member subscription plans
- **Key Fields**: membershipType, price, duration, accessHours
- **Features**: guestPasses, personalTraining, groupClasses

### 6. Payment
- **Purpose**: Payment processing and tracking
- **Key Fields**: amount, paymentType, paymentMethod, transactionId
- **Types**: MEMBERSHIP_FEE, PERSONAL_TRAINING, GROUP_CLASS, etc.

### 7. Workout
- **Purpose**: Individual workout sessions
- **Key Fields**: workoutName, workoutType, startTime, endTime
- **Types**: STRENGTH, CARDIO, FLEXIBILITY, FUNCTIONAL, SPORTS

### 8. Class
- **Purpose**: Group fitness classes
- **Key Fields**: className, classType, maxCapacity, trainer
- **Types**: YOGA, PILATES, SPINNING, ZUMBA, CROSSFIT, etc.

### 9. Schedule
- **Purpose**: Trainer availability schedules
- **Key Fields**: dayOfWeek, startTime, endTime, maxClientsPerSlot
- **Status**: ACTIVE, INACTIVE, TEMPORARILY_UNAVAILABLE

### 10. Attendance
- **Purpose**: Member check-in/check-out tracking
- **Key Fields**: checkInTime, checkOutTime, attendanceType
- **Types**: REGULAR_WORKOUT, PERSONAL_TRAINING, GROUP_CLASS

## 🚀 API Endpoints

### User Management
```
GET    /api/users                    - Get all users
POST   /api/users                    - Create user
GET    /api/users/{id}               - Get user by ID
PUT    /api/users/{id}               - Update user
DELETE /api/users/{id}               - Delete user
GET    /api/users/role/{role}        - Get users by role
GET    /api/users/search?name={name} - Search users by name
```

### Member Management
```
GET    /api/members                    - Get all members
POST   /api/members                    - Create member
GET    /api/members/{id}               - Get member by ID
PUT    /api/members/{id}               - Update member
DELETE /api/members/{id}               - Delete member
GET    /api/members/summaries          - Get member summaries
GET    /api/members/status/{status}    - Get members by status
```

### Trainer Management
```
GET    /api/trainers                    - Get all trainers
POST   /api/trainers                    - Create trainer
GET    /api/trainers/{id}               - Get trainer by ID
PUT    /api/trainers/{id}               - Update trainer
DELETE /api/trainers/{id}               - Delete trainer
GET    /api/trainers/available          - Get available trainers
GET    /api/trainers/status/{status}    - Get trainers by status
```

### Equipment Management
```
GET    /api/equipment                    - Get all equipment
POST   /api/equipment                    - Create equipment
GET    /api/equipment/{id}               - Get equipment by ID
PUT    /api/equipment/{id}               - Update equipment
DELETE /api/equipment/{id}               - Delete equipment
GET    /api/equipment/type/{type}        - Get equipment by type
GET    /api/equipment/maintenance-due    - Get equipment due for maintenance
```

### Attendance Management
```
POST   /api/attendance/check-in          - Check in member
PUT    /api/attendance/{id}/check-out    - Check out member
GET    /api/attendance                   - Get all attendance
GET    /api/attendance/{id}              - Get attendance by ID
GET    /api/attendance/member/{memberId} - Get member attendance
GET    /api/attendance/statistics        - Get attendance statistics
```

### Additional Endpoints
- **Memberships**: `/api/memberships/*`
- **Payments**: `/api/payments/*`
- **Workouts**: `/api/workouts/*`
- **Classes**: `/api/classes/*`
- **Schedules**: `/api/schedules/*`

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.5.6
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA / Hibernate
- **Security**: Spring Security
- **Validation**: Bean Validation
- **Build Tool**: Maven
- **Java Version**: 17

## 📋 Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Getting Started

### 1. Database Setup
```sql
CREATE DATABASE gym_management;
CREATE USER postgres WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE gym_management TO postgres;
```

### 2. Configuration
Update `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gym_management
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 3. Run the Application
```bash
cd backend
mvn spring-boot:run
```

The API will be available at: `http://localhost:8080/api`

## 📝 Features

### ✅ Implemented
- Complete CRUD operations for all 10 entities
- RESTful API design
- Data validation with Bean Validation
- Database relationships and constraints
- Security configuration
- Comprehensive DTOs for requests/responses
- Repository layer with custom queries
- Service layer with business logic

### 🔄 Future Enhancements
- JWT authentication
- Role-based access control
- Email notifications
- File upload for member photos
- Advanced reporting and analytics
- Payment gateway integration
- Mobile app support

## 📊 Database Schema

The application uses JPA entities with the following relationships:
- User (1:1) → Member
- User (1:1) → Trainer
- Member (1:N) → Membership
- Member (1:N) → Payment
- Member (1:N) → Workout
- Member (1:N) → Attendance
- Trainer (1:N) → Class
- Trainer (1:N) → Schedule
- Trainer (1:N) → Workout

## 🔒 Security

Currently configured for development with:
- Basic authentication
- CORS enabled for all origins
- All endpoints publicly accessible

For production, implement:
- JWT token authentication
- Role-based authorization
- HTTPS enforcement
- Input sanitization

## 📈 API Documentation

The API follows RESTful conventions:
- GET for retrieving data
- POST for creating resources
- PUT for updating resources
- DELETE for removing resources
- Proper HTTP status codes
- JSON request/response format

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.
