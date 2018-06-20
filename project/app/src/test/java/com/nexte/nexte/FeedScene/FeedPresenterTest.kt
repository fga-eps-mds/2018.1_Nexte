package com.nexte.nexte.FeedScene

import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryPlayer
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class FeedPresenterTest: HelpForRealm() {

    private var mock: MockFeedDisplayLogic? = null
    private var presenter: FeedPresenter? = null


    @Before
    fun setUp() {
        super.setUpWithUser()
        this.mock = MockFeedDisplayLogic()
        this.presenter = FeedPresenter(viewController = mock)
        this.presenter?.userManager = UserManager(UserAdapterSpy())
        this.presenter?.viewController = mock

    }

    @Test
    fun testFormatFeed() {
        // Prepare
        val id = "1234567890"
        val winner = StoryPlayer("1", 0)
        val loser = StoryPlayer("1", 1)
        val date = Date()
        val likes = listOf("1", "2", "3")
        val comments = listOf("1", "2", "3")

        // Call
        val story = Story(id, winner, loser, date, comments, likes)
        val response = FeedModel.GetFeedActivities.Response(listOf(story))
        response.feedActivities = listOf(story)

        //call
        this.presenter?.formatFeed(response = response)

        //assert
        assertNotNull(this.mock?.formattedGetFeedActivities)
        assertEquals(id, this.mock?.formattedGetFeedActivities?.identifier)
        assertEquals(likes.size.toString(), this.mock?.formattedGetFeedActivities?.numberOfLikes)
    }

    @Test
    fun testFormatFeedEmpty(){
        // Prepare
        val winner = StoryPlayer("", 0)
        val loser = StoryPlayer("", 1)
        val story = Story("", winner, loser, Date(), listOf("1", "2", "3"), listOf("1", "2", "3"))

        // Call
        val response = FeedModel.GetFeedActivities.Response(listOf(story))
        response.feedActivities= listOf(story)

        //call
        this.presenter?.formatFeed(response = response)

        //assert
        assertNotNull(this.mock?.formattedGetFeedActivities)
        assertEquals(story.id, this.mock?.formattedGetFeedActivities?.identifier)
        assertEquals(winner.userId, this.mock?.formattedGetFeedActivities?.challengedName)
        assertEquals(winner.userId, this.mock?.formattedGetFeedActivities?.challengerName)



    }

    @Test
    fun successGetViewController(){
        //assert and call
        val viewController = presenter?.viewController

        //assert
        assertEquals(viewController, presenter?.viewController)
    }

    @Test
    fun testUpdateViewActivity() {
        //prepare
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val identifier = "1"
        val likes = mutableListOf<FeedModel.FeedPlayer>()
        val date = Date()
        val activity1 = FeedModel.FeedActivity(challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date), feedDate = date, identifier = identifier, likes = likes)
        val response = FeedModel.LikeAndUnlike.Response(likedActivity = activity1)

        //call
        this.presenter?.updateViewActivity(response = response)

        //assert
        assertNotNull(this.mock?.formattedGetFeedActivities)
        assertEquals(challenger1.name, this.mock?.formattedGetFeedActivities?.challengerName)
        assertEquals(challenger1.set.toString(), this.mock?.formattedGetFeedActivities?.challengerSets)
        assertEquals(challenger1.photo, this.mock?.formattedGetFeedActivities?.challengerPhoto)
        assertEquals(challenged1.name, this.mock?.formattedGetFeedActivities?.challengedName)
        assertEquals(challenged1.set.toString(), this.mock?.formattedGetFeedActivities?.challengedSets)
        assertEquals(challenged1.photo, this.mock?.formattedGetFeedActivities?.challengedPhoto)
        assertEquals(date.toString(), this.mock?.formattedGetFeedActivities?.feedDate)
        assertEquals(likes.size.toString(), this.mock?.formattedGetFeedActivities?.numberOfLikes)
    }

    @Test
    fun testUserIsOnLikeList() {
        //prepare
        val userLogged = FeedModel.FeedPlayer(UserSingleton.loggedUser.name, 1, 2)
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val identifier = "1"
        val likes = mutableListOf(userLogged)
        val date = Date()
        val activity1 = FeedModel.FeedActivity(challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date), feedDate = date, identifier = identifier, likes = likes)
        val response = FeedModel.LikeAndUnlike.Response(likedActivity = activity1)

        //call
        this.presenter?.updateViewActivity(response = response)

        //assert
        assertTrue(this.mock?.formattedGetFeedActivities?.currentUserLiked!!)

    }

    @Test
    fun getUserManager() {
        //prepare and call
        val userManagerTest = this.presenter?.userManager
        //assert
        assertNotNull(userManagerTest)

    }

    @Test
    fun validateUserImageOnSucess() {
        //prepare and call
        val number = this.presenter?.validateUserPhoto("10")

        //assert
        assertEquals(10, number)
    }

    @After
    fun tearDown() {

        super.tearDownRealm()
        this.mock = null
        this.presenter = null
    }
}

private class MockFeedDisplayLogic: FeedDisplayLogic{

    var formattedGetFeedActivities: FeedModel.FeedActivityFormatted? = null
    var passedHere = false

    override fun updateLike(viewModel: FeedModel.LikeAndUnlike.ViewModel) {
        formattedGetFeedActivities = null
        formattedGetFeedActivities = viewModel.formattedLikedActivities
    }

        override fun displayFeed(viewModel: FeedModel.GetFeedActivities.ViewModel) {
            formattedGetFeedActivities = null
            formattedGetFeedActivities = viewModel.feedActivities[0]
            this.passedHere = true
        }
    }
}
