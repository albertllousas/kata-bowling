package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object ScoreParserSpec : Spek({


    given("a generic frame score calculation with the frame to score and the following ones") {

        val score: (Frame, Frame?, Frame?) -> Int = ::scoreFrame

        it("scores an open frame") {
            score(OpenFrame(1, 6), Spare(1), Strike) `should be equal to` 7
        }

        // add all combinations of an strike

        it("scores a strike") {
            score(Strike, Spare(1), Strike) `should be equal to` 11
        }

        it("scores a spare") {
            score(Spare(2), OpenFrame(1, 6), Strike) `should be equal to` 11
        }

        it("scores a bonus ball") {
            score(BonusBall(2), null, null) `should be equal to` 2
        }
    }

    given("a full game score function") {

        val score: (Game) -> Game = ::score

        it("scores 12 strikes in a row") {
            val game = Game((1..12).toList().map { Strike }, null)
            score(game).finalScore!! `should be equal to` 300

        }

        it("scores 11 spares in a row") {
            val game = Game((1..11).toList().map { Spare(5) }, null)
            score(game).finalScore!! `should be equal to` 150

        }
    }
})