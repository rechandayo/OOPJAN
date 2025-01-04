package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.App;

public class DashboardController {

    @FXML
    private Label welcomeMessage;

    @FXML
    private TableView<Application> applicationTable;

    @FXML
    private TableColumn<Application, String> titleColumn;

    @FXML
    private TableColumn<Application, String> companyColumn;

    @FXML
    private TableColumn<Application, String> salaryColumn;

    private SceneController sceneController;

    public DashboardController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void initialize(String username) {
        welcomeMessage.setText("Welcome, " + username + "!");
        loadApplications();
    }

    private void loadApplications() {
        List<Application> applications = new ArrayList<>();
        try (Connection connection = App.getConnection()) {
            String query = "SELECT title, company, salary FROM applications";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applications.add(new Application(
                    resultSet.getString("title"),
                    resultSet.getString("company"),
                    resultSet.getString("salary")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        applicationTable.getItems().setAll(applications);
    }

    public void handleLogout() {
        sceneController.switchToLogin();
    }

    public static class Application {
        private final String title;
        private final String company;
        private final String salary;

        public Application(String title, String company, String salary) {
            this.title = title;
            this.company = company;
            this.salary = salary;
        }

        public String getTitle() {
            return title;
        }

        public String getCompany() {
            return company;
        }

        public String getSalary() {
            return salary;
        }
    }
}
