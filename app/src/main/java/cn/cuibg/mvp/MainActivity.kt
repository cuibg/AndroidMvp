package cn.cuibg.mvp

import android.content.Context
import android.os.Bundle
import android.widget.TextView

class MainActivity : MvpActivity<MainView, MvpPresenter<MainView>>(), MainView {
    lateinit var mMvpPresenter: MainMvpPresenter;
    private val tvContent: TextView by lazy {
        findViewById(R.id.tv_content) as TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = getPresenter()
        if (presenter is MainMvpPresenter) {
            mMvpPresenter = presenter;
        }
        initDatas();
    }

    override fun createPresenter(): MvpPresenter<MainView> {
        return MainMvpPresenterImp<MainView>();
    }

    override fun displayName(name: String) {
        tvContent.text = name;
    }

    override fun context(): Context? {
        return this
    }

    private fun initDatas() {
        mMvpPresenter.processDatas();
    }
}