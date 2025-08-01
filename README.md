# 🌐 AI Translator

A web application for text translation using artificial intelligence (**GigaChat API**). Supports user authentication and translation between 7 languages.

![Java](https://img.shields.io/badge/Java-17-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green?logo=spring)

---

## 📚 Table of Contents

- [🛠 Technologies](#-technologies)
- [⚙️ Installation](#️-installation)
- [🚀 Usage](#-usage)
- [🔌 API](#-api)
- [👨‍💻 Development](#-development)
- [📄 License](#-license)

---

## 🛠 Technologies

### Backend:
- Java 17  
- Spring Boot 3  
- Spring Security  
- JWT Authentication  

### Frontend:
- HTML5, CSS3, JavaScript (Vanilla)  

### Database:
- PostgreSQL  

### AI Integration:
- GigaChat API  

---

## ⚙️ Installation

### Requirements:
- JDK 17+
- PostgreSQL 14+
- Maven 3.8+

### Steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/ai-translator.git
   cd ai-translator

2. **Configure the database:**

    Create an `application.yml` in `src/main/resources/`:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/aitranslator
       username: postgres
       password: postgres
     jpa:
       hibernate:
         ddl-auto: update

   jwt:
     secret: your-secret-key-with-at-least-32-characters
   ```

3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Open in browser:**

   ```
   http://localhost:8080
   ```

---

## 🚀 Usage

### 🔐 Authentication:

- Register a new user

- Log in with your credentials

### 🌍 Text Translation:

- Select source and target languages (or auto-detect)
- Enter text
- Click "Translate"

---

## 🔌 API

| Method | Path                | Description                  | Authentication |
|--------|---------------------|------------------------------|----------------|
| POST   | `/auth/signIn`      | User login                   | ❌             |
| POST   | `/auth/registration`| User registration            | ❌             |
| POST   | `/auth/refresh`     | Token refresh                | ✅ (Refresh)   |
| POST   | `/ai/translate`     | Translate text via AI        | ✅ (Access)    |
