package src.projetil;

public class BolaDeFogo extends Projetil{
	private static final long serialVersionUID = -3677744954496112045L;
	public static String DIRETORIO =
		      BolaDeFogo.class.getResource(".").getPath();
	public BolaDeFogo(int x, int y, int z, String direcaoA, String imagem) {
		super(DIRETORIO+"assets/"+imagem, x, y, z);
		direcao=direcaoA;
		dano=100;
		freqMov=6;
		velocidade= 1; 
		jaAgiu=false;
		
	}
	
}
