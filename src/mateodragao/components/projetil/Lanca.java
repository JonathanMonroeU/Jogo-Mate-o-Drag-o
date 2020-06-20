package mateodragao.components.projetil;

public class Lanca extends Projetil{
	public Lanca(int x, int y, String direcaoA) {
		super(x,y);
		direcao=direcaoA;
		dano= 25;
		velocidade= 2;
	}
	
}
