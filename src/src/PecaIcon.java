package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PecaIcon extends JLabel implements ActionListener{
	private static final long serialVersionUID = 2743679226737045610L;
	private int x, y;
	
	public PecaIcon(String arquivoImage) {
		super(new ImageIcon(arquivoImage));
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
