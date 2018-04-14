package com.nexte.nexte.LikeListScene

import android.provider.ContactsContract
import java.util.*

/**
 * Created by lorrany on 12/04/18.
 */

/**
 * Class Responsable to add a list of likes
 */
class LikeListModel {
    /**
     * Class responsible to pass data of view to interactor and after to worker.
     */

    class Request(var request: String)


    /**
     * Class responsible to take the informations of worker to pass for Presenter
     *
     * @param PLayers MutableList that hold informations to show in screen(name,photo and time)
     */
    class Response(var Players: MutableList<Players>)

    /**
     * Class responsible to define how the list view will display the formatted data, passed to view
     *
     * @param PlayersFormatted MutableList that hold informations to show in screen
     */

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