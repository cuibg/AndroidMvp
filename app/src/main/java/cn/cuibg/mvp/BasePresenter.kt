package cn.cuibg.mvp

import android.content.Intent

/**
 * description :
 * created by: cuibg
 */
abstract class BasePresenter<V:MvpView>: MvpNullObjectBasePresenter<V>() {
    override fun create() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun stop() {
    }

    override fun destroy() {
    }

    override fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }
}