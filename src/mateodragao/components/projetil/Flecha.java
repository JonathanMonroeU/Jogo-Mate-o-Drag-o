package mateodragao.components.projetil;

public class Flecha extends Projetil{
	private static final long serialVersionUID = -1175450512627267651L;
	public static String DIRETORIO =
		      Flecha.class.getResource(".").getPath();
	public Flecha(int x, int y, int z, String direcaoA, String imagem) {
		super(DIRETORIO+imagem, x, y, z);
		direcao=direcaoA;
		dano= 15;
		freqMov=4;
		velocidade= 1;
		jaAgiu=0;
	}
	
}
