package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {
    @FXML
    private Button StudyTimer;
    @FXML
    private void handleStudyTimer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("studyTimer.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void studyTimer(javafx.event.ActionEvent actionEvent) throws IOException {
        handleStudyTimer(actionEvent);

    }
}
