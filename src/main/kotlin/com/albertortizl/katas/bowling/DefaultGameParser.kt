package com.albertortizl.katas.bowling

object DefaultGameParser : GameParser {

    private const val WHITE_SPACE = " "
    private const val MISS = "-"
    private const val ZERO = "0"
    private const val STRIKE = "X"
    private val regexBonusBall = Regex("[0-9]|$STRIKE")
    private val regexStrike = Regex(STRIKE)
    private val regexSpare = Regex("[0-9]\\/")
    private val regexOpenFrame = Regex("[0-9]{2}")

    override fun parse(frames: String): Game {
        val allFrames = frames
                .replace(MISS, ZERO)
                .split(WHITE_SPACE)
                .map({ frame ->
                    when {
                        regexStrike.matches(frame) -> Strike
                        regexSpare.matches(frame) -> asSpare(frame)
                        regexOpenFrame.matches(frame) -> asOpenFrame(frame)
                        regexBonusBall.matches(frame) -> asBonusBall(frame)
                        else -> throw IllegalArgumentException("'$frame' is not a valid pattern")
                    }
                })
        return Game(allFrames, null)
    }

    fun asBonusBall(bonusBall: String): BonusBall =
            when (bonusBall) {
                STRIKE -> BonusBall(10)
                else -> BonusBall(bonusBall.substring(0..0).toInt())
            }

    fun asSpare(spare: String): Spare = Spare(spare.substring(0..0).toInt())

    fun asOpenFrame(openFrame: String): OpenFrame = OpenFrame(
            openFrame.substring(0..0).toInt(),
            openFrame.substring(1).toInt()
    )

}
