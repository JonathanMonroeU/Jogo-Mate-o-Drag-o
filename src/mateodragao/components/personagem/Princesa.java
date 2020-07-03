package mateodragao.components.personagem;

import mateodragao.components.Tabuleiro;
import mateodragao.interfaces.ITabuleiro;

public class Princesa extends Personagem{
	private static final long serialVersionUID = 1529623392218092957L;
	public static String DIRETORIO =
		      Princesa.class.getResource(".").getPath();
	Tabuleiro tab;
	public Princesa(int x, int y,Tabuleiro tab) {
		super(DIRETORIO+"princesa.png",x,y);
		vida = 300;
		this.tab=tab;
	}
	
	@Override
	public void movePrincesa(String direcao) {
		switch (direcao) {
			case"up":
				if(x-1>=0) {
					if (tab.getPeca(x-1,y, 0)==null) {
						tab.setPeca(x, y, 1, null);
						tab.setPeca(x-1, y, 1, this);
						tab.setPrincesaPosition(x-1,y);
						x=x-1;
						System.out.println(vida);
					}
				}break;
		
			case "left":
				if(y-1>=0) {
					if (tab.getPeca(x, y-1, 0)==null) {
					tab.setPeca(x, y, 1, null);
					tab.setPeca(x, y-1, 1, this);
					tab.setPrincesaPosition(x,y-1);
					y=y-1;
					System.out.println("left");
					}
				}break;
		
			case "right":
				if(y+1<=19) {
					if (tab.getPeca(x,y+1, 0)==null) {
						tab.setPeca(x, y, 1, null);
						tab.setPeca(x, y+1, 1, this);
						tab.setPrincesaPosition(x,y+1);
						y=y+1;
						System.out.println("right");
					}
				}break;
		
			case "down":
				if(x+1<=19) {
					if (tab.getPeca(x+1,y, 0)==null) {
						tab.setPeca(x, y,1, null);
						tab.setPeca(x+1, y,1, this);
						tab.setPrincesaPosition(x+1,y);
						x=x+1;
						System.out.println("down");
					}
				}break;
		}
	}
	
	@Override
	public void disparaProjetil(ITabuleiro tab) {	
	}

}