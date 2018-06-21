package com.nexte.nexte

import android.app.Application
import android.content.Context
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import com.nexte.nexte.Entities.User.UserManager
import io.realm.Realm
import io.realm.RealmConfiguration

class NexteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val sharedPreference = getSharedPreferences("NexteAndroid", Context.MODE_PRIVATE)
        val config = RealmConfiguration.Builder().name("mockerRealm.realm").build()
        Realm.setDefaultConfiguration(config)

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

        this.setupLoggedUser()
    }

    /*
     * Save user Id to change app status for real data
     */
    private fun  setupLoggedUser() {

        val sharedPreference = getSharedPreferences("NexteAndroid", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("userId", null)

        if(token != null) {
            UserSingleton.setLoggedUser(token, UserType.REAL)
            val config =  RealmConfiguration.Builder().name("realRealm.realm").build()
            Realm.setDefaultConfiguration(config)
        }
    }
}