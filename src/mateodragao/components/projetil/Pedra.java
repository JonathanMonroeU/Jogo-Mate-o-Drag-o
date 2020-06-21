package mateodragao.components.projetil;

public class Pedra extends Projetil{
	private static final long serialVersionUID = -618057584821850699L;
	public static String DIRETORIO =
		      Pedra.class.getResource(".").getPath();
	public Pedra(int x, int y, int z, String direcaoA) {
		super(DIRETORIO+"yoshi.png", x, y, z);
		direcao=direcaoA;
		dano= 100;
		velocidade= 4;
		emConflito=0;
	}
	
	
}
