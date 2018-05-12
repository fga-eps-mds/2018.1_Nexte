package com.nexte.nexte.Entities.Challenge.Stage

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class CancelledRealm(@PrimaryKey var id: String = "",
                          var reason: String = "",
                          var userType: String = "",
                          var date: Date? = null): RealmObject()