package com.nexte.nexte.ShowProfileScene


import android.graphics.Color
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager

import com.nexte.nexte.R
import kotlinx.android.synthetic.main.row_likes.view.*


/**
 * Interface to define Presentation Logic to Show Profile Class that
 * will be used to call this Interactor on other class layer
 */

interface ShowProfilePresentationLogic {

    /**
     * Method responsible to present profile data
     *
     * @param response contains unformatted data received from [ShowProfileModel]
     */
    fun presentUserProfile(response: ShowProfileModel.Response)
}

/**
 * This class will be responsible to receive a [ShowProfileModel.Response]
 * and generate a [ShowProfileModel.ViewModel], sending it to the [ShowProfileFragment]
 *
 * @property viewScene Reference to display formatted data
 */
class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null
    var challengeManager: ChallengeManager? = null
    var userManager: UserManager? = null

    /**
     * This method is responsible for formatting data contained on
     * [ShowProfileModel.Response] and send it to [ShowProfileFragment]
     *
     * @param response contains unformatted data received from [ShowProfileModel]
     */
    override fun presentUserProfile(response: ShowProfileModel.Response) {
        val name: String? = response.user?.name
        val ranking: String? = "#" + response.user?.rankingPosition.toString()
        val email: String? = response.user?.email

        var photo: Int? = null

        if (response.user?.profilePicture != null && response.user?.profilePicture != "") {
            photo = response.user?.profilePicture!!.toInt()
        } else {
            photo = R.mipmap.ic_launcher
        }

        val formattedPlayer : ShowProfileModel.FormattedPlayer = ShowProfileModel.FormattedPlayer(
                name,
                ranking,
                email,
                photo)
        val formattedChallenges = if (response.user != null) {
            formatChallenges(response.user!!)
        } else {
            listOf()
        }
        val viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(formattedPlayer,
                formattedChallenges)

        viewScene?.displayProfile(viewModel)
    }

    fun formatChallenges(user: User) : List<ShowProfileModel.FormattedChallenge> {
        var formattedChallengesMutable = mutableListOf<ShowProfileModel.FormattedChallenge>()
        var challenges = challengeManager?.getPlayedChallengesFromUser(user.id)
        if (challenges == null) {
            challenges = listOf()
        } else {
            // Do nothing
        }

        for (challenge in challenges!!) {
            val stage = challenge.stage as Challenge.Stage.Played
            val dateResults = "Desafio: " + stage.date
            val setResult = "SET: " + stage.setChallenger + " x " + stage.setChallenged
            val gamesResult = "JOGOS: " + stage.firstGame.gameChallenger + "/" +
                    stage.firstGame.gameChallenged + " " + stage.secondGame?.gameChallenger + "/" +
                    stage.secondGame?.gameChallenged + " " + stage.thirdGame?.gameChallenger + "/" +
                    stage.thirdGame?.gameChallenged
            val headToHeadResult = "Head to Head: " + calculateHeadToHead(challenges,
                    challenge.challengedId, challenge.challengerId)
            val opponent = userManager?.get(challenge.challengerId)
            val opponentName = opponent?.name
            val opponentPictureUrl = opponent?.profilePicture
            val opponentColor = Color.BLUE

            val formattedChallenge = ShowProfileModel.FormattedChallenge(dateResults,
                    setResult, gamesResult, headToHeadResult, opponentName, opponentPictureUrl, opponentColor)

            formattedChallengesMutable.add(formattedChallenge)

        }

        return formattedChallengesMutable.toList()

    }

    fun calculateHeadToHead(challenges: List<Challenge>, challenged: String, challenger: String) :
                            String {

        var challengerPoints = 0
        var challengedPoints = 0

        for (challenge in challenges) {

            if ((challenge.challengerId == challenger && challenge.challengerId == challenged) ||
                    (challenge.challengedId == challenger && challenge.challengedId == challenged)) {

                val gamePlayed = challenge.stage as Challenge.Stage.Played?
                if (gamePlayed != null) {
                    if (gamePlayed.setChallenger > gamePlayed.setChallenged) {
                        challengerPoints++
                    } else {
                        challengedPoints++
                    }

                } else {
                    //Do nothing
                }
            }
        }

        return challengerPoints.toString() + " x " + challengedPoints.toString()
    }

}
