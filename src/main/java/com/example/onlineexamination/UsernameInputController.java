package com.example.onlineexamination;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsernameInputController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button submitButton;

    @FXML
    private Button closeButton;

    //Function to Handle Submit Button
    @FXML
    public void handleSubmit(ActionEvent event) {
        String username = usernameField.getText();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your username.");
            return;
        }

        try {
            Connection connection = Database.connectDB();
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Username exists, proceed to forgot password screen
                Stage currentStage = (Stage) submitButton.getScene().getWindow();
                currentStage.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Username does not exist.");
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while checking the username.");
        }
    }

    // Function to Handle Close Button
    @FXML
    public void handleCancel(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }

    // Getter for Username Field
    public String getUsername() {
        return usernameField.getText();
    }
}
