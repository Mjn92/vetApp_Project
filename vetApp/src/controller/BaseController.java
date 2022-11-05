package controller;

import java.util.TreeMap;

import javafx.stage.Stage;

public abstract class BaseController {
	
	protected Stage stage = null;
	protected static TreeMap<Integer, BaseController> controllers = new TreeMap<>();
	
	public BaseController (Stage stage, int id) {
		controllers.put(id, this);
		this.stage = stage;
	}
	
	public abstract void activate();

}
