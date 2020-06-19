package mateodragao.components.projetil;

import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;

public class Projetil implements IProjetil{
	protected int x, y, dano, velocidade;
	
	public Projetil(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void move(ITabuleiro tab) {
		//parte do codigo q vai dar a nova posição
		//pensar sobre o enum de direção
		
		
		return;
	}
	
	@Override
	public int getDano() {
		return dano;
	}
}
