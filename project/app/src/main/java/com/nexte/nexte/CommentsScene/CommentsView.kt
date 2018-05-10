package com.nexte.nexte.CommentsScene

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.row_comments.view.*
import android.app.Activity
import android.app.AlertDialog
import android.widget.EditText
import com.nexte.nexte.UserSingleton


/**
 * Interface to define Display Logic to CommentsView Class that will
 * receive information from Presenter
 */
interface CommentsDisplayLogic {

    fun displayComments(viewModel: CommentsModel.GetCommentsRequest.ViewModel)
    fun displayPublishedComment(viewModel: CommentsModel.PublishCommentRequest.ViewModel)
    fun displayComplaintMessage(viewModel: CommentsModel.ComplaintRequest.ViewModel)
    fun displayCommentsAfterDel(viewModel: CommentsModel.DeleteCommentRequest.ViewModel)
}

/**
 * Class that implements [CommentsDisplayLogic] and is responsible to control
 * comments screen
 *
 * @property interactor it's a Interactor layer for sending requests [CommentsInteractor]
 */
class CommentsView: AppCompatActivity(), CommentsDisplayLogic {

    var interactor: CommentsBusinessLogic? = null

    /**
     * On Create method that will setup this scene and call first Request for Interactor
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        this.setUpCommentsScene()

        this.setActionToCloseKeyboard(mainLayout)

        val request = CommentsModel.GetCommentsRequest.Request()
        interactor?.recentComments(request)

        sendButton.setOnClickListener(sendCommentAction)
        commentEditText.setOnClickListener {
            rollToEndOfList()
        }
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    fun setUpCommentsScene() {

        val view = this
        val interactor = CommentsInteractor()
        val presenter = CommentsPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view
    }

    /**
     * Method responsible to receive the viewModel from Presenter and show it to the user
     *
     * @param viewModel is received from presenter to show on screen
     */
    override fun displayComments(viewModel: CommentsModel.GetCommentsRequest.ViewModel) {

        commentsRecyclerView.adapter = CommentsAdapter(viewModel.commentsFormatted,
                this)
    }

    /**
     * Method responsible to receive the ViewModel from Presenter and show the new comments
     * to the user.
     * @param viewModel is received from presenter to show on screen.
     */

    override fun displayPublishedComment(viewModel: CommentsModel.PublishCommentRequest.ViewModel) {
        (commentsRecyclerView.adapter as CommentsAdapter).addItem(viewModel.newCommentFormatted)
    }

    /**
     * Method responsible to receive the ViewModel from presenter and show the alert message. The
     user can cancel and confirm the report.
     * @param viewModel is received from presenter to show on screen
     */
    override fun displayComplaintMessage(viewModel: CommentsModel.ComplaintRequest.ViewModel) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setMessage(viewModel.alertMessage)
        builder.setPositiveButton("Ok", { dialogInterface, _ ->
            dialogInterface.cancel()
        })
        val alert = builder.create()
        alert.show()
    }

    override fun displayCommentsAfterDel(viewModel: CommentsModel.DeleteCommentRequest.ViewModel) {
        (commentsRecyclerView.adapter as CommentsAdapter).deleteComment(viewModel.delCommentsFormatted)
    }

    private fun setActionToCloseKeyboard(view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ -> //This '_' replaces the unused arguments
                hideSoftKeyboard()
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setActionToCloseKeyboard(innerView)
            }
        }
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager = this.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                this.currentFocus!!.windowToken, 0)
    }

    private fun rollToEndOfList(){
        commentsRecyclerView.smoothScrollToPosition(
                commentsRecyclerView.adapter.itemCount-1
        )
    }

    private val sendCommentAction = View.OnClickListener {
        if(commentEditText.text.isNotEmpty()){
            val request = CommentsModel.PublishCommentRequest.Request(
                    commentEditText.text.toString()
            )
            interactor?.publishNewComment(request)
            commentEditText.text.clear()
            rollToEndOfList()
        }
    }

    /**
     * Adapter Class to control recycler view on ListLike
     *
     * @property comments It's a list of all comments
     * @property context Context that will show this adapter
     */
    class CommentsAdapter(var comments: MutableList<CommentsModel.CommentFormatted>,
                          private val context: Context) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                CommentsView.CommentsAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.row_comments,
                    parent,
                    false)
            view.setOnClickListener {
                (context as CommentsView).hideSoftKeyboard()
            }
            return CommentsView.CommentsAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentsView.CommentsAdapter.ViewHolder,
                                      position: Int) {

            holder.bindView(comments[position])
            val message = String.format("Tem certeza que deseja denunciar o usuário " +
                    "%s pela mensagem \"%s\"?", comments[position].username, comments[position].comment)
            holder.itemView.reportButton.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setCancelable(true)
                builder.setMessage(message)
                builder.setPositiveButton("Sim", { dialogInterface, _ ->
                    val request = CommentsModel.ComplaintRequest.Request(position)
                    (context as CommentsView).interactor?.sendComplaint(request)
                    dialogInterface.dismiss()
                })
                builder.setNegativeButton("Não", { dialogInterface, _ ->
                    dialogInterface.cancel()
                })
                val alert = builder.create()
                alert.show() }



            val messageDel = "Tem certeza que deseja excluir esse comentário?"
            holder.itemView.deleteButton.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setCancelable(true)
                builder.setMessage(messageDel)
                builder.setPositiveButton("Sim", { dialogInterface, _ ->
                    val request = CommentsModel.DeleteCommentRequest.Request(position)
                    (context as CommentsView).interactor?.deleteComment(request)
                    dialogInterface.dismiss()
                })
                builder.setNegativeButton("Não", { dialogInterface, _ ->
                    dialogInterface.cancel()
                })
                val alert = builder.create()
                alert.show()
            }

        }

        override fun getItemCount(): Int {

            return this.comments.size
        }

        /**
         * Adds item on List and notify RecycleView that have a new item.
         */

        fun addItem(comment: CommentsModel.CommentFormatted) {
            comments.add(comment)
            this.notifyItemInserted(comments.size -1)
        }

        fun deleteComment(delComments: MutableList<CommentsModel.CommentFormatted>) {
            this.comments = delComments
            this.notifyDataSetChanged()
        }

        /**
         * View Holder Class to control items that will show on Recycler view
         *
         * @property itemView View that contains properties to show on recycler view
         */
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            /**
             * Function to bind all information about commentsFormatted
             *
             * @param commentsFormatted Formatted data to show in row of Comments Row
             */
            fun bindView(commentsFormatted: CommentsModel.CommentFormatted) {

                itemView.commentBox.text = commentsFormatted.comment
                itemView.commentDate.text = commentsFormatted.commentDate
                itemView.playerName.text = commentsFormatted.username
                itemView.deleteButton.visibility = View.INVISIBLE

                if(itemView.playerName.text == UserSingleton.getUserInformations().name) {
                    itemView.deleteButton.visibility = View.VISIBLE
                }


            }
        }
    }
}