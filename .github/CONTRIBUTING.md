# Contribuir com o Nexte

Obrigado por contribuir com este projeto maravilhoso, aqui podem ser encontradas alguns guidelines para serem conhecidos antes de contribuir, depois isso, basta mandar um pull request seguindo as regras apresentadas aqui.

### Relatar bug  
Se você encontrou um bug, por favor, abra uma Issue com uma label de **Bug** e preencha o formulário com a descrição contando o que foi encontrado, além disso o template tem a parte de critérios de aceitação, para issue de bugs ele deve ser excluido.

### Primeira contribuição
As issues que já foram criadas, elas tem labels que representam sua complexibilidade, _easy_, _medium_, _difficult_, é recomendado pegar uma issue com dificuldade _easy_ para primeira contribuição.

### Commits
para commitar neste projeto você deve seguir o seguinte template:

    $ git commit -m "Titúlo princpal do commit
    > 
    > * Frase de uma alteração que foi feita
    > * Frase que descreve outra alteração feita"


### Pull request

Para realizar o pull request deve ser observado o template criado e o padrão já escrito 

    Descrição
    breve descrição sobre o pull request

    Qual tipo de mudança o Pull Request introduz?
    o que foi mudado neste pull request

    Qual é o comportanto atual?
    Qual o comportamento que o aplicativo tem atualmente

    Qual é o novo comportamento?
    Qual o comportamento que o aplicativo terá depois do pull request aceito

    Quais as issues o Pull Request finaliza?
    Issue que é finalizada por este pull request: ex #100

### Análise de código

Neste projeto é utilizada a ferramenta **Detekt** para análise estática de código, para executá-la é necessário estar dentro da pasta _project_ e no terminal executar o comando :

    ./gradlew detektCheck

**Métricas a serem analisadas:**

* Quantidade de linhas por método
* Quantidade de métodos por classe
* Adequação à folha de estilo
* Complexidade ciclomática
* Quantidade de Code smells

## INSERIR VALORES ACEITÁVEIS

**Cobertura de código**

A cobertura de código é feita pelo Jacoco, para averiguar a cobertura é necessário gerar o relatório de cobertura de testes e olha-lo na pasta _build/reports/coverage/debug_ o arquivo gerado é um index.html.

O comando para gerar o relatório de cobertura de código é:

    ./gradlew createDebugCoverageReport

