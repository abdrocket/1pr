package abd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import abd.controller.Controller;
import abd.model.Crucigrama;

public class SearchWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tStringToSearch;
	private JButton bSearch;
	private JButton bAdd;
	private JButton bBack;

	private JList<String> lCrosswords;
	private DefaultListModel<String> model;
	
	private JPanel pMain;
	private JPanel pWrappSearch;
	private JPanel pWrappAdd;
	private JPanel pWrappText;
	private JPanel pWrapperBack;
	
	private JScrollPane sList;
	
	private ArrayList<Integer> crossIds;
	
	private Controller cntr;
	
	public SearchWindow(final Controller daoController){
		
		this.cntr = daoController;
		initGUI();

	}
	
	private void initGUI(){
		
		this.setLayout(new BorderLayout());
		this.setBounds(300, 300, 400, 300);
		this.setLocationRelativeTo(null);
		
		tStringToSearch = new JTextField("");
		tStringToSearch.setPreferredSize(new Dimension(100, 20));
		
		bSearch = new JButton("Buscar");
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				crossIds = new ArrayList<Integer>();
				String valueToSearch = tStringToSearch.getText();
				if(valueToSearch != null){
					List<Crucigrama> crucigramas= cntr.getCrosswordsLike(valueToSearch);
					for (Crucigrama c:crucigramas) {
						model.addElement(c.getTitulo());
						crossIds.add(c.getId());
					}
				}
				
			}
		});
		bAdd = new JButton("Agregar crucigrama");
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = lCrosswords.getSelectedIndex();
				if(pos != -1){
					Integer crossId = crossIds.get(pos);
					if(!cntr.checkCrossword(crossId)){
						cntr.addCrucigrama(crossId);
						close();
						cntr.updateMainW();
						cntr.returnToMain();
					}else{
						JOptionPane.showMessageDialog(null, "El crucigrama ya esta siendo realizado",
								"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		bBack = new JButton("Volver");
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
		
		pWrappAdd = new JPanel();
		pWrappSearch = new JPanel();
		pWrappText = new JPanel();
		pWrapperBack = new JPanel();
		pMain = new JPanel(new GridLayout(2,2));
		
		pWrappAdd.add(bSearch);
		pWrappSearch.add(bAdd);
		pWrapperBack.add(bBack);
		pWrappText.add(tStringToSearch);
		
		pMain.add(pWrappText);
		pMain.add(pWrappAdd);
		pMain.add(pWrappSearch);
		pMain.add(pWrapperBack);
		
		this.add(pMain,BorderLayout.SOUTH);
		this.add(sList, BorderLayout.CENTER);
		
		
	}

	private void close(){
		this.setVisible(false);
	}
	
}
