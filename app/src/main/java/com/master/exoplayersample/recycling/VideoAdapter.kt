package com.master.exoplayersample.recycling

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlaybackException
import com.master.exoplayersample.R


class VideoAdapter(val context: Context, val list: ArrayList<VideoModel>) : ExoPlayerRecyclerViewAdapter() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_VIDEO) {
            val view = layoutInflater.inflate(R.layout.single_card, parent, false)
            val customViewHolder = MyCustomViewHolder(view)
            customViewHolder.makeLifeCycleAware(context as AppCompatActivity)
            return customViewHolder
        } else {
            val view = layoutInflater.inflate(R.layout.single_card_image, parent, false)
            return ImageViewHolder(view)
        }
    }

    val TYPE_IMAGE = 1
    val TYPE_VIDEO = 2
    override fun getItemViewType(position: Int): Int {
        return if (list.get(position).name?.startsWith("Video") == true) {
            TYPE_VIDEO
        } else {
            TYPE_IMAGE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is MyCustomViewHolder) {
            viewHolder.playerView?.visibility = View.INVISIBLE
            viewHolder.bind(list.get(position))
        } else if (viewHolder is ImageViewHolder) {
            viewHolder.bind(list.get(position))
        }
    }


    class MyCustomViewHolder(inflatedView: View) : ExoPlayerViewHolder(
            view = inflatedView,
            playerView = inflatedView.findViewById(R.id.playerView),
            playbackView = inflatedView.findViewById(R.id.img_playback),
            imgPreview = inflatedView.findViewById<ImageView>(R.id.img_preview),
            muteUnmuteView = inflatedView.findViewById<ImageView>(R.id.img_vol),
            enableCache = true,
            loopVideo = true,
            muteDefault = true
    ) {

        val txtStatus = view.findViewById<TextView>(R.id.txtStatus)

        fun bind(videoModel: VideoModel) {
            if (videoModel.video_url?.isNotBlank() == true) {
                setUrl(videoModel.video_url!!, videoModel.image_url)
            }
        }

        override fun onStart() {
            super.onStart()
            txtStatus.text = "Playing"
        }

        override fun onBuffering(isBuffering: Boolean) {
            super.onBuffering(isBuffering)
            if (isBuffering) {
                txtStatus.text = "Buffering"
            }
        }

        override fun onStop() {
            super.onStop()
            txtStatus.text = "Stopped"
        }

        override fun onError(error: ExoPlaybackException?) {
            super.onError(error)
            txtStatus.text = "Error"
        }

    }

    class ImageViewHolder(var inflatedView: View) : RecyclerView.ViewHolder(inflatedView) {
        fun bind(get: VideoModel) {
            val requestManager = Glide.with(inflatedView.context).load(get.video_url)
            val image = inflatedView.findViewById<ImageView>(R.id.img_preview)
            requestManager.into(image)
            image.visibility = View.VISIBLE
        }
    }
}