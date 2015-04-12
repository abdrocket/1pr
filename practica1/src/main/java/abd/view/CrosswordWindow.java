package abd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.ucm.abd.crossword.CrosswordPanel;
import es.ucm.abd.crossword.CrosswordPanelEventListener;

import java.awt.Point;

import abd.controller.Controller;
import abd.model.Palabra;
import abd.model.Word;

class CrosswordWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller cntr;
	private List<Word> palabras;
	private String userPlayer;
	private String userOwner;
	
	private JButton bAccept;
	private JButton bPeticion;
	private JLabel lTitle;
	private JLabel lWord;
	private JLabel lImage;
	private JTextField tAnswer;
	private JTextArea tDescription;
	
	private JPanel pAccept;
	private JPanel pPeticion;
	private JPanel pTitle;
	private JPanel pWord;
	private JPanel pImage;
	private JPanel pAnswer;
	private JPanel pDescription;
	private JPanel pGridBot;
	private JPanel pg1;
	private JPanel pg2;
	
	private Word wordSelected;
	
	private JScrollPane sDescription;
	
	public CrosswordWindow(final Controller daoCntr, Integer crosswordId, String userOwner, String userPlayer) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cntr = daoCntr;
		this.userPlayer = userPlayer;
		this.userOwner = userOwner;
		// Creamos la lista inicial con tres palabras
		palabras = cntr.getWordList(crosswordId, userOwner);
		
		// Creamos el CrosswordPanel a partir de la lista.
		// Lo incrustamos en un JScrollPane para obtener barras de desplazamiento
		JScrollPane jScrollPane = new JScrollPane();
		this.add(jScrollPane);		
		final CrosswordPanel<Word> panel = new CrosswordPanel<Word>(jScrollPane, palabras);
		jScrollPane.setViewportView(panel);
		
		// Registramos los manejadores de eventos del CrosswordPanel
        panel.addEventListener(new CrosswordPanelEventListener<Word>() {
            public void wordSelected(CrosswordPanel<Word> source, Word newWord) {
                if (newWord != null) {
                    wordSelected = newWord;
                    lWord.setText(wordSelected.getWord().length()+" letras:");
                    Palabra p = cntr.getWordInfo(wordSelected.getPalabraRef());
                    tDescription.setText(p.getEnunciado());
                    lImage.setIcon(new ImageIcon(p.getImagen()));
                } else {
                	lWord.setText("X letras:");
                    wordSelected = null;
                }
            }
            /*panel.showWord(word1);
			panel.showWord(word2);
			panel.showWord(word3);*/
            public void cellSelected(CrosswordPanel<Word> source, Point newCell) {
                if (newCell != null) {
                    
                } else {
                	
                }
            }

            public void deselected(CrosswordPanel<Word> source) {
                System.out.println("Deselección!");
            }
        });
        
       
		
		pAccept = new JPanel();
		pAnswer = new JPanel();
		pDescription = new JPanel();
		pPeticion = new JPanel();
		pWord = new JPanel();
		pTitle = new JPanel();
		pImage = new JPanel();
		pGridBot = new JPanel(new FlowLayout());
		pg1 = new JPanel(new FlowLayout());
		pg2 = new JPanel(new FlowLayout());
		
		lTitle = new JLabel(cntr.getCrosswordTitle(crosswordId));
		lWord = new JLabel("X letras:");
		lImage = new JLabel();
		
		bAccept = new JButton("Comprobar");
		bAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		bPeticion = new JButton("Enviar a amigo..");
		bPeticion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		tAnswer = new JTextField("");
		tAnswer.setPreferredSize(new Dimension(100, 20));
		tDescription = new JTextArea();
		tDescription.setPreferredSize(new Dimension(400, 100));
		sDescription = new JScrollPane(tDescription);
		sDescription.setViewportView(tDescription);
		
		pAccept.add(bAccept);
		pPeticion.add(bPeticion);
		pAnswer.add(tAnswer);
		pDescription.add(sDescription);
		pImage.add(lImage);
		pWord.add(lWord);
		pTitle.add(lTitle);
		
		pg1.add(pImage);
		pg1.add(pDescription);
		
		pg2.add(pWord);
		pg2.add(pAnswer);
		pg2.add(pAccept);
		pg2.add(pPeticion);
		
		pGridBot.add(pg1);
		pGridBot.add(pg2);
			
		this.add(pTitle,BorderLayout.NORTH);
		this.add(pGridBot, BorderLayout.SOUTH);
		this.setBounds(300,300,1000, 1000);
		this.setLocationRelativeTo(null);
	}
	
}
