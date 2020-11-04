package cn.cuibg.mvp.delegate

import android.content.Intent
import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView

open class MvpInternalDelegate<V : MvpView, P : MvpPresenter<V>>(delegateCallback: MvpDelegateCallback<V, P>) {
    private val delegateCallback: MvpDelegateCallback<V, P>
    fun createPresenter() {
        var presenter: P? = delegateCallback.getPresenter()
        if (presenter == null) {
            presenter = delegateCallback.createPresenter()
        }
        delegateCallback.setPresenter(presenter)
    }

    fun create() {
        presenter.create()
    }

    fun attachView() {
        presenter.attachView(delegateCallback.mvpView)
    }

    fun detachView() {
        presenter.detachView(delegateCallback.shouldInstanceBeRetained())
    }

    fun resume() {
        presenter.resume()
    }

    fun pause() {
        presenter.pause()
    }

    fun stop() {
        presenter.stop()
    }

    fun destroy() {
        presenter.destroy()
    }

    private val presenter: P
        get() = delegateCallback.getPresenter()
            ?: throw NullPointerException("Presenter returned from getPresenter() is null")

    fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.activityResult(requestCode, resultCode, data)
    }

    init {
        this.delegateCallback = delegateCallback
    }
}