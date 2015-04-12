package abd.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abd.CrosswordDAO;
import abd.model.Crucigrama;
import abd.model.Peticion;
import abd.model.Usuario;
import abd.model.Word;
import abd.observer.UserObserver;
import abd.view.GUI;

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

	public void openCrossword(Integer crosswordId, String userOwner) {
		dao.openCrossword(crosswordId, userOwner,getCurrentUser().getNombre());
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

	public boolean addFriend(String friend) {
		return dao.addFriend(this.getCurrentUser().getNombre(),friend);
	}

	public ArrayList<String> getAmigos() {
		return dao.getAmigos(getCurrentUser().getNombre());
	}

	public void deleteFriend(String friendToDelete) {
		dao.deleteFriend(getCurrentUser().getNombre(),friendToDelete);
	}

	public ArrayList<Peticion> getPeticiones() {
		return dao.getPeticiones(getCurrentUser().getNombre());
	}

	public String getCrosswordTitle(Integer crucigrama) {
		return dao.getCrosswordTitle(crucigrama);
	}

	public void deleteRequest(Integer crosswordId, String userOwner) {
		dao.deleteRequest(crosswordId, userOwner);
	}
}
