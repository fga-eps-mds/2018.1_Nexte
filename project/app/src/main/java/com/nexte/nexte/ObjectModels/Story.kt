package com.nexte.nexte.ObjectModels

import java.util.*

data class Story(val id: String,
                 val winner: StoryPlayer,
                 val loser: StoryPlayer,
                 val date: Date,
                 val comments: List<Comment>,
                 val likes: List<Like>) {

    data class StoryPlayer(val id: String,
                           val setResult: Int)
}