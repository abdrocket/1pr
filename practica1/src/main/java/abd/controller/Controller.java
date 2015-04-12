package abd.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abd.CrosswordDAO;
import abd.model.Crucigrama;
import abd.model.Usuario;
import abd.model.Word;
import abd.observer.UserObserver;
import abd.observer.WindowObserver;
import abd.view.GUI;
import abd.view.ModifyUserWindow;

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
		List<Integer> crucigramasActivos = new ArrayList<Integer>();
		crucigramasActivos = this.dao.getCrosswordsOf(usr);
		if (crucigramasActivos != null) {
			for (Integer i : crucigramasActivos) {
				userCrosswords.add(this.dao.getCrosswordByTitle(i));
			}
		}
		return userCrosswords;
	}

	public void setCurrentUser(Usuario u) {
		this.currentUsr = u;
	}

	public void openCrossword(Integer crosswordId, String user) {
		dao.openCrossword(crosswordId, user);
	}

	public void searchCrossword() {
		GUI.SearchCrossword();
	}

	public List<Crucigrama> getCrosswordsLike(String title) {
		List<Crucigrama> ls = new LinkedList<Crucigrama>();
		List<Integer> l = dao.findCrosswordsByTitle(title);
		for (Integer i : l) {
			ls.add(dao.getCrosswordByTitle(i));
		}
		return ls;
	}

	public void addCrucigrama(Integer crossId) {
		dao.addCrosswordToUser(this.getCurrentUser().getNombre(), crossId);
	}

	public boolean checkCrossword(Integer crossId) {
		List<Integer> crosswords = dao.getCrosswordsOf(this.getCurrentUser().getNombre());
		boolean found = false;
		if(crosswords !=  null){
			for(Integer x:crosswords){
				if(crossId == x)
					found = true;
			}
		}
		return found;
	}

	public void returnToMain() {
		GUI.makeMainVisible();
	}

	public void updateMainW() {
		dao.updateMainW();
	}

	public void UpdateUser(Usuario u){
		this.dao.updateUser(u);
	}

	public void addWindowObserver(WindowObserver wo) {
		// TODO Auto-generated method stub
		this.dao.addWindowObserver(wo);
	}
	
}
