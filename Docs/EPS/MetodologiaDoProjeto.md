## Histórico de Revisão

| Data | Versão | Descrição | Autor |
|:----:|:------:|:---------:|:-----:|
| 13/03/2018 | 0.1 | Criado estrutura inicial do documento  | Luis Gustavo |
| 13/03/2018 | 0.2 |  Adicionado Papel do scrum master e time de desenvolvimento, introdução e ritos | Luis Gustavo |
| 13/03/2018 | 0.3 |  Adicionado nova responsabilidade para a equipe de desenvolvimento | Luis Gustavo |
| 15/03/2018 | 0.4 |  Arrumado sumário | Luis Gustavo |
| 15/03/2018 | 0.5 |  Adicionando papel arquitetura| Miguel Pimentel |
| 15/03/2018 | 0.6 |  Adicionando metodologia XP e Scrum| Miguel Pimentel |
| 15/03/2018 | 0.7 |  Adicionando papel DevOps| Miguel Pimentel |



# Sumário

[1. Introdução](#1-introdução)

[2. Papéis](#2-papéis)

[3. Scrum Ritos](#3-ritos)

[4. XP](#4-xp)

[5. Kanban](#5-kanban)

[6. Referências](#6-referências)

## 1. Introdução
Ao longo de todo o projeto será empregado metodologia de desenvolvimento ágil. No projeto será utilizado uma adaptação do Scrum e também será empregados práticas das *frameworks* XP e Kanban.

## 2. Papéis

### 2.1. Scrum Master
Durante a *Release 1* o responsável por esse papel é o [Luis Gustavo](https://github.com/luis-gustavo). São tarefas do Scrum Master:

- Fazer gestão de riscos;
- Garantir que os membros da equipe estejam executando a metodologia;
- Ficar responsável por conduzir as reuniões;
- Manter os *stakeholders* a par do projeto;
- Resolver ou designar membros da equipe para resolver impedimentos;
- Documentar o planejamento e resultados das *sprints* na Wiki;
- Garantir que nos ritos os dados sobre a equipe e a *sprint* estejam disponíveis;
- Garantir que as reuniões sejam preparadas e que não demore mais que o tempo estipulado;

### 2.2. DevOps

Neste projeto foi definido como arquiteto devOps o membro [Guilherme Baldissera](https://github.com/guibaldissera) que tem como responsabilidades:

- Engajar-se em operações como um cliente e parceiro, em que “uma das partes interessadas é a primeira classe”, - em desenvolvimento.
- Envolver os desenvolvedores em tratamento de incidentes. 
- Garantir que todas as alterações de código e configurações sejam feitas usando mecanismos automatizados, rastreáveis ​​e repetíveis.
- Implantação contínua de mudanças do check-in para produção.
- Infraestrutura como código.

### 2.3. Arquitetura
No desenvolvimento do projeto foi definido como responsável pela arquitetura o membro [Miguel Pimentel](https://github.com/miguelpimentel) que tem como responsabilidade:

- Limitar as escolhas dentro do desenvolvimento em relação: A padrões de projetos a serem utilizados; Definir/Criar frameworks a serem utilizados no processo de desenvolvimento.
- Identificar pontos de reutilização de código no desenvolvimento do projeto por meio: Enxergar de forma mais abrangente; projeto baseando-se em componentes; e ter contato com todos os produtos desenvolvidos ao longo do projeto.
- Definir escolhas em relação: A arquitetura ser fragmentada em pequenos formatos como forma de diminuir a complexidade no processo de desenvolvimento;
- Ter domínio em relação: Função componentes; dependência de componentes; e comunicar arquitetura ao time de desenvolvimento.

### 2.4. Time de Desenvolvimento
São tarefas do time de desenvolvimento:

- Elaborar protótipos;
- Elaborar documento de visão, arquitetura e especificação suplementar;
- Desenvolver funcionalidades;
- Testar funcionalidades;
- Montar *backlog* da *sprint*;
- Elaborar folha de estilo;

## 3. Scrum Ritos
Cada *sprint* se inicia ao domingo e termina ao sábado.

### 3.1. *Daily Scrum*
As *daily scrum* são reuniões diárias feitas pela equipe e tem como objetivo disseminar conhecimento sobre o que foi feito no dia anterior, identificar impedimentos e priorizar o trabalho a ser realizado. Como os membros da equipe de desenvolvimento e os membros da equipe de gerência possuem horários muito diferentes, foi definido que as reuniões seriam da seguinte forma:

- **Segunda** e **Quarta** a reunião será via Hangouts às 21h30.
- **Terça** e **Quinta** a reunião será presencial, após a aula de EPS.
- **Sexta** e **Domingo** a reunião será feita via bot standuply no Slack, o bot poderá ser respondido de 20h até 23h59.

A reunião deverá durar até 15 minutos.

### 3.2. Revisão da *Sprint*
Ao final de cada *sprint* será feito uma reunião de revisão da *sprint*, o projeto será avaliado em relação aos objetivos da *sprint*, os será discutido o que foi concluído durante a *sprint* e, se necessário, o *product backlog* poderá ser alterado. A realização dessa reunião será feito durante a reunião de sábado da equipe e deverá durar até 45 minutos.

### 3.3. Retrospectiva da *Sprint*
A retrospectiva da *sprint* ocorrerá ao final de cada *sprint* nela serão avaliados os pontos que funcionaram bem durante a *sprint*, as melhoras que a equipe teve, o que pode ser melhorado e as ações a serem tomadas. Essa reunião ocorrerá durante a reunião de sábado da equipe e deverá durar até 1h30.

### 3.4. Planejamento da *Sprint*
Na reunião de planejamento de *sprint* será planejado todo o trabalho a ser feito na respectiva *sprint*, nela será definida o objetivo da *sprint*.Essa reunião ocorrerá durante a reunião de sábado da equipe e deverá durar até 2h.

## 4. XP

Além do SCRUM, o desenvolvimento deste projeto adotou algumas práticas do Extreme Programming(XP) como forma de promover práticas que tenham impacto diretamente em código, destacando-se:

- Jogo de Planejamento (Planning Game)
- Fases pequenas (Small Releases)
- Metáfora (Metaphor)
- Design Simples (Simple Design)
- Testes de Aceitação (Customer Tests)
- Programação Pareada (Pair Programming)
- Padronização do Codigo (Coding Standards)
- Refatoração (Refactoring)
- Integração Contínua (Continuous Integration)

## 5. Kanban

Oriundo do japonés KANBAN significa cartão ou sinalização. É um método para a implantação de mudanças que não prescreve papéis ou práticas específicas. No caso, ajudaria na nossa metodologia como algo suplementar, dando uma visão clara das tarefas, seus responsáveis e seu status. Para a implementação desta técnica, utilizou-se de ferramentas como o ZenHub e o taiga, ambos interligados ao Git e Slack.

## 6. Referências

><p align="justify">O que é o Scrum?. Disponível em: <a href="http://www.desenvolvimentoagil.com.br/scrum/">http://www.desenvolvimentoagil.com.br/scrum/</a>. Acessado em 13/03/2018</p>

<br/>

><p align="justify">Scrum Guide. Disponível em: <a href="https://www.scrumguides.org/scrum-guide.html">https://www.scrumguides.org/scrum-guide.html<a>. Acessado em 13/03/2018</p>

<br/>

><p align="justify">Extreme Programming Conceitos e Práticas. Disponível em: <a href="https://www.devmedia.com.br/extreme-programming-conceitos-e-praticas/1498">https://www.devmedia.com.br/extreme-programming-conceitos-e-praticas/1498</a>. Acessado em 10/03/2018</p>

<br/>

><p align="justify"> Arquitetura de Software em DevOps. Disponível em: <a href="https://imasters.com.br/desenvolvimento/arquitetura-de-software-em-devops/?trace=1519021197&source=single">https://imasters.com.br/desenvolvimento/arquitetura-de-software-em-devops/?trace=1519021197&source=single</a>. Acessado em 15/03/2018</p>
