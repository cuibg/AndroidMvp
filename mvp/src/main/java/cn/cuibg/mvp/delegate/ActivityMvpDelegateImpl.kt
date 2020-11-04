package cn.cuibg.mvp.delegate

import android.content.Intent
import android.os.Bundle
import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView

class ActivityMvpDelegateImpl<V : MvpView, P : MvpPresenter<V>>(delegateCallback: MvpDelegateCallback<V, P>?) :
    ActivityMvpDelegate<V, P> {
    var mMvpInternalDelegate: MvpInternalDelegate<V, P>? = null;
    fun getInternalDelegate(): MvpInternalDelegate<V, P>? {
        if (mMvpInternalDelegate == null) {
            mMvpInternalDelegate = MvpInternalDelegate<V, P>(delegateCallback)
        }
        return mMvpInternalDelegate
    }

    var delegateCallback: MvpDelegateCallback<V, P>

    init {

        if (delegateCallback == null) {
            throw NullPointerException("MvpDelegateCallback is null!")
        }
        this.delegateCallback = delegateCallback
    }

    override fun onCreate(bundle: Bundle?) {
        getInternalDelegate()?.createPresenter()
        getInternalDelegate()?.attachView()
        getInternalDelegate()?.create()
    }

    override fun onDestroy() {
        getInternalDelegate()?.detachView()
        getInternalDelegate()?.destroy()
    }

    override fun onPause() {
        getInternalDelegate()?.pause()
    }

    override fun onResume() {
        getInternalDelegate()?.resume()
    }

    override fun onStart() {}

    override fun onStop() {
        getInternalDelegate()?.stop()
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
        getInternalDelegate()?.activityResult(requestCode, resultCode, data)
    }
}