package cn.cuibg.mvp.delegate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.cuibg.mvp.MvpPresenter
import cn.cuibg.mvp.MvpView


interface FragmentMvpDelegate<V : MvpView?, P : MvpPresenter<V>?> {
    fun onCreate(saved: Bundle?)

    fun onDestroy()

    fun onViewCreated(view: View?, savedInstanceState: Bundle?)

    fun onDestroyView()

    fun onPause()

    fun onResume()

    fun onStart()

    fun onStop()

    fun onActivityCreated(savedInstanceState: Bundle?)

    fun onAttach(activity: Activity?)

    fun onDetach()

    fun onSaveInstanceState(outState: Bundle?)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

}