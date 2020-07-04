package src.personagem;

import src.projetil.IProjetil;
import src.tabuleiro.ITabuleiro;
import src.IMovimento;
import src.PecaIcon;

public interface IPersonagem extends IMovimento{
	public void perdeVida(IProjetil Projetil, ITabuleiro tab);
	public void disparaProjetil(ITabuleiro tab);
	//static int getCusto();
	public int getVida();
	public int getJaAgiu();
	public void setJaAgiu(int j);
	public int getMovimento();
	public PecaIcon getPecaIcon();
}
