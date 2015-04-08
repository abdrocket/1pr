package abd.view;

import javax.swing.JFrame;

import abd.controller.Controller;
import abd.observer.UserObserver;

public class GUI extends JFrame implements UserObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginWindow login;
	private MainWindow principal;

	public GUI(final Controller DAO_Controller) {
		this.login = new LoginWindow(DAO_Controller);
		this.login.setVisible(true);
		this.principal = new MainWindow(DAO_Controller);
		this.principal.setVisible(false);
	}

	@Override
	public void onAccess() {
		// TODO Auto-generated method stub
		this.login.setVisible(false);
		this.principal.setVisible(true);
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

}
