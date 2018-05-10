package com.nexte.nexte.Entities.User.UserCategory


class UserCategoryManager(val userCategoryAdapter: UserCategoryAdapter = UserCategoryAdapterRealm()) {

    fun get(identifier: String): UserCategory? {
        return userCategoryAdapter.get(identifier)
    }

    fun getAll(): List<UserCategory> {
        return userCategoryAdapter.getAll()
    }

    fun update(userCategory: UserCategory): UserCategory? {
        return userCategoryAdapter.updateOrInsert(userCategory)
    }

    fun delete(identifier: String): UserCategory? {
        return userCategoryAdapter.delete(identifier)
    }

    fun createInitialMocker(): List<UserCategory> {
        val userCategoriesInMocker = UserCategoryMocker.generateUserCategories()
        val insertedUserCategories: MutableList<UserCategory> = mutableListOf()

        for (userCategory in userCategoriesInMocker) {

            val insertedUserCategory = userCategoryAdapter.updateOrInsert(userCategory)
            insertedUserCategory?.let {
                insertedUserCategories.add(it)
            }
        }

        return insertedUserCategories.toList()
    }
}