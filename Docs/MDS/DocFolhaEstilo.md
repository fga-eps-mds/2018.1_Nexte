### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 26/03/2018 | 0.1 | Criação do documento | Larissa Sales e Letícia Meneses |
| 27/03/2018 | 0.2 | Definição das nomeações | Alexandre Miguel e Helena Goulart |


### Índice Analítico

* [1 Nomeação](#1-nomeação)
* [2 Formatação](#2-formatação)
* [3 Comentários](#3-comentários)
* [4 Teste](#4-teste)
* [5 Imports](#5-imports)
* [6 Estilo](#6-estilo-da-Model)
* [7 Estruturas de Decisão](7-#estruturas-de-decisão)
* [8 Identação](#8-identação)
* [9 Linguagem](#9-linguagem)
* [10 Definições, Acrônimos e Abreviações](#10-definições-acrônimos-e-abreviações)
* [11 referência](#11-referência)



## 1 Nomeação
### 1.1 Pacotes
  Seguem o padrão _UpperCamelCase_, ou seja, com todas as palavras iniciadas por letras maiúsculas e sem elemento separador, com termos preferencialmente suscintos e sem o uso de _underscores_.

  Ex.:

  ```
  Certo: PackageName
  Errado: package_name
  ```

### 1.2 Classes
  São nomeadas conforme o padrão _UpperCamelCase_, sem _underscores_.

  Ex.:

  ```
  Certo: ClassName
  Errado: classname
  ```

### 1.3 Objetos
  São nomeados conforme o padrão _lowerCamelCase_, ou seja, com a primeira palavra iniciada por letra minúscula e as demais palavras iniciadas por letras maiúsculas.

  Ex.:

  ```
  Certo: ObjectName
  Errado: objectname
  ```

### 1.4 Interfaces
  São nomeados conforme o padrão _UpperCamelCase_ e sem _underscores_.

  Ex.:

  ```
  Certo: InterfaceName
  Errado: interfacename
  ```

### 1.5 Funções
  A nomenclatura segue o padrão _lowerCamelCase_, sem o uso de _underscores_ e acompanhado de parênteses.

  Ex.:

  ```
  Certo: functionName()
  Errado: Function_name()
  ```


### 1.6 Atributos
  Segue o padrão _lowerCamelCase_, contendo nomes significativos e sem _underscores_.

  Ex.:

  ```
  Certo: attribute
  Errado: Attribute_Example
  ```


### 1.7 Variáveis
  Devem ser precedidas pela declaração do tipo _var_ para valores alteráveis ou _val_ para valores fixos, seguindo o padrão _lowerCamelCase_ , contendo nomes significativos e sem _underscores_.

  Ex.:

  ```
  Certo: var variableExample
  Errado: Variable_Example
  ```


### 1.8 Testes


* Pacotes, Classes e Interfaces devem ser escritos no formato _UpperCamelCase_:

  ``
  Exemplo: ExemploClasse
  ``

## 2 Formatação

* A identação deve ser de 4 espaços para cada nível;

* Blocos que seguem a mesma lógica devem ser separados por uma linha vazia;

## 3 Comentários

* Devem iniciar com letra maiúscula;

* Devem ser escritos no mesmo idioma do código;

* Comentários de uma linha devem ser escritos usando `//`:

  ``
  Exemplo: //Exemplo de comentário
  ``

* Comentários de mais de uma linha devem ser escritos usando `/* */`:

    `Exemplo: /* Exemplo de comentário */`

* Comentários de testes devem descrever qual será o resultado esperado;



## Referências
[Kotlin Style Guide](https://kotlinlang.org/docs/reference/coding-conventions.html)
