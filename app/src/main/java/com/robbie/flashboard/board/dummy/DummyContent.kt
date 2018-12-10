package com.robbie.flashboard.board.dummy

import android.support.annotation.DrawableRes
import com.robbie.flashboard.R
import com.robbie.flashboard.flash.FlashActivity
import com.robbie.flashboard.settings.SettingsActivity
import java.util.*

object DummyContent {

    val ITEMS: MutableList<DummyItem> = ArrayList()

    init {
        addItem(createDummyItem("手电筒", R.drawable.ic_flashlight, FlashActivity::class.java))
        addItem(createDummyItem("画板", R.drawable.ic_create, FlashActivity::class.java))
        addItem(createDummyItem("弹幕", R.drawable.ic_board, FlashActivity::class.java))
        addItem(createDummyItem("设置", R.drawable.ic_settings, SettingsActivity::class.java))
        addItem(createDummyItem("更多", R.drawable.ic_more, FlashActivity::class.java))
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
    }

    private fun createDummyItem(name: String, @DrawableRes resId: Int, clz: Class<*>): DummyItem {
        return DummyItem(name, resId, clz)
    }

    data class DummyItem(val name: String, @DrawableRes val resId: Int, val clz: Class<*>) {
        override fun toString(): String = name
    }
}
