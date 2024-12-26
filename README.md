# Online_GS_Examination_System
**Project Title:** Online Examination System

**Description:** 
The Online Examination System is a robust application designed to facilitate secure and efficient management of online tests and assessments. It provides users with a seamless experience for logging in, signing up, updating profiles, and participating in multiple-choice question (MCQ) exams.

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

### Future Enhancements

1. **Analytics and Reporting:**
   - Generate detailed reports and analytics on exam performance, user activity, and system usage.

2. **Additional Question Types:**
   - Support for other question types such as short answer, true/false, and essay questions to make the exam system more versatile.

3. **Mobile Support:**
   - Develop a mobile-friendly version or a dedicated mobile app to allow users to take exams on their smartphones and tablets.

### Setup Instructions

#### Configuration

1. Copy the `config-template.properties` file to `config.properties` in the root directory.
2. Edit `config.properties` to include your database details:
   ```properties
   db.url=jdbc:mysql://localhost:3306/yourdatabase
   db.username=yourusername
   db.password=yourpassword

   currently we're using Online MySQL database from Aiven Console...Inorder to Use your own DataBase modify url,username,password accordingly in config.properties file

