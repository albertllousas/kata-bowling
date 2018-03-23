package com.albertortizl.katas.bowling

data class Game(var frames: List<Frame>)

typealias GameParser = (String) -> Game

internal fun asGame(frames:String):Game {
    return Game(emptyList())
}

val defaultGameParser: GameParser = ::asGame



