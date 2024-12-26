package com.example.onlineexamination;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ForgotPasswordController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button cancelButton;

    public void handleResetPassword(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return;
        }

        try {
            Connection connection = Database.connectDB();
            String query = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Password reset successful.");
                handleCancel(actionEvent);
            } else {
                JOptionPane.showMessageDialog(null, "Username not found.");
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while resetting the password.");
        }
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException {
        try {

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
