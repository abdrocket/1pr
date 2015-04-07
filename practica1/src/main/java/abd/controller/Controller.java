package abd.controller;

import abd.observer.UserObserver;
import test.CrosswordDAO;

public class Controller {

	private CrosswordDAO DAO;

	public Controller(CrosswordDAO DAO) {
		this.DAO = DAO;
	}

	public boolean checkUser(String usr, String pwd) {
		String s = this.DAO.getPassword(usr);
		return s != null && s.equals(pwd);
	}

	public boolean newUser(String usr, String pwd) {
		// TODO Auto-generated method stub
		boolean check = false;
		if (this.DAO.getPassword(usr) == null) {
			check = true;
			this.DAO.insertUser(usr, pwd);
		}
		return check;
	}
	
	public void addUserObserver(UserObserver uObserver){
		this.DAO.addUserObserver(uObserver);
	}

}
