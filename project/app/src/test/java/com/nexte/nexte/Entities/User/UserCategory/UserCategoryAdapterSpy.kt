package com.nexte.nexte.Entities.User.UserCategory

class UserCategoryAdapterSpy: UserCategoryAdapter {

    override fun get(identifier: String): UserCategory? {
        if (identifier == "1") {
            return mockUserCategory()
        } else {
            return null
        }
    }

    override fun getAll(): List<UserCategory> {
        val userCategoryList: MutableList<UserCategory> = mutableListOf(mockUserCategory(), mockUserCategory(), mockUserCategory())
        return userCategoryList.toList()
    }

    override fun updateOrInsert(userCategory: UserCategory): UserCategory? {

        if (userCategory.id == "1") {
            return mockUserCategory()
        } else {
            return null
        }
    }

    override fun delete(identifier: String): UserCategory? {
        if (identifier == "1") {
            return mockUserCategory()
        } else {
            return null
        }
    }

    private fun mockUserCategory(): UserCategory {
        val id = "1"
        val name = "User test"

        return UserCategory(id, name)
    }

}