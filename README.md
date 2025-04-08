# Task Manager Application

A full-stack task management application built with Angular (frontend) and Spring Boot (backend) that allows users to create, read, update, and delete tasks.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [API Endpoints](#api-endpoints)
- [Screenshots](#screenshots)

## Features

- Create new tasks with title, description, and status
- View all tasks in a list/card format
- Update existing tasks
- Delete tasks
- Filter tasks by status (TODO, IN_PROGRESS, DONE)

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.1.0
- Spring Data JPA
- MySQL Database
- Maven

### Frontend
- Angular 16
- Angular Material
- TypeScript
- RxJS
- Angular Reactive Forms

## Project Structure

```
task-manager/
├── backend/               # Spring Boot application
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
└── frontend/              # Angular application
    ├── src/
    ├── package.json
    └── README.md
```

## Backend Setup

### Prerequisites

- Java JDK 17 or higher
- Maven
- MySQL

### Database Setup

1. Create a MySQL database:

```sql
CREATE DATABASE task_manager;
CREATE USER 'taskuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON task_manager.* TO 'taskuser'@'localhost';
FLUSH PRIVILEGES;
```

2. Configure database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
spring.datasource.username=taskuser
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Running the Backend

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The backend server will start on http://localhost:8080

## Frontend Setup

### Prerequisites

- Node.js (v16 or higher)
- npm (v8 or higher)

### Installing Dependencies

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install the required packages:
```bash
npm install
```

### Running the Frontend

1. Start the development server:
```bash
ng serve
```

2. Open your browser and navigate to http://localhost:4200

### Building for Production

```bash
ng build --configuration production
```

The build artifacts will be stored in the `dist/` directory.

## API Endpoints

### Tasks

| Method | URL                   | Description           | Request Body | Response          |
|--------|-----------------------|-----------------------|-------------|-------------------|
| GET    | /api/tasks            | Get all tasks         | -           | Array of Tasks    |
| GET    | /api/tasks/{id}       | Get task by ID        | -           | Task              |
| POST   | /api/tasks            | Create a new task     | Task object | Created Task      |
| PUT    | /api/tasks/{id}       | Update an existing task | Task object | Updated Task      |
| DELETE | /api/tasks/{id}       | Delete a task         | -           | 204 No Content    |

### Task Model

```json
{
  "id": 1,
  "title": "Complete project",
  "description": "Finish the task manager application",
  "status": "IN_PROGRESS",
  "createdAt": "2025-04-08T10:30:00"
}
```

## Screenshots

[Include screenshots of your application here]

## License

This project is licensed under the MIT License - see the LICENSE file for details.
