package src.dataprovider;

import src.personagem.*;
import src.exceptions.*;

public class DataProvider implements IDataProvider{
	private int pontos;				//Quantidade de pontos disponível para serem gastos na inserção de personagens.
	private int pecaPosition[]; 	//Vetor que guarda a posição das peças que já foram colocadas em campo na forma {tipo, x, y, tipo, x, y...}.
	private int pecaPositionAtual[];//Guarda a solicitação atual de inserção ou remoção da peça em campo na forma {tipo,x,y}.
	private int atual; 				//Posiçao atual de pecaPosition.
	
	/*Inicia o objeto com a quantidade de pontos máxima determinada para se inserir personagens no campo
	 * e dois vetores auxiliares, o tamanho de pecaPosition considera a 
	 * quantidade máxima de personagens a serem inseridos, para não haver problemas.*/
	public DataProvider(int pontos) {
		this.pontos = pontos;	
		pecaPosition = new int[66];
		for (int i=0; i<66; i++) {
			pecaPosition[i] = 0;
		}
		pecaPositionAtual = new int[3];
		atual = 0;
	}
	
	/*Recebe o tipo de personagem que deve ser inserido e as coordenadas da posição, 
	 * dependendo da posição pode causar um dos erros.*/
	@Override
	public void inserePersonagem(int comando, int x, int y) throws AdicaoInvalida{
		//Se a posição for fora do campo:
		if (x<0 || x>19 || y<0 || y>19)
			throw new AdicaoLugarInexistente("Nao existe essa posicao!");
		//Se a posição for dentro de um certo raio do dragão, de 5 casas para cada direção:
		if ((x>=0 && x<=10) && (y>=4 && y<=15))
			throw new AdicaoLugarProibido("Você nao pode adicionar nesse lugar!");
		//Se a posição for a posição inicial da princesa:
		if (x==18 && y==10)
			throw new AdicaoLugarOcupado("Ja ha um personagem nessa posicao!");
		/*Se passar pelos outros erros, o vetor que guarda os personagens já inseridos e 
		 * suas posições é varrido de modo a ver se já tem um personagem nessa posição.*/
		for(int i=1; i<pecaPosition.length; i+=3) {
			if (pecaPosition[i] == x && pecaPosition[i+1] == y) {
				throw new AdicaoLugarOcupado("Ja ha um personagem nessa posicao!");
			}
		}
		
		/*Dependendo do tipo de personagem solicitado, ele é colocado no vetor que guarda todos os personagens já inseridos.
		 * E a quantidade de pontos de custo para inserir é reduzida do total disponível.*/
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
				//System.out.println("Comando Inválido!");
				break;
		}
	}
	
	/*Dependendo do tipo de personagem solicitado, ele é colocado no vetor que guarda todos os personagens já inseridos.
	 * E a quantidade de pontos de custo para inserir é reduzida do total disponível.*/
	@Override
	public void removePersonagem(int x, int y) throws RemocaoInvalida{
		//Se a posição for fora do campo:
		if (x<0 || x>19 || y<0 || y>19)
			throw new RemocaoLugarInexistente("Nao existe essa posicao!");
		if (pontos == 100)
			throw new RemocaoAntesDeAdicao("Voce deve adicionar pelo menos um personagem para poder fazer uma remocao!");
		if ((x==4 && y==9)||(x==4 && y==10)||(x==5 && y==9)||(x==5 && y==10))
			throw new RemocaoLugarDragao("Voce nao pode remover o dragao!");
		
		
		/*Passa por pecaPosition e vê se existe uma peca nessa posicao, se houver,
		 *ela é removida do vetor que guarda as peças já inseridas e os pontos são devolvidos.*/ 
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
				//Remove do vetor de personagen inseridos, colocando o tipo e as posições guardadas nele como 0.
				pecaPosition[i-1] = 0;
				pecaPosition[i] = 0;
				pecaPosition[i+1] = 0;
				atual = i-1;
				return;
			}
		}
		throw new RemocaoSemPersonagem("Nao há personagem removível nesse lugar!");
	}
	
	//Devolve o custo ao total de pontos após a remoção de um personagem.
	@Override
	public void inserePontos(int valor) {
		pontos += valor;
	}
	
	//Subtrai o custo do total de pontos após a inserção de um personagem.
	@Override
	public void removePontos(int valor) {
		pontos -= valor;
	}
	
	//Coloca tanto em pecaPosiotion quanto pecaPositionAtual o atributo x.
	@Override
	public void setX(int x) {
		pecaPositionAtual[1] = x;
		pecaPosition[atual+1] = x;
	}
	
	//Coloca tanto em pecaPosiotion quanto pecaPositionAtual o atributo y.
	@Override
	public void setY(int y) {
		pecaPositionAtual[2] = y;
		pecaPosition[atual+2] = y;
	}
	
	//Coloca tanto em pecaPosiotion quanto pecaPositionAtual o atributo tipo.
	@Override
	public void setTipo(int tipo) {
		pecaPositionAtual[0] = tipo;
		pecaPosition[atual] = tipo;
	}
	
	//Abaixo tem-se somente métodos para retornar e modificar os atributos privados do DataProvider.
	@Override
	public int[] getData() {
		return pecaPositionAtual;
	}
	
	public int getPontos() {
		return pontos;
	}
}
