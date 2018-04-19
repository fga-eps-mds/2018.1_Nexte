### Histórico de reivsão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 18/04/2018| 0.1 | Criação do documento| Luis Gustavo|


## Post Mortem
Postmortem é um documento que sinaliza a conclusão de um projeto onde a equipe faz um pontos positivos e negativos e melhorias que poderiam ser aplicadas em projetos futuros.

### Pontos fortes

* DOJOs nas reuniões de sábado foram muito úteis para auxiliar MDS e para mitigar riscos;
* Comunicação/interação entre as equipes funcionou **muito** bem;
* Conseguimos cumprir o planejamento do roadmap da release 1;
* Equipe conseguiu utilizar muito bem o github para comunicação referentes a issues e pull requests;
* Dedicação e compromisso do projeto de todos os membros da equipe;
* MDS gostou muito da arquitetura utilizada no projeto, mesmo com toda a dificuldade de compreendê-la;
* Documentação do Kotlin é muito boa;
* MDS está bem tranquila e confiante com git;

### Pontos fracos

* Algumas sprints foram discrepantes(umas muito pesadas e outras fáceis);
* Polarização do conhecimento prejudicou o conhecimento de MDS em relação a arquitetura e código;
* A pontuação de algumas issues não refletiu o real esforço para realizá-las;
* Nas primeiras sprints tiveram muitos replanejamentos;
* Desenvolvimento Nativo Android engessado com Android Studio;
* Alta dependência do Android Studio;
* Deploy automatizado para loja difícil de ser feito devido a assinatura do APK que deve ser única para a vida, e os CI não conseguem armazena-lá devido ao tipo e a criptografia. Para conseguir outra forma de gerar uma assinatura, é bem difícil de achar informações;
* Desenvolvimento com Alta dependência de Emulador, o que não faz sentido usar Docker pois o mesmo não tem como objetivo gerar uma interação gráfica;
* Arquiteto demorou até descobrir seu sentido real no projeto;
* Scrum Master demorou para fazer algumas coisas essenciais no projeto, como adicionar quadro de conhecimentos, fazer gestão de risco e definir a produtividade da equipe;
* PO não teve sentido no projeto, se desde o início tivessemos tidos 2 devops provavelmente teriamos tido menos dívidas técnicas;
* Várias sprints com dívidas técnicas;

### Lições aprendidas

* É essencial que a equipe que as reuniões de planejamento sejam bem preparadas e que a equipe esteja focada durante essa reunião, se não é bem provável que haja replanejamentos, histórias mal pontuadas, etc...;
* EPS deveria ter focados nos seus papéis específicos desde mais cedo e gastado menos tempo com algumas documentações do projeto;
* MDS - os integrantes que não estão dominando alguma tecnologia devem correr atrás do prezuijo e não ficar dependendo da sua dupla de pareamento para ensiná-los realizar a história;
* Os ritos de sprint serão extremamente importantes para o sucesso das sprints;
* Ainda que gaste muito tempo para documentar planejamento/resultado de sprint, essas são documentações muito importantes para entendimento/análise da equipe/projeto. Fazer esses documentos de qualquer maneira será só perda de tempo que não trará resultado algum;


