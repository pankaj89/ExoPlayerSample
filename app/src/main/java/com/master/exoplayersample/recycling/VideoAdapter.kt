package com.master.exoplayersample.recycling

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.exoplayer2.ui.PlayerView
import com.master.exoplayersample.ExoPlayerHelper
import com.master.exoplayersample.R

class VideoAdapter(val context: Context, val list: ArrayList<VideoModel>) : RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = layoutInflater.inflate(R.layout.single_card, parent, false)
        return MyViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(list.get(position))
    }


    class MyViewHolder(val context: Context, val view: View) : RecyclerView.ViewHolder(view) {

        var exoPlayerHelper: ExoPlayerHelper? = null
        val playerView = view.findViewById<PlayerView>(R.id.playerView)
        val img_playback = view.findViewById<ImageView>(R.id.img_playback)
        val img_vol = view.findViewById<ImageView>(R.id.img_vol)

        companion object {
            var singleInstanceExoPlayer: ExoPlayerHelper? = null
        }

        init {
            exoPlayerHelper = ExoPlayerHelper(mContext = context as AppCompatActivity, playerView = playerView, enableCache = true, loopVideo = true)
            exoPlayerHelper?.setListener(listener = object : ExoPlayerHelper.Listener {
                override fun onStart() {
                    super.onStart()
                    img_playback.isSelected = true
                }

                override fun onStop() {
                    super.onStop()
                    img_playback.isSelected = false
                }
            })
            exoPlayerHelper?.mute()
        }

        fun bind(videoModel: VideoModel) {
            exoPlayerHelper?.setUrl(videoModel.video_url ?: "", false)
            img_playback?.setOnClickListener {
                if (singleInstanceExoPlayer?.equals(exoPlayerHelper) == true) {
                    if (singleInstanceExoPlayer?.isPlaying() == true) {
                        singleInstanceExoPlayer?.pause()
                    } else {
                        singleInstanceExoPlayer?.play()
                    }
                } else {
                    singleInstanceExoPlayer?.pause()
                    singleInstanceExoPlayer = exoPlayerHelper
                    singleInstanceExoPlayer?.play()
                }
//                img_playback.isSelected = !img_playback.isSelected
            }
            img_vol?.setOnClickListener {
                exoPlayerHelper?.toggleMuteUnMute()
                img_vol.isSelected = !img_vol.isSelected
            }
        }

        fun release() {
            exoPlayerHelper?.pause()
            singleInstanceExoPlayer?.pause()
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.release()
    }
}