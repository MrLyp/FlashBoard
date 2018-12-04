package com.robbie.flashboard.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.robbie.flashboard.board.BoardFragment
import com.robbie.flashboard.settings.SettingsFragment

class HomeFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return if (p0 == 0) {
            BoardFragment.newInstance()
        } else {
            SettingsFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}