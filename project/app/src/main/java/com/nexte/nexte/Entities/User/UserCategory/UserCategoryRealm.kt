package com.nexte.nexte.Entities.User.UserCategory

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserCategoryRealm(@PrimaryKey var id: String = "",
                             var name: String = ""): RealmObject()