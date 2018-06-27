package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.User.User

/**
 * Class to define the Model of Like List Scene responsible to add a list of likes
 */
class LikeListModel {

    /**
     * Class responsible to pass data from View to Interactor and then to Worker,
     * so it can request data.
     */
    class Request(var tokenId: String, var storyId: String?)

    /**
     * Class responsible to store received information from Worker and pass to Presenter
     *
     * @param players MutableList that hold personal information of name, photo and time
     * about the players to show in screen
     */
    class Response(var players: List<User>)

    /**
     * Class responsible to define how the list fragment will display the formatted data, passed to fragment
     *
     * @param playersFormatted MutableList that hold information to show in screen
     */
    class ViewModel(var playersFormatted: MutableList<PlayersFormatted> )

    /**
     * Class with formatted information about users and how they will be displayed in View
     * @param name
     * @param photo
     */
    class PlayersFormatted(var name: String,
                           var photo: Int)
}