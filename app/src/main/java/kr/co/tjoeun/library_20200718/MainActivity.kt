package kr.co.tjoeun.library_20200718

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        callBtn.setOnClickListener {

//            안드로이드 전화 연결 기능 사용
            val phoneNumUri = Uri.parse("tel:${phoneNumTxt.text}")
            val phoneIntent = Intent(Intent.ACTION_CALL, phoneNumUri)
            startActivity(phoneIntent)

        }

        goToPhotoViewBtn.setOnClickListener {

            val myIntent = Intent(mContext, ProfilePhotoActivity::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

        val imageUrl = "http://i.pinimg.com/originals/c6/58/b9/c658b98157dec19603499b76d519e104.jpg"

        Glide.with(mContext).load(imageUrl).into(profileImg)

    }


}