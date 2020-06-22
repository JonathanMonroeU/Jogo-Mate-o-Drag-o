package mateodragao.components;

import mateodragao.interfaces.IDataProvider;
import mateodragao.components.personagem.*;

public class DataProvider implements IDataProvider{
	private int pontos;
	private int pecaPosition[]; 
	private int pecaPositionAtual[];
	private int atual; //posiçao atual de pecaPosition
	
	public DataProvider(int pontos) {
		this.pontos = pontos;
		pecaPosition = new int[66];
		for (int i=0; i<66; i++) {
			pecaPosition[i] = 0;
		}
		pecaPositionAtual = new int[3];
		atual = 0;
	}
	
	@Override
	public boolean insertData() {
		//texto mostrando os comandos, pontos
		
		//usuario insere o comando
		int comando = 3; //está tres, mas depois vai colocar a classe de entrada de dados
		
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
	
	@Override
	public void inserePersonagem() {
		//texto com opçoes de personagem
		
		//dependendo do comando realiza um dos metodos abaixo
		int comando = 0;
		int x = 0;
		int y = 0;
		
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
	
	@Override
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
	
	@Override
	public void inserePontos(int valor) {
		pontos += valor;
	}
	
	@Override
	public void removePontos(int valor) {
		pontos -= valor;
	}
}
