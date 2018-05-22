package com.nexte.nexte.Entities.User

interface UserAdapter {
    fun getAll(): List<User>
    fun get(identifier: String): User?
    fun updateOrInsert(user: User): User?
    fun delete(identifier: String): User?
}