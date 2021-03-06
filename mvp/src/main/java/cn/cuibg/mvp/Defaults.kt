package cn.cuibg.mvp

import java.util.*


object Defaults {

    private val DEFAULTS =
        Collections.unmodifiableMap<Class<*>, Any>(object :
            HashMap<Class<*>?, Any?>() {
            init {
                put(java.lang.Boolean.TYPE, false)
                put(java.lang.Byte.TYPE, 0.toByte())
                put(Character.TYPE, '\u0000')
                put(java.lang.Double.TYPE, 0.0)
                put(java.lang.Float.TYPE, 0.0f)
                put(Integer.TYPE, 0)
                put(java.lang.Long.TYPE, 0L)
                put(java.lang.Short.TYPE, 0.toShort())
            }
        })

    fun <T> defaultValue(type: Class<T>?): T {
        return DEFAULTS[type] as T
    }


}