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
    private val mMvpDelegate: FragmentMvpDelegate<V, P> by lazy {
        FragmentMvpDelegateImpl(this)
    }
    protected var mPresenter: P? = null
    abstract override fun createPresenter(): P

    override var isRetainCurrentInstance: Boolean = false


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
        mMvpDelegate.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMvpDelegate.onViewCreated(view, savedInstanceState)
    }

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
        mMvpDelegate.onDestroyView()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        mMvpDelegate.onDestroy()
    }

    @Override
    override fun onPause() {
        super.onPause()
        mMvpDelegate.onPause()
    }

    @Override
    override fun onResume() {
        super.onResume()
        mMvpDelegate.onResume()
    }

    @Override
    override fun onStart() {
        super.onStart()
        mMvpDelegate.onStart()
    }

    @Override
    override fun onStop() {
        super.onStop()
        mMvpDelegate.onStop()
    }

    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMvpDelegate.onActivityCreated(savedInstanceState)
    }

    @Override
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mMvpDelegate.onAttach(activity)
    }

    @Override
    override fun onDetach() {
        super.onDetach()
        mMvpDelegate.onDetach()
    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMvpDelegate.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mMvpDelegate.onActivityResult(requestCode, resultCode, data)
    }
}