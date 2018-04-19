# Contribuir com o Nexte

Obrigado por contribuir com este projeto maravilhoso, aqui podem ser encontradas alguns guidelines para serem conhecidos antes de contribuir, depois isso, basta mandar um pull request seguindo as regras apresentadas aqui.

### Relatar bug  
Se você encontrou um bug, por favor, abra uma Issue com uma label de **Bug** e preencha o formulário com a descrição contando o que foi encontrado, além disso o template tem a parte de critérios de aceitação, para issue de bugs ele deve ser excluido.

### Primeira contribuição
As issues que já foram criadas, elas tem labels que representam sua complexibilidade, _easy_, _medium_, _difficult_, é recomendado pegar uma issue com dificuldade _easy_ para primeira contribuição.

### Commits
para commitar neste projeto você deve seguir o seguinte template:
```bash
    $ git commit -m "Titúlo princpal do commit
    > 
    > * Frase de uma alteração que foi feita
    > * Frase que descreve outra alteração feita"
```

### Docker e CI

Neste projeto foi utilizado o Gitlab CI junto ao docker para gerenciar o controle de versão e qualidade. Desta forma, nosso processo de CI pode ser divido em etapas:

1. Análise Estática: Fase que o CI a partir do docker executa a análise estática do código. Utiliza-se do Docker para realizar essa tarefa, nesta fase é provida um relatório com duração de 48 horas

Caso deseje executar apenas a análise estática:

```bash
  docker run -v $(pwd)/project:/application -v $(pwd)/docker/local.properties:/application/local.properties baldissera/android-container /bin/bash "/static_analysis.sh"
```
    
2. Testes: Fase que o CI executa os testes unitários. Utiliza-se do Docker para realizar essa tarefa. 

Caso deseje executar apenas os testes:

```bash
  docker run -v $(pwd)/project:/application -v $(pwd)/docker/local.properties:/application/local.properties baldissera/android-container /bin/bash "/test.sh"
```

3. Build: Fase em que é criado uma build, .apk. Utiliza-se do Docker para realizar essa tarefa. 

Caso deseje executar uma build:

```bash
  docker run -v $(pwd)/project:/application -v $(pwd)/docker/local.properties:/application/local.properties baldissera/android-container /bin/bash "/build.sh"
```

4. *Deploy*: Cria uma *build* pronta para ser disponibilizada como *beta* na *Google Play*. Esta interação do CI apenas ocorre nas branches master e dev. GitlabCI faz uso do *Fastlane*, e é necessário a criação de uma TAG para o app estar disponibilizado na loja.

Ressalta-se que as fases 1, 2 e 3 são realizadas por todas as *branches*.

 A imagem a seguir apresenta estrutura do nosso processo de integração contínua:
 
 [![Group_2.png](https://s17.postimg.cc/yru8x54m7/Group_2.png)](https://postimg.cc/image/sqwk02hzv/)

5. Para integração com o servidor, basta rodá-lo utilizando o docker para o mesmo ficar disponível em seu ambiente de desenvolvimento. O Servidor ficará disponível na porta 3000.

O comando para isso é:
```bash
  docker run -d -p 3000:3000 registry.gitlab.com/nexte/bepid/nexte-server:0.1.0
```
OU
```
  docker-compose up -d
```

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

**Observação**: Os *pull requests* só serão avaliados quando o *pipeline* não estiver falho.

### Análise de código

Neste projeto é utilizada a ferramenta **Detekt** para análise estática de código, para executá-la é necessário estar dentro da pasta _project_ e no terminal executar o comando :

    ./gradlew detektCheck

**Métricas de código a  serem analisadas:**

* Quantidade de linhas por método
* Quantidade de métodos por classe
* Adequação à folha de estilo
* Complexidade ciclomática
* Quantidade de Code smells
 Coisas que não são um problema atualmente, mas no futuro pode vir a ser um bug no projeto  

**Parametros**

* Quantidade de linhas por método
    * máximo 30 linhas 
* Quantidade de métodos por classe
    * 20 métodos por classe
* Adequação à folha de estilo
    * ser totalmente condizente com a [folha de estilo](/docs/MDS/DocFolhaEstilo.md)

* Complexidade ciclomática
    De acordo com o [artigo publicado](http://www.mccabe.com/pdf/MeasuringSoftwareComplexityUAV.pdf) pelo criador da métrica de complexidade ciclomática os padrões por método são descritos na tabela abaixo
    | Complexidade | Avaliação |
    | ---- | ---- |
    | 1-10 | Método simples. Baixo risco |
    | 11-20 | Método razoavelmente complexo. Moderado risco. |
    | 21-50 | Método muito complexo. Elevado risco. |
    | 51-N | Método de altíssimo risco e bastante instável. |

* Quantidade de Code smells
    * máximo 5 Code Smells

**Cobertura de código**

A cobertura de código é feita pelo Jacoco, para averiguar a cobertura é necessário gerar o relatório de cobertura de testes e olha-lo na pasta _build/reports/coverage/debug_ o arquivo gerado é um index.html.

O comando para gerar o relatório de cobertura de código é:

    ./gradlew createDebugCoverageReport

Ela deve ser de no minimo 40%  para a primeira Release e de 90% para a segunda Release 