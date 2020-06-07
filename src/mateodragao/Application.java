package mateodragao;

import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.ITabuleiro;
import mateodragao.components.*;

public class Application {

	public static void main(String[] args) {
		IDataProvider data = new DataProvider(100);
		ITabuleiro tabuleiro = new Tabuleiro();
		boolean status = true;
		
		while(status) {
			tabuleiro.mostraTabuleiro();
			status = data.insertData();
			tabuleiro.receiveData(data);
		}	
		tabuleiro.play();
	}

}
