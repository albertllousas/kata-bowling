package com.albertortizl.katas.bowling

class BowlingGame(private val asGame:GameParser = defaultGameParser) {

    infix fun score(frames: String): Int {
        val game: Game = asGame(frames)
        return 0
    }
}