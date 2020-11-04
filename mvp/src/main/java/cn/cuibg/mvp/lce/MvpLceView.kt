package cn.cuibg.mvp.lce

import androidx.annotation.UiThread
import cn.cuibg.mvp.MvpView

interface MvpLceView : MvpView {
    @UiThread
    fun showRefreshCompleted()

    @UiThread
    fun showLoadMoreCompleted()

    @UiThread
    fun showContent()

    @UiThread
    fun showNoMoreData()

    @UiThread
    fun showEmptyView()

    @UiThread
    fun showErrorView()

    @UiThread
    fun showError(errorMsg: String?)

    @UiThread
    fun loadData(pullToRefresh: Boolean)
}