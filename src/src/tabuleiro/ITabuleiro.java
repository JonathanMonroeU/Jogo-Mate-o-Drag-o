package src.tabuleiro;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.projetil.IProjetil;
import src.tabuleiro.PainelTabuleiro;
import src.IMovimento;
import src.dataprovider.IDataProvider;
import src.exceptions.SemPersonagem;
import src.personagem.IPersonagem;

public interface ITabuleiro {
	public void play() throws SemPersonagem;
	public void modificaTabuleiro();
	public IMovimento getPeca(int x, int y, int z);
	
	public void setPersonagem(int x, int y, int z, IPersonagem peca);
	public void putPersonagem(int x, int y, int tipo);
	
	public void putProjetil(int x, int y, int z, IProjetil Projetil);
	public void removePeca (int x, int y, int z);
	public void receiveData(IDataProvider dataProvider);
	public void setProjetil(int x, int y, int z, IProjetil Projetil);
	public IProjetil getProjetil(int x, int y, int z);
	public void adicionaConflito(IProjetil projetil);
	public void resolveConflito(IProjetil projetil);
	
	public int[] getDragonPosition();
	public void setDragonPosition(int x, int y);
	public int[] getPrincesaPosition();
	public void setPrincesaPosition(int x, int y);
	
	public PainelTabuleiro getPanel();
	public JLabel getVidaDragaoLabel();
	public JLabel getVidaPrincesaLabel();
	public JLabel getFinishLabel();
	public JButton getAgainButton();
}
