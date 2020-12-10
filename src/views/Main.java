package views;

import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/mainApp.fxml"));
        primaryStage.setTitle("Hello World");
        root.getStylesheets().add("Css.css");
        primaryStage.setScene(new Scene(root));



        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
