package mateodragao.components.personagem;

import mateodragao.components.projetil.Pedra;
import mateodragao.components.projetil.Pedra;
import mateodragao.interfaces.ITabuleiro;

public class Catapulta extends Personagem{
	private static final long serialVersionUID = 4060029049567589732L;
	public static String DIRETORIO =
		      Catapulta.class.getResource(".").getPath();
	{custo=30;
	frequencia = 3;
	movimento = 0;
	passo = 0;}
	
	public Catapulta(int x, int y) {
		super(DIRETORIO+"yoshi.png",x,y);
		vida = 1;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		if (freqA==0) {
			int hor,ver; //distância horizontal e vertical ao dragao
		
			hor=(tab.getDragonPosition()[0])-x;
			ver=(tab.getDragonPosition()[1])-y;
			
			if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
				if (ver<0) {	//atira para cima
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor)))  //ver-hor é a distância entre meu ataque e o dragão quando estiver na linha do dragão
						tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"ci"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"cies"));
						if (hor>0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"cidi"));
					}
				}if (ver>0) {	//atira para baixo
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) 
						tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"bx"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"bxes"));
						if (hor>0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"bxdi"));
					}
				}
			}
			
			else {		//atira na horizontal
				if (hor<0) {	//atira para esquerda
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"es"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"cies"));
						if (ver>0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"bxes"));
					}
				}if (hor>0) {	//atira para direita
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"di"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"cidi"));
						if (ver>0)
							tab.putProjetil(x, y, 1, new Pedra(x, y, 1,"bxdi"));
					}
				}
			}tab.getProjetil(x,y,1).move(tab);
		}freqA = (freqA + 1)%frequencia;
	}
}
