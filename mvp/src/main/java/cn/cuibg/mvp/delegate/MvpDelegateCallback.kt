package cn.cuibg.mvp.delegate

import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView


/**
 * Description :
 */
interface MvpDelegateCallback<V : MvpView, P : MvpPresenter<V>> {
    fun createPresenter(): P?

    fun getPresenter(): P?

    fun setPresenter(presenter: P?)

    val mvpView: V

    var isRetainCurrentInstance: Boolean

    fun shouldInstanceBeRetained(): Boolean

}