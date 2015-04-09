package abd.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import es.ucm.abd.crossword.CrosswordPanel;
import es.ucm.abd.crossword.CrosswordPanelEventListener;

import java.awt.Point;

import abd.controller.Controller;
import abd.model.Word;

class CrosswordWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller cntr;
	
	public CrosswordWindow(final Controller daoCntr, Integer crosswordId) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cntr = daoCntr;
		
		// Creamos la lista inicial con tres palabras
		List<Word> lista = cntr.getWordList(crosswordId);
		
		// Creamos el CrosswordPanel a partir de la lista.
		// Lo incrustamos en un JScrollPane para obtener barras de desplazamiento
		JScrollPane jScrollPane = new JScrollPane();
		this.add(jScrollPane);		
		final CrosswordPanel<Word> panel = new CrosswordPanel<Word>(jScrollPane, lista);
		jScrollPane.setViewportView(panel);
		
		// Registramos los manejadores de eventos del CrosswordPanel
        panel.addEventListener(new CrosswordPanelEventListener<Word>() {
            public void wordSelected(CrosswordPanel<Word> source, Word newWord) {
                if (newWord != null) {
                    System.out.println("Seleccionada la palabra " + newWord.getWord());
                } else {
                    System.out.println("Deseleccionada palabra");
                }
            }

            public void cellSelected(CrosswordPanel<Word> source, Point newCell) {
                if (newCell != null) {
                    System.out.println("Seleccionada la celda (" + newCell.x + ", " + newCell.y + ")");
                } else {
                    System.out.println("Deseleccionada celda");
                }
            }

            public void deselected(CrosswordPanel<Word> source) {
                System.out.println("Deselección!");
            }
        });
        
        // Añadimos un botón para mostrar las palabras del crucigrama
		JButton botonMostrar = new JButton("Mostrar palabras");
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*panel.showWord(word1);
				panel.showWord(word2);
				panel.showWord(word3);*/
			}
		});
		this.add(botonMostrar, BorderLayout.SOUTH);
		this.setSize(500, 500);
	}
	
}
