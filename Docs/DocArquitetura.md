
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
| 21/03/2018| 0.7 | Adicionado o índice analítico e definidos os aspectos de qualidade | Alexandre Miguel e Helena Goulart |

### Índice Analítico
* [**1 Introdução**](#1-introdução)
  * [1.1 Finalidade](#11-finalidade)
  * [1.2 Escopo](#12-acrônimos-e-abreviações-escopo)
  * [1.3 Definições, acrônimos e abreviações](#13-definições,-acrônimos-e-abreviações)
  * [1.4 Referências](#14-referências)
* [**2 Representação Arquitetural**](#2-Representação-arquitetural)
* [**3 Requisitos e Restrições de Arquitetura**](#3-Requisitos-e-restrições-de-arquitetura)
  * [3.1 Requisitos Gerais](#31-Restriçequisitos)
  * [3.2 Restrições de Arquitetura](#32-Restrições de Arquitetura)
* [**4 Visão Lógica**](#4-Visão-lógica)
  * [4.1 Visão Geral - Pacotes e Camadas](#41-Visão-Geral-Pacotes-e-Camadas)
  	* [4.1.1 Diagrama de Pacotes](#411-diagrama-de-pacotes)
  	* [4.1.2 Diagrama de Camadas](#412-diagrama-de-camadas)  
* [**5 Visão de implementação**](#5-visão-de-implementação)  
  * [5.1 Casos de uso](#51-Casos-de-uso)
  	* [5.1.1 Diagrama de Clases](#52-Diagrama-de-classes)
  	* [5.1.2 Diagrama de Sequência](#53-Diagrama-de-sequência)
* [**6 Visão de Implantação**](#6-Visão-de-implantação)
* [**7 Dimensionamento**](#7-Dimensionamento)
* [**8 Qualidade**](#8-Qualidade)
  

# Documento de arquitetura

## Nexte

### Objetivo desse documento

Este documento tem como objetivo descrever as principais decisões de projeto tomadas pela equipe de
desenvolvimento e os critérios considerados durante a tomada destas decisões. Suas informações incluem a parte de hardware e software do sistema.

## 1 INTRODUÇÃO

### 1.1 Finalidade
É apresentada uma visão arquitetural abrangente do sistema Nexte, usando diversas modelos de arquitetura para representar diferentes aspectos do sistema. O objetivo deste documento é capturar e comunicar as decisões arquiteturais significativas que foram tomadas
em relação ao sistema.

### 1.2 Escopo
Este Documento de Arquitetura de Software se aplica ao Nexte, auxiliando os desenvolvedores na construção do projeto.

### 1.3 Definições, Acrônimos e Abreviações
- UnB: Universidade de Brasília;
- FGA: Faculdade do Gama;
- MDS: Métodos de desenvolvimento de software;
- EPS: Engenharia de Produto de Software;
- JVM: _Java Virtual Machine_.


### 1.4 Referências
- CLT: Cinta Liga de Tênis

## 2. REPRESENTAÇÃO ARQUITETURAL

Para a arquitetura será utilizado a "clean architecture", que se baseia em oito módulos:
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

Esta seção decreve os requisitos de software e restrições que têm um impacto significante no projeto.

### 3.1 Requisitos gerais
|Requisito|Solução|
|---|---|
|Linguagem  | Kotlin |
|Plataforma| Android versão 5.1 ou superior|

### 3.2 Restrições de arquitetura
A _Clean Architecture_ é organizada de modo em que as camadas mais internas não conseguem acessar os dados ou metadados das camadas mais externas.

![](https://i.imgur.com/NLLi7Kr.jpg)

## 4. VISÃO LÓGICA


|Nome da classe|Atributos|Descrição|
|:----:|:----:|:-----:|
|Jogadores|Nome completo, nome de usuário, senha, contato (e-mail e telefone), vitórias e derrotas |Define o jogador com suas informações pessoais, assim como seu desempenho nos jogos, podendo ele ser o diretor do clube, ou não.|
|Clube|Nome, Contato (e-mail e telefone), número de jogadores, diretor, membros|Gerencia informações sobre o clube, incluindo número de jogadores e quem eles são.|
|Ranking|Jogadores|Controla, a partir das regras da CLT, a colocação dos jogadores em relação aos demais membros do clube.|
|Jogos|Data, horário, jogadores, resultado, tempo de partida, local|Responsável pelo gerenciamento de jogos, desde sua marcação por meio de um desafio até a coleta de resultados.|
|Feed|Jogadores, jogos|Gerencia os jogos mais recentes a modo de torná-los visíveis, exbindo-os na página inicial do aplicativo.|


### 4.1 Visão Geral – pacotes e camadas

#### 4.1.1 Diagrama de pacotes
![ Exemplo de Diagrama de Pacotes da Aplicação](https://i.imgur.com/hVXTV2M.jpg)

No diagrama de pacotes temos que a arquitetura é composta de 3 pacotes, sendo eles:
- _Presentation_ : Responsável pela interface gráfica e controle da entrada e saída de dados;
- _Domain_ : Responsável pelo processamento de eventos, funcionando como intermediador entre a presentation e a infrastructure;
- _Infrastructure_ : Responsável pelo armazenamento e fornecimento de dados para a domain ou presentation.

#### 4.1.2 Diagrama de camadas
![ Exemplo de Diagrama de Camadas da Aplicação](https://i.imgur.com/roHKLJ1.jpg)

No diagrama de camadas é mostrado a interação entre os módulos, que são definidos como:

- _Model_ : Responsável pelo armazenamento de dados;
- _Worker_ : Responsável pela requisição dos dados da model;
- _Iteractor_ : Responsável por controlar o fluxo entre o view, worker e presenter; 
- _Presenter_ : Formata os dados que serão exibidos na view;
- _View_ : Responsável pela interface gráfica e entrada de dados.
- _Router_ : Responsável por controlar o fluxo entre as telas

## 5. VISÃO DE IMPLEMENTAÇÃO

### 5.1 Caso de Uso

#### 5.1.1  *Diagrama de Classes*
![Diagrama de classes](https://i.imgur.com/ZMuSdM2.jpg)


#### 5.1.2 *Diagrama de Sequência*
[Exemplo:](https://camo.githubusercontent.com/4cc153d724bbed2843bfb19e638477a7c504b9c9/687474703a2f2f692e696d6775722e636f6d2f517868526875322e706e67)

![Exemplo:](https://camo.githubusercontent.com/4cc153d724bbed2843bfb19e638477a7c504b9c9/687474703a2f2f692e696d6775722e636f6d2f517868526875322e706e67)



## 6. VISÃO DE IMPLANTAÇÃO



[Exemplo:](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412.jpeg)



![Exemplo:](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412.jpeg)





## 7. DIMENSIONAMENTO

Nos aspectos gerais, o aplicativo pode abrangir uma grande quantidade de clubes esportivos e assim seus membros. Considerando que cada usuário terá um jogo por semana e que utilizará o aplicativo a cada dois dias devido o sistema de gameficação para conferir sua posição no ranking, estima-se uma média de quatro a cinco acessos semanais por membro, conectando-se ao aplicativo em torno de uma vez por dia para acompanhar as atualizações do mesmo, essencialmente no período da manhã e da tarde de aproximadamente 5-7 minutos de duração.


## 8. QUALIDADE

| Item  | Solução  | Descrição |
|:---:|:---:|:---:|
|  Escalabilidade | Encapsulamento e _Clean Architecture_  | Através da modularização e do encapsulamento do software, o sistema ficará propício para manutenção, tornando-se adaptável e acolhendo o número crescente de usuários.   |
|  Disponibilidade | Manutenção periódica  | A solução apresentada permite que o software passe o menor tempo possível fora do ar devido às falhas do sistema, possibilitando o funcionamento contínuo. |  
| Portabilidade  | Linguagem Kotlin  | A linguagem escolhida permite a compilação na máquina virtual Java, permitindo que os programas sejam executados em qualquer plataforma que contenha uma versão da JVM.  |
| Segurança  |  |  |
