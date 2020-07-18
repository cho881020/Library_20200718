package kr.co.tjoeun.library_20200718

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
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

//            권한 획득 여부에 따른 행동 방안을 변수에 저장
//            인터페이스를 익명클래스에 올려서 변수에 담자.

            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
//                    모든 권한이 승인된 경우에 실행할 내용

//                    안드로이드 전화 연결 기능 사용
                    val phoneNumUri = Uri.parse("tel:${phoneNumTxt.text}")
                    val phoneIntent = Intent(Intent.ACTION_CALL, phoneNumUri)
                    startActivity(phoneIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                    하나라도 거부된 권한이 있다면 실행할 내용
                    Toast.makeText(mContext, "권한이 거부되어, 통화를 할 수 없습니다.", Toast.LENGTH_SHORT).show()

                }

            }


//            실제로 권한 확인을 요청하자.
            TedPermission.with(mContext)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .setDeniedMessage("권한을 거부하면 통화기능을 사용할 수 없습니다. 설정에서 허용해주세요.")
                .setPermissionListener(permissionListener)
                .check()


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