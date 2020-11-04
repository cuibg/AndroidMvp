package cn.cuibg.mvp

import androidx.annotation.UiThread
import java.lang.ref.WeakReference
import java.lang.reflect.ParameterizedType

abstract class MvpNullObjectBasePresenter<V : MvpView?> : MvpPresenter<V> {
    private var view: WeakReference<V>? = null
    private var nullView: V? = null
    private fun isSubTypeOfMvpView(klass: Class<*>): Boolean {
        if (klass == MvpView::class.java) {
            return true
        }
        val superInterfaces = klass.interfaces
        for (i in superInterfaces.indices) {
            if (isSubTypeOfMvpView(superInterfaces[0])) {
                return true
            }
        }
        return false
    }

    @UiThread
    override fun attachView(view: V) {
        this.view = WeakReference(view)
    }

    @UiThread
    protected fun getView(): V? {
        if (view != null) {
            val realView = view!!.get()
            if (realView != null) {
                return realView
            }
        }
        return nullView
    }

    @UiThread
    override fun detachView(retainInstance: Boolean) {
        view?.clear()
        view = null
    }

    init {
        try {
            var viewClass: Class<V>? = null
            var currentClass: Class<*> = javaClass
            while (viewClass == null) {
                var genericSuperType = currentClass.genericSuperclass
                while (genericSuperType !is ParameterizedType) {
                    currentClass = currentClass.superclass
                    genericSuperType = currentClass.genericSuperclass
                }
                val types =
                    genericSuperType.actualTypeArguments
                for (i in types.indices) {
                    val genericType = types[i] as Class<*>
                    if (genericType.isInterface && isSubTypeOfMvpView(genericType)) {
                        viewClass = genericType as Class<V>
                        break
                    }
                }
                currentClass = currentClass.superclass
            }
            nullView = NoOp.of(viewClass)
        } catch (t: Throwable) {
            throw IllegalArgumentException(
                "The generic type <V extends MvpView> must be the first generic type argument of class "
                        + javaClass.simpleName
                        + " (per convention). Otherwise we can't determine which type of View this"
                        + " Presenter coordinates.", t
            )
        }
    }
}