# 🎓 College Management System (Java + MySQL)

This is a **console-based College Management System** built using **Java, JDBC, and MySQL**, following **Object-Oriented Programming** principles and DAO architecture. It allows **students**, **faculty**, and **admins** to perform CRUD operations and login-based actions securely.

---

## 🚀 Features

- 🧑‍🎓 Student Module:
  - Register new students
  - View student profile
  - Login & update email/password

- 🧑‍🏫 Faculty Module:
  - Add, update, delete faculty records
  - View all faculty details

- 📚 Course Module:
  - Add, update, delete courses
  - Enroll students in courses

- 🔒 Authentication:
  - Role-based login (Student, Faculty, Admin)
  - Basic password hashing using a simple logic

- 🧩 Modular DAO Design:
  - Clean separation of concerns using DAO & Model packages

---

## 🧰 Technologies Used

| Tech          | Description                       |
|---------------|-----------------------------------|
| Java          | Core application logic            |
| JDBC          | Connect Java to MySQL             |
| MySQL         | Backend database storage          |
| DAO Pattern   | Separation of concerns (clean code)|
| IntelliJ IDEA | Code editor                       |
| Maven         | (Optional) Dependency management  |

---

## 🗂️ Project Structure

```bash
CollegeManagementSystem/
├── src/
│ ├── dao/
│ ├── model/
│ ├── util/
│ ├── Main.java
├── resources/
│ └── db_config.properties
└── README.md
```
---
## 🛠️ How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/college-management-system.git
   cd college-management-system
   ```
2. **Set up MySQL database**

- Create a database (e.g., college_db)
- Import tables using your schema script (if available)
3. **Update DB Configuration**

- In db_config.properties, set your MySQL username, password, and DB name.
4. **Run Main.java**
- Use IntelliJ IDEA or run via terminal:
```bash
javac Main.java
java Main
```
## 📸 Sample Credentials (for testing)

```bash
Admin
Username: admin1
Password: Pwd123

Student
ID: STU001
Password: STU001

Faculty
ID: FAC001
Password: FAC001
```
##🧠 Concepts Applied
- Java OOP: Abstraction, Inheritance, Encapsulation
- JDBC for DB communication
- Role-based Access Control
- Basic password hashing
- DAO & Model architecture

## 🙋‍♀️ Author
Subhaharini S

Java | SQL | Front-End | Full Stack Developer

📧 subhasenniappan@gmail.com

## ⭐️ If you like this project
Give it a ⭐️ on GitHub and feel free to fork it or suggest improvements!
---
Let me know if you'd like help pushing this project to GitHub or want the above as a downloadable `README.md` file.
