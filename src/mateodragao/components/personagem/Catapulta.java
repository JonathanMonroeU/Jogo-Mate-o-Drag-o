package mateodragao.components.personagem;

import mateodragao.components.projetil.Pedra;
import mateodragao.components.projetil.Pedra;
import mateodragao.interfaces.ITabuleiro;

public class Catapulta extends Personagem{
	private static final long serialVersionUID = 4060029049567589732L;
	public static String DIRETORIO =
		      Catapulta.class.getResource(".").getPath();
	public static int custo = 30;
	/*{custo=30;
	frequencia = 3;
	movimento = 0;
	passo = 0;}*/
	
	public Catapulta(int x, int y) {
		super(DIRETORIO+"catapulta.png",x,y);
		vida = 1;
		frequencia = 3;
		movimento = 0;
		passo = 0;
	}
	
	//Instancia um projetil na posição do personagem e imediatamente ativa o método que move o projétil, depois atualizando a frequência de ataque, que diz que o personagem pode disparar quando for 0
	/*Como funciona o disparo direcionado: se a frequência de ataque for 0, é a vez do personagem atacar. 
	 * É calculada a distância horizontal(hor) e vertical(ver) subtraindo a posição do alvo da posição do personagem que vai disparar, pode ser positiva ou negativa.
	 * Se o módulo da distância horizontal for menor (ou igual, para não ficarem casos sem ser abrangidos)que o da vertical, ele tem que atirar verticalmente, senão, horizontalmente.
	 * Se for verticalmente: Se ver for negativo, atira para cima, se for positivo, atira para baixo, depois o método testa se tem que atirar diretamente para cima/baixo ou em alguma das diagonais, 
	 * comparando ver-hor, que é a distância entre o ataque e o alvo quando está na linha do alvo, com hor que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo.
	 * Se for horizontalmente: Se hor for negativo, atira para esquerda, se for positivo, atira para direita, depois o método testa se tem que atirar diretamente para esquerda/direita ou em alguma das diagonais, 
	 * comparando hor-ver, que é a distância entre o ataque e o alvo quando está na coluna do alvo, com ver que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo.  */
	@Override
	public void disparaProjetil(ITabuleiro tab) {
		if (freqA==0) {
			int hor,ver; //distância horizontal e vertical ao dragao
		
			hor=(tab.getDragonPosition()[0])-x;
			ver=(tab.getDragonPosition()[1])-y;
			
			if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
				if (ver<0) {	//atira para cima
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor)))  //ver-hor é a distância entre o ataque e o dragão quando estiver na linha do dragão
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
