package com.nexte.nexte.Entities.User

import com.nexte.nexte.Entities.User.UserCategory.UserCategoryRealm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class UserRealm(@PrimaryKey var id: String = "",
                     var name: String = "",
                     var profilePicture: String? = null,
                     var nickname: String = "",
                     var birthDate: Date? = null,
                     var rankingPosition: Int = 0,
                     var email: String = "",
                     var phone: String = "",
                     var wins: Int = 0,
                     var loses: Int = 0,
                     var gender: Int = 0,
                     var category: UserCategoryRealm? = null,
                     var status: Int = 0,
                     var challengeSended: String? = null,
                     var challengeReceived: String? = null): RealmObject()