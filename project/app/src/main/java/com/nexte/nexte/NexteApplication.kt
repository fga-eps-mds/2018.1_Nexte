package com.nexte.nexte

import android.app.Application
import android.content.Context
import com.github.kittinunf.fuel.core.FuelManager
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import com.nexte.nexte.Entities.User.UserManager
import io.realm.Realm

class NexteApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = "https://nexte-dev.herokuapp.com"
        Realm.init(this)

        this.setupLoggedUser()

        val sharedPreference = getSharedPreferences("NexteAndroid", Context.MODE_PRIVATE)

        if (sharedPreference.getBoolean("FirstRun", true)) {
            UserCategoryManager().createInitialMocker()
            UserManager().createInitialMocker()
            StoryManager().createInitialMocker()
            CommentManager().createInitialMocker()
            LikeManager().createInitialMocker()
            sharedPreference.edit().putBoolean("FirstRun", false).apply()
        }

        if (sharedPreference.getBoolean("FirstRun_v2", true)) {
            ChallengeManager().createInitialMocker()
            sharedPreference.edit().putBoolean("FirstRun_v2", false).apply()
        }
    }

    /*
     * Save user Id to change app status for real data
     */
    private fun  setupLoggedUser() {

        val sharedPreference = getSharedPreferences("NexteAndroid", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("authUser", null)

        if(token != null) {
            UserSingleton.setLoggedUser(token, UserType.REAL)
        } else {
            UserSingleton.setLoggedUser("9", UserType.MOCKED)
        }
    }

    fun updateUserLoggedStatus(user: User) {
        UserSingleton.setLoggedUser(user.id, UserType.REAL)
        UserManager().update(user)
    }
}