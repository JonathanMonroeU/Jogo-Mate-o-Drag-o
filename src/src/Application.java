package src;

import src.janelajogo.JanelaJogo;

public class Application {
	public static String DIRETORIO =
		      Application.class.getResource(".").getPath();
		      
	public static void main(String[] args) {
		JanelaJogo janela = new JanelaJogo();
		janela.setTitle("Jogo - Mate O Dragão");
	}

}
