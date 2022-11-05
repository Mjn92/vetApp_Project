package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OfficeSignInView extends signInView {
	
	
		protected GridPane OM = new GridPane();

		protected Label user = new Label("Office User:");
        protected Label pass = new Label("Password:");
        
        protected TextField userIn = new TextField();
        protected TextField passIn = new TextField();
        
        protected Button SignIn = new Button("Sign In");

	public OfficeSignInView() {
		super();
		OM.setVgap(10);
		OM.setHgap(5);
		OM.setPadding(new Insets(10,10,10,10));
        
		
        user.setLabelFor(userIn);
        user.setMnemonicParsing(true);
        pass.setLabelFor(passIn);
        pass.setMnemonicParsing(true);
       		
        
        OM.add(user, 0, 0);
        OM.add(userIn, 2, 0);
        OM.add(pass, 0, 1);
        OM.add(passIn, 2, 1);
        OM.add(SignIn, 0, 3);
        GridPane.setHalignment(user, HPos.RIGHT);
        GridPane.setHalignment(pass, HPos.RIGHT);
        vbox.getChildren().add(OM);
        
	}
	
	public Scene getScene() {
		return scene;
	}
	public void setSignInButtonEventHandler(EventHandler<ActionEvent> handler) {
		SignIn.setOnAction(handler);
	}
	public String getUserId() {
		return userIn.getText();
	}
	public String getPassword() {
		return passIn.getText();
	}

}
