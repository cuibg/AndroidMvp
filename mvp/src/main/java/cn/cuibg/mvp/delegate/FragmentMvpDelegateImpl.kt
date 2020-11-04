package cn.cuibg.mvp.delegate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView

class FragmentMvpDelegateImpl<V : MvpView, P : MvpPresenter<V>>(delegateCallback: MvpDelegateCallback<V, P>) :
    FragmentMvpDelegate<V, P> {
    private val delegateCallback: MvpDelegateCallback<V, P>
    private var internalDelegate: MvpInternalDelegate<V, P>? = null
        get() {
            if (field == null) {
                field = MvpInternalDelegate<V, P>(delegateCallback)
            }
            return field
        }
    private var onViewCreatedCalled = false

    override fun onCreate(saved: Bundle?) {}
    override fun onDestroy() {
        internalDelegate!!.destroy()
    }

    override fun onViewCreated(
        view: View?,
        savedInstanceState: Bundle?
    ) {
        internalDelegate!!.createPresenter()
        internalDelegate!!.attachView()
        internalDelegate!!.create()
        onViewCreatedCalled = true
    }

    override fun onDestroyView() {
        internalDelegate!!.detachView()
    }

    override fun onPause() {
        internalDelegate!!.pause()
    }

    override fun onResume() {
        internalDelegate!!.resume()
    }

    override fun onStart() {
        check(onViewCreatedCalled) { "It seems that you are using " + delegateCallback.javaClass.canonicalName + " as headless (UI less) fragment (because onViewCreated() has not been called or maybe delegation misses that part). Having a Presenter without a View (UI) doesn't make sense. Simply use an usual fragment instead of an MvpFragment if you want to use a UI less Fragment" }
    }

    override fun onStop() {}
    override fun onActivityCreated(savedInstanceState: Bundle?) {}
    override fun onAttach(activity: Activity?) {}
    override fun onDetach() {}
    override fun onSaveInstanceState(outState: Bundle?) {}
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        internalDelegate!!.activityResult(requestCode, resultCode, data)
    }

    init {
        this.delegateCallback = delegateCallback
    }
}