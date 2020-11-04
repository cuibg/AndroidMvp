package cn.cuibg.mvp

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Description : 通过反射为给定Class对象动态代理生成一个该Class对象的实例对象
 */
internal object NoOp {
    private val DEFAULT_VALUE: InvocationHandler = DefaultValueInvocationHandler()
    fun <T> of(interfaceClass: Class<T>): T {
        return Proxy.newProxyInstance(
            interfaceClass.classLoader, arrayOf<Class<*>>(interfaceClass),
            DEFAULT_VALUE
        ) as T
    }

    private class DefaultValueInvocationHandler :
        InvocationHandler {
        @Throws(Throwable::class)
        override fun invoke(
            proxy: Any,
            method: Method,
            args: Array<Any>
        ): Any {
            return Defaults.defaultValue(method.returnType)
        }
    }
}