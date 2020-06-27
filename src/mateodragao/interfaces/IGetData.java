package mateodragao.interfaces;

public interface IGetData {
	public boolean insertData();
	public void inserePersonagem(int comando, int x, int y);
	public void removePersonagem(int x, int y);
	public void setX(int x);
	public void setY(int y);
	public void setTipo(int tipo);
	public int[] getData();
}
