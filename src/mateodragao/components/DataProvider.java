package mateodragao.components;

import mateodragao.interfaces.IDataProvider;
import mateodragao.components.personagem.*;
import mateodragao.exceptions.AdicaoInvalida;
import mateodragao.exceptions.AdicaoLugarInexistente;
import mateodragao.exceptions.AdicaoLugarOcupado;
import mateodragao.exceptions.AdicaoLugarProibido;
import mateodragao.exceptions.AdicaoPontosInsuficientes;
import mateodragao.exceptions.RemocaoAntesDeAdicao;
import mateodragao.exceptions.RemocaoInvalida;
import mateodragao.exceptions.RemocaoLugarDragao;
import mateodragao.exceptions.RemocaoLugarInexistente;
import mateodragao.exceptions.RemocaoSemPersonagem;

public class DataProvider implements IDataProvider{
	private int pontos;				//quantidade de pontos disponível para serem gastos na inserção de personagens
	private int pecaPosition[]; 	//vetor que guarda a posição das peças que já foram colocadas em campo
	private int pecaPositionAtual[];//guarda a solicitação atual de inserção de peça em campo
	private int atual; //posiçao atual de pecaPosition
	
	//inicia o objeto com a quantidade de pontos máxima determinada e dois vetores auxiliares, o tamanho de pecaPosition considera a quantidade máxima de personagens a serem inseridos, para não haver problemas
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
		
		int comando = 3; //está tres, mas depois vai colocar a classe de entrada de dados
		
		/*dependendo do comando realiza um dos metodos abaixo
		 *retorna false caso deseja parar a inserção de dados*/
		switch(comando) {
			case 1:
				//inserePersonagem();
				return true;
			case 2:
				//removePersonagem();
				return true;
			case 3:
				return false;
			default:
				System.out.println("Comando Inválido!");
				return insertData();
		}
	}
	
	@Override
	public void inserePersonagem(int comando, int x, int y) throws AdicaoInvalida{
		//texto com opçoes de personagem
		if (x<0 || x>19 || y<0 || y>19)
			throw new AdicaoLugarInexistente("Nao existe essa posicao!");
		if (x<5)
			throw new AdicaoLugarProibido("Voce nao pode adicionar nesse lugar!");
		if (x==19 && y==10)
			throw new AdicaoLugarOcupado("Já há um personagem nessa posicao!");
		//dependendo do comando realiza um dos metodos abaixo
		for(int i=1; i<pecaPosition.length; i+=3) {
			if (pecaPosition[i] == x && pecaPosition[i+1] == y) {
				throw new AdicaoLugarOcupado("Já há um personagem nessa posicao!");
			}
		}
		
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
					//removePontos(Arqueiro.custo);
				}
				else
					throw new AdicaoPontosInsuficientes("Pontos Insuficientes!");
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
					throw new AdicaoPontosInsuficientes("Pontos Insuficientes!");
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
					throw new AdicaoPontosInsuficientes("Pontos Insuficientes!");
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
					throw new AdicaoPontosInsuficientes("Pontos Insuficientes!");
				break;
			default:
				System.out.println("Comando Inválido!");
				break;
		}
	}
	
	//passa por pecaPosition e vê se existe uma peca nessa posicao, se houver, ela é removida do vetor que guarda as peças já inseridas e os pontos são devolvidos 
	@Override
	public void removePersonagem(int x, int y) throws RemocaoInvalida{
		//texto pedindo para inserir x e y
		if (x<0 || x>19 || y<0 || y>19)
			throw new RemocaoLugarInexistente("Nao existe essa posicao!");
		if (pontos == 100)
			throw new RemocaoAntesDeAdicao("Voce deve adicionar pelo menos um personagem para poder fazer uma remocao!");
		if ((x==0 && y==9)||(x==0 && y==10)||(x==1 && y==9)||(x==1 && y==10))
			throw new RemocaoLugarDragao("Voce nao pode remover o dragao!");
		
		
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
				pecaPosition[i] = 0;
				pecaPosition[i+1] = 0;
				atual = i-1;
				return;
			}
		}
		throw new RemocaoSemPersonagem("Nao há personagem removível nesse lugar!");
	}
	
	//devolve o custo aos pontos após a remoção de uma peça
	@Override
	public void inserePontos(int valor) {
		pontos += valor;
	}
	
	//subtrai o custo dos pontos após a inserção de uma peça
	@Override
	public void removePontos(int valor) {
		pontos -= valor;
	}
	
	//abaixo tem-se alguns métodos para retornar e modificar os atributos privados do DataProvider
	
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
	
	public int getPontos() {
		return pontos;
	}
}
