package mateodragao.components.personagem;

import mateodragao.components.projetil.Pedra;
import mateodragao.interfaces.ITabuleiro;

public class Catapulta extends Personagem{
	public Catapulta(int x, int y) {
		super(x,y);
		custo=30;
		vida = 1;
		frequencia = 3;
		movimento = 0;
		passo = 0;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		
		
		
		
		//metodo para inserção no tabuleiro
		tab.putProjetil(x, y, new Pedra(newX,newY,direcaoA));
		
		
		
	}
}