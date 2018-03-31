package com.albertortizl.katas.bowling

const val ALL_PINS = 10
const val ZERO_PINS = 0

sealed class Frame {
    companion object
}

data class OpenFrame(val pinsFirstRoll: Int, val pinsSecondRoll: Int) : Frame()
data class Spare(val pinsFirstRoll: Int) : Frame()
object Strike : Frame()
data class Tenth(val frame: Frame, val firstExtraBall: Int? = null, val secondExtraBall: Int? = null) : Frame()

fun Frame.Companion.fromRolls(firstRoll: Int, secondRoll: Int? = null, thirdRoll: Int? = null): Frame =
        when {
            firstRoll == ALL_PINS && thirdRoll != null ->
                Tenth(frame = Strike, firstExtraBall = secondRoll, secondExtraBall = thirdRoll)
            thirdRoll != null ->
                Tenth(frame = fromRolls(firstRoll, secondRoll), firstExtraBall = thirdRoll)
            firstRoll == ALL_PINS -> Strike
            firstRoll + (secondRoll ?: ZERO_PINS) == ALL_PINS -> Spare(firstRoll)
            else -> OpenFrame(firstRoll, secondRoll ?: ZERO_PINS)
        }

fun Frame.pinsOfFirstRoll(): Int =
        when (this) {
            is Strike -> ALL_PINS
            is Spare -> this.pinsFirstRoll
            is OpenFrame -> this.pinsFirstRoll
            is Tenth -> this.frame.pinsOfFirstRoll()
        }

fun Frame.totalOfPins(): Int =
        when (this) {
            is Strike -> ALL_PINS
            is Spare -> ALL_PINS
            is OpenFrame -> this.pinsFirstRoll + this.pinsSecondRoll
            is Tenth -> this.frame.totalOfPins() + (this.firstExtraBall ?: ZERO_PINS) + (this.secondExtraBall ?: ZERO_PINS)
        }


