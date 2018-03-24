package com.albertortizl.katas.bowling

data class Game(val frames: List<Frame>, val finalScore:Int?)

interface GameParser {
    fun parse(frames: String): Game
}





