package mateodragao.components.projetil;

public class BolaDeEnergia extends Projetil{
	private static final long serialVersionUID = 6286439068438256324L;
	public static String DIRETORIO =
		      BolaDeEnergia.class.getResource(".").getPath();
	public BolaDeEnergia(int x, int y, int z, String direcaoA) {
		super(DIRETORIO+"yoshi.png", x, y, z);
		direcao=direcaoA;
		dano= 50;
		velocidade= 2;
		emConflito=0;
	}
	
}
