package com.albertortizl.katas.bowling


fun scoreFrame(currentFrame: Frame, nextFrame: Frame?, nextOfTheFollowing: Frame?): Int =
        when (currentFrame) {
            is Strike -> scoreStrike(nextFrame, nextOfTheFollowing)
            is Spare -> nextFrame?.let { 10 + nextFrame.pinsKnockedDownOnFirstRoll() } ?: 0
            is OpenFrame, is BonusBall -> currentFrame.totalOfPinsKnockedDown()

        }

private fun scoreStrike(nextFrame: Frame?, nextOfTheFollowing: Frame?): Int =
        when (nextFrame) {
            is Strike?, is BonusBall? ->
                10 + (nextFrame?.totalOfPinsKnockedDown() ?: 0) + (nextOfTheFollowing?.pinsKnockedDownOnFirstRoll() ?: 0)
            else -> 10 + (nextFrame?.pinsKnockedDownOnFirstRoll() ?: 0)
        }


fun score(game: Game): Game {
    require(game.hasValidNumberOfFrames()) { "Invalid number of frames, was ${game.frames.size}" }
    return Game(frames = game.frames.toList(), finalScore = score(game.frames, 1, 0))
}


private fun score(rest: List<Frame>, turn: Int, accumulator: Int): Int =
        when {
            rest.isEmpty() || turn > 10 -> accumulator
            else -> {
                val tail = rest.drop(1)
                val newAccumulator = accumulator + scoreFrame(rest.first(), rest.getOrNull(1), rest.getOrNull(2))
                score(tail, turn.inc(), newAccumulator)
            }
        }




