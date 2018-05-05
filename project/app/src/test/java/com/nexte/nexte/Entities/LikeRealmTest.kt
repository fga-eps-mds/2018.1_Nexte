package com.nexte.nexte.Entities

import io.realm.BuildConfig
import io.realm.Realm
import io.realm.log.RealmLog
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertThat
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.api.mockito.PowerMockito.`when`
import org.hamcrest.CoreMatchers.`is`

//@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class, sdk = [22])
//@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
//@SuppressStaticInitializationFor("io.realm.internal.Util")
//@PrepareForTest(Realm::class, RealmLog::class)

class LikeRealmTest {

//    var mockRealm: Realm? = null

    @Before
    fun setUp() {
        // Mock for Realm tests
//        mockStatic(RealmLog::class.java)
//        mockStatic(Realm::class.java)
//
//        val mockRealm = PowerMockito.mock(Realm::class.java)
//
//        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)
//
//        this.mockRealm = mockRealm
    }

}