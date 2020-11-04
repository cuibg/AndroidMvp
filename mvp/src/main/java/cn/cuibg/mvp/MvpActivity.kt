package cn.cuibg.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.cuibg.mvp.delegate.ActivityMvpDelegate
import cn.cuibg.mvp.delegate.ActivityMvpDelegateImpl
import cn.cuibg.mvp.delegate.MvpDelegateCallback


abstract class MvpActivity<V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(),
    MvpDelegateCallback<V, P>, MvpView {
    protected var mMvpDelegate: ActivityMvpDelegate<V, P>? = null
    protected var mPresenter: P? = null;
    override var isRetainCurrentInstance = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMvpDelegate()?.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        getMvpDelegate()?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getMvpDelegate()?.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        getMvpDelegate()?.onPause()
    }

    override fun onResume() {
        super.onResume()
        getMvpDelegate()?.onResume()
    }

    override fun onStart() {
        super.onStart()
        getMvpDelegate()?.onStart()
    }

    override fun onStop() {
        super.onStop()
        getMvpDelegate()?.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        getMvpDelegate()?.onRestart()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        getMvpDelegate()?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onContentChanged() {
        super.onContentChanged()
        getMvpDelegate()?.onContentChanged()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getMvpDelegate()?.onPostCreate(savedInstanceState)
    }

    override abstract fun createPresenter(): P
    private fun getMvpDelegate(): ActivityMvpDelegate<V, P>? {
        if (mMvpDelegate == null) {
            mMvpDelegate = ActivityMvpDelegateImpl(this)
        }
        return mMvpDelegate
    }

    override fun getPresenter(): P? {
        return mPresenter
    }

    override fun setPresenter(presenter: P?) {
        this.mPresenter = presenter
    }

    override val mvpView: V
        get() = this as V

    override fun shouldInstanceBeRetained(): Boolean {
        return isRetainCurrentInstance && isChangingConfigurations
    }

}