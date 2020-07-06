package src.projetil;

public class Lanca extends Projetil{
	private static final long serialVersionUID = -6674306260323763964L;
	public static String DIRETORIO =
		      Lanca.class.getResource(".").getPath();
	public Lanca(int x, int y, int z, String direcaoA, String imagem) {
		super(DIRETORIO+"assets/"+imagem, x, y, z);
		direcao=direcaoA;
		dano= 25;
		freqMov=6;
		velocidade= 1;
		jaAgiu=false;
	}
	
}
