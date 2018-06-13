package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User

/**
 * Class to define the Model of Ranking Scene to send information about players position
 * on ranking. It contains [Request], [Response] and [ViewModel] classes to be used on the flow
 * of getting players and setting details about their wins and losses
 */
class RankingModel {

    /**
     * Class responsible to pass data from View to Interactor and then to worker,
     * so it can request data
     */
    class Request

    /**
     * Class responsible to store received information from Worker and pass it to Presenter
     *
     * @property players it's a array of all Players
     */
    class Response(var users: Array<User>?)

    /**
     * Class responsible to define how the list fragment will display the formatted data
     * passed to fragment
     *
     * @property formattedPlayers it's a list that hold information on the
     * right way to show in screen
     */
    class ViewModel(var formattedPlayers: List<FormattedPlayerInfo>)

    //-------------- Aux Classes --------------

    /**
     * Class responsible to define format players information
     *
     * @property player information about the player
     * @property shouldDrawChild responsible to expand a player cell and
     * show information on ranking list
    */
    class FormattedPlayerInfo(var player: FormattedPlayer,
                              var shouldDrawChild: Boolean)
    /**
     * Class responsible to format all the params of Player considering how they are going to
     * be shown on screen
     * @property userName it's the name of the player presented as a String
     * @property userPictureURL it's the photo of the player presented as an Int
     * @property userWins are all the games won by the player presented as a String
     * @property userRankingPosition it's the position of the player on the ranking presented as a String
     * @property userLastGame it's the user last game presented as a String
     * @property userCategory it's the player category presented as a String
     */
    class FormattedPlayer(var userName: String,
                          var userPictureURL: Int,
                          var userWins: String,
                          var userRankingPosition: String,
                          var userLastGame: String,
                          var userEfficiency: String,
                          var userCategory: String,
                          var userLastGames: List<Int>)
}

