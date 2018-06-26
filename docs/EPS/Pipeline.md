### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 26/06/2018| 0.1 | Criado versão inicial | Geovanni Oliveira |  
----
### Introdução
O pipeline é usado tanto para realização da Integração Contínua quanto para o Deploy Contínuo. 


### Integração Contínua
O GitLab CI é usado pela equipe apara a implementação da integração contínua, e nela existem três estágios 

* test
* build

#### Test
No estágio de teste é executado a **análise estática** de código, feita pela ferramenta Detekt

Em paralelo s testes unitários são executados, caso algúm teste falhe o pipeline quebra ou se a cobertura de testes estiver menor do que a aceitável ele também quebra.

#### Build

Nesta etapa é verificado se o projeto está com problemas em sua compilação e sua build.

Além de fazer a build do projeto, esta etapa é responsável por fazer o deploy da documentação do projeto para a página do GitHub Pages, porém está etapa só é realizada na branch dev.

<img src = "https://i.imgur.com/gFm6hHB.png" >

### Deploy Contínuo
Stage onde acontece: - deploy  
O Deploy Contínuo é feito com a participação do GitLab CI e do fastlane, no script do o Deploy Contínuo é feito ao gerar uma nova tag. O Aplicativo é submetido para Play Stora em Modo Beta,devido à algumas limitações impostas na equipe   
  
<div>
   <p align="center"> 
        <img src = "https://i.imgur.com/BGA37Jf.png" width="30%">
   </p>
</div>
