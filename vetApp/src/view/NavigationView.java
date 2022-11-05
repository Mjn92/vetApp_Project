package view;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class NavigationView {
	
	protected Scene scene = null;
	protected VBox vbox = new VBox(10);
	protected HBox hbox = new HBox(10);
	
	protected Button home = new Button("Home");
	
	protected Button CustomerLookUp = new Button("Customer");
	
	protected Button CalanderLookUp = new Button("Calander");
	
	protected Button PetLookUp = new Button("Pet");
	
	protected Button Appointment = new Button("Appointment");
	
	
	public NavigationView() {
		
		hbox.getChildren().addAll(home, CustomerLookUp, PetLookUp, CalanderLookUp, Appointment);
		
		vbox.getChildren().add(hbox);
		vbox.setPadding(new Insets(10,10,10,10));
		
		scene = new Scene(vbox, 450,550);
		
	}
	
	public void setHomeButtonHandler(EventHandler<ActionEvent> handle) {
		home.setOnAction(handle);
	}
	public void setCutemerLookUpButtonHandler(EventHandler<ActionEvent> handle) {
		CustomerLookUp.setOnAction(handle);
	}
	public void setCalanderLookUpButtonHandler(EventHandler<ActionEvent> handler) {
		CalanderLookUp.setOnAction(handler);
	}
	public void setPetLookUpButtonHandler(EventHandler<ActionEvent> handler) {
		PetLookUp.setOnAction(handler);
	}
	public void setAppointmentLookUpButtonHandler(EventHandler<ActionEvent> handler) {
		Appointment.setOnAction(handler);
	}

}
