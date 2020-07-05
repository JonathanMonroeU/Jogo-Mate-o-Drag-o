package src.painelmenu;

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
import javax.swing.SwingUtilities;

import src.dataprovider.IDataProvider;
import src.exceptions.AdicaoInvalida;
import src.exceptions.RemocaoInvalida;
import src.exceptions.SemPersonagem;
import src.tabuleiro.ITabuleiro;

public class PainelMenu extends JPanel implements IMenu, ActionListener{
	private static final long serialVersionUID = 6299309752925290728L;
	
	//Abaixo apresentam-se os diversos atributos gráficos de PainelMenu
	private JPanel painelInicial, painelAdicao, painelRemocao, painelTextA, painelTextR, painelSoldado, painelPontos, painelVidaDragao, painelVidaPrincesa, painelAviso, painelInstrucoes;
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
		
		//Definindo um gerente Layout. Escolheu-se o BoxLayout, para apresentar os paineis alinhados verticalmente
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(360,1000));
		JLabel titulo = new JLabel("Mate o Dragão");
		titulo.setFont(new Font("Arial", Font.BOLD, 35));
		titulo.setForeground(Color.lightGray);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(new Color(150, 0, 0));		//cor
		
		add(Box.createRigidArea(new Dimension(0,10)));
		add(titulo);
		add(Box.createRigidArea(new Dimension(0,20)));
		
		//Adicionando os botões da tela inicial
		addPersonagem = new JButton("Adicionar Personagem");
		remPersonagem = new JButton("Remover Personagem");
		iniciar = new JButton("Iniciar Jogo");
		iniciar.setBackground(Color.RED);
		iniciar.setForeground(Color.WHITE);
		iniciar.setBorder(BorderFactory.createLineBorder(Color.RED, 15));
		
		addPersonagem.setAlignmentX(CENTER_ALIGNMENT);
		remPersonagem.setAlignmentX(CENTER_ALIGNMENT);
		iniciar.setAlignmentX(CENTER_ALIGNMENT);
		
		//Adicionando ActionListener aos botões
		addPersonagem.addActionListener(this);
		remPersonagem.addActionListener(this);
		iniciar.addActionListener(this);
		
		//Adicionando os campos de texto para inserção de dados de adição e remoção
		textXA = new JTextField();
		textYA = new JTextField();
		textXR = new JTextField();
		textYR = new JTextField();
		
		textXA.setMaximumSize(new Dimension(40,20));
		textYA.setMaximumSize(new Dimension(40,20));
		textXR.setMaximumSize(new Dimension(40,20));
		textYR.setMaximumSize(new Dimension(40,20));
		
		//Adicionando os botões de "Adicionar" e "Remover" e ActionListener a eles
		adicionar = new JButton("Adicionar");
		adicionar.addActionListener(this);
		remover = new JButton("Remover");
		remover.addActionListener(this);
		
		//Customizando o painel de adição de personagem
		painelTextA = new JPanel();
		painelTextA.setLayout(new BoxLayout(painelTextA, BoxLayout.Y_AXIS));
		painelTextA.setMaximumSize(new Dimension(300,300));
		painelTextA.setAlignmentX(CENTER_ALIGNMENT);
		JLabel horizontal=new JLabel("Horizontal (A - T):");
		horizontal.setForeground(Color.WHITE);
		painelTextA.add(Box.createRigidArea(new Dimension(0,25)));
		horizontal.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(horizontal);
		textYA.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(textYA);
		JLabel vertical=new JLabel("Vertical (1 - 20):");
		vertical.setForeground(Color.WHITE);
		painelTextA.add(Box.createRigidArea(new Dimension(0,10)));
		vertical.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(vertical);
		textXA.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(textXA);
		painelTextA.add(Box.createRigidArea(new Dimension(0,10)));
		adicionar.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(adicionar);
		painelTextA.add(Box.createRigidArea(new Dimension(0,30)));
		JLabel dica = new JLabel("<html>"+"<center>"
				+"<b>DICA:</b>"+"<br/>"
				+"<b>O dragão ataca diretamente um personagem que esteja até a 5 casas de distância na horizontal ou vertical. Por isso, você não pode adicionar nas casas com identificação de E a P com 1 a 11.</b>"+"<center>"+"</html>");
			dica.setPreferredSize(new Dimension(300,155));
			dica.setFont(new Font("SansSerif", Font.PLAIN, 14));
			dica.setForeground(Color.WHITE);
			dica.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(dica);
		painelTextA.setBackground(new Color(150, 0, 0));
		painelTextA.setAlignmentX(CENTER_ALIGNMENT);//cor
		
		//Customizando o painel de remoção de personagem
		painelTextR = new JPanel();
		painelTextR.setLayout(new BoxLayout(painelTextR, BoxLayout.Y_AXIS));
		painelTextR.setMaximumSize(new Dimension(300,300));
		painelTextR.setAlignmentX(CENTER_ALIGNMENT);
		JLabel horizontalR=new JLabel("Horizontal (A - T):");
		horizontalR.setAlignmentX(CENTER_ALIGNMENT);
		horizontalR.setForeground(Color.WHITE);
		painelTextR.add(Box.createRigidArea(new Dimension(0,25)));
		painelTextR.add(horizontalR);
		textYR.setAlignmentX(CENTER_ALIGNMENT);
		painelTextR.add(textYR);
		JLabel verticalR=new JLabel("Vertical (1 - 20):");
		verticalR.setAlignmentX(CENTER_ALIGNMENT);
		verticalR.setForeground(Color.WHITE);
		painelTextR.add(Box.createRigidArea(new Dimension(0,10)));
		painelTextR.add(verticalR);
		textXR.setAlignmentX(CENTER_ALIGNMENT);
		painelTextR.add(textXR);
		painelTextR.add(Box.createRigidArea(new Dimension(0,10)));
		remover.setAlignmentX(CENTER_ALIGNMENT);
		painelTextR.add(remover);
		painelTextR.add(Box.createRigidArea(new Dimension(0,30)));
		painelTextR.setBackground(new Color(150, 0, 0));	
		
		//Adicionando os botões de soldados
		arqueiro = new JButton("Adicionar Arqueiro");
		arqueiro.setPreferredSize(new Dimension(200,25));
		lanceiro = new JButton("Adicionar Lanceiro");
		lanceiro.setPreferredSize(new Dimension(200,25));
		mago = new JButton("Adicionar Mago");
		mago.setPreferredSize(new Dimension(200,25));
		catapulta = new JButton("Adicionar Catapulta");
		catapulta.setPreferredSize(new Dimension(200,25));
		
		//Adicionando ActionListener aos botões
		arqueiro.addActionListener(this);
		lanceiro.addActionListener(this);
		mago.addActionListener(this);
		catapulta.addActionListener(this);
		
		//Adicionando as informações de cada personagem
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
		
		//Customizando o painel de soldados
		painelSoldado = new JPanel();
		painelSoldado.setLayout(new FlowLayout());
		painelSoldado.setAlignmentX(CENTER_ALIGNMENT);
		painelSoldado.setPreferredSize(new Dimension(300,300));
		painelSoldado.add(arqueiro);
		painelSoldado.add(lanceiro);
		painelSoldado.add(mago);
		painelSoldado.add(catapulta);
		painelSoldado.add(arqueiroInfo);
		painelSoldado.add(lanceiroInfo);
		painelSoldado.add(magoInfo);
		painelSoldado.add(catapultaInfo);
		painelSoldado.setBackground(new java.awt.Color(150, 0, 0));
		
		//Adicionando e customizando o painel de pontos
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
		
		//Adicionando e customizando o painel inicial
		painelInicial = new JPanel();
		painelInicial.setLayout(new BoxLayout(painelInicial, BoxLayout.Y_AXIS));
		painelInicial.setPreferredSize(new Dimension(300,400));
		painelInicial.add(Box.createRigidArea(new Dimension(0,20)));
		painelInicial.setAlignmentX(CENTER_ALIGNMENT);
		painelInicial.setBackground(new Color(150, 0, 0));
		JLabel titulo2 = new JLabel("Adicione personagens e comece o jogo!");
		titulo2.setAlignmentX(CENTER_ALIGNMENT);
		titulo2.setFont(new Font("Arial", Font.BOLD, 16));
		titulo2.setForeground(Color.white);
		painelInicial.add(Box.createRigidArea(new Dimension(0,10)));
		painelInicial.add(titulo2);
		painelInicial.add(Box.createRigidArea(new Dimension(0,5)));
		painelInicial.add(addPersonagem);
		painelInicial.add(Box.createRigidArea(new Dimension(0,5)));
		painelInicial.add(remPersonagem);
		painelInicial.add(Box.createRigidArea(new Dimension(0,10)));
		painelInicial.add(iniciar);
		add(painelInicial);
		
		//Adicionando e customizando o painel de aviso
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
		painelAviso.setVisible(false);
		
		//Adicionando e customizando o painel de instrucoes
		painelInstrucoes = new JPanel();
		painelInstrucoes.setLayout(new BoxLayout(painelInstrucoes, BoxLayout.Y_AXIS));
		painelInstrucoes.setPreferredSize(new Dimension(300,400));
		painelInstrucoes.add(Box.createRigidArea(new Dimension(0,30)));
		painelInstrucoes.setAlignmentX(CENTER_ALIGNMENT);
		painelInstrucoes.setBackground(new java.awt.Color(150, 0, 0));
		JLabel texto = new JLabel("<html>"+"<center>"
				+"	O dragão atacou o reino repentinamente e a princesa"+"<br/>"
				+"está bem no meio do ataque, reúna os soldados e mate-o "+"<br/>"
				+ "antes que o pior aconteça."+"<center>"+"</html>");
		texto.setPreferredSize(new Dimension(300,155));
		texto.setFont(new Font("SansSerif", Font.BOLD, 16));
		texto.setForeground(Color.WHITE);
		texto.setAlignmentX(CENTER_ALIGNMENT);
		painelInstrucoes.add(texto);
		painelInstrucoes.add(Box.createRigidArea(new Dimension(0,10)));
		JLabel instrucoesTitulo = new JLabel("Instruções:");
		instrucoesTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		instrucoesTitulo.setForeground(Color.white);
		instrucoesTitulo.setAlignmentX(CENTER_ALIGNMENT);
		painelInstrucoes.add(instrucoesTitulo);
		JLabel instrucoes = new JLabel("<html>"+"<center>"
			+"O objetivo do jogo é matar o dragão,"+"<br/>"
			+"para isso, coloque os soldados nas"+"<br/>"
			+"posições do campo que preferir. "+"<br/>"
			+"Após iniciar o jogo, controle a princesa "+"<br/>"
			+"usando o direcional, para evitar que seja"+"<br/>"
			+"atingida por ataques amigos e inimigos."+"<br/>"
			+"Você perde o jogo se a princesa ou todos"+"<br/>"
			+"os soldados morrerem, e vence se matar"+"<br/>"
			+"o dragão antes disso."+"<br/>"
			+""+"<br/>"
			+"<b>ATRIBUTOS DOS SOLDADOS:</b>"+"<br/>"
			+"<b>Custo:</b>"+"Pontos necessários para inserí-lo em campo."+"<br/>"
			+"<b>Resistência: </b>"+"Quantas bolas de fogo são necessárias para matá-lo."+"<br/>"
			+"<b>Movimento: </b>"+" A cada quantos passos do jogo ele se movimenta."+"<br/>"
			+"<b>Frequência de disparos: </b>"+"A cada quantos passos é feito um disparo."+"<br/>"
			+"<b>Movimento do projétil: </b>"+"A cada quantos passos o projétil se movimenta."+"<br/>"
			+"<b>Dano do projétil:</b>"+"Quantos pontos de vida subtrai do inimigo ou da princesa."+"<center>"+"</html>");
		instrucoes.setPreferredSize(new Dimension(300,155));
		instrucoes.setFont(new Font("SansSerif", Font.PLAIN, 16));
		instrucoes.setForeground(Color.WHITE);
		instrucoes.setAlignmentX(CENTER_ALIGNMENT);
		painelInstrucoes.add(instrucoes);
		add(painelInstrucoes);
		
		//Adicionando o paindel de adição de personagem
		painelAdicao = new JPanel();
		painelAdicao.setPreferredSize(new Dimension(300,300));
		painelAdicao.setLayout(new BoxLayout(painelAdicao, BoxLayout.Y_AXIS));
		painelAdicao.setAlignmentX(CENTER_ALIGNMENT);
		painelAdicao.add(painelSoldado);
		painelAdicao.add(painelTextA);
		add(painelAdicao);
		painelAdicao.setVisible(false);
		painelTextA.setVisible(false);
		
		//Adicionando o painel de remoção de personagem
		painelRemocao = new JPanel();
		painelRemocao.setLayout(new BoxLayout(painelRemocao, BoxLayout.Y_AXIS));
		painelRemocao.setAlignmentX(CENTER_ALIGNMENT);
		painelRemocao.add(painelTextR);
		add(painelRemocao);
		painelRemocao.setVisible(false);
		
		//Adicionando e customizando o painel de vida do dragão
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
		
		//Adicionando e customizando o painel de vida da princesa
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
	
	//Nesse método, dependendo do botão pressionado, irá realizar uma ação especifica
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addPersonagem) {
			showAddPersonagem();
		}
		else if (e.getSource() == remPersonagem) {
			showRemPersonagem();
		}
		else if (e.getSource() == iniciar) {
			showInicioDeJogo();
		}
		else if (e.getSource() == arqueiro) {
			showArqueiro();
		}
		else if (e.getSource() == lanceiro) {
			showLanceiro();
		}
		else if (e.getSource() == mago) {
			showMago();
		}
		else if (e.getSource() == catapulta) {
			showCatapulta();
		}
		else if (e.getSource() == adicionar) {
			showAdicionar();
		}
		else if (e.getSource() == remover) {
			showRemover();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	//Esse método retorna o painel gráfico do menu
	public PainelMenu getPanel() {
		return ((PainelMenu)this);
	}
	
	//Esse método mostra na tela o painel de adição de personagem
	public void showAddPersonagem() {
		painelInicial.setVisible(false);
		painelInstrucoes.setVisible(false);
		painelAviso.setVisible(false);
		painelAdicao.setVisible(true);
		painelSoldado.setVisible(true);
	}
	
	//Esse método mostra na tela o painel de remoção de personagem
	public void showRemPersonagem() {
		painelInicial.setVisible(false);
		painelInstrucoes.setVisible(false);
		painelAviso.setVisible(false);
		painelRemocao.setVisible(true);
	}
	
	//Esse método mostra na tela o painel pós-inicio de jogo
	public void showInicioDeJogo() {
		//Aqui há o tratamento de exceção quanto ao inicio do jogo
		try {
			tab.play();
			painelPontos.setVisible(false);
			painelInicial.setVisible(false);
			painelInstrucoes.setVisible(false);
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
	
	//Esse metodo mostra na tela o painel para adição do arqueiro
	public void showArqueiro() {
		painelSoldado.setVisible(false);
		painelTextA.setVisible(true);
		comando = 1;
	}
	
	//Esse metodo mostra na tela o painel para adição do lanceiro
	public void showLanceiro() {
		painelSoldado.setVisible(false);
		painelTextA.setVisible(true);
		comando = 2;
	}
	
	//Esse metodo mostra na tela o painel para adição do mago
	public void showMago() {
		painelSoldado.setVisible(false);
		painelTextA.setVisible(true);
		comando = 3;
	}
	
	//Esse metodo mostra na tela o painel para adição da catapulta
	public void showCatapulta() {
		painelSoldado.setVisible(false);
		painelTextA.setVisible(true);
		comando = 4;
	}
	
	//Esse metodo realiza a ação de passar os dados de adição de personagem ao DataProvider e este passar as informações ao Tabuleiro
	public void showAdicionar() {
		painelAdicao.setVisible(false);
		painelTextA.setVisible(false);
		painelInicial.setVisible(true);
		painelInstrucoes.setVisible(true);
		
		char i;
		y=0;
		try { 
			x = Integer.parseInt(textXA.getText());
			i = (textYA.getText()).charAt(0);
			for (char c='a';c<='t';c++) {
				if (Character.toLowerCase(i)==c)
					break;
				y++;
			}
			try {
				data.inserePersonagem(comando, x-1, y); 
				tab.receiveData(data);
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
	
	//Esse metodo realiza a ação de passar os dados de remoção de personagem ao DataProvider e este passar as informações ao Tabuleiro
	public void showRemover() {
		painelRemocao.setVisible(false);
		painelInicial.setVisible(true);
		painelInstrucoes.setVisible(true);
		
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
				data.removePersonagem(x-1, y);
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
}