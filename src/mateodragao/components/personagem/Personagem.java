package mateodragao.components.personagem;

import java.util.Random;

import mateodragao.PecaIcon;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.IProjetil;
import mateodragao.interfaces.ITabuleiro;

public abstract class Personagem extends PecaIcon implements IPersonagem {
	private static final long serialVersionUID = 5890715371330885791L;
	protected int x, y, vida, freqM, freqA,newX,newY, frequencia, movimento, passo, jaAgiu; //jaAgiu indica se o personagem já fez seu movimento naquele tempo do jogo
	protected Random alea=new Random();
	
	public Personagem(String caminho, int x, int y) {
		super(caminho,x,y);
		this.x = x;
		this.y = y;
		freqM = 0;	
		freqA = 0;
		jaAgiu=0;
	}
	
	//encontra a nova posição para o personagem e o move para ela
	@Override
	public void move(ITabuleiro tab) {	
		
		int tentativas=0;	//número de tentativas de se mover começa zerado, se passar de 30 provavelmente é porque está encurralado, e então deixa para tentar novamente na próxima rodada
		if (freqM == 0) {	//quando está zero, é a vez do personagem se mover
			newX = x;
			newY = y;
			
			while (tab.getPeca(newX, newY) != null && tentativas<=30) {	//fica no loop enquanto não acha uma nova posição vazia e ainda não passou do máximo de tentativas
				tentativas+=1;
				newX = x;
				newY = y;
				
				//valor entre -1,0 e 1 a ser adicionado multiplicado pelo passo e/ou subtraido aleatoriamente em x e y
				int addX = alea.nextInt(3)-1;	
				int addY = alea.nextInt(3)-1;
				System.out.println("addx:"+addX+" addy:"+addY);
				
				//se não for o dragão, testa se a nova posição é coerente para o guerreiro:
				if (vida<4) { 
					newX += passo*addX;	System.out.println("newx:"+newX);
					newY += passo*addY; System.out.println("newy:"+newY);
					//se a nova posição estiver fora do campo, reinicia e volta do inicio do while
					if(newX<0 || newX>15 || newY<0 || newY>15) {
						newX = x;
						newY = y;
						continue;
					//se a nova posição estiver a uma distância mínima do dragão, volta para o início do while, que testa se ela está vazia
					}if (newX-tab.getDragonPosition()[0]<=-5||newX-tab.getDragonPosition()[0]>=4
					||newY-tab.getDragonPosition()[1]<=-5||newY-tab.getDragonPosition()[1]>=4) {	
						continue;
					//se não estiver, reinicia e volta para o início do while
					}else {
						newX = x;
						newY = y;
						continue;
					}
				}	
				//se for o dragão, testa se a nova posição é coerente para o dragão:
				if (vida>4) { 
					//o dragão só se move em x ou y a cada passo, então é escolhido aleatoriamente um dos dois para ser modificado
					int a=alea.nextInt(2);
					if (a==0) {
						newX += passo*addX;		System.out.println("newx:"+newX);
					}else {		
						newY += passo*addY;		System.out.println("newy:"+newY);
					//se a nova posição estiver fora do campo, reinicia e volta do início do while
					}if(newX<1 || newX>15 || newY<1 || newY>15) {
						newX = x;
						newY = y;
						continue;
					//se a nova posição estiver vazia ou for parte do prṕrio dragão, para as 4 posições que o compõe, a nova posição é válida e sai do while imediatamente
					}if ((tab.getPeca(newX, newY)==null || tab.getPeca(newX, newY)==this) &&
						(tab.getPeca(newX-1, newY)==null || tab.getPeca(newX-1, newY)==this) &&
					 (tab.getPeca(newX, newY-1)==null || tab.getPeca(newX, newY-1)==this )&&
					 (tab.getPeca(newX-1, newY-1)==null || tab.getPeca(newX-1, newY-1)==this)) {
						break;
					//se não, reinicia e volta do início do while	
					}else {
						newX = x;
						newY = y;
						continue;
						
					}
				}
			}
			/*se achar a nova posição tiver sido tentado menos de 30 vezes, ou seja, passado por dentro do while e sido aprovada, 
			 * a posição atual vira null e o personagem é colocado na nova posição*/
			if (tentativas<=30) {
				tab.setPeca(x, y, null);
				
				//((PainelTabuleiro) tab).setElemento(newX,newY,(PecaIcon) this);
				
				//se for o dragão, as outras 3 posições que o compõem tem que ser ajustadas também
				if (vida>4) {//se for o dragão
					tab.setDragonPosition(newX,newY);
					tab.setPeca(x-1, y, null);	
					tab.setPeca(x, y-1, null);
					tab.setPeca(x-1, y-1, null);
					
					tab.setPeca(newX-1, newY, this);
					tab.setPeca(newX, newY-1, this);	
					tab.setPeca(newX-1, newY-1, this);
				}
				tab.setPeca(newX, newY, this);
				this.jaAgiu=1;
				
				//os atributos x e y do objeto são atualizados
				x = newX;
				y = newY;
				
				//PARA VER AS POSIÇÕES 
				System.out.println("vida:"+vida+" x:"+x+" y:"+y);
			}
		}//a frequência de movimento é atualizada se mover o personagem tiver sido um sucesso, para ele se mover novamente quando seu valor voltar a 0
		if (tentativas<=30) 
			freqM = (freqM + 1)%movimento;
	}
	
	//pega o dano do projetil e subtrai na vida do personagem que está naquela posição, se o ataque for inimigo. Se a vida do dragão for menor que 1, o jogo para
	@Override
	public void perdeVida(IProjetil projetil, ITabuleiro tab) {
		if (vida>4 && projetil.getDano()>3) {
			vida -= projetil.getDano();
			if (vida<=0)
				tab.getMetro().stop();
		}else if (vida<4 && projetil.getDano()<3)
			vida -= projetil.getDano();
	}

	//método implementado por cada subclasse
	@Override
	public abstract void disparaProjetil(ITabuleiro tab);

	/*public static int getCusto(){
		return custo;
	}*/
	
	//abaixo tem-se alguns métodos para retornar e modificar os atributos privados do Personagem
	
	@Override
	public int getJaAgiu(){
		return jaAgiu;
	}
	
	@Override
	public void setJaAgiu(int j) {
		jaAgiu=j;
	}
	
	@Override
	public int getVida() {
		return vida;
	}
}
