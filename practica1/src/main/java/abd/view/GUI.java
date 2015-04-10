package abd.view;

import javax.swing.JFrame;

import abd.controller.Controller;
import abd.model.Usuario;
import abd.observer.UserObserver;

public class GUI extends JFrame implements UserObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginWindow login;
	private MainWindow principal;
	private Controller cntr;
	private CrosswordWindow crossWindow;
	private SearchWindow sWindow;

	public GUI(final Controller daoController) {
		this.cntr = daoController;
		this.login = new LoginWindow(daoController);
		this.login.setVisible(true);
		this.principal = new MainWindow(this.cntr);
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

	@Override
	public void onCurrentUserSetting(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpenCrossword(Integer crossId, String user) {
		// TODO Auto-generated method stub
		crossWindow = new CrosswordWindow(cntr, crossId, user);
		this.principal.setVisible(false);
		this.crossWindow.setVisible(true);
	}

	@Override
	public void onSearchCrossword() {
		sWindow = new SearchWindow(cntr);
		this.sWindow.setVisible(true);
	}

}
