package com.albertortizl.katas.bowling

import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.`with message`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it


object DefaultGameParserSpec : Spek({

    given("a game parser") {

        val parse = DefaultGameParser::parse

        it("should fail when an empty string is provided") {
            val func = { parse("") }
            func `should throw` IllegalArgumentException::class `with message` "'' is not a valid pattern"
        }

        it("should parse game with a single strike when a strike pattern is provided") {
            val game: Game = parse("X")
            game `should equal` Game(listOf(Strike))
        }

        it("should parse game with a single Spare when a spare pattern is provided") {
            val game: Game = parse("2/")
            game `should equal` Game(listOf(Spare(2)))
        }

        it("should parse game with a single Spare when a spare pattern with a miss is provided") {
            val game: Game = parse("-/")
            game `should equal` Game(listOf(Spare(0)))
        }

        it("should parse game with a single OpenFrame when a open frame pattern is provided") {
            val game: Game = parse("22")
            game `should equal` Game(listOf(OpenFrame(2, 2)))
        }

        it("should parse game with a single OpenFrame when a open frame pattern with a miss is provided") {
            val game: Game = parse("2-")
            game `should equal` Game(listOf(OpenFrame(2, 0)))
        }

        it("should parse game with when a full frame pattern is provided") {
            val game: Game = parse("X 9/ X 54 -- -2 1- -/ 44 11")
            game `should equal` game `should equal` Game(
                    listOf(Strike,
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

    given("string to spare converter") {

        val asSpare = DefaultGameParser::asSpare

        it("should convert to Spare when spare is valid") {
            asSpare("5/") `should equal` Spare(5)
        }

        it("should convert to Spare when only the first character is valid") {
            asSpare("555") `should equal` Spare(5)
        }

    }
})