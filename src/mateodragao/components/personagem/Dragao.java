package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeFogo;
import mateodragao.interfaces.ITabuleiro;

import java.lang.Math;

public class Dragao extends Personagem{
	{frequencia = 1;
	movimento = 1;
	passo = 1;}
	
	public Dragao(int x, int y) {
		super(x,y);
		vida = 1000;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		int hor,ver; //distância horizontal e vertical ao dragao
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		if
		
		
		
		
		
		
		
		
		
		
		
		
		hor=(tab.getDragonPosition()[0])-x;
		ver=(tab.getDragonPosition()[1])-y;
		
		if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
			if (ver<0) {	//atira para cima
				if(Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"ci"));
				else {
					if(hor<0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"cies"));
					if(hor>0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"cidi"));
				}
			}if (ver>0) {	//atira para baixo
				if(Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"bx"));
				else {
					if(hor<0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"bxes"));
					if(hor>0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"bxdi"));
				}
			}
		}
		
		else {		//atira na horizontal
			if (hor<0) {	//atira para esquerda
				if(Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"es"));
				else {
					if(ver<0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"cies"));
					if(ver>0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"bxes"));
				}
			}if (hor>0) {	//atira para direita
				if(Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"di"));
				else {
					if(ver<0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"cidi"));
					if(ver>0)
						tab.putProjetil(x, y, 0, new BolaDeFogo(newX,newY, 0,"bxdi"));
				}
			}
		}
	}
}