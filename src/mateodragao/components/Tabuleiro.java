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
	private IProjetil vConflito[];
	private IPersonagem vPersonagem[][];
	private int DragonPosition[];
	private int numeroSoldados;
	private int atual;
	private Metronomo metro = new Metronomo(2000,4);
		
	public Tabuleiro() {
		super();
		vProjetil = new IProjetil[16][16][2];
		vConflito = new IProjetil [20];
		
		vPersonagem = new IPersonagem[16][16];
		DragonPosition = new int[2];
		numeroSoldados = 0;
		
		vPersonagem[8][1] = new Dragao(8,1);
		vPersonagem[7][1]=vPersonagem[7][1];
		vPersonagem[8][0]=vPersonagem[8][0];
		vPersonagem[7][0]=vPersonagem[7][0];
		setElemento(8,1,(PecaIcon) vPersonagem[8][1]);
		
		DragonPosition[0] = 8;
		DragonPosition[1] = 1;
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
		System.out.println("----------------------------");
		
		//primeiro faz as modificações necessárias no dragão, dispara a bola de fogo e move
				vPersonagem[DragonPosition[0]][DragonPosition[1]].disparaProjetil(this);
				vPersonagem[DragonPosition[0]][DragonPosition[1]].move(this);
		
		//primeira passagem por todas as posições do tabuleiro para mover personagens e projeteis e disparar projeteis
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (vPersonagem[i][j] != null) {
					if (vPersonagem[i][j].getVida()<4 && vPersonagem[i][j].getJaAgiu()==0) {	//se o personagem não for o dragão e não tiver agido ainda nesse tempo do jogo
						vPersonagem[i][j].setJaAgiu(1);
						vPersonagem[i][j].disparaProjetil(this);
						vPersonagem[i][j].move(this);
						
					}
				}
				
				if (vProjetil[i][j][0] != null) {
					if (vProjetil[i][j][0].getJaAgiu()==0)
						vProjetil[i][j][0].move(this);
					
				}if (vProjetil[i][j][1] != null) {
					if (vProjetil[i][j][1].getJaAgiu()==0)
						vProjetil[i][j][1].move(this); 
				}
			}
		}
		
		//passagem pelo vetor de conflitos de projeteis
		if (atual>-1) {
			for (int i=0;i<=atual;i++) {
				resolveConflito(vConflito[atual]);
			}
			atual=-1;
		}
		
		//segunda passagem por todo o tabuleiro para analisar projeteis que acertaram personagens
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
					vPersonagem[i][j].setJaAgiu(0);
					if (vPersonagem[i][j].getVida()<=0) 
						removePeca(i,j); 	//morte
				}	
				if (vProjetil[i][j][0] != null) 
					vProjetil[i][j][0].setJaAgiu(0);
						
					
				if (vProjetil[i][j][1] != null) 
					vProjetil[i][j][1].setJaAgiu(0);
				
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
	
	@Override 
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
	public void adicionaConflito(IProjetil projetil) {
		atual+=1;
		vConflito[atual]=projetil;
		projetil.setEmConflito(1);
	}
	
	public void resolveConflito (IProjetil projetil) {
		if(vProjetil[projetil.getxConflito()][projetil.getyConflito()][0]!=null) {
			if (projetil.getDano()>vProjetil[projetil.getxConflito()][projetil.getyConflito()][0].getDano()) {
				projetil.setEmConflito(0);
				projetil.setJaAgiu(1);		System.out.println("rescon dano:"+projetil.getDano()+" newX:"+projetil.getxConflito()+" newY:"+projetil.getyConflito());
				
				setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, projetil);
				setProjetil(projetil.getX(), projetil.getY(), 0, null);
				projetil.setX(projetil.getxConflito());
				projetil.setY(projetil.getyConflito());
			}
			else
				setProjetil(projetil.getX(), projetil.getY(), 0, null);
		}else {
			projetil.setEmConflito(0);
			projetil.setJaAgiu(1);		System.out.println("rescon dano:"+projetil.getDano()+" newX:"+projetil.getxConflito()+" newY:"+projetil.getyConflito());
			
			setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, projetil);
			setProjetil(projetil.getX(), projetil.getY(), 0, null);
			projetil.setX(projetil.getxConflito());
			projetil.setY(projetil.getyConflito());
			}
	}

	
}
