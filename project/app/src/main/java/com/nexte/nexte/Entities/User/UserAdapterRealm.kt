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
                this.id = user.id
                this.name = user.name
                this.profilePicture = user.profilePicture
                this.nickname = user.nickname
                this.birthDate = user.birthDate
                this.rankingPosition = user.rankingPosition
                this.email = user.email
                this.phone = user.phone
                this.wins = user.wins
                this.loses = user.loses
                this.gender = user.gender.name
                this.category = null
                user.category?.let {
                    this.category = UserCategoryRealm(it.id, it.name)
                }
                this.status = user.status.name
                this.challengeSended = user.challengeSended?.id
                this.challengeReceived = user.challengeSended?.id
            }
        }

        return userRealm
    }


    private fun convertUserRealmToUser(userRealm: UserRealm?): User? {

        var user: User? = null
        userRealm?.let {
            var category: UserCategory? = null
            userRealm.category?.let {
                category = UserCategoryManager().get(it.id)
            }

            // TODO: Update Challenge Sended e Challenge Received after do ChallengeManager
            user = User(userRealm.id,
                        userRealm.name,
                        userRealm.profilePicture,
                        userRealm.nickname,
                        userRealm.birthDate,
                        userRealm.rankingPosition,
                        userRealm.email,
                        userRealm.phone,
                        userRealm.wins,
                        userRealm.loses,
                        User.Gender.valueOf(userRealm.gender),
                        category,
                        User.Status.valueOf(userRealm.status),
                        null,
                        null,
                        emptyList()
            )
        }

        return user
    }

    private fun convertUserRealmListToUserList(userRealmResults: RealmResults<UserRealm>): List<User> {

        val users: MutableList<User> = mutableListOf()

        for (userRealm in userRealmResults) {
            convertUserRealmToUser(userRealm)?.let {
                users.add(it)
            }
        }

        return users
    }

}