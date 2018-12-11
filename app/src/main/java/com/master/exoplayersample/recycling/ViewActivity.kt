package com.master.exoplayersample.recycling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.master.exoplayersample.R
import kotlinx.android.synthetic.main.activity_video_player.*

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val list = arrayListOf<VideoModel>(
                VideoModel("http://video.pp.cn/fs08/2017/02/23/10/aa74cfad-fca1-4aa4-9969-4a22d0d2b45b.mp4", "http://android-imgs.25pp.com/fs08/2017/02/23/2/48da103a3a21d8a1dea01570bc35de8e.jpg", "Video1")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video1")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "Video2")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Image3")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "Video4")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "Video5")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "Video6")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "Image7")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "Image8")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video9")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Name10")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795676/4_nvnzry.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "Video11")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video12")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Image13")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "Image14")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "Video16")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "Image15")
                , VideoModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "Video17")
                , VideoModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "Video18")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "Image19")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "Image20")
                , VideoModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "Image21")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VideoAdapter(this, list)
    }
}