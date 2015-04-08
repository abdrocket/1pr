package abd.controller;

import abd.model.Usuario;
import abd.observer.UserObserver;
import test.CrosswordDAO;

public class Controller {

	private CrosswordDAO DAO;
	private Usuario currentUsr;

	public Controller(CrosswordDAO DAO) {
		this.DAO = DAO;
		this.currentUsr = null;
	}

	public boolean logUser(String usr, String pwd) {
		boolean check = this.DAO.logUser(usr, pwd);
		if(check){
			this.currentUsr = this.DAO.getUser(usr);
		}
		return check;
	}
	
	public Usuario getCurrentUser(){
		return this.currentUsr;
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
