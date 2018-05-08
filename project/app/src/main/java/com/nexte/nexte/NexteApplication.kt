package com.nexte.nexte

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration



class NexteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("mockerRealm.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}