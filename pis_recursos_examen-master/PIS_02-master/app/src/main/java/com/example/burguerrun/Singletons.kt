package com.example.burguerrun

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import com.example.burguerrun.Engine.GameEngine
import com.example.burguerrun.Model.Database
import com.example.burguerrun.Model.Level
import com.example.burguerrun.Model.LevelTile
import com.example.burguerrun.Model.Player
import com.example.burguerrun.Presenter.*
import com.google.firebase.firestore.FirebaseFirestore



object Singletons {
    //declaration of every singleton instance in the app.
    val database = Database()
    val mainPresenter = MainPresenter()
    val historiaPresenter = HistoriaPresenter()
    val loginPresenter = LoginPresenter()
    val lvlPresenter = LvlPresenter()
    val registerPresenter = RegisterPresenter()
    val settingsPresenter = SettingsPresenter()
    val levelsPresenter = LevelsPresenter()
    val rankingPresenter = RankingPresenter()
    val selectRankingPresenter = SelectRankingPresenter()


    var notification = true
    var connection = false
    lateinit var audio: AudioManager
    val db = FirebaseFirestore.getInstance()
    var scale:Float? = null
    var level: Level = Level()
    lateinit var plyr: Player // = com.example.burguerrun.Model.Player(currentTile=createTile(8,15,"s"), 24)

}
