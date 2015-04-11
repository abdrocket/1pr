package abd.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import abd.controller.Controller;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller cntr;
	private UserDataPanel userDataPanel;
	private UserCrosswordsPanel userCrosswordPanel;

	public MainWindow(final Controller cntr) {
		super("Ventana principal");

		this.setLayout(new BorderLayout());

		this.cntr = cntr;
		this.setBounds(300, 300, 600, 600);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.userDataPanel = new UserDataPanel(this.cntr);
		this.add(this.userDataPanel, BorderLayout.NORTH);

		this.userCrosswordPanel = new UserCrosswordsPanel(this.cntr);
		this.add(this.userCrosswordPanel, BorderLayout.CENTER);

		this.pack();
		this.setLocationRelativeTo(null);
	}

}