package mateodragao.components.projetil;

public class Pedra extends Projetil{
	public Pedra(int x, int y, int z, String direcaoA) {
		super(x, y, z);
		direcao=direcaoA;
		dano= 100;
		velocidade= 4;
		emConflito=0;
	}
	
	
}
