package com.nexte.nexte.CommentsScene

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_feed_view.*

/**
 * Interface to define Display Logic to CommentsView Class that will
 * receive information from Presenter
 */
interface CommentsDisplayLogic {

    fun displayComments(viewModel: CommentsModel.ViewModel)
}

class CommentsView: AppCompatActivity(), CommentsDisplayLogic {

    var interactor: CommentsInteractor? = null
    var commentsViewAdapter = CommentsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_view)

        this.setUpCommentsScene()

        this.commentsViewAdapter = CommentsView.CommentsAdapter(mutableListOf(), this)
        commentsRecyclerView.adapter = this.commentsViewAdapter
        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun setUpCommentsScene {

        val view = this
        val interactor = CommentsInteractor()
        val presenter = CommentsPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view
    }

    override fun displayComments(viewModel: CommentsModel.ViewModel) {

    }

    class CommentsAdapter(comments: MutableList<CommentsModel.CommentFormatted>,
                          context: Context) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                CommentsView.CommentsAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.row_feed, parent,false)
            return CommentsView.CommentsAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentsView.CommentsAdapter.ViewHolder,
                                      position: Int) {

            holder.bindView(comments[position], context)
        }

        override fun getItemCount(): Int {

            return this.comments.size
        }








        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindView(commentsFormatted: CommentsModel.CommentFormatted) {



            }

        }

    }




}