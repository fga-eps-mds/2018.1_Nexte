# Nexte

[![Icon-_App-1024x1024.png](https://s14.postimg.org/6zkzknic1/Icon-_App-1024x1024.png)](https://postimg.org/image/gww0dppxp/)

[![pipeline status](https://gitlab.com/Baldissera/2018.1_Nexte/badges/dev/pipeline.svg)](https://gitlab.com/Baldissera/2018.1_Nexte/commits/dev)

O Nexte tem como objetivo promover a integração entre tenistas a partir da realização de desafios entre os membros desta comunidade. Não obstante, Nexte é desenvolvido com tecnologias modernas como: Docker, Kotlin, Node, GO e muitas outras.

Não obstante, além desse repositório referente ao *frontend*, o Nexte dispõe de outro repositório aberto: Servidor de imagens, desenvolvido em Go. No caso, são adotadas as mesmas políticas adotadas nesse repositório, para mais informações accese no [GitLab](https://gitlab.com/Baldissera/NexteImageServer).

For english support, read out [README](https://github.com/fga-gpp-mds/2018.1_Nexte/blob/dev/Docs/EPS/READMEen.MD) provided in 
english.

## Primeiros Passos

É nessário realizar alguns passos antes de começar.

### Prerequisitos e Instalação

Neste momento, é necessário instalar o Android Studio com a SDK 22. Se ainda não fez isso, por favor siga os passos disponibilizados na 
[Documentação Oficial do Android.](https://developer.android.com/studio/index.html)

### CI e Docker

O processo de integração continua utilizada no Nexte é provida pelo GitlabCI. 

Assim, o *CI* pode ser dividido em 4 etapas:

1. Análise Estática: Fase que o CI a partir do docker executa a análise estática do código. Utiliza-se do Docker para realizar essa tarefa

Caso deseje executar apenas a análise estática:

```bash
  docker run -v $(pwd)/project:/application -v $(pwd)/docker/local.properties:/application/local.properties baldissera/android-container:2.0 /bin/bash "/static_analysis.sh"
```
    
2. Testes: Fase que o CI executa os testes unitários. Utiliza-se do Docker para realizar essa tarefa.

Caso deseje executar apenas os testes:

```bash
  docker run -v $(pwd)/project:/application -v $(pwd)/docker/local.properties:/application/local.properties baldissera/android-container:2.0 /bin/bash "/test.sh"
```

3. Build: Fase em que é criado uma build, .apk. Utiliza-se do Docker para realizar essa tarefa.

Caso deseje executar uma build:

```bash
  docker run -v $(pwd)/project:/application -v $(pwd)/docker/local.properties:/application/local.properties baldissera/android-container:2.0 /bin/bash "/build.sh"
```

4. *Deploy*: Cria uma *build* pronta para ser disponibilizada como *beta* na *Google Play*. Esta interação do CI apenas ocorre nas branches master e dev. GitlabCI faz uso do *Fastlane*.


Para mais informações sobre CI e seu funcionamento acesse nosso [CONTRIBUTING](https://github.com/fga-gpp-mds/2018.1_Nexte/blob/dev/.github/CONTRIBUTING.md)

5. Para integração com o servidor, basta rodá-lo utilizando o docker para o mesmo ficar disponível em seu ambiente de desenvolvimento. O Servidor ficará disponível na porta 3000.

O comando para isso é:

```bash
  docker run -d -p 3000:3000 registry.gitlab.com/nexte/bepid/nexte-server:0.1.0
```
OU
```bash
  docker-compose up -d
```

### **Observações**

1. Os pull request só poderão ser aceitos caso o *pipeline*  não tenha falhado, desta forma, é recomendado sempre a utilização do comando:
```bash
docker composer up
```
2. Todos os comandos do docker deverão ser executados na raiz do programa

## *Deployment*

Versões oficiais de *deployment* são disponibilizadas pelos mantedores do respositório. Mas, você pode entrar em contato com eles por [issues](https://github.com/fga-gpp-mds/2018.1_Nexte/issues) e solicitar uma nova versão de *deployment*.

Ressalta-se que a partir do *fastlane* integrado com nosso CI, quando as branches dev/master são submetidas ao  comando:

```bash
docker composer up
```

 É criado uma nova versão de *deploy* pronta para ser disponibilizada como beta no *Google Play*, entretanto é necessária a criação de uma *TAG* no Github.


## Construído com

* [Kotlin](https://kotlinlang.org/) e XML - *Frontend* da aplicação
* [Docker](https://www.docker.com/) - Ferramenta usada para gerenciar dependências e versões
* [Node](https://nodejs.org/en/) - *Web Service*
* [Neo4J](https://neo4j.com) - *Database*
* [Account Kit](https://developers.facebook.com/docs/accountkit/android?locale=pt_BR) - Autenticação
* [Fastlane](https://docs.fastlane.tools) - *Deploy*

## Contribuindo

Por favor, leia [CONTRIBUTING](https://github.com/fga-gpp-mds/2018.1_Nexte/blob/dev/.github/CONTRIBUTING.md) para detalhes do nosso código de conduta e submição de pull request.

# Versões 

Neste momento, ainda não há nenhuma versão de *deployment* para Android.

# Documentação

Para saber mais sobre o projeto acesse a [documentação do projeto](https://fga-gpp-mds.github.io/2018.1_Nexte/)

# Autores

* **Guilherme Baldissera** - *Trabalho inicial* - [Github](https://github.com/guibaldissera)
* **Miguel Pimentel** - *Trabalho inicial* - [Github](https://github.com/miguelpimentel)

Veja também a lista de  [contribuidores](https://github.com/fga-gpp-mds/2018.1_Nexte/graphs/contributors) que participaram deste projeto.

## Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE](https://github.com/fga-gpp-mds/2018.1_Nexte/blob/dev/LICENSE) para mais detalhes.

## Conhecimentos

* Inspiracional
* Utiliza as tecnologias mais recentes em desenvolvimento Android
* Alto suporte dos mantedores



