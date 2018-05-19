package com.nexte.nexte.Entities.User

import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryRealm
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class UserAdapterRealm: UserAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun get(identifier: String): User? {

        val userRealm = realm.where<UserRealm>().equalTo("id", identifier).findFirst()
        return convertUserRealmToUser(userRealm)
    }

    override fun getAll(): List<User> {

        val userRealmResults = realm.where<UserRealm>().findAll()
        return convertUserRealmListToUserList(userRealmResults)
    }

    override fun updateOrInsert(user: User): User? {

        convertUserToUserRealm(user)?.let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return user
        }
        return null
    }

    override fun delete(identifier: String): User? {

        val userRealm = realm.where<UserRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val user = convertUserRealmToUser(userRealm.first())
        userRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return user
    }

    private fun convertUserToUserRealm(user: User?): UserRealm? {

        var userRealm: UserRealm? = null

        user?.let {
            userRealm = UserRealm().apply {
                this.id = it.id
                this.name = it.name
                this.profilePicture = it.profilePicture
                this.nickname = it.nickname
                this.birthDate = it.birthDate
                this.rankingPosition = it.rankingPosition
                this.email = it.email
                this.phone = it.phone
                this.wins = it.wins
                this.loses = it.loses
                this.gender = it.gender.name
                this.category = null
                it.category?.let {
                    this.category = UserCategoryRealm(it.id, it.name)
                }
                this.status = it.status.name
                this.challengeSended = it.challengeSended?.id
                this.challengeReceived = it.challengeSended?.id
            }
        }

        return userRealm
    }


    fun convertUserRealmToUser(userRealm: UserRealm?): User? {

        var user: User? = null
        userRealm?.let {
            var category: UserCategory? = null
            it.category?.let {
                category = UserCategoryManager().get(it.id)
            }

            // TODO: Update Challenge Sended e Challenge Received after do ChallengeManager
            user = User(it.id,
                    it.name,
                    it.profilePicture,
                    it.nickname,
                    it.birthDate,
                    it.rankingPosition,
                    it.email,
                    it.phone,
                    it.wins,
                    it.loses,
                    User.Gender.valueOf(it.gender),
                    category,
                    User.Status.valueOf(it.status),
                    null,
                    null,
                    emptyList()
            )
        }

        return user
    }

    fun convertUserRealmListToUserList(userRealmResults: RealmResults<UserRealm>): List<User> {

        val users: MutableList<User> = mutableListOf()

        for (userRealm in userRealmResults) {
            convertUserRealmToUser(userRealm)?.let {
                users.add(it)
            }
        }

        return users
    }
}
