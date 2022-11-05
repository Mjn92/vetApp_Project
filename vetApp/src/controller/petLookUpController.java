package controller;

import javafx.application.*;
import javafx.stage.Stage;
import view.PetLookUpView;

public class petLookUpController extends BaseController{
	
	private PetLookUpView view = null;
	
	public static final int ID = 4;

	public petLookUpController(Stage stage) {
		super(stage, ID);
		view = new PetLookUpView();
		
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
