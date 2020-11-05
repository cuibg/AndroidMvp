package cn.cuibg.mvp.delegate

import android.content.Intent
import android.os.Bundle
import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView

class ActivityMvpDelegateImpl<V : MvpView, P : MvpPresenter<V>>(delegateCallback: MvpDelegateCallback<V, P>) :
    ActivityMvpDelegate<V, P> {

    val mvpInternalDelegate: MvpInternalDelegate<V, P> by lazy {
        MvpInternalDelegate<V, P>(delegateCallback)
    }

    override fun onCreate(bundle: Bundle?) {
        mvpInternalDelegate.createPresenter()
        mvpInternalDelegate.attachView()
        mvpInternalDelegate.create()
    }

    override fun onDestroy() {
        mvpInternalDelegate.detachView()
        mvpInternalDelegate.destroy()
    }

    override fun onPause() {
        mvpInternalDelegate.pause()
    }

    override fun onResume() {
        mvpInternalDelegate.resume()
    }

    override fun onStart() {}

    override fun onStop() {
        mvpInternalDelegate.stop()
    }

    override fun onRestart() {}
    override fun onContentChanged() {}
    override fun onSaveInstanceState(outState: Bundle?) {}
    override fun onPostCreate(savedInstanceState: Bundle?) {}

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        mvpInternalDelegate.activityResult(requestCode, resultCode, data)
    }
}