package abd.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abd.Constants;
import abd.controller.Controller;
import abd.model.Usuario;
import abd.observer.UserObserver;
import abd.observer.WindowObserver;

public class UserDataPanel extends JPanel implements UserObserver,
		WindowObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller cntr;

	private JLabel nombre;
	private JLabel edad;
	private JLabel label;
	

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
				@SuppressWarnings("unused")
				ModifyUserWindow muw = new ModifyUserWindow(cntr);
			}
		});
		
		label = new JLabel ();
		label.setForeground(Color.red);
		
		infoPanel.setLayout(new GridLayout(3, 1));
		infoPanel.add(this.nombre);
		infoPanel.add(this.edad);
		infoPanel.add(this.label);

		this.add(buttonPanel, BorderLayout.WEST);
		this.add(infoPanel, BorderLayout.CENTER);

		this.cntr.addUserObserver(this);
		this.cntr.addWindowObserver(this);
	}

	public void updateWindow() {
		Usuario u = this.cntr.getCurrentUser();
		
		if (u != null) {
			label.setText(cntr.getPuntuacion()+" puntos");
			this.nombre.setText(Constants.TAB + u.getNombre());
			if (u.getFechaNac() != null) {
				// this.edad.setText(Constants.TAB + u.getFechaNac().getTime());
				Date uDate = u.getFechaNac();
				Date now = new Date();
				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				int userDate = Integer.parseInt(yearFormat.format(uDate));
				int nowDate = Integer.parseInt(yearFormat.format(now));
				this.edad.setText(Integer.toString(nowDate - userDate)
						+ " años");
			}
			byte[] bArr = this.cntr.getCurrentUser().getImagen();
			if (bArr != null) {
				try {
					Image image = new ImageIcon(bArr).getImage();
					Image resizedImage = image.getScaledInstance(100, 100,
							Image.SCALE_SMOOTH);
					userButton.setIcon(new ImageIcon(resizedImage));
				} catch (java.lang.IllegalArgumentException e) {

				}
			} else {
				BufferedImage usrImg = null;
				try {
					usrImg = ImageIO.read(getClass().getResourceAsStream(
							"/abd/view/prukogi.png"));
					try {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(usrImg, "jpg", baos);
						baos.flush();
						byte[] imageInByte = baos.toByteArray();
						baos.close();
						this.cntr.getCurrentUser().setImagen(imageInByte);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					Image resizedImage = usrImg.getScaledInstance(100, 100,
							Image.SCALE_SMOOTH);
					this.userButton.setIcon(new ImageIcon(resizedImage));
				} catch (IOException e) {

				} catch (java.lang.IllegalArgumentException e) {

				}
			}
		}
	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		updateWindow();
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

	@Override
	public void onModifyUserConcluded() {
		// TODO Auto-generated method stub
		updateWindow();
	}

}
