### Histórico de reivsão

| Data | Versão | Descrição | Autor(es) |
| -----|--------|-----------|-----------|
| 16/04/2018 | 0.1 | Criação do Documento e adição de testes unitários | Luis Gustavo |
| 28/04/2018 | 0.2 | Adição de boas práticas, realm, enum class e sealed class, e custom application class | Guilherme Baldissera |


## Sumário

[0. Boas Práticas para Kotlin](#0-boas-práticas-para-kotlin)  
[1. Testes Unitários](#1-testes-unitários)  
[2. Custom Application Class](#2-custom-application-class)  
[3. Enum Class com Sealed Class](#3-enum-class-com-sealed-class)  
[4. Realm Database](#4-realm-database) 


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

## 2. Custom Application Class

- [Understanding the Android Application Class](https://github.com/codepath/android_guides/wiki/Understanding-the-Android-Application-Class)

## 3. Enum Class com Sealed Class

Artigo do medium usado para isso, usado na entidade Challenge.
- [Medium Enum Class com Sealed Class](https://medium.com/@arturogdg/creating-enums-with-associated-data-in-kotlin-d9e2cdcf4a99)


## 4. Realm Database

Documentação do Realm Database utilizado para a adição da Database local esscolhida para o nosso app.
- [Realm Database](https://realm.io/docs/java/latest)

