package src.projetil;

public class BolaDeEnergia extends Projetil{
	private static final long serialVersionUID = 6286439068438256324L;
	public static String DIRETORIO =
		      BolaDeEnergia.class.getResource(".").getPath();
	public BolaDeEnergia(int x, int y, int z, String direcaoA, String imagem) {
		super(DIRETORIO+"assets/"+imagem, x, y, z);
		direcao=direcaoA;
		dano= 50;
		freqMov=6;
		velocidade= 1;
		jaAgiu=false;
	}
	
}
