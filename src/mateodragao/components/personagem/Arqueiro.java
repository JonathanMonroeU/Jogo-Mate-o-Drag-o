package mateodragao.components.personagem;

import mateodragao.components.projetil.Flecha;
import mateodragao.interfaces.ITabuleiro;

public class Arqueiro extends Personagem{
	private static final long serialVersionUID = -7136572584786964361L;
	public static String DIRETORIO =
		      Arqueiro.class.getResource(".").getPath();
	public static int custo = 5;
	/*{custo=5;
	frequencia = 1;
	movimento = 1;
	passo = 2;
	}*/
	public Arqueiro(int x, int y) {
		super(DIRETORIO+"archer.png",x,y);
		vida = 1;
		frequencia = 1;
		movimento = 1;
		passo = 2;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		if (freqA==0) {
			System.out.println("dispara");
			int hor,ver; //distância horizontal e vertical ao dragao
			
			hor=(tab.getDragonPosition()[0])-x;
			ver=(tab.getDragonPosition()[1])-y;
			
			if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
				if (ver<0) {	//atira para cima
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor)))  //ver-hor é a distância entre meu ataque e o dragão quando estiver na linha do dragão
						tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"ci"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"cies"));
						if (hor>0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"cidi"));
					}
				}if (ver>0) {	//atira para baixo
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) 
						tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"bx"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"bxes"));
						if (hor>0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"bxdi"));
					}
				}
			}
			
			else {		//atira na horizontal
				if (hor<0) {	//atira para esquerda
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"es"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"cies"));
						if (ver>0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"bxes"));
					}
				}if (hor>0) {	//atira para direita
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"di"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"cidi"));
						if (ver>0)
							tab.putProjetil(x, y, 0, new Flecha(x, y, 0,"bxdi"));
					}
				}
			}tab.getProjetil(x,y,0).move(tab);
		}freqA = (freqA + 1)%frequencia;
	}
}
