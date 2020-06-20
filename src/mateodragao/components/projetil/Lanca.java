package mateodragao.components.projetil;

public class Lanca extends Projetil{
	public Lanca(int x, int y, int z, String direcaoA) {
		super(x, y, z);
		direcao=direcaoA;
		dano= 25;
		velocidade= 2;
		emConflito=0;
	}
	
}
