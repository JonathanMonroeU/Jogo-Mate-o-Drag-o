package mateodragao.components.personagem;

import java.util.Random;

import mateodragao.PecaIcon;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;

public abstract class Personagem extends PecaIcon implements IPersonagem {
	private static final long serialVersionUID = 5890715371330885791L;
	protected static int custo, frequencia, movimento, passo;
	protected int x, y, vida, freqM, freqA,newX,newY;
	protected Random alea=new Random();
	
	public Personagem(String caminho, int x, int y) {
		super(caminho,x,y);
		this.x = x;
		this.y = y;
		freqM = 0;	
		freqA = 0;
	}
	
	@Override
	public void move(ITabuleiro tab) {	//encontra a nova posição para o personagem e o move para ela
		
		int tentativas=0;	//número de tentativas de se mover começa zerado, se passar de 30 provavelmente é porque está encurralado, e então deixa para tentar novamente na próxima rodada
		if (freqM == 0) {	//quando está zero, é a vez do personagem se mover
			newX = x;
			newY = y;
			
			while (tab.getPeca(newX, newY) != null && tentativas<=30) {	//fica no loop enquanto não acha uma nova posição vazia e ainda não passou do máximo de tentativas
				tentativas+=1;
				newX = x;
				newY = y;
				
				int addX = alea.nextInt(3)-1;	//valor entre -1,0 e 1 a ser adicionado e/ou subtrai aleatoriamente em x e y
				int addY = alea.nextInt(3)-1;
				
				if (vida<4) { //se não for o dragão, testa se a nova posição é coerente para o guerreiro 
					newX += passo*addX;
					newY += passo*addY;
					if(newX<0 || newX>15 || newY<0 || newY>15) {
						newX = x;
						newY = y;
						continue;
					}if (newX-tab.getDragonPosition()[0]<=-5||newX-tab.getDragonPosition()[0]>=4
					||newY-tab.getDragonPosition()[1]<=-5||newY-tab.getDragonPosition()[1]>=4) {	
						continue;
					}else {
						newX = x;
						newY = y;
						continue;
					}
				}	
				if (vida>4) { //se for o dragão, testa se a nova posição é coerente para o dragão
					int a=alea.nextInt(2);
					if (a==0)
						newX += passo*addX;
					else	
						newY += passo*addY;
					if(newX<1 || newX>15 || newY<1 || newY>15) {
						newX = x;
						newY = y;
						continue;
					}if (tab.getPeca(newX-1, newY)!=null && tab.getPeca(newX-1, newY)!=this ||
					 tab.getPeca(newX, newY-1)!=null && tab.getPeca(newX, newY-1)!=this ||
					 tab.getPeca(newX-1, newY-1)!=null && tab.getPeca(newX-1, newY-1)!=this) {
						newX = x;
						newY = y;
						continue;
					}
				}
			}
			
			if (tentativas<=30) {
				tab.setPeca(x, y, null);
				tab.setPeca(newX, newY, this);
				//((PainelTabuleiro) tab).setElemento(newX,newY,(PecaIcon) this);
				
				if (vida>4) {//se for o dragão
					tab.setDragonPosition(newX,newY);
					tab.setPeca(x-1, y, null);
					tab.setPeca(newX-1, newY, this);
					tab.setPeca(x, y-1, null);
					tab.setPeca(newX, newY-1, this);
					tab.setPeca(x-1, y-1, null);
					tab.setPeca(newX-1, newY-1, this);
				}
				x = newX;
				y = newY;
				
				//PARA VER AS POSIÇÕES 
				System.out.println("vida:"+vida+" x:"+x+" y"+y);
			}
		}if (tentativas<=30) 
			freqM = (freqM + 1)%movimento;
	}

	@Override
	public void perdeVida(IProjetil projetil, ITabuleiro tab) {
		//pega o dano do projetil e subtrai na vida do personagem que está naquela posição, se o ataque for inimigo
		if (vida>4 && projetil.getDano()>3)
			vida -= projetil.getDano();
		else if (vida<4 && projetil.getDano()<3)
			vida -= projetil.getDano();
	}

	@Override
	public abstract void disparaProjetil(ITabuleiro tab);
	
	@Override
	public int getVida() {
		return vida;
	}

	public static int getCusto(){
		return custo;
	}
}
