package com.albertortizl.katas.bowling

const val TEN = 10

fun scoreFrame(currentFrame: Frame, nextFrame: Frame?, nextOfTheFollowing: Frame?): Int {
    val (firstNextRoll, secondNextRoll) = getNextTwoRolls(nextFrame, nextOfTheFollowing)
    return when (currentFrame) {
        is Strike -> TEN + firstNextRoll + secondNextRoll
        is Spare -> TEN + firstNextRoll
        is OpenFrame, is Tenth -> currentFrame.totalOfPins()

    }
}

private fun getNextTwoRolls(nextFrame: Frame?, nextOfTheFollowing: Frame?): Pair<Int, Int> =
        when (nextFrame) {
            is Strike? -> Pair(10, nextOfTheFollowing?.pinsOfFirstRoll() ?: 0)
            is OpenFrame? -> Pair(nextFrame?.pinsFirstRoll ?: 0, nextFrame?.pinsSecondRoll ?: 0)
            is Spare? -> Pair(nextFrame?.pinsFirstRoll ?: 0, 10 - (nextFrame?.pinsFirstRoll ?: 0))
            is Tenth? -> getNextTwoRolls(nextFrame?.frame, Frame.fromRolls(nextFrame?.firstExtraBall ?: 0, 0))
            null -> Pair(0, 0)
        }


fun score(game: Game): Int {
    require(game.frames.size == TEN) { "Invalid number of frames, was ${game.frames.size} and must be 10" }
    return score(game.frames, 1, 0)
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




