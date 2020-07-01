package mateodragao.components.projetil;

public class Lanca extends Projetil{
	private static final long serialVersionUID = -6674306260323763964L;
	public static String DIRETORIO =
		      Lanca.class.getResource(".").getPath();
	public Lanca(int x, int y, int z, String direcaoA, String imagem) {
		super(DIRETORIO+imagem, x, y, z);
		direcao=direcaoA;
		dano= 25;
		velocidade= 2;
		jaAgiu=0;
	}
	
}
