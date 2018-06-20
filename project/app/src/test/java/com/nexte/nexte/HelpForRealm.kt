package com.nexte.nexte

import android.content.Context
import com.nexte.nexte.Entities.Story.StoryRealm
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryRealm
import com.nexte.nexte.Entities.User.UserRealm
import com.nexte.nexte.FeedScene.FeedFragment
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.internal.RealmCore
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*


@RunWith(PowerMockRunner::class)
@Config(constants = io.realm.BuildConfig::class, sdk = [22])
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(UserRealm::class, Realm::class, RealmQuery::class, RealmResults::class, RealmCore::class)

open class HelpForRealm {

    var mockRealm: Realm? = null

    @Before
    fun setUpRealm() {
        // Set up some naive stubs
        PowerMockito.mockStatic(RealmCore::class.java)
        PowerMockito.mockStatic(Realm::class.java)
        PowerMockito.mockStatic(RealmResults::class.java)

        Realm.init(RuntimeEnvironment.application)

        val mockRealm: Realm = mock()
        RealmCore.loadLibrary(Matchers.any(Context::class.java))

        PowerMockito.`when`(Realm.getDefaultInstance()).thenReturn(mockRealm)

        this.mockRealm = mockRealm
    }

    fun setUpWithUser() {
        // Set up some naive stubs
        val id = "9"
        val name = "Rafael Pardal"
        val profilePicture = null
        val nickname = "Pardal"
        val birthdate = Date(1990, 10, 12)
        val rankingPosition = 10
        val email = "pardal@nexte.com"
        val phone = "130"
        val wins = 30
        val loses = 23
        val gender = User.Gender.MALE
        val category = null
        val status = User.Status.AVAILABLE
        val challengeSended = null
        val challengeReceived = null

        val u1 = UserRealm(id, name, profilePicture, nickname, birthdate, rankingPosition, email,
                phone, wins, loses, gender.name, category, status.name, challengeSended, challengeReceived)

        val userList = Arrays.asList(u1)
        val userResults: RealmResults<UserRealm> = mock()

        this.setUpRealm()
        this.setUpWithUserCategory()

        val userQuery: RealmQuery<UserRealm> = mock()

        PowerMockito.`when`(mockRealm!!.where(UserRealm::class.java)).thenReturn(userQuery)
        PowerMockito.`when`(userQuery.equalTo(Matchers.anyString(), Matchers.anyString())).thenReturn(userQuery)
        PowerMockito.`when`(userQuery.findFirst()).thenReturn(userList[0])
        PowerMockito.`when`(userQuery.findAll()).thenReturn(userResults)
    }

    fun setUpWithUserCategory() {

        val id = "1"
        val name = "Primeira Classe"
        val uc1 = UserCategoryRealm(id, name)

        val userCategoryList = Arrays.asList(uc1)
        val userCategoryResults: RealmResults<UserCategoryRealm> = mock()

        val userCategoryQuery: RealmQuery<UserCategoryRealm> = mock()

        PowerMockito.`when`(mockRealm!!.where(UserCategoryRealm::class.java)).thenReturn(userCategoryQuery)
        PowerMockito.`when`(userCategoryQuery.equalTo(Matchers.anyString(), Matchers.anyString())).thenReturn(userCategoryQuery)
        PowerMockito.`when`(userCategoryQuery.findFirst()).thenReturn(userCategoryList[0])
        PowerMockito.`when`(userCategoryQuery.findAll()).thenReturn(userCategoryResults)
    }

    @Test
    fun shouldBeAbleToGetDefaultInstance() {
        Assert.assertEquals(Realm.getDefaultInstance(), mockRealm)
    }

    @Test
    fun shouldBeAbleToMockRealmMethods() {
        PowerMockito.`when`(mockRealm!!.isAutoRefresh()).thenReturn(true)
        Assert.assertEquals(mockRealm!!.isAutoRefresh(), true)

        PowerMockito.`when`(mockRealm!!.isAutoRefresh()).thenReturn(false)
        Assert.assertEquals(mockRealm!!.isAutoRefresh(), false)
    }

    @After
    fun tearDownRealm() {

    }

    inline fun <reified T: Any> mock() = PowerMockito.mock(T::class.java)

}