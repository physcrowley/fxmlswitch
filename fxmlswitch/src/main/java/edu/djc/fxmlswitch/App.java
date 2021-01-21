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

	private static Scene scene;
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

    @Override
    public void start(Stage stage) throws IOException {
    	scene = new Scene(loadFXML("primary"), 640, 480);
    	
    	//we placed the app.css file in the same output folder as App.class
    	
    	// doesn't work because Java cannot resolve the complete file path
    	//scene.getStylesheets().add("app.css");
    	//System.out.println("app.css");
    	
    	// works using the current class as a reference point and 
    	// .toExternalForm() to generate a valid file path
    	//scene.getStylesheets().add(App.class.getResource("app.css").toExternalForm());
    	scene.getStylesheets().add(this.getClass().getResource("app.css").toExternalForm());
    	//System.out.println(this.getClass());
    	//System.out.println(this.getClass().getResource("app.css").toExternalForm());
    	
    	stage.setScene(scene);
        stage.show();
    }
    
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    // Q : why we need this method instead of just calling
    // 		setRoot(Parent root) directly on scene in the start() method
    // A : because this method is called statically (using the class name instead
    //		of using an object) in both PrimaryController and SecondaryController
    
    
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
    
    // Q: why can't we replace App.class with the more general this.getClass() in
    //		this context?
    // A: the `static` identifier means that the `loadFXML` method can only refer
    //		to the class itself (the definitions here) and not an object created
    //		by the class (an object can be referred to using `this.`)

    public static void main(String[] args) {
        launch();
    }

}