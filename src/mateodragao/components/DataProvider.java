package mateodragao.components;

import mateodragao.interfaces.IDataProvider;
import mateodragao.components.personagem.*;

public class DataProvider implements IDataProvider{
	private int pontos;
	private int pecaPosition[]; //pensar sobre o tamanho do vetor
	private int pecaPositionAtual[];// IDEIA: ter uma variavel q guarda a informaçao atual
	private int atual; //posiçao atual de pecaPosition
	
	public DataProvider(int pontos) {
		this.pontos = pontos;
		pecaPosition = new int[100];
		for (int i=0; i<100; i++) {
			pecaPosition[i] = 0;
		}
		pecaPositionAtual = new int[3];
		atual = 0;
	}
	
	public boolean insertData() {
		//texto mostrando os comandos, pontos
		
		//usuario insere o comando
		int comando = 0; //está zero, mas depois vai colocar a classe de entrada de dados
		
		/*dependendo do comando realiza um dos metodos abaixo
		 *retorna false caso deseja parar a inserção de dados*/
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
	
	public void inserePersonagem() {
		//texto com opçoes de personagem
		
		//dependendo do comando realiza um dos metodos abaixo
		int comando = 0;
		int x = 0;
		int y = 0;
		
		switch(comando) {
			case 1:
				if (Arqueiro.custo <= pontos) {
					removePontos(Arqueiro.custo);
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
				if (Lanceiro.custo <= pontos) {
					removePontos(Lanceiro.custo);
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
				if (Mago.custo <= pontos) {
					removePontos(Mago.custo);
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
				if (Catapulta.custo <= pontos) {
					removePontos(Catapulta.custo);
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
	
	public void removePersonagem() {
		//texto pedindo para inserir x e y
		
		//entra com as posiçoes x e y
		int x = 0;
		int y = 0;
		
		//passar por pecaPosition e ver se tem peca nessa posicao
		for(int i=1; i<pecaPosition.length; i+=3) {
			if (pecaPosition[i] == x && pecaPosition[i+1] == y) {
				pecaPositionAtual[0] = 0;
				pecaPositionAtual[1] = x;
				pecaPositionAtual[2] = y;
				
				switch(pecaPosition[i-1]) {
					case 1:
						inserePontos(Arqueiro.custo);
						break;
					case 2:
						inserePontos(Lanceiro.custo);
						break;
					case 3:
						inserePontos(Mago.custo);
						break;
					case 4:
						inserePontos(Catapulta.custo);
						break;
				}
				pecaPosition[i-1] = 0;
				atual = i-1;
				return;
			}

		System.out.println("Não há peça nessa posição!");
		}
	}
	
	public void setX(int x) {
		pecaPositionAtual[1] = x;
		pecaPosition[atual+1] = x;
	}
	
	public void setY(int y) {
		pecaPositionAtual[2] = y;
		pecaPosition[atual+2] = y;
	}
	
	public void setTipo(int tipo) {
		pecaPositionAtual[0] = tipo;
		pecaPosition[atual] = tipo;
	}
	
	public int[] getData() {
		return pecaPositionAtual;
	}
	
	public void inserePontos(int valor) {
		pontos += valor;
	}
	
	public void removePontos(int valor) {
		pontos -= valor;
	}
}
