package abd.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abd.Constants;
import abd.controller.Controller;
import abd.observer.UserObserver;

public class LoginPanel extends JPanel implements UserObserver {
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

	private Controller cntr;

	public LoginPanel(final Controller DAO_Controller) {
		this.cntr = DAO_Controller;

		this.userLabel = new JLabel("Nombre de usuario:");
		this.pwdLabel = new JLabel("Contraseña:");

		this.userText = new JTextField("");
		this.pwdText = new JTextField("");

		this.acceptButton = new JButton("Aceptar");
		this.acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAccess();
			}
		});

		this.newUserButton = new JButton("Nuevo usuario");

		this.newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCreate();
			}
		});

		this.setLayout(new GridLayout(3, 2));

		this.add(this.userLabel);
		this.add(this.userText);

		this.add(this.pwdLabel);
		this.add(this.pwdText);

		this.add(this.acceptButton);
		this.add(this.newUserButton);

		this.cntr.addUserObserver(this);

	}

	@Override
	public void onAccess() {
		// TODO Auto-generated method stub
		//boolean check = false;
		if (this.cntr.logUser(userText.getText(), pwdText.getText())) {
			JOptionPane.showOptionDialog(null, Constants.WELLCOME, "",
					JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
					new Object[] { "Aceptar" }, "");
			//check = true;

		} else {
			JOptionPane.showOptionDialog(null, Constants.USERLOGIN_MISTAKE, "",
					JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
					new Object[] { "Aceptar" }, "");
		}
		this.userText.setText("");
		this.pwdText.setText("");
		//return check;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		// boolean basaur = false;
		if (this.cntr.newUser(userText.getText(), pwdText.getText())) {
			JOptionPane.showOptionDialog(null, Constants.NEW_USER, "",
					JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
					new Object[] { "Aceptar" }, "");
			// basaur = true;

		} else {
			JOptionPane.showOptionDialog(null, Constants.USERLOGIN_MISTAKE, "",
					JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
					new Object[] { "Aceptar" }, "");
		}
		this.userText.setText("");
		this.pwdText.setText("");
		// return basaur;
	}

}
