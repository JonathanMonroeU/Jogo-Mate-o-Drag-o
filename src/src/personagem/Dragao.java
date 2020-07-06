package src.personagem;

import src.projetil.BolaDeFogo;
import src.tabuleiro.ITabuleiro;

import java.lang.Math;

public class Dragao extends Personagem{
	private static final long serialVersionUID = 1529623392218092957L;
	public static String DIRETORIO = Dragao.class.getResource(".").getPath();
	
	int especial;
	public Dragao(int x, int y) {
		super(DIRETORIO+"assets/dragao.png",x,y);
		vida = 1000;
		frequencia = 12;	
		movimento = 12;
		passo = 1;
		especial=9;
	}

	//Instancia um projetil na posição do personagem e imediatamente ativa o método que move o projétil, depois atualizando a frequência de ataque, que diz que o personagem pode disparar quando for 0
	/*OBS:O dragão tem 12 posições em sua volta, mas o tiro direcionado é calculado a partir da sua posição principal (que é guardada
	em dragonPosition), e atirado em uma das 8 direções possíveis do tabuleiro, para ataque especial e aleatório, todas as 12 posições são levadas em conta*/
	
	/* Disparo especial: a cada vez que a vida do dragão passa por uma centena, decrescendo, ele dispara um ataque e todas as direções
	 * Como funciona o disparo direcionado: se a frequência de ataque for 0, é a vez do personagem atacar. 
	 * É calculada a distância horizontal(hor) e vertical(ver) subtraindo a posição do alvo da posição do personagem que vai disparar, o que pode dar positivo ou negativo, no caso do dragão, ele procura alvos em uma distância de  até 5 casas para cada lado.
	 * Se o módulo da distância horizontal for menor (ou igual, para não ficarem casos sem ser abrangidos)que o da vertical, ele tem que atirar verticalmente, senão, horizontalmente.
	 * Se for verticalmente: Se ver for negativo, atira para cima, se for positivo, atira para baixo, depois o método testa se tem que atirar diretamente para cima/baixo ou em alguma das diagonais, 
	 * comparando ver-hor, que é a distância entre o ataque e o alvo quando está na linha do alvo, com hor que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo.
	 * Se for horizontalmente: Se hor for negativo, atira para esquerda, se for positivo, atira para direita, depois o método testa se tem que atirar diretamente para esquerda/direita ou em alguma das diagonais, 
	 * comparando hor-ver, que é a distância entre o ataque e o alvo quando está na coluna do alvo, com ver que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo. 
	 * 
	 *  Caso o dragão não encontre alvos no seu raio específico de tiro direcionado, ele atira aleatoriamente em uma das 12 posições+direções possíveis */
	@Override
	public void disparaProjetil(ITabuleiro tab) {
		//Se for a vez do personagem instanciar um projétil:
		if (freqA==0) {
			
			//DISPARO ESPECIAL
			if(vida/100<especial) {
				especial--;
				for(int i=0;i<12;i++) {
					switch (i) {
						case 0:
							if (x-2>0 && y-2>0 && tab.getProjetil(x-1, y-1, 0)==null){
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"cies", "boladefogo-cies.png"));
								tab.getProjetil(x-1, y-1, 0).move(tab);
							}
							break;
						case 1:
							if (x-2>0 && y-1>0 && tab.getProjetil(x-1, y-1, 0)==null){
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"ci", "boladefogo-ci.png"));
								tab.getProjetil(x-1, y-1, 0).move(tab);
							}
							break;
						case 2:
							if (x-2>0 && tab.getProjetil(x-1, y, 0)==null){
								tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"ci", "boladefogo-ci.png"));
								tab.getProjetil(x-1, y, 0).move(tab);
							}
							break;
						case 3:
							if (x-2>0 && y+1<19 && tab.getProjetil(x-1, y, 0)==null){
								tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"cidi", "boladefogo-cidi.png"));
								tab.getProjetil(x-1, y, 0).move(tab);
							}
							break;
						case 4:
							if (x-1>0 && y+1<19 && tab.getProjetil(x-1, y, 0)==null){
								tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"di", "boladefogo-di.png"));
								tab.getProjetil(x-1, y, 0).move(tab);
							}
							break;
						case 5:
							if (y+1<19 && tab.getProjetil(x, y, 0)==null){
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"di", "boladefogo-di.png"));
								tab.getProjetil(x, y, 0).move(tab);
							}
							break;
						case 6:
							if (x+1<19 && y+1<19 && tab.getProjetil(x, y, 0)==null){
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bxdi", "boladefogo-bxdi.png"));
								tab.getProjetil(x, y, 0).move(tab);
							}
							break;
						case 7:
							if (x+1<19 && tab.getProjetil(x, y, 0)==null){
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bx", "boladefogo-bx.png"));
								tab.getProjetil(x, y, 0).move(tab);
							}
							break;
						case 8:
							if (x+1<19 && y-1>0 && tab.getProjetil(x, y-1, 0)==null){
								tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"bx", "boladefogo-bx.png"));
								tab.getProjetil(x, y-1, 0).move(tab);
							}
							break;
						case 9:
							if (x+1<19 && y-2>0 && tab.getProjetil(x, y-1, 0)==null){
								tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"bxes","boladefogo-bxes.png"));
								tab.getProjetil(x, y-1, 0).move(tab);
							}
							break;
						case 10:
							if (y-2>0 && tab.getProjetil(x, y-1, 0)==null){
								tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"es", "boladefogo-es.png"));
								tab.getProjetil(x, y-1, 0).move(tab);
							}
							break;
						case 11:
							if (x-1>0 && y-2>0 && tab.getProjetil(x-1, y-1, 0)==null){
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"es", "boladefogo-es.png"));
								tab.getProjetil(x-1, y-1, 0).move(tab);
							}
							break;
					}
				}
			}	
			else {
				//DISPARO DIRECIONADO DO DRAGÃO:
				
				int hor,ver,encontrado=0, //Distância horizontal e vertical do dragão ao personagem.
					pX=100,pY=100; //posição
				
				//Para raios de distancia -6 a 5, nos eixos x e y, em relação ao dragão.
				for (int r=0;r<=4;r++) {
					if(encontrado==1)
						break;
					else {
						for (int i=-6+r;i<=5-r;i++) {
							if (x+i>=0 && x+i<=19 && y-6+r>=0){
								if (tab.getPeca(x+i,y-6+r, 0)!=null || tab.getPeca(x+i,y-6+r, 1)!=null){
									pX=x+i;
									pY=-y-6+r;
									encontrado=1;
									break;
								}
							}
							if (x+i>=0 && x+i<=19 && y+5-r<=19){
								if (tab.getPeca(x+i,y+5-r, 0)!=null || tab.getPeca(x+i,y+5-r, 1)!=null) {
									pX=x+i;
									pY=y+5-r;
									encontrado=1;
									break;
								}
							}	
							if (x-6+r>=0 && y+i>=0 && y+i<=19){
								if (tab.getPeca(x-6+r,y+i, 0)!=null || tab.getPeca(x-6+r,y+i, 1)!=null) {
									pX=x-6+r;
									pY=y+i;
									encontrado=1;
									break;
								}
							}if (x+5-r<=19 && y+i>=0 && y+i<=19){
								if (tab.getPeca(x+5-r,y+i, 0)!=null || tab.getPeca(x+5-r,y+i, 1)!=null) {
									pX=x+5-r;
									pY=y+i;
									encontrado=1;
									break;
								}
							}
						}
					}	
				}
				if (pX!=100) { //Quer dizer que ele encontrou um personagem nesse alcance.
					hor=pY-y;	
					ver=pX-x;
					int j;
					
					if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
						if (ver<0) {	//atira para cima
							if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) {
								j=alea.nextInt(2);
								if(tab.getProjetil(x-1, y-j,0)==null) {
									tab.putProjetil(x-1, y-j, 0, new BolaDeFogo(x-1, y-j, 0,"ci", "boladefogo-ci.png"));
									tab.getProjetil(x-1, y-j,0).move(tab);
								}
							}else {
								if (hor<0) {
									if(tab.getProjetil(x-1,y-1,0)==null) {
										tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0, "cies","boladefogo-cies.png"));
										tab.getProjetil(x-1,y-1,0).move(tab);
									}
								}if (hor>0) {
									if(tab.getProjetil(x,y,0)==null) {
										tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"cidi", "boladefogo-cidi.png"));
										tab.getProjetil(x,y,0).move(tab);
									}
								}
							}
						}if (ver>0) {	//atira para baixo
							if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) {
								j=alea.nextInt(2);
								if(tab.getProjetil(x,y-j,0)==null) { 
									tab.putProjetil(x, y-j, 0, new BolaDeFogo(x, y-j, 0,"bx", "boladefogo-bx.png"));
									tab.getProjetil(x,y-j,0).move(tab);
								}
							}else {
								if (hor<0) {
									if(tab.getProjetil(x,y,0)==null) { 
										tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0, "bxes","boladefogo-bxes.png"));
										tab.getProjetil(x,y,0).move(tab);
									}
								}if (hor>0) {
									if(tab.getProjetil(x,y,0)==null) {
										tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0, "bxdi","boladefogo-bxdi.png"));
										tab.getProjetil(x,y,0).move(tab);
									}
								}
							}
						}
					}
					
					else {		//atira na horizontal
						if (hor<0) {	//atira para esquerda
							if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) {
								j=alea.nextInt(2);
								if(tab.getProjetil(x-j, y-1, 0)==null) {
									tab.putProjetil(x-j, y-1, 0, new BolaDeFogo(x-j, y-1, 0,"es", "boladefogo-es.png"));
									tab.getProjetil(x-j, y-1, 0).move(tab);
								}
							}else {
								if (ver<0) {
									if(tab.getProjetil(x-1, y-1, 0)==null) {
											tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0, "cies", "boladefogo-cies.png"));
											tab.getProjetil(x-1, y-1, 0).move(tab);
									}
								}if (ver>0) {
									if(tab.getProjetil(x,y,0)==null) {
										tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0, "bxes", "boladefogo-bxes.png"));
										tab.getProjetil(x,y,0).move(tab);
									}
								}
							}
						}if (hor>0) {	//atira para direita
							if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) { 
								j=alea.nextInt(2);
								if(tab.getProjetil(x-j,y,0)==null) {
									tab.putProjetil(x-j, y, 0, new BolaDeFogo(x-j, y, 0,"di", "boladefogo-di.png"));
									tab.getProjetil(x-j,y,0).move(tab);
								}
							}else {
								if (ver<0) {
									if(tab.getProjetil(x,y,0)==null) {
										tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"cidi", "boladefogo-cidi.png"));
										tab.getProjetil(x,y,0).move(tab);
									}
								}if (ver>0) {
									if(tab.getProjetil(x,y,0)==null) {
										tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bxdi", "boladefogo-bxdi.png"));
										tab.getProjetil(x,y,0).move(tab);
									}
								}
							}
						}
					}
				}
				
				//DISPARO ALEATÓRIO:
				
				else {
					int disparado=0, tentativas=0;	
					while (disparado==0 && tentativas<30) {
						int direcaoAlea = alea.nextInt(12);
						switch (direcaoAlea) {
							case 0:
								if (x-2>0 && y-2>0 && tab.getProjetil(x-1, y-1, 0)==null){
									tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"cies", "boladefogo-cies.png"));
									tab.getProjetil(x-1, y-1, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 1:
								if (x-2>0 && y-1>0 && tab.getProjetil(x-1, y-1, 0)==null){
									tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"ci", "boladefogo-ci.png"));
									tab.getProjetil(x-1, y-1, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 2:
								if (x-2>0 && tab.getProjetil(x-1, y, 0)==null){
									tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"ci", "boladefogo-ci.png"));
									tab.getProjetil(x-1, y, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 3:
								if (x-2>0 && y+1<19 && tab.getProjetil(x-1, y, 0)==null){
									tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"cidi", "boladefogo-cidi.png"));
									tab.getProjetil(x-1, y, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 4:
								if (x-1>0 && y+1<19 && tab.getProjetil(x-1, y, 0)==null){
									tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"di", "boladefogo-di.png"));
									tab.getProjetil(x-1, y, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 5:
								if (y+1<19 && tab.getProjetil(x, y, 0)==null){
									tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"di", "boladefogo-di.png"));
									tab.getProjetil(x, y, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 6:
								if (x+1<19 && y+1<19 && tab.getProjetil(x, y, 0)==null){
									tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bxdi", "boladefogo-bxdi.png"));
									tab.getProjetil(x, y, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 7:
								if (x+1<19 && tab.getProjetil(x, y, 0)==null){
									tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bx", "boladefogo-bx.png"));
									tab.getProjetil(x, y, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 8:
								if (x+1<19 && y-1>0 && tab.getProjetil(x, y-1, 0)==null){
									tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"bx", "boladefogo-bx.png"));
									tab.getProjetil(x, y-1, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 9:
								if (x+1<19 && y-2>0 && tab.getProjetil(x, y-1, 0)==null){
									tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"bxes","boladefogo-bxes.png"));
									tab.getProjetil(x, y-1, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 10:
								if (y-2>0 && tab.getProjetil(x, y-1, 0)==null){
									tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"es", "boladefogo-es.png"));
									tab.getProjetil(x, y-1, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
							case 11:
								if (x-1>0 && y-2>0 && tab.getProjetil(x-1, y-1, 0)==null){
									tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"es", "boladefogo-es.png"));
									tab.getProjetil(x-1, y-1, 0).move(tab);
									disparado=1;
								}tentativas++;
								break;
						}
					}	
				}	
			}
		}freqA = (freqA + 1)%frequencia;	//Atualiza freqA, quando freqA fica igual a frequência de disparos, ela volta a ser 0, e então no próximo tempo é a vez do personagem disparar novamente.
	}
}