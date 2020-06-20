package mateodragao.components.projetil;

public class Flecha extends Projetil{
	public Flecha(int x, int y, String direcaoA) {
		super(x,y);
		direcao=direcaoA;
		dano= 15;
		velocidade= 3;
	}
	
}
