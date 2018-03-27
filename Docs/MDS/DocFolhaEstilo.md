### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 26/03/2018 | 0.1 | Criação do documento | Larissa Sales e Letícia Meneses |


### Índice Analítico
* [1 Nomeação](#1-nomeação)
* [2 Formatação](#2-formatação)
* [3 Comentários](#3-comentários)



## 1 Nomeação
### 1.1 Pacotes
  Devem ser preferencialmente suscintos, e caso sejam maiores serão utilizadas letras maiúsculas para facilitar a leitura. Não é recomendado o uso de _underscores_.

  ```
  Certo: NomeDoPacote 
  Errado: nome_do_pacote 
  ```
### 1.2 Classes e Objetos
  São nomeados conforme o padrão camelo, ou seja: palavras diferenciadas por letras maiúsculas.

  ```
  Certo: NomeDoObjeto ou NomeDaClasse
  Errado: nomedoobjeto ou nomedaclasse
  ```


### 1.3 Interfaces
### 1.4 Funções
### 1.5 Atributos 
### 1.6 Variáveis



* Atributos devem ser escritos no formato _lowerCamelCase_:

  ``
  Exemplo: exemploVariavel
  ``

* Os atributos devem ter nomes significativos;

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
