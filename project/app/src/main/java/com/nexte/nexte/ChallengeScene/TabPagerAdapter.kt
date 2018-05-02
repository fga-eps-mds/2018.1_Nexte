package com.nexte.nexte.ChallengeScene

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {

            0 -> return Tab1Fragment()
            1 -> return Tab2Fragment()
            else -> return null
        }
    }

    override fun getCount(): Int {

        return tabCount
    }
}
