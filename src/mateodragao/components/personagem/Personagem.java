package mateodragao.components.personagem;

import java.util.Random;

import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public abstract class Personagem implements IPersonagem {
	protected static int custo, frequencia, movimento, passo;
	protected int x, y, vida, freqM, freqA,newX,newY;
	protected Random alea=new Random();
	protected int upperbound=3 ,upperbound2=8;
	
	public Personagem(int x, int y) {
		this.x = x;
		this.y = y;
		freqM = 0;
		freqA = 0;
	}
	
	@Override
	public void move(ITabuleiro tab) {
		//int newPosition[] = new int[2];
		//parte do codigo q vai dar a nova posição
		
		//OBS: testar se não tá grudado no dragao
		//OBS: se estiver total encurralado, melhor ver se ele já testou 
			//...posições demais, e se sim, deixar parado
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
				
				if(newX<0 || newX>15 || newY<0 || newY>15) {
					newX = x;
					newY = y;
					continue;
				}if (Math.abs(tab.dragonPosition[0] || || || ||)
			}
			if (tentativas<=30) {
				tab.setPeca(x, y, null);
				tab.setPeca(newX, newY, this);
				x = newX;
				y = newY;
				/*newPosition[0] = x;
				newPosition[1] = y;*/ //nao sera mais necessario
			}
		}if (tentativas<=30) 
			freqM = (freqM + 1)%movimento;
	}

	@Override
	public void perdeVida(IProjetil Projetil, ITabuleiro tab) {
		//pegar dano do Projetil e subtrair de vida
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
