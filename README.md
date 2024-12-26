# Online_GS_Examination_System
**Project Title:** Online Examination System

**Description:** 
The Online Examination System is a robust application designed to facilitate secure and efficient management of online tests and assessments. It provides users with a seamless experience for logging in, signing up, updating profiles, and participating in multiple-choice question (MCQ) exams.

Key features of the project include:
- **User Authentication:** Secure login and signup processes to ensure authorized access.
- **Profile Management:** Users can update their profile details, including name, surname, email, and password.
- **Exam Participation:** Users can select answers for MCQs, with support for a timer and auto-submit functionality to ensure timely completion of exams.
- **Session Management:** The system handles session closure and logout operations to maintain security and data integrity.

This project offers a user-friendly interface with intuitive navigation and clear instructions, ensuring a smooth and engaging experience for all users. It is built using JavaFX for the front-end interface, with a MySQL database for backend data management. The application demonstrates best practices in software development, including secure handling of user data and efficient database interactions.

## Setup Instructions

### Configuration

1. Copy the `config-template.properties` file to `config.properties` in the root directory.
2. Edit `config.properties` to include your database details:
   ```properties
   db.url=jdbc:mysql://localhost:3306/yourdatabase
   db.username=yourusername
   db.password=yourpassword

