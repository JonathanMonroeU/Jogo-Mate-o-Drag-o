package src.personagem;

import java.util.Random;

import src.projetil.BolaDeFogo;
import src.personagem.IPersonagem;
import src.projetil.IProjetil;
import src.tabuleiro.ITabuleiro;

public abstract class Personagem extends PecaIcon implements IPersonagem {
	private static final long serialVersionUID = 5890715371330885791L;
	protected int x, y, 
					vida,	//Quantidade de pontos que tem para resistir a danos de projéteis.
					newX,	//Nova poosição x que foi calculada.
					newY, 	//Nova posição y que foi calculada.
					frequencia, //Quantos passos demora até disparar um novo projétil.
					freqA,		//Valor que vai de 0 até a frequência, ciclicamente, quando for 0, o personagem dispara. O lanceiro é exceçõ, que dispara quando for 0 e 12.
					movimento,	//É a quantidade de passos do jogo que demora até o personagem se mover de novo.
					freqM, 		//Valor que vai de 0 até movimento, ciclicamente, quando for 0, o personagem se move.
					passo; 		//Quantidade de passos que o personagem anda no campo por movimento.
					
	protected boolean jaAgiu; 	//Se for igual a false, o projétil ainda não agiu naquele tempo, se for true já. 
	
	protected Random alea=new Random();
	
	public Personagem(String caminho, int x, int y) {
		super(caminho);
		this.x = x;
		this.y = y;
		freqM = 0;	
		freqA = 0;
		jaAgiu=false;
	}
	
	//Encontra a nova posição para o personagem e o move para ela.
	@Override
	public void move(ITabuleiro tab) {	
		
		int tentativas=0;	//Número de tentativas de se mover começa zerado, se passar de 30 provavelmente é porque está encurralado, então deixa para tentar se mover no próximo passo do jogo.
		if (freqM == 0) {	//Quando está zero, é a vez do personagem se mover.
			newX = x;
			newY = y;
			
			while ( (tab.getPeca(newX, newY,0) != null || tab.getPeca(newX, newY, 1) != null) && tentativas<=30) {	//Fica no loop enquanto não acha uma nova posição vazia e ainda não passou do máximo de tentativas.
				tentativas+=1;
				
				//Valor entre -1,0 e 1 a ser adicionado multiplicado pelo passo e/ou subtraido aleatoriamente em x e y.
				int addX = alea.nextInt(3)-1;	
				int addY = alea.nextInt(3)-1;
				
				//Se não for o dragão, testa se a nova posição é coerente para o soldado:
				if (!(this instanceof Dragao)) { 
					newX += passo*addX;	
					newY += passo*addY; 
					//Se a nova posição estiver fora do campo, reinicia e volta do inicio do while.
					if(newX<0 || newX>19 || newY<0 || newY>19) {
						newX = x;
						newY = y;
						continue;
					//Se a nova posição estiver a uma distância maior ou igual a mínima em relação ao dragão, que é até 4 casas, volta para o início do while, que testa se ela está vazia.
					}if (newX-tab.getDragonPosition()[0]<=-5||newX-tab.getDragonPosition()[0]>=4
					||newY-tab.getDragonPosition()[1]<=-5||newY-tab.getDragonPosition()[1]>=4) {	
						continue;
					//Se não estiver a uma distância segura do dragão de mais de 3 casas, reinicia e volta para o início do while.
					}else {
						newX = x;
						newY = y;
						continue;
					}
				}	
				//Se for o dragão, testa se a nova posição é coerente para o dragão:
				if (this instanceof Dragao) { 
					//O dragão só se move em x ou y a cada passo, então é escolhido aleatoriamente um dos dois para ser modificado.
					int a=alea.nextInt(2);
					if (a==0) {
						newX += passo*addX;		
					}else {		
						newY += passo*addY;		
					//Se a nova posição estiver fora do campo, reinicia e volta do início do while.
					}if(newX<1 || newX>19 || newY<1 || newY>19) {
						newX = x;
						newY = y;
						continue;
					//Se a nova posição estiver vazia ou for parte do prṕrio dragão, para as 4 posições que o compõe, a nova posição é válida e sai do while imediatamente.
					}if ( ( (tab.getPeca(newX, newY, 0)==null && (tab.getPeca(newX, newY, 1)==null) )|| tab.getPeca(newX, newY, 0)==this) &&
						(  (tab.getPeca(newX-1, newY,0)==null && (tab.getPeca(newX-1, newY,1)==null) )|| tab.getPeca(newX-1, newY, 0)==this) &&
					 ( (tab.getPeca(newX, newY-1, 0)==null &&  (tab.getPeca(newX, newY-1, 1)==null) )|| tab.getPeca(newX, newY-1, 0)==this )&&
					 ( (tab.getPeca(newX-1, newY-1, 0)==null && (tab.getPeca(newX-1, newY-1, 1)==null) )|| tab.getPeca(newX-1, newY-1, 0)==this) ) {
						break;
					//Se não, reinicia e volta do início do while.	
					}else {
						newX = x;
						newY = y;
						continue;
						
					}
				}
			}
			/*Se achar a nova posição tiver sido tentado menos de 30 vezes, ou seja, passado por dentro do while 
			 * e sido aprovada, a posição atual vira null e o personagem é colocado na nova posição*/
			if (tentativas<=30) {
				tab.setPersonagem(x, y, 0, null);
					
				//Se for o dragão, as outras 3 posições que o compõem tem que ser ajustadas também.
				if (this instanceof Dragao) {
					tab.setDragonPosition(newX,newY);
					tab.setPersonagem(x-1, y, 0, null);	
					tab.setPersonagem(x, y-1, 0, null);
					tab.setPersonagem(x-1, y-1,0, null);
					
					tab.setPersonagem(newX-1, newY, 0, this);
					tab.setPersonagem(newX, newY-1, 0, this);	
					tab.setPersonagem(newX-1, newY-1, 0, this);
				}
				tab.setPersonagem(newX, newY, 0, this);
				this.jaAgiu=true;
				
				//Os atributos x e y do objeto são atualizados.
				x = newX;
				y = newY;
			}
		}//A frequência de movimento é atualizada se mover o personagem tiver sido um sucesso, para ele se mover novamente quando seu valor voltar a 0.
		if (tentativas<=30) 
			freqM = (freqM + 1)%movimento;
	}
	
	//Pega o dano do projetil e subtrai na vida do personagem que está naquela posição, se o ataque for inimigo. No caso da princesa, ela pode levar dano de qualquer projétil. 
	@Override
	public void perdeVida(IProjetil projetil) {
		if ( (this instanceof Dragao || this instanceof Princesa) && !(projetil instanceof BolaDeFogo)) {
			vida -= projetil.getDano();
		}else if (!(this instanceof Dragao) && projetil instanceof BolaDeFogo)
			vida -= projetil.getDano();
	}

	//Método implementado por cada subclasse.
	@Override
	public abstract void disparaProjetil(ITabuleiro tab);

	
	//Abaixo tem-se alguns métodos para retornar e modificar os atributos privados do Personagem.
	
	@Override
	public boolean getJaAgiu(){
		return jaAgiu;
	}
	
	@Override
	public void setJaAgiu(boolean j) {
		jaAgiu=j;
	}
	
	@Override
	public int getVida() {
		return vida;
	}
	
	@Override
	public int getMovimento() {
		return movimento;
	}
	
	public PecaIcon getPecaIcon() {
		return ((PecaIcon)this);
		
	}
	@Override
	public void movePrincesa(String direcao) {
	}
}
