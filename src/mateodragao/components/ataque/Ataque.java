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
	public int[] move(ITabuleiro tab) {
		int[] newPosition = new int[2];
		//parte do codigo q vai dar a nova posição
		
		
		return newPosition;
	}
}
