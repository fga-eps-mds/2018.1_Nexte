# Arquitetura de Microserviços

## 1. *Overview*

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


## 2. Arquitetura Geral

A seguir está apresentada a arquitetura de microsserviços/serviços utilizados na aplicação. Ressalta-se que os quadrados representam containers ou serviços. As logos do Docker e Gitlab representam containers que utilizam como integração contínua o Gitlab CI. Este CI foi utilizado em diferentes âmbitos dentro da aplicação.

![Architecture](https://i.imgur.com/IngfWJE.png)

### 2.1 Arquitetura *Frontend* Android

Em maior grau detalhamento, no processo de desenvolvimento do *frontend*, utilizou-se de quatro *frameworks*, são eles:

Realm: *Framework* utilizado para realizar a persistência dos dados localmente. Atualmente, é bastante difundido e na alicação tem papel de guardar os dados do mock, e persistir informações do usuário internamente, assim optimizando a dependência e o número de requisições a serem realizadas ao servidor principal.

Fuel: *Framework* para requisições HTTP para android com kotlin. Atualmente, ele é um dos mais estáveis para esta tecnologia, e com maior número de contribuidores no Github.

MPAndroidChart: *Framework* mais estável para desenvolvimento de gráficos para android. Além disso, sua documentação é bastante completa. 

Picasso: *Framework* utilizado para manipular o uso de imagens oriundos de URLS e path, realiza o tratamento de tarefas assíncronas de maneira fácil e eficiente.

![FrontendAndroid](https://i.imgur.com/VdubD18.png)

### 2.2 Arquitetura Servidor de Imagem

Este servidor foi criado com o objetivo de armazenar imagens. Optou-se por utilizar Go, devido a sua perfomance e APIS relacionadas ao propósito desse servidor. Não obstante, utilizou-se de conceitos como tranferência de arquivos por multipart/form-data como maneira de trabalhar com imagens em requisições HTTP. Para realizar essas tarefas, utilizou-se das seguintes tecnologias:

* Google Cloud Storage: API para armazenamento de dados no serviço de Cloud da Google. 
* Mux: API mais consistente e sólida para criar rotas em Go!

![GoMicroservice](https://i.imgur.com/545g8M4.png)

### 2.3 Arquitetura Servidor Principal

O servidor principal tem como finalidade realizar a comunicação com o Neo4J, banco de dados da aplicação, e fazer o controle das ações provenientes do *frontend*. Neste processo, utilizou-se dos seguintes *frameworks* até o momento:

* Nodemon: *Framework* que facilita o processo de desenvolvimento, visto que a partir do salvamento de qualquer alteração de código, ele reinicia o servidor, automatizando este processo. 
* Restify: Uma das maiores e mais estáveis APIS para rotas em node
* Lodash: *Framework* que dispõe de inúmeras ferramentas no desenvolvimento com Ecmascript.

![NodeMicroservice](https://i.imgur.com/DpDow0C.png)


### 3. Histórico de Versões

### Versão 0.4


![microservice4](https://i.imgur.com/IngfWJE.png)

### Versão 0.3

![microservice3](https://i.imgur.com/lkbYbts.png)

### Versão 0.2

[![Group_5.png](https://s9.postimg.cc/ui64atben/Group_5.png)](https://postimg.cc/image/pwa02gpvf/)

### Versão 0.1

[![microservice2.png](https://s17.postimg.cc/i0gs7p1i7/microservice2.png)](https://postimg.cc/image/bzj3amevv/)


### 4. Observações

* No contexto da disciplina, não foram consideradas o servidor principal e o banco de dados, visto as restrições do cliente. Este optou por deixar o Servidor Principal em cargo dos membros fundadores do projeto, dentre eles Miguel Pimentel e Guilherme Baldissera. Entretanto, essa arquitetura foi concebida na discplina de MDS/EPS. 

*  O contexto de microsserviços é extremamente válido no contexto de servidores, visto que favorece a evolução e intoperabilidade de serviços. Utilizando esta prática a granularização de serviços acontece em vários âmbitos, assim escolhendo tecnologias que melhor se adaptam a funcionalidade pretendida. Não obstante, devido a esta granularização pode se economizar esforços em níveis arquiteturais, tais como, padrões de projeto, desenvolvimento de frameworks, entre outras práticas. Ao logo da discplina foi possível compreender estes conceitos, e trouxe maior maturidade ao projeto apesar do nosso contexto não ser favorecido por esta prática.