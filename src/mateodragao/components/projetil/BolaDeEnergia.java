package mateodragao.components.projetil;

public class BolaDeEnergia extends Projetil{
	public BolaDeEnergia(int x, int y, String direcaoA) {
		super(x,y);
		direcao=direcaoA;
		dano= 50;
		velocidade= 2;
	}
	
}
