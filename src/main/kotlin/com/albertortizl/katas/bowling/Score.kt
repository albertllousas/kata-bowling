package com.albertortizl.katas.bowling

internal fun score(openFrame:OpenFrame) = openFrame.pinsKnockedDown()

internal fun score(spare:Spare, nextFrame: Frame) =  10 + nextFrame.pinsKnockedDown()

internal fun score(game: Game): Int {

    return 0
}
