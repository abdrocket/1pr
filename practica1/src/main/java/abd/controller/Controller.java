package abd.controller;

import abd.CrosswordDAO;
import abd.model.Usuario;
import abd.observer.UserObserver;

public class Controller {

	private CrosswordDAO dao;
	private Usuario currentUsr;

	public Controller(CrosswordDAO dao) {
		this.dao = dao;
		this.currentUsr = null;
	}

	public boolean logUser(String usr, String pwd) {
		boolean check = this.dao.logUser(usr, pwd);
		if(check){
			this.currentUsr = this.dao.getUser(usr);
		}
		return check;
	}
	
	public Usuario getCurrentUser(){
		return this.currentUsr;
	}

	public boolean newUser(String usr, String pwd) {
		// TODO Auto-generated method stub
		boolean check = false;
		if (this.dao.getPassword(usr) == null) {
			check = true;
			this.dao.insertUser(usr, pwd);
		}
		return check;
	}
	
	public void addUserObserver(UserObserver uObserver){
		this.dao.addUserObserver(uObserver);
	}

}
