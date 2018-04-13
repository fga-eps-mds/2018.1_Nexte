### Histórico de reivsão

| Data | Versão | Descrição | Autor(es)|
| -----|--------|-----------|-----------|
| 08/04/2018| 0.1 | Criação do Documento| Geovanni Oliveira|
| 08/04/2018| 0.2 | Criação instrução de como executar a ferramenta de análise de código estática  | Geovanni Oliveira|
| 08/04/2018| 0.2 | Criação instrução de como executar a cobertura de testes do projeto  | Geovanni Oliveira|
----

#### Análise estática de código
A análise estática de código é feita com a ferramenta **Detekt**, ela analise o código em alguns aspectos e é configurável de acordo com as as decições da equipe.
para ver a análise da ferramenta é necessário no terminal entrar na pasta **project** e executar o comando 

    ./gradlew detektCheck
e em seguida no terminal aparecerá a análise da ferramenta.

#### Cobertura de código

A cobertura de código será analisada com as ferramentas padrões do Android Studio, e apara isso na classe de teste é necessário ao pedir para executar os testes, rodar também a cobertura de código. 

![](https://i.imgur.com/FQEw7Vz.png)