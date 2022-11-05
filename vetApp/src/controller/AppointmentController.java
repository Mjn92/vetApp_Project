package controller;

import javafx.stage.Stage;
import view.AppointmentView;

public class AppointmentController extends BaseController{
	
	private AppointmentView view = null;
	
	public static final int ID = 6;

	public AppointmentController(Stage stage) {
		super(stage, ID);
		view = new AppointmentView();
		
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
