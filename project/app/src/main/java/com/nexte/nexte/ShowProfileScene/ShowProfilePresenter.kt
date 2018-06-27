package com.nexte.nexte.ShowProfileScene


import android.graphics.Color
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager

import com.nexte.nexte.R
import java.text.SimpleDateFormat



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
        val number: String? = response.user?.phone

        val photo: Int? = if (response.user?.profilePicture != null && response.user?.profilePicture != "") {
            response.user?.profilePicture!!.toInt()
        } else {
            R.mipmap.ic_launcher
        }

        val formattedPlayer : ShowProfileModel.FormattedPlayer = ShowProfileModel.FormattedPlayer(
                name,
                ranking,
                email,
                number,
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
     *
     * @param user user that need to be formatted
     *
     * @return formatted user
     */
    fun formatChallenges(user: User) : List<ShowProfileModel.FormattedChallenge> {
        val formattedChallengesMutable = mutableListOf<ShowProfileModel.FormattedChallenge>()
        var challenges = challengeManager?.getPlayedChallengesFromUser(user.id)
        if (challenges == null) {
            challenges = listOf()
        } else {
            // Do nothing
        }

        for (challenge in challenges!!) {

            when (challenge.stage) {
                is Challenge.Stage.Played -> {

                    val stage = challenge.stage as Challenge.Stage.Played

                    val dateToShow = SimpleDateFormat("dd/MM/yyyy")

                    val time = dateToShow.format(stage.date)

                    val dateResults = "Desafio: " + time
                    val setResult = "SET: " + stage.setChallenger + " x " + stage.setChallenged
                    val gamesResult = "JOGOS: " + getNumberOfSets(stage.firstGame)  +
                                                  getNumberOfSets(stage.secondGame) +
                                                  getNumberOfSets(stage.thirdGame) +
                                                  getNumberOfSets(stage.fourthGame) +
                                                  getNumberOfSets(stage.fifthGame)

                    val opponent = getOponent(challenge, user)
                    val headToHeadResult = "Head to Head: " + calculateHeadToHead(challenges,
                            user.id, opponent?.id)
                    val opponentName = opponent?.nickname
                    val opponentPictureUrl = opponent?.profilePicture
                    val opponentAddress = validateUserPhoto(opponentPictureUrl)
                    val opponentColor = Color.BLUE
                    val challengeResult = getChallengeResult(challenge, user)

                    val formattedChallenge = ShowProfileModel.FormattedChallenge(dateResults,
                            setResult, gamesResult, headToHeadResult, opponentName, opponentPictureUrl,
                            opponentAddress, opponentColor, challengeResult)

                    formattedChallengesMutable.add(formattedChallenge)
                }
            }

        }

        return formattedChallengesMutable.toList()

    }

    /**
     * This function get the result of the challenge, which defines if the owner of the profile
     * won or lost the challenge
     *
     * @param challenge the analysed challenge to be shown on screen
     * @param user current user owner of the profile shown
     */
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
     * Decides whether if the opponent is the challenger or the challenged
     *
     * @param challenge analysed challenge to be matched to user
     * @param user user owner of the profile to be shown
     *
     * @return opponent of the challenge, the one that is not the owner (between challenged and challenger)
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
     * Method that calculates head to head - How many time each player won against another
     *
     * @param challenges all challenges from the user
     * @param user user that will have its profile shown
     * @param opponent opponent to calculate head to head
     *
     * @return head to head formatted
     */
    fun calculateHeadToHead(challenges: List<Challenge>, user: String, opponent: String?) :
                            String {

        var points = mutableListOf(0, 0) //0 - user //1 - opponent

        for (challenge in challenges) {
            points = calculateWinnerInHeadToHead(points, challenge, user, opponent)
        }

        return points[0].toString() + " x " + points[1].toString()
    }

    /**
     * This function is responsible to calculate whether the winner on a challenge is the
     * owner of the profile or the opponent and set a list to the correct number of game won
     * by each of them
     *
     * @param points mutable list with the number of matches won by the owner on position 0 and
     * number of matches won by opponent on position 1
     * @param challenge challenge analysed at the moment
     * @param user id of the owner of the profile
     * @param opponent id of the opponent
     *
     * @return mutable list with the correct number of games won by opponent or owner against each other
     */
    fun calculateWinnerInHeadToHead(points: MutableList<Int>, challenge: Challenge, user: String, opponent: String?):
            MutableList<Int>{

        if (challenge.challengerId == user && challenge.challengedId == opponent){
            val gamePlayed = challenge.stage as Challenge.Stage.Played?
            if (gamePlayed != null) {
                if (gamePlayed.setChallenger > gamePlayed.setChallenged) {
                    points[0]++
                } else {
                    points[1]++
                }
            }

        } else if (challenge.challengerId == opponent && challenge.challengedId == user){
            val gamePlayed = challenge.stage as Challenge.Stage.Played?
            if (gamePlayed != null) {
                if (gamePlayed.setChallenger > gamePlayed.setChallenged) {
                    points[1]++
                } else {
                    points[0]++
                }
            }
        }

        return points
    }


    /**
     * Function that defines the printed form of the sets played by each payer
     *
     * @param gamePlayed actual game that need to be analysed
     *
     * @return string containing the number of sets on the "challenger/challenged" form
     */
    fun getNumberOfSets(gamePlayed: Challenge.Stage.Played.Game?): String?{
        val res : String?
        if (gamePlayed?.gameChallenged ==  null && gamePlayed?.gameChallenger == null) {
            res = ""
        }
        else{
            res = gamePlayed.gameChallenger.toString() + "/" + gamePlayed.gameChallenged.toString() + "  "
        }
        return res
    }

    /**
     * Function that get the string with the id of the User photo and returns a valid integer if the user
     * is valid, which means it exists
     *
     * @param imageIdentifier string containing the identifier of the profile image
     *
     * @return valid integer or standard ic_launcher
     */
    fun validateUserPhoto(imageIdentifier: String?) : Int {

        if(imageIdentifier != null && imageIdentifier != "") {
            return imageIdentifier.toInt()
        } else {
            return R.mipmap.ic_launcher
        }
    }

}
