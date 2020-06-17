package mateodragao.components.personagem;

import mateodragao.components.ataque.Flecha;
import mateodragao.interfaces.ITabuleiro;

public class Arqueiro extends Personagem{
	public Arqueiro(int x, int y) {
		super(x,y);
		vida = 1;
		frequencia = 0;
		movimento = 0;
		passo = 2;
	}

	@Override
	public void disparaAtaque(ITabuleiro tab) {
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		
		//metodo para inserção no tabuleiro
		tab.putAtaque(x, y, new Flecha());
		
		
		
	}
}
