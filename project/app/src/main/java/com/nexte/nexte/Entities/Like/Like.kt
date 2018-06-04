package com.nexte.nexte.Entities.Like
import android.annotation.SuppressLint
import io.realm.RealmObject
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

open class Like(var id: String? = null,
                var userId: String? = null,
                var date: Date? = null): RealmObject() {

    enum class ServerRequest(val request: Map<String, String>) {
        LIKES(hashMapOf("route" to "likes", "method" to "get")),
        LIKE(hashMapOf("route" to "like", "method" to "post")),
        UNLIKE(hashMapOf("route" to "like", "method" to "delete"))
    }

    companion object {

        @SuppressLint("SimpleDateFormat")
                /**
                 * Method used to tranform a jsonObject(received from api) to a User
                 *
                 * @param jsonUser jsonObject that has user data
                 *
                 * @return user created from json
                 */
        fun createLikeFromJsonObject(jsonLike: JSONObject): Like {
            val id = jsonLike["id"] as String
            val userId = jsonLike["userID"] as String
            val date = SimpleDateFormat("dd-MM-yyyy")
                    .parse(jsonLike["date"] as String)

            return Like(id, userId, date)
        }
    }
}