package abd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abd.Constants;
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
	private JButton userButton;

	private JPanel buttonPanel;
	private JPanel infoPanel;

	public UserDataPanel(final Controller cntr) {
		this.cntr = cntr;
		Dimension d1 = new Dimension(200, 100);
		this.setPreferredSize(d1);
		this.setLayout(new BorderLayout());
		buttonPanel = new JPanel(new BorderLayout());
		infoPanel = new JPanel(new BorderLayout());
		// this.infoPanel.setBackground(Color.BLACK);
		this.nombre = new JLabel();
		this.edad = new JLabel();
		this.puntuacion = new JLabel();
		this.userButton = new JButton();
		Dimension d2 = new Dimension(100, 100);
		buttonPanel.setPreferredSize(d2);
		buttonPanel.add(userButton);
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyUserWindow muw = new ModifyUserWindow(cntr);
			}
		});
		infoPanel.setLayout(new GridLayout(3, 1));
		infoPanel.add(this.nombre);
		infoPanel.add(this.edad);
		infoPanel.add(this.puntuacion);

		this.add(buttonPanel, BorderLayout.WEST);
		this.add(infoPanel, BorderLayout.CENTER);

		this.cntr.addUserObserver(this);
	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		Usuario u = this.cntr.getCurrentUser();
		if (u != null) {
			this.nombre.setText(Constants.TAB + u.getNombre());
			if (u.getFechaNac() != null) {
				this.edad.setText(Constants.TAB + u.getFechaNac().getTime());
			}

			if (u.getImagen() != null) {
				
			}
			else{
				BufferedImage usrImg = null;
				try {
					usrImg = ImageIO.read(getClass().getResourceAsStream("/abd/view/prukogi.png"));
					//Image resizedImage = usrImg.getScaledInstance(this.userButton.getWidth(), this.userButton.getHeight(),  Image.SCALE_SMOOTH);
					Image resizedImage = usrImg.getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
					this.userButton.setIcon(new ImageIcon(resizedImage));
				} catch (IOException e) {
					
				} catch (java.lang.IllegalArgumentException e) {
					
				}
			}
		}
	}
	
	public void modifyUser(){
		
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
	public void onOpenCrossword(Integer crossId, String user, String userPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdateCrosswords() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCurrentUserUpdate() {
		// TODO Auto-generated method stub
		
	}

}
