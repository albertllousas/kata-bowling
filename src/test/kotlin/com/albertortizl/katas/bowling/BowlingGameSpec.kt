package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object BowlingGameSpec : Spek({

    given("a bowling game") {

        val bowlingGame = BowlingGame()

        on("score") {
            it("should return 0 when all the frames are misses") {
                val score = bowlingGame score ""
                score `should be` 0
            }
            it("should return the max punctuation when all the frames are strikes") {
                val score = bowlingGame score "X X X X X X X X X X X X"
                score `should be` 300
            }
        }

    }
})