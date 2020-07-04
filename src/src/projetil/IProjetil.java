package src.projetil;

import src.IMovimento;
import src.PecaIcon;

public interface IProjetil extends IMovimento{
	public int getDano();
	public int getxConflito();
	public int getyConflito();
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public int getEmConflito();
	public void setEmConflito(int j);
	public int getJaAgiu();
	public void setJaAgiu(int j);
	public PecaIcon getPecaIcon();
}
