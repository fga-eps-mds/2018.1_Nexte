# Arquitetura de Microserviços


O nexte é uma aplicação android em que o backend é separado do frontend. Com o objetivo de criar um infraestrutura que melhor atende-se esse critérios, optou-se pelo uso do docker, e seus containters.

* Frontend: Para o frotend foi destinado um container que abrange algumas configuraões básicas de nosso ambiente android. No caso, neste container estão as configurações do *Grandle*, o SDK 22 e o Circle CI. O Grandle apresenta as configurações de ambiente e suas propriedades que serão utilizadas por nossa IDE(Android Studio). O SDK 22 foi escolhido por ser o kit de desenvolvimento que mais abrange em números absolutos dispositivos android. Por fim, Circle CI é responsável pela integração continua.

* Backend: Em nosso Backend foi utilizado o NODE js como *web service* que será integrado com algum banco de dados, entretanto este ainda não foi decidido.

## Arquitetura de Microserviços

[![Architecture.png](https://s7.postimg.org/hbbrc60dn/Architecture.png)](https://postimg.org/image/r8ms587zb/)