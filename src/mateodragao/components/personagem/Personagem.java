package mateodragao.components.personagem;

import java.util.Random;

import mateodragao.interfaces.IAtaque;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public abstract class Personagem implements IPersonagem {
	public static int custo;
	protected int x, y, vida, frequencia, movimento, passo, statusM, statusA;
	protected Random alea=new Random();
	protected int upperbound=2;
	
	public Personagem(int x, int y) {
		this.x = x;
		this.y = y;
		statusM = 0;
		statusA = 0;
	}
	
	@Override
	public void move(ITabuleiro tab) {
		//int newPosition[] = new int[2];
		//parte do codigo q vai dar a nova posição
		//sobre essa parte falta analisar algumas condicoes especificas de movimento
		if (statusM == 0) {
			int newX = x;
			int newY = y;
			while (tab.getPeca(newX, newY) != null) {
				int addX = alea.nextInt(upperbound)-1;
				int addY = alea.nextInt(upperbound)-1;
				newX += passo*addX;
				newY += passo*addY;
			}
			tab.setPeca(x, y, null);
			tab.setPeca(newX, newY, this);
			x = newX;
			y = newY;
			/*newPosition[0] = x;
			newPosition[1] = y;*/ //nao sera mais necessario
		}
		statusM = (statusM + 1)%movimento;
		return;
	}

	@Override
	public void perdeVida(IAtaque ataque) {
		//pegar dano do ataque e subtrair de vida
		vida -= ataque.getDano();
	}

	@Override
	public abstract void disparaAtaque(ITabuleiro tab);
		//esse daqui talvez seja mais complicado de fazer

	
}
