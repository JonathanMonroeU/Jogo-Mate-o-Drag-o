package mateodragao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PainelTabuleiro extends JPanel{
	private static final long serialVersionUID = 2294710292947110323L;
	public static String DIRETORIO = PainelTabuleiro.class.getResource(".").getPath();
	protected GramaPanel celulas[][] = new GramaPanel[16][16];
	
	public PainelTabuleiro() {
		setLayout(new GridLayout(16,16));
		for (int i=0; i<16; i++) {
	    	for (int j=0; j<16; j++) {
	    		celulas[i][j] = new GramaPanel(); //new JPanel()
	    		add(celulas[i][j]);
	    		celulas[i][j].setLayout(null);
	    		celulas[i][j].setBackground(Color.green);
	    		celulas[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
	    	}
	    }
	}
	
	public void setElemento(int x, int y, PecaIcon p) {
		celulas[x][y].add(p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void removeElemento(int x, int y, PecaIcon p) {
		celulas[x][y].remove(p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private class GramaPanel extends JPanel { 
		private static final long serialVersionUID = -3569117592893744545L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Image gramaImage = Toolkit.getDefaultToolkit().getImage(DIRETORIO+"grama200.png");
			Graphics2D graf = (Graphics2D) g;
			graf.drawImage(gramaImage, 0, 0, this);

		}
	}
}
