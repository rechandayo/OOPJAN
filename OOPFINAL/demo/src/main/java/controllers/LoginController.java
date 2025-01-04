package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.App;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private Label errorMessage;

    private SceneController sceneController;

    public LoginController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showError("Email and password are required.");
            return;
        }

        try (Connection connection = App.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password); // Ideally hashed in real-world scenarios

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Login successful, redirect to main dashboard
                System.out.println("Login successful!");
                // Implement scene switching here
            } else {
                showError("Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("An error occurred while logging in.");
        }
    }

    public void handleSignupRedirect() {
        sceneController.switchToSignup();
    }

    private void showError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }
}
