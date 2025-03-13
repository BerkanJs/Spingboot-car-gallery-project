🚗 Car Gallery Project

Car Gallery Project is a backend application built with Spring Boot, designed to manage car sales transactions. This project implements a robust architecture following SOLID principles, and it integrates real-time currency exchange rates from the Central Bank of Turkey API for accurate TL to USD conversions.


🌐 Technologies Used

Java

Spring Boot (MVC, JPA, Validation, Security)

Hibernate

JWT Authentication

Postgresql

Maven

📂 Project Structure

├── config               # Application configuration files
├── controller           # REST API controllers
├── dto                  # Data Transfer Objects
├── enums                # Enum types
├── exception            # Custom exception handling
├── handler              # Global exception handlers
├── jwt                  # JWT token management
├── model                # JPA entities
├── repository           # Database access layer
├── service              # Business logic layer
├── starter              # Application starter
├── utils                # Utility classes (Date formatting, etc.)

🛠️ Features

Car Management: Add, update, delete, and list cars

Customer Management: Create and manage customers

Car Purchase: Complete car sales transactions

Currency Conversion: Get real-time USD exchange rates from Turkey’s Central Bank API

Spring Validation: Ensure data integrity with field validation

Exception Handling: Global and custom exception management

Authentication: Secure endpoints with JWT tokens


💡 Key Concepts Implemented

SOLID Principles

MVC Architecture

JPA with Hibernate for ORM

Aggregation and Composition Relationships

Spring Security with JWT Authentication

Central Bank API Integration for currency exchange

🚀 How to Run

# Clone the repository
git clone https://github.com/yourusername/car-gallery-project.git

# Navigate into the project
cd car-gallery-project

# Build and run the Spring Boot backend
mvn spring-boot:run


