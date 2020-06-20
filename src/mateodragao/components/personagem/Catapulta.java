package mateodragao.components.personagem;

import mateodragao.components.projetil.Pedra;
import mateodragao.interfaces.ITabuleiro;

import java.lang.Math;

public class Catapulta extends Personagem{
	{custo=30;
	frequencia = 3;
	movimento = 0;
	passo = 0;}
	
	public Catapulta(int x, int y) {
		super(x,y);
		vida = 1;
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
					tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"ci"));
				else {
					if(hor<0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"cies"));
					if(hor>0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"cidi"));
				}
			}if (ver>0) {	//atira para baixo
				if(Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"bx"));
				else {
					if(hor<0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"bxes"));
					if(hor>0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"bxdi"));
				}
			}
		}
		
		else {		//atira na horizontal
			if (hor<0) {	//atira para esquerda
				if(Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"es"));
				else {
					if(ver<0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"cies"));
					if(ver>0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"bxes"));
				}
			}if (hor>0) {	//atira para direita
				if(Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"di"));
				else {
					if(ver<0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"cidi"));
					if(ver>0)
						tab.putProjetil(x, y, 1, new Pedra(newX,newY, 1,"bxdi"));
				}
			}
		}
	}
}