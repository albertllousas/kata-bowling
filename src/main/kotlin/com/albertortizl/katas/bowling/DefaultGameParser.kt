package com.albertortizl.katas.bowling

private const val WHITE_SPACE = " "
private const val MISS = "-"
private const val ZERO = "0"
private const val STRIKE = "X"
private val regexSpare = Regex("[0-7]\\/")

private fun asGame(frames: String): Game {
    val allFrames = frames
            .replace(MISS, ZERO)
            .split(WHITE_SPACE)
            .map({ frame ->
                when {
                    frame == STRIKE -> Strike
                    regexSpare.matches(frame) -> Spare(frame.substring(0..0).toInt())
                    else -> throw IllegalArgumentException("'$frame' is not a valid pattern")
                }
            })
    return Game(allFrames)
}


val defaultGameParser: GameParser = ::asGame
