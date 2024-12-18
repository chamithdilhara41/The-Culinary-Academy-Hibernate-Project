package lk.ijse.theculinaryacademyhibernateproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/theculinaryacademyhibernateproject/view/LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
