package com.albertortizl.katas.bowling

internal fun scoreOpenFrame(openFrame:OpenFrame) = openFrame.pinsKnockedDown()

internal fun scoreSpare(nextFrame: Frame) =  10 + nextFrame.pinsKnockedDown()

internal fun scoreStrike(nextFrame: Frame, nextOfTheFollowing:Frame) =
        10 + nextFrame.pinsKnockedDown() + nextOfTheFollowing.pinsKnockedDown()

internal fun score(game: Game): Int {

    return 0
}
