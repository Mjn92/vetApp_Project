package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import view.CustomerLookUpView;

public class CustomerLookUpController extends BaseController {
	
	protected CustomerLookUpView view = null;
	
	public static final int ID = 3;
	

	public CustomerLookUpController(Stage stage) {
		super(stage, ID);
		
		view = new CustomerLookUpView();
		
		view.setHomeButtonHandler(e -> {
			this.controllers.get(OfficeHomeController.ID).activate();
		});
		view.setCutemerLookUpButtonHandler(e -> {
			this.controllers.get(CustomerLookUpController.ID).activate();
		});
		view.setPetLookUpButtonHandler(e -> {
			this.controllers.get(petLookUpController.ID).activate();
		});
		view.setAppointmentLookUpButtonHandler(e -> {	
			this.controllers.get(AppointmentController.ID).activate();
		});
		view.setCalanderLookUpButtonHandler(e -> {
			this.controllers.get(CalanderController.ID).activate();
		});
	}

	public void activate() {
		stage.setScene(view.getScene());
	} 
	

}
