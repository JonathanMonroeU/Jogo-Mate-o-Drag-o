package mateodragao.components.projetil;

public class BolaDeEnergia extends Projetil{
	public BolaDeEnergia(int x, int y, int z, String direcaoA) {
		super(x, y, z);
		direcao=direcaoA;
		dano= 50;
		velocidade= 2;
		emConflito=0;
	}
	
}
