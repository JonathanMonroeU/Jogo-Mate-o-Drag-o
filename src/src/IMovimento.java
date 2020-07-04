package src;

import src.tabuleiro.ITabuleiro;

public interface IMovimento {
	public void move(ITabuleiro tab);
	public void movePrincesa(String direcao);
}
