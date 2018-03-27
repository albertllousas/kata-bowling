package com.albertortizl.katas.bowling

data class Game(val frames: List<Frame>, val total:Int?)

interface GameParser {
    fun parse(frames: String): Game
}





