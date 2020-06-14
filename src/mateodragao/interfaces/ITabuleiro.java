package mateodragao.interfaces;

public interface ITabuleiro {
	public void play();
	public void modificaTabuleiro();
	public void mostraTabuleiro();
	public IMovimento getPeca(int x, int y);
	public int[] getDragonPosition();
	public void putPeca(int x, int y, int tipo);
	public void removePeca (int x, int y);
	public void receiveData(IDataProvider dataProvider);
}
