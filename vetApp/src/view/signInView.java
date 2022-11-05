package view;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class signInView {
	
	protected Scene scene = null;
	protected VBox vbox = new VBox(10);
	protected HBox hbox = new HBox(10);
	
	protected ToggleGroup btg = new ToggleGroup();
	
	//protected RadioButton btOffice = new RadioButton("Office Sign In");
	//protected RadioButton btCustomer = new RadioButton("Customer Sign In");
	
	public signInView() {
		
		//btOffice.setToggleGroup(btg);
		//btCustomer.setToggleGroup(btg);
		
		//hbox.getChildren().addAll(btOffice, btCustomer);
		
		vbox.getChildren().add(hbox);
		vbox.setPadding(new Insets(10,10,10,10));
		
		scene = new Scene(vbox, 300,200);
		
	}
	
	

}
