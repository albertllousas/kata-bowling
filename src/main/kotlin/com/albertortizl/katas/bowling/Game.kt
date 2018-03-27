package com.albertortizl.katas.bowling

data class Game(val frames: List<Frame>, val total:Int? = null)

interface GameParser {
    fun parse(frames: String): Game
}





