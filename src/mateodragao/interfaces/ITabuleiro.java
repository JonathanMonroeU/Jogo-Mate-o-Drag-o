package mateodragao.interfaces;

import mateodragao.Metronomo;
import mateodragao.PainelTabuleiro;
import mateodragao.exceptions.SemPersonagem;

public interface ITabuleiro {
	public void play() throws SemPersonagem;
	public void modificaTabuleiro();
	public void mostraTabuleiro();
	public IMovimento getPeca(int x, int y, int z);
	public void setPeca(int x, int y, int z, IPersonagem peca);
	public int[] getDragonPosition();
	public void putPeca(int x, int y, int tipo);
	public void putProjetil(int x, int y, int z, IProjetil Projetil);
	public void removePeca (int x, int y, int z);
	public void receiveData(IDataProvider dataProvider);
	public void setProjetil(int x, int y, int z, IProjetil Projetil);
	public IProjetil getProjetil(int x, int y, int z);
	public void adicionaConflito(IProjetil projetil);
	public void resolveConflito(IProjetil projetil);
	public void setDragonPosition(int x, int y);
	public Metronomo getMetro();
	public PainelTabuleiro getPanel();
	public void setPrincesaPosition(int x, int y);
	public int[] getPrincesaPosition();
}
