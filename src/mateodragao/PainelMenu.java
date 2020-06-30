package mateodragao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import mateodragao.exceptions.AdicaoInvalida;
import mateodragao.exceptions.RemocaoInvalida;
import mateodragao.exceptions.SemPersonagem;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.ITabuleiro;

public class PainelMenu extends JPanel implements ActionListener{
	private static final long serialVersionUID = 6299309752925290728L;
	private JPanel painelInicial, painelAdicao, painelRemocao, painelTextA, painelTextR, painelSoldado, painelPontos, painelVida, painelAviso;
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
		JLabel titulo = new JLabel("Jogo Mate o Dragão");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		JLabel titulo2 = new JLabel("Adicione personagens e comece o jogo!");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo2.setAlignmentX(CENTER_ALIGNMENT);
		
		add(titulo);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(titulo2);
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
		
		try {
			MaskFormatter mask = new MaskFormatter("##");
			textXA = new JTextField();
			textYA = new JTextField();
			textXR = new JTextField();
			textYR = new JTextField();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
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
		painelTextA.add(new JLabel("Horizontal:"));
		painelTextA.add(textYA);
		painelTextA.add(Box.createRigidArea(new Dimension(10,0)));
		painelTextA.add(new JLabel("Vertical:"));
		painelTextA.add(textXA);
		painelTextA.add(adicionar);
		
		painelTextR = new JPanel();
		painelTextR.setLayout(new FlowLayout());
		painelTextR.setMaximumSize(new Dimension(300,300));
		painelTextR.setAlignmentX(CENTER_ALIGNMENT);
		painelTextR.add(new JLabel("Horizontal:"));
		painelTextR.add(textYR);
		painelTextR.add(Box.createRigidArea(new Dimension(10,0)));
		painelTextR.add(new JLabel("Vertical:"));
		painelTextR.add(textXR);
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
		
		Font fonte = new Font("SansSerif", Font.PLAIN, 12);
		arqueiroInfo = new JLabel("<html>"
			+"<b>--ARQUEIRO</b>"+"<br/>"
			+"<b>Custo:</b>"+" 5 pontos"+"<br/>"
			+"<b>Dano da flecha:</b>"+" 15 pontos de vida"+"<br/>"
			+"<b>Frenquência de disparo:</b>"+" A todo passo do jogo"+"<br/>"
			+"<b>Velocidade da flecha:</b>"+" 3 casas por passo do jogo"+"<br/>"
			+"<b>Resistência:</b>"+" Morte com uma bola de fogo"+"<br/>"
			+"<b>Movimento:</b>"+" 2 casas por passo do jogo"+"</html>");
		arqueiroInfo.setPreferredSize(new Dimension(300,160));
		arqueiroInfo.setFont(fonte);
		
		lanceiroInfo = new JLabel("<html>"
			+"<b>--LANCEIRO</b>"+"<br/>"
			+"<b>Custo:</b>"+" 10 pontos"+"<br/>"
			+"<b>Dano da lança:</b>"+" 25 pontos de vida"+"<br/>"
			+"<b>Frenquência de disparo:</b>"+" 2 passos sim, 1 passo não"+"<br/>"
			+"<b>Velocidade da lança:</b>"+" 2 casas por passo do jogo"+"<br/>"
			+"<b>Resistência:</b>"+" Morte com 3 bolas de fogo"+"<br/>"
			+"<b>Movimento:</b>"+" 1 casa, intercalando os passos do jogo"+"</html>");
		lanceiroInfo.setPreferredSize(new Dimension(300,160));
		lanceiroInfo.setFont(fonte);
		
		magoInfo = new JLabel("<html>"
			+"<b>--MAGO</b>"+"<br/>"
			+"<b>Custo:</b>"+" 15 pontos"+"<br/>"
			+"<b>Dano da bola de energia:</b>"+" 50 pontos de vida"+"<br/>"
			+"<b>Frenquência de disparo:</b>"+" A cada 2 passos do jogo"+"<br/>"
			+"<b>Velocidade da bola de energia:</b>"+" 2 casas por passo do jogo"+"<br/>"
			+"<b>Resistência:</b>"+" Morte com 2 bolas de fogo"+"<br/>"
			+"<b>Movimento:</b>"+" 1 casa por passo do jogo"+"</html>");
		magoInfo.setPreferredSize(new Dimension(300,160));
		magoInfo.setFont(fonte);
		
		catapultaInfo = new JLabel("<html>"
			+"<b>--CATAPULTA</b>"+"<br/>"
			+"<b>Custo:</b>"+" 30 pontos"+"<br/>"
			+"<b>Dano da pedra:</b>"+" 100 pontos de vida"+"<br/>"
			+"<b>Frenquência de disparo:</b>"+" A cada 3 passos do jogo"+"<br/>"
			+"<b>Velocidade da pedra:</b>"+" 4 casas por passo do jogo"+"<br/>"
			+"<b>Resistência:</b>"+" Destruida com 1 bola de fogo"+"<br/>"
			+"<b>Movimento:</b>"+" Não se move"+"</html>");
		catapultaInfo.setPreferredSize(new Dimension(300,160));
		catapultaInfo.setFont(fonte);
		
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
		
		painelPontos = new JPanel();
		painelPontos.setLayout(new BoxLayout(painelPontos, BoxLayout.Y_AXIS));
		painelPontos.setPreferredSize(new Dimension(300,70));
		JLabel titulo5 = new JLabel("PONTOS");
		titulo5.setAlignmentX(CENTER_ALIGNMENT);
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
		painelInicial.add(addPersonagem);
		painelInicial.add(remPersonagem);
		painelInicial.add(iniciar);
		painelInicial.add(Box.createRigidArea(new Dimension(0,40)));
		painelInicial.setAlignmentX(CENTER_ALIGNMENT);
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
		
		painelVida = new JPanel();
		painelVida.setLayout(new BoxLayout(painelVida, BoxLayout.Y_AXIS));
		painelVida.setPreferredSize(new Dimension(300,50));
		JLabel titulo6 = new JLabel("VIDA DO DRAGÃO");
		titulo6.setAlignmentX(CENTER_ALIGNMENT);
		painelVida.setAlignmentX(CENTER_ALIGNMENT);
		painelVida.add(titulo6);
		painelVida.add(((PainelTabuleiro) tab).getVidaLabel());
		add(Box.createRigidArea(new Dimension(0,10)));
		add(painelVida);
		add(Box.createRigidArea(new Dimension(0,10)));
		painelVida.setVisible(false);
		
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
				painelVida.setVisible(true);
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
			
			try { 
				x = Integer.parseInt(textXA.getText());
				y = Integer.parseInt(textYA.getText());
				
				try {
					data.inserePersonagem(comando, x, y); //fazer try/catch
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
			
			try {
				x = Integer.parseInt(textXR.getText());
				y = Integer.parseInt(textYR.getText());
				
				try {
					data.removePersonagem(x, y); //fazer try/catch
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
}
