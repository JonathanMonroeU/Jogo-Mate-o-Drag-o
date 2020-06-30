package mateodragao.components.projetil;

public class Pedra extends Projetil{
	private static final long serialVersionUID = -618057584821850699L;
	public static String DIRETORIO =
		      Pedra.class.getResource(".").getPath();
	public Pedra(int x, int y, int z, String direcaoA) {
		super(DIRETORIO+"pedra.png", x, y, z);
		direcao=direcaoA;
		dano= 100;
		velocidade= 4;
		jaAgiu=2;  //nunca entra em conflito
	}
	
	
}
