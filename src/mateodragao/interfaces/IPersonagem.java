package mateodragao.interfaces;

public interface IPersonagem extends IMovimento{
	public void perdeVida(IMovimento ataque);
	public void disparaAtaque(ITabuleiro tab);
}
