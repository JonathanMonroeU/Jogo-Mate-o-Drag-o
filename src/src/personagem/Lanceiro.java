package src.personagem;

import src.projetil.Lanca;
import src.tabuleiro.ITabuleiro;

public class Lanceiro extends Personagem{
	private static final long serialVersionUID = 779693959494716701L;
	public static String DIRETORIO = Lanceiro.class.getResource(".").getPath();
	public static int custo = 10;	//Pontos necessários para inserir o personagem em campo.
	
	public Lanceiro(int x, int y) {
		super(DIRETORIO+"assets/lanceiro.png",x,y);
		vida = 300;
		frequencia = 36; //a verificar
		movimento = 24;
		passo = 1;
	}
	
	//Instancia um projetil na posição do personagem e imediatamente ativa o método que move o projétil, depois atualizando a frequência de ataque, que diz que o personagem pode disparar quando for 0
	/*Como funciona o disparo direcionado: 
	 * É calculada a distância horizontal(hor) e vertical(ver) subtraindo a posição do alvo da posição do personagem que vai disparar, o que pode dar positivo ou negativo.
	 * Se o módulo da distância horizontal for menor (ou igual, para não ficarem casos sem ser abrangidos)que o da vertical, ele tem que atirar verticalmente, senão, horizontalmente.
	 * Se for verticalmente: Se ver for negativo, atira para cima, se for positivo, atira para baixo, depois o método testa se tem que atirar diretamente para cima/baixo ou em alguma das diagonais, 
	 * comparando ver-hor, que é a distância entre o ataque e o alvo quando está na linha do alvo, com hor que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo.
	 * Se for horizontalmente: Se hor for negativo, atira para esquerda, se for positivo, atira para direita, depois o método testa se tem que atirar diretamente para esquerda/direita ou em alguma das diagonais, 
	 * comparando hor-ver, que é a distância entre o ataque e o alvo quando está na coluna do alvo, com ver que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo.  */
	@Override
	public void disparaProjetil(ITabuleiro tab) {
		//Se for a vez do personagem instanciar um projétil:
		if (freqA==0 || freqA==12) {	
			int hor,ver; //distância horizontal e vertical ao dragao
		
			ver=(tab.getDragonPosition()[0])-x;
			hor=(tab.getDragonPosition()[1])-y;
			
			if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
				if (ver<0) {	//atira para cima
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor)))  //ver-hor é a distância entre o ataque e o dragão quando estiver na linha do dragão
						tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"ci","lanca-ci.png"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"cies","lanca-cies.png"));
						if (hor>0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"cidi","lanca-cidi.png"));
					}
				}if (ver>0) {	//atira para baixo
					if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) 
						tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"bx","lanca-bx.png"));
					else {
						if (hor<0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"bxes","lanca-bxes.png"));
						if (hor>0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"bxdi","lanca-bxdi.png"));
					}
				}
			}
			
			else {		//atira na horizontal
				if (hor<0) {	//atira para esquerda
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"es","lanca-es.png"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"cies","lanca-cies.png"));
						if (ver>0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"bxes","lanca-bxes.png"));
					}
				}if (hor>0) {	//atira para direita
					if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) 
						tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"di","lanca-di.png"));
					else {
						if (ver<0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"cidi","lanca-cidi.png"));
						if (ver>0)
							tab.putProjetil(x, y, 0, new Lanca(x, y, 0,"bxdi","lanca-bxdi.png"));
					}
				}
			}tab.getProjetil(x,y,0).move(tab);
		}freqA = (freqA + 1)%frequencia;//Atualiza freqA, quando freqA fica igual a frequência de disparos, ela volta a ser 0, e então no próximo tempo é a vez do personagem disparar novamente.
	}
}
