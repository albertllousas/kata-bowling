package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object ScoreParserSpec : Spek({

    given("a single open frame score calculation") {

        val score: (OpenFrame) -> Int = ::scoreOpenFrame

        it("scores as the total number of pins knocked down in his two tries") {
            score(OpenFrame(1, 6)) `should be equal to` 7
        }
    }

    given("a single spare score calculation") {

        val score: (Frame) -> Int = ::scoreSpare

        it("scores as ten plus the number of pins knocked down on his next turn") {
            score( OpenFrame(1,1)) `should be equal to` 12
            score( Strike) `should be equal to` 20
            score(Spare(5)) `should be equal to` 20
        }
    }

    given("a single strike score calculation") {

        val score: (Frame, Frame) -> Int = ::scoreStrike

        it("scores as ten plus the number of pins knocked down on his next two turns") {
            score(Spare(4), OpenFrame(1,1)) `should be equal to` 22
            score(Strike, Strike) `should be equal to` 30
            score(Spare(4), Spare(5)) `should be equal to` 30
        }
    }

    given("a generic frame score calculation with the frame to score and the following ones") {

        val score: (Frame, Frame, Frame) -> Int = ::scoreFrame

        it("scores an open frame") {
            score(OpenFrame(1, 6), Spare(1), Strike) `should be equal to` 7
        }

        it("scores a strike") {
            score(Strike, Spare(1), Strike) `should be equal to` 30
        }

        it("scores a spare") {
            score(Spare(2), OpenFrame(1, 6), Strike) `should be equal to` 17
        }
    }

    given("a full game score function") {

        val score: (Game) -> Game = ::score

        it("scores 12 strikes in a row") {
            val game = Game((1..12).toList().map { Strike }, null)
            score(game).finalScore!! `should be equal to` 300

        }

        it("scores 11 spares in a row") {
            val game = Game((1..11).toList().map { Spare(9) }, null)
            score(game).finalScore!! `should be equal to` 150

        }
    }
})