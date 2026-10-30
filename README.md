# CRUD Spring Boot Project (crudSpringBoot)

A simple and robust CRUD (Create, Read, Update, Delete) application built with **Spring Boot**, **Spring Data JPA**, and **MySQL**. This project is designed to manage student records efficiently.

---

## Features
* **Create**: Add new student records.
* **Read**: Fetch all students or find a specific student by ID.
* **Update**: Modify existing student information.
* **Delete**: Remove a student record from the database.

---

## Tech Stack
* **Language**: Java
* **Framework**: Spring Boot
* **ORM**: Spring Data JPA / Hibernate
* **Database**: MySQL
* **Build Tool**: Maven / Gradle

---

## Configuration & Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd crudSpringBoot
   ```

2. **Configure Database:**
   Create a MySQL database named `student_details`:
   ```sql
   CREATE DATABASE student_details;
   ```

3. **Update `application.properties`:**
   Open `src/main/resources/application.properties` and configure your database credentials:
   ```properties
   spring.application.name=crudSpringBoot

   # MySQL Connection Details
   spring.datasource.url=jdbc:mysql://localhost:3306/student_details?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD

   # JPA / Hibernate Configurations
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.open-in-view=false
   ```

4. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   *(Or run it directly from your IDE like IntelliJ IDEA)*

---

## API Endpoints

| Operation | HTTP Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **Get All** | `GET` | `/api/students` | Retrieve list of all students |
| **Get By ID** | `GET` | `/api/students/{id}` | Retrieve a single student by ID |
| **Create** | `POST` | `/api/students` | Create a new student record |
| **Update** | `PUT` | `/api/students/{id}` | Update an existing student |
| **Delete** | `DELETE` | `/api/students/{id}` | Delete a student record |

---

## Author
**Partha Sarker**  
Full Stack & Backend Engineer