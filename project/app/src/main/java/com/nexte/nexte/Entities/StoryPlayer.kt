package com.nexte.nexte.Entities

import io.realm.RealmObject

open class StoryPlayer(var userId: String = "",
                       var setResult: Int = 1): RealmObject()
