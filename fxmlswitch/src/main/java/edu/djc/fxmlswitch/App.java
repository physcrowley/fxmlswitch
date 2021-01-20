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

    /* Q : why our Scene is a class field and not 
     * 		a method variable
	 * A : So it can be visible to ALL this class' methods without
	 * 		being passed as a parameter. This is critical, because we
	 * 		need the `scene` in both the start() and setRoot() methods.
	 * 		Also, note that `scene` is private, so only accessible
	 * 		by methods in this class, but `setRoot` is not, so other
	 * 		classes in the same package can use it. That's why
	 * 		PrimaryController and SecondaryController call the
	 * 		`setRoot` method instead of directly modifying the scene with
	 * 		App.scene.setRoot(App.loadFXML("primary"))... which is uglier
	 * 		anyway.
	*/
	private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    // Q : why we need this method instead of just calling
    // 		setRoot(Parent root) directly on scene in the start() method
    // A : because this method is called statically (using the class name instead
    //		of using an objet) in both PrimaryController and SecondaryController
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