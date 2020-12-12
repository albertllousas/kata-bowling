package com.albertortizl.katas.bowling

import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.`with message`
import org.spekframework.spek2.Spek

object DefaultGameParserSpec : Spek({

    group("a string to single frame parser") {

        val parse = DefaultGameParser::asFrame

        test("should fail parsing an empty string") {
            val func = { parse("") }
            func `should throw` IllegalArgumentException::class `with message` "Invalid frame ''"
        }

        test("should parse a strike") {
            parse("X") `should equal` Strike
        }

        test("should parse a spare with some pins knocked down in the first roll") {
            parse("2/") `should equal` Spare(2)
        }

        test("should parse a spare with a miss in the first roll") {
            parse("-/") `should equal` Spare(0)
        }

        test("should fail parsing a frame with a more characters than expected") {
            val func = { parse("1000") }
            func `should throw` IllegalArgumentException::class `with message` "Invalid frame '1000'"
        }

        test("should parse an open frame with some pins knocked down in both rolls") {
            parse("25") `should equal` OpenFrame(2, 5)
        }

        test("should parse an open frame with all misses") {
            parse("--") `should equal` OpenFrame(0, 0)
        }

        test("should parse an open frame with a miss in the first roll") {
            parse("-5") `should equal` OpenFrame(0, 5)
        }

        test("should parse an open frame with a miss in the second roll") {
            parse("90") `should equal` OpenFrame(9, 0)
        }

        test("should parse a final frame with a three strikes") {
            parse("XXX") `should equal` Tenth(Strike, 10, 10)
        }

        test("should parse a final frame with a Strike and Spare") {
            parse("X1/") `should equal` Tenth(Strike, 1, 9)
        }

        test("should parse a final frame with a Strike and two extra balls") {
            parse("X11") `should equal` Tenth(Strike, 1, 1)
        }

        test("should parse a final frame with a Strike and two extra balls with a miss") {
            parse("X1-") `should equal` Tenth(Strike, 1, 0)
        }

        test("should parse a final frame with spare and Strike ") {
            parse("2/X") `should equal` Tenth(Spare(2), 10, null)
        }

        test("should parse a final frame with spare and an extra ball") {
            parse("2/1") `should equal` Tenth(Spare(2), 1, null)
        }
    }

    group("a string to a full game parser") {

        val parse = DefaultGameParser::parse

        test("should parse a game with a 12 strikes in a row") {
            val game: Game = parse("X X X X X X X X X XXX")
            game `should equal` Game(
                    (1..9).toList().map { Strike } + Tenth(Strike, 10, 10)
            )
        }

        test("should parse a game with 10 pairs of 5 and spare and a final 5") {
            val game: Game = parse("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5")
            game `should equal` Game(
                    (1..9).toList().map { Spare(5) } + Tenth(Spare(5), 5, null)
            )
        }

        test("should parse a game with a full game pattern") {
            val game: Game = parse("X 9/ X 54 -- -2 1- -/ 44 11")
            game `should equal` Game(
                    listOf(
                            Strike,
                            Spare(9),
                            Strike,
                            OpenFrame(5, 4),
                            OpenFrame(0, 0),
                            OpenFrame(0, 2),
                            OpenFrame(1, 0),
                            Spare(0),
                            OpenFrame(4, 4),
                            OpenFrame(1, 1)
                    ))
        }
    }
})
