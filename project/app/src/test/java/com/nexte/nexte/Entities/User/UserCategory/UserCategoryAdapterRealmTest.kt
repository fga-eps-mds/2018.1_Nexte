package com.nexte.nexte.Entities.User.UserCategory

import com.nexte.nexte.HelpForRealm
import io.realm.Realm
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
    fun testConvertUserCategoryToUserCategoryRealm() {
        val userCategory = UserCategory("1", "Primeira Classe")

        val userCategoryRealm = this.userAdapterRealm?.convertUserCategoryToUserCategoryRealm(userCategory)

        assertEquals(userCategory.id, userCategoryRealm!!.id)
        assertEquals(userCategory.name, userCategoryRealm.name)
    }

    @Test
    fun testConvertUserCategoryToUserCategoryRealmFail() {
        val userCategoryRealm = this.userAdapterRealm?.convertUserCategoryToUserCategoryRealm(null)

        assertNull(userCategoryRealm)
    }

    @Test
    fun testConvertUserCategoryRealmToUserCategory() {
        val userCategoryRealm = UserCategoryRealm("1", "Primeira Classe")

        val userCategory = this.userAdapterRealm?.convertUserCategoryRealmToUserCategory(userCategoryRealm)

        assertNotNull(userCategory)
        if (userCategory != null) {
            assertEquals(userCategory.name, userCategoryRealm.name)
            assertEquals(userCategory.id, userCategoryRealm.id)
        }
    }

    @Test
    fun testConvertUserCategoryRealmToUserCategoryFail() {
        val userCategory = this.userAdapterRealm?.convertUserCategoryRealmToUserCategory(null)

        assertNull(userCategory)
    }

    @Test
    fun testConvertListUserCategoryRealmToUserCategory() {
        val userCategoryRealm = UserCategoryRealm("1", "Primeira Classe")

        val userCategoryList= this.userAdapterRealm?.convertUserCategoryRealmListToUserCategoryList(listOf(userCategoryRealm, userCategoryRealm))


        assertNotNull(userCategoryList)
        if (userCategoryList != null) {
            assert(userCategoryList.size == 2)
            assertEquals(userCategoryList[0].id, userCategoryRealm.id)
            assertEquals(userCategoryList[0].name, userCategoryRealm.name)
        }
    }

    @Test
    fun testGet() {
        val userCategory = this.userAdapterRealm?.get("1")

        assertEquals(userCategory!!.id, "1")
        assertEquals(userCategory.name, "Primeira Classe")
    }

    @Test
    fun testGetAll() {
        val userCategory = this.userAdapterRealm?.getAll()

        assertNotNull(userCategory)
    }

    @Test
    fun testInsertOrUpdate() {
        val userCategory = UserCategory("2", "Segunda Classe")
        val userCategoryAfterInsert = this.userAdapterRealm?.updateOrInsert(userCategory = userCategory)

        assertNotNull(userCategoryAfterInsert)
    }

    @Test
    fun testDelete() {
        val userCategory = this.userAdapterRealm?.delete("1")

        assertNull(userCategory)
    }

    @Test
    fun testGetSetRealm() {
        this.userAdapterRealm?.realm = Realm.getDefaultInstance()
        assertNotNull(this.userAdapterRealm?.realm)
    }

    @After
    fun tearDown() {
        super.tearDownRealm()
    }
}