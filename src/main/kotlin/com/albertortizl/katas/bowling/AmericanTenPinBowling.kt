package com.albertortizl.katas.bowling

class AmericanTenPinBowling(private val gameParser: GameParser = DefaultGameParser) {

    infix fun score(frames: String): Int {
        val game: Game = gameParser.parse(frames)
        return score(game).total ?: 0
    }
}