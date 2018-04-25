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
}