package com.example.onlineexamination;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ExamWindowController {

    @FXML
    private Label questionLabel;

    @FXML
    private VBox optionsBox;

    @FXML
    private Button optionButton1, optionButton2, optionButton3, optionButton4, submitButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Button logout;

    private List<Question> questions; // List to hold questions from the database
    private int currentQuestionIndex = 0; // Current question index
    private int score = 0; // User's score
    private Timer timer; // Timer for the quiz
    private int timeRemaining = 600; // Time in seconds (10 minutes = 600 seconds)

    //Initializing the timer
    public void initialize() {
        startTimer();
        questions = fetchQuestionsFromDatabase();
        if (questions.size() > 0) {
            showQuestion(questions.get(currentQuestionIndex));
        } else {
            questionLabel.setText("No questions found in the database.");
        }
    }

    // Start the 10-minute timer
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    int minutes = timeRemaining / 60;
                    int seconds = timeRemaining % 60;
                    Platform.runLater(() -> timerLabel.setText(String.format("Time Remaining: %02d:%02d", minutes, seconds)));
                } else {
                    timer.cancel();
                    Platform.runLater(() -> handleSubmit()); // Auto-submit when time is up
                }
            }
        }, 0, 1000); // Run every second
    }

    // Fetch questions from the database
    private List<Question> fetchQuestionsFromDatabase() {
        List<Question> data = new ArrayList<>();

        // Use Database connection class
        Connection connection = Database.connectDB();

        try {
            // Query to get questions and answers
            String query = "SELECT question, correct, option2, option3, option4 FROM ExamQuestions ORDER BY RAND() LIMIT 20";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Loop through result set and create Question objects
            while (resultSet.next()) {
                String questionText = resultSet.getString("question");
                String correctAnswer = resultSet.getString("correct");
                String option2 = resultSet.getString("option2");
                String option3 = resultSet.getString("option3");
                String option4 = resultSet.getString("option4");

                List<String> options = new ArrayList<>();
                options.add(correctAnswer);
                options.add(option2);
                options.add(option3);
                options.add(option4);
                java.util.Collections.shuffle(options);

                data.add(new Question(questionText, correctAnswer, options));
            }

            // Close connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    // Show a question on the screen
    private void showQuestion(Question question) {
        questionLabel.setText(question.getQuestion());

        List<String> options = question.getOptions();
        optionButton1.setText(options.get(0));
        optionButton1.setOnAction(e -> handleOptionClick(options.get(0)));

        optionButton2.setText(options.get(1));
        optionButton2.setOnAction(e -> handleOptionClick(options.get(1)));

        optionButton3.setText(options.get(2));
        optionButton3.setOnAction(e -> handleOptionClick(options.get(2)));

        optionButton4.setText(options.get(3));
        optionButton4.setOnAction(e -> handleOptionClick(options.get(3)));
    }

    // Handle option click
    private void handleOptionClick(String selectedAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++; // Increment score
        }

        // Move to the next question
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            showQuestion(questions.get(currentQuestionIndex));
        } else {
            handleSubmit(); // Auto-submit when all questions are answered
        }

        // Update the score label
        scoreLabel.setText("Score: " + score);
    }

    // Handle test submission
    @FXML
    private void handleSubmit() {
        // Stop the timer
        if (timer != null) {
            timer.cancel();
        }

        // Display the final score
        questionLabel.setText("Test Complete! Your final score is: " + score);


    }

    public void logout() throws IOException {

        // Show a message box to confirm logout and return to the login page
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Stage currentStage = (Stage) logout.getScene().getWindow();
            currentStage.close();
        }
        JOptionPane.showMessageDialog(null, "LOGOUT SUCCESSFULLY ", "LOGOUT", JOptionPane.PLAIN_MESSAGE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }

    public void close() {
        System.exit(0);
    }
}

