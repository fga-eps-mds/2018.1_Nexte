### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 19/03/2018 | 0.1 | Criação do documento | Larissa Sales |
| 19/03/2018 | 0.2 | Preenchimento de Introdução, Usabilidade, Suportabilidade, Restrições de Design e Interface | Larissa Sales, Helena Goulart e Alexandre Miguel |
| 20/03/2018 | 0.3 | Preenchimento de Requisitos de Licenciamento, Observações Legais e Padrões Aplicáveis | Larissa Sales |
| 20/03/2018 | 0.4 | Preenchimento de Confiabilidade e Desempenho e modificação de Introdução | Larissa Sales, Alexandre Miguel e Helena Goulart |
| 21/03/2018 | 0.4.1 | Alteração do Nome do Arquivo | Alexandre Miguel |
| 22/03/2018 | 0.5 | Alteração dos Tópicos de Confiabilidade e Usabilidade | Alexandre Miguel, Lorrany Freire, Helena Goulart e Larissa Sales |
| 22/03/2018 | 0.6 | Padronização de tópicos e revisão | Larissa Sales |
| 22/03/2018 | 0.6.1 | Alterado diretório do arquivo | Gabriel Albino e Larissa Sales |
| 22/03/2018 | 0.7 | Alterado tópico de desempenho e correção do índice | Alexandre Miguel, Helena Goulart e Larissa Sales |
| 22/03/2018 | 0.8 | Correção do índice | Alexandre Miguel, Helena Goulart e Larissa Sales |
| 22/03/2018 | 0.9 | Correção do índice e formatação de tabela de erros| Alexandre Miguel |
-------------------------------------------------------------------------------------------------

### Índice Analítico
* [1 Introdução](#1-introdução)
  * [1.1 Finalidade](#11-finalidade)
  * [1.2 Escopo](#12-escopo)
  * [1.3 Definições, acrônimos e abreviações](#13-definições-acrônimos-e-abreviações)
  * [1.4 Referências](#14-referências)
* [2 Funcionalidade](#2-funcionalidade)
* [3 Usabilidade](#3-usabilidade)
* [4 Confiabilidade](#4-confiabilidade)
* [5 Desempenho](#5-desempenho)
* [6 Suportabilidade](#6-suportabilidade)
* [7 Restrições de Design](#7-restrições-de-design)
* [8 Interfaces](#8-interfaces)
  * [8.1	Interfaces de Usuário](#81-interfaces-de-usuário)
  * [8.2	Interfaces de _Hardware_](#82-interfaces-de-hardware)
  * [8.3 Interfaces de _Software_](#83-interfaces-de-software)
  * [8.4 Interfaces de Comunicação](#84-interfaces-de-comunicação)
* [9 Requisitos de Licenciamento](#9-requisitos-de-licenciamento)
* [10 Observações Legais, de _Copyright_ e Outras](#10-observações-legais-de-copyright-e-outras)
* [11	Padrões Aplicáveis](#11-padrões-aplicáveis)

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
* [User Onboarding](http://useronboarding.com.br/user-onboard/)


## 2 Funcionalidade

Os aspectos funcionais estão contemplados no [Documento de Visão]() e no [Documento de Arquitetura]().

## 3 Usabilidade

 - O aplicativo será intuitivo e categorizado, permitindo que o usuário identifique facilmente as ações a serem realizadas, fazendo uso de um sistema de _user-onboarding_ para auxiliá-lo na primeira utilização da aplicação;
 - Para a comodidade de utilização, a distância entre as funcionalidades possuirá a menor quantidade de cliques possível;
 - Em caso de falha no sistema ou de problemas de conexão, será apresentada uma mensagem clara e objetiva que permita ao usuário a identificação do erro apresentado;
 - O aplicativo não possui opções de acessibilidade motora ou visual.

## 4 Confiabilidade

 - O Software estará disponível a todo momento para o usuário, exceto nos períodos de manutenção;
  - As informações cadastradas serão armazenadas no servidor, sem visibilidade para os demais usuários, garantindo a segurança de dados pessoais.
 - Os Erros estão classificados da seguinte forma:

|Tipo de Erro| Definição |Exemplificação|
|----|----|----|
| Erro Pouco Importante |Impedimento provisório ou de rápida duração| Impedimento na atualização dos dados de perfil |
|Erro Importante | Falha estrutural ou de desenvolvimento | Desempenho incompleto ou ausente das funcionalidades|
|Erro Crítico| Falha extendida a várias usuários ou que afete o escopo geral do funcionamento da aplicação |Falha de Comunicação com o servidor, perda de dados cadastrados, falha de autenticação de usuário, total incapacidade de utilização do sistema|



## 5 Desempenho

 - A aplicação fará uso, preferencialmente, das conexões Wi-fi disponíveis e, na ausência destas, o uso de internet móvel como 3G ou 4G;
 - Ocorrerá alocação na memória interna dos resultados das partidas realizadas mesmo na ausência de conexão com o servidor, atualizando os dados quando a conexão for restabelecida;
 - Quando executado em segundo plano, o _Nexte_ consumirá o menor recurso possível de processamento de dados, destinando a maior parte do processamento à identificação de partidas.

## 6 Suportabilidade

- Inicialmente o suporte será dado em forma de criação de _issues_ no repositório do projeto;

- Não haverá atendimento imediato ao usuário.

## 7 Restrições de Design

O _layout_ do produto será disposto de forma trivial e com esquema de cores equilibrado e harmonioso.

## 8  Interfaces            

### 8.1 Interfaces de Usuário  

- Aplicativo instalado no celular.

### 8.2 Interfaces de _Hardware_

- Celular do tipo _Smartphone_.

### 8.3 Interfaces de _Software_  

- Sistema _Android_ 5.1 ou superior.

### 8.4 Interfaces de Comunicação

- Acesso remoto ao servidor;
- Comunicação com _apps_ de midia social (_WhatsApp_, _e-mail_, _Facebook_, outros).

## 9 Requisitos de Licenciamento  

Licença MIT.

## 10 Observações Legais, de _Copyright_ e Outras

Copyright do aplicativo original criado em versão iOS, _Nexte_.

## 11 Padrões Aplicáveis

_Template_ de documentação baseado no Modelo de Processo RUP.
