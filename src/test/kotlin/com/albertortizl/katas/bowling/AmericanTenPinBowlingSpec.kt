package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.spekframework.spek2.Spek

object AmericanTenPinBowlingSpec : Spek({

    group("a bowling game") {

        val bowlingGame = AmericanTenPinBowling()

        test("should return the max punctuation when 12 strikes in a row") {
            val totalScore = bowlingGame totalScore "X X X X X X X X X XXX"
            totalScore `should be equal to` 300
        }

        test("should calculate a game with 10 pairs of 5 and spare and a final 5") {
            val totalScore = bowlingGame totalScore "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
            totalScore `should be equal to` 150
        }

        test("should calculate an example of full miss game") {
            val totalScore = bowlingGame totalScore "-- -- -- -- -- -- -- -- -- --"
            totalScore `should be equal to` 0
        }

        test("should calculate a game with two extra balls in the tenth frame") {
            val totalScore = bowlingGame totalScore "X 3/ 6-1 X X X 2/ 9-0 7/ XXX"
            totalScore `should be equal to` 193
        }

        test("should calculate a game with one extra ball in the tenth frame") {
            val totalScore = bowlingGame totalScore "90 3/ 61 3/ 81 5/ 0/ 80 7/ 8/8"
            totalScore `should be equal to` 131
        }

        test("should calculate a game with two no extra balls in the tenth frame") {
            val totalScore = bowlingGame totalScore "90 35 61 36 81 53 25 80 71 81"
            totalScore `should be equal to` 82
        }

        test("should fail when something wrong happens") {
            val func = { bowlingGame totalScore "non valid frame" }
            func `should throw` Exception::class
        }
    }
})
