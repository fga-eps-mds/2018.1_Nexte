package com.nexte.nexte.Entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserCategory(@PrimaryKey var id: String = "",
                    var name: String = ""): RealmObject()