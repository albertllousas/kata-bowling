package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.`with message`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object AmericanTenPinBowlingSpec : Spek({

    given("a bowling game") {

        val bowlingGame = AmericanTenPinBowling()

        on("score") {

            it("should return the max punctuation when 12 strikes in a row") {
                val score = bowlingGame score "X X X X X X X X X XXX"
                score `should be equal to` 300
            }

            it("should calculate a game with 10 pairs of 5 and spare and a final 5") {
                val score = bowlingGame score "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
                score `should be equal to` 150
            }


            it("should calculate an example of full miss game") {
                val score = bowlingGame score "-- -- -- -- -- -- -- -- -- --"
                score `should be equal to` 0
            }

            it("should calculate a game with two extra balls in the tenth frame") {
                val score = bowlingGame score "X 3/ 6-1 X X X 2/ 9-0 7/ XXX"
                score `should be equal to` 193
            }

            it("should calculate a game with one extra ball in the tenth frame") {
                val score = bowlingGame score "90 3/ 61 3/ 81 5/ 0/ 80 7/ 8/8"
                score `should be equal to` 131
            }

            it("should calculate a game with two no extra balls in the tenth frame") {
                val score = bowlingGame score "90 35 61 36 81 53 25 80 71 81"
                score `should be equal to` 82
            }

            it("should fail calculating an invalid game") {
                val func = { bowlingGame score "non valid frame" }
                func `should throw` IllegalArgumentException::class `with message` "Invalid frame 'non'"
            }

            it("should fail calculating a game with non valid number of frames") {
                val func = { bowlingGame score "90 35 61 36 81 53 25 80 71 81 99" }
                func `should throw` IllegalArgumentException::class `with message` "Invalid number of frames, was 11 and must be 10"
            }
        }

    }
})