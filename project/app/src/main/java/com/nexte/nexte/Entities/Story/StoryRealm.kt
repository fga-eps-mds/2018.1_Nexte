package com.nexte.nexte.Entities.Story

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Like.Like
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class StoryRealm(@PrimaryKey var id: String? = null,
                      var winnerId: String? = null,
                      var winnerSetResult: Int? = null,
                      var loserId: String? = null,
                      var loserSetResult: Int? = null,
                      var date: Date? = null,
                      var commentsId: RealmList<String> = RealmList(),
                      var likesId: RealmList<String> = RealmList()): RealmObject() {

    enum class ServerRequest(val request: Map<String, String>) {
        STORIES(hashMapOf("route" to "stories", "method" to "get"))
    }
}