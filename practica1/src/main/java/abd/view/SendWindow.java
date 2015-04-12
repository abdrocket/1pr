package abd.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import abd.controller.Controller;

public class SendWindow extends JFrame{
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private JButton bAdd;
		private JButton bBack;

		private JList<String> lCrosswords;
		private DefaultListModel<String> model;
		
		private JPanel pMain;
		private JPanel pWrappAdd;
		private JPanel pWrapperBack;
		
		private JScrollPane sList;
		
		private Controller cntr;
		
		private String userOwner;
		private Integer crossId;
		
		public SendWindow(final Controller daoController, String userOwnerx, Integer crossIdx){
			this.userOwner = userOwnerx;
			this.crossId = crossIdx;
			this.cntr = daoController;
			initGUI();
		}
		
		private void initGUI(){
			
			this.setLayout(new BorderLayout());
			this.setBounds(300, 300, 400, 300);
			this.setLocationRelativeTo(null);
			
			bAdd = new JButton("Enviar crucigrama");
			bAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String friend = lCrosswords.getSelectedValue();
					if(friend != null){
						
						cntr.enviarPeticion(userOwner,friend,crossId);
						close();
						cntr.updateMainW();
						cntr.returnToMain();
											
					}
				}
			});
			bBack = new JButton("Salir");
			bBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close();
					cntr.returnToMain();
				}
			});
			
			model = new DefaultListModel<String>();
			lCrosswords = new JList<String>(model);
			sList = new JScrollPane(lCrosswords);
			sList.setViewportView(lCrosswords);
			
			ArrayList<String> amigos = cntr.getAmigos();
			for(String amigo:amigos){
				model.addElement(amigo);
			}
			
			pWrappAdd = new JPanel();
			pWrapperBack = new JPanel();
			pMain = new JPanel(new FlowLayout());
			
			pWrappAdd.add(bAdd);
			pWrapperBack.add(bBack);
			
			pMain.add(pWrappAdd);
			pMain.add(pWrapperBack);
			
			this.add(pMain,BorderLayout.SOUTH);
			this.add(sList, BorderLayout.CENTER);
			
			
		}

		private void close(){
			this.setVisible(false);
			this.dispose();
		}

}
