### Histórico de revisão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 20/05/2018| 0.1 | Criação do Documento| Geovanni Oliveira|

----------------------------------------------------------------------

**Número da Sprint:** 10

**Data de ínicio:** 12/04/2018

**Data de término:** 19/05/2018

**Tempo de Duração:** Sete dias

## Sumário

[1. Indicadores da equipe](#1-indicadores-da-equipe)

* [1.1 Resumo da Sprint](#11-resumo-da-sprint)

* [1.2 Entrega Contínua](#12-entrega-contínua)

* [1.3 Produtividade](#13-produtividade)

* [1.4 Quadro de Conhecimentos](#14-quadro-de-conhecimentos)

* [1.5 Retrospectiva](#15-retrospectiva)

[2. Gestão de Risco](#2-gestão-de-risco)

[3. EVM](#3-evm)

[4. Análise de código](#4-análise-de-código)

[5. Análise do Scrum Master](#5-análise-do-scrum-master)

# **Resultados**

## **1. Indicadores da Equipe**

### **1.1. Resumo da Sprint**
![Resumo da Sprint](https://i.imgur.com/y8kTCFC.png)

Como pode-se observar a equipe concluiu nessa _Sprint_ 8 das 11 histórias planejadas, sendo que uma delas foi débito da Sprint 8, totalizando um total de 70 de 83 pontos concluídos.

Pode-se perceber que uma  grande quantidade de trabalho foi feita, houve uma participação maior do time de EPS, uma análise mais profunda está no tópico [5 - Análise do Scrum Master](#5-análise-do-scrum-master).

#### 1.2.1. Burndown
![Burndown](https://i.imgur.com/yaQmeYe.png)

Apesar dos membros de MDS terem tido um desempenho muito bom, as histórias foram fechadas mais próximo para o final da _Sprint_, os pontos foram queimados restando somente 1 débito.

### 1.2.2. Commits por dia
![Commits por dia](https://i.imgur.com/9T19R1H.png)

Pode-se observar que a equipe, de maneira geral, manteve entregas contínua ao longo de toda a sprint, sendo que o dia que mais teve commits foi no quinto dia.

Percebe-se também que nessa sprint a equipe teve uma média de commits um pouco menor que a sprint anterior, e que nos nos primeiros dias não houve uma frequência de commits tão alta quanto na passada, e que a quantidade dos commits realmente aumentaram a partir de quinta feira, e na anterior no Domingo já estavam acontecendo uma quantidade boa de commits.

### **1.3. Produtividade**

#### 1.3.1. Velocity

![Velocity Sprint8](https://i.imgur.com/fqlnh3p.png)
![](https://i.imgur.com/NNy80iG.png)

Como pode-se observar a equipe fechou bem menos pontos que na sprint anterior, porém as histórias foram fechadas restando apenas 2 débitos técnicos, o que representou uma pequena melhora em relação à Sprint passada, porém a quantidade de pontos planejadas não foi tão grande, o que acarretou em uma queda no Velocity de aproximadamente 2 pontos.

Apesar do esforço dos membros, a equipe não conseguiu atingir 100% da sua capacidade de produção.

O velocity ficou em **75.67** 


### **1.4. Quadro de conhecimentos**


![Quadro de Conhecimento sprint 8](https://i.imgur.com/QMvBhee.png)

Não houve alterações no quadro de conhecimento, provavelmente porque não houve solicitação para atualiza-lo, falha do novo Scrum Master que não cobrou o time para realizar esta tarefa.

Algumas áreas ainda estão ruins, como testes tanto instrumentais quanto unitários, Docker, principalmente para o Time de MDS e a API, que representa o servidor, pois não há um servidor ainda, o aplicativo está com dados fictícios. O foco das próximas sprints será nos testes, para que por consequência a cobertura aumente.

### **1.5. Retrospectiva**
<h4> Pontos Positivos:</h4>

* Os membros Miguel e Baldisseram melhoraram o comprometimento com o time de forma a surpreender a todos, parabéns aos membros;
* O grupo no geral apresentou um maior rendimento após a reunião de resolução de conflitos na segunda feira, segundo dia da Sprint;
* Houve o primeiro encontro com o novo Cliente;
* Feedback da professora Carla/ (adequação do Roadmap) e do Cliente gerou consolidação do produto final;
* PR's feitos no meio da semana;
* Compromisso com a daily por parte dos integrantes;
* Retirada do quadro de pareamemto e de horas foi uma alívio para os membros. 

<h4> Pontos A Melhorar: </h4>

* Falta de testes unitários;
* Ninguém está rodando os testes instrumentais;
* Gitlab CI ficou fora do ar, isso fez com que alguns PR's não fossem aceitos para ter certeza que eles passariam no CI;
* Lorrany ficou esperando o CI rodar para ver a porcentagem de cobertura de código, o que poderia ser feito localmente;
* Gabriel Albino e Larissa terminaram a história com antecedência e não auxiliaram Letícia, que estava sobrecarregada;
* Houve uma overpontuating na história de deixar o ranking fixo;
* Pontuação baixa;
* Burndown não está caindo.


<h4> Como Melhorar: </h4>

* Achar alguma forma de testar a View com testes unitários para subir a cobertura de código;
* Atualizar os testes de instrumentação quando houver alguma refatoração de código, parar que os testes existentes não sejam danificados;
* Executar a cobertura de testes localmente;
* Pedir ajuda de forma direta quando necessário;
* Levar em consideração o que deve ser feito (Item por item) nas histórias
* Quebrar histórias muito grandes em histórias menores, para que o Burndown seja melhor, assim haverá uma entrega contínua e não somente ao final da Sprint.

## **2. Gestão de Risco**

#### 2.1. Riscos priorizados

![Gestão de Risco](https://i.imgur.com/Ajg1DLa.png)

#### 2.2. _Burndown risk_

![Burndow Risk do Projeto](https://i.imgur.com/E7zJVDH.png)

Para acessar o burndown risk de cada risco sepadamente clique [aqui](https://docs.google.com/spreadsheets/d/1K6oxspA7JK-XiOPEHABS56DqX9O8WnunRu-7U4CFYZ0/edit?usp=sharing) e acesse a aba "Gráficos"

#### 1.5.3 Diferenças da sprint anterior

| ID | Status | Por que mudou? |
|---|---|---|
| RN03 | Diminuiu | Esse risco diminuiu um pouco a probabilidade dada a maturidade da equipe como um todo, pois todos já estão aptos a resolver eventuais problemas de ambiente, dado que já está tudo instalado e rodando como deveria.|
| RN04 | Diminuiu | Esse risco diminuiu um pouco dado que os membros Guilherme Baldissera e Miguel Pimentel já iniciaram os trabalhos no servidor|
#### 1.5.4 Ações para riscos atuais

| ID | Como mitigar |
|---|---|
| RN01 | O pareamento entre membros de EPS e MDS foi instituido nesta sprint para que o conhecimento seja passado para todos os membros e que as limitações técnicas sejam sanadas. |
| RN02 | Houve uma conversa com os membros e aparentemente todos estão mais empenhados em fazer mais coisas, mas no limite para Não serem prejudicados em outras disciplinas. |
| RN03 | E dito que a qualquer problema, qualquer membro deve tentar resolver, caso não consigam, deve-se pedir ajuda o mais rápido possível |
| RN04 | Os membros de EPS trabalharão no servidor juntamente com o cliente. Segundo o planejamento da equipe esse servidor estará disponível para a aplicação a partir da sprint 10. |
| RN06 | Alguns membros já têm viagens marcadas, como os membros Guilherme Baldissera e Geovanni Oliveira, para que no período de ausênsia seja compensado ou antes ou depois da viagem, assim cumprindo mais tarefas. |
| RN25 | Foram planejados e serão executados dois treinamentos de GO e criação de servidor em GO para a equipe de MDS/EPS. |

## **3. EVM**

![](https://i.imgur.com/4EXbWHm.png)    

A quantidade de pontos realizados nesta sprint foi maior do que na sprint passada, houve uma entrega de valor maior para o cliente e novamente o valor agregado foi maior que o planejado.
A agregação de valor foi acima do que foi planejado, com a diferença de R$ 5.534,02.  

## **4. Análise de código**

![](https://i.imgur.com/JYmC156.png)

**Quantidade de linhas por método**
Dois métodos estão diagnosticados como longos, eles vêm das classes de tipo Mocke, em uma sprint que haverá refatoração de código ele será alocado para adequar à métrica proposta.  

**Quantidade de métodos por classe**
Não houve classes que ferissem esta métrica, todas estão dentro do parâmetro aceitável.


**Complexidade ciclomática**
Apenas um método foi relatado como muito complexo pela ferramenta de análise estática, e a complexidade ciclomática geral do projeto está em 459 e temos 291 métodos, o que dá uma média de 1,57 de complexidade por método, o que está bom, porém é necessário averiguar o que há no método que foi relatado com complexidade 27, pois o limite acertado pela ferramente por complexidade individual é 10.

**Quantidade de code smells**
9 code Smells foram encontrados pela ferramenta de análise estática, dado que o limite são 10, está bem próximo do limiar não aceitável.
Alguns deles são:
 - Nomeação d
 - Um método está com a complexidade ciclomática acima de 10, o que não é recomendado nem aceitável, loco a análise estática relatou esta ocorrência.
 - Nome de variável
 - um construtor de classe vazio
 - dois métodos longos
**Adequação à folha de estilo**
Não houve relatos pela ferramenta de análise estática sobre fugas ao padrão de código definido pela equipe, logo está métrica não foi ferida ao finalizar a sprint.

## **5. Análise do Scrum Master**

Essa sprint foi a sprint do projeto que teve uma melhora por parte dos membros de EPS, dando suporte e tirando dúvidas do projeto com o pessoal de MDS, houveram duas duas dívidas técnicas, uma que já estava desde a sprint passada.
Houve uma melhora na comunicação de modo geral, somente a integrante Letícia teve uma dificuldade em pedir ajuda quando necessário.
Os membros estão mais comprometidos com o projeto, e estão sendo mais transparentes em relação aos impedimentos que vão dificultar a participação deles no projeto, isso fez com que a alocação de membros mais atarefados fosse priorizada. 
