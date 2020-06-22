package mateodragao.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mateodragao.Metronomo;
import mateodragao.PainelTabuleiro;
import mateodragao.PecaIcon;
import mateodragao.components.personagem.Arqueiro;
import mateodragao.components.personagem.Catapulta;
import mateodragao.components.personagem.Dragao;
import mateodragao.components.personagem.Lanceiro;
import mateodragao.components.personagem.Mago;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;

public class Tabuleiro extends PainelTabuleiro implements ITabuleiro, ActionListener{
	private static final long serialVersionUID = -4923736996545875913L;
	private IProjetil vProjetil[][][];
	private int vConflito[][];
	private IPersonagem vPersonagem[][];
	private int DragonPosition[];
	private int numeroSoldados;
	private int atual;
	private Metronomo metro = new Metronomo(2000,10);
		
	public Tabuleiro() {
		super();
		vProjetil = new IProjetil[16][16][2];
		vConflito=new int [20][4];
		for (int i=0; i<20; i++) {
			for (int j=0; j<2; j++) {
				vConflito[i][j]= -1;
			}
		}
		
		vPersonagem = new IPersonagem[16][16];
		DragonPosition = new int[2];
		numeroSoldados = 0;
		
		//vPersonagem[0][7] = new Dragao(0,7);
		vPersonagem[1][8] = new Dragao(1,8);
		//vPersonagem[0][8]=vPersonagem[0][7];
		//vPersonagem[1][7]=vPersonagem[0][7];
		//vPersonagem[1][8]=vPersonagem[0][7];
		setElemento(1,8,(PecaIcon) vPersonagem[1][8]);
		
		DragonPosition[0] = 1;
		DragonPosition[1] = 8;
		atual=-1;
		
		metro.addActionListener(this);
	}
	
	@Override
	public void play() {
		metro.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (numeroSoldados != 0 && DragonPosition[0] != -1) {
			modificaTabuleiro();
		}
		else {
			metro.stop();
		}
	}

	@Override
	public void modificaTabuleiro() {
		//primeira passagem para mover e disparar projeteis
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null) {
					//vPersonagem[i][j].disparaProjetil(this);
					vPersonagem[i][j].move(this);
			
				}
				
				if (vProjetil[i][j][0] != null) 
					vProjetil[i][j][0].move(this); 
				if (vProjetil[i][j][1] != null) 
					vProjetil[i][j][1].move(this); 
				
			}
		}
		
		//OBS:passagem pelo vetor de conflitos de projeteis
		if (atual>-1) {
			for (int i=0;i<=atual;i++) {
				resolveConflito(vConflito[atual][0],vConflito[atual][1],vConflito[atual][2],vConflito[atual][3]);
			}
		}
		
		//segunda passagem para analisar projeteis que acertaram
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null && vProjetil[i][j][0]!=null) { 
					vPersonagem[i][j].perdeVida(vProjetil[i][j][0],this);
					setProjetil(i, j, 0, null);
				}
				if (vPersonagem[i][j] != null && vProjetil[i][j][1]!=null) {
					vPersonagem[i][j].perdeVida(vProjetil[i][j][1],this);
					setProjetil(i, j, 1, null);
				}
				if (vPersonagem[i][j] != null) {
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
		if (peca != null)
			setElemento(x, y, (PecaIcon) vPersonagem[x][y]);
	}

	@Override
	public void putPeca(int x, int y, int tipo) {
		switch(tipo) {
			case 1:
				vPersonagem[x][y] = new Arqueiro(x,y);
				setElemento(x, y, (PecaIcon) vPersonagem[x][y]);
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
	public IProjetil getProjetil(int x, int y, int z) {
		return vProjetil[x][y][z];
	}
	
	@Override //olhar bem
	public void setProjetil(int x, int y,int z, IProjetil Projetil) {
		vProjetil[x][y][z] = Projetil;
		
	}
	
	@Override
	public void putProjetil(int x, int y, int z, IProjetil Projetil) {
		vProjetil[x][y][z] = Projetil;
		setElemento(x,y,(PecaIcon) Projetil);
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
	public void setDragonPosition(int x, int y) {
		DragonPosition[0]=x;
		DragonPosition[1]=y;
	}
	
	
	@Override
	public void adicionaConflito(int x, int y,int newX, int newY) {
		atual+=1;
		vConflito[atual][0]=x;
		vConflito[atual][1]=y;
		vConflito[atual][2]=newX;
		vConflito[atual][3]=newY;
		vProjetil[x][y][0].setEmConflito(1);
	}
	
	public void resolveConflito (int x, int y, int newX, int newY) {
		if (vProjetil[x][y][0].getDano()>vProjetil[newX][newY][0].getDano()) {
			vProjetil[x][y][0].setEmConflito(0);
			setProjetil(newX, newY, 0, vProjetil[x][y][0]);
			setProjetil(x, y, 0, null);
		}
		else
			setProjetil(x, y, 0, null);
		
		
	}

	
}
