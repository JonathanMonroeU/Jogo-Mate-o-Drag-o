package mateodragao;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelMenu extends JPanel{
	private static final long serialVersionUID = 6299309752925290728L;
	
	public PainelMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel titulo = new JLabel("Jogo Mate o Dragão");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		JLabel titulo2 = new JLabel("Adicione personagens e comece o jogo!");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo2.setAlignmentX(CENTER_ALIGNMENT);
		add(titulo);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(titulo2);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton addPersonagem = new JButton("Adicionar Personagem");
		JButton remPersonagem = new JButton("Remover Personagem");
		JButton iniciar = new JButton("Iniciar Jogo");
		
		addPersonagem.setAlignmentX(CENTER_ALIGNMENT);
		remPersonagem.setAlignmentX(CENTER_ALIGNMENT);
		iniciar.setAlignmentX(CENTER_ALIGNMENT);
		
		add(addPersonagem);
		add(remPersonagem);
		add(iniciar);
		
	}
}
