package com.nexte.nexte

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.ChallengeScene.ChallengeModel
import com.nexte.nexte.CommentsScene.*
import com.nexte.nexte.EditProfileScene.*
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.FeedScene.*
import com.nexte.nexte.ChallengeScene.*
import kotlinx.android.synthetic.main.activity_login_view.*
import com.nexte.nexte.ShowProfileScene.*
import kotlinx.android.synthetic.main.activity_main.*
import com.nexte.nexte.FeedScene.*
import com.nexte.nexte.RankScene.*


class MainActivity : AppCompatActivity(), LoginDisplayLogic, FeedDisplayLogic, ShowProfileDisplayLogic,
        ChallengeDisplayLogic, EditProfileDisplayLogic, CommentsDisplayLogic, RankDisplayLogic {



    var showProfileInteractor : ShowProfileBusinessLogic? = null
    var loginInteractor: LoginBusinessLogic? = null
    var editProfileInteractor: EditProfileBusinessLogic? = null
    var feedInteractor: FeedBusinessLogic? = null
    var commentsInteractor: CommentsBusinessLogic? = null
    var challengeInteractor: ChallengeBussinessLogic? = null
    var rankInteractor: RankBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupLoginScene() // Setup Login Scene
        this.setupEditProfileScene()
        this.setupFeedScene() // Setup Feed Scene
        this.setupShowProfileScene() // Setup Show Profile Scene
        this.setupCommentsScene() // Setup Comments Scene
        this.setupChallengeScene() // Setup Challenge Scene

        // Testing if works the architecture
        val loginRequest: LoginModel.Request = LoginModel.Request("miguelpimentel", "123456")
        this.loginInteractor?.doAuthentication(loginRequest).

        // Testing if is working
        val editProfileRequest: EditProfileModel.Request = EditProfileModel.Request("lorranyfreire", "HDDGFRUH65752")
        this.editProfileInteractor?.getProfileToEdit(editProfileRequest)

        // Testing if is working    
        val feedRequest: FeedModel.Request = FeedModel.Request ("leticia", "larissa")
        this.feedInteractor?.recentGames(feedRequest)

        // Testing if it is working
        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                                                                                        "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)

        //testing if it is working
        var alexandre: ChallengeModel.Player = ChallengeModel.Player("Alexandre Miguel", 1, 3, 4, "www.facebook.com")
        var helena: ChallengeModel.Player = ChallengeModel.Player("Helena Goulart", 1, 4, 3, "www.instagram.com")
        val challengeRequest: ChallengeModel.Request = ChallengeModel.Request(alexandre, helena, "FGA", "14:35", "15/12/2019")
        this.challengeInteractor?.sendChallenge(challengeRequest)

        // Testing if is working
        val commentsRequest: CommentsModel.Request = CommentsModel.Request("Gandalf vs Saruman", "Frodo_Bolseiro" )
        commentsInteractor?.recentComments(commentsRequest)

        //Testing if rank is working
        setupRankScene()
        val rankRequest: RankModel.Request = RankModel.Request()
        rankInteractor?.getPlayersRanksForScene(rankRequest)
    }

    /*
     *  LOGIN SCENE
     */

    // Setup all modules for exchange of data
    private fun setupLoginScene() {

        val viewController = this
        val interactor = LoginIteractor()
        val presenter = LoginPresenter()

        viewController.loginInteractor = interactor
        interactor.presenter = presenter
        presenter.viewControler = viewController
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {

        textView.setText(viewModel.message)
    }

    /*
     *  FEED SCENE
     */

    // Setup all modules for exchange of data
    private fun setupFeedScene() {

        val viewController = this
        val interactor = FeedInteractor()
        val presenter = FeedPresenter()

        viewController.feedInteractor = interactor
        interactor.presenter = presenter
        presenter.viewController = viewController
    }

    // Print a message according with received data
    override fun displayRecentGames(viewModel: FeedModel.ViewModel) {

        textView.text = viewModel.message
    }

    /*

    *  CHALLENGE SCENE
    */

    // Setup all modules for exchange of data
    private fun setupChallengeScene() {

        val viewChallenge = this
        val interactor = ChallengeInteractor()
        val presenter = ChallengePresenter()

        viewChallenge.challengeInteractor = interactor
        interactor.presenter = presenter
        presenter.viewChallenge = viewChallenge
    }

    override fun displayChallengeAnswer(viewModel: ChallengeModel.ViewModel) {
        textView.text = viewModel.message
    }

    /*
     *  EDIT PROFILE SCENE
     */

    // Setup all modules for exchange of data
    private fun setupEditProfileScene() {

        val viewController = this
        val interactor = EditProfileInteractor()
        val presenter = EditProfilePresenter()

        viewController.editProfileInteractor = interactor
        interactor.presenter = presenter
        presenter.view = viewController
    }

    // Print a message according with received data
    override fun displayEditProfileState(viewModel: EditProfileModel.ViewModel) {
        textView.text = viewModel.message
    }


    /*
     *  COMMENTS SCENE
     */


    private fun setupCommentsScene() {

        val viewController = this
        val interactor = CommentsInteractor()
        val presenter = CommentsPresenter()

        viewController.commentsInteractor = interactor
        interactor.presenter = presenter
        presenter.viewController = viewController
    }


    override fun displayComments(viewModel: CommentsModel.ViewModel) {
        textView.text = viewModel.message
    }

    /*
     * SHOW PROFILE SCENE
     */

    // Setup all modules for exchange of data
    fun setupShowProfileScene(){

        val viewScene = this
        val interactor = ShowProfileInteractor()
        val presenter = ShowProfilePresenter()

        interactor.presenter = presenter
        presenter.viewScene = viewScene
        viewScene.showProfileInteractor = interactor
    }

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        textView.text = viewModel.message
    }

    /*
     *RANK SCENE
     */

    fun setupRankScene(){
        val viewScene = this
        val interactor = RankInteractor()
        val presenter = RankPresenter()

        viewScene.rankInteractor = interactor
        interactor.presenter = presenter
        presenter.viewScene = viewScene
    }

    override fun displayRankInScreen(viewModel: RankModel.ViewModel) {
        textView.text = viewModel.message
    }
}
