package com.master.exoplayersample.recycling

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class ExoPlayerRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder is ExoPlayerViewHolder) {
            holder.imgPreview?.visibility= View.VISIBLE
            holder.exoPlayerHelper?.pause()
            holder.exoPlayerHelper?.seekTo(0)
        }
    }

    /*override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder is ExoPlayerViewHolder) {
            holder.exoPlayerHelper?.stop()
        }
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is ExoPlayerViewHolder) {
            holder.exoPlayerHelper?.stop()
        }
        super.onViewRecycled(holder)
    }*/
}