package org.manumatnez.recursosexamenpis.game

import android.graphics.Canvas

const val IMG_ASSETS = "img"

interface GameChar {
    fun update()
    fun draw(canvas: Canvas)
}