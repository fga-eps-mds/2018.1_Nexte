package com.nexte.nexte.Entities.Challenge

import com.nexte.nexte.Entities.Challenge.Helper.CancelledRealm
import com.nexte.nexte.Entities.Challenge.Helper.PlayedRealm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ChallengeRealm(@PrimaryKey var id: String = "",
                          var challengerId: String = "",
                          var challengedId: String = "",
                          var challegeDate: Date = Date(),
                          var status: String = "",
                          var stageCancelledRealm: CancelledRealm? = null,
                          var stagePlayedRealm: PlayedRealm? = null): RealmObject()

