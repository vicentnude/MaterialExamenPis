package com.example.burguerrun.View


import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.content.Intent
import android.icu.util.Calendar
import android.service.notification.NotificationListenerService
import android.support.v4.content.ContextCompat.startForegroundService


class SettingsFragment: PreferenceFragmentCompat() {

    val settingsPresenter = Singletons.settingsPresenter
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        settingsPresenter.setView(activity!!.window.decorView.rootView)
        settingsPresenter.setFragmentActivity(activity!!)
        settingsPresenter.setContext(context!!)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        var key = preference!!.key
        var state = preferenceManager.sharedPreferences.getBoolean(key,false)
        when(key) {

            "So" -> {

                if (state) {
                    settingsPresenter.unmmute()

                }
                else {
                    settingsPresenter.mute()
                }
            }

            "Vibració" -> {//Mejorable/No se si funciona

                if (state) {
                    settingsPresenter.vibrationOn()
                }
                else {
                    settingsPresenter.vibrationOff()
                }
            }

            "Notificacions" -> {
                if (state) {
                    Singletons.notification = false
                    settingsPresenter.showMessage("Notificacions activades")

                }
                else {
                    Singletons.notification = true
                    settingsPresenter.showMessage("Notificacions desactivades")
                }
            }

            "4" -> {
                settingsPresenter.showMessage("Segueix-me a Github: @Yusepp!")
            }

            "5" -> {
                settingsPresenter.showMessage("Segueix-nos a Github!:")
            }

            "6" -> {
                settingsPresenter.showMessage("Segueix-nos a Github!:")
            }

            "7" -> {
                settingsPresenter.showMessage("Segueix-nos a Github!:")
            }
        }
        return true
    }



}