## Histórico de Revisão

| Data | Versão | Descrição | Autor |
|:----:|:------:|:---------:|:-----:|
| 29/03/2018 | 0.1 | Criação do documento | Luis Gustavo |


## 1. Matriz de Exposição do Risco

A matriz de exposição do risco será utilizada para qualificar os riscos segundo sua prioridade. Dessa forma, a matriz de exposição do risco utiliza dois atributos: probabilidade, que corresponde a chance de ocorrência do risco, e o impacto, que refere-se tamanho da perda, em dias, que ocorreria caso o risco viesse a ocorrer. Ambas medidas serão estipuladas pela equipe de gerência.

### 1.1 Probabilidade

A probabilidade (P) é dado pela estimativa da equipe de gerência. Este atributo verifica a possibilidade de ocorrência com base nos aspectos mais relevantes do projeto.

  |**Probabilidade**   |**Intervalo(%)**  | **Peso**|
  |------------------- |----------------- |----------|
  |Muito Baixa         |De 0% a 20%          |  1|
  |Baixa               |De 21% a 40%        |   2|
  |Média               |De 41% a 60%        |    3|
  |Alta                |De 61% a 80%       |     4|
  |Muito Alta          |De 81% a 100%     |      5|

### 1.2 Impacto

Assim como a probabilidade é dada pela estimativa da equipe de gerência, o impacto é dado, também, pela estimativa da equipe de gerência analisando as principais variáveis do projeto.

  |**Impacto**   |**Descrição**                                       |**Peso**|
  |------------- |--------------------------------------------------- |----------|
  |Muito Baixo   |Menos de 0.3 dias (6 horas)                  |1|
  |Baixo         |De 6 horas a 1 dia                 |2|
  |Médio         |De 1 a 3 dias   |3|
  |Alto          |3 a 7 dias                    |4|
  |Muito Alto    |7 ou mais dias                  |5|


### 1.3 Exposição do Risco

A Exposição do Risco(E) é calculada com base no produto entre probabilidade (P)  e o Impacto (I). Este atributo determina a ordenação mais indicada para o tomada de decisão relacionada aos riscos do projeto.

 | ** P / I**    | Muito Baixo  | Baixo  | Médio   |Alto   |Muito Alto|
 | -------------| -------------| -------| ------- |------ |------------|
 | Muito Baixa |  1            | 2      | 3       |4      |5|
 | Baixa       |  2            | 4      | 6       |8      |10|
 | Média       |  3            | 6      | 9       |12     |15|
 | Alta        |  4            | 8      | 12      |16     |20|
 | Muito Alta  |  5            | 10     | 15      |20     |25|

#### 1.3.1 Nível de Exposição do Risco

| Nível de Prioridade | Intervalo |
| -------------| -------------|
| Baixa | De 1 a 4 |
| Média | De 6 a 12 |
| Alta | De 15 a 25 |

## 2. Principais Riscos do projeto

### 2.1 Registro

| ID | Descrição | Causa | Consequência |
|:----:|:------:|:---------:|:-----:|
| R01 | Dificuldade com a tecnologia | Inexperiência de ambas as equipes com a tecnologia | Atraso na entrega das atividades |
| R02 | Baixa produtividade dos integrantes do grupo | Desmotivação dos membros da equipe |  Atraso na entrega das atividades |
| R03 | Problema com a configuração de ambiente | Ineperiência de membros da equipe de gerência em utilizar docker com android |  Não na entrega das atividades completas |
| R04 | O servidor RestFull de requisições não estar pronto. | Time externo do Nexte não desenvolver o servidor a tempo| Histórias não serão finalizadas, pois os dados não estarão disponíveis |
| R05 | Membro(s) da equipe de gerência ter seus projetos selecionados para a WWDC2018. | Membros da equipe de gerência tiveram que enviaram projetos para o WWDC 2018 Scholarship| Membros da equipe de gerência que forem selecionados ficarão aproximadamente 2 semanas fora do país, dessa forma não terão tempo para se dedicar ao projeto|
| R06 | Atividades atrasadas | Dificuldade com a tecnologia/Desmotivação dos membros da equipe/Baixa produtivade | Entregas incompletas |

## 2.2 Priorização
| ID | Probabildade | Impacto | Exposição do Risco |
|:----:|:------:|:---------:|:-----:|
| R01 | Muito Alta(5) | Muito Alto(5) | Alta(25) |
| R02 | Média(3) | Muito Alto(5) | Alta(15) |
| R03 | Alta(4) | Muito Alto(5) |  Alta(20) |
| R04 | Média(3) | Muito Alto(5) | Alta(15) |
| R05 | Média(3) | Muito Alto(5)| Alta(15)|
| R06 | Media(3) | Alto(4) | Média(12)|
