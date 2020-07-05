# Projeto `Mate o Dragão`

# Descrição Resumida do Projeto/Jogo

O jogo se chama Mate o Dragão, pois essa é a única maneira de o jogador vencer. Para isso, o jogador escolhe soldados para colocar nas posições que preferir no campo de batalha, e enquanto os personagens se movem de forma autônoma, 
o jogador controla a princesa para não deixá-la ser atingida pelos projéteis. O jogador perde se a princesa ou todos os soldados morrerem.

# Equipe
* Áureo Henrique e Silva Marques - 213374
* Lindon Jonathan Sanley dos Santos Pereira Monroe - 220407

# Vídeos do Projeto

## Vídeo da Prévia
[Link para o vídeo](https://drive.google.com/file/d/1WTYxDMbC_eBaEOWbAoLYA-gZTC5DdQ9w/view?usp=sharing)

## Vídeo do Jogo
> <Coloque um link para o vídeo em que é demonstrada a versão final do jogo. Esse vídeo deve ter em torno de 5 minutos. Este vídeo não apresenta slides, nem substitui a apresentação final do projeto, que será feita por conferência. Ele mostra apenas o jogo em funcionamento.>

# Slides do Projeto

## Slides da Prévia
[Link para os slides](https://docs.google.com/presentation/d/1AsV5UUcxe-LLS95gD0u0uFggMae4aAfRPWseXTlmxlo/edit?usp=sharing)

## Slides da Apresentação Final
`<Coloque um link para os slides da apresentação final do projeto.>`

# Relatório de Evolução

### Introdução / Início do projeto

Nosso projeto nasceu da ideia de fazer um jogo em que arqueiros lutariam para matar um dragão. Para melhorar essa ideia, decidimos adicionar tipos diferentes de soldados, para o jogador decidir com quais personagens ele gostaria de batalhar. Com isso, definimos o contexto base do “Mate o Dragão”.
Na parte inicial do trabalho, tivemos que fazer a apresentação do jogo através dos slides, a qual ficou bem detalhada, e o diagrama de componentes junto com detalhamento das interfaces e dos métodos que seriam usados. Esse diagrama teve alguns defeitos, pois, no começo, ainda estávamos com algumas dúvidas em relação ao conceito de componentes, de interface provida e de interface requerida, mas com a ajuda do PED e com a arguição feita em aula, conseguimos elucidar melhor esses conceitos. 
Nosso diagrama, inicialmente, possuía 5 componentes, como ilustrado abaixo:

*imagem do diagrama*

Alguns problemas citados na arguição foram a falta de conexão entre Soldados e Ataque e o fato de a interface IPeca não ser requerida por ninguém. Refletimos sobre essas incoerências e concluímos o seguinte: sobre o primeiro problema, definimos que não havia interface entre Soldados e Ataque, pois o Ataque (o qual deve ser imaginado como um projétil, a exemplo de flecha, lança, entre outros) se movimenta no tabuleiro independentemente do personagem que o disparou, sendo a única ação do soldado ser instanciar o objeto Ataque no tabuleiro, e, para isso, não foi necessário o Soldado requerer nenhuma interface do Ataque; 

*colocar trecho de código para exemplificar*

Sobre o segundo problema, reparamos que, realmente, a interface IPeca deveria ter sido colocada como requerida pelo Tabuleiro, portanto, corrigimos isso.

### Implementação de Código e Componentização

Após essa etapa inicial, começamos de fato a implementar o código do projeto. Primeiramente, implementamos os componentes Tabuleiro e DataProvider, pois vimos que eles seriam a parte macro do jogo, para depois trabalharmos com os Soldados, Dragão e Ataque, os quais seriam objetos instanciados em cima dos outros dois. 
Frente às sugestões dadas na arguição e por outras ideias que surgiram durante a criação do código, fizemos algumas alterações na nomenclatura e no design do projeto:
- O componente Ataque foi renomeado para Projetil;
- Cada soldado e cada projétil foram considerados como um componente no diagrama;
- Os soldados (Arqueiro, Lanceiro, Mago, Catapulta) e o Dragao são subclasses da classe Personagem, assim como os projéteis Flecha, Lanca, Bola de Energia, Pedra e Bola de Fogo são subclasses da classe Projetil;
- O componente Tabuleiro provê a interface ITabuleiro para cada Personagem e para cada Projetil;
- Cada Personagem provê a interface IPersonagem para Tabuleiro;
- Cada Projetil provê a interface IProjetil para Tabuleiro;
- Cada Projetil provê a interface IProjetil para cada Personagem, pois, para verificação de dano em um personagem, é necessário utilizar o método getDano( ) de IProjetil

Em seguida, começamos a implementar também a interface gráfica. Essa parte foi de certo modo desafiadora, pois tivemos que pesquisar muito sobre os diversos tipos de componentes gráficos que existem, sobre o Java Swing e os métodos para construir os painéis. Mas, ao final, conseguimos criar uma interface agradável. Adicionamos mais dois componentes ao nosso diagrama, que estão relacionados a essa parte gráfica:
- PainelMenu: Esse componente requer as interfaces IDataProvider e ITabuleiro para alterar os dados de acordo com as ações que o usuário realizar;
- JanelaJogo: Esse componente requer as interfaces ITabuleiro e IMenu (provida de PainelMenu) para poder organizar os painéis na janela do jogo.

Em nosso trabalho, como o Tabuleiro precisava ser sempre modificado em um determinado passo de tempo, decidimos utilizar também o Pattern Observer. Para isso, o Tabuleiro está relacionado a um timer (chamado Metronomo), que, a cada “clock”, notifica um ActionEvent para o Tabuleiro, o qual executa o seu ActionPerfomed( ). Esse Pattern foi utilizado também para relacionar o clique de botões do PainelMenu a respectiva ação no jogo que o usuário gostaria de realizar, como adicionar e remover personagens.

Após finalizarmos a implementação do código, decidimos adicionar mais um tipo de personagem ao jogo, o qual é representado pelo componente Princesa e que provê e requer as mesmas interfaces que os outros personagens. A ideia da Princesa é de ser movimentada pelo jogador durante o jogo, por isso, utilizamos também o Pattern Observer para esse componente se movimentar de acordo com os comandos pressionados no teclado.

### Exceções

O tratamento de exceções foi utilizado em nosso trabalho na parte de entrada de dados pelo jogador. Grande parte das exceções foram pensadas no Laboratório 12 e estão relacionadas aos processos de adição e remoção de personagens e início de jogo. Nossas principais preocupações foram: posições fora do tabuleiro e proibidas de adição; entrada incorreta de dados; pontos insuficientes; adição em locais já ocupados; remoção do dragão ou da princesa; remoção em locais não ocupados; remoção sem ter adicionado personagens; e iniciar o jogo sem personagens.

### Problemas enfrentados

O problema principal de nosso projeto foi um erro relacionado às imagens de projéteis no tabuleiro. Para explicar o problema, vamos introduzir a lógica adotada para inserir as imagens de personagem e de projétil no tabuleiro. Foram criadas as classes PecaIcon e PainelTabuleiro, as quais gerenciam as interfaces gráficas das peças em geral e do tabuleiro, respectivamente. As classes Personagem e Projetil estendem PecaIcon para poderem representar graficamente os diversos tipos de peças no jogo e a classe Tabuleiro estende PainelTabuleiro. 

Para inserir uma peça e movimentá-la são utilizados os diversos métodos explicitados nessa documentação, mas os dois principais responsáveis por adicionar e remover o desenho dela são o setElemento() e o removeElemento(). Para a movimentação de projéteis, eventualmente poderia ocorrer um conflito entre eles no meio do campo e para resolver isso criamos um método chamado resolveConflito(), no qual permaneceria o projétil de maior dano.

O erro que surgiu foi que, ao conflitarem, a peça de menor dano realmente era removida, mas sua imagem permanecia, ou seja, em algum momento, projétil e imagem se desvinculavam. Isso ocorreu, pois, ao inserir a peça de maior dano na nova posição, era necessário remover a outra antes. Embora os apontadores no vetor de projéteis eram trocados, a imagem da posição ainda assim precisava ser retirada com o removeElemento(). Com isso, resolvemos essa parte do problema.

No entanto, percebemos que alguns projéteis ainda ficavam parados após serem disparados. Após uma análise mais profunda, concluímos o seguinte: no método modificaTabuleiro(), primeiro os personagens se movimentam e disparam ataques para depois os outros projéteis em campo serem movimentados. No disparo do ataque, o projétil é instanciado na posição de seu personagem e movimentado uma vez. Isso abriu a possibilidade de um outro personagem instanciar seu projétil em cima desse, sem analisar o conflito e gerando o mesmo problema de imagem apresentado anteriormente. Portanto, a solução encontrada foi verificar se havia um projétil na posição antes de disparar.

Um outro problema encontrado tratava-se da quantidade de casas percorridas pelo projétil a cada passo de jogo. Vamos dar o exemplo da pedra da catapulta: a Pedra andava 4 casas a cada passo de jogo, deixando sua permanência em campo muito curta e seu percurso pouco visível. A solução que encontramos foi fazer cada projétil andar de 1 em 1 casa pelo tabuleiro, mas com velocidades diferentes e, para isso, ajustamos o tempo do timer para poucos milissegundos e alteramos a frequência de movimento das peças para deixar algumas mais rápidas e outras mais lentas, deixando a dinâmica do jogo mais fluida.


> <Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.>

# Destaques de Código

> <Escolha trechos relevantes e/ou de destaque do seu código. Apresente um recorte (você pode usar reticências para remover partes menos importantes). Veja como foi usado o highlight de Java para o código.>

~~~java
// Recorte do seu código
public void algoInteressante(…) {
   …
   trechoInteressante = 100;
}
~~~

# Destaques de Pattern
`<Destaque de patterns adotados pela equipe. Sugestão de estrutura:>`

## Diagrama do Pattern
`<Diagrama do pattern dentro do contexto da aplicação.>`

## Código do Pattern
~~~java
// Recorte do código do pattern seguindo as mesmas diretrizes de outros destaques
public void algoInteressante(…) {
   …
   trechoInteressante = 100;
}
~~~

> <Explicação de como o pattern foi adotado e quais suas vantagens, referenciando o diagrama.>

# Conclusões e Trabalhos Futuros

Futuramente, pretendemos evoluir a ideia do jogo. Algumas ideias propostas foram: criar outros tipos de dragão, com poderes especiais ou mais fortes; adicionar fases ao jogo, para que o jogador tenha diferentes níveis de dificuldade; criar outros tipos de personagens como o clérigo, apresentado nos slides de prévia; customizar melhor a interface gráfica; e adicionar sons característicos de personagem e de dano.

> <Apresente aqui as conclusões do projeto e propostas de trabalho futuro. Esta é a oportunidade em que você pode indicar melhorias no projeto a partir de lições aprendidas e conhecimentos adquiridos durante a realização do projeto, mas que não puderam ser implementadas por questões de tempo. Por exemplo, há design patterns aprendidos no final do curso que provavelmente não puderam ser implementados no jogo -- este é o espaço onde você pode apresentar como aplicaria o pattern no futuro para melhorar o jogo.>

# Documentação dos Componentes

O vídeo a seguir apresenta um detalhamento de um projeto baseado em componentes:

[![Projeto baseado em Componentes](http://img.youtube.com/vi/1LcSghlin6o/0.jpg)](https://youtu.be/1LcSghlin6o)

# Diagramas

## Diagrama Geral do Projeto

> <Apresente um diagrama geral de organização da organização do seu sistema. O formato é livre. A escolha de um ou mais estilos arquiteturais será considerado um diferencial.>

> <Faça uma breve descrição do diagrama.>

## Diagrama Geral de Componentes

### Exemplo 1

Este é o diagrama compondo componentes para análise:

![Diagrama Analise](diagrama-componentes-analise.png)

### Exemplo 2

Este é um diagrama inicial do projeto de jogos:

![Diagrama Jogos](diagrama-componentes-jogos.png)

### Exemplo 3

Este é outro diagrama de um projeto de vendas:

![Diagrama Vendas](diagrama-componentes-vendas.png)

Para cada componente será apresentado um documento conforme o modelo a seguir:

## Componente `DataProvider`

Componente responsável pela passagem dos dados inseridos pelo usuário para o Tabuleiro e por gerenciar a adição e a remoção de personagens do jogo em relação a quantidade de pontos; Ele provê a interface IDataProvider para o Tabuleiro poder receber essas informações.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `Áureo Henrique e Lindon Jonathan`
Interfaces | `IGetData, IPontos, IDataProvider`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `IGetData`

`Gerencia a inserção e remoção de personagens e suas respectivas posições no vetor que contém esses dados.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

### Interface `IPontos`

`Aumenta e diminui a pontuação disponível para o jogador.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

### Interface `IDataProvider`

`Reune os métodos de IGetData e IPontos.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Tabuleiro`

Componente representante do tabuleiro do jogo, o qual armazena e relaciona todos os personagens e projéteis em campo; Provê a interface ITabuleiro para os personagens, os projéteis, o Menu e a JanelaJogo, para que eles possam ter acesso aos dados guardados nos vetores de peças e para estes poderem inserirem novas peças no Tabuleiro também.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `ITabuleiro, ActionListener`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `ITabuleiro`

`Realiza as modificações que ocorrem na disposição das peças a cada tempo do jogo.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Arqueiro`

Componente representante do personagem “arqueiro”. Provê a interface IPersonagem e é responsavel por instanciar Flecha no Tabuleiro.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `IMovimento, IPersonagem`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `IMovimento`

`Apresenta os métodos de movimento de personagens e de projéteis.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

### Interface `IPersonagem`

`Reúne os métodos de personagem como ações de perda de vida e de disparo de ataque.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Lanceiro`

Componente representante do personagem “lanceiro”. Provê a interface IPersonagem e é responsavel por instanciar Lanca no Tabuleiro.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Mago`

Componente representante do personagem “mago”. Provê a interface IPersonagem e é responsavel por instanciar BolaDeEnergia no Tabuleiro.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Catapulta`

Componente representante do personagem “catapulta”. Provê a interface IPersonagem e é responsavel por instanciar Pedra no Tabuleiro.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Princesa`

Componente representante do personagem “princesa”. Provê a interface IPersonagem e é movimentado pelo usuário durante o jogo.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Dragao`

Componente representante do personagem “dragão”. Provê a interface IPersonagem e é responsavel por instanciar BoldaDeFogo no Tabuleiro.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Flecha`

Componente representante do projétil “flecha”. Provê a interface IProjetil e é responsável por diminuir a vida de Dragao e de Princesa.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `IMovimento, IProjetil`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `IProjetil`

`Reúne os métodos de projétil como ações de dano e gestão de dados de posição e de conflito.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Lanca`

Componente representante do projétil “lança”. Provê a interface IProjetil e é responsável por diminuir a vida de Dragao e de Princesa.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `BolaDeEnergia`

Componente representante do projétil “bola de energia”. Provê a interface IProjetil e é responsável por diminuir a vida de Dragao e de Princesa.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `Pedra`

Componente representante do projétil “pedra”. Provê a interface IProjetil e é responsável por diminuir a vida de Dragao e de Princesa.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `BolaDeFogo`

Componente representante do projétil “bola de fogo”. Provê a interface IProjetil e é responsável por diminuir a vida de qualquer personagem.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `PainelMenu`

Componente responsável por projetar na tela o menu do jogo, que é composto por textos explicativos, por botões e por caixas de texto. Com isso, também administra a entrada de dados e as ações do usuário.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `IMenu, ActionListener`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `IMenu`

`Apresenta o método de retornar o componente gráfico de algum menu.`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `JanelaJogo`

Componente responsável por projetar na tela a interface gráfica completa do jogo, contendo o tabuleiro e o menu.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `ActionListener`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Componente `<Nome do Componente>`

> <Resumo do papel do componente e serviços que ele oferece.>

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`



## Exemplo:

### Interface `ITableProducer`

Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

~~~java
public interface ITableProducer {
  String[] requestAttributes();
  String[][] requestInstances();
}
~~~

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IDataSetProperties`

Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados.

~~~java
public interface IDataSetProperties {
  public String getDataSource();
  public void setDataSource(String dataSource);
}
~~~

Método | Objetivo
-------| --------
`getDataSource` | Retorna o caminho da fonte de dados.
`setDataSource` | Define o caminho da fonte de dados, informado através do parâmetro `dataSource`.

# Plano de Exceções

## Diagrama da hierarquia de exceções
`<Elabore um diagrama com a hierarquia de exceções como detalhado abaixo>`

![Hierarquia Exceções](assets/documentacao/exception-hierarchy.png)

## Descrição das classes de exceção

Classe | Descrição
----- | -----
SemPersonagem | Indica que não pode iniciar o jogo sem personagens no campo.
AdicaoInvalida | Engloba todas as exceções de adições não aceitas.
AdicaoLugarInexistente | Indica que o jogador tentou adicionar em uma posição que nem existe no campo.
AdicaoLugarOcupado | Indica que já há um personagem na posição solicitada. 
AdicaoLugarProibido | Indica que não pode inserir um personagem naquela posição.
AdicaoPontosInsuficientes | Indica que não possui pontos suficientes para o atual personagem.
RemocaoInvalida | Engloba todas as exceções de remoções não aceitas.
RemocaoAntesDeAdicao | Indica a tentativa de remoção antes de inserir pelo menos um personagem.
RemocaoLugarDragao | Indica que não pode remover o dragão.
RemoçãoLugarInexistente | Indica que o jogador tentou remover de uma posição que nem existe no campo.
RemocaoSemPersonagem | Indica que não há personagem no local de remoção.
