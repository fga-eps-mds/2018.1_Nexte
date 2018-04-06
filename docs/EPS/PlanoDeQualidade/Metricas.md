## Plano Goal Question Metric(GQM)

### Métricas (*Metrics*)

#### **Métricas para o objetivo 01(Equipe do Projeto Nexte Android)**

* Métrica 01 - Burndown

| **Métrica**           | **Burndown** |
|---------------------|----------|
| **Objetivo de Medição** | Verificar se as tarefas da sprint estão sendo entregues de forma contínua.  |
| **Descrição**           |   O *burndown* apresenta a progressão de *user stories* entregues em relação ao tempo da *sprint*. A partir desta métrica é possível compreender a lista de pendência(antes do término da *sprint*), compreender o tempo desempenhado que a equipe demanda para suas tarefas e prever quando a equipe atingirá suas metas.      |
| **Fórmula**             | Consiste em um gráfico de pontos planejados por *sprint* x tempo da *sprint*  (usualmente, em dias). Assim, conforme as histórias vão terminando o gráfico vai tomando um formato de uma reta decrescente.   |
| **Escala**              | Racional        |
| **Coleta**              | Responsável: Luís Gustavo Periodicidade: Semanalmente         |
| **Procedimento**        | Utilizar o *burndown* disponibilizado pelo zenHub. |
| **Análise**              | Caso a histórias sejam entregues antes do término da *sprint*, significa que a equipe foi subestimada, ou os *sprint points* foram estimados de forma errada. Caso os as histórias não sejam entregues, isso significa que a equipe não se aplicou tanto quanto deveria na realizacão das histórias, ou que a equipe foi super estimada. Num cenário ideal, é que as histórias sejam entregues de forma gradual, e que sejam executadas no tempo de uma interação, formando uma uma gráfico linear decrescente. |
| **Providência**        | Para o caso que as histórias sejam entregues de forma antecipada ao término da *sprint*, significa que será necessário replanejamento. Caso as história sejam entregues no final ou não entregues, precisa haver novas políticas que assegurem maior respeito as práticas da metodologia, tais como: Replanejar duplas de pareamento; definir horários de planejamento; e delegar histórias de acordo com preferências de cada dupla.       |


* Métrica 02 - Velocity

| **Métrica**           | **Velocity** |
|---------------------|----------|
| **Objetivo de Medição** | Verificar o desempenho da equipe por sprints        |
| **Descrição**           | O *Velocity* é métrica que apresenta quantos *sprint points*  foram realizados em uma *sprint*         |
| **Fórmula**             | Dado uma sprint n o velocity da equipe é calculado com base no histórico das sprints passadas logo velocity=1n−1∑i=0n−1PCSi. Onde PCS representa a quantidade de Pontos Completos na Sprint.  |
| **Escala**              |  Racional        |
| **Coleta**              |  Responsável: Luís Gustavo </br> Periocidade: Semalmente   |
| **Procedimento**        |  Analisar a quantidade de pontos concluídos na *sprint*     |
| **Análise**             |  O *velocity* é uma métrica oriunda de *sprints*, dessa forma, a partir das primeiras *sprints* será possível determina um *baseline* de pontos**. A partir disso, espera-se que a quantidade de pontos entre as *sprints* se mantenham ou aumentem. Ressalta-se que a partir do velocity que são planejados a quantidade de pontos** que são esperados para cada sprint. |
| **Providência**         | Caso a equipe a equipe apresente diminuicões grande de pontos entre *sprints*, por exemplo (-13) pontos. Será necessário adotar uma série de práticas, tais como: Replanejar duplas de pareamento; definir horários de planejamento; e delegar histórias de acordo com preferências de cada dupla.        |


* Métrica 03 - Nível de conhecimento sobre tecnologias

| **Métrica**           | **Quadro de Conhecimento** |
|---------------------|----------|
| **Objetivo de Medição** |  Melhorar o conhecimento dos membros sobre as tecnologias do projeto   |
| **Descrição**           |  Quadro utilizado para visualizar o conhecimento de cada membro sobre as tecnologias utilizadas no projeto   |
| **Fórmula**             | Não se aplica    |
| **Escala**              |  Ordinal        |
| **Coleta**              | Responsável: Geovanni Oliveira </br> Periocidade: Semanalmente  |
| **Procedimento**        | Verificar com todos os membros do time se houve melhora no conhecimento sobre determinadas tecnologias ou ferramentas em relacão a *sprint* anterior.  |
| **Análise**              | A análise do conhecimento sobre tecnologias será descrito de acordo com a escala ordinal apresentadas a seguir: </br>  **Excelente:** Tenho total seguraca sobre determinada tecnologia </br> **Bom:** Consigo fazer uso desta detcnologia quase sempre com facilidade </br> **Regular:** Tenho certa dificuldade, mas consigo trabalhar com esta tecnologia </br> **Ruim:**  Tenho dificuldade, e preciso de ajudar para trabalhar com esta tecnologia </br> **Péssimo:** Tenho dificuldades, e quase sempre procuro ajuda com esta tecnologia|
| **Providência**        |  Caso seja notável uma grande dificuldade da maioria dos integrantes com certa tecnologia, será necessário a utilizacão de treinamentos. Caso esta dificuldade esteja reduzida a um pequeno grupo de integrantes, será realizada políticas de pareamento entre os que mais dominam o conteúdo e os que estão com mais dificuldades.        |

* Métrica 04 - Nível de Contribuicão por *sprint* points

| **Métrica**           | **Pontos integrantes por *Sprint*** |
|---------------------|----------|
| **Objetivo de Medição** |  Verificar o nível de contribuição em *sprint points* por interação        |
| **Descrição**           |  O nível de contribuição refere-se a quantidade de pontos que um indivíduo concluiu em uma *sprint*, dessa forma, sua contribuição com o time. |
| **Fórmula**             |  Não se aplica        |
| **Escala**              |  Intervalar |
| **Coleta**              |  Responsável: Miguel Pimentel </br> Periodicidade: Semanalmente |
| **Procedimento**        |  Verificar ao final de cada *sprint* quantos pontos foram entregues por cada integrante         |
| **Análise**              |  Analisar a quantidade de pontos entregue por cada integrante, e ver sua constância em relação a outras *sprints*  |
| **Providência**        |  Caso um membro tenha um desempenho menor do que sua média, será necessário tentar compreender se há impedimentos, para então realizar medidas tais como:  Replanejar duplas de pareamento; definir horários de planejamento; delegar histórias de acordo com suas preferências; e até medidas mais severas. |

* Métrica 05 - Nível de contribuição por número de *commits* diários

| **Métrica**           | **Números de commits por dia da *Sprint*** |
|---------------------|----------|
| **Objetivo de Medição** | Verificar se existe constância na realização das tarefas por meio do número de *commits* |
| **Descrição**           | O número de *commits* diário ao longo da *sprint* apresenta se as tarefas estão sendo executadas de forma contínua e gradual, esta métrica foi utilizada com o objetivo de ser uma alternativa ao *burndown* que não considera a curva de aprendizado. |
| **Fórmula**             | Não se aplica |
| **Escala**              | Intervalar |
| **Coleta**              | Responsável: Luís Gustavo </br> Periodicidade: Semanalmente |
| **Procedimento**        | Verificar ao final de cada *sprint* quantos *commits* foram entregues em cada dia |
| **Análise**              | Analisar a quantidade de *commits* para cada dia da interação, e comparar em valores absolutos com os demais dias dessa *sprint*  |
| **Providência**        | Caso seja possível notar grandes diferenças de *commits* por dia ao longo da sprint, será necessário tentar compreender se há impedimentos, para então realizar medidas tais como:  Replanejar duplas de pareamento; definir horários de planejamento;  e delegar histórias de acordo com preferências de cada integrante. |

* Métrica 06 - Nível de horas dedicadas por **sprint**

| **Métrica**           | **Números de horas dedicadas por *sprint*** |
|---------------------|----------|
| **Objetivo de Medição** | Analisar o número de horas dedicadas por cada integrante ao longo de uma *sprint* |
| **Descrição**           | A quantidade de horas dedicadas por cada integrante apresenta em números absolutos sua proatividade e responsabilidade com o projeto, esta métrica adicionadas a outras podem trazer informações importantes sobre a eficiência dos integrantes do times. |
| **Fórmula**             | Não se aplica |
| **Escala**              | Ordinal |
| **Coleta**              | Responsável: Luís Gustavo </br> Periodicidade: Semanalmente |
| **Procedimento**        | Recolher os dados disponíveis na planilha de horas no Google Drive |
| **Análise**             | A dedicação de horas trabalhadas pode ser divida em horas: </br > Esperado[8, 12] </br> Menos que o Esperado ]0, 8] </br> Mais que o esperado ]12, ∞]  |
| **Providência**         | Caso a pessoa tenha realizado suas histórias e se encontre definida como menos que esperado de dedicação, requer replanejamento. Caso a pessoa se apresente como Dedicação menos que o esperado e não tenha finalizado suas histórias, será necessário encontrar se há algum impedimento. Caso a pessoa esteja trabalhando mais que o esperado, será necessário tomar uma das seguintes medidas: Alteração no quadro de pareamentos; Adoção de mais treinamentos, entre outras práticas. |

#### **Métricas para o objetivo 02(Código Fonte)**

* Métrica 07 - Cobertura de Testes do Código

| **Métrica**           | **Cobertura de Testes do Código Fonte*** |
|---------------------|----------|
| **Objetivo de Medição** | Garantir se o *software* funciona de acordo com o comportamento esperado, e consequentemente, sua qualidade. |
| **Descrição**           | Determina por meio de um porcentual a quantidade de código que foi testada |
| **Fórmula**             | No contexto de código fonte, a cobertura do código é dado por: `CoberturadoCódigo=LOC/LCT` <br>No LOC é a quantidade de linhas de código (_Lines of Code_) e LCT é a quantidade de linhas de código testadas |
| **Escala**              | Racional |
| **Coleta**              |  Responsável: Miguel Pimentel </br> Periodicidade: Semanalmente |
| **Procedimento**        | Submeter o código fonte a ferramenta nativa de *test coverage* presente no Android Studio |
| **Análise**              | O projeto se divide em dois momentos:<br>Release I : onde ao final da release a cobertura deve ser maior ou igual a 40%<br> - [40, 100[ - Completo <br> - [0, 40[ - Incompleto <br>Release II: onde ao final da release a cobertura deve ser maior ou igual a 90%<br> - [90, 100[ - Completo<br> - [0, 90[ - Incompleto|
| **Providência**        |  Caso o status de cobertura de teste se encontre como incompleta, deve-se adicionar histórias com esta finalidade, realizar mais treinamentos, e mudar o quadro de pareamentos.|

* Métrica 08 - Duplicação de Código

| **Métrica** | Duplicação de Código |
| --- | --- |
| **Objetivo de Medição** | Evitar a redundância de código no projeto, desta forma, criando componentes que possam ser reutilizados. |
| **Descrição** | A duplicação de código consiste na repetição de uma determinada parte de código. |
| **Fórmula** | O _detekt_ utiliza árvore sintática abstrata (AST) para definir se o código está ou não duplicado. Assim, ele compara os valores do nós com objetivo de encontrar valores idênticos. |
| **Escala** | Racional |
| **Coleta** | Responsável: Miguel Pimentel <br>Periodicidade ou Evento: A cada iteração (Semanal ou Quinzenal) |
| **Procedimento** | Submeter código fonte a ferramenta Detekt |
| **Análise** | Se MC > X - Código Duplicado<br>Se MC <= X - Código Não Duplicado |
| **Providência** | Caso o estado do arquivo esteja como duplicado, deve-se utilizar da abordagem apresentada no *Code Craft*, ou seja, o refatoramento a partir da técnica: "*Do not repear yourself*". |

* Métrica 09- Média da complexidade ciclomática dos métodos (_Average McCabe’s Cyclomatic Complexity_)

| **Métrica** | Média da complexidade ciclomática dos métodos(AMCC) |
| --- | --- |
| **Objetivo de Medição** | Melhorar a eficiência e complexidade do código, tornando-o mais manutenível|
| **Descrição** | Complexidade ciclomática é o número de possibilidades independentes que um software pode seguir em sua execução, calculado a partir de grafos em fluxos de controle. Esta métrica normalmente esta associada a uma classe. |
| **Fórmula** |  Dado um grafo de fluxo que representa o método: `MCC = E- N+2`. Onde, MCC é a complexidade ciclomática do método, E é o número de arestas e N é o número de nós do grafo G. Diante disso, a média da complexidade ciclomática dos métodos de uma classe é dada por:  `ACCM = (1/n) ∑CC(Métodoi)`. Onde n é quantidade de métodos da classe e i representa cada um dos métodos.|
| **Escala** | Racional |
| **Coleta** | Responsável: Geovanni Oliveira  <br>Periodicidade ou Evento: A cada iteração (Semanal ou Quinzenal) |
| **Procedimento** | Submeter código fonte a ferramenta ao Detekt |
| **Análise** | <ul> <li>[X, Y[ - Excelente </li> <li>[Y, Z[ - Bom </li><li> [Z, T[ - Regular </li> <li> [T, ∞[ - Preocupante</li> </ul>  |
| **Providência** | Caso o módulo ou classe se encontre nas classificações de Regular ou Preocupante, deve-se optar pela refatoração, principalmente fazendo uso de funções atômicas. |

* Métrica 10 - Cobertura de Testes do Código

| **Métrica**           | **Cobertura de Testes do Código Fonte*** |
|---------------------|----------|
| **Objetivo de Medição** | Garantir se o *software* funciona de acordo com o comportamento esperado, e consequentemente, sua qualidade. |
| **Descrição**           | Determina por meio de um porcentual a quantidade de código que foi testada |
| **Fórmula**             | No contexto de cósigo fonte, a cobertura do código é dado por: `CoberturadoCódigo=LOC/LCT` <br>No LOC é a quantidade de linhas de código (_Lines of Code_) e LCT é a quantidade de linhas de código testadas |
| **Escala**              | Racional |
| **Coleta**              | Responsável: Miguel Pimentel </br> Periodicidade: Semanalmente |
| **Procedimento**        | Submeter o código fonte a ferramenta nativa de *test coverage* presente no Android Studio |
| **Análise**              | O projeto se divide em dois momentos:<br>Release I : onde ao final da release a cobertura deve ser maior ou igual a 40%<br> - [40, 100[ - Completo <br> - [0, 40[ - Incompleto <br>Release II: onde ao final da release a cobertura deve ser maior ou igual a 90%<br> - [90, 100[ - Completo<br> - [0, 90[ - Incompleto|
| **Providência**        |  Caso o status de cobertura de teste se encontre como incompleta, deve-se adicionar história com esta finalidade, realizar mais treinamentos,  e mudar quadro de pareamentos.|

* Métrica 11 - Code *Style*

| **Métrica**           | *Code Style* |
|---------------------|----------|
| **Objetivo de Medição** | Ver se o código fonte está de acordo com as normas apresentadas na folha de estilo |
| **Descrição**           | Verificar se o código desenvolvido segue as normas apresentadas na folha de estilo |
| **Fórmula**             | Não se aplica |
| **Escala**              | Ordinal |
| **Coleta**              | Responsável: Guilherme Baldissera e Miguel Pimentel </br> Periocidade: Semanalmente  |
| **Procedimento**        | Será realizado durante a integração contínua por meio da ferramenta Detekt. E também pela avaliação dos Pull Requests pelos membros de EPS |
| **Análise**             | Pode ser definido da seguinte forma: </br> Atende - Quando aceito pelos *committers* e Detekt(CI) </br> Não aceito - Quando recusado pelos *committers* ou Detekt(CI) |
| **Providência**        | Refatoração do código aos padrões definidos na folha de estilo

#### **Métricas para o objetivo 03(Riscos do Projeto)**

| **Métrica**           | *Burndown de Riscos* |
|---------------------|----------|
| **Objetivo de Medição** | Compreender se os riscos do projeto se reduzem a cada *sprint* |
| **Descrição**           | A partir da identificação dos riscos, essa métrica tem como fundamento possibilitar o monitoramente e gerenciamento destes |
| **Fórmula**             | Não se aplica |
| **Escala**              | Ordinal |
| **Coleta**              | Responsável: Guilherme Baldissera, Miguel Pimentel, Luís Gustavo e Geovanni Oliveira </br> Periocidade: Semanalmente  |
| **Procedimento**        | Ao final de cada *sprint* os membros de EPS se reunirão para decidir como cada risco pode impactar o projeto |
| **Análise**             | Pode ser definido da seguinte forma: </br> Sob Controle - se Exposição de Risco < 15 </br> Preocupante - se Exposição de Risco > 15 </br>|
| **Providência**        | Caso os riscos se encontrem como preocupante, deve-se realizar ações e medidas para reduzí-los. 


## **Observações**

*  Ressalta-se que algumas das métricas, pode ser mais bem compreendidas como indicadores, visto que agrega mais valor a parte interna da organizacão.

*  Os pontos serão definidos a partir da prática do *planning poker*, e utilizarão os valores apresentados pela sequência de Fibonacci.
