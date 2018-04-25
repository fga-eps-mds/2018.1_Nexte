package com.nexte.nexte.ObjectModels

import java.util.*

class Story(val id: String,
            val winner: StoryPlayer,
            val loser: StoryPlayer,
            val date: Date,
            val comments: List<Comment>,
            val likes: List<Like>) {

    class StoryPlayer(val id: String,
                      val setResult: Int)

    enum class ServerRequest(val request: Map<String, String>) {
        STORIES(hashMapOf("route" to "stories", "method" to "get"))
    }
}