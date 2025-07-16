package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

public class SignupController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField schoolField;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private void signUp() {
        String name = nameField.getText().trim();
        String school = schoolField.getText().trim();
        String username = userField.getText().trim();
        String password = passField.getText().trim();

        // Check if any field is empty
        if (name.isEmpty() || school.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        // Create file path
        File file = new File("data/users.txt");

        try {
            // Ensure the 'data/' folder exists
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Ensure the file exists
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            showAlert("Error creating user data file.");
            e.printStackTrace();
            return;
        }

        // Check for duplicate username
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username)) {
                    showAlert("Username already exists. Try a different one.");
                    return;
                }
            }
        } catch (IOException e) {
            showAlert("Error reading user data.");
            e.printStackTrace();
            return;
        }

        // Save the user information
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + password + "," + name + "," + school);
            writer.newLine();
            showAlert("Signup successful!");
            clearFields();
        } catch (IOException e) {
            showAlert("Error saving user info.");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        nameField.clear();
        schoolField.clear();
        userField.clear();
        passField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}