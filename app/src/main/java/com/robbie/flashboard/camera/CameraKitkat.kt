package com.robbie.flashboard.camera

import android.content.Context
import android.hardware.Camera

class CameraKitkat : ICamera {

    var mCamera: Camera? = null

    override fun init(context: Context) {
        try {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun toggleFlash(enable: Boolean):Boolean {
        if (mCamera == null) {
            return false
        }
        val parameter = mCamera?.parameters
        if (enable) {
            parameter?.flashMode = Camera.Parameters.FLASH_MODE_ON
            mCamera?.parameters = parameter
            mCamera?.startPreview()
        } else {
            parameter?.flashMode = Camera.Parameters.FLASH_MODE_OFF
            mCamera?.parameters = parameter
            mCamera?.stopPreview()
        }
        return true
    }

    override fun release() {
        mCamera?.release()
        mCamera = null
    }
}