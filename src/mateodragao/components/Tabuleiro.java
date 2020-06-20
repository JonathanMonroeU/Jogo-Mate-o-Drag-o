package mateodragao.components;

import mateodragao.components.personagem.Arqueiro;
import mateodragao.components.personagem.Catapulta;
import mateodragao.components.personagem.Dragao;
import mateodragao.components.personagem.Lanceiro;
import mateodragao.components.personagem.Mago;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public class Tabuleiro implements ITabuleiro{
	private IProjetil vProjetil[][][];
	private int vConflitos [][];
	private IPersonagem vPersonagem[][];
	private int DragonPosition[];
	private int numeroSoldados;
	private int atual;
	
	public Tabuleiro() {
		vProjetil = new IProjetil[16][16][2];
		vConflitos=new int [20][2];
		for (int i=0; i<20; i++) {
			for (int j=0; j<2; j++) {
				vConflitos[i][j]= -1;
			}
		}
		
		vPersonagem = new IPersonagem[16][16];
		DragonPosition = new int[2];
		numeroSoldados = 0;
		
		vPersonagem[0][7] = new Dragao(0,7);
		DragonPosition[0] = 0;
		DragonPosition[1] = 7;
		atual=-1;
	}
	
	@Override
	public void play() {
		while (numeroSoldados != 0 && DragonPosition[0] != -1) {
			modificaTabuleiro();
		}
	}

	@Override
	public void modificaTabuleiro() {
		//primeira passagem para mover e disparar projeteis
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null) {
					vPersonagem[i][j].disparaProjetil(this);
					vPersonagem[i][j].move(this);
			
				}
				
				if (vProjetil[i][j][0] != null) 
					vProjetil[i][j][0].move(this); 	
			}
		}
		
		//OBS:passagem pelo vetor de conflitos
		if (atual>-1) {
			for (int i=0;i<=atual;i++) {
				
			
			}
		}
		
		
		
		
		//segunda passagem para analisar conflitos
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null && vProjetil[i][j][0]!=null) {
					vPersonagem[i][j].perdeVida(vProjetil[i][j][0],this);
					if (vPersonagem[i][j].getVida()<=0) 
						removePeca(i,j); 	//morte
				}	
			}
		}
		
	}

	@Override
	public void mostraTabuleiro() {
		
		
	}

	@Override
	public IPersonagem getPeca(int x, int y) {
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
				vPersonagem[x][y] = new Arqueiro(x,y);
				break;
			case 2:
				vPersonagem[x][y] = new Lanceiro(x,y);
				break;
			case 3:
				vPersonagem[x][y] = new Mago(x,y);
				break;
			case 4:
				vPersonagem[x][y] = new Catapulta(x,y);
				break;
		}
		numeroSoldados += 1;
	}
	
	@Override
	public IProjetil getProjetil(int x, int y) {
		return vProjetil[x][y][0];
	}
	
	@Override //olhar bem
	public void setProjetil(int x, int y, IProjetil Projetil) {
		vProjetil[x][y][0] = Projetil;
		
	}
	
	@Override
	public void putProjetil(int x, int y, IProjetil Projetil) {
		vProjetil[x][y][0] = Projetil;
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
	
	@Override
	public void adicionaConflito(int x, int y) {
		atual+=1;
		vConflitos[atual][0]=x;
		vConflitos[atual][1]=y;
		
	}
	
	
}
