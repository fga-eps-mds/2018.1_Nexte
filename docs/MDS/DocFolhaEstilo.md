### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 26/03/2018 | 0.1 | Criação do documento | Larissa Sales e Letícia Meneses |
| 27/03/2018 | 0.2 | Definição das nomeações | Alexandre Miguel e Helena Goulart |
| 28/03/2018 | 0.3 | Adição de novos tópicos e correção dos topicos | Gabriel Albino e Helena Goulart |
| 28/03/2018 | 1.0 | Finalização dos tópicos que estavam pendentes no documento | Gabriel Albino e Letícia Meneses |
| 29/03/2018 | 1.1 | Adição de subtópicos em comentários e remoção do tópico de Testes | Alexandre Miguel e Helena Goulart|
| 29/03/2018 | 1.1.1 | Correções de design no documento | Gabriel Albino|
| 31/03/2018 | 1.1.2 | Adição de tópico de nomeação de Interface e Model | Gabriel Albino e Larissa Sales|
| 31/03/2018 | 1.1.3 | Melhoria na formatação do arquivo | Gabriel Albino e Larissa Sales|
| 08/04/2018 | 1.1.4 | Alteração do tópico 3.3 | Gabriel Albino e Larissa Sales|
| 13/04/2018 | 1.1.4 | Alteração das regras dos comentários e da model. | Gabriel Albino|
| 13/04/2018 | 1.1.5 | Corrigido erros de formatação | Gabriel Albino|

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
1. Seguem o padrão _UpperCamelCase_, ou seja, com todas as palavras iniciadas por letras maiúsculas e sem elemento separador, com termos preferencialmente suscintos e sem o uso de _underscores_.

    Ex.:

      * Certo:

        ```
            package PackageName
        ```
      * Errado:

        ```
          package package_name
        ```

### 1.2 Classes
1. São nomeadas conforme o padrão _UpperCamelCase_, sem _underscores_.

    Ex.:

    * Certo:

      ```
        class ClassName
      ```
    * Errado:

      ```
        class classname
      ```

- A classe Model de cada cena devem seguir o padrão _'SceneName'Model_

    Ex.:

    * Certo:

      ```
      	class LoginModel
      ```
    * Errado:

      ```
      	class Model
      ```

### 1.3 Objetos
1. São nomeados conforme o padrão _lowerCamelCase_, ou seja, com a primeira palavra iniciada por letra minúscula e as demais palavras iniciadas por letras maiúsculas.

    Ex.:

    * Certo:

      ```
      	var objectName = ObjectClass()
      ```

    * Errado:

      ```
      	var objectname = ObjectClass()
      ```

### 1.4 Interfaces
1. São nomeados conforme o padrão _UpperCamelCase_ e sem _underscores_.

    Ex.:

    * Certo:

      ```
      	interface InterfaceName
      ```
    * Errado:

      ```
      	interface interfacename
      ```

2. Interfaces pertencentes à Presenter devem ser nomeadas com _'SceneName'PresentationLogic_

    Ex.:

    * Certo:

      ```
      	interface LoginPresentationLogic
      ```
    * Errado:

      ```
      	interface PresentationLogic
      ```

3. Interfaces pertencentes à View devem ser nomeadas com _'SceneName'DisplayLogic_

    Ex.:

    * Certo:

      ```
      	interface LoginDisplayLogic
      ```

    * Errado:

      ```
      	interface DisplayLogic
      ```

4. Interfaces pertencentes à interactor devem ser nomeadas com _'SceneName'BusinessLogic_

    Ex.:

    * Certo:

      ```
      	interface LoginBusinessLogic
      ```

    * Errado:

      ```
      	interface BusinessLogic_
      ```

### 1.5 Métodos
1. A nomenclatura segue o padrão _lowerCamelCase_, sem o uso de _underscores_ e acompanhado de parênteses.

    Ex.:

    * Certo:

      ```
      	fun methodName()
      ```

    * Errado:

      ```
      	fun Method_name()
      ```


### 1.6 Atributos
1. Segue o padrão _lowerCamelCase_, contendo nomes significativos e sem _underscores_.

    Ex.:

    * Certo:

      ```
      	fun method(attribute: String)
      ```

    * Errado:

      ```
      	fun method(Attribute_Example: String)
      ```


### 1.7 Variáveis
1. Devem ser precedidas pela declaração do tipo _var_ para valores alteráveis ou _val_ para valores fixos, seguindo o padrão _lowerCamelCase_ , contendo nomes significativos e sem _underscores_.

    Ex.:

    * Certo:

      ```
      	var variableExample
      ```

    * Errado:

      ```
      	var Variable_Example
      ```


### 1.8 Testes

1. Utilizarão do padrão _UpperCamelCase_ e não permitirão o uso de _underscores_.

   Ex.:

    * Certo:

    ```
      class TestCase {
      
      }
    ```

    * Errado:

      ```
      	class test_case {

      	}
      ```

2. Classe de teste deverá ter o nome da classe a ser testada e deverá terminar com a palavra "Test", utilizando o padrão _upperCamelCase_

   Ex.:

    * Certo:

      ```
      	class someClassTest {

      	}
      ```

    * Errado:

      ```
      	class someclasstest {

      	}
      ```

## 2 Formatação e Estilo

1. As chaves devem ser abertas no final de cada linha e fechadas em uma linha separada e alinhada com a mesma coluna em que foi aberta e haverá espaços após a escrita de classes, métodos e estruturas de decisão.

    Ex.:

    * Certo:

      ```
      	class Formating_Example {

      	}
      ```

    * Errado:

      ```
      	class Formating_Example {

                                                }  
      ```

2. Pontos e vírgulas não serão utilizados no final de cada linha de código.

    Ex.:

    * Certo:

      ```
      	var rightExample
      ```
    * Errado:

      ```
      	var example;
      ```

3. Serão usados espaços entre operações lógicas, como somas e subtrações, todavia não serão utilizados em casos de incrementação.

    Ex.:

    * Certo:

      ```
      	var rightExample = 1 + 1
      ```

    * Errado:

      ```
      	var wrongExample=1+1
      ```
    * Certo:

      ```
      	i++
      ```
    * Errado:

      ```
      	i + +
      ```


## 3 Comentários

1. Devem iniciar com letra maiúscula e ter espaço apoś "//". Para uma maior compreensão do código, os comentários devem estar presentes neles como uma breve apresentação do objetivo e como o método funciona.

    Ex.:

    - Certo:

      ```
        // Right example
      ```
    * Errado:
  
      ```
        //wrong example
      ```

2. Devem ser escritos no mesmo idioma do código

    Ex.:

    * Certo:

    ```
      //Right example
    ```
    * Errado:

      ```
      	//Exemplo errado
      ```

3. Comentários de uma linha devem ser escritos usando `//`

    Ex.:

    * Certo:

      ```
      	//Right example
      ```

    * Errado:

      ```
      	/* Wrong example */
      ```

4. Comentários de mais de uma linha devem ser escritos usando `/* */`, contendo quebra de linhas que auxiliem na visualização do comentário, sendo iniciado e finalizado na mesma linha da sinalização. Cada linha do comentário deve estar alinhada com a primeira linha, mantendo a inicialização do texto na mesma coluna.

    Ex.:

    * Certo:

      ```
      	/* This is an example about
          how to use correctly the commentary
          on the code */
      ```

    * Errado:

      ```
      /*
      This is not
          the right example
        about using a commentary
      */
      ```


### 3.1 Comentários em classes

1. Os comentários devem estar uma linha acima da classe referenciada, possuindo quebras de linha para melhor visualização do conteúdo e contendo uma breve descrição da funcionalidade dessa classe. O uso de comentários em classes é obrigatório.

    Ex.:

      * Certo:

        ```
          /* This class exemplifies
            and portraits how to correctly
            use the commentaries on the classes */

        class ExampleClass<Parameter> : ClassImplementations {

        }
        ```

      * Errado:

        ```
          // This is not the right example
          // about using a commentary in class
          class ExampleClass<Parameter> : ClassImplementations {
            //neither is this expeficiations

          }

        ```

### 3.2 Comentários em interfaces

1. Os comentários devem estar uma linha acima da interface referenciada.
  - A primeira linha deve conter uma breve descrição da funcionalidade dessa interface.
  
  O uso de comentários em interfaces é opcional.

    Ex.:

    * Certo:

      ```
      /* This interface do the
          comunication between other
          packages and classes */

          interface ExampleInterfaceLogic {

              fun doComunication(request: ExampleModel.Request)
          }
      ```

    * Errado:

      ```
      // This interface do the
        //    comunication between other
          //  packages and classes
            interface ExampleInterfaceLogic {

                fun doComunication(request: ExampleModel.Request)
            }

      }

      ```

### 3.3 Comentários em Métodos

1. Os comentários devem seguir o padrão KDoc, isto é, iniciar com `\**` e ser finalizado com `*\`, além de estar uma linha acima do método.

- A primeira linha deve conter uma breve explicação da funcionalidade implementada, seguida de uma linha em branco.
- A partir da segunda lista são descritos as tags da função. São elas:
  - @param: Descreve os argumentos da função. cada argumento deve estar em uma nova linha seguindo o padrão `@param [name] description`
  - @return: Descreve o tipo e a descrição do retorno da função seguindo o padrão `@return [name] description`
  - @throws: Descreve as excessões que o método pode lançar separadas por virgula, seguindo o padrão `@throws InvalidArgumentExeption, NullPointerException`
  
  O uso de comentários em métodos não é obrigatório, mas é extremamente recomendado.

    Ex.:

    * Certo:

      ```
      /**
      * Returns an Image object that can then be painted on the screen.
      * The url argument must specify an absolute {@link URL}. The name
      * argument is a specifier that is relative to the url argument.
      * <p>
      * This method always returns immediately, whether or not the
      * image exists. When this applet attempts to draw the image on
      * the screen, the data will be loaded. The graphics primitives
      * that draw the image will incrementally paint on the screen.
      *
      * @param  url  an absolute URL giving the base location of the image
      * @param  name the location of the image, relative to the url argument
      * @return      the image at the specified URL
      */

      fun getImage(URL url, String name): Image {
          try {
             return getImage(new URL(url, name));
          } catch (MalformedURLException e) {
             return null;
          }
      }
      ```

    * Errado:

      ```
      fun getImage(URL url, String name): Image {
          try {
             return getImage(new URL(url, name));
          } catch (MalformedURLException e) {
             return null;
          }
      }
      ```

### 3.4 Comentários em Atributos

1. Os comentários em atributos devem ser feitos na mesma linha de declaração dos mesmos, separados por um espaço entre o símbolo ``//`` e iniciados com letra maiúscula, explicitando o conteúdo referenciado. O Uso de comentários em atributos é obrigatório.

   Ex.:

    * Certo:

      ```
        class ExampleClass<Parameter> : ClassImplementations {

          var example : String? = "" // Reference to worker

        }
      ```

    * Errado:

      ```
        class ExampleClass<Parameter> : ClassImplementations {

          var example : String? = "" /* wrong reference to worker*/

        }
      ```


### 3.5 Comentários em Variáveis

1. Os comentários em variáveis devem ser feitos na mesma linha de declaração, separados por um espaço entre o símbolo ``//`` e iniciados com letra maiúscula, explicitando o conteúdo utilizado. O Uso de comentários em variáveis é opcional.

    Ex.:

    * Certo:

        ```
          var name : String? = null // Stores name of players
        ```

    * Errado:

        ```
          var name : String? = null /* the variable is called name*/

        ```

### 3.6 Comentários em Testes

1. Utilizar comentários de uma linha para descrever cada etapa do teste, imetiadamente antes da etapa descrita.

   Ex.:

   * Certo:

      ```
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

      ```
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

### 3.7 Referencia a outras documentações

1. Referencia a outra classe podem ser feitas para evitar documentação reduntante. Para utiliza-la deve-se seguir o padrão `[nomeDaClasse]`
   Ex.:

   * Certo:

      ```
      /**
      * Class that implements [ProgramDisplayLogic] and controls program scene.
      */
      ```
   * Errado:

      ```
      /**
      * Class that use the function doSomething that is on ProgramDisplayLogic and this function is responsible to do something.
      */
      ```

## 4 Import

1. As importações deverão usar o termo _import_ seguido do nome do pacote ou classe, e em caso de mais de uma chamada é necessário que seja realizada em uma linha separada.

    Ex.:

    * Certo:
      ```
      import NameOfPackage
      import NameOfClass
      ```

    * Errado:
      ```
      import NameOfPackage, import NameOfCLass
      ```


## 5 Estruturas de Decisão

1. Serão colocados espaços entre essas estruturas e os parênteses que contém os termos. Sempre serão usadas chaves para implementar o corpo das condicionais.

    Ex.:

    * Certo:

      ```
      if (rightExample == right) {
              return rightExample
            }
      ```
    * Errado:

      ```
      if(wrongExample == wrong)
                return wrongExample
      ```

## 6 Identação

1. A identação deve ser de 4 espaços para cada nível;

    Ex.:

      * Certo:

        ```
        if (examples != null)
            examples == 1
        }
        ```
      * Errado:

        ```
        if (examples == null) {
          examples == null
        }
        ```

## 7 Linguagem

1. O linguagem padrão do programa é em inglês, extendendo-se para pacotes, classes, objetos, atributos e variáveis.

    Ex.:

    * Certo:

      ```
      class GreatExample;
      ```
    * Errado:

      ```
      classe ExemploErrado;
      ```

## 8 Estilo da Model

1. A _Model_ deve ser organizada de modo em que possua 3 classes internas, que serão a _request_, a _response_ e a _ViewModel_, necessáriamente nessa ordem.

   Ex.:

   * Certo:

      ```
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

      ```
        class Model {
            var age: Int? = null
            var name: String? = null
        }
      ```
2. As classes da model devem conter apenas informações essenciais para seu funcionamento.

    Ex.:
        
    * Certo:

      ```
        class Model {
            class Request(userID: Integer) 
            ...
        }
      ```
      
    * Errado:

      ```
        class Model {
            class Request(userID: Integer, someUnusedArgument: String, anotherUnusedArgument: Integer) 
            ...
        }
      ```

3. Classes da model que não são as essenciais (Request, Response e ViewModel) devem estar separadas por um comentário que segue o modelo:

   ````// ------------------------------ Aux classes to use in this scene --------------------------------------````

   E esse comentário deve estar localizado abaixo das mesmas.

    Ex.:
          
    * Certo:

      ```
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
            // ------------------------------ Aux classes to use in this scene --------------------------------------
            class User (userID: Integer)
        }
      ```
      
    * Errado:

      ```
        class User (userID: Integer)
 
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
      ```



## 9 Referências

[Kotlin Style Guide](https://kotlinlang.org/docs/reference/coding-conventions.html)
