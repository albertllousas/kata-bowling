package com.albertortizl.katas.bowling


internal fun scoreOpenFrame(openFrame: OpenFrame): Int = openFrame.pinsKnockedDown()

internal fun scoreSpare(nextFrame: Frame): Int = 10 + nextFrame.pinsKnockedDown()

internal fun scoreStrike(nextFrame: Frame, nextOfTheFollowing: Frame): Int =
        10 + nextFrame.pinsKnockedDown() + nextOfTheFollowing.pinsKnockedDown()

internal fun scoreFrame(currentFrame: Frame, nextFrame: Frame?, nextOfTheFollowing: Frame?): Int =
        when (currentFrame) {
            is Strike ->
                if (nextFrame != null && nextOfTheFollowing != null) scoreStrike(nextFrame, nextOfTheFollowing) else 0
            is Spare -> nextFrame?.let { scoreSpare(it) } ?: 0
            is OpenFrame -> scoreOpenFrame(currentFrame)
        }


fun score(game: Game): Game {
    require(game.hasValidNumberOfFrames()) { "Invalid number of frames, was ${game.frames.size}" }
    val finalScore = score(game.frames, 0)
    return Game(frames = game.frames.toList(), finalScore = finalScore)
}


tailrec private fun score(rest: List<Frame>, accumulator: Int): Int =
        when {
            rest.isEmpty() -> accumulator
            else -> {
                val current: Frame = rest.first()
                val next: Frame? = rest.getOrNull(1)
                val nextOfTheFollowing: Frame? = rest.getOrNull(2)
                val tail = rest.drop(1)
                val newAccumulator = accumulator + scoreFrame(current, next, nextOfTheFollowing)
                score(tail, newAccumulator)
            }
        }




