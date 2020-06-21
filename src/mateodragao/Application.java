package mateodragao;

import mateodragao.components.DataProvider;
import mateodragao.components.Tabuleiro;
import mateodragao.components.personagem.Arqueiro;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public class Application {
	public static String DIRETORIO =
		      Application.class.getResource(".").getPath();
		      
	public static void main(String[] args) {
		IDataProvider data = new DataProvider(100);
		ITabuleiro tabuleiro = new Tabuleiro();
		boolean status = true;
		
		JanelaJogo janela = new JanelaJogo(DIRETORIO+"grama.png", (PainelTabuleiro) tabuleiro);
		
		IPersonagem p1 = new Arqueiro(10,10);
		janela.adicionaElemento((PecaIcon) p1);
		p1.disparaProjetil(tabuleiro);
		p1.move(tabuleiro);
		p1.move(tabuleiro);
		p1.move(tabuleiro);
		p1.move(tabuleiro);
		
		/*while(status) {
			tabuleiro.mostraTabuleiro();
			status = data.insertData();
			tabuleiro.receiveData(data);
		}	
		tabuleiro.play();*/
	}

}
