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
	private Controller cntr;

	public GUI(final Controller daoController) {
		this.cntr = daoController;
		this.login = new LoginWindow(daoController);
		this.login.setVisible(true);
		this.principal = new MainWindow(daoController);
		this.principal.setVisible(false);
		this.cntr.addUserObserver(this);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		this.login.setVisible(false);
		this.principal.setVisible(true);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserAccessRefused() {
		// TODO Auto-generated method stub
		
	}

}
