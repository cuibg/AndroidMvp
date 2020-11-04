package cn.cuibg.mvp;

import android.content.Intent;

import androidx.annotation.UiThread;

public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void attachView(V view);

    @UiThread
    void detachView(boolean retainInstance);

    @UiThread
    void create();

    @UiThread
    void resume();

    @UiThread
    void pause();

    @UiThread
    void stop();

    @UiThread
    void destroy();

    @UiThread
    void activityResult(int requestCode, int resultCode, Intent data);
}
