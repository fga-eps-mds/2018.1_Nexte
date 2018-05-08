package com.nexte.nexte.Entities.User

class UserManager(val userAdapter: UserAdapter = UserAdapterRealm()) {

    fun get(identifier: String): User? {
        return userAdapter.get(identifier)
    }

    fun getAll(): List<User> {
        return userAdapter.getAll()
    }

    fun update(user: User): User? {
        return userAdapter.update(user)
    }

    fun updateMany(users: List<User>): List<User> {
        val newUsers: MutableList<User> = mutableListOf()
        for (user in users) {
            val updatedUser = userAdapter.update(user)
            updatedUser?.let {
                newUsers.add(it)
            }
        }
        return newUsers.toList()
    }

    fun delete(identifier: String): User? {
        return userAdapter.delete(identifier)
    }

    fun createInitialMocker(): List<User> {
        val usersInMocker = UserMocker.generateUsers()
        val insertedUsers: MutableList<User> = mutableListOf()

        for (user in usersInMocker) {

            val insertedUser = userAdapter.update(user)
            insertedUser?.let {
                insertedUsers.add(it)
            }
        }

        return insertedUsers.toList()
    }

}