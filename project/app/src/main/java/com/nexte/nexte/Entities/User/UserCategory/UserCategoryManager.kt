package com.nexte.nexte.Entities.User.UserCategory


class UserCategoryManager(val userCategoryAdapter: UserCategoryAdapter = UserCategoryAdapterRealm()) {

    fun get(identifier: String): UserCategory? {
        return userCategoryAdapter.get(identifier)
    }
}