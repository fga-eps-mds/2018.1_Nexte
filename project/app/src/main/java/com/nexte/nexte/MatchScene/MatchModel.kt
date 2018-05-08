package com.nexte.nexte.MatchScene

/**
 * Created by leticia on 30/04/18.
 */

/**
 * Class that defines the attributes and types of objects that will be used on
 * Match Scene, containing classes to define Requests, Responses and ViewModels
 * to ensure the activity flows and other attributes needed to the correct
 * functionalism of the scene
 */
class MatchModel {

    /**
     * Class that define the activty flow of getting the scene started
     */
  class InitScene {

      /**
       * Class responsible to receive the ID of the challenge and passing it to
       * request other informations on worker
       *
       * @param matchID identifier of the challenge that needs to be found
       */
      class Request(var matchID: String)

      /**
       * Class responsible to pass the unformmated elements from worker to presenter
       *
       * @param match informations about the match as they should be altered on program
       */
      class Response(var match: MatchData)

      /**
       * Class responsible to store the formatted data and use it to display on view
       *
       * @param matchFormatted data ready to be displayed on screen
       */
      class ViewModel(var matchFormatted: FormattedMatchData)

  }
    // --------- Aux classes ---------

    /**
     * Class responsible to define data of the Challenge received on response
     *
     * @param challenged informations of the challenged player
     * @param challenger informations of the challenger player
     */
    class MatchData (var challenged: MatchPlayer,
                     var challenger: MatchPlayer)


    /**
     * Class that stores formatted Data to be shown on screen, as the name of the players
     * and their profile pictures
     *
     * @param challengedName name of the challenged player
     * @param challengedPhoto profile picture of the challenged player
     * @param challengerName name of the challenger player
     * @param challengerPhoto profile picture of the challenger player
     */
    class FormattedMatchData (var challengedName: String,
                              var challengedPhoto: Int,
                              var challengerName: String,
                              var challengerPhoto: Int)

    /**
     * Class that defines the types of sets to be used on definition by user on view
     *
     * @param number integer to define the number of matches that will be played
     */
    enum class SetsNumber (val number: Int) {
        One(1),
        Three(3),
        Five(5),
        WO(0)
    }

    /**Class that defines the attributes that form a player
     *
     * @param name Name of the player
     * @param photo Profile picture of the player
     */
    class MatchPlayer (var name: String, var photo: Int)

}