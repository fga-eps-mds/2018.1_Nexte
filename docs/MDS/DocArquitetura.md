
### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 16/03/2018| 0.1 |Criação do documento | Gabriel Albino, Letícia Meneses |
| 17/03/2018| 0.1.1 |Correção do índice analítico | Gabriel Albino |
| 18/03/2018| 0.2 |Adicionado introdução e representação arquitetural | Gabriel Albino |
| 18/03/2018| 0.3 | Finalizado restrições de arquitetura. | Gabriel Albino |
| 19/03/2018| 0.4 | Produção do Dimensionamento e Performance. | Gabriel Albino, Letícia Meneses e Lorrany Freire |
| 19/03/2018| 0.4.1 | Adicionado descrição e atributos das classes principais. | Gabriel Albino, Letícia Meneses e Lorrany Freire |
| 20/03/2018| 0.5 | Adicionado diagrama de pacotes e camadas, assim como sua descrição. | Gabriel Albino e Letícia Meneses |
| 21/03/2018| 0.5.1 | Revisão de texto e padronização do formato das tabelas| Alexandre Miguel |
| 21/03/2018| 0.6 | Adicionado diagrama de classes | Gabriel Albino, Letícia Meneses e Lorrany Freire |
| 21/03/2018| 0.7 | Adicionado o índice analítico e definidos os aspectos de qualidade | Alexandre Miguel e Helena Goulart |
| 21/03/2018| 0.7.1 | Realizadas correções nos tópicos e no diagrama de classes | Gabriel Albino, Letícia Meneses e Lorrany Freire |
| 21/03/2018| 0.7.2 | Relizado correção na definição de _model_ | Gabriel Albino |
| 21/03/2018| 0.7.3 | Relizado correção no diagrama de classes | Gabriel Albino |
| 21/03/2018| 0.7.4 | Relizado correção no diagrama de classes | Gabriel Albino |
| 23/03/2018| 0.8 | Adicionado diagrama de sequência | Lorrany Freire |
| 24/03/2018| 1.0 | Correção de detalhes e remoção de tópicos que não serão abordados nesse documento. | Gabriel Albino |
| 24/03/2018| 1.0.1| Correção do tópico 3.2 (Restrições de arquitetura). | Gabriel Albino, Letícia Meneses, Lorrany Freire |
| 19/04/2018| 1.1| Adição dos novos diagramas de classe. | Gabriel Albino, Alexandre Miguel |
| 27/04/2018| 1.1.1| Adição do novo diagrama de classe da Comments. | Alexandre Miguel |
| 01/05/2018| 1.1.2| Adição do novo diagrama de classe da Comments. | Gabriel Albino |
| 03/05/2018| 2.0| Atualização da Arquitetura((Frontend) | Miguel Pimentel|
| 10/05/2018| 2.0.1| Atualização do novo diagrama da classe da Comments | Alexandre Miguel|
| 26/06/2018| 2.1| Atualização Arquitetural e Adição do Microsserviços atualizados | Miguel Pimentel|

### Índice Analítico

- [Nexte](#nexte)
    - [Objetivo desse documento](#objetivo-desse-documento)
- [1 INTRODUÇÃO](#1-introdu%C3%A7%C3%A3o)
    - [1.1 Finalidade](#11-finalidade)
    - [1.2 Escopo](#12-escopo)
    - [1.3 Definições, Acrônimos e Abreviações](#13-defini%C3%A7%C3%B5es-acr%C3%B4nimos-e-abrevia%C3%A7%C3%B5es)
    - [1.4 Referências](#14-refer%C3%AAncias)
- [2. REPRESENTAÇÃO ARQUITETURAL](#2-representa%C3%A7%C3%A3o-arquitetural)
    - [2.1 Representação Arquitetural das Cenas](#21-representa%C3%A7%C3%A3o-arquitetural-das-cenas)
    - [2.2 Representação Arquitetural das Entidades Globais](#22-representa%C3%A7%C3%A3o-arquitetural-das-entidades-globais)
- [3. REQUISITOS E RESTRIÇÕES DE ARQUITETURA](#3-requisitos-e-restri%C3%A7%C3%B5es-de-arquitetura)
    - [3.1 Requisitos gerais](#31-requisitos-gerais)
    - [3.2 Restrições de arquitetura](#32-restri%C3%A7%C3%B5es-de-arquitetura)
- [4. VISÃO LÓGICA](#4-vis%C3%A3o-l%C3%B3gica)
    - [4.1 Visão Geral para as Cenas – pacotes e camada](#41-vis%C3%A3o-geral-para-as-cenas-%E2%80%93-pacotes-e-camada)
        - [4.1.1 Divisão dos módulos por responsabilidades](#411-divis%C3%A3o-dos-m%C3%B3dulos-por-responsabilidades)
        - [4.1.2 Diagrama de camadas](#412-diagrama-de-camadas)
    - [4.2 Visão Geral para as Entidade Globais – pacotes e camada](#42-vis%C3%A3o-geral-para-as-entidade-globais-%E2%80%93-pacotes-e-camada)
    - [4.3 Observações](#43-observa%C3%A7%C3%B5es)
- [5. VISÃO DE IMPLEMENTAÇÃO](#5-vis%C3%A3o-de-implementa%C3%A7%C3%A3o)
    - [5.1 Caso de Uso](#51-caso-de-uso)
        - [5.1.1 *Diagrama de Classes*](#511-diagrama-de-classes)
        - [5.1.2 *Diagrama de Sequência*](#512-diagrama-de-sequ%C3%AAncia)
- [6. DIMENSIONAMENTO](#6-dimensionamento)
- [7. QUALIDADE](#7-qualidade)
- [8. ARQUITETURA DE MICROSSERVIÇOS](#8-arquitetura-de-microsservi%C3%A7os)
    - [8.1 *Overview*](#81-overview)
- [8.2 Arquitetura Geral](#82-arquitetura-geral)
    - [8.2.1 Arquitetura *Frontend* Android](#821-arquitetura-frontend-android)
    - [8.2.2 Arquitetura Servidor de Imagem](#822-arquitetura-servidor-de-imagem)
    - [8.2.3 Arquitetura Servidor Principal](#823-arquitetura-servidor-principal)
        - [8.3. Histórico de Versões](#83-hist%C3%B3rico-de-vers%C3%B5es)
            - [8.3.1. Versão 0.4](#831-vers%C3%A3o-04)
            - [8.3.2. Versão 0.3](#832-vers%C3%A3o-03)
            - [8.3.3. Versão 0.2](#833-vers%C3%A3o-02)
            - [8.3.4. Versão 0.1](#834-vers%C3%A3o-01)
        - [8.4. Observações](#84-observa%C3%A7%C3%B5es)

## Nexte

### Objetivo desse documento

Este documento tem como objetivo descrever as principais decisões de projeto tomadas pela equipe de
desenvolvimento e os critérios considerados durante a tomada destas decisões. Suas informações incluem a parte de hardware e software do sistema.

## 1 INTRODUÇÃO

### 1.1 Finalidade
É apresentada uma visão arquitetural abrangente do sistema Nexte, usando diversas modelos de arquitetura para representar diferentes aspectos do sistema. O objetivo deste documento é capturar e comunicar as decisões arquiteturais significativas que foram tomadas
em relação ao sistema. Vale-se ressaltar que este documento de arquitetura é referente ao *frontend* desenvolvido em Android.

### 1.2 Escopo
Este Documento de Arquitetura de Software se aplica ao Nexte, auxiliando os desenvolvedores na construção do projeto.

### 1.3 Definições, Acrônimos e Abreviações
- UnB: Universidade de Brasília;
- FGA: Faculdade do Gama;
- MDS: Métodos de desenvolvimento de software;
- EPS: Engenharia de Produto de Software;
- JVM: _Java Virtual Machine_.


### 1.4 Referências
- CLT: Cinta Liga de Tênis

## 2. REPRESENTAÇÃO ARQUITETURAL

A arquitetura do frontend da aplicação faz uso de modernos conceitos arquiteturais para *mobiles* como os propostos por [Uncle Bob]() com o *clean architecture*. Entretanto,  devido a alguns requisitos do projeto, como a sincronização dos dados entre banco de dados local e outro remoto, houve a necessidade de adaptar esse modelo arquitetural. Não obstante, foram utilizados padrões de projeto GoF como o adapter com o objetivo de manter o projeto desaclopado e manutenível.

Na arquitetura do frontend do Nexte Android, pode-se compreender duas representações:

* Representação Arquitetural das Cenas: Consiste no fluxo de dados e funcionalidades de uma feature, de modo que cada cena tenha controle restrito apenas as camadas pertencentes a ela.

*  Representação Arquitetural das Entidades Globais:  Consiste em modelos de dados que serão utilizados pela cenas, esta parte da representação arquitetural está relacionada com a persistência local dos dados, assim como a sincronização com os dados proveninentes do servidor.

### 2.1 Representação Arquitetural das Cenas

Para a arquitetura será utilizado a "clean architecture", que se baseia em seis módulos:

- Model: Responsável pelas regras de definição de dados e pode possuir modelos globais ou modelos locais de cada cena;
- Worker: É responsável por controlar as requisições de dados ao local em que esses dados estão armazenados, isto é, faz requisições ao servidor ou ao banco de dados local;
- View: Apresenta os elementos na interface gráfica;
- Presenter: Formata os dados que serão exibidos e transmite essa informação para a view;
- Interactor: Módulo centralizador que controla o fluxo entre o Presenter e o Worker;
- Router: Controla o fluxo entre as cenas.

As relações entre os módulos estão representadas no esquema a seguir.

Imagem 1: Relação entre os módulos de cada cena

[![VIP.png](https://s9.postimg.cc/b4tq4jly7/VIP.png)](https://postimg.cc/image/umodkhivv/)


### 2.2 Representação Arquitetural das Entidades Globais

- Entity: Modelo de dados referentes aos objetos da aplicação, em outras palavras seriam as models globais que seria utilizadas dentro das diferentes cenas.
- EntityAdapter: Permite que uma entidade possa assumir diferentes valores de acordo com as cenas e o modelo de armazenamento proposto pelo Realm.
- EntityManager: Módulo central que decide de acordo com a requisição proveniente da *worker* qual será o *response*, se será necessário trabalhar com o armazenamento de dados remoto ou local, entre outras operações relacionadas com armazenamento de dados.
- EntityMocker: Módulo responsável por carregar na memória instância dos dados mocados pela aplicação.
- EntityRealm: Modelo de dados que a model global será armazenado pelo real.
- EntityRealmAdapter: Permite que os dados sejam adaptados para o formato aceito pelo Realm, de forma que também facilite migrações para outros serviços de persistência local.

Imagem 2:  Relação entre os módulos das entidades globais

[![Global_Entity.png](https://s9.postimg.cc/9ps5fvxqn/Global_Entity.png)](https://postimg.cc/image/fdyg6s22z/)

## 3. REQUISITOS E RESTRIÇÕES DE ARQUITETURA

Esta seção decreve os requisitos de software e restrições que têm um impacto significante no projeto.

### 3.1 Requisitos gerais
|Requisito|Solução|
|---|---|
|Linguagem  | Kotlin |
|Plataforma| Android versão 5.1 ou superior|

### 3.2 Restrições de arquitetura

A persistência e o servidor web são restrições da arquitetura utilizada, pois podem influenciar diretamente nela. Neste caso, foram realizadas alterações ao estabelecer camadas e padrões para realizar a sincronização entre dados remotos e locais.

## 4. VISÃO LÓGICA

|Nome da classe|Atributos|Descrição|
|:----:|:----:|:-----:|
|Jogadores|Nome completo, nome de usuário, senha, contato (e-mail e telefone), vitórias e derrotas |Define o jogador com suas informações pessoais, assim como seu desempenho nos jogos, podendo ele ser o diretor do clube, ou não.|
|Clube|Nome, Contato (e-mail e telefone), número de jogadores, diretor, membros|Gerencia informações sobre o clube, incluindo número de jogadores e quem eles são.|
|Ranking|Jogadores|Controla, a partir das regras da CLT, a colocação dos jogadores em relação aos demais membros do clube.|
|Jogos|Data, horário, jogadores, resultado, tempo de partida, local|Responsável pelo gerenciamento de jogos, desde sua marcação por meio de um desafio até a coleta de resultados.|
|Feed|Jogadores, jogos|Gerencia os jogos mais recentes a modo de torná-los visíveis, exbindo-os na página inicial do aplicativo.|
|Match|Número de Sets, Data e Hora do jogo, valor obtido em cada set | Permite ao usuário a definição, no aplicativo, do horário em que um jogo ocorreru, bem como o resultado final (placar, sets e eventual WO) da partida.|


### 4.1 Visão Geral para as Cenas – pacotes e camada

A _Clean Architecture_ é organizada de modo em que as camadas mais externas passam as informações para as camadas mais internas, facilitando o armazenamento e obtenção de dados.

![](https://i.imgur.com/NLLi7Kr.jpg)

#### 4.1.1 Divisão dos módulos por responsabilidades
![ Exemplo de Diagrama de Pacotes da Aplicação](https://i.imgur.com/hVXTV2M.jpg)

No diagrama de pacotes temos que a arquitetura é composta de 3 partes distintas, sendo elas:
- _Presentation_ : Responsável pela interface gráfica e controle da entrada e saída de dados;
- _Domain_ : Responsável pelo processamento de eventos, funcionando como intermediador entre a presentation e a infrastructure;
- _Infrastructure_ : Responsável pelo armazenamento e fornecimento de dados para a domain ou presentation.

#### 4.1.2 Diagrama de camadas

[![VIP.png](https://s9.postimg.cc/b4tq4jly7/VIP.png)](https://postimg.cc/image/umodkhivv/)

No diagrama de camadas é mostrado a interação entre os módulos, que são definidos como:

- _Model_ : Responsável por criar uma estruturação para troca de informações entre as camadas, mudando a estrutura de acordo com as camadas envolvidas, armazenando momentaneamente os dados.
- _Worker_ : Responsável pela requisição dos dados para o servidor ou para um banco de dados local.;
- _Iteractor_ : Responsável por controlar o fluxo entre o view, worker e presenter;
- _Presenter_ : Formata os dados que serão exibidos na view;
- _View_ : Responsável pela interface gráfica e entrada de dados;
- _Router_ : Responsável por controlar o fluxo entre as telas;

### 4.2 Visão Geral para as Entidade Globais – pacotes e camada

[![Global_Entity.png](https://s9.postimg.cc/9ps5fvxqn/Global_Entity.png)](https://postimg.cc/image/fdyg6s22z/)

No diagrama de camadas é mostrado a interação entre os módulos, que são definidos como:

-_EntityRealm_ : Responsável por criar uma estruturação para troca de informações entre as camadas, mudando a estrutura de acordo com as camadas envolvidas, armazenando momentaneamente os dados.
- _EntityAdapterRealm_ : Responsável pela requisição dos dados para o servidor ou para um banco de dados local.;
- _EntityAdapter_ : Responsável por controlar o fluxo entre o view, worker e presenter;
- _Entity_ : Formata os dados que serão exibidos na view;
- _EntityManager_ : Responsável pela interface gráfica e entrada de dados.
- _EntityMocker_ : Responsável por controlar o fluxo entre as telas
- _worker_ : Módulo que está presente na cenas, enviar o request que será manipulado pela EntityManager

### 4.3 Observações

Algumas cenas podem necessitar do uso das entidades globais. Dessa forma, para estas cenas poderíamos ter o seguinte diagrama de pacotes:

[![Architecture.png](https://s9.postimg.cc/qdjnigd3j/Architecture.png)](https://postimg.cc/image/wehcfizpn/)

## 5. VISÃO DE IMPLEMENTAÇÃO

### 5.1 Caso de Uso

#### 5.1.1 *Diagrama de Classes*

* **Diagrama de classe - Cenas**

![Diagrama de classes da editProfile](https://i.imgur.com/tXgt9oo.jpg)

![Diagrama de classes da showProfile](https://i.imgur.com/OCn6Gh3.jpg)

![Diagrama de classes da comments](https://i.imgur.com/xGqIEIH.jpg)

![Diagrama de classes do feed](https://i.imgur.com/z6icAhU.jpg)

![Diagrama de classes do challange](https://i.imgur.com/Cj3v8cF.jpg)

![Diagrama de classes da likelist](https://i.imgur.com/HBW40Qv.jpg)

![Diagrama de classes do login](https://i.imgur.com/JXGEqyj.jpg)

![Diagrama de classes da ranking](https://i.imgur.com/MHfir5I.jpg)

![Diagrama de classes da Match](https://i.imgur.com/EuwfGjt.jpg)

*  **Diagrama de classe - Entidade Global**

[![Diagrama_Classe_Novo.png](https://s9.postimg.cc/hiit84ynj/Diagrama_Classe_Novo.png)](https://postimg.cc/image/xtix4gb57/)

#### 5.1.2 *Diagrama de Sequência*

No aprimoramento do aplicativo Nexte garantindo uma fácil manutenção e flexibilidade para se adaptar a transformações, utiliza-se a Clean Architecture para garantir esses quesitos, assim como padrões de projeto GRASP e GoF

[![Diagrama_Sequencia_Novo.png](https://s9.postimg.cc/qdjniknf3/Diagrama_Sequencia_Novo.png)](https://postimg.cc/image/5tetk37nv/)


## 6. DIMENSIONAMENTO

Nos aspectos gerais, o aplicativo pode abrangir uma grande quantidade de clubes esportivos e assim seus membros. Considerando que cada usuário terá um jogo por semana e que utilizará o aplicativo a cada dois dias devido o sistema de gameficação para conferir sua posição no ranking, estima-se uma média de quatro a cinco acessos semanais por membro, conectando-se ao aplicativo em torno de uma vez por dia para acompanhar as atualizações do mesmo, essencialmente no período da manhã e da tarde de aproximadamente 5-7 minutos de duração.


## 7. QUALIDADE

| Item  | Solução  | Descrição |
|:---:|:---:|:---:|
|  Escalabilidade | Encapsulamento e _Clean Architecture_  | Através da modularização e do encapsulamento do software, o sistema ficará propício para manutenção, tornando-se adaptável e acolhendo o número crescente de usuários.   |
|  Disponibilidade | Manutenção periódica e _clean architecture_ | A solução apresentada permite que o software passe o menor tempo possível fora do ar devido às falhas do sistema, possibilitando o funcionamento contínuo. Isso ocorre pelo fato da arquitetura utilizada proporcionar um maior controle e maior modularização do código |  
| Portabilidade  | Linguagem Kotlin  | A linguagem escolhida permite a compilação na máquina virtual Java, permitindo que os programas sejam executados em qualquer sistema Android que contenha uma versão da JVM.  |
| Segurança  | Servidor remoto | Os dados dos usuários serão protegidos em um servidor remoto, e informações confidenciais como senha serão armazenadas em HASH. |


## 8. ARQUITETURA DE MICROSSERVIÇOS

### 8.1 *Overview*

No processo de desenvolvimento do Nexte foram utilizadas uma série de microserviços/serviços com o objetivo de granularizar os componentes e delegar funções específicas para estes. Para gerenciar esses serviços foram utilizados *containers* do docker para o controle por meio de imagens, e assim garantindo maior qualidade da infraestrutura. Foram adotados alguns containers nesse processo, são eles:

* Container Frontend Android;
* Container Servidor Principal; 
* Container para Banco de Dados; 
* Container para Servidor de Imagens;

Não obstante, foram adicionados muitos serviços externos, no qual cada um é relacionado a uma feature específica. Desta forma, pode-se citar os seguintes serviços:

* Firebase Cloud Messsaging: "*Real time server*" utilizado para notificações remotas, é possível utilizá-lo no Android e iOS.  No entanto, como o escopo do Servidor principal estava em cargo do membros do Nexte, **não houve disponibilidade de tempo para implantação deste serviço**. Entretanto, em futuras evoluções, externas a disciplina, esta feature será implementada.

* *Image server *(Servidor de Imagens): Servidor que irá armazenar as imagens dos usuários do Nexte. Este servidor foi implementado utilizando GO juntamente a API do Google Cloud. 

* Neo4j: Banco de dados não relacional, orientado a grafos, que possui boa adaptabilidade as regras de negócio da aplicação.

* *Account Kit*: Serviço de autenticação provido pelo facebook que utiliza apenas do e-mail ou telefone para realizar essa terefa. Este serviço é adicionado diretamente ao *frontend*.

A seguir é apresentado a arquitetura e alguns dos micro serviços a serem utilizados.


## 8.2 Arquitetura Geral

A seguir está apresentada a arquitetura de microsserviços/serviços utilizados na aplicação. Ressalta-se que os quadrados representam containers ou serviços. As logos do Docker e Gitlab representam containers que utilizam como integração contínua o Gitlab CI. Este CI foi utilizado em diferentes âmbitos dentro da aplicação.

![Architecture](https://i.imgur.com/IngfWJE.png)

### 8.2.1 Arquitetura *Frontend* Android

Em maior grau detalhamento, no processo de desenvolvimento do *frontend*, utilizou-se de quatro *frameworks*, são eles:

Realm: *Framework* utilizado para realizar a persistência dos dados localmente. Atualmente, é bastante difundido e na alicação tem papel de guardar os dados do mock, e persistir informações do usuário internamente, assim optimizando a dependência e o número de requisições a serem realizadas ao servidor principal.

Fuel: *Framework* para requisições HTTP para android com kotlin. Atualmente, ele é um dos mais estáveis para esta tecnologia, e com maior número de contribuidores no Github.

MPAndroidChart: *Framework* mais estável para desenvolvimento de gráficos para android. Além disso, sua documentação é bastante completa. 

Picasso: *Framework* utilizado para manipular o uso de imagens oriundos de URLS e path, realiza o tratamento de tarefas assíncronas de maneira fácil e eficiente.

![FrontendAndroid](https://i.imgur.com/VdubD18.png)

### 8.2.2 Arquitetura Servidor de Imagem

Este servidor foi criado com o objetivo de armazenar imagens. Optou-se por utilizar Go, devido a sua perfomance e APIS relacionadas ao propósito desse servidor. Não obstante, utilizou-se de conceitos como tranferência de arquivos por multipart/form-data como maneira de trabalhar com imagens em requisições HTTP. Para realizar essas tarefas, utilizou-se das seguintes tecnologias:

* Google Cloud Storage: API para armazenamento de dados no serviço de Cloud da Google. 
* Mux: API mais consistente e sólida para criar rotas em Go!

![GoMicroservice](https://i.imgur.com/545g8M4.png)

### 8.2.3 Arquitetura Servidor Principal

O servidor principal tem como finalidade realizar a comunicação com o Neo4J, banco de dados da aplicação, e fazer o controle das ações provenientes do *frontend*. Neste processo, utilizou-se dos seguintes *frameworks* até o momento:

* Nodemon: *Framework* que facilita o processo de desenvolvimento, visto que a partir do salvamento de qualquer alteração de código, ele reinicia o servidor, automatizando este processo. 
* Restify: Uma das maiores e mais estáveis APIS para rotas em node
* Lodash: *Framework* que dispõe de inúmeras ferramentas no desenvolvimento com Ecmascript.

![NodeMicroservice](https://i.imgur.com/DpDow0C.png)


#### 8.3. Histórico de Versões

##### 8.3.1. Versão 0.4


![microservice4](https://i.imgur.com/IngfWJE.png)

##### 8.3.2. Versão 0.3

![microservice3](https://i.imgur.com/lkbYbts.png)

##### 8.3.3. Versão 0.2

[![Group_5.png](https://s9.postimg.cc/ui64atben/Group_5.png)](https://postimg.cc/image/pwa02gpvf/)

##### 8.3.4. Versão 0.1

[![microservice2.png](https://s17.postimg.cc/i0gs7p1i7/microservice2.png)](https://postimg.cc/image/bzj3amevv/)


#### 8.4. Observações

* No contexto da disciplina, não foram consideradas o servidor principal e o banco de dados, visto as restrições do cliente. Este optou por deixar o Servidor Principal em cargo dos membros fundadores do projeto, dentre eles Miguel Pimentel e Guilherme Baldissera. Entretanto, essa arquitetura foi concebida na discplina de MDS/EPS. 

*  O contexto de microsserviços é extremamente válido no contexto de servidores, visto que favorece a evolução e intoperabilidade de serviços. Utilizando esta prática a granularização de serviços acontece em vários âmbitos, assim escolhendo tecnologias que melhor se adaptam a funcionalidade pretendida. Não obstante, devido a esta granularização pode se economizar esforços em níveis arquiteturais, tais como, padrões de projeto, desenvolvimento de *frameworks*, entre outras práticas. Ao logo da discplina foi possível compreender estes conceitos, e trouxe maior maturidade ao projeto apesar do nosso contexto não ser favorecido por esta prática.
