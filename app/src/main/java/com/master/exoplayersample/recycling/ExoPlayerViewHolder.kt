package com.master.exoplayersample.recycling

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ui.PlayerView
import com.master.exoplayersample.ExoPlayerHelper

abstract class ExoPlayerViewHolder(val view: View,
                                   val playerView: PlayerView,
                                   val playbackView: View,
                                   val muteUnmuteView: View? = null,
                                   val imgPreview: ImageView? = null,
                                   enableCache: Boolean = true,
                                   loopVideo: Boolean = false,
                                   loopCount: Int = Integer.MAX_VALUE,
                                   val muteDefault: Boolean = true) : RecyclerView.ViewHolder(view), ExoPlayerHelper.Listener {
    companion object {
        var singleInstanceExoPlayer: ExoPlayerHelper? = null
    }

    var exoPlayerHelper: ExoPlayerHelper? = null

    init {

        exoPlayerHelper = ExoPlayerHelper(mContext = view.context, playerView = playerView, enableCache = enableCache, loopVideo = loopVideo, loopCount = loopCount)
        exoPlayerHelper?.setListener(listener = this)
        playbackView.setOnClickListener {

            if (ExoPlayerViewHolder.singleInstanceExoPlayer?.equals(exoPlayerHelper) == true) {
                if (ExoPlayerViewHolder.singleInstanceExoPlayer?.isPlaying() == true) {
                    ExoPlayerViewHolder.singleInstanceExoPlayer?.pause()
                } else {
                    playerView.visibility = View.VISIBLE
                    ExoPlayerViewHolder.singleInstanceExoPlayer?.play()
                    imgPreview?.visibility = View.GONE
                }
            } else {
                ExoPlayerViewHolder.singleInstanceExoPlayer?.pause()
                ExoPlayerViewHolder.singleInstanceExoPlayer = exoPlayerHelper
                playerView.visibility = View.VISIBLE
                ExoPlayerViewHolder.singleInstanceExoPlayer?.play()
                imgPreview?.visibility = View.GONE
            }
        }
    }

    //------------------------

    fun makeLifeCycleAware(activity: AppCompatActivity) {
        exoPlayerHelper?.makeLifeCycleAware(activity)
    }


    fun setUrl(url: String, imageUrl: String? = "") {
        exoPlayerHelper?.setUrl(url)
        if (muteDefault) {
            exoPlayerHelper?.mute()
        } else {
            exoPlayerHelper?.unMute()
        }

        muteUnmuteView?.setOnClickListener {
            exoPlayerHelper?.toggleMuteUnMute()
            it.isSelected = !it.isSelected
        }

        if (imgPreview != null) {
            val requestManager = Glide.with(view.context).load(imageUrl)
            requestManager.into(imgPreview!!)
        }
        imgPreview?.visibility = View.VISIBLE
        playerView?.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        playbackView.isSelected = true
    }

    override fun onBuffering(isBuffering: Boolean) {
        super.onBuffering(isBuffering)
    }

    override fun onStop() {
        super.onStop()
        playbackView.isSelected = false
    }

    override fun onError(error: ExoPlaybackException?) {
        super.onError(error)
        imgPreview?.visibility = View.VISIBLE
        playerView?.visibility = View.GONE
    }
}