package mateodragao.components.projetil;

public class BolaDeFogo extends Projetil{
	public BolaDeFogo(int x, int y,String direcaoA) {
		super(x,y);
		direcao=direcaoA;
		dano=1;
		velocidade= 1;
		
	}
	
}
