package cn.cuibg.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.cuibg.mvp.delegate.ActivityMvpDelegate
import cn.cuibg.mvp.delegate.ActivityMvpDelegateImpl
import cn.cuibg.mvp.delegate.MvpDelegateCallback


abstract class MvpActivity<V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(),
    MvpDelegateCallback<V, P>, MvpView {

    val mMvpDelegate: ActivityMvpDelegate<V, P> by lazy {
        ActivityMvpDelegateImpl(this)
    }
    protected var mPresenter: P? = null;
    override var isRetainCurrentInstance = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMvpDelegate.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mMvpDelegate.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMvpDelegate.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        mMvpDelegate.onPause()
    }

    override fun onResume() {
        super.onResume()
        mMvpDelegate.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMvpDelegate.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMvpDelegate.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        mMvpDelegate.onRestart()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        mMvpDelegate.onActivityResult(requestCode, resultCode, data)
    }

    override fun onContentChanged() {
        super.onContentChanged()
        mMvpDelegate.onContentChanged()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mMvpDelegate.onPostCreate(savedInstanceState)
    }

    override abstract fun createPresenter(): P

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