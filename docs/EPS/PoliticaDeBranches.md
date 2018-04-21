### Histórico da Revisão
| Data | Versão | Descrição | Autor |
|---|---|---|---|
| 10/03/2018| 0.1 |Criação do documento | Geovanni Oliveira | 
| 13/03/2018| 0.2 |Criação do proposta inicial da política de branches| Geovanni Oliveira | 
| 14/03/2018| 0.3 |Adicionado gitflow e restruturado documento| Luis Gustavo|
| 04/04/2018 | 0.4 | Adicionado versão 1.0 do giflow | Luis Gustavo|
| 04/04/2018 | 0.5 | Corrigido erros de formatação | Gabriel Albino|

-------------------------------------------------------------------------------------------------

## Versão 1.0

* <b><em>Branch gh-pages</em></b>: *Branch* será a branch utilizada por guardar os arquivos gerados pelo [mkdocs](http://www.mkdocs.org/). Essa branch conterá os arquivos do site referente a documentação da disciplina. Todo final de _sprint_ essa branch será atualizada por meio da _branch_ dev.

* As demais branchs continuam funcionando da mesma forma a 0.1.


![Gitflow](https://i.imgur.com/w7heu6M.png) 
_Figura 1 - Gitflow 1.0_

## Versão 0.1
### Gitflow
* <b><em>Branch master</em></b>: *Branch* que contém código em nível de produção, no caso deste projeto é 
a *branch* que conterá o código para ser apresentado para as *releases*. O código mais maduro existente na 
sua aplicação. Todo o código novo produzido eventualmente é juntado com a branch master, em algum momento do 
desenvolvimento.

* <b><em>Branch hotfix</em></b>: São *branches* no qual são realizadas correções de bugs críticos encontrados 
em ambiente de produção, e que por isso são criadas a partir da *branch master*, e são juntadas diretamente 
com a *branch master* e com a *branch dev*. Essas *branches* deverão ter o nome começando com a palavra "hotfix/" 
e terminado com a ultima tag da *branch master*. Ex: hotfix/0.1

* <b><em>Branch dev</em></b>: *Branch* que contém código em nível preparatório para a próxima *release*. 
Quando *features* são terminadas, elas são juntadas com a *branch dev*, testadas e somente depois as atualizações
 da *branch dev* passam por mais um processo para então ser juntadas com a *branch master*.

* <b><em>Branch feature</em></b>: *Branches* quais são desenvolvidos recursos novos para o projeto. Elas tem por 
são criadas a partir da *branch dev* (pois um recurso pode depender diretamente de outro recurso em algumas 
situações), e, ao final, são juntadas com a *branch dev* via *pull requests*. Essas *branches* deverão ter o 
nome começando com a palavra "feature/" e terminado com a o número referente a *issue* a qual a *feature* está 
associada. Ex: feature/14


![Gitflow](https://i.imgur.com/tETmqYN.png)  
_Figura 1 - Gitflow 0.1_

### Referências
><p align="justify">Git Flow – Uma forma legal de organizar repositórios git. Disponível em: <a href="https://fjorgemota.com/git-flow-uma-forma-legal-de-organizar-repositorios-git/">https://fjorgemota.com/git-flow-uma-forma-legal-de-organizar-repositorios-git/</a>. Acesso em: 14/03/2018</p>

><p align="justify">Using git-flow to automate your git branching workflow. Disponível em: <a href="https://jeffkreeftmeijer.com/git-flow/">https://jeffkreeftmeijer.com/git-flow/</a>. Acesso em: 14/03/2018</p>
