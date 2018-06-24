# Arquitetura de Microserviços


No processo de desenvolvimento do Nexte foram utilizadas uma série de micro serviços com o objetivo de granularizar os componentes e delegar funções específicas para estes. Para gerenciar esses serviços foram utilizados *containers* do docker para o controle por meio de imagens, e assim garantindo maior qualidade da infraestrutura.
foram adotados dois *containers* até o momento: 

* Container Android: Contém o projeto Android e os frameworks a serem utilizados no projeto(MPAndroidCharts, Realm e Fuel)

* Container Servidor Principal: Contém que armazena o servidor principal(Node) e o Banco de Dados Remoto(Neo4J).


Além disso, foram adicionados muitos serviços externos, no qual cada é relacionado a uma feature específica. Desta forma, pode-se citar os seguintes serviços:

* Firebase Cloud Messsaging: "*Real time server*" utilizado para notificações remotas, é possível utilizá-lo no Android e iOS.  No entanto, como o escopo do Servidor principal estava em cargo do membros do Nexte, **não houve disponibilidade de tempo para implantação deste serviço**. Entretanto, em futuras evoluções, externas a disciplina, esta feature será implementada.

* *Image server *(Servidor de Imagens): Servidor que irá armazenar as imagens dos usuários do Nexte. Este servidor foi implementado utilizando Go juntamente a API do Google Cloud. 

* Neo4j: Banco de dados não relacional, orientado a grafos, que possui boa adaptabilidade as regras de negócio da aplicação.

* *Account Kit*: Serviço de autenticação provido pelo facebook que utiliza apenas do e-mail ou telefone para realizar essa terefa. Este serviço é adicionado diretamente ao *frontend*.

A seguir é apresentado a arquitetura e alguns dos micro serviços a serem utilizados.

## Arquitetura de micro serviços


### Versão 0.3

![microservice3](https://i.imgur.com/lkbYbts.png)

### Versão 0.2

[![Group_5.png](https://s9.postimg.cc/ui64atben/Group_5.png)](https://postimg.cc/image/pwa02gpvf/)

### Versão 0.1

[![microservice2.png](https://s17.postimg.cc/i0gs7p1i7/microservice2.png)](https://postimg.cc/image/bzj3amevv/)


