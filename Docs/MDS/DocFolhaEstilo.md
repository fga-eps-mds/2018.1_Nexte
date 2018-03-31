### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 26/03/2018 | 0.1 | Criação do documento | Larissa Sales e Letícia Meneses |
| 27/03/2018 | 0.2 | Definição das nomeações | Alexandre Miguel e Helena Goulart |
| 28/03/2018 | 0.3 | Adição de novos tópicos e correção dos topicos | Gabriel Albino e Helena Goulart |
| 28/03/2018 | 1.0 | Finalização dos tópicos que estavam pendentes no documento | Gabriel Albino e Letícia Meneses |
| 29/03/2018 | 1.1 | Adição de subtópicos em comentários e remoção do tópico de Testes | Alexandre Miguel e Helena Goulart|
| 29/03/2018 | 1.1.1 | Correções de design no documento | Gabriel Albino|
### Índice Analítico

  * [1 Nomeação](#1-nomeação)
    * [1.1 Pacotes](#11-pacotes)
    * [1.2 Classes](#12-classes)
    * [1.3 Objetos](#13-objetos)
    * [1.4 Interfaces](#14-interfaces)
    * [1.5 Métodos](#15-métodos)
    * [1.6 Atributos](#16-atributos)
    * [1.7 Variáveis](#17-variáveis)
    * [1.8 Testes](#18-testes)
  * [2 Formatação e Estilo](#2-formatação-e-estilo)
  * [3 Comentários](#3-comentários)
    * [3.1 Comentários em Classes](#31-comentários-em-classes)
    * [3.2 Comentários em Interfaces](#32-comentários-em-interfaces)
    * [3.3 Comentários em Métodos](#33-comentários-em-métodos)
    * [3.4 Comentários em Atributos](#34-comentários-em-atributos)
    * [3.5 Comentários em Variáveis](#35-comentários-em-variáveis)
    * [3.6 Comentários em Testes](#36-comentários-em-testes)
  * [4 Import](#4-import)
  * [5 Estruturas de Decisão](#5-estruturas-de-decisão)
  * [6 Identação](#6-identação)
  * [7 Linguagem](#7-linguagem)
  * [8 Estilo da Model](#8-estilo-da-model)
  * [9 Referências](#9-referências)



## 1 Nomeação
### 1.1 Pacotes
Seguem o padrão _UpperCamelCase_, ou seja, com todas as palavras iniciadas por letras maiúsculas e sem elemento separador, com termos preferencialmente suscintos e sem o uso de _underscores_.

Ex.:

  * Certo:

  ```kotlin
    package PackageName
  ```
  * Errado:

  ```kotlin
    package package_name
  ```

### 1.2 Classes
São nomeadas conforme o padrão _UpperCamelCase_, sem _underscores_.

Ex.:

  * Certo:

  ```kotlin
  class ClassName
  ```
  * Errado:

  ```kotlin
  class classname
  ```

### 1.3 Objetos
São nomeados conforme o padrão _lowerCamelCase_, ou seja, com a primeira palavra iniciada por letra minúscula e as demais palavras iniciadas por letras maiúsculas.

Ex.:

  * Certo:

  ```kotlin
  var objectName = ObjectClass()
  ```

  * Errado:

  ```kotlin
  var objectname = ObjectClass()
  ```

### 1.4 Interfaces
São nomeados conforme o padrão _UpperCamelCase_ e sem _underscores_.

Ex.:

  * Certo:

  ```kotlin
  interface InterfaceName
  ```
  * Errado:

  ```kotlin
  interface interfacename
  ```

### 1.5 Métodos
A nomenclatura segue o padrão _lowerCamelCase_, sem o uso de _underscores_ e acompanhado de parênteses.

Ex.:

  * Certo:

  ```kotlin
  fun methodName()
  ```

  * Errado:

  ```kotlin
  fun Method_name()
  ```


### 1.6 Atributos
Segue o padrão _lowerCamelCase_, contendo nomes significativos e sem _underscores_.

Ex.:

  * Certo:

  ```kotlin
  fun method(attribute: String)
  ```

  * Errado:

  ```kotlin
  fun method(Attribute_Example: String)
  ```


### 1.7 Variáveis
Devem ser precedidas pela declaração do tipo _var_ para valores alteráveis ou _val_ para valores fixos, seguindo o padrão _lowerCamelCase_ , contendo nomes significativos e sem _underscores_.

Ex.:

  * Certo:
  ```kotlin
  var variableExample
  ```

  * Errado:

  ```kotlin
  var Variable_Example
  ```


### 1.8 Testes

Utilizarão do padrão _UpperCamelCase_ e não permitirão o uso de _underscores_.

Ex.:

* Certo:
 ```kotlin
class TestCase {

       }
```

* Errado:

```kotlin
 class test_case {

       }
```

* Classe de teste deverá ter o nome da classe a ser testada e deverá terminar com a palavra "Test", utilizando o padrão _upperCamelCase_

* Certo
```Kotlin
class LoginWorkerTest {

}
```

* Errado:
```Kotlin
class testLoginWorker {

}
```


## 2 Formatação e Estilo

As chaves devem ser abertas no final de cada linha e fechadas em uma linha separada e alinhada com a mesma coluna em que foi aberta e haverá espaços após a escrita de classes, métodos e estruturas de decisão.

Ex.:

  * Certo:

  ```kotlin
  class Formating_Example {

         }
  ```

  * Errado:

  ```kotlin
  class Formating_Example {

                                            }  
  ```


Pontos e vírgulas não serão utilizados no final de cada linha de código.

Ex.:

  * Certo:

  ```kotlin
  var rightExample
  ```
  * Errado:

  ```kotlin
  var   xample;
  ```

Serão usados espaços entre operações lógicas, como somas e subtrações, todavia não serão utilizados em casos de incrementação.

Ex.:

  * Certo:

  ```kotlin
  var rightExample = 1 + 1
  ```

  * Errado:

  ```kotlin
  var wrongExample=1+1
  ```
  * Certo:

  ```kotlin
  i++
  ```
  * Errado:

  ```kotlin
  i + +
  ```


## 3 Comentários

Devem iniciar com letra maiúscula e ter espaço apoś "//". Para uma maior compreensão do código, os comentários devem estar presentes neles como uma breve apresentação do objetivo e como o método funciona.

Ex.:

  - Certo:

  ```kotlin
  // Right example
  ```
  * Errado:
  ```kotlin
  //wrong example
  ```


- Devem ser escritos no mesmo idioma do código

Ex.:

  * Certo:

  ```kotlin
  //Right example
  ```
  * Errado:

  ```kotlin
  //Exemplo errado
  ```

* Comentários de uma linha devem ser escritos usando `//`

Ex.:

  * Certo:

  ```kotlin
  //Right example
  ```

  * Errado:

  ```kotlin
   /* Wrong example */
  ```

* Comentários de mais de uma linha devem ser escritos usando `/* */`, contendo quebra de linhas que auxiliem na visualização do comentário, sendo iniciado e finalizado na mesma linha da sinalização. Cada linha do comentário deve estar alinhada com a primeira linha, mantendo a inicialização do texto na mesma coluna.

Ex.:

  * Certo:

  ```kotlin
   /* This is an example about
      how to use correctly the commentary
      on the code */
  ```

  * Errado:

  ```kotlin
   /*
   This is not
      the right example
     about using a commentary
   */
  ```


### 3.1 Comentários em classes

Os comentários devem estar uma linha acima da classe referenciada, possuindo quebras de linha para melhor visualização do conteúdo e contendo uma breve descrição da funcionalidade dessa classe. O uso de comentários em classes é obrigatório.

Ex.:

  * Certo:

  ```kotlin
   /* This class exemplifies
      and portraits how to correctly
      use the commentaries on the classes */

   class ExampleClass<Parameter> : ClassImplementations {

   }
  ```

  * Errado:

  ```kotlin
   // This is not the right example
   // about using a commentary in class
   class ExampleClass<Parameter> : ClassImplementations {
     //neither is this expeficiations

   }

  ```

### 3.2 Comentários em interfaces

Os comentários devem estar uma linha acima da interface referenciada e contendo uma breve descrição da funcionalidade dessa interface. O uso de comentários em interfaces é opcional.

Ex.:

  * Certo:

  ```kotlin
   /* This interface do the
      comunication between other
      packages and classes */

      interface ExampleInterfaceLogic {

          fun doComunication(request: ExampleModel.Request)
      }
  ```

  * Errado:

  ```kotlin
   // This interface do the
    //    comunication between other
      //  packages and classes
        interface ExampleInterfaceLogic {

            fun doComunication(request: ExampleModel.Request)
        }

   }

  ```

### 3.3 Comentários em Métodos

Os comentários devem estar uma linha acima do método, contendo uma breve explicação da funcionalidade implementada, bem como a relação dessa funcionalidade com os parâmteros declarados. O uso de comentários em métodos não é obrigatório, mas é extremamente recomendado.

Ex.:

  * Certo:

  ```kotlin
  /* This method adds
     a member "T" to
     this function */

  fun addThis(member: T): Int { ... }
```

  * Errado:

  ```kotlin
   fun addThis(member: T): Int { ... }
  ```

### 3.4 Comentários em Atributos

Os comentários em atributos devem ser feitos na mesma linha de declaração dos mesmos, separados por um espaço entre o símbolo ``//`` e iniciados com letra maiúscula, explicitando o conteúdo referenciado. O Uso de comentários em atributos é obrigatório.

Ex.:

  * Certo:

  ```kotlin
    class ExampleClass<Parameter> : ClassImplementations {

      var example : String? = "" // Reference to worker

    }
  ```

  * Errado:

  ```kotlin
    class ExampleClass<Parameter> : ClassImplementations {

      var example : String? = "" /* wrong reference to worker*/

    }
  ```


### 3.5 Comentários em Variáveis

Os comentários em variáveis devem ser feitos na mesma linha de declaração, separados por um espaço entre o símbolo ``//`` e iniciados com letra maiúscula, explicitando o conteúdo utilizado. O Uso de comentários em variáveis é opcional.

Ex.:

  * Certo:

  ```kotlin
    var name : String? = null // Stores name of players
  ```

  * Errado:

  ```kotlin
    var name : String? = null /* the variable is called name*/

  ```

### 3.6 Comentários em Testes

Utilizar comentários de uma linha para descrever cada etapa do teste, imetiadamente antes da etapa descrita.

Ex.:

  * Certo:

  ```kotlin
  @Test
  fun testSuccess_basic() {
    // prepare
    val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
    val response = Response.success(redditNewsResponse)

    when (callMock.execute()).thenReturn(response)

    // call
    val newsManager = NewsManager(apiMock)
    newsManager.getNews("").subscribe(testSub)

    // assert
    testSub.assertNoErrors()
    testSub.assertValueCount(1)
    testSub.assertCompleted()
  }
  ```
  * Errado:

  ```kotlin
  @Test
  fun testSuccess_basic() {
    val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
    val response = Response.success(redditNewsResponse)
    when (callMock.execute()).thenReturn(response)
    val newsManager = NewsManager(apiMock)
    newsManager.getNews("").subscribe(testSub)
    testSub.assertNoErrors()
    testSub.assertValueCount(1)
    testSub.assertCompleted()
  }
  ```

## 4 Import

As importações deverão usar o termo _import_ seguido do nome do pacote ou classe, e em caso de mais de uma chamada é necessário que seja realizada em uma linha separada.

  Ex.:

  * Certo:
   ```kotlin
  import NameOfPackage
  import NameOfClass
  ```

  * Errado:
  ```kotlin
   import NameOfPackage, import NameOfCLass
  ```


## 5 Estruturas de Decisão

Serão colocados espaços entre essas estruturas e os parênteses que contém os termos. Sempre serão usadas chaves para implementar o corpo das condicionais.

Ex.:

  * Certo:

  ```kotlin
  if (rightExample == right) {
          return rightExample
        }
  ```
  * Errado:

  ```kotlin
   if(wrongExample == wrong)
            return wrongExample
  ```

## 6 Identação

A identação deve ser de 4 espaços para cada nível;

Ex.:

  * Certo:

  ```kotlin
  if (examples != null) 
      examples == 1
  }
  ```
  * Errado:

  ```kotlin
  if (examples == null) {
    examples == null
  }
  ```

## 7 Linguagem

O linguagem padrão do programa é em inglês, extendendo-se para pacotes, classes, objetos, atributos e variáveis.

Ex.:

  * Certo:

  ```kotlin
  class GreatExample;
  ```
  * Errado:

  ```kotlin
  classe ExemploErrado;
  ```

## 8 Estilo da Model

A _Model_ deve ser organizada de modo em que possua 3 classes internas, que serão a _request_, a _response_ e a _ViewModel_

Ex.:

  * Certo:

  ```kotlin
    class Model {
        class Request {
            var name: String? = null
            constructor(name: String?) {
                this.name = name
            }
        }
        class Response {
            var age: Int? = null
            constructor(age: Int?) {
                this.age = age
            }
        }
        class ViewModel {
            var message: String?= null
            constructor(message: String?) {
                this.message = message
            }
        }
    }
```

  * Errado:

  ```kotlin
    class Model {
        var age: Int? = null
        var name: String? = null
    }
  ```


## 9 Referências

[Kotlin Style Guide](https://kotlinlang.org/docs/reference/coding-conventions.html)
