
## Benchamark

### 1. Objetivo

Com o objetivo de obter métricas e compreender *gaps* de desempenho da aplicação foram realizados *benchmarks* sob o ponto de vista do arquiteto de software. A partir desses resultados é possível identificar possíveis melhorias em partes específicas da aplicação, e assim realizar ações para corrigi-las. Foram realizados *benchmarks* no aplicação iOS e Android, ambas com funcionalidades equivalentes. 


### 2. Tarefas Mensuradas

No processo de mensuração dos dados sobre CPU, bateria e consumo de memótia foram realizadas todas as tarefas descritas a seguir nas plataformas iOS e Android:

#### 2.1 Realizar Login

* Ação de login da aplicação apartir do *username* e senha.

#### 2.2 Visualizar Perfil

* Visualizar perfil e suas estatísticas de vitórias, derrotas, jogos e posição ao longo do tempo. 
* Mudar o status atual(afastado, disponível, machucado) 
* Acessar contatos whatsapp, telefone e adicionar contato.

#### 2.3 Visualizar Ranking

* Visualizar posições do ranking 

#### 2.4 Visualizar Feed

* Visualizar feed 
* Curtir publicações 
* Comentar em publicações  

#### 2.5 Desafiar Oponente

* Selecionar oponente  
* Confirmar desafio 
* Colocar resultados para o desafio criado 


### 3. *Benchmark* iOS

A medição da aplicação iOS foi realizada por meio do *instruments*, e os arquivos com os *benchmarks* encontram-se disponíveis em nosso Google Drive. Não obstante, foram realizados *benchmarks* para casa uma das tarefas citadas a seguir, sendo que para cada uma categoria geral foi gerado um relatório. [Clique aqui para acessar o benchmarks referentes ao iOS](https://drive.google.com/drive/folders/1nhNff6rZr_pc9mGPFH-lXtjwPxksNDfx?usp=sharing)

**Observação:** Teste realizado no Iphone 6S na versão iOS 11.3

### 4. *Benchmark* Android

O processo de *benchmark* do Android foi realizado a partir do Android Profiler. Os dados para cada uma das tarefas estão apresentados nas tarefas a seguir. Os prints dos resultados encontrados, encontram-se disponíveis a seguir. [Clique aqui para acessar o benchmarks referentes ao Android](https://drive.google.com/open?id=1QUbCals5_5-CCnYV8gVEk1jSyS1s52hQ)

**Observação:** Teste realizado no Xiaomi Mi A1 na versão 7.1.2 do Android

#### 4.1 Realizar Login

| Medição | Tempo de Execução | Dados Recebidos (KB/s) | Dados Enviados (KB/s)) | Uso de Memória (em MB) | Consumo de CPU (%) |   |
|---------------------|-------------------|------------------------|------------------------|------------------------|--------------------|---|
| 1 | 0:00:02 | 0,5 | 53,3 | 135,49 | 9,82 |   |
| 2 | 0:00:02 | 5,47 | 37,42 | 157,52 | 23,05 |   |
| 3 | 0:00:01 | 2,45 | 6,91 | 104,77 | 35,27 |   |

#### 4.2 Visualizar Perfil

| Medição | Tempo de Execução | Dados Recebidos (KB/s) | Dados Enviados (KB/s)) | Uso de Memória (em MB) | Consumo de CPU (%) |   |
|---------------------|-------------------|------------------------|------------------------|------------------------|--------------------|---|
| 1 | 0:00:03 | 0 | 0 | 135,45 | 16,41 |   |
| 2 | 0:00:03 | 0 | 0 | 161,54 | 31,69 |   |
| 3 | 0:00:01 | 0 | 0 | 162,88 | 1,53 |   |

#### 4.3 Visualizar Ranking

| Medição | Tempo de Execução | Dados Recebidos (KB/s) | Uso de Memória (em MB) | Uso de Memória (em MB) | Consumo de CPU (%) |    |
|---------------------|-------------------|------------------------|------------------------|------------------------|--------------------|---|
| 1 | 0:00:03 | 27,17 | 23,17 | 156,9 | 14 |   |
| 2 | 0:00:01 | 0 | 0 | 140,27 | 0 |   |
| 3 | 0:00:01 | 0 | 0 | 204,35 | 15,93 |   |

#### 4.4 Visualizar Feed

| Medição             | Tempo de Execução | Dados Recebidos (KB/s) | Uso de Memória (em MB) | Uso de Memória (em MB) | Consumo de CPU (%) |   |
|---------------------|-------------------|------------------------|------------------------|------------------------|--------------------|---|
| 1 | 0:00:02 | 0 | 10,61 | 161,55 | 8,36 |   |
| 2 | 0:00:01 | 0 | 0 | 168,14 | 17,82 |
| 3 | 0:00:01 | 0 | 0 | 152,7 | 12,32 |   |         

#### 4.5 Desafiar Oponente

| Medição             | Tempo de Execução | Dados Recebidos (KB/s) | Uso de Memória (em MB) | Uso de Memória (em MB) | Consumo de CPU (%) |   |
|---------------------|-------------------|------------------------|------------------------|------------------------|--------------------|---|
| 1 | 0:00:02 | 0 | 0 | 140,28 | 14,75 |   |
| 2 | 0:00:03 | 0 | 0 | 147,34 | 4,47 |
| 3 | 0:00:02 | 0 | 0 | 152,99 | 20,73 |   |         


### 5. Conclusão

Pode-se observar pelos *benchmarks* do iOS que a aplicação está fluída e atualmente não apresenta gargalos de performance. Entretanto, foi possível concluir que principalmente ao carregar um nova tela(fragment/activity) o consumo de hardware é exigido com mais intensidade, e esse foi um dos GAPS encontrados com este benchmark. Não obstante, identificou-se que este gargalo pode ser oriundo de dois fatores:
 
 * Fluxo de telas: Controle de instancias e fluxo entre fragments e Acitvities;

 * Requisições ao servidor;

Em futuras releases, o time irá gastar esforços para obter melhores métricas..