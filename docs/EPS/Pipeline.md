### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 26/06/2018| 0.1 | Criado versão inicial | Geovanni Oliveira |  
----
### Introdução
O pipeline é usado tanto para realização da Integração Contínua quanto para o Deploy Contínuo. 


### Integração Contínua
O GitLab CI é usado pela equipe apara a implementação da integração contínua, e nela existem três estágios 

* Test
* Build
* Deploy
#### Test
No estágio de teste é executado a **análise estática** de código, feita pela ferramenta Detekt, cujo os parâmetros aceitáveis são:

**Métricas Detekt**  
* Code Smells < 10  
* Adequação à folha de estilo  
* Complexidade ciclomática por método < 10  
* Método < 30 linhas
* Classe <= 15 métodos


Em paralelo s testes unitários são executados, caso algúm teste falhe o pipeline quebra ou se a cobertura de testes estiver menor do que a aceitável ele também quebra.

Para a Release 1 o critério para passar no estágio de Test era ter 40% de cobertura de código, já na Release 2 o objetivo era de deixar o projeto com cobertura de 90%, mas como até alguns dias atrás não estava, o projeto estava com o critério que de acordo que acobertura ia aumentando ela não poderia abaixar, somente aumentar, e até chegar à 90% de cobertura esse critério foi utilizado.

Esta etapa é feita em todas as branches e tags.  
#### Build

Nesta etapa é verificado se o projeto está com problemas em sua compilação e sua build.

Além de fazer a build do projeto, esta etapa é responsável por fazer o deploy da documentação do projeto para a página do GitHub Pages, porém está etapa só é realizada na branch dev.
Assim como a etapa anterior, esta etapa é executada  em todas as branches e tags.
<img src = "https://i.imgur.com/gFm6hHB.png" >

### Deploy Contínuo
Stage onde acontece: - deploy  
O Deploy Contínuo é feito com a participação do GitLab CI e do fastlane, no script do o Deploy Contínuo é feito ao gerar uma nova tag. O Aplicativo é submetido para Play Store em Modo Beta, devido a algumas limitações externas, fora de controle, impostas à equipe.

  
<div>
   <p align="center"> 
        <img src = "https://i.imgur.com/BGA37Jf.png" width="30%">
   </p>
</div>

### CI no servidor de imagens

O servidor de imagens se encontra em um repositório separado em relação ao de código e nele também há a Integração Contínua, e em relação ao servidor o CI tem apenas três estágios, e são eles: 

* Test
* Build

A Integração Contínua do servidor é feito em um outro repositório a parte do repositório do código fonte.