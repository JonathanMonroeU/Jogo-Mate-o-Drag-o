package mateodragao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PecaIcon extends JLabel implements ActionListener{
	private static final long serialVersionUID = 2743679226737045610L;
	private int x, y;
	
	public PecaIcon(String arquivoImage, int x, int y) {
		super(new ImageIcon(arquivoImage));
		//setSize(100,100); //estava 150x100
		//setBounds(0,0,50,50);
		this.x = x;
		this.y = y;
		//this.j = j;
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
