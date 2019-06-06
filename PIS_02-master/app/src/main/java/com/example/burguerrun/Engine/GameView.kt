package com.example.burguerrun.Engine

import android.content.Context

import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView



class GameView(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private val gameEngine: GameEngine
    var thread: GameThread? = null



    init {

        gameEngine = GameEngine(context,this)
        try {

            // Register our interest in hearing about changes to our surface
            holder.addCallback(this)

            thread = GameThread(holder, context, gameEngine)

            isFocusable = true // Make sure we get key events
            isFocusableInTouchMode = true// Make sure we get key events

        } catch (e: Exception) {
            //TODO Handle errors
        }

    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        stop()
    }

    fun stop() {
        // Stop the gaming thread
        //thread!!.isRunning = false
        try {
            thread!!.join(300)
            thread!!.setRunning(false)
        } catch (e: InterruptedException) {
        }

    }

    fun pause() {
        //thread!!.isPaused = true
    }

    fun resume() {
        //thread!!.isPaused = false
    }

    fun start() {
        //thread!!.isRunning = true
        thread!!.start()

        thread!!.run();//thread!!.start()
        thread!!.setRunning(true)
    }

    fun goRight() {
        gameEngine.goRight()
    }

    fun goDown() {
        gameEngine.goDown()
    }

    fun goLeft() {
        gameEngine.goLeft()
    }

    fun goUp() {
        gameEngine.goUp();
    }




}


/**
     * Everything that has to be drawn on Canvas
     */






