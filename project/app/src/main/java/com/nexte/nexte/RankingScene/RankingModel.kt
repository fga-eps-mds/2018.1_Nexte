package com.nexte.nexte.RankingScene

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
    class Response(var players: Array<Player>)

    /**
     * Class responsible to define how the list view will display the formatted data
     * passed to view
     *
     * @property formattedPlayers it's a list that hold information on the
     * right way to show in screen
     */
    class ViewModel(var formattedPlayers: List<FormattedPlayerInfo>)

    //-------------- Aux Classes --------------

    /**
     * Class responsible to define personal data of all players
     *
     * @property name it's the name of the player
     * @property pictureURL it's the photo of the player
     * @property wins are all the games won by the player
     * @property losses are all the games lost by the player
     * @property rankingPosition it's the position of the player on the ranking
     */
    class Player(var name: String,
                 var pictureURL: Int,
                 var wins: Int,
                 var losses: Int,
                 var rankingPosition: Int)

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
     * @property userLosses are all the games lost by the player presented as a String
     * @property userRankingPosition it's the position of the player on the ranking presented as a String
     */
    class FormattedPlayer(var userName: String,
                          var userPictureURL: Int,
                          var userWins: String,
                          var userLosses: String,
                          var userRankingPosition: String)
}

