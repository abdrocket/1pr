package abd.view;

import javax.swing.JPanel;

import abd.controller.Controller;
import abd.observer.UserObserver;

public class UserCrosswordsPanel extends JPanel implements UserObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller cntr;

	public UserCrosswordsPanel(final Controller cntr) {
		this.cntr = cntr;
		
		this.cntr.addUserObserver(this);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserAccessRefused() {
		// TODO Auto-generated method stub
		
	}

}
