package abd.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePickerImpl;

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
	private JButton imageButon;
	private JButton acceptButon;
	private JButton cancelButton;

	private Controller cntr;
	private Usuario u;

	private JDatePickerImpl datePicker;

	public UpdateUserPanel(final Controller daoController) {
		this.cntr = daoController;
		this.u = this.cntr.getCurrentUser();
		this.setLayout(new GridLayout(5, 2));
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

		UtilDateModel model = new UtilDateModel();
		Date uDate = u.getFechaNac();
		if (uDate == null) {
			uDate = new Date();
		}
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		SimpleDateFormat mm = new SimpleDateFormat("MM");
		SimpleDateFormat dd = new SimpleDateFormat("dd");
		int year = Integer.parseInt(yy.format(uDate));
		int month = Integer.parseInt(mm.format(uDate));
		int day = Integer.parseInt(dd.format(uDate));
		model.setDate(year, month, day);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		this.datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		this.add(this.datePicker);

		this.imageButon = new JButton("Cambiar imagen");
		this.imageButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					byte[] b = readContentIntoByteArray(file);
					cntr.getCurrentUser().setImagen(b);
				}
				if (checkChanges()) {
					cntr.UpdateUser(u);
					JOptionPane.showOptionDialog(null, Constants.CHANGES, "",
							JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
							new Object[] { "Aceptar" }, "");
					cntr.updateUserInfo();
				} else {
					JOptionPane.showOptionDialog(null, Constants.NO_CHANGES,
							"", JOptionPane.OK_OPTION, JOptionPane.OK_OPTION,
							null, new Object[] { "Aceptar" }, "");
					userTextField.setText("");
					userTextField2.setText("");
				}
			}
		});
		this.add(new JLabel("Avatar:"));
		this.add(this.imageButon);
		this.acceptButon = new JButton("Aceptar");
		this.acceptButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkChanges()) {
					cntr.UpdateUser(u);
					JOptionPane.showOptionDialog(null, Constants.CHANGES, "",
							JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null,
							new Object[] { "Aceptar" }, "");
					cntr.updateUserInfo();
				} else {
					JOptionPane.showOptionDialog(null, Constants.NO_CHANGES,
							"", JOptionPane.OK_OPTION, JOptionPane.OK_OPTION,
							null, new Object[] { "Aceptar" }, "");
					userTextField.setText("");
					userTextField2.setText("");
				}

			}
		});
		this.add(this.acceptButon);
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int seleccion = JOptionPane.showOptionDialog(null,
						Constants.CANCEL_CONFIRM, "",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] {
								"Cancelar", "Aceptar" }, "");
				if (seleccion == 1) {
					cntr.updateUserInfo();
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
		char[] p1 = this.userTextField.getPassword();
		char[] pu = this.u.getPassword().toCharArray();
		char[] p2 = this.userTextField2.getPassword();
		check = Arrays.equals(p1, p2) && !Arrays.equals(p1, pu);// &&
																// !String.valueOf(p1).equalsIgnoreCase("");
		Date uDate = u.getFechaNac();
		Date selectedDate = (Date) datePicker.getModel().getValue();
		// if(uDate != null && selectedDate ){
		if (selectedDate != null) {
			selectedDate = (Date) datePicker.getModel().getValue();
			SimpleDateFormat yy = new SimpleDateFormat("yyyy");
			SimpleDateFormat mm = new SimpleDateFormat("MM");
			SimpleDateFormat dd = new SimpleDateFormat("dd");
			if (uDate != null) {
				int year = Integer.parseInt(yy.format(uDate));
				int month = Integer.parseInt(mm.format(uDate));
				int day = Integer.parseInt(dd.format(uDate));
				int year2 = Integer.parseInt(yy.format(selectedDate));
				int month2 = Integer.parseInt(mm.format(selectedDate));
				int day2 = Integer.parseInt(dd.format(selectedDate));
				check = check
						&& ((year != year2) || (month != month2) || (day != day2));
			}
			uDate = selectedDate;
		}

		byte[] bArr = this.cntr.getCurrentUser().getImagen();
		if (bArr != null) {
			try {
				Image image = new ImageIcon(bArr).getImage();
				Image resizedImage = image.getScaledInstance(100, 100,
						Image.SCALE_SMOOTH);
				imageButon.setIcon(new ImageIcon(resizedImage));
			} catch (java.lang.IllegalArgumentException e) {

			}
		}
		if (check) {
			String password = String.valueOf(p1);
			if (password.equalsIgnoreCase("")) {
				password = this.u.getPassword();
			}

			this.u = new Usuario(this.u.getNombre(), password, uDate,
					this.u.getImagen());
			this.cntr.setCurrentUser(u);
		}
		return check;
	}

	private static byte[] readContentIntoByteArray(File file) {
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			for (int i = 0; i < bFile.length; i++) {
				System.out.print((char) bFile[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}

}
