package abd.view;

import javax.swing.JFrame;
import abd.controller.Controller;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI(final Controller DAO_Controller) {
		Login log = new Login(DAO_Controller);
		log.setVisible(true);
	}

}
