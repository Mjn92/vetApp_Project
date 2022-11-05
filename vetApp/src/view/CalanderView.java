package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.CustomerLookUpView.Info;

public class CalanderView extends NavigationView {
	
	private CheckBox cb1 = new CheckBox("08:00 ");
	private CheckBox cb2 = new CheckBox("09:00 ");
	private CheckBox cb3 = new CheckBox("10:00");
	private CheckBox cb4 = new CheckBox("11:00");
	private CheckBox cb5 = new CheckBox("12:00");
	private CheckBox cb6 = new CheckBox("13:00");
	private CheckBox cb7 = new CheckBox("14:00");
	private CheckBox cb8 = new CheckBox("15:00");
	private CheckBox cb9 = new CheckBox("16:00");
	private CheckBox cb10 = new CheckBox("17:00");
	
	private TextField tcb1 = new TextField();
	private TextField tcb2 = new TextField();
	private TextField tcb3 = new TextField();
	private TextField tcb4 = new TextField();
	private TextField tcb5 = new TextField();
	private TextField tcb6 = new TextField();
	private TextField tcb7 = new TextField();
	private TextField tcb8 = new TextField();
	private TextField tcb9 = new TextField();
	private TextField tcb10 = new TextField();
	
	private Button gobu = new Button("Look Up");
	private Button clear = new Button("Clear");
	private Label connLabel = new Label();
	private Connection connection = null;
	private String dateS = new String();
 
	
	public CalanderView(){
		super();
		
		VBox appbox = new VBox(10);
		appbox.setPadding(new Insets(10,10,10,10));

        Label piDate = new Label("Pick a Date ");

        DatePicker datePicker = new DatePicker();

        datePicker.setOnAction(e -> {
        	LocalDate date = datePicker.getValue();
        	piDate.setText(date.toString());
        	dateS = date.toString();
        	 System.out.println(dateS);
        });
        
        gobu.setOnAction(e -> goButton());
        clear.setOnAction(e -> clear());
        
        HBox hcb1 = new HBox(10);
        hcb1.getChildren().addAll(cb1, tcb1);
        HBox hcb2 = new HBox(10);
        hcb2.getChildren().addAll(cb2, tcb2);
        HBox hcb3 = new HBox(10);
        hcb3.getChildren().addAll(cb3, tcb3);
        HBox hcb4 = new HBox(10);
        hcb4.getChildren().addAll(cb4, tcb4);
        HBox hcb5 = new HBox(10);
        hcb5.getChildren().addAll(cb5, tcb5);
        HBox hcb6 = new HBox(10);
        hcb6.getChildren().addAll(cb6, tcb6);
        HBox hcb7 = new HBox(10);
        hcb7.getChildren().addAll(cb7, tcb7);
        HBox hcb8 = new HBox(10);
        hcb8.getChildren().addAll(cb8, tcb8);
        HBox hcb9 = new HBox(10);
        hcb9.getChildren().addAll(cb9, tcb9);
        HBox hcb10 = new HBox(10);
        hcb10.getChildren().addAll(cb10, tcb10);
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(gobu, clear);
        
        
        VBox cbbx = new VBox(10);
        cbbx.setPadding(new Insets(10,10,10,10));
        cbbx.getChildren().addAll(hcb1,hcb2,hcb3,hcb4,hcb5,hcb6,hcb7,hcb8,hcb9,hcb10, buttons);
        

        appbox.getChildren().addAll(connLabel,datePicker, piDate, cbbx);

       
        try {
			Class.forName("com.mysql.jdbc.Driver");
			connLabel.setText("Driver loaded");
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb", "root", "");
			connLabel.setText("Connected to database");

			
		}
		catch(ClassNotFoundException e) {
			connLabel.setText("Driver not loaded");
		}
		catch(SQLException e) {
			connLabel.setText("Database connection failed");
		}	
        
        vbox.getChildren().add(appbox);

	}
	
	public void goButton() {
		
		try {
			
			Statement stmt = connection.createStatement();
			String cmd ="select * "
					+ "from mydb.calander_appointment " 
					+ "where pet_visit_date = '" +  dateS + "';";
			
			// System.out.println(cmd);

			ResultSet results = stmt.executeQuery(cmd);

			while(results.next()) {
				
				String timest = results.getString(3);
				
			
			if (timest.equalsIgnoreCase("08:00:00")) {
		        cb1.indeterminateProperty().set(true);
		        tcb1.setText("Filled");
			}
			if (timest.equalsIgnoreCase("09:00:00")) {
		        cb2.indeterminateProperty().set(true);
		        tcb2.setText("Filled");
			}
			if (timest.equalsIgnoreCase("10:00:00")) {
		        cb3.indeterminateProperty().set(true);
		        tcb3.setText("Filled");
			}
			if (timest.equalsIgnoreCase("11:00:00")) {
		        cb4.indeterminateProperty().set(true);
		        tcb4.setText("Filled");
			}
			if (timest.equalsIgnoreCase("12:00:00")) {
		        cb5.indeterminateProperty().set(true);
		        tcb5.setText("Filled");
			}
			if (timest.equalsIgnoreCase("13:00:00")) {
		        cb6.indeterminateProperty().set(true);
		        tcb6.setText("Filled");
			}
			if (timest.equalsIgnoreCase("14:00:00")) {
		        cb7.indeterminateProperty().set(true);
		        tcb7.setText("Filled");
			}
			if (timest.equalsIgnoreCase("15:00:00")) {
		        cb8.indeterminateProperty().set(true);
		        tcb8.setText("Filled");
			}
			if (timest.equalsIgnoreCase("16:00:00")) {
		        cb9.indeterminateProperty().set(true);
		        tcb9.setText("Filled");
			}
			if (timest.equalsIgnoreCase("17:00:00")) {
		        cb10.indeterminateProperty().set(true);
		        tcb10.setText("Filled");
			}
			
			}
			
			
			//System.out.println(results);

		}
		catch(SQLException e) {
			connLabel.setText("Record not Inserted");
		}
		
	}
	public void clear() {
		 cb1.indeterminateProperty().set(false);
	     tcb1.setText("");
	     cb2.indeterminateProperty().set(false);
	     tcb2.setText("");
	     cb3.indeterminateProperty().set(false);
	     tcb3.setText("");
	     cb4.indeterminateProperty().set(false);
	     tcb4.setText("");
	     cb5.indeterminateProperty().set(false);
	     tcb5.setText("");
	     cb6.indeterminateProperty().set(false);
	     tcb6.setText("");
	     cb7.indeterminateProperty().set(false);
	     tcb7.setText("");
	     cb8.indeterminateProperty().set(false);
	     tcb8.setText("");
	     cb9.indeterminateProperty().set(false);
	     tcb9.setText("");
	     cb10.indeterminateProperty().set(false);
	     tcb10.setText("");
	     
	}
	public void setCalanderpButtonEventHandler(EventHandler<ActionEvent> handler) {
		home.setOnAction(handler);
	}
	public Scene getScene() {
		return scene;
	}

}
