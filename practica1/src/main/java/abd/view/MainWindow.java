package abd.view;

import javax.swing.JFrame;

import abd.controller.Controller;
import abd.observer.UserObserver;

public class MainWindow extends JFrame implements UserObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller cntr;
	
	public MainWindow() {
		
		
		
		this.cntr.addUserObserver(this);
	}

	@Override
	public boolean onAccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

}
