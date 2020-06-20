package mateodragao.components.projetil;

import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;

public class Projetil implements IProjetil{
	protected int x, y, dano, velocidade; 
	protected String direcao;
	
	public Projetil(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void move(ITabuleiro tab) {
		//parte do codigo q vai dar a nova posição
		//pensar sobre o enum de direção
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
			tab.setProjetil(x, y, null);
		else if (tab.getProjetil(newX, newY) == null) {	
			tab.setProjetil(x, y, null);
			tab.setProjetil(newX, newY, this);
			x = newX;
			y = newY;
		}else{
			tab.adicionaConflito(x,y);
		}
	}
	
	@Override
	public int getDano() {
		return dano;
	}
}
