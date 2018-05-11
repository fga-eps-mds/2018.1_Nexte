package com.nexte.nexte

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nexte.nexte.ChallengeScene.ChallengeModel
import com.nexte.nexte.FeedScene.FeedView
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.RankingScene.RankingView
import com.nexte.nexte.ShowProfileScene.ShowProfileView
import com.nexte.nexte.ChallengeScene.ChallengeView
import com.nexte.nexte.MatchScene.MatchView
import com.nexte.nexte.R.id.feedRecyclerView
import kotlinx.android.synthetic.main.activity_challenger_sent.*
import kotlinx.android.synthetic.main.activity_feed_view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_feed.*

class MainActivity : AppCompatActivity(), LoginDisplayLogic {

    private var loginInteractor: LoginBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up feed button listener
        /*feedButton.setOnClickListener {
            val intent = Intent(this, FeedView::class.java)
            startActivity(intent)
        }*/

        showProfileButton.setOnClickListener {
            val intent = Intent(this, ShowProfileView::class.java)
            startActivity(intent)
        }

        /*rankingButton.setOnClickListener {
            val intent = Intent(this, RankingView::class.java)
            startActivity(intent)
        }*/

        challangeButton.setOnClickListener {
            val intent = Intent(this, ChallengeView::class.java)
            startActivity(intent)
        }

        // Ranking button
        /* rankingButton.setOnClickListener {
            val intent = Intent(this, RankingView::class.java)
            startActivity(intent)
        }*/

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }


        //Button to display Match scene
        matchButton.setOnClickListener {
            val intent = Intent(this, MatchView::class.java)
            intent.putExtra("identifier", "matchName")
            startActivity(intent)
        }
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {}


    // Helena: comecei a fazer aqui!
    class TabFragment : Fragment() {

        var position = 0
        private var feedRecyclerView: RecyclerView? = null

        /**
         * Method that gets which tab is selected by the user
         */
        fun getInstance(position: Int): ChallengeView.TabFragment {

            val bundle = Bundle()
            val tabFragment = ChallengeView.TabFragment()

            bundle.putInt("position", position)
            tabFragment.arguments = bundle
            return tabFragment
        }

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            position = arguments.getInt("position")
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val view: View?

            if(position == 0) {
                view = inflater?.inflate(R.layout.activity_feed_view, container, false)
                 val feedViewAdapter = FeedView.FeedAdapter(mutableListOf(), context)
                feedRecyclerView= view?.findViewById(R.id.feedRecyclerView)
                feedRecyclerView?.adapter = feedViewAdapter
                feedRecyclerView?.layoutManager = LinearLayoutManager(context)

                .createGetActivitiesRequest()

            } else if(position == 1){
                view = inflater?.inflate(R.layout.activity_challenger, container, false)
                recyclerView = view?.findViewById(R.id.challengeRecyclerView)
                this.sendChallengeButton = view?.findViewById(R.id.sendChallengeButton)
                (context as ChallengeView).sendChallengeButton = this.sendChallengeButton
                (context as ChallengeView).expandedLosses = view?.findViewById(R.id.expandedLosses)
                (context as ChallengeView).expandedName = view?.findViewById(R.id.expandedName)
                (context as ChallengeView).expandedRankingTextView = view?.findViewById(R.id.expandedRankingTextView)
                (context as ChallengeView).expandedWins = view?.findViewById(R.id.expandedWins)
            } else {
                view = inflater?.inflate(R.layout.activity_ranking, container,  false)
            }
            return view
        }

        // Acho que aqui podemos setar o clique do bottom navigation, mas não to sabendo
        // chamar os parametros do que tá no xml dele

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

            super.onViewCreated(view, savedInstanceState)

            feedActivity?.setOnClickListener {
                val  =
//                        ChallengeModel.ChallengeButtonRequest.Request(this.expandedName.text.toString())
//                (context as ChallengeView).interactor?.requestMessageForChallenger(request)
            }

            val request = ChallengeModel.ShowRankingPlayersRequest.Request(UserSingleton.getUserInformations().rankingPosition)
            (context as ChallengeView).interactor?.requestPlayersToChallenge(request)
        }
//        class ViewPagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
//
//            private val pagePosition =
//
//            override fun getCount(): Int {
//
//                return pagePosition.size
//            }
//
//            override fun getItem(position: Int): Fragment {
//
//                val tabFragment = TabFragment()
//
//                return tabFragment.getInstance(position)
//            }
//
//            override fun getPageTitle(position: Int): CharSequence {
//
//                return pagePosition.elementAt(position)
//            }
//        }


    }

}
