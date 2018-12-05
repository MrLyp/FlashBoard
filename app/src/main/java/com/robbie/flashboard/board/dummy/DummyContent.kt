package com.robbie.flashboard.board.dummy

import com.robbie.flashboard.flash.FlashActivity
import java.util.*

object DummyContent {

    val ITEMS: MutableList<DummyItem> = ArrayList()

    init {
        addItem(createDummyItem("手电筒", FlashActivity::class.java))
        addItem(createDummyItem("画板", FlashActivity::class.java))
        addItem(createDummyItem("弹幕", FlashActivity::class.java))
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
    }

    private fun createDummyItem(name: String, clz: Class<*>): DummyItem {
        return DummyItem(name, clz)
    }

    data class DummyItem(val name: String, val clz: Class<*>) {
        override fun toString(): String = name
    }
}
