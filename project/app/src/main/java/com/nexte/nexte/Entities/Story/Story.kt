package com.nexte.nexte.Entities.Story

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Like.Like
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Story(@PrimaryKey var id: String? = null,
                 var winner: StoryPlayer? = null,
                 var loser: StoryPlayer? = null,
                 var date: Date? = null,
                 var commentsId: List<String> = listOf(),
                 var likesId: List<String> = listOf()){

//    enum class ServerRequest(val request: Map<String, String>) {
//        STORIES(hashMapOf("route" to "stories", "method" to "get"))
//    }
}
