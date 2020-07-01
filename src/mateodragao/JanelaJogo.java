package mateodragao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mateodragao.interfaces.IMenu;
import mateodragao.interfaces.ITabuleiro;


public class JanelaJogo extends JFrame{
	private static final long serialVersionUID = -617666447542987164L;
	private IMenu painelMenu;
	private ITabuleiro painelGrid;
	
	public JanelaJogo(ITabuleiro tab, IMenu menu) {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		painelGrid = tab;
		painelMenu = menu;
		visual();
	}
	
	public void visual() {
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(painelGrid.getPanel(), BorderLayout.CENTER);
	    contentPane.add(painelMenu.getPanel(), BorderLayout.EAST);
	    
	    JPanel letras = new JPanel();
	    letras.setLayout(new BoxLayout(letras, BoxLayout.X_AXIS));
	    JPanel vLetras = new JPanel();
	    vLetras.setLayout(new GridLayout(1,16));
	    vLetras.setPreferredSize(new Dimension(0,20));
	    for (char c = 'a'; c<'q'; c++) {
	    	JLabel j = new JLabel(Character.toString(Character.toUpperCase(c)));
	    	j.setHorizontalAlignment(0);
	    	j.setFont(new Font("Arial", Font.BOLD, 18));
	    	vLetras.add(j);
	    }
	    letras.add(Box.createRigidArea(new Dimension(30,0)));
	    letras.add(vLetras);
	    letras.add(Box.createRigidArea(new Dimension(360,0)));
	    contentPane.add(letras, BorderLayout.SOUTH);
	    
	    JPanel numeros = new JPanel();
	    numeros.setLayout(new BoxLayout(numeros, BoxLayout.Y_AXIS));
	    JPanel vNumeros = new JPanel();
	    vNumeros.setLayout(new GridLayout(16,1));
	    vNumeros.setPreferredSize(new Dimension(30,0));
	    for (int n = 1; n<=16; n++) {
	    	JLabel j = new JLabel(Integer.toString(n));
	    	j.setVerticalAlignment(0);
	    	j.setHorizontalAlignment(0);
	    	j.setFont(new Font("Arial", Font.BOLD, 18));
	    	vNumeros.add(j);
	    }
	    
	    numeros.add(vNumeros);
	    contentPane.add(numeros, BorderLayout.WEST);
	    setVisible(true);
	}
	
	/*public void adicionaElemento(PecaIcon obj) {
		painelGrid.setElemento(obj.getX(),obj.getY(),obj);
		SwingUtilities.updateComponentTreeUI(this);
	}*/
	
	
	/*private class NewContentPane extends JPanel{
		private static final long serialVersionUID = -4416680110711209505L;

		protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                Image img = imagemFundo.getImage();
                g.drawImage(img, 0, 0, this);
        }
    }*/
}
