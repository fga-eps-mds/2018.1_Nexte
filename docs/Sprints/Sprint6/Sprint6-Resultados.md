### Histórico de revisão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 23/04/2018| 0.1 | Criação do Documento| Luis Gustavo|
| 24/04/2018| 0.2 | Preenchimento da EVM e da Análise de Código| Geovanni Oliveira|

----------------------------------------------------------------------

**Número da Sprint:** 6

**Data de ínicio:** 15/04/2018

**Data de término:** 21/04/2018

**Tempo de Duração:** Sete dias

## Sumário

[1. Indicadores da equipe](#1-indicadores-da-equipe)

* [1.1 Resumo da Sprint](#11-resumo-da-sprint)

* [1.2 Entrega Contínua](#12-entrega-contínua)

* [1.3 Produtividade](#13-produtividade)

* [1.4 Quadro de Conhecimentos](#14-quadro-de-conhecimentos)

* [1.5 Retrospectiva](#15-retrospectiva)

[2. Gestão de Risco](#2-gestão-de-risco)

[3. EVM](#2-evm)

[4. Análise de código](#3-análise-de-código)

[5. Análise do Scrum Master](#4-análise-do-scrum-master)

# **Resultados**

## **1. Indicadores da Equipe**

### **1.1. Resumo da Sprint**
![Fechamento da Sprint](https://i.imgur.com/fkNzuEm.png)

Além das histórias acima, também foi finalizada a dívida técnica [#108](https://github.com/fga-gpp-mds/2018.1_Nexte/issues/108). Como pode-se observar a issue [#204](https://github.com/fga-gpp-mds/2018.1_Nexte/issues/204) ficará como dívida técnica pois a equipe percebeu que não havia sentido em centralizar os dados mockados do aplicativo sem ter um banco de dados local integrado. Assim, ficará para a próxima sprint centralizar os dados mockados e também integrar o *Realm*, que será o banco de dados local.

### **1.2. Entrega Contínua**

#### 1.2.1. Burndown
![Burndown](https://i.imgur.com/PK6cIyR.png)


### 1.2.2. Commits por dia
![Commits por dia](https://i.imgur.com/na04X6N.png)

Pode-se observar que a equipe trabalhou constatemente ao longo da *sprint*, conseguindo finalizar as histórias antes da deadline da sprint. Tanto que, como se pode ver no gráfico de commits por dia, que nos últimos dias da sprint a equipe praticamente não trabalhou, pois já haviam finalizados as suas histórias.

Vale ressaltar que as histórias dessa sprint eram bem simples e menores, pois essa foi uma sprint a parte que foi voltada correção de pequenos ajustes para a apresentação da release 1.

### **1.3. Produtividade**

#### 1.3.1. Velocity

![Velocity Sprint6](https://i.imgur.com/7jZfQHx.png)

A métrica do velocity não faz muito sentido para essa sprint. Como dito no documento de planejamento dessa sprint, essa sprint é uma sprint diferenciada das demais do projeto, pois ela não estava programada no roadmap, e assim, a equipe decidiu deixar essa sprint para a correção de pequenos ajustes e realização de dívidas técnicas para a apresentação da release 1.

Por isso, o velocity da equipe não levará em consideração essa sprint.


#### 1.3.2. Planilha de horas
![Planilha de horas](https://i.imgur.com/7JIxwkt.png)

Pode-se observar que nessa sprint os integrantes diveram carga horária bem menor que nas sprints anteriores, devido as particularidades dessa sprint. Mesmo assim, a maior parte dos integrantes obteve uma boa quantidade de horas dedicadas, seguindo a definição de produtividade da equipe.

### **1.4. Quadro de conhecimentos**

* **Sprint 4**
![Quadro de Conhecimento sprint 5](https://i.imgur.com/pvx1sbh.png)

* **Sprint 5**
![Quadro de Conhecimento sprint 6](https://i.imgur.com/XaY1cky.png)

Houveram melhoras mínimas no conhecimento das tecnologias gerais do projeto da sprint 5 para essa. Para as próximas sprints, será focado principalmente melhoras no conhecimento da equipe em relação a parte de testes unitários e testes de instrumentação.

### **1.5. Retrospectiva**
<h4> Pontos Positivos:</h4>

* MDS está bem sólido em relação a arquitetura;
* Sprint bem mais tranquila, o que possibilitou os integrantes a se dedicarem a outras disciplinas;
* Bom planejamento do roadmap(está sendo seguido);
* Equipe tem bom relacionamento;

<h4> Pontos A Melhorar: </h4>

* O produto "não foi apresentado" na release 1;
* Falha na comunicação sobre a data de entregas dessa sprint;

<h4> Como Melhorar: </h4>

* Deixar claro no planejamento de sprint as datas finais de entrega da sprint;
* Conferir previamente com "a banca" o conteúdo da Release 2;

## **2. Gestão de Risco**

#### 2.1. Riscos priorizados

![Gestão de Risco](https://i.imgur.com/vRT5YJn.png)


#### 2.2. _Burndown risk_

![Burndow Risk do Projeto](https://i.imgur.com/jnKUnlu.png  )

Para acessar o burndown risk de cada risco sepadamente clique [aqui](https://docs.google.com/spreadsheets/d/1K6oxspA7JK-XiOPEHABS56DqX9O8WnunRu-7U4CFYZ0/edit?usp=sharing) e acesse a aba "Sprint 6"

#### 1.5.3 Diferenças da sprint anterior

| ID | Status | Por que mudou? |
|---|---|---|
| RN03 | Diminuiu | Esse risco abaixou pois os maiores gargalos da configuração de ambiente(CI, CI integrado com testes,  cobertura de testes e métricas de código, deploy contínuo, ambiente de desenvolvimento, testes e homologação) já foram feitos. Além disso, agora a equipe de EPS possui dois devops o que diminui ainda mais que haja problemas com configuração de ambiente. |
| RN07 | Diminuiu | Esse risco diminuiu pois desde o início do projeto a comunicação entre as equipe tem sido um ponto positivo. Além de que a equipe de EPS está mais proativa no github. |
| RN15 | Diminuiu | Esse risco diminuiu agora EPS tem dois devops atuando, o que diminiu o risco de que só um integrante da equipe saiba realizar esse papel. Além disso, o membros de MDS estão bem menos dependentes do Gabriel Albino e do Alexandre Miguel para realizar as histórias. |
| RN22 | Diminuiu | Esse risco diminuiu pois após a reunião de retrospectiva MDS citou que estavam bem confiantes e com conhecimento sólido na arquitetura, além disso não houve mais problemas de histórias não realizadas(de MDS) por causa de dificuldades na arquitetura. |
| RN05 | Novo | O Geovanni, membro de EPS, irá participar da WWDC 2018, o que resultará em quase 2 semanas em que ele não conseguirá trabalhar no projeto. |
| RN 11 | Retirado | O Scrum Master não viu mais sentido em fazer a gestão desse risco, visto que ao longo de todo o semestre os membros da equipe ficarão atarefados com tarefas de todas as disciplinas. Além disso, já estão sendo geridos riscos de de baixa produtividade da equipe e membros não trabalhando no projeto. |

#### 1.5.4 Ações para riscos atuais

| ID | Como mitigar |
|---|---|
| RN05 | A equipe de EPS tentará mitigar esse risco por meio de que o  Geovanni terá a obrigação adiantar parte de suas tarefas antes da viagem, de modo que as poucas tarefas que ele teria nessas sprints a equipe de EPS consiga realizar sem que fiquem sobrecarregados. Além disso, quando ele voltar ele terá a obrigação de cumprir uma quantidade maior de tarefas para compensar o deficit que ele terá nessas semanas. |
| RN04 | A Equipe de EPS agora está atuando na entrega desse servidor, e ele ficará pronto essa sprint. |
| RN23/RN24 | Já foram realizados treinamentos sobre esses temas, agora EPS estará dando suporte ao máximo que MDS não deixe de entregar suas histórias por causa de testes ou métricas.|

## **3. EVM**
Como a sprint foi mais em relação às outras, dado que a Release foi na quinta feira (19/04) e todo o escopo foi planejado para ser entregue até quarta feira, logo a quantidade de pontos entregues foi menor do que o ocorrido nas outras, e isso reflete na planilha de valor agregado, foi bem menor do que as outras sprints.
Foi analisádo que o planejamento de pontos para a Release foi super estimado, o que foi concretisado na entrega da primeira Release, na quarta feira. A figura abaixo mostra como ficou a planilha de EVM para a primeira release.  
![](https://i.imgur.com/FuCQQfn.png)  

## **4. Análise de código**
A análise do código dada pela ferramenta escolhida pode ser analisada na imagem abaixo, onde relata as métricas de análise de código.
Foi averiguado 7 code smells pois, dois deles foram recebidos dado à uma falha de configuração da ferramenta, para a padronização de atributos de classe e constantes, a ferramenta fica incapaz de diferenciar as duas, logo existem dois code smells que são referentes à nomeação de atributos, porém está seguindo a folha de estilo no ponto de nomeação de constantes no código, então essa questão vai ser analisada mais a fundo para solucionar esse problema.
A complexidade ciclomática aumentou em 7 pontos, dado que na última análise estava em 161, e atualmente é representado na imagem que está em 168, existe somente um método que está fugindo do padrão adotado, que é de no máximo 30 linhas por método, e esse método possui 50 linhas, logo, devera ser refatorado.
As outras métricas que não foram pontuadas nesta análise, não houveram diagnósticos negativos pela ferramenta Detekt.
![](https://i.imgur.com/lcI8Enn.png)
## **5. Análise do Scrum Master**

Como dito acima, essa foi uma sprint diferenciada das demais do projeto, assim não faz muito sentido tirar conclusões diretamente dela. Mas, nessa sprint foi muito positivo ver o produto desenvolvido ganhar "vida" com a implementação do design, e também foi muito positivo ver a integração contínua e o deploy contínuo funcionando da maneira que a equipe planejou.

Também é muito positivo ver a equipe de MDS se sentido bem confiante e com conhecimento sólido em relação a arquitetura do projeto.

O ponto chave a ser melhorado nas próximas sprints é na produtividade da equipe, de modo que o velocity da equipe aumente e que não hajam mais dívidas técnicas. E também que a equipe aumente a qualidade dos documento feitos, visto que esse foi um ponto criticado na release 1.