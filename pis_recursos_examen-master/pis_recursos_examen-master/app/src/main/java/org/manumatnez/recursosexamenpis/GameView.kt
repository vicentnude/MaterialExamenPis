package org.manumatnez.recursosexamenpis

import android.graphics.Canvas
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

var widthGame = 0
var heightGame = 0

const val UP = 0
const val DOWN = 1
const val LEFT = 2
const val RIGHT = 3

var movement = LEFT

var pause = false
var end_game = false

class GameView(snake: SnakeActivity) : SurfaceView(snake.applicationContext), SurfaceHolder.Callback {

    private val TAG = javaClass.simpleName

    private var engine: GameEngine
    private var thread: GameThread

    init {
        pause = false
        end_game = false

        holder.addCallback(this)

        engine = GameEngine(snake)
        thread = GameThread(this, engine)
    }

    override fun surfaceChanged(surfHolder: SurfaceHolder, format: Int, w: Int, h: Int) {
        widthGame = w
        heightGame = h
    }

    override fun surfaceDestroyed(surfHolder: SurfaceHolder?) {
        while (thread.isAlive) {
            try {
                thread.running = false
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun surfaceCreated(surfHolder: SurfaceHolder?) {
        // start the game thread
        if (thread.state == Thread.State.TERMINATED) {
            thread = GameThread(this, engine)
        }
        thread.running = true
        thread.start()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        engine.draw(canvas!!)
    }

    fun resumeThread() {
        pause = false
        surfaceCreated(holder)
    }

    fun pauseThread() {
        pause = true
    }

}