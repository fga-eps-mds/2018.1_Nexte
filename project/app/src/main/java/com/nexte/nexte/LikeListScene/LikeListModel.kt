package com.nexte.nexte.LikeListScene

/**
 * Class to define the Model of Like List Scene responsible to add a list of likes
 */
class LikeListModel {

    /**
     * Class responsible to pass data from View to Interactor and then to Worker,
     * so it can request data.
     */
    class Request(var request: String)

    /**
     * Class responsible to store received information from Worker and pass to Presenter
     *
     * @param players MutableList that hold personal information of name, photo and time
     * about the players to show in screen
     */
    class Response(var players: MutableList<Players>)

    /**
     * Class responsible to define how the list view will display the formatted data, passed to view
     *
     * @param playersFormatted MutableList that hold information to show in screen
     */
    class ViewModel(var playersFormatted: MutableList<PlayersFormatted> )

    /**
     * Class with information about users who will be displayed in the likes list
     * @param name
     * @param photo
     * @param time
     */
    class Players(var name: String, var photo: Int, var time: String)

    /**
     * Class with formatted information about users and how they will be displayed in View
     * @param name
     * @param photo
     * @param time
     */
    class PlayersFormatted(var name: String,
                           var photo: Int,
                           var time: String)
}