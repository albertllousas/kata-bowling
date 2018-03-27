package com.albertortizl.katas.bowling

const val TEN = 10

fun scoreFrame(currentFrame: Frame, nextFrame: Frame?, nextOfTheFollowing: Frame?): Int =
        when (currentFrame) {
            is Strike -> scoreStrike(nextFrame, nextOfTheFollowing)
            is Spare -> TEN + (nextFrame?.pinsOfFirstRoll() ?: 0)
            is OpenFrame, is LastFrame -> currentFrame.totalOfPins()

        }

private fun scoreStrike(nextFrame: Frame?, nextOfTheFollowing: Frame?): Int =
        when  {
            nextFrame is LastFrame? && nextFrame?.frame is Strike -> TEN + TEN + (nextFrame?.firstExtraBall?:0)
            nextFrame is Strike? -> TEN + TEN + (nextOfTheFollowing?.pinsOfFirstRoll() ?: 0)
            else -> TEN + (nextFrame?.totalOfPins() ?:0)
        }


fun score(game: Game): Game {
    require(game.frames.size == TEN) { "Invalid number of frames, was ${game.frames.size}" }
    return Game(game.frames.toList(), score(game.frames, 1, 0))
}


private fun score(rest: List<Frame>, turn: Int, accumulator: Int): Int =
        when {
            rest.isEmpty() || turn > TEN -> accumulator
            else -> {
                val tail = rest.drop(1)
                val newAccumulator = accumulator + scoreFrame(rest.first(), rest.getOrNull(1), rest.getOrNull(2))
                score(tail, turn.inc(), newAccumulator)
            }
        }




