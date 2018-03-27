package com.albertortizl.katas.bowling

sealed class Frame
data class OpenFrame(val pinsFirstRoll: Int, val pinsSecondRoll: Int) : Frame()
data class Spare(val pinsFirstRoll: Int) : Frame()
object Strike : Frame() {
    override fun toString() = "Strike"
}
data class LastFrame(val frame: Frame, val firstExtraBall: Int? = null, val secondExtraBall: Int? = null) : Frame()


fun Frame.pinsOfFirstRoll(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> this.pinsFirstRoll
            is OpenFrame -> this.pinsFirstRoll
            is LastFrame -> this.frame.pinsOfFirstRoll()
        }

fun Frame.totalOfPins(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> 10
            is OpenFrame -> this.pinsFirstRoll + this.pinsSecondRoll
            is LastFrame -> this.frame.totalOfPins() + (this.firstExtraBall ?: 0) + (this.secondExtraBall ?: 0)
        }


