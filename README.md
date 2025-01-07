# Online_GS_Examination_System
**Project Title:** Online Examination System

**Description:** 
The Online Examination System is a robust application designed to facilitate secure and efficient management of online tests and assessments. It provides users with a seamless experience for logging in, signing up, updating profiles, and participating in multiple-choice question (MCQ) exams.

To ensure the project runs correctly, you need to update the libraries by adding them from the global libraries to the current project. Follow these steps:

1. **Open the `Project Structure` window in IntelliJ IDEA:**
   - Go to **File** > **Project Structure**.

2. **Navigate to `Platform Settings` and select `Global Libraries`:**
   - In the left sidebar, expand **Platform Settings** and click on **Global Libraries**.

3. **Locate the required library (e.g., `mysql-connector-j-9.1.0`):**
   - Find the required library in the list.

4. **Add the library to the current project:**
   - Right-click on the library and select **Add to Project** > **Copy to Project Libraries**.
   - Choose the appropriate module to which you want to add the library.

5. **Apply the changes and click `OK`:**

   This ensures that all necessary libraries are correctly linked to your project.

### Key Features
1. **User Authentication and Authorization:**
   - **Secure Login:** Users can log in using their email and password. The system checks the credentials against the stored data in the MySQL database.
   - **Signup:** New users can register by providing their name, surname, email, and password. The system validates the input and stores the data securely.
   - **Password Encryption:** User passwords are encrypted before storing in the database to ensure security.

2. **Profile Management:**
   - **View Profile:** Users can view their profile details, including name, surname, and email.
   - **Edit Profile:** Users can update their profile information. The changes are validated and updated in the database.

3. **Exam Participation:**
   - **MCQ Exam Interface:** The system provides an intuitive interface for answering multiple-choice questions (MCQs). Users can select answers and navigate between questions.
   - **Timer and Auto-Submit:** A timer is displayed during the exam to ensure that users complete the test within the allotted time. The system automatically submits the answers when the time expires.

4. **Session Management:**
   - **Session Handling:** The system maintains user sessions to keep track of logged-in users. Sessions are securely managed to prevent unauthorized access.
   - **Logout:** Users can log out of the system, which invalidates their session and ensures security.

### Implementation Details

1. **Front-End (JavaFX):**
   - **User Interface:** The user interface is built using JavaFX, providing a modern and responsive design. It includes various screens such as login, signup, profile, and exam.
   - **FXML Files:** The layout of the screens is defined using FXML files, which are loaded and controlled by corresponding Java controllers.

2. **Back-End (Java):**
   - **Controllers:** Java controllers handle user interactions and business logic. They process user input, interact with the database, and update the UI accordingly.
   - **Database Access:** JDBC (Java Database Connectivity) is used to interact with the MySQL database. SQL queries are executed to fetch, insert, update, and delete data.

3. **Database (MySQL):**
   - **User Table:** Stores user details such as name, surname, email, and encrypted password.
   - **Question Table:** Stores MCQ questions along with options and correct answers.
   - **Exam Table:** Stores exam details, including user responses and scores.

4. **Security:**
   - **Password Encryption:** User passwords are encrypted using a secure hashing algorithm before storing in the database.
   - **Input Validation:** Input from users is validated on both the client and server sides to prevent SQL injection and other attacks.
   - **Session Management:** Sessions are securely managed using session tokens, ensuring that only authorized users can access their accounts.

### **Requirements**:

To run the **Online Examination System** project, make sure you have the following tools and dependencies installed:

#### 1. **Java Development Kit (JDK)**:
   - Ensure you have **JDK 8 or higher** installed. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

#### 2. **IntelliJ IDEA**:
   - Download and install **IntelliJ IDEA** from [JetBrains' official website](https://www.jetbrains.com/idea/). This is the recommended IDE for Java development.
   - Make sure the **JavaFX** plugin is enabled in IntelliJ IDEA.

#### 3. **JavaFX Framework**:
   - **JavaFX** is used to create the front-end of the application. If not already installed, follow these steps:
     - **For IntelliJ IDEA**:
       - Go to **File > Project Structure > Libraries**.
       - Download and add **JavaFX SDK** from [Gluon](https://gluonhq.com/products/javafx/).
       - Under the **VM options** in IntelliJ, add the following:
         ```
         --module-path <path_to_your_javafx_sdk>/lib --add-modules javafx.controls,javafx.fxml
         ```
     - **For other IDEs or command-line builds**, ensure that the **JavaFX SDK** is included in your `classpath` or build configuration.

#### 4. **MySQL Database**:
   - **JDBC Driver**: You need to install the **MySQL JDBC driver** to connect to the database.
     - Download **MySQL Connector/J** (e.g., `mysql-connector-j-9.1.0`) from the [official MySQL website](https://dev.mysql.com/downloads/connector/j/).
   - **MySQL Server**:
     - Use an **online MySQL database** such as **Aiven** or run your own MySQL server locally.
     - Update the **database connection settings** (URL, username, password) in the `database` file or code configuration.
     - Example connection URL:
       ```java
       'String url = "jdbc:mysql://your-database-url:3306/database_name";
       String user = "your-username";
       String password = "your-password";
       Connection conn = DriverManager.getConnection(url, user, password);'
       ```

---

### **Setup Instructions**:

1. **Download Dependencies**:
   - Download the necessary libraries (JavaFX, JDBC, etc.) and ensure they are added to the project as described above.

2. **Configure MySQL Database**:
   - If using the **Aiven Console** for MySQL:
     - Log in to the **Aiven Console**, create a MySQL instance, and get the connection details (host, port, username, password).
     - Update your `database` connection settings in your backend code to match these credentials.

3. **Build and Run**:
   - Open **IntelliJ IDEA** and import the project.
   - Ensure the libraries are correctly linked by navigating to **File > Project Structure** and confirming that all dependencies (like JDBC, JavaFX) are added to the project.
   - Build the project and run it.

4. **Test Functionality**:
   - Test each of the features: logging in, signing up, profile management, and taking an MCQ exam.

---

### **Future Enhancements**:

- **Analytics and Reporting**: Implement features to generate detailed reports on exam performance and user activity.
- **Additional Question Types**: Support for other types of questions like true/false, short answers, or essays.
- **Mobile Support**: Develop a mobile-friendly version or dedicated mobile app for exams.
