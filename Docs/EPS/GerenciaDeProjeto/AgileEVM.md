### Histórico de reivsão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 19/03/2018| 0.1 | criação do Documento| Geovanni Oliveira|
| 22/03/2018| 0.2 | criação das nomenclaturas a serem usadas e definições| Geovanni Oliveira|
----
PRP - pontos total planejado para a Release
Schedule base time = número de sprints planejadas vezes a duração delas
BAC - total de despesas planejadas até o final da Release
PPC - Planed Percente completed número da sprint atual / total de sprints planejadas

### Introdução
Este documento é criado para saber o valor agregado e o andamento do projeto levando em consideração o lado do custo. Algumas siglas serão utilizadas para auxiliar a confecção do documento e a interpretação de seus resultados.

Tabela 1 - significado das siglas a serem utilizadas
| Sigla | Definição |
| ----| ----|
| BAC | Total de despesas planejado para a Release |
| L | Duração da Sprint |
| PS | número total de sprints planejadas para a Release |
| AC | custo real, | 
| SD | Data de inicio até chegar na Release |
| PRP | Pontos planejados para serem feitos na Release |
| RPC  | Pontos concluidos na Release
| APC | Porcentagem real de conclusão da Release |
| PPC | porcentagem planejada de conclusão |


Dadas algumas siglas principais que serão trabalhadas nesse documento, também baseado no artigo [1], as equacões de custo são levantadas da conforme a tabela 2

Tabela 2 - Equações de custo do projeto e definições

| Equação | Definição |
| ---- | ---- |
| PV =  PPC * BAC | Valor planejado |
| EV = APC * BAC | valor agregado |
| CV = EV - AC | variancia de custo |
| SV = EV - PV | variancia no cronograma |
| CPI = EV / AC | indice de performance de custo |
| SPI = EV / PV |  indice de performance do cronograma|
| ETC = 1 / CPI * ( BAC - EV) | Estimativa para concluir |
| EAC = AC + ECT | Estimativa no final  |


Ao final de cada sprint os dados perinentes seram mensurados para obter o insumo para a análise de valor agregado ao projeto.

[link para a planilha](https://docs.google.com/spreadsheets/d/1-YfPLlNFHWocxRSZ3WpIfGgju3ALPO0iJjQ94Ivyixs/edit?usp=sharing)

### Referências