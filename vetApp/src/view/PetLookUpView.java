package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PetLookUpView extends NavigationView{
	
	private Label connLabel = new Label();
	private TextField petId = new TextField();
	private TextField ownerId = new TextField();
	private TextField petName = new TextField();
	private TextField petSpecies = new TextField();
	private TextField petAge = new TextField();
	private TextField petType = new TextField();
	private TextField petWeight = new TextField();
	private Button btLookUp = new Button("Lookup");
	private Button btCreate = new Button("Create");
	private Button btModify = new Button("Modify");
	private Button btClear = new Button("Clear");
	private Connection connection = null;
	
	public PetLookUpView() {
		super();
		
		
		petId.setPrefColumnCount(3);
		ownerId.setPrefColumnCount(2);
		petName.setPrefColumnCount(20);
		petSpecies.setPrefColumnCount(20);
		petType.setPrefColumnCount(20);
		petAge.setPrefColumnCount(2);
		petWeight.setPrefColumnCount(3);
		
		HBox hbId = new HBox(5);
		hbId.getChildren().addAll(new Label ("Pet Id: "), petId);
		
		HBox hbownerName = new HBox(5);
		hbownerName.getChildren().addAll(new Label ("Owner's ID: "), ownerId);
		
		HBox hbPetName = new HBox(5);
		hbPetName.getChildren().addAll(new Label ("Pet's Namne: "), petName);

		HBox hbspeciesAge = new HBox(5);
		hbspeciesAge.getChildren().addAll(new Label("Species: "), petSpecies, new Label("Age: "), petAge);

		HBox hbHightWeight = new HBox(5);
		hbHightWeight.getChildren().addAll(new Label("Type: "), petType, new Label("Weight: "), petWeight);
		
		HBox hbButtons = new HBox(10);
		hbButtons.getChildren().addAll(btLookUp, btCreate, btModify, btClear);
		hbButtons.setAlignment(Pos.CENTER);
		
		VBox petbox = new VBox(5);
		petbox.getChildren().addAll(connLabel, hbId, hbownerName, hbPetName, hbspeciesAge, hbHightWeight, hbButtons);
		petbox.setPadding(new Insets(10,10,10,10));
		
		btLookUp.setOnAction(e -> LookUp());
		btCreate.setOnAction(e -> Create());
		btModify.setOnAction(e -> Modify());
		btClear.setOnAction(e -> Clear());
		
		
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
		vbox.getChildren().add(petbox);
		
	}
	void LookUp() {
		try {
			Statement stmt = connection.createStatement();
			String cmd = "select * from mydb.pet_info where pet_id = '" + petId.getText() + "';";
			
			//System.out.println(cmd);
			
			ResultSet results = stmt.executeQuery(cmd);
			results.next();
			
			
			petName.setText(results.getString(2));
			petSpecies.setText(results.getString(3));
			petType.setText(results.getString(4));
			petAge.setText(results.getString(5));
			petWeight.setText(results.getString(6));
			ownerId.setText(results.getString(7));
			
		}
		catch(SQLException e) {
			connLabel.setText("Record not found");
		}
	}
	void Create() {
		try {
			Statement stmt = connection.createStatement();
			
			String pId = petId.getText();
			String pName = petName.getText();
			String pSpecies = petSpecies.getText();
			String pAge = petAge.getText();
			String pType = petType.getText();
			String pweight  = petWeight.getText();
			String oId = ownerId.getText();

			
			String cmd ="insert into mydb.pet_info values('"
			+ pId + "', '" + pName + "', '" + pSpecies + "', '" + pType 
				+	"', '" + pAge + "', '" + pweight + "' , '"  + oId + "' );";
			//System.out.println(cmd);
			stmt.execute(cmd);
		}
		catch(SQLException e) {
			connLabel.setText("Record not Inserted");
		}
		
	}
	void Modify() {
		try {
			Statement stmt = connection.createStatement();
			
			String id = petId.getText();
			String oname = ownerId.getText();
			String pname = petName.getText();
			String psp = petSpecies.getText();
			String ptype = petType.getText();
			String page = petAge.getText();
			String pweight = petWeight.getText();
			
			if(!(oname.isEmpty()))
				stmt.executeUpdate("update mydb.pet_info set customer_id = '" + oname + 
						"'  where pet_id = " + id + ";");
			if(!(pname.isEmpty()))
				stmt.executeUpdate("update mydb.pet_info set pet_name = '" + pname + 
						"'  where pet_id = " + id + ";");
			if(!(psp.isEmpty()))
				stmt.executeUpdate("update mydb.pet_info set pet_speices = '" + psp + 
						"'  where pet_id = " + id + ";");
			if(!(page.isEmpty()))
				stmt.executeUpdate("update mydb.pet_info set pet_age = '" + page + 
						"'  where pet_id = " + id + ";");
			if(!(ptype.isEmpty()))
				stmt.executeUpdate("update mydb.pet_info set pet_type = '" + ptype + 
						"'  where pet_id = " + id + ";");
			if(!(pweight.isEmpty()))
				stmt.executeUpdate("update mydb.pet_info set pet_weight = '" + pweight + 
						"'  where pet_id = " + id + ";");
		}
		catch(SQLException e) {
			connLabel.setText("Record not updated");
		}
	}
	void Clear() {
		petId.setText("");
		ownerId.setText("");
		petName.setText("");
		petSpecies.setText("");
		petAge.setText("");
		petType.setText("");
		petWeight.setText("");

	}
	public void setPetLookUpButtonEventHandler(EventHandler<ActionEvent> handler) {
		home.setOnAction(handler);
	}
	public Scene getScene() {
		return scene;
	}


}
