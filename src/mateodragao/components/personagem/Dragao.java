package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeFogo;
import mateodragao.interfaces.ITabuleiro;

import java.lang.Math;

public class Dragao extends Personagem{
	private static final long serialVersionUID = 1529623392218092957L;
	public static String DIRETORIO =
		      Dragao.class.getResource(".").getPath();
	{frequencia = 1;
	movimento = 1;
	passo = 1;}
	
	public Dragao(int x, int y) {
		super(DIRETORIO+"yoshi.png",x,y);
		vida = 1000;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		
		int hor,ver, //distância horizontal e vertical do dragão ao personagem
			pX=-1,pY=-1;
		
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		for (int i=-4;i<=5;i++) {
			if (i!=0 && i!=1) {
				if (tab.getPeca(i,-4)!=null){
					pX=i;
					pY=-4;
					break;
				}if (tab.getPeca(i,4)!=null) {
					pX=i;
					pY=4;
					break;
				}if (tab.getPeca(-4,i)!=null) {
					pX=-4;
					pY=i;
					break;
				}if (tab.getPeca(4,i)!=null) {
					pX=4;
					pY=i;
					break;
				}	
			}
		}
		if (pX!=-1) {
			hor=pX-x;
			ver=pY-y;
			
			if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
				if (ver<0) {	//atira para cima
					if (Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
						tab.putProjetil(x, y-2, 0, new BolaDeFogo(x, y-2, 0,"ci"));
					else {
						if (hor<0)
							tab.putProjetil(x-2, y-2, 0, new BolaDeFogo(x-2, y-2, 0,"cies"));
						if (hor>0)
							tab.putProjetil(x+1, y-1, 0, new BolaDeFogo(x+1, y-1, 0,"cidi"));
					}
				}if (ver>0) {	//atira para baixo
					if (Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
						tab.putProjetil(x, y+1, 0, new BolaDeFogo(x, y+1, 0,"bx"));
					else {
						if (hor<0)
							tab.putProjetil(x-1, y+1, 0, new BolaDeFogo(x-1, y+1, 0,"bxes"));
						if (hor>0)
							tab.putProjetil(x+1, y+1, 0, new BolaDeFogo(x+1, y+1, 0,"bxdi"));
					}
				}
			}
			
			else {		//atira na horizontal
				if (hor<0) {	//atira para esquerda
					if (Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x-2, y, 0, new BolaDeFogo(x-2, y, 0,"es"));
					else {
						if (ver<0)
							tab.putProjetil(x-2, y-2, 0, new BolaDeFogo(x-2, y-2, 0,"cies"));
						if (ver>0)
							tab.putProjetil(x-1, y+1, 0, new BolaDeFogo(x-1, y+1, 0,"bxes"));
					}
				}if (hor>0) {	//atira para direita
					if (Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x+1, y, 0, new BolaDeFogo(x+1, y, 0,"di"));
					else {
						if (ver<0)
							tab.putProjetil(x+1, y-1, 0, new BolaDeFogo(x+1, y-1, 0,"cidi"));
						if (ver>0)
							tab.putProjetil(x+1, y+1, 0, new BolaDeFogo(x+1, y+1, 0,"bxdi"));
					}
				}
			}
		}
		else {
			int disparado=0;
			while (disparado==0) {
				int direcaoAlea = alea.nextInt(upperbound);
				switch (direcaoAlea) {
					case 0:
						if (x-2>0 && y-2>0){
							tab.putProjetil(x-2, y-2, 0, new BolaDeFogo(x-2, y-2, 0,"cies"));
							disparado=1;
						}break;
					case 1:
						if (x-1>0 && y-2>0){
							tab.putProjetil(x-1, y-2, 0, new BolaDeFogo(x-1, y-2, 0,"ci"));
							disparado=1;
						}break;
					case 2:
						if (y-2>0){
							tab.putProjetil(x, y-2, 0, new BolaDeFogo(x, y-2, 0,"ci"));
							disparado=1;
						}break;
					case 3:
						if (x+1<15 && y-2>0){
							tab.putProjetil(x+1, y-2, 0, new BolaDeFogo(x+1, y-2, 0,"cidi"));
							disparado=1;
						}break;
					case 4:
						if (x+1<15 && y-1>0){
							tab.putProjetil(x+1, y-1, 0, new BolaDeFogo(x+1, y-1, 0,"di"));
							break;
						}
					case 5:
						if (x+1<15){
							tab.putProjetil(x+1, y, 0, new BolaDeFogo(x+1, y, 0,"di"));
							disparado=1;
						}break;
					case 6:
						if (x+1<15 && y+1<15){
							tab.putProjetil(x+1, y+1, 0, new BolaDeFogo(x+1, y+1, 0,"bxdi"));
							disparado=1;
						}break;
					case 7:
						if (y+1<15){
							tab.putProjetil(x, y+1, 0, new BolaDeFogo(x, y+1, 0,"bx"));
							disparado=1;
						}break;
					case 8:
						if (x-1>0 && y+1<15){
							tab.putProjetil(x-1, y+1, 0, new BolaDeFogo(x-1, y+1, 0,"bx"));
							disparado=1;
						}break;
					case 9:
						if (x-2>0 && y+1<15){
							tab.putProjetil(x-2, y+1, 0, new BolaDeFogo(x-2, y+1, 0,"bxes"));
							disparado=1;
						}break;
					case 10:
						if (x-2>0){
							tab.putProjetil(x-2, y, 0, new BolaDeFogo(x-2, y, 0,"es"));
							disparado=1;
						}break;
					case 11:
						if (x-2>0 && y-1>0){
							tab.putProjetil(x-2, y-1, 0, new BolaDeFogo(x-2, y-1, 0,"es"));
							disparado=1;
						}break;
				}	
			}	
		}
	}
}