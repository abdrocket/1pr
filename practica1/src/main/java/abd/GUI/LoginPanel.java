package abd.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abd.Constants;
import abd.Controller;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel userLabel;
	private JLabel pwdLabel;
	private JButton acceptButton;
	private JButton newUserButton;
	private JTextField userText;
	private JTextField pwdText;

	public LoginPanel(final Controller DAO_Controller) {
		this.userLabel = new JLabel("Nombre de usuario:");
		this.pwdLabel = new JLabel("Contraseña:");

		this.userText = new JTextField("");
		this.pwdText = new JTextField("");

		this.acceptButton = new JButton("Aceptar");
		this.acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (DAO_Controller.checkUser(userText.getText(),
						pwdText.getText())) {
					JOptionPane.showOptionDialog(null, "Bienvenido!", "",
							JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
							new Object[] { "Aceptar" }, "");
					
					// DESPERTAR GUI !!!
					//recuperar contacto
					// this.setVisible(false);

				} else {
					JOptionPane.showOptionDialog(null,
							Constants.USERLOGIN_MISTAKE, "",
							JOptionPane.ERROR_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null,
							new Object[] { "Aceptar" }, "");
				}

			}
		});

		this.newUserButton = new JButton("Nuevo usuario");

		this.newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// NEW USER REATION

			}
		});

		this.setLayout(new GridLayout(3, 2));

		this.add(this.userLabel);
		this.add(this.userText);

		this.add(this.pwdLabel);
		this.add(this.pwdText);

		this.add(this.acceptButton);
		this.add(this.newUserButton);

	}

}
