package abd.view;

import javax.swing.JFrame;

import abd.controller.Controller;

public class LoginWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginWindow(final Controller daoController) {
		super("Ventana de acceso");

		this.add(new LoginPanel(daoController));

		this.setBounds(200, 200, 350, 175);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
}