package mateodragao.components.projetil;

import mateodragao.PecaIcon;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;
public class Projetil extends PecaIcon implements IProjetil{
	private static final long serialVersionUID = -459082752300783478L;
	protected int x, y, z, dano, velocidade, xConflito, yConflito, emConflito, jaAgiu; //jaAgiu indica se o projetil já fez sua ação naquele tempo do jogo; 
	protected String direcao;
	
	public Projetil(String caminho, int x, int y, int z) {
		super(caminho,x,y);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void move(ITabuleiro tab) {
		//parte do codigo q vai dar a nova posição

		int newX=x;
		int newY=y;
		
		switch(direcao) {
		case "cm":
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
		case "cmes":
			newX-=velocidade;
			newY-=velocidade;
			break;
		case "cmdi":
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
		}if (newX<0 || newX>15 || newY<0 || newY>15) 
			tab.setProjetil(x, y, z, null);
		else if (tab.getProjetil(newX, newY, z) == null) {	
			jaAgiu=1;  System.out.println("dano:"+dano+" newX:"+newX+" newY:"+y);
			tab.setProjetil(x, y, z, null);
			tab.setProjetil(newX, newY, z, this);
			x = newX;
			y = newY;
		}else{
			if (z==0 && tab.getProjetil(newX, newY, 0).getEmConflito()==0) {
				this.xConflito=newX;
				this.yConflito=newY;
				tab.adicionaConflito(this);
			}else {
				jaAgiu=1;	System.out.println("dano:"+dano+" newX:"+newX+" newY:"+y);
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
