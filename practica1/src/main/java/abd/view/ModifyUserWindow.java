package abd.view;

import javax.swing.JFrame;

import abd.controller.Controller;
import abd.observer.WindowObserver;

public class ModifyUserWindow extends JFrame  implements WindowObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModifyUserWindow(final Controller daoController) {
		super("Editar perfil");

		this.add(new UpdateUserPanel(daoController));

		this.setBounds(200, 200, 350, 175);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		daoController.addWindowObserver(this);
	}

	@Override
	public void onModifyUserConcluded() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
