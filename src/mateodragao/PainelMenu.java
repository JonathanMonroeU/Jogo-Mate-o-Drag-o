package mateodragao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import mateodragao.exceptions.AdicaoInvalida;
import mateodragao.exceptions.RemocaoInvalida;
import mateodragao.exceptions.SemPersonagem;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.IMenu;
import mateodragao.interfaces.ITabuleiro;

public class PainelMenu extends JPanel implements IMenu, ActionListener{
	private static final long serialVersionUID = 6299309752925290728L;
	private JPanel painelInicial, painelAdicao, painelRemocao, painelTextA, painelTextR, painelSoldado, painelPontos, painelVidaDragao, painelVidaPrincesa, painelAviso;
	private JButton addPersonagem, remPersonagem, iniciar, adicionar, remover;
	private JButton arqueiro, lanceiro, mago, catapulta;
	private JTextField textXA, textYA, textXR, textYR;
	private JLabel pontos, arqueiroInfo, lanceiroInfo, magoInfo, catapultaInfo, aviso;
	private ITabuleiro tab;
	private IDataProvider data;
	private int comando, x, y;
	
	public PainelMenu(ITabuleiro tab, IDataProvider data) {
		this.tab = tab;
		this.data = data;
		System.out.println(data.getPontos());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(360,1000));
		JLabel titulo = new JLabel("Mate o Dragão");
		titulo.setFont(new Font("Arial", Font.BOLD, 35));
		titulo.setForeground(Color.lightGray);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(new java.awt.Color(175, 0 , 0));		//cor
		
		add(Box.createRigidArea(new Dimension(0,10)));
		add(titulo);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		addPersonagem = new JButton("Adicionar Personagem");
		remPersonagem = new JButton("Remover Personagem");
		iniciar = new JButton("Iniciar Jogo");
		
		addPersonagem.setAlignmentX(CENTER_ALIGNMENT);
		remPersonagem.setAlignmentX(CENTER_ALIGNMENT);
		iniciar.setAlignmentX(CENTER_ALIGNMENT);
		
		addPersonagem.addActionListener(this);
		remPersonagem.addActionListener(this);
		iniciar.addActionListener(this);
		
		textXA = new JTextField();
		textYA = new JTextField();
		textXR = new JTextField();
		textYR = new JTextField();
		
		textXA.setPreferredSize(new Dimension(40,20));
		textYA.setPreferredSize(new Dimension(40,20));
		textXR.setPreferredSize(new Dimension(40,20));
		textYR.setPreferredSize(new Dimension(40,20));
		
		adicionar = new JButton("Adicionar");
		adicionar.addActionListener(this);
		remover = new JButton("Remover");
		remover.addActionListener(this);
		
		
		
		
		painelTextA = new JPanel();
		painelTextA.setLayout(new FlowLayout());
		painelTextA.setMaximumSize(new Dimension(300,300));
		painelTextA.setAlignmentX(CENTER_ALIGNMENT);
		JLabel horizontal=new JLabel("Horizontal:");
		horizontal.setForeground(Color.WHITE);
		painelTextA.add(Box.createRigidArea(new Dimension(0,40)));
		painelTextA.add(horizontal);
		painelTextA.add(textYA);
		painelTextA.add(Box.createRigidArea(new Dimension(10,0)));
		JLabel vertical=new JLabel("Vertical:");
		vertical.setForeground(Color.WHITE);
		painelTextA.add(vertical);
		painelTextA.add(textXA);
		painelTextA.setBackground(new java.awt.Color(175, 0 , 0));		//cor
		painelTextA.add(adicionar);
		
		painelTextR = new JPanel();
		painelTextR.setLayout(new FlowLayout());
		painelTextR.setMaximumSize(new Dimension(300,300));
		painelTextR.setAlignmentX(CENTER_ALIGNMENT);
		JLabel horizontalR=new JLabel("Horizontal:");
		horizontalR.setForeground(Color.WHITE);
		painelTextR.add(Box.createRigidArea(new Dimension(0,40)));
		painelTextR.add(horizontalR);
		painelTextR.add(textYR);
		painelTextR.add(Box.createRigidArea(new Dimension(10,0)));
		JLabel verticalR=new JLabel("Vertical:");
		verticalR.setForeground(Color.WHITE);
		painelTextR.add(verticalR);
		painelTextR.add(textXR);
		painelTextR.setBackground(new java.awt.Color(175, 0 , 0));		//cor
		painelTextR.add(remover);
		
		arqueiro = new JButton("Adicionar Arqueiro");
		arqueiro.setPreferredSize(new Dimension(200,25));
		lanceiro = new JButton("Adicionar Lanceiro");
		lanceiro.setPreferredSize(new Dimension(200,25));
		mago = new JButton("Adicionar Mago");
		mago.setPreferredSize(new Dimension(200,25));
		catapulta = new JButton("Adicionar Catapulta");
		catapulta.setPreferredSize(new Dimension(200,25));
		
		arqueiro.addActionListener(this);
		lanceiro.addActionListener(this);
		mago.addActionListener(this);
		catapulta.addActionListener(this);
		
		Font fonte = new Font("SansSerif", Font.PLAIN, 14);
		arqueiroInfo = new JLabel("<html>"
			+"<b>--ARQUEIRO</b>"+"<br/>"
			+"<b>Custo:</b>"+" 5 pontos."+"<br/>"
			+"<b>Resistência: </b>"+" 1 bola de fogo."+"<br/>"
			+"<b>Movimento: </b>"+" 6 passos."+"<br/>"
			+"<b>Frequência de disparos: </b>"+" 12 passos."+"<br/>"
			+"<b>Movimento do projétil: </b>"+" 4 passos."+"<br/>"
			+"<b>Dano do projétil:</b>"+" 15 pontos."+"</html>");
		arqueiroInfo.setPreferredSize(new Dimension(300,155));
		arqueiroInfo.setFont(fonte);
		arqueiroInfo.setForeground(Color.WHITE);
		
		lanceiroInfo = new JLabel("<html>"
			+"<b>--LANCEIRO</b>"+"<br/>"
			+"<b>Custo:</b>"+" 10 pontos."+"<br/>"
			+"<b>Resistência: </b>"+" 3 bolas de fogo."+"<br/>"
			+"<b>Movimento: </b>"+" 12 passos."+"<br/>"
			+"<b>Frequência de disparos: </b>"+" De 12 em 12 passos, 2 vezes sim, uma vez não."+"<br/>"
			+"<b>Movimento do projétil: </b>"+" 6 passos."+"<br/>"
			+"<b>Dano do projétil:</b>"+" 25 pontos."+"</html>");
		lanceiroInfo.setPreferredSize(new Dimension(300,155));
		lanceiroInfo.setFont(fonte);
		lanceiroInfo.setForeground(Color.WHITE);
		
		magoInfo = new JLabel("<html>"
			+"<b>--MAGO</b>"+"<br/>"
			+"<b>Custo:</b>"+" 15 pontos."+"<br/>"
			+"<b>Resistência: </b>"+" 2 bolas de fogo."+"<br/>"
			+"<b>Movimento: </b>"+" 12 passos."+"<br/>"
			+"<b>Frequência de disparos: </b>"+" 24 passos."+"<br/>"
			+"<b>Movimento do projétil: </b>"+" 6 passos."+"<br/>"
			+"<b>Dano do projétil:</b>"+" 50 pontos."+"</html>");
		magoInfo.setPreferredSize(new Dimension(300,155));
		magoInfo.setFont(fonte);
		magoInfo.setForeground(Color.WHITE);
		
		catapultaInfo = new JLabel("<html>"
			+"<b>--CATAPULTA</b>"+"<br/>"
			+"<b>Custo:</b>"+" 30 pontos."+"<br/>"
			+"<b>Resistência: </b>"+" 1 bola de fogo."+"<br/>"
			+"<b>Movimento: </b>"+" Não se move."+"<br/>"
			+"<b>Frequência de disparos: </b>"+" 36 passos."+"<br/>"
			+"<b>Movimento do projétil: </b>"+" 3 passos."+"<br/>"
			+"<b>Dano do projétil:</b>"+" 100 pontos."+"</html>");
		catapultaInfo.setPreferredSize(new Dimension(300,155));
		catapultaInfo.setFont(fonte);
		catapultaInfo.setForeground(Color.WHITE);
		
		painelSoldado = new JPanel();
		painelSoldado.setLayout(new FlowLayout());
		painelSoldado.setAlignmentX(CENTER_ALIGNMENT);
		painelSoldado.setPreferredSize(new Dimension(300,300));
		painelSoldado.add(arqueiroInfo);
		painelSoldado.add(arqueiro);
		painelSoldado.add(lanceiroInfo);
		painelSoldado.add(lanceiro);
		painelSoldado.add(magoInfo);
		painelSoldado.add(mago);
		painelSoldado.add(catapultaInfo);
		painelSoldado.add(catapulta);
		painelSoldado.setBackground(new java.awt.Color(175, 0 , 0));
		
		painelPontos = new JPanel();
		painelPontos.setPreferredSize(new Dimension(30,70));
		painelPontos.setLayout(new BoxLayout(painelPontos, BoxLayout.Y_AXIS));
		painelPontos.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel titulo5 = new JLabel("  PONTOS  ");
		titulo5.setAlignmentX(CENTER_ALIGNMENT);
		titulo5.setForeground(Color.blue);
		pontos = new JLabel(Integer.toString(data.getPontos()));
		pontos.setAlignmentX(CENTER_ALIGNMENT);
		pontos.setHorizontalAlignment(0);
		pontos.setMaximumSize(new Dimension(50,30));
		pontos.setBorder(BorderFactory.createLineBorder(Color.black));
		painelPontos.add(Box.createRigidArea(new Dimension(0,10)));
		painelPontos.add(titulo5);
		painelPontos.add(pontos);
		painelPontos.add(Box.createRigidArea(new Dimension(0,10)));
		add(painelPontos);
		
		painelInicial = new JPanel();
		painelInicial.setLayout(new BoxLayout(painelInicial, BoxLayout.Y_AXIS));
		painelInicial.setPreferredSize(new Dimension(300,400));
		JLabel titulo2 = new JLabel("Adicione personagens e comece o jogo!");
		titulo2.setAlignmentX(CENTER_ALIGNMENT);
		titulo2.setForeground(Color.white);
		painelInicial.add(Box.createRigidArea(new Dimension(0,10)));
		painelInicial.add(titulo2);
		painelInicial.add(Box.createRigidArea(new Dimension(0,10)));
		painelInicial.add(addPersonagem);
		painelInicial.add(remPersonagem);
		painelInicial.add(iniciar);
		painelInicial.add(Box.createRigidArea(new Dimension(0,40)));
		painelInicial.setAlignmentX(CENTER_ALIGNMENT);
		painelInicial.setBackground(new java.awt.Color(175, 0 , 0));
		JLabel texto = new JLabel("<html>"
			+"	O dragão atacou o reino  repentinamente e a princesa"+"<br/>"
			+"está bem no meio do ataque, reúna os"+"<br/>"
			+"soldados e mate-o antes que o pior aconteça."+"</html>");
		texto.setPreferredSize(new Dimension(300,155));
		texto.setFont(new Font("SansSerif", Font.BOLD, 16));
		texto.setForeground(Color.WHITE);
		texto.setAlignmentX(CENTER_ALIGNMENT);
		painelInicial.add(texto);
		painelInicial.add(Box.createRigidArea(new Dimension(0,10)));
		JLabel instrucoesTitulo = new JLabel("Instruções:");
		instrucoesTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		instrucoesTitulo.setForeground(Color.white);
		
		instrucoesTitulo.setAlignmentX(CENTER_ALIGNMENT);
		painelInicial.add(instrucoesTitulo);
		JLabel instrucoes = new JLabel("<html>"
			+"O objetivo do jogo é matar o dragão,"+"<br/>"
			+"para isso, escolha como quer posicionar os"+"<br/>"
			+"soldados que adicionar em campo. Após"+"<br/>"
			+"iniciar o jogo, controle a princesa "+"<br/>"
			+"usando o direcional, de forma a evitar que seja"+"<br/>"
			+"atingida por ataques amigos e inimigos."+"<br/>"
			+"Você perde o jogo se a princesa ou todos"+"<br/>"
			+"os soldados morrerem, e vence se matar"+"<br/>"
			+"o dragão antes disso."+"<br/>"
			+"<b>--ATRIBUTOS DOS SOLDADOS:</b>"+"<br/>"
			+"<b>Custo:</b>"+"Pontos necessários para inserí-lo em campo."+"<br/>"
			+"<b>Resistência: </b>"+"Quantas bolas de fogo são necessárias para matá-lo."+"<br/>"
			+"<b>Movimento: </b>"+" A cada quantos passos do jogo ele se movimenta."+"<br/>"
			+"<b>Frequência de disparos: </b>"+"A cada quantos passos é feito um disparo."+"<br/>"
			+"<b>Movimento do projétil: </b>"+"A cada quantos passos o projétil se movimenta."+"<br/>"
			+"<b>Dano do projétil:</b>"+"Quantos pontos de vida subtrai do inimigo ou da princesa."+"</html>");
		instrucoes.setPreferredSize(new Dimension(300,155));
		instrucoes.setFont(new Font("SansSerif", Font.PLAIN, 14));
		instrucoes.setForeground(Color.WHITE);
		instrucoes.setAlignmentX(CENTER_ALIGNMENT);
		painelInicial.add(instrucoes);
		add(painelInicial);
		
		painelAdicao = new JPanel();
		painelAdicao.setPreferredSize(new Dimension(300,300));
		painelAdicao.setLayout(new BoxLayout(painelAdicao, BoxLayout.Y_AXIS));
		/*JLabel titulo3 = new JLabel("TELA DE ADICAO");
		titulo3.setAlignmentX(CENTER_ALIGNMENT);*/
		painelAdicao.setAlignmentX(CENTER_ALIGNMENT);
		//painelAdicao.add(titulo3);
		painelAdicao.add(painelSoldado);
		painelAdicao.add(painelTextA);
		add(painelAdicao);
		painelAdicao.setVisible(false);
		painelTextA.setVisible(false);
		
		painelRemocao = new JPanel();
		painelRemocao.setLayout(new BoxLayout(painelRemocao, BoxLayout.Y_AXIS));
		/*JLabel titulo4 = new JLabel("TELA DE REMOCAO");
		titulo4.setAlignmentX(CENTER_ALIGNMENT);*/
		painelRemocao.setAlignmentX(CENTER_ALIGNMENT);
		//painelRemocao.add(titulo4);
		painelRemocao.add(painelTextR);
		add(painelRemocao);
		painelRemocao.setVisible(false);
		
		painelAviso = new JPanel();
		painelAviso.setLayout(new BoxLayout(painelAviso, BoxLayout.Y_AXIS));
		painelAviso.setPreferredSize(new Dimension(300,50));
		JLabel titulo7 = new JLabel("ATENÇÃO");
		titulo7.setAlignmentX(CENTER_ALIGNMENT);
		aviso = new JLabel();
		aviso.setAlignmentX(CENTER_ALIGNMENT);
		aviso.setHorizontalAlignment(0);
		aviso.setMaximumSize(new Dimension(300,50));
		aviso.setForeground(Color.RED);
		painelAviso.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		painelAviso.setAlignmentX(CENTER_ALIGNMENT);
		painelAviso.add(Box.createRigidArea(new Dimension(0,10)));
		painelAviso.add(titulo7);
		painelAviso.add(aviso);
		painelAviso.add(Box.createRigidArea(new Dimension(0,10)));
		add(painelAviso);
		//add(Box.createRigidArea(new Dimension(0,10)));
		painelAviso.setVisible(false);
		
		painelVidaDragao = new JPanel();
		painelVidaDragao.setLayout(new BoxLayout(painelVidaDragao, BoxLayout.Y_AXIS));
		painelVidaDragao.setPreferredSize(new Dimension(30,70));
		painelVidaDragao.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel titulo6 = new JLabel("  VIDA DO DRAGÃO  ");
		titulo6.setForeground(Color.RED);
		titulo6.setAlignmentX(CENTER_ALIGNMENT);
		painelVidaDragao.setAlignmentX(CENTER_ALIGNMENT);
		painelVidaDragao.add(titulo6);
		painelVidaDragao.add(tab.getVidaDragaoLabel());
		add(Box.createRigidArea(new Dimension(0,10)));
		add(painelVidaDragao);
		add(Box.createRigidArea(new Dimension(0,10)));
		painelVidaDragao.setVisible(false);
		
		painelVidaPrincesa = new JPanel();
		painelVidaPrincesa.setLayout(new BoxLayout(painelVidaPrincesa, BoxLayout.Y_AXIS));
		painelVidaPrincesa.setPreferredSize(new Dimension(300,50));
		painelVidaPrincesa.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel titulo8 = new JLabel("  VIDA DA PRINCESA  ");
		titulo8.setForeground(new java.awt.Color(147, 6 , 175));
		titulo8.setAlignmentX(CENTER_ALIGNMENT);
		painelVidaPrincesa.setAlignmentX(CENTER_ALIGNMENT);
		painelVidaPrincesa.add(titulo8);
		painelVidaPrincesa.add(tab.getVidaPrincesaLabel());
		add(Box.createRigidArea(new Dimension(0,10)));
		add(painelVidaPrincesa);
		add(Box.createRigidArea(new Dimension(0,10)));
		painelVidaPrincesa.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addPersonagem) {
			painelInicial.setVisible(false);
			painelAviso.setVisible(false);
			painelAdicao.setVisible(true);
			painelSoldado.setVisible(true);
		}
		else if (e.getSource() == remPersonagem) {
			painelInicial.setVisible(false);
			painelAviso.setVisible(false);
			painelRemocao.setVisible(true);
		}
		else if (e.getSource() == iniciar) {
			try {
				tab.play();
				painelPontos.setVisible(false);
				painelInicial.setVisible(false);
				painelAviso.setVisible(false);
				painelVidaDragao.setVisible(true);
				painelVidaPrincesa.setVisible(true);
				add(tab.getFinishLabel());
				add(tab.getAgainButton());
			} catch (SemPersonagem erro) {
				aviso.setText("<html>"+"<center>"+erro.getMessage()+"</center>"+"</html>");
				painelAviso.setVisible(true);
			}
			
		}
		else if (e.getSource() == arqueiro) {
			painelSoldado.setVisible(false);
			painelTextA.setVisible(true);
			comando = 1;
		}
		else if (e.getSource() == lanceiro) {
			painelSoldado.setVisible(false);
			painelTextA.setVisible(true);
			comando = 2;
		}
		else if (e.getSource() == mago) {
			painelSoldado.setVisible(false);
			painelTextA.setVisible(true);
			comando = 3;
		}
		else if (e.getSource() == catapulta) {
			painelSoldado.setVisible(false);
			painelTextA.setVisible(true);
			comando = 4;
		}
		else if (e.getSource() == adicionar) {
			painelAdicao.setVisible(false);
			//painelRemocao.setVisible(false);
			painelTextA.setVisible(false);
			painelInicial.setVisible(true);
			
			char i;
			y=0;
			try { 
				x = Integer.parseInt(textXA.getText());
				i = (textYA.getText()).charAt(0);
				for (char c='a';c<='t';c++) {
					if (i==c)
						break;
					y++;
				}
				try {
					data.inserePersonagem(comando, x-1, y); //fazer try/catch
					tab.receiveData(data);
					//System.out.println(data.getPontos());
					pontos.setText(Integer.toString(data.getPontos()));
				} catch (AdicaoInvalida erro) {
					aviso.setText("<html>"+"<center>"+erro.getMessage()+"</center>"+"</html>");
					painelAviso.setVisible(true);
				}
			} catch (Exception erro) {
				aviso.setText("<html>"+"<center>"+"As informações não foram inseridas!"+"</center>"+"</html>");
				painelAviso.setVisible(true);
			}
			
			textXA.setText(null);
			textYA.setText(null);
			
		}
		else if (e.getSource() == remover) {
			//painelAdicao.setVisible(false);
			painelRemocao.setVisible(false);
			//painelTextA.setVisible(false);
			painelInicial.setVisible(true);
			
			char i;
			y=0;
			try { 
				x = Integer.parseInt(textXR.getText());
				i = (textYR.getText()).charAt(0);
				for (char c='a';c<='t';c++) {
					if (i==c)
						break;
					y++;
				}
				try {
					data.removePersonagem(x-1, y); //fazer try/catch
					tab.receiveData(data);
					pontos.setText(Integer.toString(data.getPontos()));
				} catch (RemocaoInvalida erro){
					aviso.setText("<html>"+"<center>"+erro.getMessage()+"</center>"+"</html>");
					painelAviso.setVisible(true);
				}
			} catch (Exception erro) {
				aviso.setText("<html>"+"<center>"+"As informações não foram inseridas!"+"</center>"+"</html>");
				painelAviso.setVisible(true);
			}
			
			textXR.setText(null);
			textYR.setText(null);
			
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public PainelMenu getPanel() {
		return ((PainelMenu)this);
	}
}
