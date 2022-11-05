package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import view.NavigationView;
import view.OfficeHomeView;

public class OfficeHomeController extends BaseController {
	
	private OfficeHomeView view = null;
	
	public static final int ID = 2;

	public OfficeHomeController(Stage stage) {
		super(stage, ID);
		
		view = new OfficeHomeView();
		
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
