package mateodragao.interfaces;

public interface IPersonagem extends IMovimento{
	public void perdeVida(IProjetil Projetil);
	public void disparaProjetil(ITabuleiro tab);
}
