package com.bw.a2307a1

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val PREF_NAME = "app_preferences"
    private const val KEY_PRIVACY_AGREED = "privacy_agreed"
    private const val KEY_FIRST_LAUNCH = "first_launch"
    private const val KEY_TEEN_MODE_SHOWN = "teen_mode_shown"
    private const val KEY_TEEN_MODE_ENABLED = "teen_mode_enabled"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // 是否已同意隐私协议
    fun isPrivacyAgreed(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_PRIVACY_AGREED, false)
    }

    // 设置已同意隐私协议
    fun setPrivacyAgreed(context: Context, agreed: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_PRIVACY_AGREED, agreed).apply()
    }

    // 是否是首次启动
    fun isFirstLaunch(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_FIRST_LAUNCH, true)
    }

    // 设置首次启动状态
    fun setFirstLaunch(context: Context, isFirst: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_FIRST_LAUNCH, isFirst).apply()
    }

    // 是否已显示过青少年模式提示
    fun isTeenModeShown(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_TEEN_MODE_SHOWN, false)
    }

    // 设置青少年模式提示已显示
    fun setTeenModeShown(context: Context, shown: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_TEEN_MODE_SHOWN, shown).apply()
    }

    // 是否已开启青少年模式
    fun isTeenModeEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_TEEN_MODE_ENABLED, false)
    }

    // 设置青少年模式开启状态
    fun setTeenModeEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_TEEN_MODE_ENABLED, enabled).apply()
    }
}
