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
	protected int upperbound=3 ,upperbound2=12;
	
	public Personagem(String caminho, int x, int y) {
		super(caminho,x,y);
		this.x = x;
		this.y = y;
		freqM = 0;
		freqA = 0;
	}
	
	@Override
	public void move(ITabuleiro tab) {
		//int newPosition[] = new int[2];
		//parte do codigo q vai dar a nova posição
		
		int tentativas=0;
		if (freqM == 0) {
			newX = x;
			newY = y;
			
			while (tab.getPeca(newX, newY) != null && tentativas<=30) {
				tentativas+=1;
				
				int addX = alea.nextInt(upperbound)-1;
				newX += passo*addX;
				
				int addY = alea.nextInt(upperbound)-1;
				newY += passo*addY;
				
				
				if (vida<4) { //se não for o dragão
					if(newX<0 || newX>15 || newY<0 || newY>15) {
						newX = x;
						newY = y;
						continue;
					}if (newX-tab.getDragonPosition()[0]<=-5||newX-tab.getDragonPosition()[0]>=4
					||newY-tab.getDragonPosition()[1]<=-5||newX-tab.getDragonPosition()[1]>=4) {	
						break;
					}else 
						newX = x;
						newY = y;
						continue;
				}	
				if (vida>4) { //se for o dragão
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
				x = newX;
				y = newY;
				/*newPosition[0] = x;
				newPosition[1] = y;*/ //nao sera mais necessario
				if (vida>4) //se for o dragão
					tab.setDragonPosition(x,y);
					/*tab.setPeca(x-1, y, null);
					tab.setPeca(newX-1, newY, this);
					tab.setPeca(x, y-1, null);
					tab.setPeca(newX, newY-1, this);
					tab.setPeca(x-1, y-1, null);
					tab.setPeca(newX-1, newY-1, this);	*/	
			}
		}if (tentativas<=30) 
			freqM = (freqM + 1)%movimento;
	}

	@Override
	public void perdeVida(IProjetil Projetil, ITabuleiro tab) {
		//pegar dano do Projetil e subtrair de vida
		if (vida>4 && Projetil.getDano()>3)
			vida -= Projetil.getDano();
		else if (vida<4 && Projetil.getDano()<3)
			vida -= Projetil.getDano();
	}

	@Override
	public abstract void disparaProjetil(ITabuleiro tab);
		//esse daqui talvez seja mais complicado de fazer
	

	public static int getCusto(){
		return custo;
	}
	@Override
	public int getVida() {
		return vida;
	}

	
}
