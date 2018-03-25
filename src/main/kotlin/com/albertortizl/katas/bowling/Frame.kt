package com.albertortizl.katas.bowling

sealed class Frame
data class OpenFrame(val pinsFirstRoll: Int, val pinsSecondRoll: Int) : Frame()
data class Spare(val pinsFirstRoll: Int) : Frame()
object Strike : Frame() {
    override fun toString() = "Strike"
}
data class BonusBall(val pins: Int) : Frame()


fun Frame.pinsKnockedDownOnFirstRoll(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> this.pinsFirstRoll
            is OpenFrame -> this.pinsFirstRoll
            is BonusBall -> this.pins
        }

fun Frame.totalOfPinsKnockedDown(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> 10
            is OpenFrame -> this.pinsFirstRoll + this.pinsSecondRoll
            is BonusBall -> this.pins
        }


