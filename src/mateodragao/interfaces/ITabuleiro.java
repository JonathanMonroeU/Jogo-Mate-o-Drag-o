package mateodragao.interfaces;

public interface ITabuleiro {
	public void play();
	public void modificaTabuleiro();
	public void mostraTabuleiro();
	public IMovimento getPeca(int x, int y);
	public void setPeca(int x, int y, IPersonagem peca);
	public int[] getDragonPosition();
	public void putPeca(int x, int y, int tipo);
	public void putProjetil(int x, int y, int z, IProjetil Projetil);
	public void removePeca (int x, int y);
	public void receiveData(IDataProvider dataProvider);
	public void setProjetil(int x, int y, int z, IProjetil Projetil);
	public IProjetil getProjetil(int x, int y, int z);
	public void adicionaConflito(int x, int y, int newX, int newY);
	public void setDragonPosition(int x, int y);
}
