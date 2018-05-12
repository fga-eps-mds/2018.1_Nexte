package com.nexte.nexte.Entities.Challenge

import com.nexte.nexte.Entities.Challenge.Stage.CancelledRealm
import com.nexte.nexte.Entities.Challenge.Stage.PlayedRealm
import com.nexte.nexte.Entities.Challenge.Stage.ScheduledRealm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ChallengeRealm(@PrimaryKey var id: String = "",
                          var challengerId: String = "",
                          var challengedId: String = "",
                          var challegeDate: Date = Date(),
                          var status: String = "",
                          var stageScheduledRealm: ScheduledRealm? = null,
                          var stageCancelledRealm: CancelledRealm? = null,
                          var stagePlayedRealm: PlayedRealm? = null): RealmObject()