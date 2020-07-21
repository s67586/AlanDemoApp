package com.example.basemvvm.util

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import com.example.basemvvm.BuildConfig
import java.util.*

class ALogger {
    companion object {
        val instance: ALogger by lazy { ALogger() }
    }

    private val TAG = "Alan"

    //規定每段顯示的長度
    private val LOG_MAX_LENGTH = 2000
    private val mLogEnable = BuildConfig.DEBUG
    private val mApiLogEnable = true
    private val mDebugLogEnable = true
    private val mInfoLogEnable = true
    private val mWarningLogEnable = true
    private val mErrorLogEnable = true

    private var mSb: StringBuilder? = null
    private var mResources: Resources? = null
    private var mMapFilterCount: ArrayMap<String, Int>? = null
    private var mMapFilterBuilder: ArrayMap<String, StringBuilder>? = null

    init {
        mSb = StringBuilder()
        mMapFilterCount = ArrayMap()
        mMapFilterBuilder = ArrayMap()
    }

    /**
     * Just Log
     */
    fun v(message: String) {
        if (isLogEnable()) LogV(TAG, message)
    }

    fun d(message: String) {
        if (isDebugLogEnable()) LogD(TAG, message)
    }


    fun i(message: String) {
        if (isInfoLogEnable()) LogI(TAG, message)
    }


    fun w(message: String) {
        if (isWarningLogEnable()) LogW(TAG, message)
    }


    fun e(message: String) {
        if (isErrorLogEnable()) LogE(TAG, message)
    }


    /**
     * Basic Log
     */
    fun log() {
        if (isLogEnable()) LogV(TAG, traceMessage("--------------------------------", 2))
    }

    fun log(message: String) {
        if (isLogEnable()) LogV(TAG, traceMessage(message, 2))
    }

    fun logDebug(message: String) {
        if (isDebugLogEnable()) LogD(TAG, traceMessage(message, 2))
    }

    fun logInfo(message: String) {
        if (isInfoLogEnable()) LogI(TAG, traceMessage(message, 2))
    }

    fun logWarning(message: String) {
        if (isWarningLogEnable()) LogW(TAG, traceMessage(message, 2))
    }

    fun logError(message: String) {
        if (isErrorLogEnable()) LogE(TAG, traceMessage(message, 2))
    }

    fun log(message: Int) {
        originLog(message.toString(), 3)
    }

    fun logDebug(message: Int) {
        originDebug(message.toString(), 3)
    }

    fun logInfo(message: Int) {
        originInfo(message.toString(), 3)
    }

    fun logWarning(message: Int) {
        originWarning(message.toString(), 3)
    }

    fun logError(message: Int) {
        originError(message.toString(), 3)
    }

    fun log(message: Boolean) {
        originLog(message.toString(), 3)
    }

    fun logDebug(message: Boolean) {
        originDebug(message.toString(), 3)
    }

    fun logInfo(message: Boolean) {
        originInfo(message.toString(), 3)
    }

    fun logWarning(message: Boolean) {
        originWarning(message.toString(), 3)
    }

    fun logError(message: Boolean) {
        originError(message.toString(), 3)
    }

    fun log(message: Float) {
        originLog(message.toString(), 3)
    }

    fun logDebug(message: Float) {
        originDebug(message.toString(), 3)
    }

    fun logInfo(message: Float) {
        originInfo(message.toString(), 3)
    }

    fun logWarning(message: Float) {
        originWarning(message.toString(), 3)
    }

    fun logError(message: Float) {
        originError(message.toString(), 3)
    }

    fun log(message: Double) {
        originLog(message.toString(), 3)
    }

    fun logDebug(message: Double) {
        originDebug(message.toString(), 3)
    }

    fun logInfo(message: Double) {
        originInfo(message.toString(), 3)
    }

    fun logWarning(message: Double) {
        originWarning(message.toString(), 3)
    }

    fun logError(message: Double) {
        originError(message.toString(), 3)
    }

    /**
     * Log Array
     */
    fun log(messageArray: Array<String>) {
        originLog(parseStringArray(messageArray), 3)
    }

    fun logDebug(messageArray: Array<String>) {
        originDebug(parseStringArray(messageArray), 3)
    }

    fun logInfo(messageArray: Array<String>) {
        originInfo(parseStringArray(messageArray), 3)
    }

    fun logWarning(messageArray: Array<String>) {
        originWarning(parseStringArray(messageArray), 3)
    }

    fun logError(messageArray: Array<String>) {
        originError(parseStringArray(messageArray), 3)
    }

    fun log(vararg messageArray: Int) {
        originLog(parseStringArray(messageArray), 3)
    }

    fun logDebug(vararg messageArray: Int) {
        originDebug(parseStringArray(messageArray), 3)
    }

    fun logInfo(vararg messageArray: Int) {
        originInfo(parseStringArray(messageArray), 3)
    }

    fun logWarning(vararg messageArray: Int) {
        originWarning(parseStringArray(messageArray), 3)
    }

    fun logError(vararg messageArray: Int) {
        originError(parseStringArray(messageArray), 3)
    }

    fun log(vararg messageArray: Boolean) {
        originLog(parseStringArray(messageArray), 3)
    }

    fun logDebug(vararg messageArray: Boolean) {
        originDebug(parseStringArray(messageArray), 3)
    }

    fun logInfo(vararg messageArray: Boolean) {
        originInfo(parseStringArray(messageArray), 3)
    }

    fun logWarning(vararg messageArray: Boolean) {
        originWarning(parseStringArray(messageArray), 3)
    }

    fun logError(vararg messageArray: Boolean) {
        originError(parseStringArray(messageArray), 3)
    }

    fun log(vararg messageArray: Float) {
        originLog(parseStringArray(messageArray), 3)
    }

    fun logDebug(vararg messageArray: Float) {
        originDebug(parseStringArray(messageArray), 3)
    }

    fun logInfo(vararg messageArray: Float) {
        originInfo(parseStringArray(messageArray), 3)
    }

    fun logWarning(vararg messageArray: Float) {
        originWarning(parseStringArray(messageArray), 3)
    }

    fun logError(vararg messageArray: Float) {
        originError(parseStringArray(messageArray), 3)
    }

    fun log(vararg messageArray: Double) {
        originLog(parseStringArray(messageArray), 3)
    }

    fun logDebug(vararg messageArray: Double) {
        originDebug(parseStringArray(messageArray), 3)
    }

    fun logInfo(vararg messageArray: Double) {
        originInfo(parseStringArray(messageArray), 3)
    }

    fun logWarning(vararg messageArray: Double) {
        originWarning(parseStringArray(messageArray), 3)
    }

    fun logError(vararg messageArray: Double) {
        originError(parseStringArray(messageArray), 3)
    }

    private fun parseStringArray(array: Array<String>): String {
        mSb!!.setLength(0)
        for (message in array) mSb!!.append("$message,")
        return mSb.toString()
    }

    private fun parseStringArray(array: BooleanArray): String {
        mSb!!.setLength(0)
        for (message in array) mSb!!.append("$message,")
        return mSb.toString()
    }

    private fun parseStringArray(array: IntArray): String {
        val stringArray =
            Arrays.toString(array).split("[\\[\\]]").toTypedArray()[1].split(", ")
                .toTypedArray()
        mSb!!.setLength(0)
        for (message in stringArray) mSb!!.append("$message,")
        return mSb.toString()
    }

    private fun parseStringArray(array: FloatArray): String {
        val stringArray =
            Arrays.toString(array).split("[\\[\\]]").toTypedArray()[1].split(", ")
                .toTypedArray()
        mSb!!.setLength(0)
        for (message in stringArray) mSb!!.append("$message,")
        return mSb.toString()
    }

    private fun parseStringArray(array: DoubleArray): String {
        val stringArray =
            Arrays.toString(array).split("[\\[\\]]").toTypedArray()[1].split(", ")
                .toTypedArray()
        mSb!!.setLength(0)
        for (message in stringArray) mSb!!.append("$message,")
        return mSb.toString()
    }

    /**
     * Log Object Field
     */
    fun logObject(`object`: Any?) {
        logObject(null, `object`, 4)
    }

    fun logObject(log: String?, `object`: Any?) {
        logObject(log, `object`, 4)
    }

    fun logObjectArray(objectArray: Array<Any?>) {
        for (i in objectArray.indices) logObject(null, objectArray[i], 4)
    }

    fun logObjectArray(
        log: String?,
        objectArray: Array<Any?>
    ) {
        for (i in objectArray.indices) logObject(log, objectArray[i], 4)
    }

    fun logObjectList(objectList: List<*>) {
        for (i in objectList.indices) logObject(null, objectList[i], 4)
    }

    fun logObjectList(
        log: String?,
        objectList: List<*>
    ) {
        for (i in objectList.indices) logObject(log, objectList[i], 4)
    }

    private fun logObject(
        log: String?,
        `object`: Any?,
        trace: Int
    ) {
        if (!isLogEnable()) return
        try {
            if (`object` == null) {
                originLog("Object is null", trace)
                return
            }
            mSb!!.setLength(0)
            val objClass: Class<*> = `object`.javaClass
            mSb!!.append(objClass.simpleName).append(") ")
                .append(log ?: "NoneTag").append("\n")
            for (i in objClass.declaredFields.indices) {
                try {
                    val field = objClass.declaredFields[i]
                    field.isAccessible = true
                    val classPath = field.type.name
                    var className: String? = null
                    className =
                        if (classPath == "int") Int::class.java.name else if (classPath == "double") Double::class.java.name else if (classPath == "float") Float::class.java.name else if (classPath == "long") Long::class.java.name else if (classPath == "byte") Byte::class.java.name else if (classPath == "boolean") Boolean::class.java.name else field.type.name
                    var nameArray: Array<String?> =
                        className.split("\\.").toTypedArray()
                    if (Class.forName(className).isArray) {
                        val isByteArray = classPath == "[B"
                        if (isByteArray) {
                            nameArray =
                                Byte::class.java.name.split("\\.").toTypedArray()
                            mSb!!.append(nameArray[nameArray.size - 1])
                        } else {
                            mSb!!.append(nameArray[nameArray.size - 1])
                                .deleteCharAt(mSb!!.length - 1)
                        }
                        mSb!!.append(" array) ").append(field.name).append(" || size: ")
                        val arrayObj = field[`object`]
                        mSb!!.append(
                            if (arrayObj != null) java.lang.reflect.Array.getLength(
                                arrayObj
                            ) else "null"
                        )
                        mSb!!.append(" = ").append(
                            if (!isByteArray && arrayObj != null) Arrays.toString(arrayObj as Array<Any?>) else ""
                        )
                    } else {
                        mSb!!.append(nameArray[nameArray.size - 1]).append(") ")
                            .append(field.name).append(": ")
                        mSb!!.append(field[`object`])
                    }
                    field.isAccessible = false
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                    mSb!!.append("Error: ").append(e.message)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                    mSb!!.append("Error: ").append(e.message)
                }
                mSb!!.append("\n")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            mSb!!.append("\nALoggerError: ").append(ex.message)
        }
        originLog(mSb.toString(), trace)
    }

    /**
     * Log View Some Property
     */
    fun logView(view: View?) {
        logView(null, null, view, 4)
    }

    fun logView(log: String?, view: View?) {
        logView(log, null, view, 4)
    }

    fun logView(
        resources: Resources?,
        view: View?
    ) {
        logView(null, resources, view, 4)
    }

    fun logView(
        log: String?,
        view: View?,
        resources: Resources?
    ) {
        logView(log, resources, view, 4)
    }

    private fun logView(
        log: String?,
        resources: Resources?,
        view: View?,
        trace: Int
    ) {
        if (!isLogEnable()) return
        if (view == null) {
            originLog("View is null", trace)
            return
        }
        mSb!!.setLength(0)
        mSb!!.append(view.javaClass.simpleName)
            .append(") ").append(log ?: "NoneTag")
            .append(" || id: ").append(parseViewId(resources, view.id))
            .append(" || tag: ").append(view.tag)
        if (view is ViewGroup) {
            mSb!!.append(" || childCount: ").append(view.childCount)
        }
        originLog(mSb.toString(), trace)
    }

    fun logViewAll(view: View?) {
        logViewAll(null, null, view, 4)
    }

    fun logViewAll(log: String?, view: View?) {
        logViewAll(log, null, view, 4)
    }

    fun logViewAll(
        resources: Resources?,
        view: View?
    ) {
        logViewAll(null, resources, view, 4)
    }

    fun logViewAll(
        log: String?,
        resources: Resources?,
        view: View?
    ) {
        logViewAll(log, resources, view, 4)
    }

    private fun logViewAll(
        log: String?,
        resources: Resources?,
        view: View?,
        trace: Int
    ) {
        if (!isLogEnable()) return
        try {
            if (view == null) {
                originLog("View is null", trace)
                return
            }
            mSb!!.setLength(0)
            mSb!!.append(view.javaClass.simpleName)
                .append(") ").append(log ?: "NoneTag")
                .append(" || id: ").append(parseViewId(resources, view.id))
            logBaseView(view, mSb)
            if (view is ViewGroup) {
                logViewGroup(view, mSb)
            } else {
                logTextView(view, mSb)
                logImageView(view, mSb)
                logEditView(view, mSb)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            mSb!!.append("\nALoggerError: ").append(ex.message)
        }
        originLog(mSb.toString(), trace)
    }

    private fun logBaseView(
        view: View,
        stringBuilder: StringBuilder?
    ) {
        stringBuilder!!.append("\nw: ").append(view.width)
            .append(" and  h: ").append(view.height)
            .append(" || measuredW: ").append(view.measuredWidth)
            .append(" and  measuredH: ").append(view.measuredHeight)
            .append(" || minWidth: ").append(view.minimumWidth)
            .append(" and minHeight: ").append(view.minimumHeight)
        stringBuilder.append("\nx: ").append(view.x)
            .append(" and y: ").append(view.y)
            .append(" || transX: ").append(view.translationX)
            .append(" and transY: ").append(view.translationY)
        stringBuilder.append("\nenable: ").append(view.isEnabled)
            .append(" || visible: ")
            .append(if (view.visibility == View.VISIBLE) "Visible" else if (view.visibility == View.INVISIBLE) "Invisible" else "Gone")
            .append(" || isActivated: ").append(view.isActivated)
            .append(" || isFocusable: ").append(view.isFocusable)
            .append(" || hasFocus: ").append(view.hasFocus())
            .append(" || clickable: ").append(view.isClickable)
        val parentClass =
            if (view.parent != null) view.parent.javaClass.toString().split("\\.")
                .toTypedArray() else null
        stringBuilder.append("\nparent: ")
            .append(parentClass?.get(parentClass.size - 1) ?: "null")
            .append(" || tag: ").append(view.tag)
    }

    private fun logViewGroup(
        view: View,
        stringBuilder: StringBuilder?
    ) {
        if (view is ViewGroup) {
            stringBuilder!!.append("\nchildCount: ").append(view.childCount)
        }
    }

    private fun logTextView(
        view: View,
        stringBuilder: StringBuilder?
    ) {
        if (view is TextView) {
            var textView: TextView? = view
            stringBuilder!!.append("\ntext: ").append(textView!!.text.toString())
                .append(" || textColor: ").append(textView.currentTextColor)
                .append(" || hint: ").append(textView.hint)
                .append(" || hintColor: ").append(textView.currentHintTextColor)
                .append(" || textSize: ").append(textView.textSize)
            stringBuilder.append("\ngravity: ").append(textView.gravity)
            stringBuilder.append("\nbackground: ")
            val background = textView.background
            if (background == null) stringBuilder.append(" null") else if (background != null && background is ColorDrawable) stringBuilder.append(
                "ColorDrawable "
            )
                .append(background.color) else if (background != null && background is ShapeDrawable) stringBuilder.append(
                "ShapeDrawable"
            )
            textView = null
        }
    }

    private fun logImageView(
        view: View,
        stringBuilder: StringBuilder?
    ) {
        if (view is ImageView) {
            var imageView: ImageView? = view
            stringBuilder!!.append("\nimage: ").append(imageView!!.drawable)
            stringBuilder.append("\nbackground: ")
                .append((imageView.background as ColorDrawable).color)
            imageView = null
        }
    }

    private fun logEditView(
        view: View,
        stringBuilder: StringBuilder?
    ) {
        if (view is EditText) {
            var editText: EditText? = view
            stringBuilder!!.append("\nselectionStart: ").append(editText!!.selectionStart)
            editText = null
        }
    }

    fun setResources(resources: Resources?) {
        mResources = resources
    }

    private fun parseViewId(
        resources: Resources?,
        @IdRes id: Int
    ): String? {
        val res =
            resources ?: if (mResources != null) mResources else null
        try {
            if (res != null && id != 0) return res.getResourceEntryName(id)
        } catch (ex: Resources.NotFoundException) {
            ex.printStackTrace()
        }
        return id.toString()
    }

    /**
     * Log Collection Size and Key Value
     */
    fun logCollection(map: Map<*, *>?) {
        logCollection(null, map, 4)
    }

    fun logCollection(log: String?, map: Map<*, *>?) {
        logCollection(log, map, 4)
    }

    private fun logCollection(
        log: String?,
        map: Map<*, *>?,
        trace: Int
    ) {
        if (!isLogEnable()) return
        try {
            if (map == null) {
                originLog("Collection is null", trace)
                return
            }
            mSb!!.setLength(0)
            mSb!!.append(map.javaClass.simpleName)
                .append(") ").append(log ?: "NoneTag")
                .append(" || size: ").append(map.size)
                .append(" = ").append(map.toString())
        } catch (ex: Exception) {
            ex.printStackTrace()
            mSb!!.append("\nALoggerError: ").append(ex.message)
        }
        originLog(mSb.toString(), trace)
    }

    fun logCollection(collection: Collection<*>?) {
        logCollection(null, collection, 4)
    }

    fun logCollection(
        log: String?,
        collection: Collection<*>?
    ) {
        logCollection(log, collection, 4)
    }

    private fun logCollection(
        log: String?,
        collection: Collection<*>?,
        trace: Int
    ) {
        if (!isLogEnable()) return
        try {
            if (collection == null) {
                originLog("Collection is null", trace)
                return
            }
            mSb!!.setLength(0)
            mSb!!.append(collection.javaClass.simpleName)
                .append(") ").append(log ?: "NoneTag")
                .append(" || size: ").append(collection.size)
                .append(" = ").append(collection.toString())
        } catch (ex: Exception) {
            ex.printStackTrace()
            mSb!!.append("\nALoggerError: ").append(ex.message)
        }
        originLog(mSb.toString(), trace)
    }

    /**
     * Log Bundle Key and Value
     */
    fun logBundle(bundle: Bundle?) {
        if (!isLogEnable()) return
        try {
            if (bundle == null) {
                originLog("Bundle is null", 3)
                return
            }
            mSb!!.setLength(0)
            mSb!!.append("Bundle) ")
                .append(" size: ").append(bundle.keySet().size)
                .append("\n")
            for (key in bundle.keySet()) {
                mSb!!.append(key)
                mSb!!.append(" / ")
                mSb!!.append(bundle[key])
                mSb!!.append(", ")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            mSb!!.append("\nALoggerError: ").append(ex.message)
        }
        originLog(mSb.toString(), 3)
    }

    /**
     * Log Thread
     */
    fun logThread() {
        if (!isLogEnable()) return
        mSb!!.setLength(0)
        mSb!!.append("Thread) ").append(Thread.currentThread().name)
            .append(" || priority: ").append(Thread.currentThread().priority)
        originLog(mSb.toString(), 3)
    }

    /**
     * Log Method Route
     */
    fun logRoute() {
        logRoute(null, 2, 5, 3)
    }

    fun logRoute(count: Int) {
        logRoute(null, 2, count + 2, 3)
    }

    fun logRoute(tag: String?) {
        logRoute(tag, 2, 5, 3)
    }

    fun logRoute(tag: String?, count: Int) {
        logRoute(tag, 2, count + 2, 3)
    }

    private fun logRoute(
        log: String?,
        startTrace: Int,
        count: Int,
        trace: Int
    ) {
        if (!isLogEnable()) return
        mSb!!.setLength(0)
        mSb!!.append("Route")
        if (!TextUtils.isEmpty(log)) mSb!!.append(" ").append(log)
            .append(") ") else mSb!!.append(") ")
        val stackTraceElement =
            Throwable().stackTrace
        var methodCount = 0
        val traceLimit =
            if (count < stackTraceElement.size) count - 1 else stackTraceElement.size - 1
        for (i in traceLimit downTo startTrace) {
            ++methodCount
            mSb!!.append(stackTraceElement[i].methodName)
                .append(if (i != startTrace) " -> " else "")
            if (methodCount % 5 == 0) mSb!!.append("\n")
        }
        LogV(TAG, traceMessage(mSb.toString(), trace))
    }

    /**
     * Log Something after filter
     */

    fun <T> logFilter(message: T) {
        logFilter(null, message, 10, null, 4)
    }

    fun <T> logFilter(log: String?, message: String) {
        logFilter(log, message, 10, null, 4)
    }

    fun <T> logFilter(
        log: String?,
        message: String,
        eachCount: Int
    ) {
        logFilter(log, message, eachCount, null, 4)
    }

    fun <T> logFilter(message: T, eachCount: Int) {
        logFilter(null, message, eachCount, null, 4)
    }

    fun <T> logFilter(message: T, filter: Filter<T>?) {
        logFilter(null, message, 0, filter, 4)
    }

    fun <T> logFilter(log: String?, message: T, filter: Filter<T>?) {
        logFilter(log, message, 0, filter, 4)
    }

    private fun <T> logFilter(
        log: String?,
        message: T,
        eachCount: Int,
        filter: Filter<T>?,
        trace: Int
    ) {
        if (!isLogEnable()) return
        val acceptCollect =
            filter?.acceptCollect(message) ?: true
        if (!acceptCollect) return
        val mapLog = (if (TextUtils.isEmpty(log)) TAG else log)!!
        var count = 0
        var sbFilter: StringBuilder?
        val tempEachCount = filter?.eachCount ?: eachCount
        val isLogContent = filter?.isLogContent ?: false
        synchronized(mMapFilterCount!!) {
            if (mMapFilterCount!!.containsKey(mapLog)) {
                count = mMapFilterCount!![mapLog]!!
                sbFilter = mMapFilterBuilder!![mapLog]
            } else {
                sbFilter = StringBuilder()
                mMapFilterBuilder!![mapLog] = sbFilter
            }
            mMapFilterCount!![mapLog] = ++count
            if (count != 0 && count % tempEachCount == 0) {
                if (isLogContent) sbFilter!!.append(message)
                val stringBuilder = StringBuilder()
                stringBuilder.append("Filter")
                if (!TextUtils.isEmpty(log)) stringBuilder.append(" ").append(log)
                    .append(") Collect To ").append(count)
                    .append(if (isLogContent) ": " else "") else stringBuilder.append(") Collect To ")
                    .append(count).append(if (isLogContent) ": " else "")
                sbFilter!!.insert(0, stringBuilder.toString())
                    .append(if (isLogContent) "\n" else "")
                originLog(sbFilter.toString(), trace)
                sbFilter!!.setLength(0)
            } else if (count == 1) {
                sbFilter!!.append("Filter")
                if (!TextUtils.isEmpty(log)) sbFilter!!.append(" ").append(log)
                    .append(") Start:") else sbFilter!!.append(") Start:")
                originLog(sbFilter.toString(), trace)
                sbFilter!!.setLength(0)
                if (isLogContent) sbFilter!!.append(message).append(",")
            } else if (isLogContent) {
                sbFilter!!.append(message).append(",")
            }
        }
    }

    /**
     * Log Delay
     */
    fun logDelay(message: String, delay: Long) {
        logDelay(null, message, delay, 4)
    }

    fun logDelay(
        log: String?,
        message: String,
        delay: Long
    ) {
        logDelay(log, message, delay, 4)
    }

    private fun logDelay(
        log: String?,
        message: String,
        delay: Long,
        trace: Int
    ) {
        Handler().postDelayed({
            val logExist = !TextUtils.isEmpty(log)
            val stringBuilder = StringBuilder()
            stringBuilder.append("Delay:")
            if (logExist) stringBuilder.append(" ").append(if (logExist) log else "")
                .append(") ")
            stringBuilder.append(message)
            originLog(stringBuilder.toString(), trace)
        }, delay)
    }

    /**
     * Log Release
     */
    fun logRelease() {
        if (!isLogEnable()) return
        originLog(mSb.toString(), 3)
        mSb!!.setLength(0)
    }

    fun logReleaseFilter(log: String) {
        if (!isLogEnable()) return
        val sbFilter = mMapFilterBuilder!![log]
        originLog(sbFilter?.toString() ?: "null", 3)
        sbFilter?.setLength(0)
    }

    fun clear() {
        mResources = null
        mSb!!.setLength(0)
        mMapFilterCount = ArrayMap()
        mMapFilterBuilder = ArrayMap()
    }

    /**
     * Base Method
     */
    private fun traceMessage(message: String, trace: Int): String {
        try {
            mSb!!.setLength(0)
            val stackTraceElement =
                Throwable().stackTrace[trace]
            mSb!!.append("(")
            val array =
                stackTraceElement.className.split("\\.").toTypedArray()
            var className = array[array.size - 1]
            var threadName: String? = null
            if (className.contains("$")) {
                className = className.split("\\$").toTypedArray()[0]
                threadName = Thread.currentThread().name
            }
            mSb!!.append(className).append(".java:")
            mSb!!.append(stackTraceElement.lineNumber).append("){")
            mSb!!.append(stackTraceElement.methodName).append("}")
            if (!TextUtils.isEmpty(threadName)) mSb!!.append("in ").append(threadName)
            mSb!!.append(": ").append(message)
        } catch (ex: Exception) {
            ex.printStackTrace()
            mSb!!.append("\nALoggerError: ").append(ex.message)
        }
        return mSb.toString()
    }

    private fun originLog(message: String, trace: Int) {
        if (isLogEnable()) LogV(TAG, traceMessage(message, trace))
    }

    private fun originDebug(message: String, trace: Int) {
        if (isDebugLogEnable()) LogD(TAG, traceMessage(message, trace))
    }

    private fun originInfo(message: String, trace: Int) {
        if (isInfoLogEnable()) LogI(TAG, traceMessage(message, trace))
    }

    private fun originWarning(message: String, trace: Int) {
        if (isWarningLogEnable()) LogW(TAG, traceMessage(message, trace))
    }

    private fun originError(message: String, trace: Int) {
        if (isErrorLogEnable()) LogE(TAG, traceMessage(message, trace))
    }

    /**
     * Log Enable SettingLobby
     */
    protected fun isLogEnable(): Boolean {
        return mLogEnable
    }

    protected fun isDebugLogEnable(): Boolean {
        return mLogEnable && mDebugLogEnable
    }

    protected fun isInfoLogEnable(): Boolean {
        return mLogEnable && mInfoLogEnable
    }

    protected fun isWarningLogEnable(): Boolean {
        return mLogEnable && mWarningLogEnable
    }

    protected fun isErrorLogEnable(): Boolean {
        return mErrorLogEnable
    }

    /**
     * Static Method
     */
    fun show() {
        originShow("--------------------------------", 2)
    }

    fun show(message: String) {
        originShow(message, 2)
    }

    fun show(message: Int) {
        originShow(message.toString(), 2)
    }

    fun show(message: Boolean) {
        originShow(message.toString(), 2)
    }

    fun show(message: Float) {
        originShow(message.toString(), 2)
    }

    fun show(message: Double) {
        originShow(message.toString(), 2)
    }

    private fun originShow(message: String, trace: Int) {
        if (BuildConfig.DEBUG) {
            val mStringBuilder = StringBuilder()
            val stackTraceElement =
                Throwable().stackTrace[trace]
            mStringBuilder.append("(")
            val array =
                stackTraceElement.className.split("\\.").toTypedArray()
            var className = array[array.size - 1]
            var threadName: String? = null
            if (className.contains("$")) {
                className = className.split("\\$").toTypedArray()[0]
                threadName = Thread.currentThread().name
            }
            mStringBuilder.append(className).append(".java:")
            mStringBuilder.append(stackTraceElement.lineNumber).append("){")
            mStringBuilder.append(stackTraceElement.methodName).append("}")
            if (!TextUtils.isEmpty(threadName)) mStringBuilder.append("in ").append(threadName)
            mStringBuilder.append(": ").append(message)
            LogD(TAG, mStringBuilder.toString())
        }
    }

    interface Filter<T> {
        fun acceptCollect(message: T): Boolean
        val isLogContent: Boolean
        val eachCount: Int
    }

    private fun LogV(tag: String, msg: String) {
        val strLength = msg.length
        var start = 0
        var end = LOG_MAX_LENGTH
        for (i in 0..99) {
            //剩下的文本還是大於規定長度則繼續重複截取並輸出
            if (strLength > end) {
                Log.v(tag + i, msg.substring(start, end))
                start = end
                end = end + LOG_MAX_LENGTH
            } else {
                Log.v(tag, msg.substring(start, strLength))
                break
            }
        }
    }

    private fun LogD(tag: String, msg: String) {
        val strLength = msg.length
        var start = 0
        var end = LOG_MAX_LENGTH
        for (i in 0..99) {
            //剩下的文本還是大於規定長度則繼續重複截取並輸出
            if (strLength > end) {
                Log.d(tag + i, msg.substring(start, end))
                start = end
                end = end + LOG_MAX_LENGTH
            } else {
                Log.d(tag, msg.substring(start, strLength))
                break
            }
        }
    }

    private fun LogI(tag: String, msg: String) {
        val strLength = msg.length
        var start = 0
        var end = LOG_MAX_LENGTH
        for (i in 0..99) {
            //剩下的文本還是大於規定長度則繼續重複截取並輸出
            if (strLength > end) {
                Log.i(tag + i, msg.substring(start, end))
                start = end
                end = end + LOG_MAX_LENGTH
            } else {
                Log.i(tag, msg.substring(start, strLength))
                break
            }
        }
    }

    private fun LogW(tag: String, msg: String) {
        val strLength = msg.length
        var start = 0
        var end = LOG_MAX_LENGTH
        for (i in 0..99) {
            //剩下的文本還是大於規定長度則繼續重複截取並輸出
            if (strLength > end) {
                Log.w(tag + i, msg.substring(start, end))
                start = end
                end = end + LOG_MAX_LENGTH
            } else {
                Log.w(tag, msg.substring(start, strLength))
                break
            }
        }
    }

    private fun LogE(tag: String, msg: String) {
        val strLength = msg.length
        var start = 0
        var end = LOG_MAX_LENGTH
        for (i in 0..99) {
            //剩下的文本還是大於規定長度則繼續重複截取並輸出
            if (strLength > end) {
                Log.e(tag + i, msg.substring(start, end))
                start = end
                end = end + LOG_MAX_LENGTH
            } else {
                Log.e(tag, msg.substring(start, strLength))
                break
            }
        }
    }
}