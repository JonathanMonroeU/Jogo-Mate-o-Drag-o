package mateodragao.components.personagem;

import mateodragao.components.projetil.Lanca;
import mateodragao.interfaces.ITabuleiro;

public class Lanceiro extends Personagem{
	public Lanceiro(int x, int y) {
		super(x,y);
		custo=10;
		vida = 3;
		frequencia = 3; //a verificar
		movimento = 2;
		passo = 1;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		
		//metodo para inserção no tabuleiro
		tab.putProjetil(x, y, new Lanca(newX,newY,direcaoA));
		
		
		
	}
}