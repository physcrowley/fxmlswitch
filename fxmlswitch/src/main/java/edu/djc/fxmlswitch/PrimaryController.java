package edu.djc.fxmlswitch;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

	// @FXML is to refer to names in the linked FXML file
	// The @ specifies a notation that affects how the compiler
	//		(`javac`) runs
    @FXML
    private void switchToSecondary() throws IOException {
    	// switchToSecondary is the value given to the  `onAction` attribute of
    	// a button in the primary.fxml file
    	App.setRoot("secondary");
    }
}
