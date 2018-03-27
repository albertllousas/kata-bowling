package com.albertortizl.katas.bowling

import org.amshove.kluent.`should equal`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object FrameSpec : Spek({

    given("a frame from rolls function") {

        it("should parse a strike") {
            Frame.fromRolls(10) `should equal` Strike
        }

        it("should parse a spare ") {
            Frame.fromRolls(1,9) `should equal` Spare(1)
        }

        it("should parse an open frame ") {
            Frame.fromRolls(2,5) `should equal` OpenFrame(2, 5)
        }

        it("should parse a final frame with a three strikes") {
            Frame.fromRolls(10,10,10) `should equal` LastFrame(Strike, 10, 10)
        }

        it("should parse a final frame with a strike and spare") {
            Frame.fromRolls(10,1,9) `should equal` LastFrame(Strike, 1, 9)
        }

        it("should parse a final frame with a spare and strike") {
            Frame.fromRolls(5,5,10) `should equal` LastFrame(Spare(5), 10 )
        }

        it("should parse a final frame with an open frame") {
            Frame.fromRolls(5,4) `should equal` LastFrame(OpenFrame(5, 4))
        }
    }
})

