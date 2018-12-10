package com.robbie.flashboard.camera

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
class CameraMarshmallow : ICamera {

    private lateinit var mCameraManager: CameraManager

    private var mCameraID: String? = null

    override fun toggleFlash(enable: Boolean): Boolean {
        return try {
            mCameraManager.setTorchMode(mCameraID, enable)
            true
        } catch (e: Throwable) {
            e.printStackTrace()
            false
        }
    }

    override fun release() {

    }


    override fun init(context: Context) {
        mCameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            for (cameraId in mCameraManager.cameraIdList) {
                val characteristics = mCameraManager.getCameraCharacteristics(cameraId)
                val cOrientation = characteristics.get(CameraCharacteristics.LENS_FACING)!!
                if (cOrientation == CameraCharacteristics.LENS_FACING_BACK) {
                    mCameraID = cameraId
                }
            }

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

}
