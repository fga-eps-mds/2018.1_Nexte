package com.nexte.nexte.LikeListScene

import android.provider.ContactsContract
import java.util.*

/**
 * Created by lorrany on 12/04/18.
 */
class LikeListModel {

    /**
     * Class Responsable to add a list of likes
     */

    class Request(var request: String)


    class Response(var Players: MutableList<Players>)


    class ViewModel(var PlayersFormatted: MutableList<PlayersFormatted> )

    /**
     * Class with informations about users who will be displayed in the likes list
     * @param: name
     * @param:photo
     * @param: time
     */
    class Players(var name: String, var photo: Int, var time: Date)
    /**
     * Class with informations about users who will be displayed in View
     * @param: name
     * @param:photo
     * @param: time
     */

    class PlayersFormatted(var name: String,
                               var photo: Int,
                               var time: String)
}