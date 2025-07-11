# 🌐 AI Translator

Веб-приложение для перевода текста с использованием искусственного интеллекта (**GigaChat API**). Поддерживает авторизацию пользователей и перевод между 7 языками.

![Java](https://img.shields.io/badge/Java-17-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green?logo=spring)

---

## 📚 Содержание

- [🛠 Технологии](#-технологии)
- [⚙️ Установка](#️-установка)
- [🚀 Использование](#-использование)
- [🔌 API](#-api)
- [👨‍💻 Разработка](#-разработка)
- [📄 Лицензия](#-лицензия)

---

## 🛠 Технологии

### Backend:
- Java 17  
- Spring Boot 3  
- Spring Security  
- JWT аутентификация  

### Frontend:
- HTML5, CSS3, JavaScript (Vanilla)  

### База данных:
- PostgreSQL  

### AI-интеграция:
- GigaChat API  

---

## ⚙️ Установка

### Требования:
- JDK 17+
- PostgreSQL 14+
- Maven 3.8+

### Шаги:

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/your-username/ai-translator.git
   cd ai-translator
   ```

2. **Настройте базу данных:**

   Создайте файл `application.yml` в `src/main/resources/`:

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
     secret: ваш-секретный-ключ-длиной-не-менее-32-символов
   ```

3. **Запустите приложение:**

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Откройте в браузере:**

   ```
   http://localhost:8080
   ```

---

## 🚀 Использование

### 🔐 Авторизация:

- Зарегистрируйте нового пользователя
- Войдите с вашими учетными данными

### 🌍 Перевод текста:

- Выберите исходный и целевой язык (или автоопределение)
- Введите текст
- Нажмите **"Translate"**

---

## 🔌 API

| Метод | Путь                | Описание                     | Аутентификация |
|-------|---------------------|------------------------------|----------------|
| POST  | `/auth/signIn`      | Вход в систему               | ❌             |
| POST  | `/auth/registration`| Регистрация пользователя     | ❌             |
| POST  | `/auth/refresh`     | Обновление токена            | ✅ (Refresh)   |
| POST  | `/ai/translate`     | Перевод текста через AI      | ✅ (Access)    |

---

## 👨‍💻 Разработка

### ✅ Тестирование:

```bash
./mvnw test
```

### 📦 Сборка:

```bash
./mvnw clean package
```

### 🤝 Вклад:

1. Форкните репозиторий
2. Создайте ветку (`git checkout -b feature/ваша-фича`)
3. Сделайте коммит (`git commit -m 'Добавил фичу'`)
4. Запушьте (`git push origin feature/ваша-фича`)
5. Откройте Pull Request

---


**Автор:** Антон Шконда 
**Версия:** 1.0.0
