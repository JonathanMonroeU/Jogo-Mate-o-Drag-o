package mateodragao.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import mateodragao.Metronomo;
import mateodragao.PainelTabuleiro;
import mateodragao.PecaIcon;
import mateodragao.components.personagem.Arqueiro;
import mateodragao.components.personagem.Catapulta;
import mateodragao.components.personagem.Dragao;
import mateodragao.components.personagem.Lanceiro;
import mateodragao.components.personagem.Mago;
import mateodragao.components.personagem.Princesa;
import mateodragao.exceptions.SemPersonagem;
import mateodragao.interfaces.IDataProvider;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;

public class Tabuleiro extends PainelTabuleiro implements ITabuleiro, ActionListener{
	private static final long serialVersionUID = -4923736996545875913L;
	public static String DIRETORIO = Dragao.class.getResource(".").getPath();
	private IPersonagem vPersonagem[][][];		//matriz para guardar os personagens nas suas devidas posições no campo
	private IProjetil vProjetil[][][];			//matriz de projeteis lançados pelos personagens, com 3 dimensões pois a terceira serve para guardar uma segunda camada de projeteis, que são as pedras de catapultas 
	private IProjetil vConflito[];				//vetor que guarda os projeteis que estão na mesma posição, para comparar quem tem o maior dano
	private int DragonPosition[];				//guarda as posições x,y do dragão, para que sejam acessíveis a todos os outros personagens
	private int PrincesaPosition[];
	private int numeroSoldados;					//quantidade de soldados inseridos pelo jogador no momento
	private int atual;							
	private Metronomo metro = new Metronomo(20);	//metronomo definindo o tempo para ativação de cada modificação do campo
	private PecaIcon compl1,compl2,compl3;
	
	public Tabuleiro() {
		super();
		vProjetil = new IProjetil[20][20][2];
		vConflito = new IProjetil [20];
		
		vPersonagem = new IPersonagem[20][20][2];
		DragonPosition = new int[2];
		PrincesaPosition=new int[2];
		numeroSoldados = 0;
		
		vPersonagem[1][10][0] = new Dragao(1,10);					//4 posições vizinhas do tabuleiro apontam para o dragão
		vPersonagem[0][10][0]=vPersonagem[1][10][0];
		vPersonagem[0][9][0]=vPersonagem[1][10][0];
		vPersonagem[1][9][0]=vPersonagem[1][10][0];
		
		compl1 = new PecaIcon(DIRETORIO+"dragao1.png");
		compl2 = new PecaIcon(DIRETORIO+"dragao2.png");
		compl3 = new PecaIcon(DIRETORIO+"dragao3.png");
		setElemento(1,10,(PecaIcon) vPersonagem[1][10][0]);
		setElemento(0,9,compl1);
		setElemento(0,10,compl2);
		setElemento(1,9,compl3);
		
		DragonPosition[0] = 1;									
		DragonPosition[1] = 10;
		atual=-1;
		
		vPersonagem[19][10][1] = new Princesa(19,10, this);	
		PrincesaPosition[0] = 19;									
		PrincesaPosition[1] = 10;
		setElemento(19,10,(PecaIcon) vPersonagem[19][10][1]);
		
		vida = new JLabel(Integer.toString(vPersonagem[DragonPosition[0]][DragonPosition[1]][0].getVida()));
		vida.setAlignmentX(CENTER_ALIGNMENT);
		vida.setHorizontalAlignment(0);
		vida.setMaximumSize(new Dimension(50,30));
		vida.setBorder(BorderFactory.createLineBorder(Color.black));
		
		finish = new JLabel();
		finish.setAlignmentX(CENTER_ALIGNMENT);
		finish.setHorizontalAlignment(0);
		finish.setMaximumSize(new Dimension(300,100));
		finish.setForeground(Color.RED);
		
		metro.addActionListener(this);
		
		
	}
	
	//método que inicia o metrônomo que cadencia os passos do jogo
	@Override
	public void play() throws SemPersonagem{
		if (numeroSoldados == 0)
			throw new SemPersonagem("Voce nao pode comecar o jogo sem personagens!");
		metro.start();
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (PrincesaPosition[0]!=-1) {  //-1 indica que a princesa está morta
	                if (event.getKeyCode()== KeyEvent.VK_UP) {
	                	vPersonagem[PrincesaPosition[0]][PrincesaPosition[1]][1].movePrincesa("up");
	            	}
	                if (event.getKeyCode()== KeyEvent.VK_LEFT) {
	                	vPersonagem[PrincesaPosition[0]][PrincesaPosition[1]][1].movePrincesa("left");
	                }
	                if (event.getKeyCode()== KeyEvent.VK_RIGHT) {
	                	vPersonagem[PrincesaPosition[0]][PrincesaPosition[1]][1].movePrincesa("right");
	                }
	                if (event.getKeyCode()== KeyEvent.VK_DOWN) {
	                	vPersonagem[PrincesaPosition[0]][PrincesaPosition[1]][1].movePrincesa("down");
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
        });
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	//a cada passo do metrônomo, se nem todos os soldados tiverem morrido, nem o dragão, então o jogo continua
	public void actionPerformed(ActionEvent e) {
		if (numeroSoldados != 0 && vPersonagem[DragonPosition[0]][DragonPosition[1]][0].getVida()>0 && vPersonagem[PrincesaPosition[0]][PrincesaPosition[1]][1].getVida()>0) {
			modificaTabuleiro();
		}
		else {
			finish();
			metro.stop();
		}
	}

	//
	@Override
	public void modificaTabuleiro() {
		System.out.println("----------------------------");
		
		//o ddragão é o primeiro a disparar seu projétil e se mover
		vPersonagem[DragonPosition[0]][DragonPosition[1]][0].disparaProjetil(this);
		vPersonagem[DragonPosition[0]][DragonPosition[1]][0].move(this);
		/*essa linha é pra ajudar*/ //setElemento(DragonPosition[0],DragonPosition[1],(PecaIcon)vPersonagem[DragonPosition[0]][DragonPosition[1]]);
		setElemento(DragonPosition[0]-1,DragonPosition[1]-1,compl1);
		setElemento(DragonPosition[0]-1,DragonPosition[1],compl2);
		setElemento(DragonPosition[0],DragonPosition[1]-1,compl3);
		
		
		//primeira passagem por todas as posições do tabuleiro para mover personagens e projeteis e disparar projeteis
		for (int i=0; i<20; i++) {
			for (int j=0; j<20; j++) {
				if (vPersonagem[i][j][0] != null) {
					//se o personagem não for o dragão e não tiver agido ainda nesse tempo do jogo:
					if (vPersonagem[i][j][0] instanceof Dragao==false && vPersonagem[i][j][0].getJaAgiu()==0) {	
						vPersonagem[i][j][0].setJaAgiu(1);
						if(vProjetil[i][j][0]==null && vPersonagem[i][j][0] instanceof Catapulta==false || vProjetil[i][j][1]==null && vPersonagem[i][j][0] instanceof Catapulta)
							vPersonagem[i][j][0].disparaProjetil(this);
						if(vPersonagem[i][j][0].getMovimento()!=0)
							vPersonagem[i][j][0].move(this);
					}
				}
				//se houver um projétil na posição atual e ele não tiver agido ainda nesse tempo, ele é movido
				if (vProjetil[i][j][0] != null) {
					if (vProjetil[i][j][0].getJaAgiu()==0)
						vProjetil[i][j][0].move(this);
					//se houver um projétil na posição atual e ele não tiver agido ainda nesse tempo, ele é movido	
				}if (vProjetil[i][j][1] != null) {
					if (vProjetil[i][j][1].getJaAgiu()==0)
						vProjetil[i][j][1].move(this); 
				}
			}
		}
		
		//passagem pelo vetor de conflitos de projeteis, se houver algum conflito no vetor, ele é percorrido e os conflitos são resolvidos pelo método resolveConflito
		if (atual>-1) {
			for (int i=0;i<=atual;i++) {
				resolveConflito(vConflito[i]);
			}
			atual=-1;
		}
		
		//segunda passagem por todo o tabuleiro para analisar projeteis que acertaram personagens
		for (int i=0; i<20; i++) {
			for (int j=0; j<20; j++) {
				//se houver um pesonagem e um projetil na mesma posição é ativado o método que checa se ele deve perder vida
				if (vPersonagem[i][j][0] != null && vProjetil[i][j][0]!=null) { 
					vPersonagem[i][j][0].perdeVida(vProjetil[i][j][0],this);
					setProjetil(i, j, 0, null);
				}
				//se houver um pesonagem e um projetil na mesma posição é ativado o método que checa se ele deve perder vida
				if (vPersonagem[i][j][0] != null && vProjetil[i][j][1]!=null) {
					vPersonagem[i][j][0].perdeVida(vProjetil[i][j][1],this);
					setProjetil(i, j, 1, null);
				}
				//se a princesa estiver na posição do ataque
				if (vPersonagem[i][j][1] != null && vProjetil[i][j][0]!=null) {
					vPersonagem[i][j][1].perdeVida(vProjetil[i][j][0],this);
					setProjetil(i, j, 0, null);
				}
				
				//se houver um personagem na posição, é reiniciado o atributo que diz que ele já agiu no tempo, para estar 0 no pŕoximo tempo do jogo
				if (vPersonagem[i][j][0] != null) {
					vPersonagem[i][j][0].setJaAgiu(0);
					//se a vida for menor que 1, o personagem morreu e é removido de campo
					if (vPersonagem[i][j][0].getVida()<=0) 
						removePeca(i,j,0); 	//morte
				}
				//se houver um projetil na posição, é reiniciado o atributo que diz que ele já agiu no tempo, para estar 0 no pŕoximo tempo do jogo
				if (vProjetil[i][j][0] != null) 
					vProjetil[i][j][0].setJaAgiu(0);
						
				//se houver um projetil na posição, é reiniciado o atributo que diz que ele já agiu no tempo, para estar 0 no pŕoximo tempo do jogo
				if (vProjetil[i][j][1] != null) 
					vProjetil[i][j][1].setJaAgiu(0);
				
			}
		}
		vida.setText(Integer.toString(vPersonagem[DragonPosition[0]][DragonPosition[1]][0].getVida()));
		
	}

	@Override
	public void mostraTabuleiro() {
		
		
	}
	
	//pega os dados armazenados na vetor pecaPositionAtual do dataProvider para fazer a remoção ou inserção do personagem no campo, dependendo dasinformações contidas nele
	@Override
	public void receiveData(IDataProvider dataProvider) {
		int position[] = dataProvider.getData();
		if (position[0] == 0)  //zero é remocao
			removePeca(position[1],position[2], 0);
		else
			putPeca(position[1],position[2],position[0]);
	}
	
	//adiciona o conflito no vetor de conflitos de projeteis a serem resolvidos depois que todo o campo for percorrido a primeira vez em modificaTabuleiro
	@Override
	public void adicionaConflito(IProjetil projetil) {
		atual+=1;
		vConflito[atual]=projetil;
		projetil.setEmConflito(1);
	}
	
	//resolve o conflito da posição atual do vetor de conflitos de projeteis
	public void resolveConflito (IProjetil projetil) {
		//se a posição para o qual o projetil quer se mover ainda estiver ocupada:
		if(vProjetil[projetil.getxConflito()][projetil.getyConflito()][0]!=null) {
			//se o dano do projetil for maior do que o que está na posição para o qual ele quer se mover, ele se move ocupando o lugar do outro
			if  (projetil.getDano()>vProjetil[projetil.getxConflito()][projetil.getyConflito()][0].getDano()) {
				projetil.setEmConflito(0);
				projetil.setJaAgiu(1);		System.out.println("rescon dano:"+projetil.getDano()+" newX:"+projetil.getxConflito()+" newY:"+projetil.getyConflito());
				
				setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, null);
				setProjetil(projetil.getX(), projetil.getY(), 0, null);	
				setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, projetil);
				projetil.setX(projetil.getxConflito());
				projetil.setY(projetil.getyConflito());
			}
			//caso o dano seja menor ou igual, ele some
			else
				setProjetil(projetil.getX(), projetil.getY(), 0, null);
		//caso a posição para o qual ele quer se mover já esteja vazia, ele se move
		}else {
			projetil.setEmConflito(0);
			projetil.setJaAgiu(1);		System.out.println("rescon dano:"+projetil.getDano()+" newX:"+projetil.getxConflito()+" newY:"+projetil.getyConflito());
			
			setProjetil(projetil.getX(), projetil.getY(), 0, null);
			setProjetil(projetil.getxConflito(), projetil.getyConflito(), 0, projetil);
			projetil.setX(projetil.getxConflito());
			projetil.setY(projetil.getyConflito());
			}
	}
	
	//recebe o tipo e posição do personagem, e com essa informação o instancia e o insere na posição solicitada
	@Override
	public void putPeca(int x, int y, int tipo) {
		switch(tipo) {
			case 1:
				vPersonagem[x][y][0] = new Arqueiro(x,y);
				setElemento(x, y, (PecaIcon) vPersonagem[x][y][0]);
				break;
			case 2:
				vPersonagem[x][y][0] = new Lanceiro(x,y);
				setElemento(x, y, (PecaIcon) vPersonagem[x][y][0]);
				break;
			case 3:
				vPersonagem[x][y][0] = new Mago(x,y);
				setElemento(x, y, (PecaIcon) vPersonagem[x][y][0]);
				break;
			case 4:
				vPersonagem[x][y][0] = new Catapulta(x,y);
				setElemento(x, y, (PecaIcon) vPersonagem[x][y][0]);
				break;
		}
		numeroSoldados += 1;
	}
	
	//coloca o projetil já instanciado na posição solicitada
	@Override
	public void putProjetil(int x, int y, int z, IProjetil Projetil) {
		vProjetil[x][y][z] = Projetil;
		setElemento(x,y,(PecaIcon) Projetil);
	}
	
	//recebe a posição da peça a ser removida, a remove e diminui o número de soldados em campo em 1
	@Override
	public void removePeca(int x, int y, int z) {
		if (vPersonagem[x][y][z] instanceof Dragao) {
			removeElemento(x,y, (PecaIcon) vPersonagem[x][y][z]);
			removeElemento(DragonPosition[0]-1,DragonPosition[1]-1,compl1);
			removeElemento(DragonPosition[0]-1,DragonPosition[1],compl2);
			removeElemento(DragonPosition[0],DragonPosition[1]-1,compl3);
		}
		else {
			removeElemento(x,y, (PecaIcon) vPersonagem[x][y][z]);
			vPersonagem[x][y][z] = null;
			numeroSoldados -= 1;
		}
	}
	
	//abaixo tem-se alguns métodos para retornar e modificar os atributos privados do Tabuleiro
	
	@Override
	public IPersonagem getPeca(int x, int y, int z) {
		return vPersonagem[x][y][z];
	}
	
	@Override
	public void setPeca(int x, int y, int z, IPersonagem peca) {
		vPersonagem[x][y][z] = peca;
		if (peca != null)
			setElemento(x, y, (PecaIcon) peca);
	}
	
	@Override
	public IProjetil getProjetil(int x, int y, int z) {
		return vProjetil[x][y][z];
	}
	
	@Override 
	public void setProjetil(int x, int y,int z, IProjetil Projetil) {
		if (Projetil != null)
			setElemento(x,y,(PecaIcon) Projetil);
		else if (vProjetil[x][y][z] != null)
			removeElemento(x, y, (PecaIcon) vProjetil[x][y][z]);
		vProjetil[x][y][z] = Projetil;
	}
	
	@Override
	public int[] getDragonPosition() {
		return DragonPosition;
	}
	
	@Override
	public void setDragonPosition(int x, int y) {
		DragonPosition[0]=x;
		DragonPosition[1]=y;
	}
	
	@Override
	public int[] getPrincesaPosition() {
		return PrincesaPosition;
	}
	
	@Override
	public void setPrincesaPosition(int x, int y) {
		PrincesaPosition[0]=x;
		PrincesaPosition[1]=y;
	}
	
	@Override
	public Metronomo getMetro() {
		return metro;
	}
	
	public PainelTabuleiro getPanel() {
		return ((PainelTabuleiro)this);
	}
	
	public void finish() {
		if (vPersonagem[DragonPosition[0]][DragonPosition[1]][0].getVida() <= 0) {
			finish.setText("Você Ganhou!");
			vida.setText("0");
		}
		else {
			if (vPersonagem[PrincesaPosition[0]][PrincesaPosition[1]][1].getVida()<=0) {
				removePeca(PrincesaPosition[0], PrincesaPosition[1], 1);
				PrincesaPosition[0]=-1;
			}
			finish.setText("Você Perdeu!");
		}
	}
	
}
