package com.nexte.nexte.CommentsScene

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.row_comments.view.*

/**
 * Interface to define Display Logic to CommentsView Class that will
 * receive information from Presenter
 */
interface CommentsDisplayLogic {

    fun displayComments(viewModel: CommentsModel.GetCommentsRequest.ViewModel)
}

/**
 * Class that implements [CommentsDisplayLogic] and is responsible to control
 * comments screen
 *
 * @property interactor it's a Interactor layer for sending requests [CommentsInteractor]
 */
class CommentsView: AppCompatActivity(), CommentsDisplayLogic {

    var interactor: CommentsInteractor? = null

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


        val request = CommentsModel.GetCommentsRequest.Request("exampleString")
        interactor?.recentComments(request)
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    private fun setUpCommentsScene() {

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

    fun sendCommentAction(){
        if(commentEditText.text.isNotEmpty()){

        }
    }

    /**
     * Adapter Class to control recycler view on ListLike
     *
     * @property comments It's a list of all comments
     * @property context Context that will show this adapter
     */
    class CommentsAdapter(private val comments: MutableList<CommentsModel.CommentFormatted>,
                          private val context: Context) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                CommentsView.CommentsAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.row_comments,
                                                            parent,
                                                            false)
            return CommentsView.CommentsAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentsView.CommentsAdapter.ViewHolder,
                                      position: Int) {

            holder.bindView(comments[position])
        }

        override fun getItemCount(): Int {

            return this.comments.size
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
            }
        }
    }
}