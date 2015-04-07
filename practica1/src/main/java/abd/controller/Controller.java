package abd.controller;

import test.CrosswordDAO;

public class Controller {

	private CrosswordDAO DAO;
	
	public Controller(CrosswordDAO DAO) {
		this.DAO = DAO;
	}

	public boolean checkUser(String nick, String pwd) {
		String s = this.DAO.getPassword(nick);
		return s != null && s.equals(pwd);
	}

	
	
}
