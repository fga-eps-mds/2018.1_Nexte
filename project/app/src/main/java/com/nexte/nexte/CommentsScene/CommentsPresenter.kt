package com.nexte.nexte.CommentsScene


import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.UserSingleton
import java.text.SimpleDateFormat


/**
 * Interface to define Presentation Logic to Comment Class that
 * will be used to call this Interactor on other class layer
 */
interface CommentsPresentationLogic {

    /**
     * Method responsible to format comments data and send to view
     *
     * @param response contains unformatted data of a list of comments received from [CommentsModel]
     */
    fun presentComment(response: CommentsModel.GetCommentsRequest.Response)

    /**
     * Method responsible to format the new comment to be displayed on view
     *
     * @param response contains unformatted data of a comment received from [CommentsModel]
     */
    fun presentNewComment (response: CommentsModel.PublishCommentRequest.Response)

    /**
     * Method responsible to format the server answer to be displayed
     *
     * @param response contains integer that represents the server message
     */
    fun presentComplaint (response: CommentsModel.ComplaintRequest.Response)

    /**
     * Method responsible to format the new list of comments, without the excluded comment
     *
     * @param response contains unformatted data of list of comments
     */
    fun presentPositionToDelete(response: CommentsModel.DeleteCommentRequest.Response)
}

/**
 * Class needed to format response so the data can be displayed on activity
 *
 * @property viewController Reference to the activity where data will be displayed
 * on view
 */
class CommentsPresenter : CommentsPresentationLogic {

    var userManager: UserManager? = null
    var viewController: CommentsDisplayLogic? = null

    override fun presentComment(response: CommentsModel.GetCommentsRequest.Response) {

        val viewModel = CommentsModel.GetCommentsRequest.ViewModel(formatComment(response.comments))
        viewController?.displayComments(viewModel)
    }

    /**
     * Function that formatted the new comment wrote by user to send to View
     * @param response
     */
    override fun presentNewComment(response: CommentsModel.PublishCommentRequest.Response) {

        val newComment = response.newComment
        val dateToShow = SimpleDateFormat("EEE, dd 'of' LLL")
        val time = dateToShow.format(newComment.date)
        var image = UserSingleton.loggedUser.profilePicture?.toInt()
        if(image == null){
            image = 1
        }

        val userComment = userManager?.get(newComment.userId!!)
        val formatted = CommentsModel.CommentFormatted(
                newComment.comment!!,
                time,
                userComment!!.name,
                image)

        val viewModel = CommentsModel.PublishCommentRequest.ViewModel(formatted)

        viewController?.displayPublishedComment(viewModel)
    }

    /**
     * Function that formatted the alert message to send to View and define the message in case
     * of success ou error.
     * @param response
     */
    override fun presentComplaint(response: CommentsModel.ComplaintRequest.Response) {
        val message: String

        if (response.serverResponse == okMessage) {
            message = "Denúncia efetuada com sucesso"
        }
        else {
            message = "Erro ao conectar com o servidor"
        }
        val viewModel = CommentsModel.ComplaintRequest.ViewModel(message)

        viewController?.displayComplaintMessage(viewModel)
    }

    /**
     * Function that format the list of comments after the deletion of the comment indicated.
     *
     * @param response unformatted list of comments after deletion
     */
    override fun presentPositionToDelete(response: CommentsModel.DeleteCommentRequest.Response) {
        val viewModel = CommentsModel.DeleteCommentRequest.ViewModel(formatComment(response.delComments))
        viewController?.displayCommentsAfterDel(viewModel)
    }

    /**
     * Function that converts the MutableList Comment unformatted to
     * MutableList CommentFormatted.
     *
     * @param gameComments MutableList of unformatted comments
     * @return MutableList of formatted comments
     */
    private fun formatComment(gameComments: MutableList<Comment>) :
            MutableList<CommentsModel.CommentFormatted> {

        val commentsFormatted: MutableList<CommentsModel.CommentFormatted> = mutableListOf()
        val emptyUser = User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("", ""),
                User.Status.UNAVAILABLE,null, null, null)

        for (gameComment in gameComments) {
            var user = userManager?.get(gameComment.userId!!)
            user = if (user == null) {
                emptyUser
            } else {
                user
            }


            var image = user.profilePicture?.toInt()
            if(image == null){
                image = 1
            }


            val dateToShow = SimpleDateFormat("EEE, dd 'of' LLL")
            val time = dateToShow.format(gameComment.date)

            val commentFormatted = CommentsModel.CommentFormatted(
                    gameComment.comment!!,
                    time,
                    user.name,
                    image)

            commentsFormatted.add(commentFormatted)
        }

        return commentsFormatted
    }


    companion object {
        const val okMessage = 200
    }
}