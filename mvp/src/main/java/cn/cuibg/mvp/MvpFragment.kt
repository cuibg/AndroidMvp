package cn.cuibg.mvp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cn.cuibg.mvp.delegate.FragmentMvpDelegate
import cn.cuibg.mvp.delegate.FragmentMvpDelegateImpl
import cn.cuibg.mvp.delegate.MvpDelegateCallback

abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> :
    Fragment(),
    MvpDelegateCallback<V, P>,
    MvpView {
    protected var mMvpDelegate: FragmentMvpDelegate<V, P>? = null
    protected var mPresenter: P? = null
    abstract override fun createPresenter(): P

    override var isRetainCurrentInstance: Boolean = false

    protected fun getMvpDelegate(): FragmentMvpDelegate<V, P>? {
        if (mMvpDelegate == null) {
            mMvpDelegate = FragmentMvpDelegateImpl(this)
        }
        return mMvpDelegate
    }

    override fun getPresenter(): P? {
        return mPresenter
    }

    override fun setPresenter(presenter: P?) {
        this.mPresenter = presenter
    }

    override fun shouldInstanceBeRetained(): Boolean {
        val activity = activity
        val changingConfig = activity != null && activity.isChangingConfigurations
        return retainInstance && changingConfig
    }

    override val mvpView: V
        get() = this as V

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMvpDelegate()?.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMvpDelegate()?.onViewCreated(view, savedInstanceState)
    }

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
        getMvpDelegate()?.onDestroyView()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        getMvpDelegate()?.onDestroy()
    }

    @Override
    override fun onPause() {
        super.onPause()
        getMvpDelegate()?.onPause()
    }

    @Override
    override fun onResume() {
        super.onResume()
        getMvpDelegate()?.onResume()
    }

    @Override
    override fun onStart() {
        super.onStart()
        getMvpDelegate()?.onStart()
    }

    @Override
    override fun onStop() {
        super.onStop()
        getMvpDelegate()?.onStop()
    }

    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMvpDelegate()?.onActivityCreated(savedInstanceState)
    }

    @Override
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        getMvpDelegate()?.onAttach(activity)
    }

    @Override
    override fun onDetach() {
        super.onDetach()
        getMvpDelegate()?.onDetach()
    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getMvpDelegate()?.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getMvpDelegate()?.onActivityResult(requestCode, resultCode, data)
    }
}