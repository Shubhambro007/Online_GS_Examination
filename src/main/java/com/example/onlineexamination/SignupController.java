package com.example.onlineexamination;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private Button signupButton;

    //Function to Handle Signup button
    @FXML
    public void handleSignup(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return;
        }

        try {
            Connection connection = Database.connectDB();
            String queryCheck = "SELECT * FROM users WHERE username=?";
            PreparedStatement preparedStatementCheck = connection.prepareStatement(queryCheck);
            preparedStatementCheck.setString(1, username);
            ResultSet resultSet = preparedStatementCheck.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Username already exists.");
                resultSet.close();
                preparedStatementCheck.close();
                connection.close();
                return;
            }

            resultSet.close();
            preparedStatementCheck.close();

            String queryInsert = "INSERT INTO users (name, surname, username, password, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementInsert = connection.prepareStatement(queryInsert);
            preparedStatementInsert.setString(1, name);
            preparedStatementInsert.setString(2, surname);
            preparedStatementInsert.setString(3, username);
            preparedStatementInsert.setString(4, password);
            preparedStatementInsert.setString(5, email);

            int rowsInserted = preparedStatementInsert.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Signup successful.");
                handleCancel(event);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to signup.");
            }

            preparedStatementInsert.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while signing up.");
        }
    }

    //Function to Handle Cancel button
    @FXML
    public void handleCancel(ActionEvent event) {
        try {
            Stage currentStage = (Stage) signupButton.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while loading the login screen.");
        }
    }
}
