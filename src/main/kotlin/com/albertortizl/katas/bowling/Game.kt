package com.albertortizl.katas.bowling

data class Game(var frames: List<Frame>)

interface GameParser {
    fun parse(frames: String): Game
}





