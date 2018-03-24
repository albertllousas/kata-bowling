package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object ScoreParserSpec : Spek({

    given("a open frame score calculation") {

        val score: (OpenFrame) -> Int = ::score

        it("scores as the total number of pins knocked down in his two tries") {
            score(OpenFrame(1, 6)) `should be equal to` 7
        }
    }

    given("a spare score calculation") {

        val score: (Spare, Frame) -> Int = ::score

        it("scores as ten plus the number of pins knocked down on his next throw") {
            score(Spare(4), OpenFrame(1,1)) `should be equal to` 12
            score(Spare(4), Strike) `should be equal to` 20
            score(Spare(4), Spare(5)) `should be equal to` 15
        }
    }
})