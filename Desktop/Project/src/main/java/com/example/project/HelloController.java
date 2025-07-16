package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class HelloController {
    @FXML
    public TextField userIdField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Label negga;

    @FXML
    private Button Click;
    private Button Mail;
    private Button Call;

    @FXML
    private void login(ActionEvent event) throws IOException {
        String enteredUsername = userIdField.getText().trim();
        String enteredPassword = passwordField.getText().trim();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            showAlert("Please enter both username and password.");
            return;
        }

        boolean userFound = false;
        boolean passwordCorrect = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(enteredUsername)) {
                    userFound = true;
                    if (parts[1].equals(enteredPassword)) {
                        passwordCorrect = true;
                    }
                    break;
                    }
                }
            }
         catch (IOException e) {
            e.printStackTrace();
            showAlert("Error reading user data.");
            return;
        }

        if (!userFound) {
            showAlert("No account found with this User ID.");
        } else if (!passwordCorrect) {
            showAlert("Incorrect password.");
        } else {
            showAlert("Login successful!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    @FXML
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void closeprgrm() {
        System.exit(0);
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgot_password.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleGetMail(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mail.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private void handleCall(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("call.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void openFacebook(ActionEvent event) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.google.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void click(ActionEvent actionEvent) {
        try {
            login(actionEvent);  // Now properly wrapped
        } catch (Exception e) {
            showAlert("Unexpected error: " + e.getMessage());
        }
    }

    @FXML
    public void close(javafx.event.ActionEvent actionEvent) {
        closeprgrm();
    }

    @FXML
    public void forgotPassword(javafx.event.ActionEvent actionEvent) throws IOException {
        handleForgotPassword(actionEvent);
    }
    @FXML
    public void getMail(javafx.event.ActionEvent actionEvent) throws IOException {
        handleGetMail(actionEvent);

    }
    @FXML
    public void getFbId(javafx.event.ActionEvent actionEvent) throws IOException {
        openFacebook(actionEvent);
    }
    @FXML
    public void getCallNumber(javafx.event.ActionEvent actionEvent) throws IOException {
        handleCall(actionEvent);
    }
}
