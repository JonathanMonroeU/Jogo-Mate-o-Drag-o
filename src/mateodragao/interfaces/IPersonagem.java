package mateodragao.interfaces;

public interface IPersonagem extends IMovimento{
	public void perdeVida(IProjetil Projetil, ITabuleiro tab);
	public void disparaProjetil(ITabuleiro tab);
	//static int getCusto();
	public int getVida();
}
