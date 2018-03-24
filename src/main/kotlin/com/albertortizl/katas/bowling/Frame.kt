package com.albertortizl.katas.bowling

sealed class Frame
data class OpenFrame(val pinsFirstRoll: Int, val pinsSecondRoll: Int) : Frame()
data class Spare(val pinsFirstRoll: Int, val bonusBall: Int? = null) : Frame()
data class Strike(val firstBonusBall: Int? = null, val secondBonusBall: Int? = null) : Frame()

fun Frame.pinsKnockedDown(): Int =
        when (this) {
            is Strike -> 10 + (this.firstBonusBall ?: 0) + (this.secondBonusBall ?: 0)
            is Spare -> 10 + (this.bonusBall ?: 0)
            is OpenFrame -> this.pinsFirstRoll + this.pinsSecondRoll
        }


