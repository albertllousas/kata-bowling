package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object AmericanTenPinBowlingSpec : Spek({

    given("a bowling game") {

        val bowlingGame = AmericanTenPinBowling()

        on("score") {
            it("should return the max punctuation when all the frames are strikes") {
                val score = bowlingGame score "X X X X X X X X X X X X"
                score `should be equal to` 300
            }
        }

    }
})