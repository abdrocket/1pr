package abd.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import abd.controller.Controller;
import abd.model.Usuario;
import abd.observer.UserObserver;

public class UserDataPanel extends JPanel implements UserObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller cntr;
	
	private JLabel nombre;
	private JLabel edad;
	private JLabel puntuacion;
	
	public UserDataPanel(final Controller cntr){
		this.cntr = cntr;
		
		this.nombre = new JLabel("");
		this.edad = new JLabel("");
		this.puntuacion = new JLabel("");
		
		this.add(this.nombre);
		this.add(this.edad);
		this.add(this.puntuacion);
		
		this.cntr.addUserObserver(this);
	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		Usuario u = this.cntr.getCurrentUser();
		if(u != null){
			this.nombre = new JLabel(u.getNombre());
			this.edad = new JLabel(u.getNombre());
			this.puntuacion = new JLabel(u.getFechaNac() + " cuela?");
		}
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
	public void onOpenCrossword(Integer crossId, String user) {
		// TODO Auto-generated method stub
		
	}

}
