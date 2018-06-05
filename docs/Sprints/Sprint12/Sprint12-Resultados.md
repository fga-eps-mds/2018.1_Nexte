### Histórico de revisão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 03/05/2018| 0.1 | Criação do Documento | Miguel Pimentel|
| 05/05/2018| 0.2 | Atualização de Riscos, EVM e Análise do Scrum Master| Miguel Pimentel|

**Número da Sprint:** 12

**Data de ínicio:** 27/05/2018

**Data de término:** 02/06/2018

**Tempo de Duração:** Sete dias

## Sumário

[1. Indicadores da equipe](#1-indicadores-da-equipe)

* [1.1 Resumo da Sprint](#11-resumo-da-sprint)

* [1.2 Entrega Contínua](#12-entrega-contínua)

* [1.3 Produtividade](#13-produtividade)

* [1.4 Retrospectiva](#15-retrospectiva)

[2. Gestão de Risco](#2-gestão-de-risco)

[3. EVM](#3-evm)

[4. Análise de código](#4-análise-de-código)

[5. Análise do Scrum Master](#5-análise-do-scrum-master)

# **Resultados**

## **1. Indicadores da Equipe**

### **1.1. Resumo da Sprint**

// Trocar imagem

![Resumo da Sprint](https://i.imgur.com/f5Cnid5.png)

Como pode-se observar a equipe concluiu nessa _Sprint_, 17 das 19 histórias planejadas, sendo que duas delas foram débitos da Sprint 11, totalizando uma pontuação total de 72 de 85 pontos possíveis.

#### 1.2.1. Burndown

![Burndown](https://i.imgur.com/qSltkjE.png)

// Trocar imagem

O *Burndown* caiu somente no final da semana. Os primeiros *Pull Requests* foram aceitos na quinta-feira, e muitos outros no sábado. Entretanto, devido a muitas refatorações em código  algumas outras *user stories* foram fechadas somente no domingo pela necessidade de resolver conflitos e corrigir testes.

### 1.2.2. *Commits* por dia

![Commits por dia](https://i.imgur.com/7vrlEIG.png)

//  Trocar  Imgaem

O número de *commits* por dia foi mais irregular em relação a *sprint* anterior, entretanto houveram contribuições significativas todos os dias da semana. Nesta perspectiva, na terça e quinta-feira a equipe se mostrou mais ativa em relação ao número de *commits*.

### **1.3. Produtividade**

#### 1.3.1. Velocity

// Trocar imagem

![Velocity](https://i.imgur.com/gFMCSS0.png)

Como pode-se observar a equipe fechou a *sprint* com 7 pontos acima da última *sprint*. Entretanto, o velocity dessa *sprint* teve um valor inferior a última média inferida no valor de 75.4. 

Dessa forma, apesar do esforço dos membros, a equipe não conseguiu terminar 100% das histórias planejadas para essa *sprint*, como consequência, da dificuldade de configurar o Google Cloud e suas ferramentas.

Portanto, a nova média do velocity ficou em **75,36** *sprint points*

### **1.4 Retrospectiva**

<h4> Pontos Positivos:</h4>

* Cultura de refatoração(*Clean code*, correção de warnings, melhore práticas).
* Larissa mais ativa em relação a *sprints* anteriores.
* Pareamentos funcionaram de forma geral, com destaque para os respectivos pareamentos:
   - Geovanni e Luís;
   - Miguel e Letícia; 
   - Helena e Lorrany;
   - Alexandre e Albino;
   - Guilherme e Larissa;
* A equipe se reuniu para desconstrair e trabalhar no feriado do dia 31 de maio.  
* Decisão importante em relação aos testes da *view*. Gargalo da equipe de desenvolvimento.
* Proximidade de conclusão de escopo definido no *Roadmap*.

<h4> Pontos A Melhorar: </h4>

* Refatoração trouxe conflitos a outras histórias.
* Dificuldades de encontrar horário de pareamento entre Guilherme e Larissa.
* As medidas do Zeplin para as constraints são valores constantes, e não funcionam conforme o esperado no desenvolvimento.
* Albino e Alexandre ficaram sobrecarregados com dificuldades das histórias.
* Configurar o Google Cloud para utilização dos seus serviços.
* Viagens constantes de integrantes.
* O papel de P.O existiu por duas *sprints* apenas como um título. Sendo que muitas vezes, Guilherme Baldissera atuou como P.O ao longo do projeto.
* Alexandre chegou atrasado na reunião de retrospcetiva/review.

<h4> Como Melhorar: </h4>

* Comunicação sobre possíveis refatorações.
* Desenvolver conhecimento técnico sobre auto-layout avançado.
* Analisar a necessidades dos papéis antes de cada *sprint*.
* Compromisso com as reuniões presenciais. 

## **2. Gestão de Risco**

#### 2.1. Riscos priorizados

// Mudar imagem
![Gestão de Risco](https://i.imgur.com/3lFw00t.png)

#### 2.2. _Burndown risk_

// Mudar imagem
![Burndow Risk do Projeto](https://i.imgur.com/O3IeVWj.png)

Para acessar o burndown risk de cada risco sepadamente clique [aqui](https://docs.google.com/spreadsheets/d/1K6oxspA7JK-XiOPEHABS56DqX9O8WnunRu-7U4CFYZ0/edit?usp=sharing) e acesse a aba "Gráficos"

#### 2.3 Diferenças da sprint anterior

| ID | Status | Por que mudou? |
|---|---|---|
| RN01 | diminuiu | Crescimento técnico, tanto que o time optou pela retirada do quadro de conhecimento |
| RN01 | diminuiu | Reuniões de confraternização/trabalho em conjunto foram realizadas |
| RN25 | diminuiu | O servidor está sendo implementado e esta história ficou em cargo de EPS |
| RN23 | diminuiu | O time se sente mais confiante em relação a testes unitários |

#### 2.4 Ações para riscos atuais

| ID | Como mitigar |
|---|---|
| RN01 | O pareamento entre membros de EPS e MDS vai continuar. |
| RN02 | Realização de "maratonas" para trabalhar no projeto |
| RN03 | E dito que a qualquer problema, qualquer membro deve tentar resolver, caso não consigam, deve-se pedir ajuda o mais rápido possível |
| RN04 | Os membros de EPS trabalharão no servidor juntamente com o cliente. Segundo o planejamento da equipe esse servidor estará disponível para a aplicação a partir da sprint 10. |
| RN06 | Alguns membros já têm viagens marcadas, como os membros Guilherme e Geovanni, para que o período de ausência seja compensado, antes ou depois da viagem os membros devem realizar suas tarefas com empenho e dedicação |
| RN07 | O Scrum Master, será mais presente no papel de fazer com que todos integrantes estejam ativos na implementação de histórias e participação do rituais do SCRUM. |

## **3. EVM**

// Trocar Imagem
![EVM Gráfico](https://i.imgur.com/8vbjbLh.png) 

// Trocar Imagem
![EVM](https://i.imgur.com/pRtGfgG.png)

A quantidade de pontos realizados nessa *sprint* foi de 65 pontos,houve uma entrega de valor maior para o cliente e novamente o valor agregado foi maior que o planejado.

## **4. Análise de código**

![Detekt](https://i.imgur.com/XbwBsfn.png)

**Quantidade de linhas por método**

Dois métodos estão diagnosticados como longos, eles vêm das classes de tipo Mocker, em uma sprint que haverá refatoração de código ele será alocado para adequar à métrica proposta.  

**Quantidade de métodos por classe**

Não houve classes que ferissem esta métrica, todas estão dentro do parâmetro aceitável.

**Complexidade ciclomática**

Apenas um método foi relatado como muito complexo pela ferramenta de análise estática, e a complexidade ciclomática geral do projeto está em 675 e temos 406 métodos, o que dá uma média de 1,66 de complexidade por método, o que está bom, porém é necessário averiguar o que há no método que foi relatado com complexidade 27, pois o limite definido é 10.

**Quantidade de code smells**

6 code Smells foram encontrados pela ferramenta de análise estática, dado que o limite são 10, melhor que a *sprint* anterior, dentre os motivos, pode-se destacar alguns deles:

 - Um método está com a complexidade ciclomática acima de 10, o que não é recomendado nem aceitável, loco a análise estática relatou esta ocorrência.
 - Nome de variável
 - um construtor de classe vazio
 - dois métodos longos

**Adequação à folha de estilo**

Não houve relatos pela ferramenta de análise estática sobre fugas ao padrão de código definido pela equipe, logo está métrica não foi ferida ao finalizar a sprint.

## **5. Análise do Scrum Master**

Nesta Sprint, ocorreram decisões importantes acerca do projeto. Primeiro, definiu-se de de retirar os testes de activities/fragments por não existir como testar esse componente, visto que ele é provido pela própria SDK do Android. Todos os grupos de Android nativo de semestres anteriores nunca testaram esse componente.

Com objetivo de conseguir terminar o projeto da melhor forma possível o time se reuniu no feriado do dia 31 para trabalhar e descontrair, promovendo um momento de descontração em meio ao caos do final do semestre.

Os pareamentos da *sprint* funcionaram, houveram refatoramentos e optou-se pela retirada do quadro de pareamento visto que os integrantes já se consideravam maduros técnicamente. 

O time está comprometido com o projeto e tem como desafio conseguir associar o fim do semestre com as poucas tarefas restantes do projeto.  Apesar do *velocity* ter caído, muito em virtude de dificudade técnicas de ferramentas necessárias para realização da sprint o desempenho melhorou em relação as últimas *sprints* de maneira geral.





