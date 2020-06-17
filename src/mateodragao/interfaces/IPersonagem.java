package mateodragao.interfaces;

public interface IPersonagem extends IMovimento{
	public void perdeVida(IAtaque ataque);
	public void disparaAtaque(ITabuleiro tab);
}
