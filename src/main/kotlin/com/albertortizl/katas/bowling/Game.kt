package com.albertortizl.katas.bowling

data class Game(val frames: List<Frame>)

interface GameParser {
    fun parse(frames: String): Game
}





