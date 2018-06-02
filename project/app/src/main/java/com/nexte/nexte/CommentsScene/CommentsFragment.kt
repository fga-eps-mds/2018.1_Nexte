package com.nexte.nexte.CommentsScene

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.row_comments.view.*
import android.app.Activity
import android.app.AlertDialog
import android.app.Fragment
import android.support.constraint.ConstraintLayout
import android.widget.EditText
import android.widget.ImageButton
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.UserSingleton

/**
 * Interface to define Display Logic to CommentsFragment Class that will
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
class CommentsFragment : Fragment(), CommentsDisplayLogic {

    var commentsRecyclerView : RecyclerView? = null
    var interactor: CommentsBusinessLogic? = null
    var checkButton : ImageButton? = null
    var commentEditText: EditText? = null
    var mainLayout: ConstraintLayout? = null

    /**
     * On Create method that will setup this scene and call first Request for Interactor
     */
    fun getInstance(): CommentsFragment{
        val commentsFragment = CommentsFragment()
        return commentsFragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val newView = inflater?.inflate(R.layout.activity_comments, container, false)

        commentsRecyclerView = newView?.findViewById(R.id.commentsRecyclerView)
        checkButton = newView?.findViewById(R.id.checkButton)
        commentEditText = newView?.findViewById(R.id.commentEditText)
        mainLayout = newView?.findViewById(R.id.mainLayout)

        commentsRecyclerView?.layoutManager = LinearLayoutManager(this.activity)
        this.setUpCommentsScene()

        this.setActionToCloseKeyboard(newView)

        val request = CommentsModel.GetCommentsRequest.Request()
        interactor?.recentComments(request)

        checkButton?.setOnClickListener(sendCommentAction)
        commentEditText?.setOnClickListener {
            rollToEndOfList()
        }

        return newView

    }


    /**
     * Method responsible to setup all the references of this scene
     */
    fun setUpCommentsScene() {

        val view = this
        val interactor = CommentsInteractor()
        val presenter = CommentsPresenter()
        presenter.userManager = UserManager()

        view.interactor = interactor
        interactor.worker.updateLogic = interactor
        interactor.worker.commentsManager = CommentManager()
        interactor.presenter = presenter
        presenter.viewController = view
    }

    /**
     * Method responsible to receive the viewModel from Presenter and show it to the user
     *
     * @param viewModel is received from presenter to show on screen
     */
    override fun displayComments(viewModel: CommentsModel.GetCommentsRequest.ViewModel) {

        commentsRecyclerView?.adapter = CommentsAdapter(viewModel.commentsFormatted, this)
    }

    /**
     * Method responsible to receive the ViewModel from Presenter and show the new comments
     * to the user.
     * @param viewModel is received from presenter to show on screen.
     */

    override fun displayPublishedComment(viewModel: CommentsModel.PublishCommentRequest.ViewModel) {
        (commentsRecyclerView?.adapter as CommentsAdapter).addItem(viewModel.newCommentFormatted)
    }

    /**
     * Method responsible to receive the ViewModel from presenter and show the alert message. The
     user can cancel and confirm the report.
     * @param viewModel is received from presenter to show on screen
     */
    override fun displayComplaintMessage(viewModel: CommentsModel.ComplaintRequest.ViewModel) {
        val builder = AlertDialog.Builder(this.activity)
        builder.setCancelable(true)
        builder.setMessage(viewModel.alertMessage)
        builder.setPositiveButton("Ok", { dialogInterface, _ ->
            dialogInterface.cancel()
        })
        val alert = builder.create()
        alert.show()
    }

    override fun displayCommentsAfterDel(viewModel: CommentsModel.DeleteCommentRequest.ViewModel) {
        (commentsRecyclerView?.adapter as CommentsAdapter).deleteComment(viewModel.delCommentsFormatted)
    }

    private fun setActionToCloseKeyboard(view: View?) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view?.setOnTouchListener { _, _ -> //This '_' replaces the unused arguments
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
        val inputMethodManager = this.activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                this.activity?.currentFocus?.windowToken, 0)
    }

    private fun rollToEndOfList(){
        commentsRecyclerView?.smoothScrollToPosition(
                commentsRecyclerView?.adapter!!.itemCount-1
        )
    }

    private val sendCommentAction = View.OnClickListener {
        if(commentEditText?.text!!.isNotEmpty()){
            val request = CommentsModel.PublishCommentRequest.Request(
                    commentEditText?.text.toString()
            )
            interactor?.publishNewComment(request)
            commentEditText?.text?.clear()
            rollToEndOfList()
        }
    }

    /**
     * Adapter Class to control recycler view on ListLike
     *
     * @property comments It's a list of all comments
     * @property fragment Context that will show this adapter
     */
    class CommentsAdapter(var comments: MutableList<CommentsModel.CommentFormatted>,
                          private val fragment: Fragment) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                CommentsFragment.CommentsAdapter.ViewHolder {

            val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_comments,
                    parent,
                    false)
            view.setOnClickListener {
                (fragment as CommentsFragment).hideSoftKeyboard()
            }
            return CommentsFragment.CommentsAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentsFragment.CommentsAdapter.ViewHolder,
                                      position: Int) {

            holder.bindView(comments[position])
            val message = String.format("Tem certeza que deseja denunciar o usuário " +
                    "%s pela mensagem \"%s\"?", comments[position].username, comments[position].comment)
            holder.itemView.reportButton.setOnClickListener {
                val builder = AlertDialog.Builder(fragment.activity)
                builder.setCancelable(true)
                builder.setMessage(message)
                builder.setPositiveButton("Sim", { dialogInterface, _ ->
                    val request = CommentsModel.ComplaintRequest.Request(position)
                    (fragment as CommentsFragment).interactor?.sendComplaint(request)
                    dialogInterface.dismiss()
                })
                builder.setNegativeButton("Não", { dialogInterface, _ ->
                    dialogInterface.cancel()
                })
                val alert = builder.create()
                alert.show() }

            val messageDel = "Tem certeza que deseja excluir esse comentário?"
            holder.itemView.deleteButton.setOnClickListener {
                val builder = AlertDialog.Builder(fragment.activity)
                builder.setCancelable(true)
                builder.setMessage(messageDel)
                builder.setPositiveButton("Sim", { dialogInterface, _ ->
                    val request = CommentsModel.DeleteCommentRequest.Request(position)
                    (fragment as CommentsFragment).interactor?.deleteComment(request)
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


                if(itemView.playerName.text == UserSingleton.getUserInformations().name) {
                    itemView.deleteButton.visibility = View.VISIBLE
                    itemView.deleteButton.isEnabled = true
                }
                else {
                    itemView.deleteButton.visibility = View.INVISIBLE
                    itemView.deleteButton.isEnabled = false
                }


            }
        }
    }
}