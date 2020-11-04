package cn.cuibg.mvp.delegate

import android.content.Intent
import android.os.Bundle
import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView

interface ActivityMvpDelegate<V : MvpView?, P : MvpPresenter<V>?> {

    fun onCreate(bundle: Bundle?)

    fun onDestroy()

    fun onPause()

    fun onResume()

    fun onStart()

    fun onStop()

    fun onRestart()

    fun onContentChanged()

    fun onSaveInstanceState(outState: Bundle?)

    fun onPostCreate(savedInstanceState: Bundle?)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

}