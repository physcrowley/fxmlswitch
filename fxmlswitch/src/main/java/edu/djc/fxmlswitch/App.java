package edu.djc.fxmlswitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App that switches Scenes with FXML
 */
public class App extends Application {

    // TODO 3 explain why our Scene is a class field and not 
	//		a method variable
	private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    // TODO 2 explain why we need this method instead of just calling
    // 		setRoot(Parent root) directly on scene in the start() method
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    // Q: what FXMLLoader does and why it `throws IOException`
    // A: because the method tries to open a file
    
    // Q: why these two lines are in a method instead of
    //		directly as commands before stage.setScene() in start()
    // A: because the code is used in 2 different spots : start() and setRoot()
    //		and a method prevents copying and pasting code that needs to be
    //		reused in multiple places

    public static void main(String[] args) {
        launch();
    }

}