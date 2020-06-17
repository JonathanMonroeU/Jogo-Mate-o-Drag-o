package mateodragao.components;

import mateodragao.components.personagem.*;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.IMovimento;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public class Tabuleiro implements ITabuleiro{
	private IMovimento vAtaque[][][];
	private IPersonagem vPersonagem[][];
	private int DragonPosition[];
	private int numeroSoldados;
	
	public Tabuleiro() {
		vAtaque = new IMovimento[16][16][2];
		vPersonagem = new IPersonagem[16][16];
		DragonPosition = new int[2];
		numeroSoldados = 0;
		
		vPersonagem[0][7] = new Dragao();
		DragonPosition[0] = 0;
		DragonPosition[1] = 7;
	}
	
	@Override
	public void play() {
		while (numeroSoldados != 0 && DragonPosition[0] != -1) {
			modificaTabuleiro();
		}
	}

	@Override
	public void modificaTabuleiro() {
		//primeira passagem para mover e disparar ataques
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null) {
					vPersonagem[i][j].disparaAtaque(this);
					vPersonagem[i][j].move(this);
					//decidir entre tabuleiro fazer movimento ou peca
				}
				
				if (vAtaque[i][j][0] != null) {
					vAtaque[i][j][0].move(this); 	
				}
				if (vAtaque[i][j][1] != null) {
					vAtaque[i][j][1].move(this);
				}	
			}
		}
		
		//segunda passagem para analisar conflitos
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null) {
					vPersonagem[i][j].perdeVida(vAtaque[i][j][0]);
					//analisar como vai ser a hora da morte
				}	
			}
		}
		
	}

	@Override
	public void mostraTabuleiro() {
		
		
	}

	@Override
	public IMovimento getPeca(int x, int y) {
		return vPersonagem[x][y];
	}
	
	@Override
	public void setPeca(int x, int y, IPersonagem peca) {
		vPersonagem[x][y] = peca;
		
	}

	@Override
	public void putPeca(int x, int y, int tipo) {
		switch(tipo) {
			case 1:
				vPersonagem[x][y] = new Arqueiro();
				break;
			case 2:
				vPersonagem[x][y] = new Lanceiro();
				break;
			case 3:
				vPersonagem[x][y] = new Mago();
				break;
			case 4:
				vPersonagem[x][y] = new Catapulta();
				break;
		}
		numeroSoldados += 1;
	}

	@Override
	public void removePeca(int x, int y) {
		vPersonagem[x][y] = null;
		numeroSoldados -= 1;
	}

	@Override
	public void receiveData(IDataProvider dataProvider) {
		int position[] = dataProvider.getData();
		if (position[0] == 0)  //zero é remocao
			removePeca(position[1],position[2]);
		else
			putPeca(position[1],position[2],position[0]);
	}

	@Override
	public int[] getDragonPosition() {
		return DragonPosition;
	}

}
