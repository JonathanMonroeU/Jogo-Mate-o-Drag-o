package src.projetil;

import src.tabuleiro.PainelTabuleiro;
import src.PecaIcon;
import src.tabuleiro.ITabuleiro;

public class Projetil extends PecaIcon implements IProjetil{
	private static final long serialVersionUID = -459082752300783478L;
	protected int x, y, z, 
					dano, 		//Dano efetuado no inimigo pelo projétil.
					velocidade, //Número de casas que o projetil se move por tempo do jogo.	
					xConflito, yConflito, //Posição ocupada por outro projétil, para o qual esse projetil quer se movimentar.
					emConflito, //Se o projetil estiver guardado no vetor emConflito para ainda ser resolvido, é igual 1, se não, é igual a 0.
					jaAgiu, //Se for igual a 0, o projétil ainda não agiu naquele tempo, se for 1 já. 
					freqMov,//Frequência com que o projétil se move, ou seja, quantos passos entre um movimento e o próximo.
					freqM;	//Se for 0, é a vez do projétil se movimentar, se não, freqM vai aumentar de tempo em tempo até ficar igual a freqMov e ser resetado para 0 novamente.
	protected String direcao; //Direcao em que o projetil se move.
	
	//Inicia o projétil com o caminho para sua respectiva imagem e sua posição x, y e z.
	public Projetil(String caminho, int x, int y, int z) {
		super(caminho);
		this.x = x;
		this.y = y;
		this.z = z;
		freqM=0;
	}
	
	/*Dependendo do atributo de direção do projétil, o método move ele em uma das 8 direções possíveis, 
	 * se for sua vez se mover, indicada quando freqM=0. */
	@Override
	public void move(ITabuleiro tab) {
		if(freqM==0) {	
			int newX=x;
			int newY=y;
		
			switch(direcao) {
			case "ci":
				newX-=velocidade;
				break;
			case "bx":
				newX+=velocidade;
				break;
			case "es":
				newY-=velocidade;
				break;
			case "di":
				newY+=velocidade;
				break;
			case "cies":
				newX-=velocidade;
				newY-=velocidade;
				break;
			case "cidi":
				newX-=velocidade;
				newY+=velocidade;
				break;
			case "bxes":
				newX+=velocidade;
				newY-=velocidade;
				break;
			case "bxdi":
				newX+=velocidade;
				newY+=velocidade;
				break;	
			}	
			
			//Se a nova posição for fora do tabuleiro:
			if (newX<0 || newX>19 || newY<0 || newY>19) {
				tab.setProjetil(x, y, z, null);	
			//Se a nova posiçao estiver vazia o projetil se move para ela.
			}else if (tab.getProjetil(newX, newY, z) == null) {	
				jaAgiu=1; 
				tab.setProjetil(x, y, z, null);
				tab.setProjetil(newX, newY, z, this);
				x = newX;
				y = newY;
			
			}else{
				/*Se a nova posiçao não estiver vazia e contiver um projetil que não seja uma pedra, e esse projetil não tiver 
				 * efetuado sua ação ainda nesse tempo, o projetil não se move e é colocado em um vetor auxiliar para tratar 
				 * os confitos entre projeteis, após o tabuleiro inteiro ser percorrido.*/
				if (z==0 && tab.getProjetil(newX, newY, 0).getEmConflito()==0) {
					this.xConflito=newX;
					this.yConflito=newY;
					((PainelTabuleiro) tab).removeElemento(x,y,(PecaIcon)this);
					tab.adicionaConflito(this);
				//Se não estiver vazia, mas os outros requisitos acima não forem preenchidos, o projetil pode ir para a nova posição normalmente.
				}else {
					jaAgiu=1;	
					tab.setProjetil(x, y, z, null);
					tab.setProjetil(newX, newY, z, this);
					x = newX;
					y = newY;
				}
			}
		}freqM = (freqM + 1)%freqMov;	//freqM aumenta até ficar igual a freqMov e virar 0 novamente.
	}
	
	
	//Abaixo tem-se vários métodos para retornar e modificar os atributos privados do Projetil.
	
	@Override
	public int getDano() {
		return dano;
	}
	
	@Override
	public int getJaAgiu() {
		return jaAgiu;
	}
	
	@Override
	public void setJaAgiu(int j) {
		jaAgiu=j;
	}
	
	@Override
	public void setX(int x) {
		this.x=x;
	}
	
	@Override
	public void setY(int y) {
		this.y=y;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public int getEmConflito() {
		return emConflito;
	}
	
	@Override
	public void setEmConflito(int e) {
		emConflito=e;
	}
	
	@Override
	public int getxConflito() {
		return xConflito;
	}
	
	@Override
	public int getyConflito() {
		return yConflito;
	}
	
	public PecaIcon getPecaIcon() {
		return ((PecaIcon) this);
	}
	
	@Override
	public void movePrincesa(String direcao) {
		// TODO Auto-generated method stub
		
	}
	
}
