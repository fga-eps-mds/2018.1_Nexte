package com.nexte.nexte.Entities.Challenge

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ChallengeRealm(@PrimaryKey var id: String = "",
                          var challengerId: String = "",
                          var challengedId: String = "",
                          var challegeDate: Date = Date(),
                          var status: String = "",
                          var stage: String = ""): RealmObject()