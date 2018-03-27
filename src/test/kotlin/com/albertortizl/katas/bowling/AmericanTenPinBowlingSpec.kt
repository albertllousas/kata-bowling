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

            it("should return the max punctuation when 12 strikes in a row") {
                val score = bowlingGame score "X X X X X X X X X X X X"
                score `should be equal to` 300
            }

            it("should calculate a game with 10 pairs of 5 and spare and a final 5") {
                val score = bowlingGame score "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
                score `should be equal to` 150
            }
        }

    }
})