package com.nexte.nexte.Entities.User.UserCategory

interface UserCategoryAdapter {
    fun get(identifier: String): UserCategory?
}