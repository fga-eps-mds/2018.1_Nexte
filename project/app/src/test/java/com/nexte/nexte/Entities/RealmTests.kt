package com.nexte.nexte.Entities

import io.realm.*
import io.realm.internal.RealmCore
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertThat
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.rule.PowerMockRule
import org.hamcrest.CoreMatchers.`is`
import org.powermock.api.mockito.PowerMockito.*
import org.robolectric.RuntimeEnvironment

//@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class, sdk = [22])
//@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
//@SuppressStaticInitializationFor("io.realm.internal.Util")
//@PrepareForTest(Realm::class, RealmConfiguration::class, RealmQuery::class, RealmResults::class,
//        RealmList::class, RealmCore::class, RealmObject::class)


class RealmTests {

//    @get:Rule
//    var rule = PowerMockRule()
//    var mockRealm: Realm? = null

    @Before
    fun setUp() {
        // Mock for Realm tests
//        mockStatic(Realm::class.java)
//        Realm.init(RuntimeEnvironment.application)
//
//        val mockRealm = PowerMockito.mock(Realm::class.java)
//
//        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)
//
//        this.mockRealm = mockRealm
    }

//    @Test
//    fun shouldBeAbleToGetDefaultInstance() {
//        assertThat(Realm.getDefaultInstance(), `is`(mockRealm))
//    }

//    @Test
//    fun shouldBeAbleToMockRealmMethods() {
//        `when`(mockRealm?.isAutoRefresh()).thenReturn(true)
//        assertThat(mockRealm?.isAutoRefresh(), `is`(true))
//
//        `when`(mockRealm?.isAutoRefresh()).thenReturn(false)
//        assertThat(mockRealm?.isAutoRefresh(), `is`(false))
//    }

}