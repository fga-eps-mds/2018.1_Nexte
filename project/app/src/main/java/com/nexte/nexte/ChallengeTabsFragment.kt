package com.nexte.nexte

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.PlayersListScene.PlayersListFragment
import com.nexte.nexte.MatchScene.MatchFragment
import com.nexte.nexte.MatchScene.MatchModel

/**
 * This class is responsible for treating user actions and also showing user needed information.
 */
class ChallengeTabsFragment : Fragment() {

    var match: MatchModel.MatchData?= null
    var viewpager: ViewPager?= null
    var tabs: TabLayout?= null


    /**
     * This method is used for get an instance of the fragment and pass arguments to it
     */
    fun getInstance(): ChallengeTabsFragment {
        return ChallengeTabsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val newView: View? = inflater?.inflate(R.layout.activity_challenger, container, false)
        //Above occurs the view instantiation
        viewpager = newView?.findViewById(R.id.viewpager)
        tabs = newView?.findViewById(R.id.tabs)

        viewpager?.adapter = ViewPagerAdapter(this.activity.supportFragmentManager, this)
        tabs?.setupWithViewPager(viewpager)

        return newView
    }

    /**
     * Adapter Class that populates the fragment
     *
     * @param fragmentManager is the manager that will control wich tab will be displayed
     * @param fragment is the fragment that will render the tabs and contains the necessary methods to display it
     */
    class ViewPagerAdapter (fragmentManager: FragmentManager,
                            var fragment: Fragment) : FragmentStatePagerAdapter(fragmentManager) {

        private val pageTitles = listOf("Tenistas", "Enviados", "Recebidos")

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }

        override fun getCount(): Int {

            return pageTitles.size
        }

        override fun getItem(position: Int): Fragment {
            var fragmentInstanced: Fragment? = null
            if(position == 0){
                fragmentInstanced = PlayersListFragment().getInstance((fragment as ChallengeTabsFragment).match != null)
            } else if(position == 1){
                fragmentInstanced = MatchFragment().getInstance((fragment as ChallengeTabsFragment).match)
            }
            else{
                fragmentInstanced = MatchFragment().getInstance((fragment as ChallengeTabsFragment).match)
            }

            return fragmentInstanced
        }

        override fun getPageTitle(position: Int): CharSequence {

            return pageTitles.elementAt(position)
        }
    }


}