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

import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.ITabuleiro;

public class PainelMenu extends JPanel implements ActionListener{
	private static final long serialVersionUID = 6299309752925290728L;
	private JPanel painelInicial, painelAdicao, painelRemocao, painelTextA, painelTextR, painelSoldado, painelPontos;
	private JButton addPersonagem, remPersonagem, iniciar, adicionar, remover;
	private JButton arqueiro, lanceiro, mago, catapulta;
	private JTextField textXA, textYA, textXR, textYR;
	private JLabel pontos;
	private ITabuleiro tab;
	private IDataProvider data;
	private int comando, x, y;
	
	public PainelMenu(ITabuleiro tab, IDataProvider data) {
		this.tab = tab;
		this.data = data;
		System.out.println(data.getPontos());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
		painelTextA.setPreferredSize(new Dimension(300,300));
		painelTextA.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(new JLabel("Horizontal:"));
		painelTextA.add(textXA);
		painelTextA.add(Box.createRigidArea(new Dimension(10,0)));
		painelTextA.add(new JLabel("Vertical:"));
		painelTextA.add(textYA);
		painelTextA.add(adicionar);
		
		painelTextR = new JPanel();
		painelTextR.setLayout(new FlowLayout());
		painelTextR.setPreferredSize(new Dimension(300,300));
		painelTextR.setAlignmentX(CENTER_ALIGNMENT);
		painelTextR.add(new JLabel("Horizontal:"));
		painelTextR.add(textXR);
		painelTextR.add(Box.createRigidArea(new Dimension(10,0)));
		painelTextR.add(new JLabel("Vertical:"));
		painelTextR.add(textYR);
		painelTextR.add(remover);
		
		arqueiro = new JButton("Arqueiro");
		arqueiro.setPreferredSize(new Dimension(200,25));
		lanceiro = new JButton("Lanceiro");
		lanceiro.setPreferredSize(new Dimension(200,25));
		mago = new JButton("Mago");
		mago.setPreferredSize(new Dimension(200,25));
		catapulta = new JButton("Catapulta");
		catapulta.setPreferredSize(new Dimension(200,25));
		
		arqueiro.addActionListener(this);
		lanceiro.addActionListener(this);
		mago.addActionListener(this);
		catapulta.addActionListener(this);
		
		painelSoldado = new JPanel();
		painelSoldado.setLayout(new FlowLayout());
		painelSoldado.setAlignmentX(CENTER_ALIGNMENT);
		painelSoldado.setPreferredSize(new Dimension(300,300));
		painelSoldado.add(arqueiro);
		painelSoldado.add(lanceiro);
		painelSoldado.add(mago);
		painelSoldado.add(catapulta);
		
		painelPontos = new JPanel();
		painelPontos.setLayout(new BoxLayout(painelPontos, BoxLayout.Y_AXIS));
		painelPontos.setPreferredSize(new Dimension(300,50));
		JLabel titulo5 = new JLabel("PONTOS");
		titulo5.setAlignmentX(CENTER_ALIGNMENT);
		pontos = new JLabel(Integer.toString(data.getPontos()));
		pontos.setAlignmentX(CENTER_ALIGNMENT);
		pontos.setHorizontalAlignment(0);
		pontos.setMaximumSize(new Dimension(50,30));
		pontos.setBorder(BorderFactory.createLineBorder(Color.black));
		painelPontos.setAlignmentX(CENTER_ALIGNMENT);
		painelPontos.add(titulo5);
		painelPontos.add(pontos);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(painelPontos);
		add(Box.createRigidArea(new Dimension(0,40)));
		
		painelInicial = new JPanel();
		painelInicial.setLayout(new BoxLayout(painelInicial, BoxLayout.Y_AXIS));
		painelInicial.setPreferredSize(new Dimension(300,400));
		painelInicial.add(addPersonagem);
		painelInicial.add(remPersonagem);
		painelInicial.add(iniciar);
		painelInicial.setAlignmentX(CENTER_ALIGNMENT);
		add(painelInicial);
		
		painelAdicao = new JPanel();
		painelAdicao.setPreferredSize(new Dimension(300,300));
		painelAdicao.setLayout(new BoxLayout(painelAdicao, BoxLayout.Y_AXIS));
		JLabel titulo3 = new JLabel("TELA DE ADICAO");
		titulo3.setAlignmentX(CENTER_ALIGNMENT);
		painelAdicao.setAlignmentX(CENTER_ALIGNMENT);
		painelAdicao.add(titulo3);
		painelAdicao.add(painelSoldado);
		painelAdicao.add(painelTextA);
		add(painelAdicao);
		painelAdicao.setVisible(false);
		painelTextA.setVisible(false);
		
		painelRemocao = new JPanel();
		painelRemocao.setLayout(new BoxLayout(painelRemocao, BoxLayout.Y_AXIS));
		JLabel titulo4 = new JLabel("TELA DE REMOCAO");
		titulo4.setAlignmentX(CENTER_ALIGNMENT);
		painelRemocao.setAlignmentX(CENTER_ALIGNMENT);
		painelRemocao.add(titulo4);
		painelRemocao.add(painelTextR);
		add(painelRemocao);
		painelRemocao.setVisible(false);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addPersonagem) {
			painelInicial.setVisible(false);
			painelAdicao.setVisible(true);
			painelSoldado.setVisible(true);
		}
		else if (e.getSource() == remPersonagem) {
			painelInicial.setVisible(false);
			painelRemocao.setVisible(true);
		}
		else if (e.getSource() == iniciar) {
			painelInicial.setVisible(false);
			tab.play();
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
			
			x = Integer.parseInt(textXA.getText());
			y = Integer.parseInt(textYA.getText());
			
			data.inserePersonagem(comando, x, y); //fazer try/catch
			tab.receiveData(data);
			System.out.println(data.getPontos());
			textXA.setText(null);
			textYA.setText(null);
			pontos.setText(Integer.toString(data.getPontos()));
		}
		else if (e.getSource() == remover) {
			//painelAdicao.setVisible(false);
			painelRemocao.setVisible(false);
			//painelTextA.setVisible(false);
			painelInicial.setVisible(true);
			
			x = Integer.parseInt(textXR.getText());
			y = Integer.parseInt(textYR.getText());
			data.removePersonagem(x, y); //fazer try/catch
			tab.receiveData(data);
			
			textXR.setText(null);
			textYR.setText(null);
			pontos.setText(Integer.toString(data.getPontos()));
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
}
