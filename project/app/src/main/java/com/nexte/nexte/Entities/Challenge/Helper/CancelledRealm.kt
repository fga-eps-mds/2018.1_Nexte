package com.nexte.nexte.Entities.Challenge.Helper

import com.nexte.nexte.Entities.Challenge.Challenge
import io.realm.RealmObject
import java.util.*

open class CancelledRealm(var reason: String = "",
                          var userType: String = "",
                          var date: Date? = null): RealmObject() {

    constructor(canceled: Challenge.Stage.Canceled): this() {
        reason = canceled.reason
        userType = canceled.user.name
        date = canceled.date
    }
}