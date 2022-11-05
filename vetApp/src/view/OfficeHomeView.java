package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

public class OfficeHomeView extends NavigationView {
	
	
	public OfficeHomeView() {
		super();
		
		StackPane titlePane = new StackPane();
		Text title = new Text("Welcome");
		title.setFont(new Font(30));
		titlePane.getChildren().add(title);
		
		Text infoOnCustomer = new Text("Customer Tab: look up, create, or modify customer infomation \n requries Customer ID \nAlso able to look up customer's pet names \n ");
		Text InfoOnPet = new Text("Pet Tab: look up, create, or modify pet infomation, requires pet ID \n ");
		Text infoOnCalander = new Text("Calander Tab: To check what times are avialble, requires: date \n ");
		Text infoOnAppointment = new Text("Appointment Tab: set up or modify appointments \n requries: date ID, date, time, customer ID, and pet ID"
				 + "\n \n The file will be on the desktop and used to send to customer.  ");
		
		VBox infobox = new VBox(10);
		infobox.setPadding(new Insets(10,10,10,10));
		infobox.getChildren().addAll(infoOnCustomer, InfoOnPet, infoOnCalander, infoOnAppointment);
		
		
		
		
		vbox.getChildren().addAll(titlePane, infobox);
		
	}
	
	
	public Scene getScene() {
		return scene;
	}
	public void setOfficeHomeButtonEventHandler(EventHandler<ActionEvent> handler) {
		home.setOnAction(handler);
	}
}
