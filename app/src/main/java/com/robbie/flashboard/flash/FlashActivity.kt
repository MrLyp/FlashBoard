package com.robbie.flashboard.flash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.robbie.flashboard.R
import com.robbie.flashboard.camera.CameraKitkat
import com.robbie.flashboard.camera.CameraMarshmallow
import com.robbie.flashboard.camera.ICamera

class FlashActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val REQUEST_CAMERA = 1
    }

    private lateinit var mIconView: ImageView

    private lateinit var mErrorView: TextView

    private lateinit var mCamera: ICamera

    private var mFlashOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)
        mIconView = findViewById(R.id.iv_flash)
        mIconView.setOnClickListener(this)
        mErrorView = findViewById(R.id.tv_error)
        mCamera = if (Build.VERSION.SDK_INT >= 23) {
            CameraMarshmallow()
        } else {
            CameraKitkat()
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            mCamera.init(this)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)) {
                mErrorView.visibility = View.VISIBLE
                mIconView.visibility = View.GONE
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // If request is cancelled, the result arrays are empty.
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            mCamera.init(this)
        } else {
            mErrorView.visibility = View.VISIBLE
            mIconView.visibility = View.GONE
        }
        return
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_flash) {
            if (mCamera.toggleFlash(!mFlashOn)) {
                mFlashOn = !mFlashOn
                val icon = if (mFlashOn) {
                    R.drawable.ic_power_on
                } else {
                    R.drawable.ic_power_off
                }
                mIconView.setImageResource(icon)
            } else {
                Toast.makeText(this, R.string.failed_to_open_flash, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCamera.toggleFlash(false)
        mCamera.release()
    }
}
