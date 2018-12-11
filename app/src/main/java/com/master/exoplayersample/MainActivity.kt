package com.master.exoplayersample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.exoplayer2.ExoPlaybackException
import com.master.exoplayersample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {

    var leftProgress: Float = 0f
    var rightProgress: Float = 0f
    lateinit var exoPlayerHelper: ExoPlayerHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidUtilities.init(this)

//        val file="https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4"
        val file = "/storage/emulated/0/WhatsApp/Media/WhatsApp Video/VID-20181209-WA0001.mp4"

        exoPlayerHelper = ExoPlayerHelper(this, playerView, enableCache = false)
        exoPlayerHelper.setUrl(file, autoPlay = false)
//        exoPlayerHelper.clip(21000L, 41000L)
//        exoPlayerHelper.seekTo(21000L)
//        exoPlayerHelper.setSpeed(2.5f)
//        exoPlayerHelper.play()
//        exoPlayerHelper.pause()
//        exoPlayerHelper.stop()
//        exoPlayerHelper.getCurrentPosition()
//        exoPlayerHelper.getPlayer()
//        exoPlayerHelper.setQualityUrl("")


        val TAG = "TAG"
        exoPlayerHelper.setListener(true, object : ExoPlayerHelper.Listener {

            override fun onProgress(positionMs: Long) {
                super.onProgress(positionMs)
                Log.d(TAG, "onProgress $positionMs")

                if (positionMs >= rightProgress) {
                    exoPlayerHelper.pause()
                }
            }

            override fun onPlayerReady() {
                Log.d(TAG, "onPlayerReady")
            }

            override fun onBuffering(isBuffering: Boolean) {
                Log.d(TAG, "onBuffering: ${isBuffering}")
            }

            override fun onError(error: ExoPlaybackException?) {
                Log.d(TAG, "onError: ${error}")
            }

            override fun onStart() {
                super.onStart()
                Log.d(TAG, "onStart")
            }

            override fun onStop() {
                super.onStop()
                Log.d(TAG, "onStop")
                exoPlayerHelper.seekTo(leftProgress.roundToLong())
            }

            override fun onToggleControllerVisible(isVisible: Boolean) {
                Log.d(TAG, "onToggleControllerVisible:${isVisible}")
            }
        })

        //------Trimmer
        range_slider.setVideoPath(file)

        range_slider.setMaxProgressDiffInSec(200f)
        range_slider.setMinProgressDiffInSec(2f)

        leftProgress = range_slider.leftProgressInSec
        rightProgress = range_slider.rightProgressInSec
        tvMessage.setText("$leftProgress-$rightProgress")

        exoPlayerHelper.seekTo(leftProgress.roundToLong())

//        range_slider.setMaxProgressDiff(0.5f)
//        range_slider.setMinProgressDiff(0.2f)

//        range_slider.setRoundFrames(true)
        range_slider.setDelegate(object : VideoTimelineView.VideoTimelineViewDelegate {
            override fun onLeftProgressChanged(progress: Float) {
                leftProgress = range_slider.leftProgressInSec
                rightProgress = range_slider.rightProgressInSec
                tvMessage.setText("$leftProgress-$rightProgress")

                exoPlayerHelper.seekTo(leftProgress.roundToLong())
            }

            override fun onRightProgressChanged(progress: Float) {
                leftProgress = range_slider.leftProgressInSec
                rightProgress = range_slider.rightProgressInSec
                tvMessage.setText("$leftProgress-$rightProgress")

                exoPlayerHelper.seekTo(leftProgress.roundToLong())
            }

            override fun didStartDragging() {
            }

            override fun didStopDragging() {
            }
        })
    }
}