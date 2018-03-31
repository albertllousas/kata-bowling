package com.albertortizl.katas.bowling

const val TEN = 10
const val ZERO = 0

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
            is Strike? -> Pair(TEN, nextOfTheFollowing?.pinsOfFirstRoll() ?: ZERO)
            is OpenFrame? -> Pair(nextFrame?.pinsFirstRoll ?: ZERO, nextFrame?.pinsSecondRoll ?: ZERO)
            is Spare? -> Pair(nextFrame?.pinsFirstRoll ?: ZERO, TEN - (nextFrame?.pinsFirstRoll ?: ZERO))
            is Tenth? -> getNextTwoRolls(nextFrame?.frame, Frame.fromRolls(nextFrame?.firstExtraBall ?: ZERO, ZERO))
            null -> Pair(ZERO, ZERO)
        }


fun score(game: Game): Int {
    require(game.frames.size == TEN) { "Invalid number of frames, was ${game.frames.size} and must be 10" }
    return score(game.frames, 1, ZERO)
}


private tailrec fun score(frames: List<Frame>, turn: Int, accumulator: Int): Int =
        when {
            frames.isEmpty() || turn > TEN -> accumulator
            else -> {
                val tail = frames.drop(1)
                val newAccumulator = accumulator + scoreFrame(frames.first(), frames.getOrNull(1), frames.getOrNull(2))
                score(tail, turn.inc(), newAccumulator)
            }
        }




