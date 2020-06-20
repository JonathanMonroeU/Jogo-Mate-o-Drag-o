package mateodragao.components.projetil;

public class Flecha extends Projetil{
	public Flecha(int x, int y, int z, String direcaoA) {
		super(x, y, z);
		direcao=direcaoA;
		dano= 15;
		velocidade= 3;
		emConflito=0;
	}
	
}
