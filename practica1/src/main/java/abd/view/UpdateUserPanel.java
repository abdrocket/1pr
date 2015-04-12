package abd.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import abd.Constants;
import abd.controller.Controller;
import abd.model.Usuario;

public class UpdateUserPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel userLabel;
	private JPasswordField userTextField;
	private JLabel userLabel2;
	private JPasswordField userTextField2;
	private JLabel fecha_nLabel;
	// private JTextField fecha_nTextField;
	private JButton acceptButon;
	private JButton cancelButton;

	private Controller cntr;
	private Usuario u;

	public UpdateUserPanel(final Controller daoController) {
		this.cntr = daoController;
		this.u = this.cntr.getCurrentUser();
		this.setLayout(new GridLayout(4, 2));
		this.userLabel = new JLabel("Contraseña: ");
		this.add(this.userLabel);
		this.userTextField = new JPasswordField();
		this.add(this.userTextField);
		this.userLabel2 = new JLabel("Confirmar: ");
		this.add(this.userLabel2);
		this.userTextField2 = new JPasswordField();
		this.add(this.userTextField2);
		this.fecha_nLabel = new JLabel("Fecha: ");
		this.add(this.fecha_nLabel);
		// this.fecha_nTextField;
		this.acceptButon = new JButton("Aceptar");
		this.acceptButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkChanges()) {
					cntr.UpdateUser(u);
				} else {
					JOptionPane.showOptionDialog(null, Constants.NO_CHANGES,
							"", JOptionPane.OK_OPTION, JOptionPane.OK_OPTION,
							null, new Object[] { "Aceptar" }, "");
				}
			}
		});
		this.add(this.acceptButon);
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int seleccion = JOptionPane
						.showOptionDialog(
								null,
								Constants.CANCEL_CONFIRM,
								"", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null,
								new Object[] { "Cancelar", "Aceptar" }, "");
				if (seleccion == 1) {
					
				}
			}
		});
		this.add(this.cancelButton);
	}

	/**
	 * 
	 * @return true if have had been changes
	 */
	public boolean checkChanges() {
		boolean check = false;
		char[] arr11 = this.userTextField.getPassword();
		char[] arr12 = this.u.getPassword().toCharArray();
		char[] arr21 = this.userTextField.getPassword();
		char[] arr22 = this.u.getPassword().toCharArray();
		check = !(Arrays.equals(arr11, arr12) && Arrays.equals(arr21, arr22)
				&& Arrays.equals(arr12, arr21));
		String password = null;
		if(check){
			password = u.getPassword();
			if(Arrays.equals(arr11,arr21) && !Arrays.equals(arr11, password.toCharArray())){
				password = String.valueOf(arr11);
			}
			this.u = new Usuario(this.u.getNombre(), password, null, null);
		}
		return check;// incluir fecha e imagen
	}

}
