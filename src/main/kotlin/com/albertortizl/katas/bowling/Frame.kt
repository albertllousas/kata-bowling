package com.albertortizl.katas.bowling

sealed class Frame
data class OpenFrame(val firstPins: Int, val secondPins: Int) : Frame()
data class Spare(val pins: Int) : Frame()
object Strike : Frame() {
    override fun toString() = "Strike"
}
