package mateodragao.components.ataque;

import mateodragao.interfaces.IAtaque;
import mateodragao.interfaces.ITabuleiro;

public class Ataque implements IAtaque{
	protected int x, y, dano, velocidade;
	
	public Ataque(int x, int y) {
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
