package mateodragao.components.projetil;

public class Pedra extends Projetil{
	public Pedra(int x, int y, String direcaoA) {
		super(x,y);
		direcao=direcaoA;
		dano= 100;
		velocidade= 4;
	}
	
	
}
