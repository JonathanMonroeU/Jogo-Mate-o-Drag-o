package src.tabuleiro;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MeuKeyListener implements KeyListener {
	ITabuleiro tab;
	
	MeuKeyListener(ITabuleiro tab){
		this.tab=tab;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		if (tab.getPrincesaPosition()[0]!=-1) {  //-1 indica que a princesa está morta.
            if (event.getKeyCode()== KeyEvent.VK_UP) {
            	tab.getPeca(tab.getPrincesaPosition()[0],tab.getPrincesaPosition()[1],1).movePrincesa("up");
        	}
            if (event.getKeyCode()== KeyEvent.VK_LEFT) {
            	tab.getPeca(tab.getPrincesaPosition()[0],tab.getPrincesaPosition()[1],1).movePrincesa("left");
            }
            if (event.getKeyCode()== KeyEvent.VK_RIGHT) {
            	tab.getPeca(tab.getPrincesaPosition()[0],tab.getPrincesaPosition()[1],1).movePrincesa("right");
            }
            if (event.getKeyCode()== KeyEvent.VK_DOWN) {
            	tab.getPeca(tab.getPrincesaPosition()[0],tab.getPrincesaPosition()[1],1).movePrincesa("down");
            }
		}
    }
    @Override
    public void keyReleased(KeyEvent event) {
        // 
    }
    @Override
    public void keyTyped(KeyEvent event) {
        // 
    }
}
