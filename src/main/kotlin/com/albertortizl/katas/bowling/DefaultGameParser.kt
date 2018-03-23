package com.albertortizl.katas.bowling

private const val WHITE_SPACE = " "
private const val MISS = "-"
private const val ZERO = "0"
private val regexStrike = Regex("X")
private val regexSpare = Regex("([0-9])\\/")
private val regexOpenFrame = Regex("[0-9]{2}")

private fun asGame(frames: String): Game {
    val allFrames = frames
            .replace(MISS, ZERO)
            .split(WHITE_SPACE)
            .map({ frame ->
                when {
                    regexStrike.matches(frame) -> Strike
                    regexSpare.matches(frame) -> Spare(frame.substring(0..0).toInt())
                    regexOpenFrame.matches(frame) -> OpenFrame(
                            frame.substring(0..0).toInt(),
                            frame.substring(1).toInt()
                    )
                    else -> throw IllegalArgumentException("'$frame' is not a valid pattern")
                }
            })
    return Game(allFrames)
}


val defaultGameParser: GameParser = ::asGame
