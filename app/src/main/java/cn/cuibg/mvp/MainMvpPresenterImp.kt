package cn.cuibg.mvp

/**
 * description :
 * created time: 2020/11/6 14:20
 * created by: cuibg
 */
class MainMvpPresenterImp<T> : BasePresenter<MainView>(), MainMvpPresenter {

    override fun processDatas() {
        val name = "它的名字是金毛和哈士奇"
        getView()?.displayName(name);
    }

}