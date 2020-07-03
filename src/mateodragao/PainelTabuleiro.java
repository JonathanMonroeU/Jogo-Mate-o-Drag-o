package mateodragao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PainelTabuleiro extends JPanel{
	private static final long serialVersionUID = 2294710292947110323L;
	public static String DIRETORIO = PainelTabuleiro.class.getResource(".").getPath();
	protected GramaPanel celulas[][] = new GramaPanel[20][20];
	protected JLabel vidaDragao, vidaPrincesa, finish;
	protected JButton again;
	
	public PainelTabuleiro() {
		setLayout(new GridLayout(20,20));
		this.setBackground(new java.awt.Color(150, 0, 0));
		for (int i=0; i<20; i++) {
	    	for (int j=0; j<20; j++) {
	    		celulas[i][j] = new GramaPanel(); //new JPanel()
	    		add(celulas[i][j]);
	    		celulas[i][j].setLayout(new BorderLayout());		//celulas[i][j].setLayout(null);
	    		celulas[i][j].setBackground(Color.green);
	    		celulas[i][j].setBorder(BorderFactory.createLineBorder(Color.green,0));
	    	}
	    }
	}
	
	public void setElemento(int x, int y, PecaIcon p) {
		/*celulas[x][y].add(p);*/	celulas[x][y].add(p,BorderLayout.CENTER);
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
