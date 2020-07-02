package mateodragao.components.projetil;

public class BolaDeFogo extends Projetil{
	private static final long serialVersionUID = -3677744954496112045L;
	public static String DIRETORIO =
		      BolaDeFogo.class.getResource(".").getPath();
	public BolaDeFogo(int x, int y, int z, String direcaoA, String imagem) {
		super(DIRETORIO+imagem, x, y, z);
		direcao=direcaoA;
		dano=1;
		velocidade= 2; //estávamos pensando em 2
		jaAgiu=0;
		
	}
	
}
