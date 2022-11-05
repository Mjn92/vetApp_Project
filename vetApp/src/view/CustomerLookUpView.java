package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class CustomerLookUpView extends NavigationView {
	
	private Label connLabel = new Label();
	private TextField custIn = new TextField();
	private TextField custId = new TextField();
	private TextField petId = new TextField();
	private TextField petName = new TextField();
	private Connection connection = null;
	private Button btPetLookUp = new Button("Look Up Pet");
	private Button btLookUp = new Button("Look up Customer");
	private Button btCreate = new Button("Create Customer");
	private Button btModify = new Button("Modify Customer");
	private Button btClear = new Button("Clear");
	
	private ObservableList<Info> data = 
			FXCollections.observableArrayList();

	
	public CustomerLookUpView() {
		super();
		
		custIn.setPrefColumnCount(20);
		custId.setPrefColumnCount(2);
		
		HBox idbox = new HBox(5);
		idbox.getChildren().addAll(new Label ("Customer Id: "), custId);
		HBox nbox = new HBox(5);
		nbox.getChildren().addAll(new Label ("Customer Name: "), custIn);
		
		HBox bbox = new HBox(5);
		bbox.getChildren().addAll(btLookUp, btPetLookUp);
		bbox.setAlignment(Pos.CENTER);
		
		HBox bbox2 = new HBox(5);
		bbox2.getChildren().addAll(btCreate, btModify, btClear);
		bbox2.setAlignment(Pos.CENTER);
		
		btLookUp.setOnAction(e -> LookUp());
		btPetLookUp.setOnAction(e -> PetLookUp());
		btCreate.setOnAction(e -> addCustomer());
		btModify.setOnAction(e -> modifyCustomer());
		btClear.setOnAction(e -> Clear());
		
		TableView table = new TableView();
		
		table.setEditable(true);
		
		TableColumn OwnerNameCol = new TableColumn("Owner Name");
		OwnerNameCol.setMinWidth(150);
		OwnerNameCol.setCellValueFactory(
				new PropertyValueFactory<Info, String> ("OwnerName") );
		
		TableColumn PetIdCol = new TableColumn(" ");
		PetIdCol.setMinWidth(100);
		PetIdCol.setCellValueFactory(
				new PropertyValueFactory<Info, String> ("PetSpe") );
		
		TableColumn PetNameCol = new TableColumn("Pet Name");
		PetNameCol.setMinWidth(150);
		PetNameCol.setCellValueFactory(
				new PropertyValueFactory<Info, String> ("PetName") );
		
		table.setItems(data);
		
		table.getColumns().addAll(OwnerNameCol, PetIdCol, PetNameCol);

		
		
		
		VBox cusbox = new VBox(5);
		cusbox.setPadding(new Insets(10,10,10,10));
		cusbox.getChildren().addAll(connLabel, idbox, nbox, bbox, bbox2, table);
		
        
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
        
        
        vbox.getChildren().add(cusbox);
		
	}
	
	public Scene getScene() {
		return scene;
	}
	public void setCustomerLookUpButtonEventHandler(EventHandler<ActionEvent> handler) {
		CustomerLookUp.setOnAction(handler);
	}
	private void Clear() {
		custId.setText("");
		custIn.setText("");
	}
	private void LookUp() {
		try {
			Statement stmt = connection.createStatement();
			String cmd = "select * from mydb.customer where customer_id = '" + custId.getText()
			+ "';";
			
			//System.out.println(cmd);
			
			ResultSet results = stmt.executeQuery(cmd);
			results.next();
			
			custIn.setText(results.getString(2));
		}
		catch(SQLException e) {
			connLabel.setText("Record not found");
		}
	}
	private void PetLookUp() {
		try {
			Statement stmt = connection.createStatement();
			String cmd =" select custormer_name, pet_speices, pet_name"  +
					" from mydb.customer c " +
					" join mydb.pet_info p " + 
					" on c.customer_id = p.customer_id " +
					" where c.customer_id = '" +  custId.getText() + "';";
			
			//System.out.println(cmd);

			ResultSet results = stmt.executeQuery(cmd);

			while(results.next()) {
				
				String OwnerName = results.getString(1);
				String PetSpe = results.getString(2);
				//String sPetId = results.getString(2);
				String PetName = results.getString(3);
				// System.out.println(PetSpe);
				Info x = new Info(OwnerName, PetSpe, PetName);
				data.add(x);
				
				
			}
			
			
			
			//System.out.println(results);

		}
		catch(SQLException e) {
			connLabel.setText("Record not Inserted");
		}
		
	}
	private void addCustomer() {
		try {
			Statement stmt = connection.createStatement();
			
			String oId = custId.getText();
			String oName = custIn.getText();

			
			String cmd ="insert into mydb.customer values(' " + oId + "', '" + oName
					+ "' );";
			//System.out.println(cmd);
			stmt.execute(cmd);
		}
		catch(SQLException e) {
			connLabel.setText("Record not Inserted");
		}
	}
	private void modifyCustomer() {
		try {
			Statement stmt = connection.createStatement();
			
			String id = custId.getText();
			String oname = custIn.getText();
			
			
			if(!(oname.isEmpty()))
				stmt.executeUpdate("update mydb.customer set custormer_name = '" + oname + 
						"'  where customer_id = " + id + ";");
			
		}
		catch(SQLException e) {
			connLabel.setText("Record not updated");
		}
		
	}
	public static class Info {
		private  SimpleStringProperty OwnerName;
		private  SimpleStringProperty PetSpe;
		private  SimpleStringProperty PetName;
		
		private Info (String oName, String petSpe, String pName) {
			this.OwnerName = new SimpleStringProperty(oName);
			this.PetSpe  = new SimpleStringProperty(petSpe);
			this.PetName = new SimpleStringProperty(pName);
		}

		public String getOwnerName() {
			return OwnerName.get();
		}
		public void setOwnerName(String oName) {
			OwnerName.set(oName);
		}
		public String getPetID() {
			return PetSpe.get();
		}
		public void setPetId(String petSpe) {
			PetSpe.set(petSpe);
		}
		public String getPetName() {
			return PetName.get();
		}
		public void setPetName(String pName) {
			PetName.set(pName);
		}
	}
}



