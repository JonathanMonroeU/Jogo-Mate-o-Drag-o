package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeEnergia;
import mateodragao.interfaces.ITabuleiro;

public class Mago extends Personagem{
	public Mago(int x, int y) {
		super(x,y);
		custo=15;
		vida = 2;
		frequencia = 2;
		movimento = 1;
		passo = 1;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		//int newPosition[] = new int[2];
		//int newPosition[] = new int[2];
		//parte do codigo q vai dar a nova posição
		//sobre essa parte falta analisar algumas condicoes especificas de movimento
		if (freqA == 0) {
			
		}
		freqA = (freqA + 1)%frequencia;
		//metodo para inserção no tabuleiro
		tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,direcaoA));
		
		
		
	}
}