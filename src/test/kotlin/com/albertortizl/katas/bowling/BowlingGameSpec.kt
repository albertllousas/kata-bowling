package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object BowlingGameSpec : Spek({


    given("a bowling game") {
        val bowlingGame = BowlingGame()
        on("score") {
            val score = bowlingGame.score("")
            it("should be 0 when all are misses") {
                score `should be` 0
            }
        }

    }
})