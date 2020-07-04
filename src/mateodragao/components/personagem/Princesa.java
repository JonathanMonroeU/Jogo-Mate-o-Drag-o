package mateodragao.components.personagem;

import mateodragao.interfaces.ITabuleiro;

public class Princesa extends Personagem{
	private static final long serialVersionUID = 1529623392218092957L;
	public static String DIRETORIO =
		      Princesa.class.getResource(".").getPath();
	private ITabuleiro tab;
	
	public Princesa(int x, int y, ITabuleiro tab) {
		super(DIRETORIO+"princesa.png",x,y);
		vida = 300;
		this.tab=tab;
	}
	
	/*Método para mover a princesa, recebe uma direção de acordo com a tecla clicada pelo jogador.
	 * Primeiro verifica se a nova posição não sai do campo, depois se não há outro personagem na mesma coordenada x, y, e então, 
	 * se estiver tudo certo, ela é movida e tem suas posições atualizadas.*/
	@Override
	public void movePrincesa(String direcao) {
		switch (direcao) {
			case"up":
				if(x-1>=0) {
					if (tab.getPeca(x-1,y, 0)==null) {
						tab.setPersonagem(x, y, 1, null);
						tab.setPersonagem(x-1, y, 1, this);
						tab.setPrincesaPosition(x-1,y);
						x=x-1;
					}
				}break;
		
			case "left":
				if(y-1>=0) {
					if (tab.getPeca(x, y-1, 0)==null) {
					tab.setPersonagem(x, y, 1, null);
					tab.setPersonagem(x, y-1, 1, this);
					tab.setPrincesaPosition(x,y-1);
					y=y-1;
					}
				}break;
		
			case "right":
				if(y+1<=19) {
					if (tab.getPeca(x,y+1, 0)==null) {
						tab.setPersonagem(x, y, 1, null);
						tab.setPersonagem(x, y+1, 1, this);
						tab.setPrincesaPosition(x,y+1);
						y=y+1;
					}
				}break;
		
			case "down":
				if(x+1<=19) {
					if (tab.getPeca(x+1,y, 0)==null) {
						tab.setPersonagem(x, y,1, null);
						tab.setPersonagem(x+1, y,1, this);
						tab.setPrincesaPosition(x+1,y);
						x=x+1;
					}
				}break;
		}
	}
	
	@Override
	public void disparaProjetil(ITabuleiro tab) {	
	}

}