package abd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abd.controller.Controller;
import abd.model.Usuario;
import abd.observer.UserObserver;

public class SearchWindow extends JFrame implements UserObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lStringToSearch;
	private JTextField tStringToSearch;
	private JButton bSearch;
	private JButton bAdd;

	private JList lCrosswords;
	
	private JPanel pMain;
	private JPanel pWrappSearch;
	private JPanel pWrappAdd;
	private JPanel pWrappLabel;
	private JPanel pWrappText;
	
	private Integer[] crossIds;
	
	private Controller cntr;
	
	public SearchWindow(final Controller daoController){
		crossIds = new Integer[50];
		this.cntr = daoController;
		initGUI();
		this.cntr.addUserObserver(this);
		pack();
	}
	
	private void initGUI(){
		
		this.setLayout(new BorderLayout(20, 20));
		this.setPreferredSize(new Dimension(600,400));
		this.setLocationRelativeTo(null);
		
		lStringToSearch = new JLabel("Cadena para buscar:");
		tStringToSearch = new JTextField("");
		tStringToSearch.setPreferredSize(new Dimension(100, 20));
		
		bSearch = new JButton("Buscar");
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valueToSearch = tStringToSearch.getText();
				if(valueToSearch != null){
					List<String> titles = cntr.getCrosswordsLike(valueToSearch);
					for (String title:titles) {
						lCrosswords.add(new JLabel(title));//No se si asi
					}
				}
				
			}
		});
		bAdd = new JButton("Agregar crucigrama");
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		lCrosswords = new JList<String>();
		
		pWrappAdd = new JPanel();
		pWrappSearch = new JPanel();
		pWrappLabel = new JPanel();
		pWrappText = new JPanel();
		pMain = new JPanel(new GridLayout(2,2));
		
		pWrappAdd.add(bSearch);
		pWrappSearch.add(bAdd);
		pWrappLabel.add(lStringToSearch);
		pWrappText.add(tStringToSearch);
		
		pMain.add(pWrappLabel);
		pMain.add(pWrappText);
		pMain.add(pWrappSearch);
		pMain.add(pWrappAdd);
		
		this.add(pMain,BorderLayout.SOUTH);
		this.add(lCrosswords, BorderLayout.CENTER);
		
		
	}
	@Override
	public void onOpenCrossword(Integer crossId, String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserAccessAccept() {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void onSearchCrossword() {
		// TODO Auto-generated method stub
		
	}

}
