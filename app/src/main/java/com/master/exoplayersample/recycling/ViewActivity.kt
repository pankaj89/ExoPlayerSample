package com.master.exoplayersample.recycling

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.master.exoplayersample.R
import kotlinx.android.synthetic.main.activity_video_player.*

class ViewActivity : AppCompatActivity() {
    var adapter: VideoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val list = arrayListOf<VideoModel>(
                VideoModel("http://video.pp.cn/fs08/2017/02/23/10/aa74cfad-fca1-4aa4-9969-4a22d0d2b45b.mp4", "http://android-imgs.25pp.com/fs08/2017/02/23/2/48da103a3a21d8a1dea01570bc35de8e.jpg", "Video1")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video1")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "Video2")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", image_url = "", name="Image3")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "Video4")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "Video5")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "Video6")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", image_url = "", name="Image7")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", image_url = "", name="Image8")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video9")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", image_url = "", name="Name10")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795676/4_nvnzry.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "Video11")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video12")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", image_url = "", name="Image13")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", image_url = "", name="Image14")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "Video16")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", image_url = "", name="Image15")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "Video17")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video18")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", image_url = "", name="Image19")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", image_url = "", name="Image20")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", image_url = "", name="Image21")
        )

        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            initialPrefetchItemCount=5
        }
        adapter = VideoAdapter(this, list)
        recyclerView.adapter = adapter

        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                playAvailableVideos(newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun playAvailableVideos(newState: Int) {

        if (newState == 0) {
            val firstVisiblePosition = (recyclerView.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()
            val lastVisiblePosition = (recyclerView.getLayoutManager() as LinearLayoutManager).findLastVisibleItemPosition()
            if (firstVisiblePosition >= 0) {
                val mostVisiblePosition = getMostVisiblePosition(recyclerView.getLayoutManager() as LinearLayoutManager, firstVisiblePosition, lastVisiblePosition)
                //notify that position
                Log.i("mostVisiblePosition", "$mostVisiblePosition")
                Handler().postDelayed({
                    val holder = recyclerView.findViewHolderForAdapterPosition(mostVisiblePosition)
                    if (holder is VideoAdapter.MyCustomViewHolder && holder.exoPlayerHelper?.isPlaying() == false) {
                        holder.playbackView.callOnClick()
                    }
                }, 100)
//                runOnUiThread {
//                    val holder = recyclerView.findViewHolderForAdapterPosition(mostVisiblePosition) as VideoAdapter.MyViewHolder
//                    if (holder.exoPlayerHelper?.isPlaying() == false) {
//                        holder.img_playback.callOnClick()
//                    }
//                }

            }
        }
    }

    private fun getMostVisiblePosition(mLayoutManager: LinearLayoutManager, pos1: Int, pos2: Int): Int {

        val rec1 = Rect()
        val view1 = mLayoutManager.findViewByPosition(pos1)
        view1?.getGlobalVisibleRect(rec1)

        val rec2 = Rect()
        val view2 = mLayoutManager.findViewByPosition(pos2)
        view2?.getGlobalVisibleRect(rec2)

        val visibility1 = rec1.bottom - rec1.top
        val visibility2 = rec2.bottom - rec2.top

        return if (visibility1 >= visibility2) {
            pos1
        } else {
            if (pos2 - pos1 >= 2) {
                pos1 + 1
            } else {
                pos2
            }
        }
    }
}