package view;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AppointmentView extends NavigationView {
	
	private Label connLabel = new Label();
	private Connection connection = null;
	private Button btLookUp = new Button("Lookup");
	private Button btCreate = new Button("Create");
	private Button btModify = new Button("Modify");
	private Button btClear = new Button("Clear");
	
	private TextField petId = new TextField();
	private TextField ownerId = new TextField();
	private TextField datetf = new TextField();
	private TextField timetf = new TextField();
	private TextField dateIdTf = new TextField();
	private String dateS = new String();
	
	private TextField petName = new TextField();
	
	public AppointmentView() {
		super();
		
		petId.setPrefColumnCount(2);
		ownerId.setPrefColumnCount(2);
		datetf.setPrefColumnCount(8);
		timetf.setPrefColumnCount(5);
		dateIdTf.setPrefColumnCount(2);
		
		
		btLookUp.setOnAction(e -> LookUp());
		btCreate.setOnAction(e -> Create());
		btModify.setOnAction(e -> Modify());
		btClear.setOnAction(e -> Clear());
		
		VBox appbox = new VBox(10);
		appbox.setPadding(new Insets(10,10,10,10));

        Label piDate = new Label("Pick a Date ");

        DatePicker datePicker = new DatePicker();

        datePicker.setOnAction(e -> {
        	Clear();
        	LocalDate date = datePicker.getValue();
        	piDate.setText(date.toString());
        	dateS = date.toString();
        	System.out.println(dateS);
        	datetf.setText(dateS); 
        });
        
        HBox hdId = new HBox(5);
		hdId.getChildren().addAll(new Label ("Appointment Id: "), dateIdTf);
		
		HBox hdate = new HBox(5);
		hdate.getChildren().addAll(new Label ("Date of Appointment: "), datetf);
		
		HBox htime = new HBox(5);
		htime.getChildren().addAll(new Label ("Time of Appointment: "), timetf);
        
        HBox hpId = new HBox(5);
        hpId.getChildren().addAll(new Label ("Pet Id: "), petId, new Label ("Owner's ID: "), ownerId);
		
		HBox hbPetName = new HBox(5);
		hbPetName.getChildren().addAll(new Label ("Pet's Namne: "), petName);
		
		HBox hbtn = new HBox(10);
		hbtn.getChildren().addAll(btLookUp, btCreate, btModify, btClear);
		
		appbox.getChildren().addAll(connLabel, datePicker, hdId, hdate, htime, hpId, hbtn);
		
		
		vbox.getChildren().add(appbox);
		
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
	}
	void LookUp() {
		try {
			Statement stmt = connection.createStatement();
			String cmd = "select * from mydb.calander_appointment where calander_id = '" + dateIdTf.getText() + "';";
			
			//System.out.println(cmd);
			
			ResultSet results = stmt.executeQuery(cmd);
			results.next();
			System.out.println(results);
			String datest = results.getString(2);
			String timest = results.getString(3);
			String cust = results.getString(4);
			String pet = results.getString(5);
			System.out.println(datest + ", " + timest + ", " + cust + ", " + pet);
			datetf.setText(results.getString(2));
			timetf.setText(results.getString(3));
			ownerId.setText(results.getString(4));
			petId.setText(results.getString(5));
			
			
			
		}
		catch(SQLException e) {
			connLabel.setText("Record not found");
		}
	}
	void Create() {
		try {
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			Statement stmt3 = connection.createStatement();
			
			String dateId = dateIdTf.getText();
			String datest = datetf.getText();
			String timest = timetf.getText();
			String oId = ownerId.getText();
			String pId = petId.getText();
			
			
			String cmd ="insert into mydb.calander_appointment values(' "+ dateId + "', '"
			+ datest + "', '" + timest + ":00', '" + oId + "', '" + pId +  "' );";
			System.out.println(cmd);
			stmt.execute(cmd);
			
			
			String cmd2 = "select * from mydb.pet_info where pet_id = '" + pId + "';";
			System.out.println(cmd2);
			
			ResultSet results = stmt2.executeQuery(cmd2);
			
			results.next();
			String petName = results.getString(2);
			
			
			System.out.println(petName);
			String cmd3 = "SELECT * FROM mydb.customer where customer_id = '" + oId + "';";
			System.out.println(cmd3);
			
			ResultSet results2 = stmt3.executeQuery(cmd3);
			
			
			System.out.println("hello ");
			results2.next();
			String OwnerName = results2.getString(2);
			
			System.out.println(OwnerName);
			
			
	        Path myPath = Paths.get("C:/Users/Nicol/Desktop/Appointment.txt");

	        if (Files.exists(myPath)) {
	            
	          System.out.println("File already exists");
	        } else {
	            
	            Files.createFile(myPath);
	           System.out.println("File created");
	        }
	        
	        List<String> lines = new ArrayList<>();
	        
	        lines.add("Pet Name: " + petName);
	        lines.add("Owenr Name: " + OwnerName);
	        lines.add("Please show up 10 to 15 minutes before the appointed time.");
	        lines.add("");
	        lines.add("Date of Appointment: " + datest);
	        lines.add("Time of Appointmetn: " + timest + ":00");
	        
	        
	        Files.write(myPath, lines, StandardCharsets.UTF_8, 
	              StandardOpenOption.CREATE);
	        
	       System.out.println("Data written");
			
		}
		catch(SQLException e) {
			connLabel.setText("Record not Inserted");
		}
		catch(IOException e) {
			System.out.println("Text file not written");
		}
		
	}
	void Modify() {
		try {
			Statement stmt = connection.createStatement();

			String dateId = dateIdTf.getText();
			String pid = petId.getText();
			String oid = ownerId.getText();
			String datest = datetf.getText();
			String timest = timetf.getText();

			
			if(!(pid.isEmpty()))
				stmt.executeUpdate("update mydb.calander_appointment set pet_id = '" + pid + 
						"'  where calander_id = ' " + dateId + "' ;");
			if(!(oid.isEmpty()))
				stmt.executeUpdate("update mydb.calander_appointment set customer_id = '" + oid + 
						"'  where calander_id = ' " + dateId + " ';");
			if(!(datest.isEmpty()))
				stmt.executeUpdate("update mydb.calander_appointment set pet_visit_date = '" + datest + 
						"'  where calander_id = ' " + dateId + " ';");
			if(!(timest.isEmpty()))
				stmt.executeUpdate("update mydb.calander_appointment set pet_vist_time = '" + timest + 
						"'  where calander_id = ' " + dateId + " ';");
		}
		catch(SQLException e) {
			connLabel.setText("Record not updated");
		}
	}
	void Clear() {
		dateIdTf.setText("");
		petId.setText("");
		ownerId.setText("");
		datetf.setText("");
		timetf.setText("");

	}
	public void setAppointmentButtonEventHandler(EventHandler<ActionEvent> handler) {
		home.setOnAction(handler);
	}
	public Scene getScene() {
		return scene;
	}


}
