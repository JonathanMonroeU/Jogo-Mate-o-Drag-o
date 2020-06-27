package mateodragao;

import mateodragao.components.DataProvider;
import mateodragao.components.Tabuleiro;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.ITabuleiro;

public class Application {
	public static String DIRETORIO =
		      Application.class.getResource(".").getPath();
		      
	public static void main(String[] args) {
		IDataProvider data = new DataProvider(100);
		ITabuleiro tabuleiro = new Tabuleiro();
		PainelMenu menu = new PainelMenu(tabuleiro, data);
		JanelaJogo janela = new JanelaJogo((PainelTabuleiro) tabuleiro, menu);
		
		/*while(status) {
			tabuleiro.mostraTabuleiro();
			status = data.insertData();
			//tabuleiro.receiveData(data);
		}	tabuleiro.play();*/
	}

}
