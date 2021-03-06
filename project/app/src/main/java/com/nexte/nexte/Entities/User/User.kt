package com.nexte.nexte.Entities.User

import android.annotation.SuppressLint
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryAdapter
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// ChallengeSended and ChallengeReceived have Challenge ID if are done or nil if don't
class User(val id: String,
           val name: String,
           val profilePicture: String?,
           val nickname: String,
           val birthDate: Date?,
           val rankingPosition: Int,
           val email: String,
           val phone: String,
           val wins: Int,
           val loses: Int,
           val gender: Gender,
           val category: UserCategory?,
           val status: Status,
           val challengeSended: Challenge?,
           val challengeReceived: Challenge?,
           var latestGames: List<Challenge>?) {

    companion object {

        @SuppressLint("SimpleDateFormat")
                /**
         * Method used to tranform a jsonObject(received from api) to a User
         *
         * @param jsonUser jsonObject that has user data
         *
         * @return user created from json
         */
        fun createUserFromJsonObject(jsonUser: JSONObject, userCategoryManagerArgument: UserCategoryAdapter? = null): User {
            val userCategoryManager = getUserCategoryManager(userCategoryManagerArgument)
            val id = jsonUser["id"] as String
            val name = jsonUser["name"] as String
            val profilePicture = jsonUser.optString("profileImageURL")
            val nickname = jsonUser.optString("nickname")
            var rankingPosition = jsonUser.optInt("rankPosition")
            val email = jsonUser["email"] as String
            val phone = jsonUser["phone"] as String
            val wins = jsonUser["wins"] as Int
            val loses = jsonUser["loses"] as Int
            val birthDateFromJSON = jsonUser.optString("birthDate")
            val birthDate = try {
                SimpleDateFormat("dd-MM-yyyy").parse(birthDateFromJSON)
            } catch (e: ParseException) {
                Date()
            }

            val genderString = jsonUser.optString("gender")
            var gender: Gender = getGenderFromGenderString(genderString)
            val categoryInt = jsonUser.optInt("category")
            val category = userCategoryManager?.get(categoryInt.toString())
            val statusInt = jsonUser["status"] as Int
            val status: Status?
            status = getStatusFromStatusInt(statusInt)
            val challengeSended = null
            val challengeReceived = null
            val latestGames = null

            return User(id, name, profilePicture, nickname, birthDate,
                    rankingPosition, email, phone, wins, loses, gender,
                    category, status, challengeSended, challengeReceived, latestGames)
        }

        fun getUserCategoryManager(userCategoryManagerArgument: UserCategoryAdapter?): UserCategoryManager?{
            return if(userCategoryManagerArgument == null){
                UserCategoryManager()
            } else{
                UserCategoryManager(userCategoryManagerArgument)
            }
        }

        fun getGenderFromGenderString(genderString: String): Gender{
            return if (genderString == "M") {
                Gender.MALE
            } else {
                Gender.FEMALE
            }
        }

        fun getStatusFromStatusInt(statusInt: Int): Status{
            return when (statusInt) {
                0 ->
                    Status.AVAILABLE
                1 ->
                    Status.INJURED
                2 ->
                    Status.UNAVAILABLE
                else -> {
                    Status.AVAILABLE
                }
            }
        }
    }

    val matches: Int
        get() = this.wins + this.loses


    // Enums
    enum class Gender(val identifier: Int, val value: String) {
        MALE(0, "Male"),
        FEMALE(1,"Female")
    }

    enum class Status(val identifier: Int, val value: String) {
        AVAILABLE(0, "Available"),
        INJURED(1, "Injured"),
        UNAVAILABLE(2, "Unavailable")
    }

    enum class ServerRequest(val request: Map<String, String>) {
        USERS(hashMapOf("route" to "users", "method" to "get")),
        USER(hashMapOf("route" to "user", "method" to "get")),
        UPDATE_USER(hashMapOf("route" to "user", "method" to "update")),
        LOGIN(hashMapOf("route" to "login", "method" to "post"))
    }
}

