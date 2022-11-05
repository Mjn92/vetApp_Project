package controller;

import javafx.stage.Stage;
import view.CalanderView;

public class CalanderController extends BaseController {

	private CalanderView view = null;
	
	public static final int ID = 5;

	public CalanderController(Stage stage) {
		super(stage, ID);
		view = new CalanderView();
		
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
