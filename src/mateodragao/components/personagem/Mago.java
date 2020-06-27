package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeEnergia;
import mateodragao.components.projetil.BolaDeEnergia;
import mateodragao.interfaces.ITabuleiro;

public class Mago extends Personagem{
	private static final long serialVersionUID = 3986495568011382602L;
	public static String DIRETORIO =
		      Mago.class.getResource(".").getPath();
	public static int custo = 15;
	/*{custo=15;
	frequencia = 2;
	movimento = 1;
	passo = 1;}*/
	
	public Mago(int x, int y) {
		super(DIRETORIO+"yoshi.png",x,y);
		vida = 2;
		frequencia = 2;
		movimento = 1;
		passo = 1;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		if (freqA==0){
			int hor,ver; //distância horizontal e vertical ao dragao
			
			hor=(tab.getDragonPosition()[0])-x;
			ver=(tab.getDragonPosition()[1])-y;
			
			if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
				if (ver<0) {	//atira para cima
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor)))  //ver-hor é a distância entre meu ataque e o dragão quando estiver na linha do dragão
						tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"ci"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"cies"));
						if (hor>0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"cidi"));
					}
				}if (ver>0) {	//atira para baixo
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) 
						tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"bx"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"bxes"));
						if (hor>0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"bxdi"));
					}
				}
			}
			
			else {		//atira na horizontal
				if (hor<0) {	//atira para esquerda
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"es"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"cies"));
						if (ver>0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"bxes"));
					}
				}if (hor>0) {	//atira para direita
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"di"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"cidi"));
						if (ver>0)
							tab.putProjetil(x, y, 0, new BolaDeEnergia(x, y, 0,"bxdi"));
					}
				}
			}tab.getProjetil(x,y,0).move(tab);
		}freqA = (freqA + 1)%frequencia;
	}
}
