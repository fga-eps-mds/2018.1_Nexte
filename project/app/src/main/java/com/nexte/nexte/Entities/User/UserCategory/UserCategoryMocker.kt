package com.nexte.nexte.Entities.User.UserCategory

object UserCategoryMocker {

    fun generateUserCategories(): List<UserCategory> {
        val userCategories: MutableList<UserCategory> = mutableListOf()

        userCategories.add(UserCategory("1", "Primeira Classe"))
        userCategories.add(UserCategory("2", "Segunda Classe"))
        userCategories.add(UserCategory("3", "Terceira Classe"))
        userCategories.add(UserCategory("4", "Quarta Classe"))
        userCategories.add(UserCategory("5", "Quinta Classe"))
        userCategories.add(UserCategory("6", "Kid Classe"))
        userCategories.add(UserCategory("7", "Melhor Idade Classe"))
        userCategories.add(UserCategory("8", "Especial"))

        return  userCategories.toList()
    }
}