package mateodragao.components;

import mateodragao.interfaces.IDataProvider;
import mateodragao.components.personagem.*;

public class DataProvider implements IDataProvider{
	private int pontos;
	private int pecaPosition[]; 			//vetor com todos os tipos e posições dos personagens já inseridos
	private int pecaPositionAtual[];		//vetor com os dados de inserção e remoção atuais
	private int atual;						//posiçao atual de pecaPosition
	 
	public DataProvider(int pontos) {
		this.pontos = pontos;			//inicia com a quantidade máxima de pontos
		pecaPosition = new int[66];		//tamanho considerando a quantidade máxima de personagens que pode ser inserida
		for (int i=0; i<66; i++) {
			pecaPosition[i] = 0;		//0 indica que não há um personagem inserido naquela posição do vetor
		}
		pecaPositionAtual = new int[3];
		atual = 0;
	}

	@Override
	public boolean insertData() {
		//TEXTO MOSTRANDO OS COMANDOS, PONTOS
		
		//usuário insere o comando
		int comando = 3; //ESTÁ 3, MAS DEPOIS VAI  COLOCAR A CLASSE DE ENTRADA DE DADOS
	
		
		/*dependendo do comando, é realizado um dos metodos abaixo
		 retorna false caso o usuário deseje parar a inserção de dados*/
		switch(comando) {
			case 1:
				inserePersonagem();
				return true;
			case 2:
				removePersonagem();
				return true;
			case 3:
				return false;
			default:
				System.out.println("Comando Inválido!");
				return insertData();
		}
	}
	
	//nesse método é inserido um personagem em campo baseado nos dados inseridos pelo jogador
	@Override
	public void inserePersonagem() {
		//TEXTO COM OPCOES DE PERSONAGEM
		
		
		
		//usuário insere o tipo e a posição do personagem que quer inserir
		int comando = 0; //NÃO SERÁ 0
		int x = 0;
		int y = 0;
		
		/*para cada personagem inserido abaixo, se os pontos restantes forem suficientes, são subtraídos os pontos 
		 * necessários para inserção, e são inseridos em dois vetores auxiliares os dados de tipo e posição do personagem*/
		
		//dependendo do tipo realiza um dos metodos abaixo
		switch(comando) {
			case 1:
				if (Arqueiro.getCusto() <= pontos) {
					removePontos(Arqueiro.getCusto());
					setX(x);
					setY(y);
					setTipo(1);
					while (pecaPosition[atual] != 0) {
						atual += 3;
					}
				}
				else
					System.out.println("Pontos Insuficientes!");
				break;
			case 2:
				if (Lanceiro.getCusto() <= pontos) {
					removePontos(Lanceiro.getCusto());
					setX(x);
					setY(y);
					setTipo(2);
					while (pecaPosition[atual] != 0) {
						atual += 3;
					}
				}
				else
					System.out.println("Pontos Insuficientes!");
				break;
			case 3:
				if (Mago.getCusto() <= pontos) {
					removePontos(Mago.getCusto());
					setX(x);
					setY(y);
					setTipo(3);
					while (pecaPosition[atual] != 0) {
						atual += 3;
					}
				}
				else
					System.out.println("Pontos Insuficientes!");
				break;
			case 4:
				if (Catapulta.getCusto() <= pontos) {
					removePontos(Catapulta.getCusto());
					setX(x);
					setY(y);
					setTipo(4);
					while (pecaPosition[atual] != 0) {
						atual += 3;
					}
				}
				else
					System.out.println("Pontos Insuficientes!");
				break;
			default:
				System.out.println("Comando Inválido!");
				break;
		}
	}

	//nesse método é removido um personagem do campo baseado nos dados inseridos pelo jogador
	@Override
	public void removePersonagem() {
		//TEXTO PEDINDO PARA INSERIR X E Y
		
		//jogador insere as posiçoes x e y
		int x = 0;
		int y = 0;
		
		/*o vetor pecaPosition, que armazena os personagens que foram escolhidos para entrarem em campo e suas posições, 
		é percorrido em busca do personagem que está na posição onde foi solicitada a remoção do mesmo*/
		for(int i=1; i<pecaPosition.length; i+=3) {
			if (pecaPosition[i] == x && pecaPosition[i+1] == y) {
				pecaPositionAtual[0] = 0;
				pecaPositionAtual[1] = x;
				pecaPositionAtual[2] = y;
				
				switch(pecaPosition[i-1]) {
					case 1:
						inserePontos(Arqueiro.getCusto());
						break;
					case 2:
						inserePontos(Lanceiro.getCusto());
						break;
					case 3:
						inserePontos(Mago.getCusto());
						break;
					case 4:
						inserePontos(Catapulta.getCusto());
						break;
				}
				pecaPosition[i-1] = 0;
				atual = i-1;
				return;
			}

		System.out.println("Não há peça nessa posição!");
		}
	}
	
	/*setX, setY e setTipo abaixo inserem os dados colocados pelo jogador nos vetores pecaPositionAtual e pecaPosition, que têm o seguinte padrão:
	 pecaPositionAtual= {tipo, x, y}, pecaPosition={tipo,x,y, tipo2,x2,y2, tipo3,x3,y3...} */
	@Override
	public void setX(int x) {
		pecaPositionAtual[1] = x;
		pecaPosition[atual+1] = x;
	}
	
	@Override
	public void setY(int y) {
		pecaPositionAtual[2] = y;
		pecaPosition[atual+2] = y;
	}
	
	@Override
	public void setTipo(int tipo) {
		pecaPositionAtual[0] = tipo;
		pecaPosition[atual] = tipo;
	}

	@Override
	public int[] getData() {
		return pecaPositionAtual;
	}
	
	//aumenta os pontos restantes quando um personagem é removido
	@Override
	public void inserePontos(int valor) {
		pontos += valor;
	}
	
	//diminui os pontos restantes quando um personagem é inserido
	@Override
	public void removePontos(int valor) {
		pontos -= valor;
	}
}
