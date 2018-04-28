
## Testes de Integração

### Roboeletric

O Robo eletric torna o processo de teste mais eficiente, visto que é capaz de rodar testes de tecnologias presentes na SDK na JVM, a partir de simulação de condições de um device na execução dos testes.

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

### Mockito e Mock

Para simular condições necessárias para o testes, muitas vezes se faz necerrário a criação de  mocks e para isso pode se fazer o uso de dois frameworks:

* [Mockito](http://site.mockito.org)
* [Mockk](http://mockk.io)

Dentre eles, destaca-se o mockk, pois ele é propriamente provido a Kotlin.

### Testing external frameworks

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

