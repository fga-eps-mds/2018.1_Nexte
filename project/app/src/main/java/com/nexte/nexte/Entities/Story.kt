package com.nexte.nexte.Entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Story(@PrimaryKey var id: String? = null,
                 var winner: StoryPlayer? = null,
                 var loser: StoryPlayer? = null,
                 var date: Date? = null,
                 var comments: RealmList<Comment> = RealmList(),
                 var likes: RealmList<Like> = RealmList()): RealmObject() {

    enum class ServerRequest(val request: Map<String, String>) {
        STORIES(hashMapOf("route" to "stories", "method" to "get"))
    }
}

open class StoryPlayer(var id: String = "",
                       var setResult: Int = 1): RealmObject()
