package com.nexte.nexte.Entities.Story
import android.annotation.SuppressLint
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
            val winnerJson = jsonStory["winner"] as JSONObject
            val winnerId = winnerJson["userID"] as String
            val winnerSetResult = (winnerJson["setResult"] as String).toInt()
            val winner = StoryPlayer(userId = winnerId, setResult = winnerSetResult)
            val loserJson = jsonStory["loser"] as JSONObject
            val loserId = loserJson["userID"] as String
            val loserSetResult = (loserJson["setResult"] as String).toInt()
            val loser = StoryPlayer(userId = loserId, setResult = loserSetResult)
            val commentsIds = jsonStory["comments"] as List<String>
            val likesIds = jsonStory["comments"] as List<String>
            val date = SimpleDateFormat("yyyy-mm-dd")
                    .parse(jsonStory["date"] as String)

            return Story(id, winner, loser, date, commentsIds, likesIds)
        }
    }
}
