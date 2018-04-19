package com.nexte.nexte.ChallengeScene

/**
 * Class to define the Model of Challenge Scene to send informations between layers
 */
class ChallengeModel{
    /**
     * Class responsible to pass data from View to Interactor and then to worker,
     * so it can request data
     *
     * @property challenger
     * @property challenged
     * @property place
     * @property hour
     * @property date
     */
    class Request(var challenger: ChallengeModel.Player?,
                  var challenged: ChallengeModel.Player?,
                  var place: String?,
                  var hour: String?,
                  var date: String?)

    /**
     * Class responsible to store received informations from Worker and pass it to Presenter
     *
     * @property challengedAnswer
     */
    class Response(var challengedAnswer: Boolean?)

    /**
     * Class responsible to define how the list view will display the formatted data passed to view
     *
     * @property message Message that will be shown in the screen
     */
    class ViewModel(var message: String?)

    // --------- Aux class ---------

    /**
     * Class responsible to hold player informations
     */
    class Player(var name: String?,
                 var rankingPosition: Int?,
                 var wins: Int?,
                 var losses: Int?,
                 var pictureAdress: String?)
}