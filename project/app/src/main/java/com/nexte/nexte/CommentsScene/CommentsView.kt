package com.nexte.nexte.CommentsScene

/**
 * Interface to define Display Logic to CommentsView Class that will
 * receive information from Presenter
 */
interface CommentsDisplayLogic {

    fun displayComments(viewModel: CommentsModel.ViewModel)
}