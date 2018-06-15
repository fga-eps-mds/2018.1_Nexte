package com.nexte.nexte.ShowProfileScene


import android.graphics.Color
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager

import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import kotlinx.android.synthetic.main.row_likes.view.*
import java.text.SimpleDateFormat
import java.util.*


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


    /**
     * Function that formats the challenge to be displayed on fragment
     */
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

            val dateToShow = SimpleDateFormat("dd/MM/yyy")
            val time = dateToShow.format(stage.date)

            val dateResults = "Desafio: " + time
            val setResult = "SET: " + stage.setChallenger + " x " + stage.setChallenged
            val gamesResult = "JOGOS: " + getNumberOfSets(stage.firstGame)  +
                                          getNumberOfSets(stage.secondGame) +
                                          getNumberOfSets(stage.thirdGame) +
                                          getNumberOfSets(stage.fourthGame) +
                                          getNumberOfSets(stage.fifthGame)

            val headToHeadResult = "Head to Head: " + calculateHeadToHead(challenges,
                    challenge.challengedId, challenge.challengerId)

            val opponent = getOponent(challenge, user)
            val opponentName = opponent?.name
            val opponentPictureUrl = opponent?.profilePicture
            val opponentAddress = validateUserPhoto(opponentPictureUrl)
            val opponentColor = Color.BLUE
            val challengeResult = getChallengeResult(challenge, user)
            val formattedChallenge = ShowProfileModel.FormattedChallenge(dateResults,
                    setResult, gamesResult, headToHeadResult, opponentName, opponentPictureUrl,
                    opponentAddress, opponentColor, challengeResult)

            formattedChallengesMutable.add(formattedChallenge)

        }

        return formattedChallengesMutable.toList()

    }

    fun getChallengeResult(challenge: Challenge, user: User) : ShowProfileModel.ChallengeResult{
        val result: ShowProfileModel.ChallengeResult
        if(challenge.winner == user.id){
            result = ShowProfileModel.ChallengeResult.WON
        }
        else {
            result = ShowProfileModel.ChallengeResult.LOST
        }

        return result
    }

    /**
     * Decides wether if the opponent is the challenger or the challenged
     */
    fun getOponent(challenge: Challenge, user: User) : User? {
        val opponentId : String?
        if(challenge.challengedId == user.id){
            opponentId = challenge.challengerId
        }
        else{
            opponentId = challenge.challengedId
        }

        val realOponent = userManager?.get(opponentId)
        return realOponent
    }

    /**
     *
     */
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

    /**
     *
     */
    fun getNumberOfSets(gamePlayed: Challenge.Stage.Played.Game?): String?{
        val res : String?
        if (gamePlayed?.gameChallenged ==  null && gamePlayed?.gameChallenger == null) {
            res = ""
        }
        else{
            res = gamePlayed.gameChallenger.toString() + "/" + gamePlayed.gameChallenged.toString() + "  "
        }
        return res;
    }

    /**
     *
     */
    fun validateUserPhoto(imageIdentifier: String?) : Int {

        if(imageIdentifier != null && imageIdentifier != "") {
            return imageIdentifier.toInt()
        } else {
            return R.mipmap.ic_launcher
        }
    }

}
