package mateodragao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class JanelaJogo extends JFrame{
	private static final long serialVersionUID = -617666447542987164L;
	private JPanel painelControle;
	private PainelTabuleiro painelGrid;
	
	public JanelaJogo(String caminho, PainelTabuleiro tab) {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		painelGrid = tab;
		visual();
	}
	
	public void visual() {
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(painelGrid, BorderLayout.CENTER);
		
		painelControle = new JPanel();
	    painelControle.setLayout(new FlowLayout());
	    contentPane.add(painelControle, BorderLayout.SOUTH);
	    
	    setVisible(true);
	}
	
	public void adicionaElemento(PecaIcon obj) {
		painelGrid.setElemento(obj.getX(),obj.getY(),obj);
		SwingUtilities.updateComponentTreeUI(this);
	}
	/*private class NewContentPane extends JPanel{
		private static final long serialVersionUID = -4416680110711209505L;

		protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                Image img = imagemFundo.getImage();
                g.drawImage(img, 0, 0, this);
        }
    }*/
}
