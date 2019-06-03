package org.openjfx;

import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main class for the calculator application
 * @author Damian Koss
 *
 */
public class MainApp extends Application {

    /**
     * Overloaded function for starting calculator interface. 
     * It loads fxml files for interface outlook and css file with styles
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Main function for starting the applications 
     * @param args - start application arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}