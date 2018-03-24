package com.albertortizl.katas.bowling

data class Game(val frames: List<Frame>, val finalScore:Int?) {
    fun hasValidNumberOfFrames() = frames.size == 10 || frames.size == 11 || frames.size == 12
}

interface GameParser {
    fun parse(frames: String): Game
}





