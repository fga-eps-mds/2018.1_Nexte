package com.nexte.nexte

import android.app.Application
import io.realm.Realm

class NexteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}