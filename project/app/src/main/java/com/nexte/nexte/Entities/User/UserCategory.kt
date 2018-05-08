package com.nexte.nexte.Entities.User

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserCategory(@PrimaryKey var id: String = "",
                    var name: String = ""): RealmObject()