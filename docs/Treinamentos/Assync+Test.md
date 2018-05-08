
## *Couroutines* e Tarefas Assíncronas

No desenvolvimento de software é bastante usual a utilização de Courotines, isto é a capacidade de suspender e continuar sua própria execução. Esta prática se torna bastante comum quando utilizada em paralelo com tarefas assíncronas.

Pseudo-código para *couroutine*:

```
    start courotine 
    var dataFromServer = fetchDataFromServer().await()
    var data = parserData(dataFromServer).await()
    displayInList(data)
```

Entretanto, alguns frameworks disponibilizam métodos assync

## Tarefas Assíncronas em Kotlin

Em Android com Kotlin, podemos fazer uso de tarefas assynchronas da seguinte forma:

```
suspend fun getListOfThings() {
    asyncTask { storageHelper.getListFromDb() }
        .let { list -> 
            view.useTheResult(list)
    }
}
```

```
suspend fun <T> asyncTask(function: () -> T): T {
    return run(CommonPool) { function() }
}
```

Vale ressaltar, que muitos frameworks de terceiros disponibilizam métodos assíncronos que não necessita do uso de funções auxiliares como *asyncTask*

## **Teste Unitários**


Para realizar testes unitários, com o uso do Mockito podemos realizar os testes da seguinte forma:

```
// TEST

@Test
fun getPackageFiltersForWidget() {
    val list: ArrayList<PackageFilter> = ArrayList()
    `when`(storageHelper.getListFromDb()).thenReturn(list)
    presenter.getListOfThings()
    Mockito.verify(view).useTheResult(ArgumentMatchers.eq(list))
}

// PRESENTER

fun getListOfThings() {
    launch(Android) {
        asyncTask { storageHelper.getListFromDb() }.await()
        .let { list -> 
            view.useTheResult(list)
        }
    }
}

fun <T> asyncTask(function: () -> T): Deferred<T> {
    return async(CommonPool) { function() }
}

// ACTIVITY

fun getList() {
    presenter.getListOfThings()
}
```

## Observações

* Para utilizar tarefas assíncronas em kotlin é necessário importar o módulo de cotoutines:

```
compile "org.jetbrains.kotlinx:kotlinx-coroutines-core:0.11-rc"
```

### **Referências**

*  [Android Coroutines](https://medium.com/@macastiblancot/android-coroutines-getting-rid-of-runonuithread-and-callbacks-cleaner-thread-handling-and-more-234c0a9bd8eb)

* [Android Couroutine - Unit Test](https://medium.com/@tonyowen/android-kotlin-coroutines-unit-test-16e984ba35b4)


