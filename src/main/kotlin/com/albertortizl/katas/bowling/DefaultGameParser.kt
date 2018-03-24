package com.albertortizl.katas.bowling

object DefaultGameParser: GameParser{

    private const val WHITE_SPACE = " "
    private const val MISS = "-"
    private const val ZERO = "0"
    private val regexStrike = Regex("X")
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
                        else -> throw IllegalArgumentException("'$frame' is not a valid pattern")
                    }
                })
        return Game(allFrames, null)
    }

    fun asSpare(spare:String): Spare = Spare(spare.substring(0..0).toInt())

    fun asOpenFrame(openFrame:String): OpenFrame = OpenFrame(
            openFrame.substring(0..0).toInt(),
            openFrame.substring(1).toInt()
    )

}
