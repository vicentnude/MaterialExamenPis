package com.example.burguerrun.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class RankingActivity : AppCompatActivity() {

    val rankingPresenter = Singletons.rankingPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        rankingPresenter.setView(window.decorView.rootView)
        rankingPresenter.setActivity(this)
        rankingPresenter.setContext(this)
        rankingPresenter.createList(intent.getStringExtra("ranking").toInt())


    }

    override fun onBackPressed() {
        finish()
        rankingPresenter.update()
    }


}
