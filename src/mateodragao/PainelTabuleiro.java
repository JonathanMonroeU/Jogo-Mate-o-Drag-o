package mateodragao;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PainelTabuleiro extends JPanel{
	private static final long serialVersionUID = 2294710292947110323L;
	//private ImageIcon imagem;
	protected JPanel celulas[][] = new JPanel[16][16];
	
	public PainelTabuleiro() {
		setLayout(new GridLayout(16,16));
		for (int i=0; i<16; i++) {
	    	for (int j=0; j<16; j++) {
	    		celulas[i][j] = new JPanel(); //new JPanel()
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
	/*private class NewContentPane extends JPanel{
		private static final long serialVersionUID = -4416680110711209505L;

		protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                Image img = imagem.getImage();
                g.drawImage(img, 0, 0, this);
        }
    }*/
}
