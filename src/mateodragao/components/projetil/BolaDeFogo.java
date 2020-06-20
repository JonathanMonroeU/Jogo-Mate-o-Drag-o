package mateodragao.components.projetil;

public class BolaDeFogo extends Projetil{
	public BolaDeFogo(int x, int y, int z,String direcaoA) {
		super(x, y, z);
		direcao=direcaoA;
		dano=1;
		velocidade= 1;
		emConflito=0;
		
	}
	
}
