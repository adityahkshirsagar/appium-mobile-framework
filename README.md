# Mobile Automation Framework

A comprehensive Appium-based mobile automation testing framework for Android applications.

## Features

- Page Object Model implementation
- TestNG integration for test management
- ExtentReports for detailed test reporting
- Database validation utilities
- Screenshot capture functionality
- MongoDB integration
- Excel data-driven testing support

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Android SDK
- Appium Server
- Node.js and npm

## Setup

1. Clone the repository
2. Update the app path in test files to point to your APK
3. Configure database connection settings in properties files
4. Update device capabilities in BaseClass.java

## Running Tests

```bash
mvn clean test
```

## Project Structure

- `src/main/java/` - Framework utilities and page objects
- `src/test/java/` - Test classes
- `src/test/resources/` - Test data and configuration files
