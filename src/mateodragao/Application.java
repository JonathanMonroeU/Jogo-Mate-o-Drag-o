package mateodragao;

import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.ITabuleiro;
import mateodragao.components.*;

public class Application {

	public static void main(String[] args) {
		IDataProvider data = new DataProvider();
		ITabuleiro tabuleiro = new Tabuleiro();
		
		data.insertData();
		tabuleiro.play();

	}

}
