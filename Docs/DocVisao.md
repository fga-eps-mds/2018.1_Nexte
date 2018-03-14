### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 10/03/2018| 0.1 |Criação do documento | Geovanni Oliveira |
| 10/03/2018| 0.2 |Início do preenchimento do Documento | Alexandre Miguel, Gabriel Albino, Helena Goulart, Larissa Sales, Letícia Meneses, Lorrany Freire|
| 11/03/2018| 0.3 |Estrutura do documento foi completada | Geovanni Olivera|
| 12/03/2018| 0.4 |Preenchimento inicial do posicionamento | Alexandre Miguel, Letícia Meneses, Lorrany Freire |  
| 12/03/2018| 0.5 |Adicionado as descrições da parte interessada e do usuário | Helena Goulart, Larissa Sales |  
| 13/03/2018| 0.6 |Finalização do preenchimento e Alternativas e concorrencia | Gabriel Albino, Letícia Meneses, Lorrany Freire |  
| 13/03/2018| 0.6.1 |Removido tópicos que não serão abordados nesse documento | Gabriel Albino |
| 13/03/2018| 0.6.2 |Atualização do índice | Gabriel Albino | 
| 13/03/2018| 0.7 |Melhoria da descrição das partes interessadas e visão geral do documento| Alexandre Miguel, Gabriel Albino | 
| 13/03/2018| 0.8 |Preenchimento das descrições das partes interessadas e do usuário| Gabriel Albino, Helena Goulart, Larissa Sales, Leticia Meneses | 
| 13/03/2018| 0.9| Atualização das necessidades das partes interessadas| Gabriel Albino|
-------------------------------------------------------------------------------------------------

### Índice Analítico
* [**1 Introdução**](#1-introdução)
  * [1.1 Propósito](#11-Propósito)
  * [1.2 Escopo](#12-Escopo)
  * [1.3 Definições, acrônimos e abreviações](#13-Definições,-acrônimos-e-abreviações)
  * [1.4 Referências](#14-Referências)
  * [1.5 Visão Geral](#15-Visão-geral)
* [**2 Posicionando**](#2-Posicionando)
  * [2.1  Oportunidade de Negócios](#21-Oportunidade-de-Negócios)
  * [2.2 Instrução do Problema](#22-Instrução-do-Problema)
  * [2.3 Instrução de Posição do Produto](#23-Instrução-de-Posição-do-Produto)
* [**3 Descrições da Parte Interessada e do Usuário**](#3-Descrições-da-Parte-Interessada-e-do-Usuário)
  * [3.1  Demográficos de Mercado](#31-Demográficos-de-Mercado)
  * [3.2 Resumo da Parte Interessada](#32-Resumo-da-Parte-Interessada)
  * [3.3 Resumo do Usuário](#33-Resumo-do-Usuário)
  * [3.4 Ambiente do Usuário](#34-Ambiente-do-Usuário)
  * [3.5 Perfis das Partes Interessadas](#35-Perfis-das-Partes-Interessadas)
  * [3.6 Perfis do Usuário](#3.6-Perfis-do-Usuário)
  * [3.7 Principais Necessidades da Parte Interessada ou do Usuário](#37-Principais-Necessidades-da-Parte-Interessada-ou-do-Usuário)
  * [3.8 Alternativas e Concorrência](#3.8-Alternativas-e-Concorrência)
* [**4 Visão Geral do Produto**](#4-Visão-Geral-do-Produto)
  * [4.1 Perspectiva do Produto](#41-Perspectiva-do-Produto)
  * [4.2 Resumo das Capacidades](#42-Resumo-das-Capacidades)
  * [4.3 Suposições e Dependências](#43-Suposições-e-Dependências)  
  * [4.4 Custo e Precificação ](#44-Custo-e-Precificação)
  * [4.5 Licenciamento e Instalação](#45-Licenciamento-e-Instalação)
* [5 Recursos do Produto](#5-Recursos-do-Produto)  
  * [5.1 Sistema de autenticação de clubes](#51-Sistema-de-autenticação-de-clubes)
  * [5.2 Sistema de rankings](#52-Sistema-de-rankings)
  * [5.3 Sistema de desafios](#53-Sistema-de-desafios)
  * [5.4 Gerenciamento de partidas](#54-Gerenciamento-de-partidas)
* [6 Restrições](#6-Restrições)
  * [6.1 Restrições de design](#61-Restrições-de-design)
  * [6.2 Restrições de plataforma](#62-Restrições-de-plataforma)
  * [6.3 Restrições de implementação](#63-Restrições-de-implementação)
  * [6.4 Restrições de uso](#64-Restrições-de-uso)

## 1  Introdução
Esta introdução fornece uma visão geral de todo o documento de visão. Ela inclui o propósito, escopo, definições, acrônimos, abreviações, referências e visão geral de todo o documento.  

### 1.1 Propósito
O documento explana o funcionamento do aplicativo Nexte, assim como os requisitos necessários para a compreensão do escopo do projeto.

### 1.2 Escopo
O projeto contemplado por esse documento apresenta formas de auxiliar a interação entre os jogadores de tênis através do gerenciamento de rankings e partidas.

### 1.3 Definições, acrônimos e abreviações
- MDS - Métodos de Desenvolvimento de Software;
- EPS - Engenharia de Produto de Software;
- FGA - Faculdade do Gama;
- UnB - Universidade de Brasília;
- CLT - Cinta Liga de Tênis. 

### 1.4  Referências
Entre as fontes de base para o desenvolvimento do projeto é importante citar:
1. Regras de jogos da CLT
2. [Tênis Integrado](http://www.tenisintegrado.com.br/clube/) 

### 1.5  Visão geral
O documento objetiva descrever de forma sucinta o software como serviço, ressaltando os aspectos fundamentais para o seu desenvolvimento, tais como:
- O posicionamento do produto em relação ao mercado, a perspectiva das partes interessadas e dos usuários;
- Descrição das partes interessadas e dos usuários;
- Custo, capacidades e dependências para a ampla funcionalidade do aplicativo;
- Descrição dos recursos oferecidos pelo aplicativo, apresentando a perspectiva da sua funcionalidade;
- Restrições de uso, desenvolvimento e funcionalidade do aplicativo.

## 2  Posicionando
### 2.1	Oportunidade de Negócios
O projeto propõe uma melhoria no envolvimento dos associados dos clubes, resultando em uma maior taxa de fidelidade dos membros e a oportunidade de adesão de novos, favorecendo o comércio local através da movimentação das quadras esportivas.

### 2.2	Instrução do Problema

A desorganização de partidas de tênis afeta os seus praticantes bem como os proprietários de clubes, ocasionando a falta de incentivo aos jogadores em se desenvolverem dentro do esporte através da prática com adversários distintos e seus diferentes níveis de habilidades. O desenvolvimento de um método gamificado possibilita a solução desse problema, uma vez que proporcia a praticidade dos usuários em encontrar novos oponentes em horários vantajosos para ambos, aliando essas funcionalidades a um sistema de rankings que promove a competividade sadia entre os jogadores e estimula a prática do esporte.


### 2.3	Instrução de Posição do Produto

Para os membros dos clubes de tênis que necessitam de incentivo e praticidade pra exercer o esporte, o Nexte é um aplicativo que administra os jogos de tênis e a competitividade entre seus usuários. Comparando-o com o sistema *Quero Jogar Tênis*, o projeto Nexte possui sistema de gamificação que possibilita a imersão do usuário no esporte, possibilitanto também o engajamento das comunidades amadoras já existentes de tênis. O aplicativo divulga o ranking entre jogadores com facilidade e rapidez em relação âs formas anteriores de registro de resultados, como o excel, sites ou aplicativos exclusivos para esse fim.


## 3  Descrições da Parte Interessada e do Usuário

Para os proprietários de clubes amadores de tênis, o problema enfrentado é, principalmente, a falta de estímulo dos membros associados, acarretando na falta de frequência e participação nas quadras. O aplicativo visa melhorar o engajamento dos sócios como um incentivador, atuando no aumento o envolvimento dos associados nos eventos, colaborando para a assiduidade.

Para os membros de clubes amadores de tênis, o maior desafio enfrentado é a dificuldade de achar oponentes em horários que se adequem à rotina pessoal. O aplicativo visa administrar o sistema de partidas, atuando principalmente no gerenciamento dos jogos e competições, oferencendo comodidade na organização da prática do esporte.

Para os patrocinadores, que necessitam de um ambiente para expor seus produtos, o aplicativo visa oferecer uma plataforma conveniente para o anúncio de artefatos esportivos, aumentando a visibilidade da marca no nicho propício. 

### 3.1 Demográficos de Mercado
O aplicativo Nexte pretende atingir as organizações amadoras de tênis, assim como seus sócios. Atualmente, no Brasil, existem cerca de [300 clubes](#14-referencias) que poderiam se beneficiar do aplicativo.

### 3.2 Resumo da Parte Interessada
A necessidade de desenvolver habilidades na modalidade tênis através do desafio entre oponentes, assim como a utilização de diferentes espaços físicos e o interesse em organizar as partidas e seus respectivos rankings, apresentam três partes interessadas: tenistas, clubes e desenvolvedores do projeto.

### 3.3 Resumo do Usuário
O aplicativo Nexte conta com representantes de clubes de tênis e tenistas ativos acima de 18 anos como usuários que tenham disponibilidade de disputar o ranking como desafiantes e desafiados. Torna-se importante ressaltar que os clubes são os responsáveis pelo cadastro dos tenistas, não sendo possível o cadastro de usuários não associados a um clube.

### 3.4 Ambiente do Usuário
As quadras de tênis de Brasília são os principais ambientes compartilhados pelos esportistas. Sejam em clubes ou em áreas privadas, a localização da quadra é um critério escolhido entre os jogadores da partida, que tendem a enfrentar algumas limitações relacionadas à reserva das quadras por serem exclusivas a moradores e sócios. O tempo de duração de cada partida varia de acordo com a pontuação de cada set.

### 3.5 Perfis das Partes Interessadas

Dentre as partes interessadas encontram-se os representantes dos clubes de tênis, responsáveis  pelo cadastro dos sócios do clube e infraestrutura do local.
O engajamento de novos membros e a assiduidade dos antigos são as principais recompensas para os representantes.

### 3.6 Perfis do Usuário

Dentre os usuários encontram-se os associados dos clubes de tênis, sendo responsáveis pela organização de partidas, sendo gratificados com a comodidade na organização da prática do esporte. 

### 3.7 Principais Necessidades da Parte Interessada ou do Usuário

| Necessidade | Prioridade | Interesses | Solução atual | Solução proposta |
|---|---|---|---|---|
| Dificuldade no gerenciamento de partidas| Alta | Sócios dos clubes  | Softwares de baixa qualidade feita pelos próprios clubes, sendo a maioria sem suporte para mobile | Aplicativo que integra de forma mais rápida e funcional os membros do clube |
| Falta de motivação dos membros do clube  | Alta | Representantes do clube | Os softwares existentes não se preocupam com essa necessidade, fornecendo apenas funcionalidades básicas sem nenhum tipo de incentivo | O aplicativo proposto utiliza a metodologia de gameficação, que vai incentivar o uso constante do aplicativo e uma eventual motivação para os membros do clube continuarem a desafiar novos oponentes. |
| Ausência de dados dos jogadores (Vitorias, derrotas, aproveitamento, etc.) | Alta | Membros e representantes dos clubes | Os softwares existentes não se preocupam em armazenar os dados dos jogadores. | O aplicativo proposto deixará os dados dos jogadores mais acessíveis, tornando possível uma analise geral dos jogadores, assim como irá fornecer detalhes sobre o desempenho deles nas partidas. |
| Falta de ferramentas de integração da comunidade | Media | Membros do clube | As soluções atuais não apresentam um canal de interação entre os usuários | Será implementado no aplicativo um sistema de feed que exibirá resumo das partidas, assim como chat e integração com redes sociais e/ou informações de contato. |



### 3.8 Alternativas e Concorrência
Os principais concorrentes apresentam como pontos fortes a possibilidade de reservar quadras e marcar horarios com treinadores, mas em contrapartida não possuem um sitema para manter os usuários engajados no uso diário do aplicativo.
Como diferencial, o Nexte apresenta um sistema de gamificação, incentivando os usuários a manterem o uso constante do aplicativo, tanto pelo sistema de rank quanto pelo sistema de trofeús e conqusitas.


 ## 4  Visão Geral do Produto
O aplicativo é voltado para gerenciamento de clubes amadores de tênis, possuindo um sistema de ranking baseado nas regras da CLT.
### 4.1	Perspectiva do Produto
 O produto se difere dos demais pois ele é focado em clubes ao invés de jogadores, onde o clube é responsável pelo controle de seus membros. Utilizando a gamificação para incentivar os usuários, o aplicavo visa aumentar tanto a pratica do esporte, quanto a utilização do mesmo.
 O Nexte é um aplicativo independente com possibilidade de parcerias.
### 4.2	Resumo das Capacidades

| Benefício para o Cliente| Recursos de Suporte|  
| ----| ----|
| Gerenciamento de clubes. | Engajamento dos clubes já existentes de tênis, permitindo cadastro de seus membros. |
| Sistema de rankings | Colocação dos membros do clube com base em seu desempenho nas partidas. |
| Gerenciamento de partidas. | Marcação da partida por meio de desafios com regras pré-definidas e controle de resultados para o sistema de rankings |
| Sistema de gamificação.| Incentivo à competitividade sadia entre jogadores do clube|

### 4.3	Suposições e Dependências
* Os membros do clube devem possuir um smartphone com harware suficiente para sua utilização.
* Os membros do clube devem possuir uma versão mínima do sistema operacional Android (A definir).

 ## 5  Recursos do Produto

### 5.1 Sistema de autenticação de clubes
O aplicativo fornece um sistema de gerenciamento de clubes, permitindo cadastramento, atualização, login ou exclusão da conta.

### 5.2 Sistema de rankings
O aplicativo segue regras pré-definidas para classificar o usuário.

### 5.3 Sistema de desafios
O aplicativo torna possível o usuário desafiar outros jogadores seguindo regras pré-definidas.

### 5.4 Gerenciamento de partidas
O aplicativo gerencia as partidas realizadas, desde o desafio até a coleta do resultado.

## 6  Restrições
### 6.1 Restrições de design
O design do aplicativo será limpo e "user-friendly", de modo a tornar a navegação o mais natural possível.
### 6.2 Restrições de plataforma
O aplicativo será desenvolvido para smartphones com sistema android.
### 6.3 Restrições de implementação
Será utilizado no desenvolvimento a linguagem  Kotlin.
### 6.4 Restrições de uso
Para a utilização do aplicativo é necessário que o usuário possua um dispositivo android com conexão de internet.
