### Histórico de reivsão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 16/04/2018| 0.1 | Criação do Documento e adição de testes unitários | Luis Gustavo|
| 28/04/2018| 0.2 | Testes de Integração e  Alguns Frameworks | Miguel Pimentel |


## Sumário

[1. Testes Unitários](#1-testes-unitários)

[2. Testes de Integração](#2-testes-de-integracao)

## 1. Testes Unitários

### 1.1. Criando documento de testes

Na classe que deseja testar, clique em cima da declaração da classe e aperte: *Ctrl + shift + t*. Em seguida clique em *Create New Text*, conforme a figura abaixo.
![Criação de testes](https://i.imgur.com/bmNmfMp.png)

Na tela seguinte, adicione os os métodos *setUp* e *tearDown* e clique em *Ok*.
![Configuração de testes](https://i.imgur.com/CUJaUB7.png)

Após isso, sua classe de testes será criada.

### 1.2. *setUp* e *tearDown*

* **setUp**: Método que será executado antes de cada teste unitário.

* **tearDown**: Método que será executado após cada teste unitário.

Esses métodos são úteis para instanciar e desalocar variáveis que serão utilizadas em mais de um teste, desse modo, não sendo necessário declarar e instanciar novas variáveis em cada teste.

Ex:

```Kotlin
var worker: FeedWorker? = null

fun setUp(){
    this.worker = FeedWorker()
}

//@Test
//Vários testes que utilizarão a variável worker

fun tearDown(){
    this.worker = null
}

```

### 1.3. Testando

A estrutura do teste unitário se dá em três etapas:

* **prepare**: Preparação dos dados que serão utilizados no teste e criação do objeto a ser testado.

* **call**: Chamada do método a ser testado, com os parâmetros utilizados na etapa *prepare*.

* **assert**: Verificar se o objeto testado tem o valor esperado.

Ex:

```Kotlin
@Test
fun successRequest(){
    //prepare
    val userName = "luis-gustavo"
    val tokenId = "akjbd2130as"

   //call
    val request = ShowProfileModel.Request(username = "luis-gustavo", tokenID = "akjbd2130as")

    //assert
    assertEquals(userName, request.username)
    assertEquals(tokenId, request.tokenID)
}
```

### 1.4. Mocking

*Mocking* é usado principalmente em testes unitários. Um objeto em teste pode ter dependências em outros objetos (complexos). Para isolar o comportamento do objeto que você deseja testar, substitua os outros objetos por simulados que simulam o comportamento dos objetos reais. Isso é útil se os objetos reais forem impraticáveis ​​para incorporar ao teste de unitário.

Ou seja, *mocking* deve ser utilizado para criar objetos que irão simular o comportamento de objetos reais.

Ex: 

Como se pode-se observar na imagem abaixo, no método *presentLogin()* há a chamada do metodo *displayAuthenticateState()* da variável *viewController*. Entretanto, para o teste unitário do método *presentLogin()*, o comportamento do método *displayAuthenticateState()*, não deve ser considerado. Assim, para que não ficar dependente do comportamento desse método, utiliza-se mocking.

![Login Presenter](https://i.imgur.com/d3aOrAr.png)


**Mockando *LoginDisplayLogic***
```Kotlin

class MockLoginDisplayLogic: LoginDisplayLogic{

    //Simulando o comportamento real
    var message: String? = null
    
    override fun fun displayAuthenticateState(viewModel: LoginModel.ViewModel){
        
        //Aqui deve ser implementado o comportamento simulado desse método

        //Nesse caso, será atribuido em uma variável local desse classe, a mensagem que vem como parâmetro desse método
        this.message = viewModel.message
    }
}
```

**Utilizando o objeto mockado no teste unitário**

```Kotlin
class LoginPresenterTest {

    //Declaração do objeto mockado
    private var mock: MockLoginDisplayLogic? = null

    private var presenter: LoginPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockLoginDisplayLogic()
        this.presenter = LoginPresenter()

        //Atribuição do objeto mockado
        this.presenter?.viewControler = mock
    }

    @Test
    fun successMessagePresentLogin(){
        //prepare
        val response = LoginModel.Response(tokenId = "h1n3vv3u13ola")
        val expectedMessage = "Congratz! U get it"

        //call
        this.presenter?.presentLogin(response = response)

        //assert
        //Utilização do objeto mockado para a finalidade do teste
        assertEquals(this.mock?.message, expectedMessage)
    }
}
```

# 2. Testes de Integração

### 2.1 Roboeletric

O Roboeletric torna o processo de teste mais eficiente, visto que é capaz de rodar testes de tecnologias presentes na SDK na JVM, a partir de simulação de condições de um device na execução dos testes.

Configuração em Kotlin:

* **No gradle**

```
testCompile "org.robolectric:robolectric:3.8"
```

* **Nas classes de teste**

```
@RunWith(RobolectricTestRunner::class) //  Executa a ferramenta
@Config(manifest= Config.NONE) // Carrega configurações específicas definidas pelo ambiente de desenvolvimento
```

### 2.2 Mockito e Mock

Para simular condições necessárias para o testes, muitas vezes se faz necerrário a criação de  mocks e para isso pode se fazer o uso de dois frameworks:

* [Mockito](http://site.mockito.org)
* [Mockk](http://mockk.io)

Dentre eles, destaca-se o mockk, pois ele é propriamente provido a Kotlin.

### 2.3 Testing external frameworks

* **Testing Fuel**

Na documentação da framework, eles requerem o uso do Mockk, um framework para mock em kotlin. 

```
val client = mockk<Client>()

val someJson = "{\"key\":\"value\"}"

every { client.executeRequest(any()).statusCode } returns 200
every { client.executeRequest(any()).responseMessage } returns "OK"
every { client.executeRequest(any()).data } returns someJson.toByteArray()

FuelManager.instance.client = client
```
Para mais informações, acesse esta [issue](https://github.com/kittinunf/Fuel/issues/186) presente no repositório oficial

* **Testing Realm**

O teste do realm pode ser feito a partir de ferramentas já providas pelo *framework*. Este processo consiste em  simular uma instancia e garantir a manipulação destes valores.

```
// Configuring Test

RealmConfiguration testConfig = 
   new RealmConfiguration.Builder().
      inMemory().
      name("test-realm").build();
Realm testRealm = Realm.getInstance(testConfig);

// Execute Tests

//0. inject the prepared Realm
MyBusinessLogic interactor = new MyBusinessLogic(testRealm);
//1.1 write expected data
testRealm.executeTransaction(copyExpectedData);
//1.2 assert that business logic loads correct data
MyBusinessEntity actualData = interactor.loadBusinessStuff();
assertThat(actualData, equalTo(expectedData));
//2.1 write actual data
interactor.saveBusinessStuff(actualData);
//2.2 assert that business logic writes correct data
actualData = testRealm.where(MyBusinessEntity.class).findAll();
assertThat(actualData, equalTo(expectedData));
```

Para mais informações: [Android testing Realm](https://medium.com/@q2ad/android-testing-realm-2dc1e1c94ee1)

