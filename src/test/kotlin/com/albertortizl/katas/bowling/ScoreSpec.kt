package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object ScoreParserSpec : Spek({

    given("a open frame score calculation") {

        val score: (OpenFrame) -> Int = ::score

        it("should score as the sum of the two tries") {
            score(OpenFrame(1, 6)) `should be equal to` 7
        }
    }
})