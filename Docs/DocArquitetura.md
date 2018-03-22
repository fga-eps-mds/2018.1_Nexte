
### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 16/03/2018| 0.1 |Criação do documento | Gabriel Albino, Letícia Meneses |
| 17/03/2018| 0.1.1 |Correção do índice analítico | Gabriel Albino |
| 18/03/2018| 0.2 |Adicionado introdução e representação arquitetural | Gabriel Albino |
| 18/03/2018| 0.3 | Finalizado restrições de arquitetura. | Gabriel Albino |
| 19/03/2018| 0.4 | Produção do Dimensionamento e Performance. | Gabriel Albino, Letícia Meneses e Lorrany Freire |
| 19/03/2018| 0.4.1 | Adicionado descrição e atributos das classes principais. | Gabriel Albino, Letícia Meneses e Lorrany Freire |
| 20/03/2018| 0.5 | Adicionado diagrama de pacotes e camadas, assim como sua descrição. | Gabriel Albino e Letícia Meneses |
| 21/03/2018| 0.5.1 | Revisão de texto e padronização do formato das tabelas| Alexandre Miguel |
| 21/03/2018| 0.6 | Adicionado diagrama de classes | Gabriel Albino, Letícia Meneses e Lorrany Freire |

### Índice Analítico
[INSERIR INDICE ANALÍTICO AQUI]

# Documento de arquitetura

## Nexte

### Objetivo desse documento

Este documento tem como objetivo descrever as principais decisões de projeto tomadas pela equipe de
desenvolvimento e os critérios considerados durante a tomada destas decisões. Suas informações incluem a
parte de hardware e software do sistema.

## 1 INTRODUÇÃO

### 1.1 Finalidade
Este documento fornece uma visão arquitetural abrangente do sistema Nexte,
usando diversas visões de arquitetura para representar diferentes aspectos do sistema. O objetivo
deste documento é capturar e comunicar as decisões arquiteturais significativas que foram tomadas
em relação ao sistema.

### 1.2 Escopo
Este Documento de Arquitetura de Software se aplica ao Next, auxiliando os desenvolvedores na construção do projeto.

### 1.3 Definições, Acrônimos e Abreviações
- UnB: Universidade de Brasília
- FGA: Faculdade do Gama
- MDS: Métodos de desenvolvimento de software
- EPS: Engenharia de Produto de Software


### 1.4 Referências
- CLT: Cinta Liga de Tênis

## 2. REPRESENTAÇÃO ARQUITETURAL

Para a arquitetura será utilizado a "clean architecture", que se baseia em oito modulos:
- Model: Responsável pelas regras de manipulação de dados e pode possuir modelos globais ou modelos locais de cada cena;
- Worker: É responsável por controlar as requisições de dados ao local em que esses dados estã armazenados, isto é, se comunica com a model;
- View: Apresenta os elementos na interface gráfica;
- Presenter: Formata os dados que serão exibidos e trata dos eventos da view;
- Interactor: Módulo centralizador que controla o fluxo entre o Presenter e o Worker;
- Router: Controla o fluxo entre as cenas.

As relações entre os módulos estão representadas no esquema a seguir.

Imagem 1: Relação entre os modulos da arquitetura clean

![EsquemaDaCleanArchitecture](https://github.com/fga-gpp-mds/2018.1-Grupo4/blob/master/Docs/Images/architectureScheme.jpg?raw=true)

## 3. REQUISITOS E RESTRIÇÕES DE ARQUITETURA

Esta seção  decreverá os requisitos de software e restrições que têm um impacto significante no projeto.

### 3.1 Restrições gerais
|Requisito|Solução|
|---|---|
|Linguagem  | Kotlin |
|Plataforma| Android versão 5.1 ou superior|

### 3.2 Restrições de arquitetura
A arquitetura clean é organizada de modo em que as camadas mais internas não conseguem acessar os dados ou metadados das camadas mais externas.

![](https://i.imgur.com/NLLi7Kr.jpg)

## 4. VISÃO LÓGICA


|Nome da classe|Atributos|Descrição
|:----:|:----:|:-----:|
|Jogadores|Nome completo, nome de usuário, senha, contato (e-mail e telefone), vitórias e derrotas |Define o jogador com suas informações pessoais, assim como seu desempenho nos jogos, podendo ele ser o diretor do clube, ou não.|
|Clube|Nome, Contato(e-mail e telefone), número de jogadores, diretor, membros|Gerencia informações sobre o clube, incluindo número de jogadores e quem eles são.|
|Ranking|Jogadores|Controla, a partir das regras da CLT, a colocação dos jogadores em relação aos demais membros do clube.|
|Jogos|Data, horário, jogadores, resultado, tempo de partida, local|Responsável pelo gerenciamento de jogos, desde sua marcação por meio de um desafio até a coleta de resultados.|
|Feed|Jogadores, jogos|Gerencia os jogos mais recentes a modo de torná-los visíveis, exbindo-os na página inicial do aplicativo.|


### 4.1 Visão Geral – pacotes e camadas

#### 4.1.1 Diagrama de pacotes
![ Exemplo de Diagrama de Pacotes da Aplicação](https://i.imgur.com/hVXTV2M.jpg)

No diagrama de pacotes temos que a arquitetura é composta de 3 pacotes, sendo eles:
- Presentation: Responsável pela interface gráfica e controle da entrada e saída de dados;
- Domain: Responsável pelo processamento de eventos, funcionando como intermediador entre a presentation e a infrastructure;
- Infrastructure: Responsável pelo armazenamento e fornecimento de dados para a domain ou presentation.

#### 4.1.2 Diagrama de camadas
![ Exemplo de Diagrama de Camadas da Aplicação](https://i.imgur.com/roHKLJ1.jpg)

No diagram
a de camadas é mostrado a interação entre os módulos, que são definidos como:

- Model: Responsável pelo armazenamento de dados;
- Worker: Responsável pela requisição dos dados da model;
- Iteractor: Responsável por controlar o fluxo entre o view, worker e presenter; 
- Presenter: Formata os dados que serão exibidos na view;
- View: Responsável pela interface gráfica e entrada de dados.
- Router: Responsável por controlar o fluxo entre as telas

## 5. VISÃO DE IMPLEMENTAÇÃO

### 5.1 Caso de Uso

#### 5.1.1  *Diagrama de Classes*
![Diagrama de classes](https://i.imgur.com/ZMuSdM2.jpg)


#### 5.1.2 *Diagrama de Sequência*
[Exemplo:](https://camo.githubusercontent.com/4cc153d724bbed2843bfb19e638477a7c504b9c9/687474703a2f2f692e696d6775722e636f6d2f517868526875322e706e67)

![Exemplo:](https://camo.githubusercontent.com/4cc153d724bbed2843bfb19e638477a7c504b9c9/687474703a2f2f692e696d6775722e636f6d2f517868526875322e706e67)



## 6. VISÃO DE IMPLANTAÇÃO

​ Descrever os nodos físicos, as configurações e os artefatos que serão implantados.



[Exemplo:](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412.jpeg)



![Exemplo:](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412.jpeg)





## 7. DIMENSIONAMENTO E PERFORMANCE

### 7.1. Volume
Enumerar os itens relativos ao volume de acesso aos recursos da aplicação:


Nos aspectos gerais, o aplicativo pode abrangir uma grande quantidade de clubes esportivos e assim seus membros. Considerando que cada usuário terá um jogo por semana e que utilizará o aplicativo a cada dois dias devido o sistema de gameficação para conferir sua posição no ranking, estima-se uma média de quatro a cinco acessos semanais por membro, conectando-se ao aplicativo em torno de uma vez por dia para acompanhar as atualizações do mesmo, essencialmente no período da manhã e da tarde de aproximadamente 5-7 minutos de duração.


### 7.2. Performance
Enumerar os itens referentes à resposta esperada do sistema:

* Tempo máximo para a execução de determinada transação:


## 8. QUALIDADE
Enumerar os itens de qualidade de software [QOS] significativos para a aplicação:

| Item  | Solução  | Descrição |
|:---:|:---:|:---:|
|  Escalabilidade | [Breve Descrição]  |[Breve descrição da Solução]   |
|  Confiabilidade, Disponibilidade | [Breve Descrição]  | [Breve descrição da Solução]  |  
| Portabilidade  | [Breve Descrição]  |  [Breve descrição da Solução] |
| Segurança  | [Breve Descrição]  |  [Breve descrição da Solução] |
