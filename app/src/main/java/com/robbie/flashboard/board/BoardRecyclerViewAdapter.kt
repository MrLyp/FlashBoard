package com.robbie.flashboard.board

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.robbie.flashboard.R


import com.robbie.flashboard.board.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.item_board_list.view.*

class BoardRecyclerViewAdapter(
        private val mActivity: Activity?,
        private val mValues: List<DummyItem>)
    : RecyclerView.Adapter<BoardRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            val intent = Intent(mActivity, item.clz)
            mActivity?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_board_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.name
        holder.mIconView.setImageResource(item.resId)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.tv_content
        val mIconView: ImageView = mView.iv_icon

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
