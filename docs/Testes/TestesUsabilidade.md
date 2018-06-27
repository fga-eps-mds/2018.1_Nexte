### Histórico de reivsão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 25/06/2018| 0.1 | Criação do Documento| Luis Gustavo |

----

## Primeira rodada de testes - feita no início do mês maio

Foram feitos testes com 25 usuários e esses teste não foram gravados. Foi pedido para os usuário realizarem algumas tarefas, como: **Olhar seu perfil**, **Ver quem ganhou um jogo específico no feed**, **Marcar um desafio**, **Encontrar sua posição no ranking**, e em cima da experiência e feedback dos usuário foram anotados os seguintes pontos.


* Tela de Feed 

| Comentário | Status |
| -----|--------|
| Adicionar comentários nas atividades do Feed seria algo bastante interessante | Alteração implementada. | 
| Evidenciar se as atividades são de jogos futuros ou jogos que já aconteceram | Alteração implementada. Agora mostra no feed há quanto tempo aconteceu a partida. | 
| Deu a entender que aparecem jogadores que já desfiei | Aqui o usuário não entendeu direito, pois no feed podem aparecer jogos do próprio usuário. | 

* Tela de Perfil

| Comentário | Status |
| -----|--------|
| O botão de Perfil deveria estar em outro lugar | Alteração implementada. Botão de perfil foi colocado na Tabbar/Bottom Navigation. | 
| A borda vermelha ao redor da foto do perfil, remete ao Stories do Instagram, sugerindo que ao clicar ali vai acontecer alguma coisa | Não será feita nenhuma alteração. Isso não afeta a experiência do usuário e apenas um usuário entendeu isso. |
| O botão de contato no perfil logado não faz sentido | No futuro, o botão contato será colocado dentro de editar perfil, onde terá mais sentido. | 
| A label do estado atual do perfil dos tenista, como "ativo” e “machucado”, está meio jogada, visualmente falando | Alteração implementada. A label foi reposicionada e adicionado uma borda a ela. | 
| Poderia ter informações sobre o nível de jogo do tenista |  | 
| As estatísticas são legais mas não são simples de entender. O gráfico não é muito preciso | No futuro, os gráficos serão mais descritivos. Para o escopo da disciplina, não será feitas alterações pois não é uma prioridade do cliente | 
| Pensar também nas cores vermelhas da tela | O usuário em questão não gostou das cores escuras. Mas não será feita nenhuma alteração em relação a isso. | 
| Sobre os jogos do jogador no perfil, poderia ser uma table View(recycle view, em android) para facilitar ao usuário | Não será feita nenhuma alteração em relação a isso, pois ser fosse uma table view(recycle view) a tela de perfil ficaria muito grande e necessitaria de muitos scrolls para ver todo seu conteúdo. | 
| Faltou trocar a foto de perfil caso o usuário deseje | No escopo da disciplina, isso não será alterado. Mas futuramente isso será adicionado | 
| O gráfico poderia ser ativo e desativado quando o usuário desejar e ter mais precisão de valores | Por vontade do cliente, isso não será adicionado | 

* Tela de Desafiar

| Comentário | Status |
| -----|--------|
| Após desafiar um tenista, o usuário fica meio perdido. Geralmente os usuários acham que a tela que abriu no lugar, tem como objetivo marcar o jogo e não para colocar o resultado da partida | Alterações mplementadas. Agora já é possível realizar o resultado da partida. | 
| Quando seleciona um jogador não tem como desclicar ele novamente | Alteração implementada. Agora é possível desclicar um jogador. |


* Tela de informar resultado do desafio

| Comentário | Status |
| -----|--------|
| Tem que arrumar o teclado quando o usuário clicar em qualquer campo da tela | Alteração implementada. Ao clicar em qualquer lugar da tela o teclado "desce", não atrapalhando mais a visualização do usuário. | 

* Tela do Ranking

| Comentário | Status |
| -----|--------|
| O botão de ranking deu a entender que é de montar campeonato | Alteração não será realizada, além de não ser nada grave ao clicar uma ou mais vezes o usuário entenderá que não se trata de um campeonato |
| As cores das 5 bolinhas ficaram confusas ao usuário, principalmente a cor amarela | No futuro, será adicionado um onboarding explicando melhor a cor de cada bolinha. |

## Segunda rodada de testes - feita no início/meio do mês junho

* Teste de usabilidade 1

Tarefas realizadas: Desafiar um usuário, colocar o resultado do desafio como melhor de 3 e se encontrar no ranking.

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/fo06HsV2vSs/0.jpg)](https://youtu.be/fo06HsV2vSs)


* Testes de usabilidade 2 e 3

Para acessar os demais vídeos dos testes de usabilidade clique [aqui](https://www.dropbox.com/sh/fuldoepb69y0vwy/AADA2l9Ic1OgVg05zQf0lEwea?dl=0).

**Principais Feedbacks:**
* Dúvida no que significam as cores das bolinhas do ranking;
* Acharam o ranking e demais telas do aplicativo bonitas;
* Gostaram da célula fixa que indica a posição do usuário no ranking;
* Em um momento não apareceu tenistas para serem desafiados(BUG);
* Alguns acharam complicado colocar resultados da partida(muitas opções);
* Gostaram dos gráficos de perfil, mesmo sem entender completamente o que representavam;
