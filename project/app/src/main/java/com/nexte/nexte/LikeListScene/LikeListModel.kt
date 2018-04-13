package com.nexte.nexte.LikeListScene

/**
 * Created by lorrany on 12/04/18.
 */
class LikeListModel {

    /**
     * Class Responsable to add a list of likes
     */

    class Request()


    class Response(var listPlayers: List<ListPlayers>)


    class ViewModel(var listPlayersFormatted: ListPlayersFormatted )

    /**
     * Class with informations about users who will be displayed in the likes list
     * @param: name
     * @param:photo
     * @param: time
     */
    class ListPlayers(var name: String, var photo: Int, var time: String)
    /**
     * Class with informations about users who will be displayed in View
     * @param: name
     * @param:photo
     * @param: time
     */

    class ListPlayersFormatted(var name: String,
                               var photo: Int,
                               var time: String)
}