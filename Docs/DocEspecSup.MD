### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 19/03/2018 | 0.1 | Criação do documento | Larissa Sales |
| 19/03/2018 | 0.2 | Preenchimento de Introdução, Usabilidade, Suportabilidade, Restrições de Design e Interface | Larissa Sales, Helena Goulart, Alexandre Miguel |
| 20/03/2018 | 0.3 | Preenchimento de Requisitos de Licenciamento, Observações Legais e Padrões Aplicáveis | Larissa Sales |
| 20/03/2018 | 0.4 | Preenchimento de Confiabilidade e Desempenho e modificação de Introdução | Larissa Sales, Alexandre Miguel e Helena Goulart |

-------------------------------------------------------------------------------------------------

### Índice Analítico
* [**1 Introdução**](#1-Introdução)
  * [1.1 Propósito](#11-Propósito)
  * [1.2 Escopo](#12-Escopo)
  * [1.3 Definições, acrônimos e abreviações](#13-Definições,-acrônimos-e-abreviações)
  * [1.4 Referências](#14-Referências)
  * [1.5 Visão Geral](#15-Visão-geral)
* [**2 Funcionalidade**](#2-Funcionalidade)
* [**3 Usabilidade**](#3-Usabilidade)
  * [3.1 Mensagens de Erro](#31-Mensagens-de-Erro)
  * [3.2 Facilidade de Uso](#32-Facilidade-de-Uso)
* [**4 Confiabilidade**](#4-Confiabilidade)
* [**5 Desempenho**](#5-Desempenho)
* [**6 Suportabilidade**](#6-Restrições)
  * [6.1 Suportabilidade para _Android_](#61-Suportabilidade-para-_Android_)
* [**7 Restrições de Design**](#7-Restrições-de-Design)
  * [7.1 Compatibilidade](#71-Compatibilidade)
  * [7.2 Aparência Geral](#72-Aparência-Geral)
* [**8 Interface**](#8-Interface)
    * [8.1 Interfaces de Usuário](#81-Interfaces-de-Usuário)
    * [8.2 Interfaces de _Hardware_](#82-Interfaces-de-_Hardware_)
    * [8.3 Interfaces de _Software_](#83-Interfaces-de-_Software_)
    * [8.4 Interfaces de Comunicação](#84-Interfaces-de-Comunicação)
* [**9	Requisitos de Licenciamento**](#9-Requisitos-de-Licenciamento)
* [**10	Observações Legais, de _Copyright_ e Outras**](#10-Observações-Legais,-de-_Copyright_-e-Outras)
* [**11	Padrões Aplicáveis**](#11-Padrões-Aplicáveis)


## 1 Introdução

### 1.1 Finalidade

Este documento apresenta os requisitos que não estão relacionados diretamente ao usuário, especificando os requisitos não funcionais do software, sendo complementar ao Documento de Arquitetura.

### 1.2 Escopo

O projeto contemplado por este documento foi desenvolvido por alunos das disciplinas de MDS e EPS do curso de Engenharia de Software da Universidade de Brasília e busca, por meio das restrições e dos requisitos aqui descritos, garantir o engajamento e a interação entre jogadores de tênis por meio de uma aplicação _mobile_.

### 1.3 Definições, Acrônimos e Abreviações   

  - UnB - Universidade de Brasília;
  - MDS - Métodos de Desenvolvimento de Software;
  - EPS - Engenharia de Produto de Software;
  - RUP - _Rational Unified Process_.

### 1.4 Referências
* [O que é um Requisito Não-Funcional](http://www.ateomomento.com.br/o-que-e-um-requisito-nao-funcional/)
* [Especificação Suplementar](http://www.funpar.ufpr.br:8080/rup/webtmpl/templates/req/rup_sspec.htm)

## 2 Funcionalidade

Os aspectos funcionais estão contemplados no [Documento de Visão]() e no [Documento de Arquitetura]().

## 3 Usabilidade

### 3.1 Mensagens de Erro

Em caso de falha no sistema ou erro de conexão, o sistema apresentará uma mensagem de erro clara e objetiva, permitindo ao usuário identificar o erro apresentado.

### 3.2 Facilidade de Uso

O aplicativo será intuitivo e categorizado, assim como agradável e harmonioso, permitindo que o usuário identifique, sem dificuldade, as ações a serem realizadas, de modo que uma ação poderá ser desenvolvida em no máximo 6 cliques. Esta aplicação não possui opções de acessibilidade para usuários com deficiência.

## 4 Confiabilidade

Este requisito garante ao usuário a segurança do conjunto de informações cadastradas, a credibilidade dos dados apresentados e a convicção de acertividade nas ações propostas.

## 5 Desempenho

O tempo de resposta das solicitações dos usuários varia de acordo com o servidor utilizado, atingindo um tempo médio de 5 segundos. Em caso de erro interno, o usuário será notificado e o aplicativo será reiniciado.

## 6 Suportabilidade

### 6.1 Suportabilidade para _Android_

O aplicativo terá suporte para Android 5.1 ou superior.

## 7 Restrições de Design

### 7.1 Compatibilidade

O _software_ será compatível com o sistema operacional _Android_.

### 7.2 Aparência Geral

O _layout_ do produto será disposto de forma trivial e com esquema de cores equilibrado e harmonioso.

## 8  Interfaces            

### 8.1 Interfaces de Usuário  

As principais interfaces do usuário permitirão a criação e edição dos dados do perfil, acompanhamento do _ranking_ dos jogadores, reserva de quadra e de espaços para eventos.

### 8.2 Interfaces de _Hardware_

- Celular do tipo _Smartphone_.

### 8.3 Interfaces de _Software_  

- Sistema _Android_ 5.1 ou superior.

### 8.4 Interfaces de Comunicação

- Acesso remoto;
- Comunicação com _apps_ de midia social (_WhatsApp_, _e-mail_, _Facebook_, outros).

## 9 Requisitos de Licenciamento  

Licença MIT.

## 10 Observações Legais, de _Copyright_ e Outras

Copyright do aplicativo original criado em versão iOS, _Nexte_.

## 11 Padrões Aplicáveis

_Template_ de documentação baseado no Modelo de Processo RUP.
