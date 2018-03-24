package com.albertortizl.katas.bowling

internal fun scoreOpenFrame(openFrame: OpenFrame): Int = openFrame.pinsKnockedDown()

internal fun scoreSpare(nextFrame: Frame): Int = 10 + nextFrame.pinsKnockedDown()

internal fun scoreStrike(nextFrame: Frame, nextOfTheFollowing: Frame): Int =
        10 + nextFrame.pinsKnockedDown() + nextOfTheFollowing.pinsKnockedDown()

internal fun score(currentFrame: Frame, nextFrame: Frame, nextOfTheFollowing: Frame): Int =
        when (currentFrame) {
            is Strike -> scoreStrike(nextFrame, nextOfTheFollowing)
            is Spare -> scoreSpare(nextFrame)
            is OpenFrame -> scoreOpenFrame(currentFrame)
        }


fun score(game: Game): Int {

    return 0
}
