package com.example.onlineexamination;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import java.io.IOException;

//Main Class ExamApp to Start Application
public class ExamApp extends Application {
    private double x = 0;
    private double y = 0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8);
        });

        root.setOnMouseReleased(event -> stage.setOpacity(1));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}