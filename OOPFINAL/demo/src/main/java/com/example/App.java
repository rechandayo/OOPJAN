package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.DatabaseInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/Login.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("Job Application Manager");
            primaryStage.setScene(scene);
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
