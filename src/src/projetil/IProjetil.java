package src.projetil;

import src.personagem.IMovimento;
import src.personagem.PecaIcon;

public interface IProjetil extends IMovimento{
	public int getDano();
	public int getxConflito();
	public int getyConflito();
	public int getx();
	public int gety();
	public void setX(int x);
	public void setY(int y);
	public boolean getEmConflito();
	public void setEmConflito(boolean j);
	public boolean getJaAgiu();
	public void setJaAgiu(boolean j);
	public PecaIcon getPecaIcon();
}
