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
| 13/03/2018| 0.7 |Melhoria da descrição das partes interessadas e visão geral do documento | Alexandre Miguel, Gabriel Albino | 
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
  * [3.8	Alternativas e Concorrência](#3.8-Alternativas-e-Concorrência)
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

## 1: Introdução
Esta introdução fornece uma visão geral de todo o documento de visão. Ela inclui o propósito, escopo, definições, acrônimos, abreviações, referências e visão geral de todo o documento.  

### 1.1 Propósito
O documento explana o funcionamento do aplicativo Nexte, assim como os requisitos necessários para a compreensão do escopo do projeto.

### 1.2	Escopo
O projeto contemplado por esse documento apresenta formas de auxiliar a interação entre os jogadores de tênis através do gerenciamento de rankings e partidas.

### 1.3 Definições, acrônimos e abreviações
- MDS - Métodos de Desenvolvimento de Software
- EPS - Engenharia de Produto de Software
- FGA - Faculdade do Gama
- UnB - Universidade de Brasília
- CLT - Cinta Liga de Tênis

### 1.4	Referências
Entre as fontes de base para o desenvolvimento do projeto é importante citar:
1. Regras de jogos da CLT
2. [Tenis Integrado](http://www.tenisintegrado.com.br/clube/) 
### 1.5	Visão geral
O documento objetiva descrever de forma sucinta o software como serviço, ressaltando os aspectos fundamentais para o seu desenvolvimento, tais como:
- O posicionamento do produto em relação ao mercado, assim como na perspectiva das partes interessadas e dos usuários;
- Descrição das partes interessadas e dos usuários;
- Custo, capacidades e depencias para a ampla funcionalidade do aplicativo;
- Descrição dos recursos oferecidos pelo aplicativo, apresentando uma perspectiva da sua funcionalidade;
- Restrições dos uso, desenvolvimento e funcionalidade do aplicativo.

## 2: Posicionando
### 2.1	Oportunidade de Negócios
O projeto propõe uma melhoria no envolvimento dos associados do clube, gerando uma maior taxa de fidelidade, podendo trazer novos membros, alavancando o comércio local atraves da movimentação das quadras esportivas.

### 2.2	Instrução do Problema

A desorganização de partidas de tênis afeta os seus praticantes bem como os proprietários de clubes destinados ao exercício desse esporte. Dentre os problemas associados a essa desorganização destacam-se a falta de incentivo aos jogadores a continuarem a prática a dificuldade na procura de adversários. Possíveis formas de solucionar esses problemas encontram-se no desenvolvimento de um método gameficado, proporcionando a praticidade dos usuários a encontrar novos oponentes em horários vantajosos para ambos, aliando essas funcionalidades a um sistema de rankings que promove a competividade sadia entre os jogadores e estimula a prática do esporte.


### 2.3	Instrução de Posição do Produto

Para o membro do clube de tênis que necessita de uma organização de partidas de tẽnis. O Nexte é um aplicativo Mobile que gerencia os jogos de tênis. De outro modo (principal alternativa competitiva), nosso produto (instrução da principal diferenciação).O aplicativo divulga o ranking entre jogadores com mais facilidade e rapidez em relação âs formas anteriores de registro de resultados, utilizando o excel, sites ou aplicativos exclusivos para tal ação e possibilitar uma interação mais eficiente entre usuários interessados em praticar o esporte

Para os membros dos clubes de tênis que necessitam de incentivo e praticidade pra exercer o esporte. O Nexte é um aplicativo que administra os jogos de tênis e a competitividade sadia entre seus usuários. De outro modo comparando-o com o sistema Quero Jogar Tênis, o Nexte possui sistema de gamificação, de modo a incentivar a utilização do aplicativo, além disso o Nexte é focado em engajar as comunidades amadoras já existentes de tênis. O aplicativo divulga o ranking entre jogadores com mais facilidade e rapidez em relação âs formas anteriores de registro de resultados, como o excel, sites ou aplicativos exclusivos para tal ação e possibilitar uma interação mais eficiente entre usuários interessados em praticar o esporte.


## 3: Descrições da Parte Interessada e do Usuário

Para os proprietários de clubes amadores de tênis, o problema enfrentado é, principalmente, a falta de estimulo dos membros do clube que acarretam em uma falta de frequência de participação. O aplicativo visa melhorar o engajamento dos sócios como um incentivador, atuando no aumento o envolvimento dos associados nos eventos, colaborando para a assiduidade.

Para os membros de clubes amadores de tênis, o maior desafio enfrentado é dificuldade em achar oponentes em horarios adequados. O aplicativo visa administrar o sistema de partidas, atuando principalmente no gerenciamento dos jogos e competições, oferencendo comodidade na organização da prática do esporte.

Para os patrocinadores, que necessitam de um ambiênte para expor seus produtos. O aplicativo visa oferecer uma plataforma conveniente para o anuncio de artefatos esportivos, aumentando a visibilidade da marca no nicho propício. 

### 3.1 Demográficos de Mercado
O aplicativo Nexte pretende atingir as organizações amadores de tênis, assim como seus sócios. Atualmente, no Brasil, existem cerca de [300 clubes](#14-referencias) que poderiam se beneficiar do aplicativo.

### 3.2 Resumo da Parte Interessada
A necessidade de desenvolver habilidades na modalidade tênis através do desafio entre oponentes, assim como a utilização de diferentes espaços físicos e o interesse em organizar as partidas e seus respectivos rankings, apresentam três partes interessadas: tenistas, clubes e desenvolvedores do projeto.

### 3.3 Resumo do Usuário
O aplicativo Nexte conta com tenistas ativos acima de 18 anos como usuários, ou seja, com praticantes do esporte que tenham a idade mínima e disponibilidade de disputar o ranking como desafiantes e desafiados, assim como o clube a qual ele pertence, que será responsável pelo cadastro do tenista, não sendo possível o cadastro de usuários não associados a um clube.

### 3.4 Ambiente do Usuário
As quadras de tênis de Brasília são os principais ambientes compartilhados pelos esportistas. Sejam em clubes ou em áreas privadas, a localização da quadra é um critério escolhido entre os jogadores da partida, que tendem a enfrentar algumas limitações relacionadas à reserva das quadras por serem exclusivas a moradores e sócios. É importante ressaltar que o tempo de duração de cada partida varia de acordo com a pontuação de cada set.

### 3.5 Perfis das Partes Interessadas

Representante: Determina quem representa a parte interessada para o projeto (Essa informação será opcional se estiver documentada em algum outro lugar.) Insira os nomes dos representantes.
Descrição: Descreve brevemente o tipo de parte interessada.
Tipo: Qualifica o conhecimento da parte interessada, como "usuário avançado", "especialista em negócios", ou "usuário informal". Essa designação pode sugerir a experiência técnica e o grau de sofisticação.
Responsabilidades: Lista as principais responsabilidades da parte interessada no sistema em desenvolvimento; lista seus interesses como uma parte interessada.
Critérios de Sucesso: Determina como a parte interessada define o sucesso. Como a parte interessada é recompensada?
Envolvimento - Descreve como a parte interessada está envolvida no projeto. Onde possível, relate o envolvimento nas funções do processo; por exemplo, uma parte interessada pode ser um revisor de requisitos.
Entregas: Identifica as entregas adicionais que a parte interessada requer. Esses itens podem ser entregas do projeto ou saída a partir do sistema em desenvolvimento.
Comentários ou Problemas: Determina os problemas que interferem com o sucesso e quaisquer outras informações relevantes.

### 3.6 Perfis do Usuário
Descreve cada usuário do sistema aqui, preenchendo a seguinte tabela para cada tipo de usuário. Lembre-se que os tipos de usuário podem ser especialistas e novatos; por exemplo, um especialista pode precisar de uma ferramenta sofisticada e flexível com suporte para várias plataformas, enquanto um novato pode precisar de uma ferramenta que seja fácil de usar. Um perfil completo abrange esses tópicos para cada tipo de usuário:
Representante: Indica quem representa o usuário para o projeto. (Essa informação será opcional se estiver documentada em algum outro lugar.) Esse representante, geralmente refere-se à parte interessada que representa o conjunto de usuários; por exemplo, Parte Interessada: Parte Interessada1.
Descrição: Descreve brevemente o tipo de usuário.
Tipo: Qualifica o conhecimento do usuário, como "usuário avançado" ou "usuário informal." Essa designação pode sugerir a experiência técnica e o grau de sofisticação.
Responsabilidades: Lista as principais responsabilidades do usuário com respeito ao sistema; por exemplo, determina quem captura os detalhes do cliente, produz relatórios e coordena trabalho, etc.
Critérios de Sucesso: Determina como o usuário define o sucesso. Como o usuário é recompensado?
Envolvimento: Descreve como o usuário está envolvido no projeto. Onde possível, relate o envolvimento nas funções do processo; por exemplo, uma parte interessada pode ser um revisor de requisitos.
Entregas: Identifica as entregas que o usuário produz e para quem.
Comentários ou Problemas: Determina os problemas que interferem com o sucesso e quaisquer outras informações relevantes. Descreve as tendências que tornam a tarefa do usuário mais fácil ou mais difícil.

### 3.7 Principais Necessidades da Parte Interessada ou do Usuário
 Lista os principais problemas com soluções existentes como observadas pela parte interessada. Esclarece estas questões para cada problema:
Quais são os motivos para esse problema?
Como o problema é resolvido agora?
Quais soluções a parte interessada deseja?
Você deve entender a importância relativa que a parte interessada coloca na solução de cada problema. A classificação e técnicas de votos acumulativos indicam os problemas que devem ser resolvidos versus os problemas que as partes interessadas gostariam de tratar. Use esta tabela para capturar as necessidades da parte interessada.

| Necessidade | Prioridade | Interesses | Solução atual | Solução proposta |
|---|---|---|---|---|
| ex| ex2 | ex3 | ex4 | ex5 |
|  |  |  |  |  |
|  |  |  |  |  |


### 3.8 Alternativas e Concorrência
 Identifica as alternativas que a parte interessada percebe como disponíveis. Essas alternativas podem incluir a compra do produto de um concorrente, a criação de uma solução desenvolvida internamente ou manter o status quo. Listam todas as opções disponíveis e conhecidas. Elas incluem os principais pontos fortes e fracos de cada concorrente como observados pela parte interessada.

 ## 4:	Visão Geral do Produto
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

 ## 5:	Recursos do Produto
Lista e descreve brevemente os recursos do produto. Os recursos são capacidades de alto nível do sistema que são necessários para entregar benefícios aos usuários. Cada recurso é um serviço solicitado que, em geral, requer uma série de entradas para alcançar o resultado desejado. Por exemplo, um recurso de um sistema de rastreamento de problemas pode ser a capacidade de fornecer relatórios de tendências. À medida que o modelo de casos de uso toma forma, atualize a descrição para fazer referência aos casos de uso.
Como o documento de visão é revisado por uma ampla variedade de equipes envolvidas, mantenha o nível de detalhes gerais suficiente para que todos possam entender. No entanto, ofereça detalhes suficientes para fornecer à equipe as informações que ela precisa para criar um modelo de casos de uso ou outros documentos de design.
Para gerenciar a complexidade do aplicativo, para um novo sistema ou uma mudança incremental, liste os recursos em um alto nível para que você possa incluir aproximadamente 25 a 99 recursos. Esses recursos fornecem a base para a definição do produto, gerenciamento de escopo e gerenciamento do projeto. Cada recurso será expandido mais detalhadamente no modelo de casos de uso.
Em toda esta seção, torne cada recurso relevante para usuários, operadores ou outros sistemas externos. Inclua uma descrição de funções e problemas de usabilidade que devem ser tratados. As seguintes diretrizes se aplicam:
* Evite design. Mantenha as descrições do recurso em um nível geral. Foque nas capacidades necessárias e por que (não como) elas devem ser implementadas.
* Designe todos os recursos como requisitos de um tipo de recurso específico para fácil referência e rastreamento.

### 5.1 Sistema de autenticação de clubes
O aplicativo fornece um sistema de gerenciamento de clubes, permitindo cadastramento, atualização, login ou exclusão da conta.

### 5.2 Sistema de rankings
O aplicativo segue regras pré-definidas para classificar o usuário.

### 5.3 Sistema de desafios
O aplicativo torna possível o usuário desafiar outros jogadores seguindo regras pré-definidas.

### 5.4 Gerenciamento de partidas
O aplicativo gerencia as partidas realizadas, desde o desafio até a coleta do resultado.

## 6: Restrições
### 6.1 Restrições de design
O design do aplicativo será limpo e "user-friendly", de modo a tornar a navegação o mais natural possível.
### 6.2 Restrições de plataforma
O aplicativo será desenvolvido para smartphones com sistema android.
### 6.3 Restrições de implementação
Será utilizado no desenvolvimento a linguagem  Kotlin.
### 6.4 Restrições de uso
Para a utilização do aplicativo é necessário que o usuário possua um dispositivo android com conexão de internet.
