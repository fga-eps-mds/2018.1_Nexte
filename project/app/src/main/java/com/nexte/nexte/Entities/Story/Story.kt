package com.nexte.nexte.Entities.Story
import java.util.*

open class Story(var id: String? = null,
                 var winner: StoryPlayer? = null,
                 var loser: StoryPlayer? = null,
                 var date: Date? = null,
                 var commentsId: List<String> = listOf(),
                 var likesId: List<String> = listOf()){

    enum class ServerRequest(val request: Map<String, String>) {
        STORIES(hashMapOf("route" to "stories", "method" to "get"))
    }
}
