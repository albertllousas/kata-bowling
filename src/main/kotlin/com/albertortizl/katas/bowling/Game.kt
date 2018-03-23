package com.albertortizl.katas.bowling

data class Game(var frames: List<Frame>)

typealias GameParser = (String) -> Game

internal const val WHITE_SPACE = " "

internal fun asGame(frames: String): Game =
        Game(frames.split(WHITE_SPACE)
                .map({ str ->
                    when (str) {
                        "X" -> Strike
                        else -> throw IllegalArgumentException("'$str' is not a valid pattern")
                    }
                })
        )

val defaultGameParser: GameParser = ::asGame



