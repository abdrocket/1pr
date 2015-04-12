package abd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import abd.controller.Controller;
import abd.model.Crucigrama;
import abd.model.Peticion;
import abd.model.Usuario;
import abd.observer.UserObserver;

public class UserCrosswordsPanel extends JTabbedPane implements UserObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller cntr;
	private JPanel p_tab1;
	private JPanel p_tab2;
	private JPanel p_tab3;
	private JPanel p_wrapper1;
	private DefaultTableModel tbm;
	private JTable t_crosswords;
	private JButton b_abrir;
	private JButton b_buscar;
	private JScrollPane scroll;
	private int sfila = -1;
	
	private JButton bAdd;
	private JButton bDelete;
	private JTextArea tFriend;
	private JPanel pWrappAdd;
	private JPanel pWrappDelete;
	private JPanel pWrappFriend;
	private JPanel pButtons;
	private JList<String> lFriends;
	private DefaultListModel<String> model;
	private JScrollPane sList;
	
	private JButton b_Add;
	private JButton b_Delete;
	private JPanel pWAdd;
	private JPanel pWDelete;
	private JPanel p_buttons;
	private DefaultTableModel tmodel;
	private JTable tPetitions;
	private JScrollPane sPetitions;
	private int sf = -1;
	
	private Integer[] userCrosswords;
	private Integer nCrossw = 0;
	
	private ArrayList<Peticion> peticiones;
	private Integer nPet = 0;

	public UserCrosswordsPanel(final Controller cntr) {
		this.cntr = cntr;
		this.userCrosswords = new Integer[100];

		this.setBorder(BorderFactory.createRaisedBevelBorder());
		p_tab1 = new JPanel(new BorderLayout());
		p_tab2 = new JPanel(new BorderLayout());
		p_tab3 = new JPanel(new BorderLayout());

		initTab1();
		initTab2();
		initTab3();

		this.addTab("Crucigramas", p_tab1);
		this.addTab("Amigos", p_tab2);
		this.addTab("Peticiones de ayuda", p_tab3);

		this.cntr.addUserObserver(this);
	}
	
	private void initTab1(){
				p_wrapper1 = new JPanel();

				tbm = new DefaultTableModel(new String[] { "Titulo", "Fecha" }, 10);
				t_crosswords = new JTable(tbm) {

					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				scroll = new JScrollPane(t_crosswords);
				scroll.setViewportView(t_crosswords);

				t_crosswords.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						sfila = t_crosswords.rowAtPoint(e.getPoint()); // !null...
					}
				});

				b_abrir = new JButton("Abrir crucigrama");
				b_abrir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (sfila != -1) {
							if (tbm.getValueAt(sfila, 0) != null) {
								Integer crosswordId = userCrosswords[sfila];
								String userOwner = cntr.getCurrentUser().getNombre();
								cntr.openCrossword(crosswordId, userOwner);
								sfila = -1;
							}
						}
					}
				});
				b_buscar = new JButton("Buscar crucigrama");
				b_buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cntr.searchCrossword();
					}
				});

				p_wrapper1.add(b_abrir);
				p_wrapper1.add(b_buscar);

				p_tab1.add(scroll, BorderLayout.CENTER);
				p_tab1.add(p_wrapper1, BorderLayout.SOUTH);
	}
	
	private void initTab2() {
		pWrappAdd = new JPanel();
		pWrappDelete = new JPanel();
		pWrappFriend = new JPanel();
		pButtons = new JPanel(new GridLayout(1,3));
		
		tFriend = new JTextArea("");
		tFriend.setPreferredSize(new Dimension(100, 20));
		
		bAdd = new JButton("Agregar amigo");
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String friend = tFriend.getText();
				boolean isFriend = false;
				
				if(!friend.equalsIgnoreCase("")){
					
					for (int i = 0; i < model.size(); i++) {
						if(friend.equalsIgnoreCase(model.get(i)))
							isFriend = true;
					}
					if(!isFriend){
						if(!cntr.addFriend(friend)){
							JOptionPane.showMessageDialog(null, "El usuario no existe",
									"Error",JOptionPane.ERROR_MESSAGE);
						}else{
							cntr.updateMainW();
						}
					}
					tFriend.setText("");
				}
			}
		});
		
		bDelete = new JButton("Eliminar");
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String friendToDelete = lFriends.getSelectedValue();
				if(friendToDelete != null){
					
					cntr.deleteFriend(friendToDelete);
					cntr.updateMainW();
					
				}
			}
		});
		
		model = new DefaultListModel<String>();
		lFriends = new JList<String>(model);
		sList = new JScrollPane(lFriends);
		sList.setViewportView(lFriends);
		
		pWrappAdd.add(bAdd);
		pWrappDelete.add(bDelete);
		pWrappFriend.add(tFriend);
		
		pButtons.add(pWrappDelete);
		pButtons.add(pWrappAdd);
		pButtons.add(pWrappFriend);
		
		p_tab2.add(sList,BorderLayout.CENTER);
		p_tab2.add(pButtons,BorderLayout.SOUTH);
	}
	
	private void initTab3() {
		pWAdd = new JPanel();
		pWDelete = new JPanel();
		p_buttons = new JPanel(new GridLayout(1,2));
		
		b_Add = new JButton("Abrir crucigrama");
		b_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (sf != -1) {
					if (tmodel.getValueAt(sf, 0) != null) {
						Integer crosswordId = peticiones.get(sf).getCrucigrama();
						String userOwner = peticiones.get(sf).getUsuario_source();
						cntr.openCrossword(crosswordId, userOwner);
						sf = -1;
					}
				}	
			}
		});
		
		b_Delete = new JButton("Rechazar crucigrama");
		b_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sf != -1) {
					if (tmodel.getValueAt(sf, 0) != null) {
						Integer crosswordId = peticiones.get(sf).getCrucigrama();
						String userOwner = peticiones.get(sf).getUsuario_source();
						cntr.deleteRequest(crosswordId, userOwner);
						cntr.updateMainW();
						sf = -1;
					}
				}
			}
		});
		
		tmodel = new DefaultTableModel(new String[] { "Usuario", "Crucigrama" }, 10);
		tPetitions = new JTable(tmodel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		sPetitions = new JScrollPane(tPetitions);
		sPetitions.setViewportView(tPetitions);

		tPetitions.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sf = tPetitions.rowAtPoint(e.getPoint());
			}
		});
		
		pWAdd.add(b_Add);
		pWDelete.add(b_Delete);
		
		p_buttons.add(pWAdd);
		p_buttons.add(pWDelete);
		
		p_tab3.add(sPetitions, BorderLayout.CENTER);
		p_tab3.add(p_buttons, BorderLayout.SOUTH);
	}	
	
	@Override
	public void onUserAccessAccept() {
		loadRows();
		
	}

	private void loadRows() {
		//tab1
		nCrossw = 0;
		Usuario u = this.cntr.getCurrentUser();
		ArrayList<Crucigrama> userCross = this.cntr.getUserCrosswords(u
				.getNombre());
		for (Crucigrama c : userCross) {

			tbm.setValueAt(c.getTitulo(), nCrossw, 0);
			tbm.setValueAt(c.getFecha(), nCrossw, 1);
			userCrosswords[nCrossw] = c.getId();
			nCrossw++;
		}
		//tab2
		model.clear();
		ArrayList<String> amigos = cntr.getAmigos();
		if(!amigos.isEmpty()){
			for(String amigo:amigos){
				model.addElement(amigo);
			}
		}
		//tab3
		nPet = 0;
		peticiones = cntr.getPeticiones();
		for(Peticion p : peticiones){
			tmodel.setValueAt(p.getUsuario_source(), nPet, 0);
			String title = cntr.getCrosswordTitle(p.getCrucigrama());
			tmodel.setValueAt(title, nPet, 1);
			nPet++;
		}
	}

	@Override
	public void onUserAccessRefused() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCurrentUserSetting(Usuario u) {
		// TODO Auto-generated method stub
		this.cntr.setCurrentUser(u);
	}

	@Override
	public void onOpenCrossword(Integer crossId, String user, String userPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdateCrosswords() {
		loadRows();
	}

}
