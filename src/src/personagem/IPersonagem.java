package src.personagem;

import src.projetil.IProjetil;
import src.tabuleiro.ITabuleiro;

public interface IPersonagem extends IMovimento{
	public void perdeVida(IProjetil Projetil);
	public void disparaProjetil(ITabuleiro tab);
	public int getVida();
	public boolean getJaAgiu();
	public void setJaAgiu(boolean j);
	public int getMovimento();
	public PecaIcon getPecaIcon();
}
