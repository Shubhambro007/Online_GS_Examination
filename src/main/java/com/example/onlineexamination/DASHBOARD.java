package com.example.onlineexamination;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DASHBOARD implements Initializable {

    @FXML
    private Button start;

    public void close() {
        System.exit(0);
    }

    //Function to Start Exam
    public void Start() throws IOException {
        start.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamWindow.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    //Function to Update Profie
    public void profile() throws IOException {
        start.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

