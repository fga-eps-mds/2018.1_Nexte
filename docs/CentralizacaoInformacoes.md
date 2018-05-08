### Histórico de reivsão

| Data | Versão | Descrição | Autor(es) |
| -----|--------|-----------|-----------|
| 16/04/2018 | 0.1 | Criação do Documento e adição de testes unitários | Luis Gustavo |
| 28/04/2018 | 0.2 | Adição de boas práticas, realm, enum class e sealed class, e custom application class | Guilherme Baldissera |
| 28/04/2018 | 0.3 | Testes de Integração e  Alguns Frameworks | Miguel Pimentel | 
| 28/04/2018 | 0.4 | Testes Assíncronos | Miguel Pimentel |


## Sumário

[0. Boas Práticas para Kotlin](#0-boas-práticas-para-kotlin)  
[1. Testes Unitários](#1-testes-unitários)  
[2. Testes de Integração](#2-testes-de-integracao)  
[3. Testes Assíncronos](#3-testes-assincronos)  
[4. Custom Application Class](#4-custom-application-class)  
[5. Enum Class com Sealed Class](#5-enum-class-com-sealed-class)  
[6. Realm Database](#6-realm-database) 


## 0. Boas Práticas para Kotlin

- [Clean Code](https://blog.philipphauer.de/clean-code-kotlin/)
- [Kotlin e Melhores práticas](https://blog.philipphauer.de/idiomatic-kotlin-best-practices/)
- [Boas práticas para Teste Unitário em Kotlin](https://blog.philipphauer.de/best-practices-unit-testing-kotlin/)


## 1. Testes Unitários

- [Testes Unitário com Mockito](https://developer.android.com/training/testing/unit-testing/local-unit-tests?hl=pt-br)

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


# 3. Testes Assíncronos

## 3.1 *Couroutines* e Tarefas Assíncronas

No desenvolvimento de software é bastante usual a utilização de Courotines, isto é a capacidade de suspender e continuar sua própria execução. Esta prática se torna bastante comum quando utilizada em paralelo com tarefas assíncronas.

Pseudo-código para *couroutine*:

```
    start courotine 
    var dataFromServer = fetchDataFromServer().await()
    var data = parserData(dataFromServer).await()
    displayInList(data)
```

Entretanto, alguns frameworks disponibilizam métodos assync

##  3.2 Tarefas Assíncronas em Kotlin

Em Android com Kotlin, podemos fazer uso de tarefas assynchronas da seguinte forma:

```
suspend fun getListOfThings() {
    asyncTask { storageHelper.getListFromDb() }
        .let { list -> 
            view.useTheResult(list)
    }
}
```

```
suspend fun <T> asyncTask(function: () -> T): T {
    return run(CommonPool) { function() }
}
```

Vale ressaltar, que muitos frameworks de terceiros disponibilizam métodos assíncronos que não necessita do uso de funções auxiliares como *asyncTask*

## 3.3 **Teste Unitários**


Para realizar testes unitários, com o uso do Mockito podemos realizar os testes da seguinte forma:

```
// TEST

@Test
fun getPackageFiltersForWidget() {
    val list: ArrayList<PackageFilter> = ArrayList()
    `when`(storageHelper.getListFromDb()).thenReturn(list)
    presenter.getListOfThings()
    Mockito.verify(view).useTheResult(ArgumentMatchers.eq(list))
}

// PRESENTER

fun getListOfThings() {
    launch(Android) {
        asyncTask { storageHelper.getListFromDb() }.await()
        .let { list -> 
            view.useTheResult(list)
        }
    }
}

fun <T> asyncTask(function: () -> T): Deferred<T> {
    return async(CommonPool) { function() }
}

// ACTIVITY

fun getList() {
    presenter.getListOfThings()
}
```

## Observações

* Para utilizar tarefas assíncronas em kotlin é necessário importar o módulo de cotoutines:

```
compile "org.jetbrains.kotlinx:kotlinx-coroutines-core:0.11-rc"
```

### **Referências**

Para mais informações sobre testes e tarefas assíncronas:

*  [Android Coroutines](https://medium.com/@macastiblancot/android-coroutines-getting-rid-of-runonuithread-and-callbacks-cleaner-thread-handling-and-more-234c0a9bd8eb)

* [Android Couroutine - Unit Test](https://medium.com/@tonyowen/android-kotlin-coroutines-unit-test-16e984ba35b4)

## 4. Custom Application Class

- [Understanding the Android Application Class](https://github.com/codepath/android_guides/wiki/Understanding-the-Android-Application-Class)

## 5. Enum Class com Sealed Class

Artigo do medium usado para isso, usado na entidade Challenge.
- [Medium Enum Class com Sealed Class](https://medium.com/@arturogdg/creating-enums-with-associated-data-in-kotlin-d9e2cdcf4a99)


## 6. Realm Database

Documentação do Realm Database utilizado para a adição da Database local esscolhida para o nosso app.
- [Realm Database](https://realm.io/docs/java/latest)
