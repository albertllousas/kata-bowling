package com.albertortizl.katas.bowling

class AmericanTenPinBowling(
        private val gameParser: GameParser = DefaultGameParser,
        private val score: (Game) -> Int = Score::score
) {

    infix fun totalScore(frames: String): Int {
        val game: Game = gameParser.parse(frames)
        return score(game)
    }
}