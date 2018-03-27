package com.albertortizl.katas.bowling

import java.lang.Character.getNumericValue

object DefaultGameParser : GameParser {

    private const val WHITE_SPACE = " "
    private const val MISS = "-"
    private const val STRIKE = "X"
    private const val SPARE = "/"
    private val regexStrike = Regex(STRIKE)
    private val regexSpare = Regex("[-0-9]\\/")
    private val regexOpenFrame = Regex("[-0-9]{2}")

    override fun parse(frames: String): Game {
        val last = frames.split(WHITE_SPACE).last()
        val rest = frames.split(WHITE_SPACE).dropLast(1)
        val allFrames = rest.map(::asFrame) + asLastFrame(last)
        return Game(allFrames, null)
    }

    fun asFrame(frame: String): Frame =
            when {
                regexStrike.matches(frame) -> Strike
                regexSpare.matches(frame) -> frame.toSpare()
                regexOpenFrame.matches(frame) -> frame.toOpenFrame()
                else -> throw IllegalArgumentException("'$frame' is not a valid pattern")
            }


    fun asLastFrame(frame: String): LastFrame =
            when {
                frame.startsWith(STRIKE) -> LastFrame(
                        Strike,
                        frame.getOrNull(1)?.toNumberOfPins(),
                        frame.getOrNull(2)?.toNumberOfPins(frame.getOrNull(1))
                )
                else -> LastFrame(asFrame(frame.substring(0..1)), frame.getOrNull(2)?.toNumberOfPins())
            }

    private fun Char.toNumberOfPins(previous: Char? = null): Int =
            when (this) {
                STRIKE.single() -> 10
                MISS.single() -> 0
                SPARE.single() -> (10 - (previous?.toNumberOfPins() ?: 0))
                else -> getNumericValue(this)
            }

    private fun String.toSpare() = Spare(this[0].toNumberOfPins())

    private fun String.toOpenFrame() = OpenFrame(this[0].toNumberOfPins(), this[1].toNumberOfPins())

}
