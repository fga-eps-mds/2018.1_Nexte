package com.nexte.nexte.Entities.Story
import android.annotation.SuppressLint
import com.google.gson.JsonObject
import org.json.JSONObject
import java.text.SimpleDateFormat
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

    companion object {

        @SuppressLint("SimpleDateFormat")
                /**
                 * Method used to tranform a jsonObject(received from api) to a Story
                 *
                 * @param jsonStory jsonObject that has story data
                 *
                 * @return story created from json
                 */
        fun createStoryFromJsonObject(jsonStory: JSONObject): Story {
            val id = jsonStory["id"] as String
            val challenge = (jsonStory["challenge"] as JsonObject)
            val winnerId = challenge["winner"] as String
            val winner = StoryPlayer(userId = winnerId, setResult = 0)
            val loserId = challenge["loser"] as String
            val loser = StoryPlayer(userId = loserId, setResult = 0)
            val commentsId = listOf<String>()
            val likesId = listOf<String>()
            val date = SimpleDateFormat("yyyy-mm-dd")
                    .parse(jsonStory["date"] as String)

            return Story(id, winner, loser, date, commentsId, likesId)
        }
    }
}
