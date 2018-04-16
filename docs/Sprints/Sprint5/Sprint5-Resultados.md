### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 09/04/2018| 0.1 |Criação da estrutura do documento | Luis Gustavo |
-------------------------------------------------------------------------------------------------

**Número da Sprint:** 5

**Data de ínicio:** 08/03/2018

**Data de término:** 14/03/2018

**Tempo de Duração:** Sete dias

## Sumário
[1. Fechamento da Sprint](#1-fechamento-da-sprint)

[2. Entrega Contínua](#2-entrega-contínua)

[3. Produtividade](#3-produtividade)

[4. Quadro de Conhecimentos](#4-quadro-de-conhecimentos)

[5. Pareamentos Reais](#5-pareamentos-reais)

[6. Gestão de Risco](#6-gestão-de-risco)

[7. Retrospectiva](#7-retrospectiva)

[8. EVM](#8-evm)

[9. Análise do Scrum Master](#9-análise-do-scrum-master)


## **Resultados**
### **1. Fechamento da Sprint**
![Fechamento da Sprint](https://i.imgur.com/GDwv0k1.png)

Além das histórias concluídas acima também formas concluídas as dívidas técnicas [#96](https://github.com/fga-gpp-mds/2018.1_Nexte/issues/96) e [#191](https://github.com/fga-gpp-mds/2018.1_Nexte/issues/191)

Infelizmente duas histórias não foram concluídas a tempo, um delas é a de deploy contínuo que não foi finalizada principalmente por causa da da instabilidade do gitlab, que ficou instável e fora do ar ao longo dessa sprint, principalmente nos últimos dias. O lado bom é que ambas as histórias já estão bem encaminhadas, e provavelmente serão finalizadas no início da próxima sprint.

### **2. Entrega Contínua**

#### 2.1. Burndown
![Burndown](https://i.imgur.com/S21exSA.png)


### 2.2. Commits por dia
![Commits por dia](https://i.imgur.com/He5zkpm.png)


### **3. Produtividade**

Para melhor entendimento sobre como a produtividade da equipe deve ser analisada/interpretada, veja o documento [definição de produtividade](https://github.com/fga-gpp-mds/2018.1_Nexte/blob/dev/docs/EPS/DefinicaoDeProdutividade.md).

#### 3.1. Velocity

![Velocity Sprint5](https://i.imgur.com/MXbPNb6.png)


#### 3.2. Planilha de horas
![Planilha de horas](https://i.imgur.com/5G98ZU3.png)


### **4. Quadro de conhecimentos**

* **Sprint 4**
![Quadro de Conhecimento sprint 4](https://i.imgur.com/7XaE6gr.png)

* **Sprint 4**
![Quadro de Conhecimento sprint 5]()


### **5. Pareamentos Reais**
![Quadro de Pareamento](https://i.imgur.com/geuctDY.png)


### **6. Gestão de Risco**

#### 6.1 Riscos priorizados

![Gestão de Risco]()

#### 6.2 _Burndown risk_

Visto que 11 riscos estão sendo priorizados nessa _sprint_ é inviável colocar as 11 imagens nesse arquivo, sendo assim para ver os gráficos clique nesse [link](https://docs.google.com/spreadsheets/d/1K6oxspA7JK-XiOPEHABS56DqX9O8WnunRu-7U4CFYZ0/edit?usp=sharing) e acesse a aba "Sprint5".

#### 6.3 Diferenças da sprint anterior

A maior parte dos riscos priorizados nessa _sprint_ mantiveram o valor da _sprint_ passada, pois não foram tomadas ações específicas para mitigá-los. Apenas os riscos abaixo tiveram alterações:

| ID | Status | Por que mudou? |
|---|---|---|
| RN01 | Diminuiu | Essa foi a segunda _sprint_ seguida em que MDS trabalhou com código, e em ambas, todas as histórias foram entregues. Assim a confiança da equipe com a tecnologia está bem mais alta e EPS considerou que a probabilidade desse risco acontecer no futuro diminuiu. |
| RN03 | Diminuiu | Esse risco diminuiu pois a equipe não enfrentou nenhum problema no ambiente de desenvolvimento até então. Além disso, o _devops_ da equipe conseguiu concluir a história de integração contínua e está mais seguro em relação a infraestrutura do projeto. |
| RN07 | Diminuiu | Desde a primeira _sprint_ a comunicação entre os membros da equipe funciona bem. Na _sprint 4_ a equipe passou a utilizar a plataforma github com bem mais frequência em relação a criação/conversa nas _issues e pull requests_. Sendo assim, viável dizer que esse risco se torna menor agora que os integrantes estão utilizando melhor mais uma plataforma para comunicação. |
| RN22 | Diminuiu | Os integrantes de MDS conseguiram entregar todas as histórias, seguindo a arquitetura, pela segunda _sprint_ consecutiva. Além disso, foi abordado na restrospectiva que os membros de MDS entenderem melhor e estão mais seguros em relação a arquitetura.|


#### 6.4 Ações para riscos atuais

| ID | Como mitigar |
|---|---|
| RN01 | Foi dado um treinamento de boas práticas com Kotlin, a equipe de EPS forneceu um código exemplo de Recycle View para a equipe de MDS e também está sendo preparado um treinamento de _integration tests_ para a equipe de MDS. |
| RN02 | O scrum master fará, a partir dessa _sprint_, melhor análise/coleta da produtividade da equipe |
| RN03 | Assim como na _sprint_ passada, mais de um membro de EPS trabalhará na parte de _devops_ do projeto durante essa _sprint_. |
| RN04 | Para mitigar esse risco o quanto antes, nessa própria _sprint_ será criado o servidor com algumas funcionalidades essenciais e ele já será integrado ao Docker como um micro serviço. |
| RN07 | Será cobrado dos integrantes da equipe maior responsabilidade com as _daily meetings_, que é aonde a equipe ainda tem bastante espaço para melhorar na comunicação. Além disso, o _scrum master_ irá apoiar os membros do grupo para que os mesmos não deixem de falar seus impedimentos. |
| RN15 | Esse risco estava acontecendo bastante, visto que um integrante de MDS está dominando mais a tecnologia(Androind com Kotlin) mais que os outros integrantes, o que resultava em os outros integrantes necessitarem da ajuda dele para entregar suas histórias. Para mitigar esse risco, os pareamentos essa _sprint_ serão fixos, de forma que os integrantes que ainda não dominam a tecnologia terão de correr atrás desde o início da _sprint_ para aprender e conseguir finalizar suas histórias. |
| RN20 | Essa sprint serão entregue parte do servidor e ao final da _sprint_ parte de três cenas(ranking, feed e perfil) já estarão parcialmente implementadas, o que possibilitará que novas histórias já possam ser feitas, visto que essas três "macro" cenas estarão prontas. |
 

### **7. Retrospectiva**
<h4> Pontos Positivos:</h4>

* Muito aprendizado, tanto para MDS como para EPS
* MDS gostou de fazer outras atividades além de codar(protótipo de alta fidelidade com marvel)
* MDS aprendeu a utilizar o debbuger do android studio
* Melhoria no feedback/ajudar por parte de EPS
* Melhora no documento de resultados de sprint e análise de produtividade
* O arquiteto da equipe compreendeu melhor o seu papel

<h4> Pontos A Melhorar: </h4>

* Larissa e Lorrany, durante os pareamentos, não agiram como pilotas, nem mesmo quando elas eram as pilotas. Elas ficaram muito dependentes da dupla de pareamento delas para conseguir realizar as histórias
* Dependência de MDS com EPS para desenvolvimento das histórias
* Letícia e Helena não tiveram compatibilidade de horário para parear durante essa sprint, o que resultou em maior parte da história delas feitas individualmente por cada uma
* Membros não estão avisando dos impedimentos nas dailys
* Sobrecarga do Baldissera para dar feedback nas issues
* PO sem função real no projeto
* Todas as sprints do projeto, incluindo essa, tiveram dívidas técnicas

<h4> Como Melhorar: </h4>

* Pareamentos nessa sprint serão feitos entre duplas de pessoas que ainda não estão dominando a tecnologia, de modo que force a dupla de pareamento a correr atrás de solucionar o problema. Ou seja, as pessoas que sabem menos não conseguiram ficar sendo "suportadas" por integrantes que estão dominando a tecnologia
* Pesquisar antes de começar a implementação de código
* Levar em consideração a refatoração de código e testes ao votar os pontos referentes a história
* Membros de EPS precisam ser mais proativos no github
* Geovanni deixará de ser PO e passará a atuar como segundo devops da equipe
* Na próxima sprint será criado um documento para centralizar os estudos referentes as tecnologias do projeto, em especial Kotlin. Dessa forma, ficará mais fácil que todos fiquem a par do desenvolvimento do projeto e ficará mais fácil encontrar material de estudo para auxiliar o desenvolvimento das histórias

### **8. EVM**

![EVM]()

### **9. Análise do Scrum Master**


