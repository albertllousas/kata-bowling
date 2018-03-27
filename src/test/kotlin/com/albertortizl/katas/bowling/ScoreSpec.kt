package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object ScoreParserSpec : Spek({


    given("a single frame score calculation") {

        val scoreFrame: (Frame, Frame?, Frame?) -> Int = ::scoreFrame

        it("should score an open frame") {
            scoreFrame(OpenFrame(1, 6), null, null) `should be equal to` 7
        }

        it("should score a strike when the next two rolls form a spare") {
            scoreFrame(Strike, Spare(1), null) `should be equal to` 20
        }

        it("should score a strike when the next two rolls are strikes too") {
            scoreFrame(Strike, Strike, Strike) `should be equal to` 30
        }

        it("should score a strike when the next two rolls are strikes in the tenth frame") {
            scoreFrame(Strike, LastFrame(Strike,10,2), null) `should be equal to` 30
        }

        it("should score a strike when the next two rolls form a spare in the tenth frame") {
            scoreFrame(Strike, LastFrame(Spare(2),1,null), null) `should be equal to` 20
        }

        it("should score the last frame with an strike when the next two rolls are strikes") {
            scoreFrame(LastFrame(Strike,10,10), null, null) `should be equal to` 30
        }

        it("should score the last frame  when the next two rolls form a spare") {
            scoreFrame(LastFrame(Strike,1,9), null, null) `should be equal to` 20
        }

        //añadir esto como integration

        it("scores a spare") {
            scoreFrame(Spare(2), OpenFrame(1, 6), Strike) `should be equal to` 11
        }

    }

    given("a full game score function") {

        val score: (Game) -> Game = ::score

        it("should score a game with 12 strikes in a row") {
            val game = Game((1..10).toList().map { Strike }, null)
            score(game).total!! `should be equal to` 300

        }


        //validator game falta

        it("should score a game with scores 11 spares in a row") {
            val game = Game((1..10).toList().map { Spare(5) }, null)
            score(game).total!! `should be equal to` 150

        }
    }
})