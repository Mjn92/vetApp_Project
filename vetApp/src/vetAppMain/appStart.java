package vetAppMain;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class appStart extends Application {
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		OfficeSignInController controller1 = new OfficeSignInController(stage);
		OfficeHomeController controller2 = new OfficeHomeController(stage);
		CustomerLookUpController controller3 = new CustomerLookUpController(stage);
		petLookUpController controller4 = new petLookUpController(stage);
		CalanderController controller5 = new CalanderController(stage);
		AppointmentController controller6 = new AppointmentController(stage);
		
		controller1.activate();
		
		
		stage.setTitle("Vet Window");
		stage.show();
	}
	
	

}
