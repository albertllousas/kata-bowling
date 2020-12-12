package com.albertortizl.katas.bowling

import org.amshove.kluent.`should equal`
import org.spekframework.spek2.Spek


object FrameSpec : Spek({

    group("build a frame from balls") {

        test("should create a strike from one ball knocking down all pins") {
            Frame.fromBalls(10) `should equal` Strike
        }

        test("should create a spare from two ball knocking down all pins") {
            Frame.fromBalls(1, 9) `should equal` Spare(1)
        }

        test("should create an open frame from two balls knocking down not all pins") {
            Frame.fromBalls(2, 5) `should equal` OpenFrame(2, 5)
        }

        test("should create a tenth frame with a strike and two extra balls when first of three balls knocks down all pins") {
            Frame.fromBalls(10, 10, 10) `should equal` Tenth(Strike, 10, 10)
        }

        test("should parse a final frame with a spare and one extra ball when first and second of three balls knock down all pins") {
            Frame.fromBalls(5, 5, 10) `should equal` Tenth(Spare(5), 10)
        }
    }
})

