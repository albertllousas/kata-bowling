package com.albertortizl.katas.bowling

import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object BowlingGameSpec : Spek({


    describe("bowling game") {

        on("create an instance of a bowling game") {
            val bowlingGame = BowlingGame()
            it("should be not null") {
                bowlingGame `should not be` null
            }
        }

    }
})