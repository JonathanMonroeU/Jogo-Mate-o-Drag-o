package mateodragao.components.personagem;

import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public class Personagem implements IPersonagem {
	public static int custo;
	protected int x, y, vida, frequencia, movimento;
	
	public Personagem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int[] move(ITabuleiro tab) {
		int newPosition[] = new int[2];
		//parte do codigo q vai dar a nova posição
		
		
		return newPosition;
	}

	@Override
	public void perdeVida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disparaAtaque() {
		// TODO Auto-generated method stub
		
	}
}
