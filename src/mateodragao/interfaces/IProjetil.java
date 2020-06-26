package mateodragao.interfaces;

public interface IProjetil extends IMovimento{
	public int getDano();
	public int getEmConflito();
	public void setEmConflito(int e);
	public int getxConflito();
	public int getyConflito();
	public int getX();
	public int getY();
}
