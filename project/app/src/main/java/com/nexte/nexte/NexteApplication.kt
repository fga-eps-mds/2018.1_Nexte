package com.nexte.nexte

import android.app.Application
import android.content.Context
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import com.nexte.nexte.Entities.User.UserManager
import io.realm.Realm
import io.realm.RealmConfiguration

class NexteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("mockerRealm.realm").build()
        Realm.setDefaultConfiguration(config)

        val sharedPreference = getSharedPreferences("NexteAndroid", Context.MODE_PRIVATE)
        if (sharedPreference.getBoolean("FirstRun", true)) {
            // TODO: Call other mocker methods of other Managers
            // Call order of createInitialMocker is important!!!
            UserCategoryManager().createInitialMocker()
            UserManager().createInitialMocker()
            ChallengeManager().createInitialMocker()
            sharedPreference.edit().putBoolean("FirstRun", false).apply()
        }
    }
}