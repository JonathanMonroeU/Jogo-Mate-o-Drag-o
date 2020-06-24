package mateodragao;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class PainelMenu extends JPanel implements ActionListener{
	private static final long serialVersionUID = 6299309752925290728L;
	private JPanel painelInicial, painelAdicao, painelRemocao, painelTextA, painelTextR, painelSoldado;
	private JButton addPersonagem, remPersonagem, iniciar, adicionar, remover;
	private JButton arqueiro, lanceiro, mago, catapulta;
	private JFormattedTextField TextXA, TextYA, TextXR, TextYR;
	
	public PainelMenu() {
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
			TextXA = new JFormattedTextField(mask);
			TextYA = new JFormattedTextField(mask);
			TextXR = new JFormattedTextField(mask);
			TextYR = new JFormattedTextField(mask);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		TextXA.setPreferredSize(new Dimension(40,20));
		TextYA.setPreferredSize(new Dimension(40,20));
		TextXR.setPreferredSize(new Dimension(40,20));
		TextYR.setPreferredSize(new Dimension(40,20));
		
		adicionar = new JButton("Adicionar");
		adicionar.addActionListener(this);
		remover = new JButton("Remover");
		remover.addActionListener(this);
		
				
		painelTextA = new JPanel();
		painelTextA.setLayout(new FlowLayout());
		painelTextA.setPreferredSize(new Dimension(300,300));
		painelTextA.setAlignmentX(CENTER_ALIGNMENT);
		painelTextA.add(new JLabel("Horizontal:"));
		painelTextA.add(TextXA);
		painelTextA.add(Box.createRigidArea(new Dimension(10,0)));
		painelTextA.add(new JLabel("Vertical:"));
		painelTextA.add(TextYA);
		painelTextA.add(adicionar);
		
		painelTextR = new JPanel();
		painelTextR.setLayout(new FlowLayout());
		painelTextR.setPreferredSize(new Dimension(300,300));
		painelTextR.setAlignmentX(CENTER_ALIGNMENT);
		painelTextR.add(new JLabel("Horizontal:"));
		painelTextR.add(TextXR);
		painelTextR.add(Box.createRigidArea(new Dimension(10,0)));
		painelTextR.add(new JLabel("Vertical:"));
		painelTextR.add(TextYR);
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
		}
		else if (e.getSource() == arqueiro) {
			painelSoldado.setVisible(false);
			painelTextA.setVisible(true);
		}
		else if (e.getSource() == adicionar || e.getSource() == remover) {
			painelAdicao.setVisible(false);
			painelRemocao.setVisible(false);
			painelTextA.setVisible(false);
			painelInicial.setVisible(true);
		}
	}
}
