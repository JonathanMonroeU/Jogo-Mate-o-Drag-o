package mateodragao.components.projetil;

import mateodragao.PainelTabuleiro;
import mateodragao.PecaIcon;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;
public class Projetil extends PecaIcon implements IProjetil{
	private static final long serialVersionUID = -459082752300783478L;
	protected int x, y, z, 
					dano, 		//dano efetuado no inimigo pelo projetil
					velocidade, //número de casas que o projetil se move por tempo do jogo	
					xConflito, yConflito, //posição ocupada por outro projetil para o qual esse projetil quer se movimentar
					emConflito, //se o projetil estiver guardado no vetor emConflito
					jaAgiu; //jaAgiu indica se o projetil já fez sua ação naquele tempo do jogo; 
	protected String direcao; //direcao em que o projetil se move
	
	//inicia o projétil com o caminho para sua respectiva imagem e sua posição x, y e z
	public Projetil(String caminho, int x, int y, int z) {
		super(caminho,x,y);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//dependendo do atributo de direção do projétil, o método move ele em uma das 8 direções possíveis, na quantidade de casas que ele se move por tempo
	@Override
	public void move(ITabuleiro tab) {
		int newX=x;
		int newY=y;
	
		switch(direcao) {
		case "ci":
			newY-=velocidade;
			break;
		case "bx":
			newY+=velocidade;
			break;
		case "es":
			newX-=velocidade;
			break;
		case "di":
			newX+=velocidade;
			break;
		case "cies":
			newX-=velocidade;
			newY-=velocidade;
			break;
		case "cidi":
			newX+=velocidade;
			newY-=velocidade;
			break;
		case "bxes":
			newX-=velocidade;
			newY+=velocidade;
			break;
		case "bxdi":
			newX+=velocidade;
			newY+=velocidade;
			break;	
		}	
		
		//se a nova posição for fora do tabuleiro:
		if (newX<0 || newX>19 || newY<0 || newY>19) {
			tab.setProjetil(x, y, z, null);	System.out.println("saiu");
		//se a nova posiçao estiver vazia o projetil se move para ela
		}else if (tab.getProjetil(newX, newY, z) == null) {	
			jaAgiu=1;  System.out.println("moveproj dano:"+dano+" newX:"+newX+" newY:"+newY);
			tab.setProjetil(x, y, z, null);
			tab.setProjetil(newX, newY, z, this);
			x = newX;
			y = newY;
		
		}else{
			/*se a nova posiçao não estiver vazia e contiver um projetil que não seja uma pedra, e esse projetil não tiver 
			 * efetuado sua ação ainda nesse tempo, o projetil não se move e é colocado em um vetor auxiliar para tratar 
			 * os confitos entre projeteis após o tabuleiro inteiro ser percorrido*/
			if (z==0 && tab.getProjetil(newX, newY, 0).getEmConflito()==0) {
				System.out.println("entrou em conflito");
				this.xConflito=newX;
				this.yConflito=newY;
				((PainelTabuleiro) tab).removeElemento(x,y,(PecaIcon)this);
				tab.adicionaConflito(this);
			/*se não estiver vazia, mas os outros requisitos acima não forem preenchidos, o projetil pode ir para a nova posição normalmente*/
			}else {
				jaAgiu=1;	System.out.println("moveproj dano:"+dano+" newX:"+newX+" newY:"+newY);
				tab.setProjetil(x, y, z, null);
				tab.setProjetil(newX, newY, z, this);
				x = newX;
				y = newY;
			}
		}
	}
	
	
	//abaixo tem-se vários métodos para retornar e modificar os atributos privados do Projetil
	
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
	
	
}
