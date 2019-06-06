package org.manumatnez.recursosexamenpis

import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder

class GameThread(view: GameView, engine: GameEngine) : Thread() {

    private val gameView: GameView = view
    private val gameEngine: GameEngine = engine

    private var surfaceHolder: SurfaceHolder = view.holder
    var running: Boolean = false
    private val targetFPS = 30

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / targetFPS).toLong()

        gameEngine.initGame()

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            if (pause) {
                running = false
            }

            if (end_game) {
                running = false
                gameEngine.endGame()
            }

            try {
                // locking the canvas allows us to draw on to it
                canvas = surfaceHolder.lockCanvas()

                synchronized(surfaceHolder) {
                    if (surfaceHolder.surface.isValid) {
                        gameView.draw(canvas)
                    }
                    gameEngine.update()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            if (waitTime > 0) {
                sleep(waitTime)
            }
        }
    }

    companion object {
        private var canvas: Canvas? = null
    }

}