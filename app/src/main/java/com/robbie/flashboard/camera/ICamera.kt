package com.robbie.flashboard.camera

import android.content.Context

interface ICamera {
    fun init(context: Context)

    fun toggleFlash(enable: Boolean): Boolean

    fun release()
}