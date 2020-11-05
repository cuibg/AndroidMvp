package cn.cuibg.mvp

import android.content.Intent
import androidx.annotation.UiThread

interface MvpPresenter<V : MvpView?> {
    @UiThread
    fun attachView(view: V)

    @UiThread
    fun detachView(retainInstance: Boolean)

    @UiThread
    fun create()

    @UiThread
    fun resume()

    @UiThread
    fun pause()

    @UiThread
    fun stop()

    @UiThread
    fun destroy()

    @UiThread
    fun activityResult(requestCode: Int, resultCode: Int, data: Intent?)
}