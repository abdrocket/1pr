package abd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import abd.Constants;
import abd.controller.Controller;
import abd.model.Usuario;
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
	private JPasswordField pwdText;
	private JPanel p_main;
	private JPanel wrapper1;
	private JPanel wrapper2;
	private JPanel wrapper3;
	private JPanel wrapper4;
	private JPanel wrapper5;
	private JPanel wrapper6;

	private Controller cntr;

	public LoginPanel(final Controller daoController) {
		this.cntr = daoController;
		initGUI();
		this.cntr.addUserObserver(this);
	}
	
	private void initGUI(){
		this.userLabel = new JLabel("Nombre de usuario:");
		this.pwdLabel = new JLabel("Password:");

		this.userText = new JTextField("");
		this.pwdText = new JPasswordField("");

		this.acceptButton = new JButton("Aceptar");
		this.acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!userText.getText().equals("")
						&& !pwdText.getPassword().equals("")) {
					cntr.logUser(userText.getText(),
							new String(pwdText.getPassword()));
				} else {
					userText.requestFocus();
				}
			}
		});

		this.newUserButton = new JButton("Nuevo usuario");

		this.newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCreate();
			}
		});

		this.setLayout(new BorderLayout(20, 20));

		wrapper1 = new JPanel();
		wrapper2 = new JPanel();
		wrapper3 = new JPanel();
		wrapper4 = new JPanel();
		wrapper5 = new JPanel();
		wrapper6 = new JPanel();
		p_main = new JPanel(new GridLayout(3, 2));

		wrapper1.add(userLabel);
		wrapper2.add(userText);
		userText.setPreferredSize(new Dimension(120, 20));
		wrapper3.add(pwdLabel);
		wrapper4.add(pwdText);
		pwdText.setPreferredSize(new Dimension(120, 20));
		wrapper5.add(acceptButton);
		wrapper6.add(newUserButton);

		p_main.add(wrapper1, BorderLayout.CENTER);
		p_main.add(wrapper2, BorderLayout.CENTER);
		p_main.add(wrapper3, BorderLayout.CENTER);
		p_main.add(wrapper4, BorderLayout.CENTER);
		p_main.add(wrapper5, BorderLayout.CENTER);
		p_main.add(wrapper6, BorderLayout.CENTER);

		add(p_main);
	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		JOptionPane.showOptionDialog(null, Constants.WELLCOME, "",
				JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
				new Object[] { "Aceptar" }, "");
		this.userText.setText("");
		this.pwdText.setText("");
	}

	@Override
	public void onUserAccessRefused() {
		// TODO Auto-generated method stub
		JOptionPane.showOptionDialog(null, Constants.USERLOGIN_MISTAKE, "",
				JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
				new Object[] { "Aceptar" }, "");
		this.userText.setText("");
		this.pwdText.setText("");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		if (this.cntr.newUser(userText.getText(),
				new String(pwdText.getPassword()))) {
			JOptionPane.showOptionDialog(null, Constants.NEW_USER, "",
					JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
					new Object[] { "Aceptar" }, "");

		} else {
			JOptionPane.showOptionDialog(null, Constants.USERLOGIN_MISTAKE, "",
					JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
					new Object[] { "Aceptar" }, "");
		}
		this.userText.setText("");
		this.pwdText.setText("");
	}

	@Override
	public void onCurrentUserSetting(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpenCrossword(Integer crossId, String user,String userPlayer) {
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
