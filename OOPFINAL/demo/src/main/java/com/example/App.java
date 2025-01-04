package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controllers.LoginController;
import controllers.SceneController;
import database.DatabaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Connection connection;

    public static void main(String[] args) {
        // Initialize database connection
        initializeDatabase();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            SceneController sceneController = new SceneController(primaryStage);

            // Step 2: Set up FXMLLoader and Controller
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/Login.fxml"));
            LoginController loginController = new LoginController(sceneController); // Pass SceneController
            loader.setController(loginController);

            // Step 3: Load the Scene
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Job Application Manager");
            primaryStage.setScene(scene);

            // Step 4: Show the Stage
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Failed to load the initial scene.");
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:job_applications.db");
            System.out.println("Database connected successfully.");

            // Initialize schema and populate data
            DatabaseInitializer.initializeDatabase(connection);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
