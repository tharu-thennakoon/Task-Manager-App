# 📝 Task Manager – Spring Boot Backend

Welcome to the **Task Manager API**, a robust and cleanly structured Spring Boot backend designed for managing tasks efficiently. This RESTful service allows you to perform CRUD operations on tasks, with additional features like filtering, input validation, and error handling – all designed to integrate seamlessly with an Angular frontend.

---

## 📌 Features at a Glance

- ✅ **CRUD Operations** – Create, Read, Update, and Delete tasks
- 📂 **Filter by Status** – View tasks based on their current status
- 🔐 **Input Validation** – Ensure clean data with meaningful error messages
- 🌐 **CORS Support** – Configured to communicate securely with Angular frontend
- 📅 **Auto Defaults** – Task status and creation date are auto-assigned
- 🧠 **Service-Oriented Architecture** – Clean separation of concerns using layered architecture
- 🧩 **Consistent API Responses** – Clear and descriptive HTTP statuses

---

## 📁 Project Structure
src 
    ├── controller # REST endpoints for task management 
    ├── service # Business logic layer 
    ├── repository # Spring Data JPA interface 
    ├── dto # Data Transfer Objects 
    ├── entity # JPA entity for Task 
    ├── exception # Custom exceptions and global handler 
    └── config # CORS and other configurations

## 📌 API Endpoints

| Method | Endpoint                            | Description                        |
|--------|-------------------------------------|------------------------------------|
| GET    | `/api/tasks`                        | Retrieve all tasks                 |
| GET    | `/api/tasks/{id}`                   | Get a specific task by ID          |
| GET    | `/api/tasks/status/{status}`        | Filter tasks by status             |
| POST   | `/api/tasks`                        | Create a new task                  |
| PUT    | `/api/tasks/{id}`                   | Update an existing task            |
| DELETE | `/api/tasks/{id}`                   | Delete a task                      |

---

## 📦 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Hibernate**
- **Lombok**
- **Maven**

---

## 🛠️ Getting Started

### ⚙️ Prerequisites

- Java 17+
- MySQL database
- Maven
