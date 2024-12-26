package com.example.onlineexamination;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    public Button cancel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button saveButton;

    // Fetch profile data when initializing the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = ExamAppController.getLoggedInUsername();
        try {
            Connection connection = Database.connectDB();
            String query = "SELECT name, surname, email FROM users WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                surnameField.setText(resultSet.getString("surname"));
                emailField.setText(resultSet.getString("email"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while fetching the profile data.");
        }
    }

    // Method to handle saving profile changes
    @FXML
    public void handleSave(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String username = ExamAppController.getLoggedInUsername();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return;
        }

        try {
            Connection connection = Database.connectDB();
            String query = "UPDATE users SET name=?, surname=?, email=?, password=? WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setString(5, username);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Profile updated successfully.");
                handleCancel(event);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile.");
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while updating the profile.");
        }
    }

    // Method to handle canceling the operation
    @FXML
    public void handleCancel(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DASHBOARD.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
