package src.personagem;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class PecaIcon extends JLabel {
	private static final long serialVersionUID = 2743679226737045610L;
	
	public PecaIcon(String arquivoImage) {
		super(new ImageIcon(arquivoImage));
	}
	
}
