package abd.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abd.CrosswordDAO;
import abd.model.Crucigrama;
import abd.model.Usuario;
import abd.model.Word;
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
		if (check) {
			this.currentUsr = this.dao.getUser(usr);
		}
		return check;
	}

	public Usuario getCurrentUser() {
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

	public void addUserObserver(UserObserver uObserver) {
		this.dao.addUserObserver(uObserver);
	}

	public List<Word> getWordList(Integer crosswordId, String user) {
		return dao.getCrosswordInfo(crosswordId, user);
	}

	public ArrayList<Crucigrama> getUserCrosswords(String usr) {
		ArrayList<Crucigrama> userCrosswords = new ArrayList<Crucigrama>();
		List<Integer> crucigramasActivos =  this.dao.getCrosswordsOf(usr);

		for (Integer i : crucigramasActivos) {
			userCrosswords.add(this.dao.getCrosswordByTitle(i));

		}
		return userCrosswords;
	}

	public void setCurrentUser(Usuario u) {
		// TODO Auto-generated method stub
		this.currentUsr = u;
	}

	public void openCrossword(Integer crosswordId, String user) {
		dao.openCrossword(crosswordId,user);
	}

	public void searchCrossword() {
		dao.searchCrossword();
	}

	public List<String> getCrosswordsLike(String title) {
		int x = 0;
		List<String> ls = new LinkedList<String>();
		List<Integer> l= dao.findCrosswordsByTitle(title);
		for(Integer i:l){
			Crucigrama c = dao.getCrosswordByTitle(i);
			ls.add(c.getTitulo());
		}
		return ls;
	}

}
