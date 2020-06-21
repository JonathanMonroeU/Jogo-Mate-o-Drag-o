package mateodragao.components.projetil;

public class Flecha extends Projetil{
	private static final long serialVersionUID = -1175450512627267651L;
	public static String DIRETORIO =
		      Flecha.class.getResource(".").getPath();
	public Flecha(int x, int y, int z, String direcaoA) {
		super(DIRETORIO+"yoshi.png", x, y, z);
		direcao=direcaoA;
		dano= 15;
		velocidade= 3;
		emConflito=0;
	}
	
}
