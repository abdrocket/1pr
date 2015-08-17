package abd;

import abd.controller.Controller;
import abd.view.GUI;

public class Main {

	public static void main(String[] args) {
		Controller daoCntr = new Controller(new CrosswordDAO());
		@SuppressWarnings("unused")
		GUI window = new GUI(daoCntr);
		
	}
}
