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

    fun displayComments(viewModel: CommentsModel.ViewModel)
}

class CommentsView: AppCompatActivity(), CommentsDisplayLogic {

    var interactor: CommentsInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        this.setUpCommentsScene()


        val request = CommentsModel.Request("exampleString")
        interactor?.recentComments(request)
    }

    fun setUpCommentsScene() {

        val view = this
        val interactor = CommentsInteractor()
        val presenter = CommentsPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view
    }

    override fun displayComments(viewModel: CommentsModel.ViewModel) {

        commentsRecyclerView.adapter = CommentsAdapter(viewModel.commentsFormatted,
                                                                    this)

    }



    class CommentsAdapter(private val comments: MutableList<CommentsModel.CommentFormatted>,
                          private val context: Context) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                CommentsView.CommentsAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.row_comments, parent,false)
            return CommentsView.CommentsAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentsView.CommentsAdapter.ViewHolder,
                                      position: Int) {

            holder.bindView(comments[position])
        }

        override fun getItemCount(): Int {

            return this.comments.size
        }



        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindView(commentsFormatted: CommentsModel.CommentFormatted) {

                itemView.commentBox.text = commentsFormatted.comment
                itemView.commentDate.text = commentsFormatted.commentDate
                itemView.playerName.text = commentsFormatted.username

            }
        }
    }
}