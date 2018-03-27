package com.albertortizl.katas.bowling

import java.lang.Character.getNumericValue

object DefaultGameParser : GameParser {

    private const val WHITE_SPACE = " "
    private const val MISS = "-"
    private const val STRIKE = "X"
    private const val SPARE = "/"
    private val regexFrame= Regex("[-0-9X][-0-9X/]{0,2}")


    override fun parse(frames: String): Game {
        val allFrames = frames.split(WHITE_SPACE).map(::asFrame)
        return Game(allFrames)
    }

    fun asFrame(frame: String): Frame {
        require(regexFrame.matches(frame)) { "Invalid frame '$frame'" }
        val firstRoll = frame[0].toNumberOfPins()
        val secondRoll = frame.getOrNull(1)?.toNumberOfPins(frame[0])
        val thirdRoll = frame.getOrNull(2)?.toNumberOfPins(frame.getOrNull(1))
        return Frame.fromRolls(firstRoll, secondRoll, thirdRoll)
    }

    private fun Char.toNumberOfPins(previous: Char? = null): Int =
            when (this) {
                STRIKE.single() -> 10
                MISS.single() -> 0
                SPARE.single() -> (10 - (previous?.toNumberOfPins() ?: 0))
                else -> getNumericValue(this)
            }


}
