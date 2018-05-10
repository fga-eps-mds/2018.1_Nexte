package com.nexte.nexte.Entities.User.UserCategory

interface UserCategoryAdapter {
    fun get(identifier: String): UserCategory?
    fun getAll(): List<UserCategory>
    fun updateOrInsert(userCategory: UserCategory): UserCategory?
    fun delete(identifier: String): UserCategory?
}