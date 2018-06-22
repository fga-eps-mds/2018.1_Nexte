package com.nexte.nexte.Entities.User.UserCategory

import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UserCategoryAdapterRealmTest: HelpForRealm() {

    var userAdapterRealm: UserCategoryAdapterRealm? = null

    @Before
    fun setUp() {
        setUpWithUserCategory()
        this.userAdapterRealm = UserCategoryAdapterRealm()
    }

    @Test
    fun testConvertUserCategoryToUserCategoryRealm(){
        val userCategory = UserCategory("1", "1")

        val userCategoryRealm = this.userAdapterRealm?.convertUserCategoryToUserCategoryRealm(userCategory)

        assertEquals(userCategory.id, userCategoryRealm!!.id)
        assertEquals(userCategory.name, userCategoryRealm.name)
    }

    @Test
    fun testConvertUserCategoryRealmToUserCategory(){
        val userCategoryRealm = UserCategoryRealm("1", "1")

        val userCategory = this.userAdapterRealm?.convertUserCategoryRealmToUserCategory(userCategoryRealm)

        assertEquals(userCategory!!.id, userCategoryRealm.id)
        assertEquals(userCategory.name, userCategoryRealm.name)
    }

    @Test
    fun testConvertListUserCategoryRealmToUserCategory(){
        val userCategoryRealm = UserCategoryRealm("1", "1")

        val userCategory = this.userAdapterRealm?.convertUserCategoryRealmListToUserCategoryList(listOf(userCategoryRealm, userCategoryRealm))

        assertEquals(userCategory!!.size, 2)
        assertEquals(userCategory[0].id, userCategoryRealm.id)
        assertEquals(userCategory[0].name, userCategoryRealm.name)
    }

    @Test
    fun testGet(){
        this.userAdapterRealm?.realm
        val userCategory = this.userAdapterRealm?.get("1")

        assertEquals(userCategory!!.id, "1")
        assertEquals(userCategory.name, "Primeira Classe")
    }

    @After
    fun tearDown() {
    }
}