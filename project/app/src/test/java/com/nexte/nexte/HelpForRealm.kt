package com.nexte.nexte

import android.content.Context
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeRealm
import com.nexte.nexte.Entities.Comment.CommentRealm
import com.nexte.nexte.Entities.Like.LikeRealm
import com.nexte.nexte.Entities.Story.Story
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
@PrepareForTest(UserRealm::class, Realm::class, RealmQuery::class, RealmResults::class, RealmCore::class, StoryRealm::class, ChallengeRealm::class, LikeRealm::class, CommentRealm::class)

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

    fun setUpWithStory() {
        // Set up some naive stubs
        val id = "9"
        val winnerId = "1"
        val winnerSetResult = 3
        val loserId = "1"
        val loserSetResult = 1
        val date = Date()

        val u1 = StoryRealm(id, winnerId, winnerSetResult, loserId, loserSetResult, date)

        val storyList = Arrays.asList(u1)
        val storyResults: RealmResults<StoryRealm> = mock()

        this.setUpRealm()

        val storyQuery: RealmQuery<StoryRealm> = mock()

        PowerMockito.`when`(mockRealm!!.where(StoryRealm::class.java)).thenReturn(storyQuery)
        PowerMockito.`when`(storyQuery.equalTo(Matchers.anyString(), Matchers.anyString())).thenReturn(storyQuery)
        PowerMockito.`when`(storyQuery.findFirst()).thenReturn(storyList[0])
        PowerMockito.`when`(storyQuery.findAll()).thenReturn(storyResults)
    }

    fun setUpWithChallenge() {
        // Set up some naive stubs
        val id = "9"
        val challengerId = "1"
        val challengedId = "1"
        val date = Date()
        val status = "WAITING"

        val u1 = ChallengeRealm(id, challengerId, challengedId, date, status)

        val challengeList = Arrays.asList(u1)
        val challengeResults: RealmResults<ChallengeRealm> = mock()

        this.setUpRealm()

        val challengeQuery: RealmQuery<ChallengeRealm> = mock()

        PowerMockito.`when`(mockRealm!!.where(ChallengeRealm::class.java)).thenReturn(challengeQuery)
        PowerMockito.`when`(challengeQuery.equalTo(Matchers.anyString(), Matchers.anyString())).thenReturn(challengeQuery)
        PowerMockito.`when`(challengeQuery.findFirst()).thenReturn(challengeList[0])
        PowerMockito.`when`(challengeQuery.findAll()).thenReturn(challengeResults)
    }

    fun setUpWithLike() {
        // Set up some naive stubs
        val id = "9"
        val userId = "1"
        val date = Date()

        val u1 = LikeRealm(id, userId, date)

        val likeList = Arrays.asList(u1)
        val likeResults: RealmResults<LikeRealm> = mock()

        this.setUpRealm()

        val likeQuery: RealmQuery<LikeRealm> = mock()

        PowerMockito.`when`(mockRealm!!.where(LikeRealm::class.java)).thenReturn(likeQuery)
        PowerMockito.`when`(likeQuery.equalTo(Matchers.anyString(), Matchers.anyString())).thenReturn(likeQuery)
        PowerMockito.`when`(likeQuery.findFirst()).thenReturn(likeList[0])
        PowerMockito.`when`(likeQuery.findAll()).thenReturn(likeResults)
    }

    fun setUpWithComment() {
        // Set up some naive stubs
        val id = "9"
        val userId = "1"
        val comment = "Jogo top demais"
        val date = Date()

        val u1 = CommentRealm(id, userId, comment, date)

        val commentList = Arrays.asList(u1)
        val commentResults: RealmResults<CommentRealm> = mock()

        this.setUpRealm()

        val commentQuery: RealmQuery<CommentRealm> = mock()

        PowerMockito.`when`(mockRealm!!.where(CommentRealm::class.java)).thenReturn(commentQuery)
        PowerMockito.`when`(commentQuery.equalTo(Matchers.anyString(), Matchers.anyString())).thenReturn(commentQuery)
        PowerMockito.`when`(commentQuery.findFirst()).thenReturn(commentList[0])
        PowerMockito.`when`(commentQuery.findAll()).thenReturn(commentResults)
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