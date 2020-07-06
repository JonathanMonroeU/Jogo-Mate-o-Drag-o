package src.tabuleiro;

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

import src.personagem.PecaIcon;

public class PainelTabuleiro extends JPanel{
	private static final long serialVersionUID = 2294710292947110323L;
	public static String DIRETORIO = PainelTabuleiro.class.getResource(".").getPath();
	protected GramaPanel celulas[][] = new GramaPanel[20][20]; //Matriz que guarda os componentes gráficos
	protected JLabel vidaDragao, //Campo que mostra a vida do dragão
					 vidaPrincesa, //Campo que mostra a vida da princesa
					 finish; //Campo que mostra a mensagem de fim de jogo
	protected JButton again; //Botão de "Jogar novamente"
	
	//Customização inicial do painel do tabuleiro
	public PainelTabuleiro() {
		setLayout(new GridLayout(20,20));
		this.setBackground(new Color(150, 0, 0));
		for (int i=0; i<20; i++) {
	    	for (int j=0; j<20; j++) {
	    		celulas[i][j] = new GramaPanel(); //new JPanel()
	    		add(celulas[i][j]);
	    		celulas[i][j].setLayout(new BorderLayout());		
	    		celulas[i][j].setBackground(Color.green);
	    		celulas[i][j].setBorder(BorderFactory.createLineBorder(Color.green,0));
	    	}
	    }
	}
	
	//Esse método adiciona componentes gráficos ao vetor "celulas"
	public void setElemento(int x, int y, PecaIcon p) {
		celulas[x][y].add(p,BorderLayout.CENTER);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	//Esse método remove componentes gráficos do vetor "células"
	public void removeElemento(int x, int y, PecaIcon p) {
		celulas[x][y].remove(p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	//Essa classe privada realiza a inserção de uma imagem de grama como imagem de fundo de cada célula do grid
	private class GramaPanel extends JPanel { 
		private static final long serialVersionUID = -3569117592893744545L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Image gramaImage = Toolkit.getDefaultToolkit().getImage(DIRETORIO+"assets/grama200.png");
			Graphics2D graf = (Graphics2D) g;
			graf.drawImage(gramaImage, 0, 0, this);

		}
	}
}
