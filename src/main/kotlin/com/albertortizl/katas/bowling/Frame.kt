package com.albertortizl.katas.bowling

sealed class Frame {
    companion object
}
data class OpenFrame(val pinsFirstRoll: Int, val pinsSecondRoll: Int) : Frame()
data class Spare(val pinsFirstRoll: Int) : Frame()
object Strike : Frame()
data class Tenth(val frame: Frame, val firstExtraBall: Int? = null, val secondExtraBall: Int? = null) : Frame()

fun Frame.Companion.fromRolls(firstRoll: Int, secondRoll: Int? = null, thirdRoll: Int? = null): Frame =
        when {
            firstRoll == 10 && thirdRoll != null ->
                Tenth(frame = Strike, firstExtraBall = secondRoll, secondExtraBall = thirdRoll)
            thirdRoll != null ->
                Tenth(frame = fromRolls(firstRoll, secondRoll), firstExtraBall = thirdRoll)
            firstRoll == 10 -> Strike
            firstRoll + (secondRoll ?: 0) == 10 -> Spare(firstRoll)
            else -> OpenFrame(firstRoll, secondRoll ?: 0)
        }

fun Frame.pinsOfFirstRoll(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> this.pinsFirstRoll
            is OpenFrame -> this.pinsFirstRoll
            is Tenth -> this.frame.pinsOfFirstRoll()
        }

fun Frame.totalOfPins(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> 10
            is OpenFrame -> this.pinsFirstRoll + this.pinsSecondRoll
            is Tenth -> this.frame.totalOfPins() + (this.firstExtraBall ?: 0) + (this.secondExtraBall ?: 0)
        }


