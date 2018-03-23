

# 1. Goal Question Metrics - GQM

Utilizou-se como metodologia o GQM,  pois esta metodologia permite visualizar de forma mais fácil a organizacao do projeto e seus possíveis tópicos a serem mensurados[1]. Isto é possível devido a sua organizacão, no qual, apresenta-se três níveis:

* Conceitual (*GOAL*): Fase em que objeto alvo de medicão é definido
* Operacional (*QUESTION*): Fase em que são elaboradas perguntas para caracterizar o objeto alvo de medicão.
* Quantitativo (*METRIC*): Fase  em que são definidas medidas e métricas a serem aplicadas no objeto alvo.


Não obstante, Van Solingen, Rini e Berghout[2] definem  a divisão do GQM  em quatro fases:

* Planejamento: Fase em que um plano de projeto é desenvolvido para caracterizar, definir, resultando em um plano de projeto.
* Definicão: Realizacão do plano de medicão ( Objetivo, quesões, métricas e hipóteses)
* Coleta de Dados: Os dados são coletados de acordo com o planejamento.
* Interpretacão: Fase em que os dados são submetidos as métricas para assim serem interpretados. Os dados obtidos servem de insumos para respondes as questões proveninente do GQM, para então entender se os objetivos foram alcancados.

Referências

1. https://github.com/FGA-MED/MedeMais_2017-2/wiki/GQM


GQM

### Objetivos (*Goals*)

Os objetivos de medicão do projeto foram criados a partir do objeto a ser mensurado, propósito, foco de qualidade, ponto de vista e ambiente no qual o projeto se encontra. Dessa forma, optou-se pode defini-los de acordo com os padrões definidos a seguir.

| **Analisar**                | Defines os objetos que podem ser analisados  |
|-------------------------|---|
| **Para o propósito de**     |  Indica o motivo de análise |
| **Com respeito**            |  Delimita qual propriedade será objeto de análise |
| **Sob o ponto de vista de** | Define que fará uso dos dados coletados  |
| **No Contexto**             |  Determina o contexto em que a análise foi realizada |

* Objetivo 01 - Equipe do Projeto Nexte Android(EPS/MDS)

| **Analisar**                | Equipe do Nexte em Android(EPS/MDS)  |
|-------------------------|---|
| **Para o propósito de**     |  Melhorar |
| **Com respeito**            | Produtividade do time |
| **Sob o ponto de vista de** | Gerentes do Projeto  |
| **No Contexto**             |   Projeto Nexte Android(EPS/MDS) |

* Objetivo 02 - Código Fonte

| **Analisar**                | Código Fonte |
|-------------------------|---|
| **Para o propósito de**     |  Melhorar |
| **Com respeito**            |  Qualidade do Software |
| **Sob o ponto de vista de** | Gerentes do Projeto  |
| **No Contexto**             |   Projeto Nexte Android(EPS/MDS) |

## 2.2 Questões (*Questions*)

A seguir foram definidas as questões para os objetivos de medicão de acordo com os interesses da organizacão. 

* **Questões para o objetivo 01:**

O1Q01 - Os membros da equipe estão sendo proativos em relacão as tarefas do projeto?
01Q02 - A deidicão dos membros é constante?

* **Questões para o objetivo 02:**

O1Q01 - Qual o nível de manutenabilidade do código fonte?
01Q02 - Qual a complexidade do código fonte?

## 2.3 Métricas (*Metrics*)

### **Métricas para o objetivo 01(Equipe do Projeto Nexte Android)**

* Métrica 01 - Burndown

| **Métrica**           | **Burndown** |
|---------------------|----------|
| **Objetivo de Medição** | Verificar se as tarefas da sprint estão sendo entregues de forma continua.  |
| **Descrição**           |   O *burndown* apresenta a progressão de *user stories* entregue em relacão ao tempo da *sprint*. A partir desta métrica é possível compreender a lista de pendência(antes do término da *sprint*), compreender o tempo desempenhado que a equipe demanda para suas tarefas e prever quando a equipe atingirá suas metas.      |
| **Fórmula**             | Consiste em um gráfico de pontos planejados por *sprint* x tempo da *sprint*  (usualmente, em dias). Assim, conforme as histórias vão terminando o gráfico vai tomando um formato de uma linha decrescente.   |
| **Escala**              | Racional        |
| **Coleta**              | Responsável: Luís Gustavo Periodicidade: Semanalmente         |
| **Procedimento**        | Utilizar o *burndown* disponibilizado pelo zenHub. |
| **Análise**              | Caso a histórias sejam entregues antes do término da *sprint*, significa que a equipe foi subestimada, ou os *sprint points* foram estimados de forma errada. Caso os as histórias não sejam entregues, isso significa que a equipe não se aplicou tanto quanto deveria na realizacão das histórias, ou que a equipe foi super estimada. Num cenário ideal, é que as histórias sejam entregues de forma gradual, e que sejam executadas no tempo de uma interacão, formando uma uma gráfico linear decrescente. |
| **Providência**        | Para o caso que as histórias sejam entregues de forma antecipada ao término da *sprint*, significa que será necessário replanejamento. Caso as história sejam entregues no final ou não entregues, precisa haver novas políticas que assegurem maior respeito as práticas da metodologia, tais como: Replanejar duplas de pareamento; definir horários de planejamento; e delegar histórias de acordo com preferências de cada dupla.       |


* Métrica 02 - Velocity

| **Métrica**           | **Velocity** |
|---------------------|----------|
| **Objetivo de Medição** | Verificar o desempenho da equipe por sprints        |
| **Descrição**           | O *Velocity* é métrica que apresenta quantos *sprint points*  foram realizados em uma *sprint*         |
| **Fórmula**             | Dado uma sprint n o velocity da equipe é calculado com base no histórico das sprints passadas logo velocity=1n−1∑i=0n−1PCSi. Onde PCS representa a quantidade de Pontos Completos na Sprint.  |
| **Escala**              |  Racional        |
| **Coleta**              |  Responsável: Luís Gustavo Periocidade: Semalmente   |
| **Procedimento**        |  Analisar a quantidade de pontos concluídos na *sprint*     |
| **Análise**             |  O *velocity* é uma métrica oriunda de *sprints*, dessa forma, a partir das primeiras *sprints* será possível determina um *baseline* de pontos**. A partir disso, espera-se que a quantidade de pontos entre as *sprints* se mantenham ou aumentem. Ressalta-se que a partir do velocity que são planejados a quantidade de pontos** que são esperados para cada sprint. |
| **Providência**         | Caso a equipe a equipe apresente diminuicões grande de pontos entre *sprints*, por exemplo (-13) pontos. Será necessário adotar uma série de práticas, tais como: Replanejar duplas de pareamento; definir horários de planejamento; e delegar histórias de acordo com preferências de cada dupla.        |


* ## Métrica 03 - Nível de conhecimento sobre tecnologias

| **Métrica**           | Burndown |
|---------------------|----------|
| **Objetivo de Medição** |  Melhorar o conhecimento dos membros sobre as tecnologias do projeto   |
| **Descrição**           |  Quadro utilizado para visualizar o conhecimento de cada membro sobre as tecnologias utilizadas no projeto   |
| **Fórmula**             | Não se aplica    |
| **Escala**              |  Ordinal        |
| **Coleta**              | Responsável: Geovanni Oliveira Periocidade: Semanalmente  |
| **Procedimento**        | Verificar com todos os membros do time se houve melhora no conhecimento sobre determinadas tecnologias ou ferramentas em relacão a *sprint* anterior.  |
| **Análise**              | A análise do conhecimento sobre tecnologias será descrito de acordo com a escala ordinal apresentadas a seguir: - Excelente: </br> * Bom:
- Regular:  |
| **Providência**        |          |



<!--
Melhorar o rendimento individual de cada membro da equipe.
Descrição	Este quadro é utilizado para poder verificar o conhecimento da equipe.
Fórmula	-
Escala	Ordinal
Coleta	Responsável: João Paulo
Periodicidade: Ao fim de cada iteração.
Procedimento	Verificar a cada iteração se houve melhora no conhecimento de uma determinada tecnologia ou ferramenta em relação, em relação à iteração anterior.
Análise	​Analisar a confiança de cada membro na utilização das ferramentas e tecnologias presentes no projeto.O conhecimento do membro será de acordo com um dos níveis a seguir: 
- Ótimo : Tenho muita segurança em usar a tecnologia. 
- Bom : Consigo usar com facilidade na maior parte das vezes. 
- Regular : Tenho um pouco de dificuldade, mas sei me virar. 
- Ruim : Tenho dificuldade e preciso de ajuda nisso várias vezes. 
- Péssimo : Tenho muita dificuldade e sempre preciso de ajuda nisso.
Providência	Caso se verifique que o nível de conhecimento dos membros da equipe não está homogêneo em uma determinada ferramenta ou tecnologia, um treinamento sobre o mesmo deverá ser realizado para que o conhecimento seja nivelado.

-->

<!--

Verificar o nível de contribuição de cada membro da equipe de desenvolvimento durante uma iteração sob o ponto de vista dos outros membros.
Descrição	O nível de contribuição é responsável por indicar o quanto um membro está contribuindo para o desenvolvimento do projeto.
Fórmula	-
Escala	Intervalar
Coleta	Responsável: Thiago 
Periodicidade: Ao fim de cada iteração.
Procedimento	Verificar a cada iteração a quantidade de pontos atribuídas a cada membro pelos outros membros.
Análise	Analisar a contribuição dos membros para o projeto.
Providência	Caso se verifique que um membro tenha recebido uma pontuação menor ou igual 20 , deverá se verificar os motivos que levaram a isso e agir de acordo com o planejado pois a baixo deste índice considera-se que o indivíduo se esforçou pouco.

-->

* Métrica 04 - Nível de Contribuicão por *sprint* points

| **Métrica**           | Burndown |
|---------------------|----------|
| **Objetivo de Medição** |          |
| **Descrição**           |          |
| **Fórmula**             |          |
| **Escala**              |          |
| **Coleta**              |          |
| **Procedimento**        |          |
| **Análise**              |          |
| **Providência**        |          |


* Ressalta-se que algumas das métricas, pode ser mais bem compreendidas como indicadores, visto que agrega mais valor a parte interna da organizacão.