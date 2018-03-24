package com.albertortizl.katas.bowling

sealed class Frame
data class OpenFrame(val pinsFirstRoll: Int, val pinsSecondRoll: Int) : Frame()
data class Spare(val pinsFirstRoll: Int) : Frame()
object Strike : Frame() {
    override fun toString() = "Strike"
}

fun Frame.pinsKnockedDown(): Int =
        when (this) {
            is Strike -> 10
            is Spare -> this.pinsFirstRoll
            is OpenFrame -> this.pinsFirstRoll + this.pinsSecondRoll
        }


