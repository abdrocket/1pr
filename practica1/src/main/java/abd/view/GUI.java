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
	private static MainWindow principal;
	private static Controller cntr;
	private CrosswordWindow crossWindow;
	private static SearchWindow sWindow;

	public GUI(final Controller daoController) {
		cntr = daoController;
		this.login = new LoginWindow(daoController);
		this.login.setVisible(true);
		principal = new MainWindow(cntr);
		principal.setVisible(false);
		cntr.addUserObserver(this);
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		this.login.setVisible(false);
		principal.setVisible(true);
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
	public void onOpenCrossword(Integer crossId, String user,String userPlayer) {
		crossWindow = new CrosswordWindow(cntr, crossId, user, userPlayer);
		principal.setVisible(false);
		this.crossWindow.setVisible(true);
	}

	
	public static void SearchCrossword() {
		sWindow = new SearchWindow(cntr);
		principal.setVisible(false);
		sWindow.setVisible(true);
	}
	
	public static void makeMainVisible(){
		principal.setVisible(true);
	}

	@Override
	public void onUpdateCrosswords() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCurrentUserUpdate() {
		// TODO Auto-generated method stub
		
	}

}
