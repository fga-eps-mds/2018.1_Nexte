package com.nexte.nexte.Entities.User.UserCategory

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class UserCategoryAdapterRealm: UserCategoryAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun get(identifier: String): UserCategory? {
        val userCategoryRealm = realm.where<UserCategoryRealm>().equalTo("id", identifier).findFirst()
        return convertUserCategoryRealmToUserCategory(userCategoryRealm)
    }

    override fun getAll(): List<UserCategory> {
        val userCategoryRealmResults = realm.where<UserCategoryRealm>().findAll()
        return convertUserCategoryRealmListToUserCategoryList(userCategoryRealmResults)
    }

    override fun updateOrInsert(userCategory: UserCategory): UserCategory? {

        convertUserCategoryToUserCategoryRealm(userCategory)?.let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return userCategory
        }
        return null
    }

    override fun delete(identifier: String): UserCategory? {

        val userCategoryRealm = realm.where<UserCategoryRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val userCategory = convertUserCategoryRealmToUserCategory(userCategoryRealm.first())
        userCategoryRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return userCategory
    }

    fun convertUserCategoryToUserCategoryRealm(userCategory: UserCategory?): UserCategoryRealm? {

        var userCategoryRealm: UserCategoryRealm? = null
        userCategory?.let {
            userCategoryRealm = UserCategoryRealm().apply {
                this.id = it.id
                this.name = it.name
            }
        }
        return userCategoryRealm
    }


    fun convertUserCategoryRealmToUserCategory(userCategoryRealm: UserCategoryRealm?): UserCategory? {

        var userCategory: UserCategory? = null
        userCategoryRealm?.let {
            userCategory = UserCategory(it.id, it.name)
        }
        return userCategory
    }

    fun convertUserCategoryRealmListToUserCategoryList(userCategoryRealmResults: List<UserCategoryRealm>): List<UserCategory> {

        val userCategories: MutableList<UserCategory> = mutableListOf()
        for (userCategoryRealm in userCategoryRealmResults) {
            convertUserCategoryRealmToUserCategory(userCategoryRealm)?.let {
                userCategories.add(it)
            }
        }
        return userCategories
    }
}