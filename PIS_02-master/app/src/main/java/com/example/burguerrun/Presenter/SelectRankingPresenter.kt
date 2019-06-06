package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.example.burguerrun.View.RankingActivity

class SelectRankingPresenter:Presenter {
    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt: Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity

    override fun setView(v: View){
        this.v = v;
    }

    override fun setActivity(activity: Activity) {
        this.actv = activity
    }

    override fun setFragmentActivity(fragmentActivity: FragmentActivity) {
        this.fragact = fragmentActivity
    }

    override fun setContext(context: Context) {
        this.ctxt = context
    }

    override fun showMessage(text:String) {
        Toast.makeText(ctxt, text, Toast.LENGTH_LONG).show()
    }

    fun openRanking(lvl:String){
        val intent = Intent(fragact, RankingActivity::class.java)
        intent.putExtra("ranking",lvl)
        ctxt.startActivity(intent)

    }
}