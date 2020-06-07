package mateodragao.components;

import mateodragao.interfaces.IDataProvider;

public class DataProvider implements IDataProvider{
	private int pontos;
	private String[] pecaPosition; //pensar sobre o tamanho do vetor
	// IDEIA: ter uma variavel q guarda a informaçao atual
	private String[] atual;
	
	public DataProvider(int pontos) {
		this.pontos = pontos;
		pecaPosition = new String[100];
		atual = new String[3];
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
		
		switch(comando) {
			case 1:
		
			case 2:
			
			case 3:
			
			case 4:
			
			default:
				System.out.println("Comando Inválido!");
				break;
		}
	}
	
	public void removePersonagem() {
		//texto pedindo para inserir x e y
		
		//entra com as posiçoes x e y
		String x = "a";
		String y = "0";
		
		//passar por pecaPosition e ver se tem peca nessa posicao
		for(int i=1; i<pecaPosition.length; i+=3) {
			if (pecaPosition[i] == x && pecaPosition[i+1] == y) {
				setX(x);
				setY(y);
				setTipo("R"); //R de remocao
				switch(pecaPosition[i-1]) {
					case 1:
			
					case 2:
				
					case 3:
				
					case 4:
				
				}
			}
			else {
				System.out.println("Não há peça nessa posição!");
				removePersonagem();
			}
		}
	}
	
	public void setX(String x) {
		atual[1] = x;
	}
	
	public void setY(String y) {
		atual[2] = y;
	}
	
	public void setTipo(String tipo) {
		atual[0] = tipo;
	}
	
	public String[] getData() {
		return atual;
	}
	
	public void inserePontos(int valor) {
		pontos += valor;
	}
	
	public void removePontos(int valor) {
		pontos -= valor;
	}
}
