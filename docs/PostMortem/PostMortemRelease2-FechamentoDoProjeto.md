### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 24/06/2018| 0.1 | Criação do documento | Luis Gustavo |
-------------------------------------------------------------------------------------------------

**Total de Sprints:** 16

**Data de ínicio:** 08/03/2018

**Data de término:** 27/06/2018

**Tempo de Duração:** 3 meses e 19 dias

[1. Fechamento do Projeto](#1-fechamento-do-projeto)

* [1.1 Velocity](#11-velocity)

* [1.2 Quadro de Conhecimento](#12-quadro-de-conhecimento)

* [1.3 EVM](#13-evm)

* [1.4 Métricas](#14-métricas)

* [1.5 Riscos](#15-riscos)

[2. Post Mortem](#2-post-mortem)

* [2.1 Pontos Fortes](#21-pontos-fortes)

* [2.2 Pontos Fracos](#22-pontos-fracos)

* [2.3 Lições Aprendidas](#23-lições-aprendidas)

## **1. Fechamento do Projeto**

### **1.1. Velocity**

### **1.2. Quadro de Conhecimento**

\* *O quadro de conhecimentos foi adicionado na sprint 4.*

* Sprint 4

![Quadro de Conhecimento Sprint 4](https://i.imgur.com/7XaE6gr.png)

* Sprint 15

![Quadro de Conhecimento Sprint 15]()

### **1.3. EVM**

### **1.4. Métricas**

### **1.5. Riscos**

## **2. Post Mortem**

### **2.1. Pontos Fortes**

* Kotlin é muito bom e foi muito bom aprender uma nova linguagem, que tem muita documentação;
* Trabalho colaborativo da equipe, se ajudavam bastante para fazer as histórias;
* Comunicação foi um ponto forte da equipe;
* MDS e EPS se tornaram uma só equipe;
* Equipe não parou de trabalhar em nenhum momento;
* Entregamos o escopo definido e algumas funcionalidades a mais;
* MDS percebeu que o modelo ágil/scrum/dailys/pareamentos/... foram muito importantes para a entrega do projeto;
* Arquitetura VIP foi um ponto positivo para manutenibilidade e testabilidade da aplicação;
* Código ficou muito limpo, manutenível e com muitos comentários;
* Tracking e refatoração de métricas de código foi muito simples de manter nas faixas ideais;
* Dar atenção e gastar tempo com gerenciamento de riscos foi muito importante para mitigar problemas que poderiam ter tido mais impacto no projeto;
* Utilização de github para comunicação(*PRs e Issues*) foi um ponto forte da equipe;
* Bot da daily no slack;
* Devops foi um ponto muito forte da equipe, quase tudo(CI, Deploy, Ambiente de desenvolvimento/teste/homologação) ficou pronto e estável desde a release 1;
* Miguel melhorou muito depois que o grupo conversou com ele;
* EPS deu muito suporte a MDS;
* EPS teve preocupação em capacitar MDS para o desenvolvimento;
* Luis cobrava a equipe de maneira muito incisiva;
* Equipe não deixou de entregar;
* Equipe manteve o foco durante todo o semestre;
* Arquitetura de micro-serviços trouxe uma nova maturidade de projeto, e reflexão sobre o assunto.

### **2.2. Pontos Fracos**

* Luis cobrava a equipe de maneira muito incisiva, o que era chato muitas vezes;
* Android Studio e os simuladores(muitos integrantes da equipe não tinha celular android) são ruins e instáveis;
* Alta dependência do Android Studio para o desenvolvimento android;
* Complicações para testes em android, qualquer coisa além do básico necessita de frameworks externas para mock(http, json, view, ...);
* Erros nos planejamentos, por tentar sempre aumentar o *velocity*, e muitas vezes não considerava a curva de aprendizado e eventuais problemas;
* Membros da equipe não tiveram semanas saudaveis por causa da dedicação, tempo e esforço gigantesco que a matérias(e as demais matérias da FGA) exigem;
* Viagens constantes: Baldissera 3 viagens; Geovanni 1 viagem; Miguel 1 viagem; Larissa 1 viagem; Além da Hackaton da Uber que os membros de EPS(menos o Luis) participaram. E as pessoas que viajavam não se preocupavam em fazer mais histórias para não sobrecarregar a equipe(não se aplica a viagem do Miguel);
* Miguel sempre tomava mais responsabilidade para si do que ele conseguia fazer e não era transparente quanto ao andamentos das histórias, e no final isso resultava em dívidas técnicas;
* Muitas issues não foram feitas granularizadas, e algumas com pontuação muito abaixo do que deveria ter sido;
* Baldissera esquecia de colocar os critérios de aceitação das issues;
* A aplicação da metodologia ágil e demais coisas necessarias da disciplinas ficaram prejudicadas no projeto, pois o  Baldissera tomou deciões como cliente/PO, aparentemente sem se importar com as obrigações da disciplina, sem importando apenas com ter o aplicativo na versão android;
* Algumas vezes o Baldissera(membro de EPS e cliente do projeto Nexte) parecia que não se importava com a disciplina, por ele ser o cliente muitas vezes não ligou para mitigar riscos do projeto, como colocar o servidor no projeto o quanto antes. Isso fez alguns membros da equipe se sentirem prejudicados;
* Larissa não mudou mesmo após a equipe convesar com ela;
* Falta de proatividade de pilotar no pareamento fez com que alguns membros do grupo ficassem desnivelados em relação ao conhecimento;
* Testes de view foram um problema gigante, demorou muito para a equipe conseguir achar uma solução para esse problema;
* Dados mockados(exigência do cliente) atrasaram muito o desenvolvimento do projeto. O servidor poderia ter entrado antes;
* Desenvolvimento da matéria ficou muito travado algumas vezes por um membro da equipe ser também o cliente;
* Design e servidor deixados para o final do projeto atrapalharam o desenvolvimento;
* Ter que encaixar micro-serviço após ter arquitetura definida, fez com que houvesse um replanejamento e gasto de esforços que resultaram em dívidas técnicas. 

### **2.3. Lições Aprendidas**

* NÃO TENHA UM CLIENTE QUE É MEMBRO DO GRUPO;
* Brincadeira de mal gosto, ainda que não tenha a intenção real de ofender, não fazem bem para o relacionamento e comunicação entre a equipe;
* Não utilize *git add .*;
* Não confie quando a equipe fala que dessa vez vai conseguir entregar mais pontos essa sprint, confie nos dados e se baseie neles para planejar;
* Sempre seja transparente com os membros da equipe;
* Coloque os riscos principais no início do projeto, indepentende das coisas sem sentido que o cliente falar;
* Não fique dependente do android studio para desenvolvimento android;
* Monitorar os membros da equipe e os riscos é um trabalho muito cansativo e difícil, mas é extramamente necessário e traz bons resultados;
* Não confie que alguém vai entregar as histórias no final da sprint. Cobre transparência e impedimentos nas dailys;
* Gastem esforços para preparar todos os dados para o planejamento. Siga os dados utilizados para o planejamento e produtividade da equipe;
* Aceitar menos decisões de EPS, questionar mais;
* Se você sabe mais da tecnologia que os demais integrantes, não saia se metendo nos pareamentos alheios. Deixa as pessoas pesquisarem e aprenderem sozinhas/com suas respectivas duplas;
* Seja rigoroso em aceitar as entregas no dias marcados da sprint, uma vez que se aceita uma entrega após a sprint ter fechada a equipe tenderá a atrasar as entregas;
