package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeEnergia;
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
		int hor,ver; //distância horizontal e vertical ao dragao
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		hor=(tab.getDragonPosition()[0])-x;
		ver=(tab.getDragonPosition()[1])-y;
		
		if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
			if (ver<0) {	//atira para cima
				if(Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"ci"));
				else {
					if(hor<0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"cies"));
					if(hor>0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"cidi"));
				}
			}if (ver>0) {	//atira para baixo
				if(Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"bx"));
				else {
					if(hor<0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"bxes"));
					if(hor>0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"bxdi"));
				}
			}
		}
		
		else {		//atira na horizontal
			if (hor<0) {	//atira para esquerda
				if(Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"es"));
				else {
					if(ver<0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"cies"));
					if(ver>0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"bxes"));
				}
			}if (hor>0) {	//atira para direita
				if(Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"di"));
				else {
					if(ver<0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"cidi"));
					if(ver>0)
						tab.putProjetil(x, y, new BolaDeEnergia(newX,newY,"bxdi"));
				}
			}
		}
	}
}