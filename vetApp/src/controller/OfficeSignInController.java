package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import view.OfficeSignInView;

public class OfficeSignInController extends BaseController {
	
	private OfficeSignInView view = null;
	
	public static final int ID = 1;

	public OfficeSignInController(Stage stage) {
		super(stage, ID);
		
		view = new OfficeSignInView();
		
		
		view.setSignInButtonEventHandler(e -> {
			if(checkUserID() && checkPassword()) {
			this.controllers.get(OfficeHomeController.ID).activate();
			}else {
				System.out.println("no access");
			}
		});
		
	}

	public void activate() {
		stage.setScene(view.getScene());
	}
	public boolean checkUserID() {
		String user = view.getUserId();
		if (user.equalsIgnoreCase("user1"))  {
			return true;
		}else {
			return false;
		}
	}
	public boolean checkPassword() {
		String pass = view.getPassword();
		if(pass.matches("Password123")) {
			return true;
		}else {
			return false;
		}
	}

}
