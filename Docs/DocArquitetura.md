### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 16/03/2018| 0.1 |Criação do documento | Gabriel Albino, Letícia Meneses |
| 17/03/2018| 0.1.1 |Correção do índice analítico | Gabriel Albino |


### Índice Analítico

* [1 INTRODUÇÃO](#1-introdução)
    * [1.1 Finaliade](#11-finaliade)
    * [1.2 Escopo](#12-escopo)
    * [1.3 Definições, Acrônimos e Abreviações](#13-definições-acrônimos-e-abreviações)
    * [1.4 Referências](#14-referências)
* [2. REPRESENTAÇÃO ARQUITETURAL](#2-representação-arquitetural)
* [3. REQUISITOS E RESTRIÇÕES DE ARQUITETURA](#3-requisitos-e-restrições-de-arquitetura)
* [4. VISÃO DE CASO DE USO](#4-visão-de-caso-de-uso)
    * [Lista de casos de uso do sistema:](#lista-de-casos-de-uso-do-sistema)
    * [4.1 Casos de Uso significantes para a arquitetura](#41-casos-de-uso-significantes-para-a-arquitetura)
* [5. VISÃO LÓGICA](#5-visão-lógica)
    * [5.1 Visão Geral – pacotes e camadas](#51-visão-geral-–-pacotes-e-camadas)
* [6. VISÃO DE IMPLEMENTAÇÃO](#6-visão-de-implementação)
    * [6.1 Caso de Uso](#61-caso-de-uso)
    * [6.1.1  <em>Diagrama de Classes</em>](#611--diagrama-de-classes)
    * [6.1.2 <em>Diagrama de Sequência</em>](#612-diagrama-de-sequência)
* [7. VISÃO DE IMPLANTAÇÃO](#7-visão-de-implantação)
* [8. DIMENSIONAMENTO E PERFORMANCE](#8-dimensionamento-e-performance)
    * [8.1. Volume](#81-volume)
    * [8.2. Performance](#82-performance)
* [9. QUALIDADE](#9-qualidade)

# Documento de arquitetura

### Sigla/Nome do projeto

| Gestor do projeto | Gerente do projeto |
|:-:|:-:|
| Nome | Nome |
| E-mail | E-mail |
| Telefone | Telefone |

### Objetivo desse documento

Este documento tem como objetivo descrever as principais decisões de projeto tomadas pela equipe de
desenvolvimento e os critérios considerados durante a tomada destas decisões. Suas informações incluem a
parte de hardware e software do sistema.

## 1 INTRODUÇÃO

### 1.1 Finaliade
Este documento fornece uma visão arquitetural abrangente do sistema [nome do sistema],
usando diversas visões de arquitetura para representar diferentes aspectos do sistema. O objetivo
deste documento é capturar e comunicar as decisões arquiteturais significativas que foram tomadas
em relação ao sistema.
O documento irá adotar uma estrutura baseada na visão “4+1” de modelo de arquitetura [KRU41]

![Arquitetura 4+1](  https://image.slidesharecdn.com/20090108slides-modelagem-arquitetural-1231554342642224-2/95/modelagem-arquitetural-e-viso-41-17-728.jpg?cb=1231526651)

### 1.2 Escopo
Este Documento de Arquitetura de Software se aplica ao [nome do sistema], que será
desenvolvido pela [área / equipe]. 

### 1.3 Definições, Acrônimos e Abreviações
QoS – Quality of Service, ou qualidade de serviço. Termo utilizado para descrever um conjunto de
qualidades que descrevem as requisitos não-funcionais de um sistema, como performance,
disponibilidade e escalabilidade[QOS]. 

### 1.4 Referências
[KRU41]: The “4+1” view model of software architecture, Philippe Kruchten, November 1995,
http://www3.software.ibm.com/ibmdl/pub/software/rational/web/whitepapers/2003/
Pbk4p1.pdf

[QOS] https://docs.oracle.com/cd/E19636-01/819-2326/6n4kfe7dj/index.html

## 2. REPRESENTAÇÃO ARQUITETURAL
Este documento irá detalhar as visões baseado no modelo “4+1” [KRU41], utilizando como
referência os modelos definidos na MDS. As visões utilizadas no documento serão: 

| Visão | Púbico | Área | Modelo da MDS |
|-------|--------|------|---------------|
|Lógica|Analistas|Realizações do caso de uso||
|Processo|Integradores|Performance, escalabilidade, concorrência||
|Implementação|Programadores|Componentes de software||
|Implantação|Gerência de configuração|Nodos físicos||
|Casos de uso|Todos|Requisitos funcionais||
|Dados|Especialsitas em dados e Administradores de dados|Persistência de dados||

## 3. REQUISITOS E RESTRIÇÕES DE ARQUITETURA

Esta seção descrever os requisitos de software e restrições que tem um impacto significante na
arquitetura.

|Requisito|Solução|
|---|---|
|Linguagem  |[Especificar a(s) linguagem(ns) utilizada(s) no desenvolvimento.]|
|Plataforma|[Especificar o servidor de aplicações utilizado.]|
|Segurança|[Especificar a necessidade de segurança e as características básicas da segurança.]|
|Persistência|[Especificar a necessidade de persistência e qual o mecanismo de persistência que será adotado.]|
|Internacionalização(i18n)|[Especificar a necessidade de internacionalização/localização na aplicação.]|


## 4. VISÃO DE CASO DE USO

Esta seção lista as especificações centrais e significantes para a arquitetura do sistema.
### Lista de casos de uso do sistema:
- Caso de Uso [00X]
- Caso de Uso [00X]

### 4.1 Casos de Uso significantes para a arquitetura
![Exemplo de Diagrama com os casos de uso significativos e atores](https://i.imgur.com/uob7Xn4.png)

## 5. VISÃO LÓGICA
Descrever uma visão lógica da arquitetura. Descrever as classes mais importantes, sua
organização em pacotes de serviços e subsistemas, e a organização desses subsistemas em
camadas. Também descreve as realizações dos casos de uso mais importantes, por exemplo,
aspectos dinâmicos da arquitetura. Diagramas de classes e sequência devem ser incluídos para
ilustrar os relacionamentos entre as classes significativas na arquitetura, subsistemas, pacotes e
camadas. 

### 5.1 Visão Geral – pacotes e camadas 

![ Exemplo de Diagrama de Camadas da Aplicação](https://i.imgur.com/f7Y7Tdg.png) 

![ Exemplo de Diagrama de Pacotes da Aplicação ](https://i.imgur.com/9paYDxg.png)



## 6. VISÃO DE IMPLEMENTAÇÃO
 
### 6.1 Caso de Uso
 
#### 6.1.1  *Diagrama de Classes*
[Exemplo:](https://wandersonalvesdev.files.wordpress.com/2013/02/diagrma-classe.png)
 
![Exemplo:](https://wandersonalvesdev.files.wordpress.com/2013/02/diagrma-classe.png)
 
#### 6.1.2 *Diagrama de Sequência*
[Exemplo:](https://camo.githubusercontent.com/4cc153d724bbed2843bfb19e638477a7c504b9c9/687474703a2f2f692e696d6775722e636f6d2f517868526875322e706e67)
 
![Exemplo:](https://camo.githubusercontent.com/4cc153d724bbed2843bfb19e638477a7c504b9c9/687474703a2f2f692e696d6775722e636f6d2f517868526875322e706e67)
 
 
 
## 7. VISÃO DE IMPLANTAÇÃO
 
​ Descrever os nodos físicos, as configurações e os artefatos que serão implantados.
 
 
 
[Exemplo:](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412.jpeg)
 
 
 
![Exemplo:](https://d2slcw3kip6qmk.cloudfront.net/marketing/pages/chart/uml/deployment-diagram/deployment-diagram-example-700x412.jpeg)
 
 
 
 
 
## 8. DIMENSIONAMENTO E PERFORMANCE
 
### 8.1. Volume
Enumerar os itens relativos ao volume de acesso aos recursos da aplicação:
 
* Número estimado de usuários:
* Número estimado de acessos diários:
* Número estimado de acessos por período:
* Tempo de sessão de um usuário:
 
### 8.2. Performance
Enumerar os itens referentes à resposta esperada do sistema:
 
* Tempo máximo para a execução de determinada transação:
 
 
## 9. QUALIDADE
Enumerar os itens de qualidade de software [QOS] significativos para a aplicação:
 
| Item  | Solução  | Descrição  |
|:---:|:---:|:---:|:---:|
|  Escalabilidade | [Breve Descrição]  |[Breve descrição da Solução]   |
|  Confiabilidade, Disponibilidade | [Breve Descrição]  | [Breve descrição da Solução]  |  
| Portabilidade  | [Breve Descrição]  |  [Breve descrição da Solução] |
| Segurança  | [Breve Descrição]  |  [Breve descrição da Solução] |