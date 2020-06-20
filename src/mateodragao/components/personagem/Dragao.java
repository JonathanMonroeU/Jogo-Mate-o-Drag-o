package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeFogo;
import mateodragao.interfaces.ITabuleiro;

public class Dragao extends Personagem{
	
	public Dragao(int x, int y) {
		super(x,y);
		vida = 1000;
		frequencia = 1;
		movimento = 1;
		passo = 1;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		
		/*OBS: o dragão tem 12 posições ao redor, tem varias direções
		  para lançar os ataques e ainda tem a coisa do raio ao redor 
		 */
		
		//metodo para inserção no tabuleiro
		tab.putProjetil(x, y, new BolaDeFogo(newX,newY,direcaoA));
		
	}
}