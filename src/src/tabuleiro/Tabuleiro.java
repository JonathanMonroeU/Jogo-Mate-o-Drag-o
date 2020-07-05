package src.tabuleiro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import src.dataprovider.IDataProvider;
import src.exceptions.SemPersonagem;
import src.personagem.Arqueiro;
import src.personagem.Catapulta;
import src.personagem.Dragao;
import src.personagem.IPersonagem;
import src.personagem.Lanceiro;
import src.personagem.Mago;
import src.personagem.PecaIcon;
import src.personagem.Princesa;
import src.projetil.IProjetil;

public class Tabuleiro extends PainelTabuleiro implements ITabuleiro, ActionListener{
	private static final long serialVersionUID = -4923736996545875913L;
	public static String DIRETORIO = Dragao.class.getResource(".").getPath();
	private IPersonagem vPersonagem[][][];		//Matriz para guardar os personagens nas suas devidas posições no campo, a princesa é o único personagem que fica em uma camada diferente da matriz, [x][y][1].
	private IProjetil vProjetil[][][];			//Matriz de projeteis lançados pelos personagens, com 3 dimensões pois a terceira serve para guardar uma segunda camada de projeteis, que são as pedras de catapultas. 
	private IProjetil vConflito[];				//Vetor que guarda os projeteis que estão na mesma posição, para comparar quem tem o maior dano.
	private int dragonPosition[];				//Guarda as posições x,y do dragão, para que sejam acessíveis a todos os outros personagens.
	private int princesaPosition[];
	private int numeroSoldados;					//Quantidade de soldados inseridos pelo jogador no momento.
	private int atual;							
	private Metronomo metro = new Metronomo(20);	//Metrônomo definindo o tempo para ativação de cada modificação do campo.
	private MeuKeyListener keys;				//Implementa KeyListener, e contém métodos para receber as teclas do teclado e ativar a movientação da princesa.
	private PecaIcon compl1,compl2,compl3;		//3 PecaIcon para completar a imagem do dragão.
	
	public Tabuleiro() {
		super();
		vProjetil = new IProjetil[20][20][2];
		vConflito = new IProjetil [20];
		
		vPersonagem = new IPersonagem[20][20][2];
		dragonPosition = new int[2];
		princesaPosition=new int[2];
		numeroSoldados = 0;
		
		//As 3 posições vizinhas do tabuleiro apontam para a na qual o dragão é instanciado, e são adicionadas 3 imagens para formarem o dragão.
		vPersonagem[5][10][0] = new Dragao(5,10);					
		vPersonagem[4][10][0]=vPersonagem[5][10][0];
		vPersonagem[4][9][0]=vPersonagem[5][10][0];
		vPersonagem[5][9][0]=vPersonagem[5][10][0];
		compl1 = new PecaIcon(DIRETORIO+"dragao1.png");
		compl2 = new PecaIcon(DIRETORIO+"dragao2.png");
		compl3 = new PecaIcon(DIRETORIO+"dragao3.png");
		setElemento(5,10,(PecaIcon) vPersonagem[5][10][0]);
		setElemento(4,9,compl1);
		setElemento(4,10,compl2);
		setElemento(5,9,compl3);
		
		dragonPosition[0] = 5;									
		dragonPosition[1] = 10;
		atual=-1;
		
		//A princesa é instanciada em sua posição inicial.
		vPersonagem[18][10][1] = new Princesa(18,10, this);	
		princesaPosition[0] = 18;									
		princesaPosition[1] = 10;
		setElemento(18,10,(PecaIcon) vPersonagem[18][10][1]);
		
		//São criadas JLabels que mostram a vida do dragão e da princesa. 
		vidaDragao = new JLabel(Integer.toString(vPersonagem[dragonPosition[0]][dragonPosition[1]][0].getVida()));
		vidaDragao.setAlignmentX(CENTER_ALIGNMENT);
		vidaDragao.setHorizontalAlignment(0);
		vidaDragao.setMaximumSize(new Dimension(50,30));
		vidaDragao.setBorder(BorderFactory.createLineBorder(Color.black));
		
		vidaPrincesa = new JLabel(Integer.toString(vPersonagem[princesaPosition[0]][princesaPosition[1]][1].getVida()));
		vidaPrincesa.setAlignmentX(CENTER_ALIGNMENT);
		vidaPrincesa.setHorizontalAlignment(0);
		vidaPrincesa.setMaximumSize(new Dimension(50,30));
		vidaPrincesa.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//É criada a JLabel que mostra a mensagem quando o jogo acaba.
		finish = new JLabel();
		finish.setFont(new Font("SansSerif", Font.BOLD, 16));
		finish.setAlignmentX(CENTER_ALIGNMENT);
		finish.setHorizontalAlignment(0);
		finish.setMaximumSize(new Dimension(300,100));
		
		//É criado o botão de Jogar Novamente.
		again = new JButton("Jogar Novamente");
		again.setAlignmentX(CENTER_ALIGNMENT);
		again.setVisible(false);
		
		keys=new MeuKeyListener(this);
		metro.addActionListener(this);
	}
	
	/*O método é ativado quando é pressionado o botão "Iniciar Jogo", que inicia o metrônomo que cadencia os passos do jogo
	 * e também ativa o método para receber os cliques do direcional que ativam o método de movimento da princesa.*/
	@Override
	public void play() throws SemPersonagem{
		if (numeroSoldados == 0)
			throw new SemPersonagem("Voce nao pode comecar o jogo sem personagens!");
		metro.start();
		
		this.addKeyListener(keys); 
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	//A cada passo do metrônomo que gera o evento, se nem todos os soldados tiverem morrido, nem o dragão, nem a princesa, então o jogo continua.
	public void actionPerformed(ActionEvent e) {
		if (numeroSoldados != 0 && vPersonagem[dragonPosition[0]][dragonPosition[1]][0].getVida()>0 && vPersonagem[princesaPosition[0]][princesaPosition[1]][1].getVida()>0) {
			modificaTabuleiro();
		}
		else {
			finish();
			metro.stop();
		}
	}

	//Método que modifica o tabuleiro a cada passo do jogo, cadenciado pelo metrônomo.
	@Override
	public void modificaTabuleiro() {
		//O dragão é o primeiro a disparar seu projétil e se mover.
		vPersonagem[dragonPosition[0]][dragonPosition[1]][0].disparaProjetil(this);
		vPersonagem[dragonPosition[0]][dragonPosition[1]][0].move(this);
		setElemento(dragonPosition[0]-1,dragonPosition[1]-1,compl1);
		setElemento(dragonPosition[0]-1,dragonPosition[1],compl2);
		setElemento(dragonPosition[0],dragonPosition[1]-1,compl3);
		
		
		//Primeira passagem por todas as posições do tabuleiro para disparar projéteis e mover personagens diferentes do dragão e projéteis.
		for (int i=0; i<20; i++) {
			for (int j=0; j<20; j++) {
				if (vPersonagem[i][j][0] != null) {
					//Se o personagem não for o dragão e não tiver agido ainda nesse tempo do jogo:
					if (!(vPersonagem[i][j][0] instanceof Dragao) && !(vPersonagem[i][j][0].getJaAgiu())) {	
						vPersonagem[i][j][0].setJaAgiu(true);
						//Se a posição onde o personagem está estiver vazia na sua respectiva posição no vetor de ataques:
						if(vProjetil[i][j][0]==null && !(vPersonagem[i][j][0] instanceof Catapulta) || vProjetil[i][j][1]==null && vPersonagem[i][j][0] instanceof Catapulta)
							vPersonagem[i][j][0].disparaProjetil(this);
						if(vPersonagem[i][j][0].getMovimento()!=0)
							vPersonagem[i][j][0].move(this);
					}
				}
				
				//Se houver um projétil na posição atual e ele não tiver agido ainda nesse tempo, ele é movido.
				if (vProjetil[i][j][0] != null) {
					if (!(vProjetil[i][j][0].getJaAgiu()))
						vProjetil[i][j][0].move(this);
				//Se houver um projétil na posição atual e ele não tiver agido ainda nesse tempo, ele é movido.	
				}if (vProjetil[i][j][1] != null) {
					if (!(vProjetil[i][j][1].getJaAgiu()))
						vProjetil[i][j][1].move(this); 
				}
			}
		}
		
		/*Passagem pelo vetor de conflitos de projéteis, se houver algum conflito no vetor, 
		 * ele é percorrido e os conflitos são resolvidos pelo método resolveConflito */
		if (atual>-1) {
			for (int i=0;i<=atual;i++) {
				resolveConflito(vConflito[i]);
			}
			atual=-1;
		}
		
		/*Segunda passagem pelo tabuleiro para analisar projéteis que acertaram personagens, ou seja, 
		 * estão na mesma posição x, y que um personagem nesse tempo:*/
		
		//Se a princesa estiver na posição de um projétil, é ativado o método que faz ela perder vida.
		if (vProjetil[princesaPosition[0]][princesaPosition[1]][0]!=null) {
			vPersonagem[princesaPosition[0]][princesaPosition[1]][1].perdeVida(vProjetil[princesaPosition[0]][princesaPosition[1]][0],this);
			setProjetil(princesaPosition[0], princesaPosition[1], 0, null);
		}
		for (int i=0; i<20; i++) {
			for (int j=0; j<20; j++) {
				//Se houver um pesonagem e um projétil na mesma posição é ativado o método que checa se ele deve perder vida e a reduz se necessário.
				if (vPersonagem[i][j][0] != null && vProjetil[i][j][0]!=null) { 
					vPersonagem[i][j][0].perdeVida(vProjetil[i][j][0],this);
					setProjetil(i, j, 0, null);
				}
				//Se houver um pesonagem e um projétil na mesma posição é ativado o método que checa se ele deve perder vida e a reduz se necessário.
				if (vPersonagem[i][j][0] != null && vProjetil[i][j][1]!=null) {
					vPersonagem[i][j][0].perdeVida(vProjetil[i][j][1],this);
					setProjetil(i, j, 1, null);
				}
				//Se houver um personagem na posição, é reiniciado o atributo que diz que ele já agiu no tempo, para estar 0 no pŕoximo tempo do jogo.
				if (vPersonagem[i][j][0] != null) {
					vPersonagem[i][j][0].setJaAgiu(false);
					//Se a vida for menor ou igual a 0, o personagem morreu e é removido de campo.
					if (vPersonagem[i][j][0].getVida()<=0) 
						removePeca(i,j,0); 	//morte
				}
				//Se houver um projetil na posição, é reiniciado o atributo que diz que ele já agiu no tempo, para estar false no pŕoximo tempo do jogo.
				if (vProjetil[i][j][0] != null) 
					vProjetil[i][j][0].setJaAgiu(false);
						
				//Se houver um projetil na posição, é reiniciado o atributo que diz que ele já agiu no tempo, para estar false no pŕoximo tempo do jogo.
				if (vProjetil[i][j][1] != null) 
					vProjetil[i][j][1].setJaAgiu(false);
				
			}
		}
		//Atualiza a vida do dragão e da princesa a serem exibidas.
		vidaDragao.setText(Integer.toString(vPersonagem[dragonPosition[0]][dragonPosition[1]][0].getVida()));
		vidaPrincesa.setText(Integer.toString(vPersonagem[princesaPosition[0]][princesaPosition[1]][1].getVida()));
	}

	//Pega o vetor pecaPositionAtual do dataProvider para fazer a remoção ou inserção do personagem no campo, dependendo das informações contidas nele.//
	@Override
	public void receiveData(IDataProvider dataProvider) {
		int position[] = dataProvider.getData();
		if (position[0] == 0)  //Zero é remoção.
			removePeca(position[1],position[2], 0);
		else
			putPersonagem(position[1],position[2],position[0]);
	}
	
	//Adiciona o conflito no vetor de conflitos de projeteis a serem resolvidos depois que todo o campo for percorrido a primeira vez em modificaTabuleiro.
	@Override
	public void adicionaConflito(IProjetil projetil) {
		atual+=1;
		vConflito[atual]=projetil;
		projetil.setEmConflito(true);
	}
	
	//Resolve o conflito da posição atual do vetor de conflitos de projéteis.
	public void resolveConflito (IProjetil projetil) {
		//Se a posição para o qual o projetil quer se mover ainda estiver ocupada:
		if(vProjetil[projetil.getxConflito()][projetil.getyConflito()][0]!=null) {
			//Se o dano do projetil for maior do que o que está na posição para o qual ele quer se mover, ele se move ocupando o lugar do outro.
			if  (projetil.getDano()>vProjetil[projetil.getxConflito()][projetil.getyConflito()][0].getDano()) {
				projetil.setEmConflito(false);	//Seu conflito foi resolvido.	
				projetil.setJaAgiu(true);	
				
				setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, null); //Retira o outro projétil da posição para onde ele quer ir.
				setProjetil(projetil.getx(), projetil.gety(), 0, null);	
				setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, projetil);
				projetil.setX(projetil.getxConflito());
				projetil.setY(projetil.getyConflito());
			}
			//Caso o dano do projétil seja menor ou igual do que o que está na posição para o qual ele se moveria, ele some.
			else
				setProjetil(projetil.getx(), projetil.gety(), 0, null);
		//Caso a posição para o qual ele quer se mover já esteja vazia, ele se move.
		}else {
			projetil.setEmConflito(false);
			projetil.setJaAgiu(true);		
			
			setProjetil(projetil.getx(), projetil.gety(), 0, null);
			setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, projetil);
			projetil.setX(projetil.getxConflito());
			projetil.setY(projetil.getyConflito());
			}
	}
	
	//Recebe o tipo e posição do personagem, e com essa informação o instancia e o insere na posição solicitada
	@Override
	public void putPersonagem(int x, int y, int tipo) {
		switch(tipo) {
			case 1:
				vPersonagem[x][y][0] = new Arqueiro(x,y);
				setElemento(x, y, vPersonagem[x][y][0].getPecaIcon());
				break;
			case 2:
				vPersonagem[x][y][0] = new Lanceiro(x,y);
				setElemento(x, y, vPersonagem[x][y][0].getPecaIcon());
				break;
			case 3:
				vPersonagem[x][y][0] = new Mago(x,y);
				setElemento(x, y, vPersonagem[x][y][0].getPecaIcon());
				break;
			case 4:
				vPersonagem[x][y][0] = new Catapulta(x,y);
				setElemento(x, y, vPersonagem[x][y][0].getPecaIcon());
				break;
		}
		numeroSoldados += 1;
	}
	
	//Coloca o projetil já instanciado na posição solicitada.
	@Override
	public void putProjetil(int x, int y, int z, IProjetil Projetil) {
		vProjetil[x][y][z] = Projetil;
		setElemento(x,y, Projetil.getPecaIcon());
	}
	
	//Recebe a posição da peça a ser removida, a remove e diminui o número de soldados em campo em 1.
	@Override
	public void removePeca(int x, int y, int z) {
		if (vPersonagem[x][y][z] instanceof Dragao) {
			removeElemento(x,y, vPersonagem[x][y][z].getPecaIcon());
			removeElemento(dragonPosition[0]-1,dragonPosition[1]-1,compl1);
			removeElemento(dragonPosition[0]-1,dragonPosition[1],compl2);
			removeElemento(dragonPosition[0],dragonPosition[1]-1,compl3);
		}
		else {
			removeElemento(x,y, vPersonagem[x][y][z].getPecaIcon());
			vPersonagem[x][y][z] = null;
			numeroSoldados -= 1;
		}
	}
	
	//Se alguma das condições para o jogo terminar for cumprida, esse método é chamado para exibir a mensagem final e a vida do dragão ou princesa como 0 se tiverem morrido.
	public void finish() {
		if (vPersonagem[dragonPosition[0]][dragonPosition[1]][0].getVida() <= 0) {
			finish.setForeground(Color.GREEN);
			finish.setText("<html>"+"<center>"
				+"<b>VOCÊ GANHOU!</b>"+"<br/>"
				+"	Você conseguiu matar o dragão,"+"<br/>"
				+ "salvando a princesa "+"<br/>"
				+"e todo o reino!!!"+"<center>"+"</html>");
			vidaDragao.setText("0");
			princesaPosition[0]=-1;
		}
		else {
			if (vPersonagem[princesaPosition[0]][princesaPosition[1]][1].getVida()<=0) {
				removePeca(princesaPosition[0], princesaPosition[1], 1);
				vidaPrincesa.setText("0");
				finish.setForeground(Color.lightGray);
				finish.setText("<html>"+"<center>"
						+"<b>VOCÊ PERDEU!</b>"+"<br/>"
						+"A princesa morreu e levou"+"<br/>"
						+"consigo a alegria do reino."+"<br/>"
						+"O rei infartou ao saber."+"<center>"+"</html>");
				princesaPosition[0]=-1;	
			}else {
				finish.setForeground(Color.lightGray);
				finish.setText("<html>"+"<center>"
						+"<b>VOCÊ PERDEU!</b>"+"<br/>"
						+"Todos os soldados morreram e o"+"<br/>"
						+ "dragão pôde avançar pelo reino,"+"<br/>"
						+"causando caos e morte."+"<center>"+"</html>");
				princesaPosition[0]=-1;
			}
		}
		again.setVisible(true);
	}
	
	//Abaixo tem-se alguns métodos para retornar e modificar os atributos privados do Tabuleiro.

	@Override
	public IPersonagem getPeca(int x, int y, int z) {
		return vPersonagem[x][y][z];
	}
	
	@Override
	public void setPersonagem(int x, int y, int z, IPersonagem peca) {
		vPersonagem[x][y][z] = peca;
		if (peca != null)
			setElemento(x, y, peca.getPecaIcon());
	}
	
	@Override
	public IProjetil getProjetil(int x, int y, int z) {
		return vProjetil[x][y][z];
	}
	
	@Override 
	public void setProjetil(int x, int y,int z, IProjetil Projetil) {
		if (Projetil != null)
			setElemento(x,y, Projetil.getPecaIcon());
		else if (vProjetil[x][y][z] != null)	//Se houver algum projétil na posição para a qual ele quer se movimentar, é removida a imagem do destino primeiro.
			removeElemento(x, y, vProjetil[x][y][z].getPecaIcon());
		vProjetil[x][y][z] = Projetil;
	}
	
	@Override
	public int[] getDragonPosition() {
		return dragonPosition;
	}
	
	@Override
	public void setDragonPosition(int x, int y) {
		dragonPosition[0]=x;
		dragonPosition[1]=y;
	}
	
	@Override
	public int[] getPrincesaPosition() {
		return princesaPosition;
	}
	
	@Override
	public void setPrincesaPosition(int x, int y) {
		princesaPosition[0]=x;
		princesaPosition[1]=y;
	}
	
	public JLabel getVidaDragaoLabel() {
		return vidaDragao;
	}
	
	public JLabel getVidaPrincesaLabel() {
		return vidaPrincesa;
	}
	
	public JLabel getFinishLabel() {
		return finish;
	}
	
	public JButton getAgainButton() {
		return again;
	}
	
	public PainelTabuleiro getPanel() {
		return ((PainelTabuleiro)this);
	}
}
