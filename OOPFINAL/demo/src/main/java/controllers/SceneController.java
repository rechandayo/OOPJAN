package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    private Stage stage;

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void switchToLogin() {
        loadScene("/Login.fxml");
    }

    public void switchToSignup() {
        loadScene("/Signup.fxml");
    }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
