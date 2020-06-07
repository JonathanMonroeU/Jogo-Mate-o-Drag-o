package mateodragao.interfaces;

public interface ITabuleiro {
	public void play();
	public void modificaTabuleiro();
	public void mostraTabuleiro();
	public IMovimento getPeca(int x, int y);
	public void putPeca(int x, int y, String tipo);
	public void removePeca (int x, int y);
	public void receiveData(IDataProvider dataProvider);
}
