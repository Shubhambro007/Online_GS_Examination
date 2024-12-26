
package com.example.onlineexamination;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ExamAppController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Label forgotpassword;

    @FXML
    private Button signup;

    private static String loggedInUsername;

    //DATABASE TOOLS
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    public static void setLoggedInUsername(String username) { loggedInUsername = username; }

    public static String getLoggedInUsername() { return loggedInUsername; }


    //Function to Handle Login
    public void loginAdmin() {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        conn = Database.connectDB();

        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, username.getText());
            pstm.setString(2, password.getText());
            rs = pstm.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "PLEASE FILL BLANK FIELDS");
            } else {
                if (rs.next()) {
                    ExamAppController.setLoggedInUsername(username.getText());
                    login.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DASHBOARD.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "WRONG USERNAME/PASSWORD");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void close() {
        System.exit(0);
    }

    //Function to Handle Forgot Password
    public void forgotPassword() {
        try {
            // Hide the current login window
            login.getScene().getWindow().hide();

            // Load the UsernameInput.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UsernameInput.fxml"));
            Parent root = loader.load();

            // Show the UsernameInput window
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            // Wait for the user to enter the username and proceed
            UsernameInputController controller = loader.getController();
            stage.setOnHidden(e -> {
                String username = controller.getUsername();

                // Check if the username field is empty
                if (username == null || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "PLEASE FILL BLANK FIELDS");
                    // Reopen the UsernameInput window
                    forgotPassword();
                } else {
                    // Check if the username exists in the database
                    try {
                        String query = "SELECT * FROM users WHERE username=?";
                        conn = Database.connectDB();
                        pstm = conn.prepareStatement(query);
                        pstm.setString(1, username);
                        rs = pstm.executeQuery();

                        if (rs.next()) {
                            // Username exists, load the ForgotPassword.fxml
                            Stage currentStage = (Stage) forgotpassword.getScene().getWindow();
                            currentStage.close();
                            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
                            Parent root1 = loader1.load();

                            Stage stage1 = new Stage();
                            Scene scene1 = new Scene(root1);

                            stage1.initStyle(StageStyle.TRANSPARENT);
                            stage1.setScene(scene1);
                            stage1.show();
                        } else {
                            JOptionPane.showMessageDialog(null, "USERNAME DOES NOT EXIST");
                            // Reopen the UsernameInput window
                            forgotPassword();
                        }

                        rs.close();
                        pstm.close();
                        conn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "An error occurred while checking the username.");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Function to Handle Signup Button
    public void Signup() throws IOException {
        Stage currentStage = (Stage) signup.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root1 = loader1.load();

        Stage stage1 = new Stage();
        Scene scene1 = new Scene(root1);

        stage1.initStyle(StageStyle.TRANSPARENT);
        stage1.setScene(scene1);
        stage1.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
