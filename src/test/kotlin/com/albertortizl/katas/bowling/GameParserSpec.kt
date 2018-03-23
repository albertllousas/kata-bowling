package com.albertortizl.katas.bowling

import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFails

object GameParserSpec : Spek({

    given("a game parser") {

        val asGame = defaultGameParser

        on("parse") {

            it("should fail when an empty string is provided") {
                val func = { asGame("") }
                func `should throw` IllegalArgumentException::class `with message` "'' is not a valid pattern"
            }

            it("should return a game with a single strike when a string with strike pattern is provided") {
                val game: Game = asGame("X")
                game `should equal` Game(listOf(Strike))
            }
        }

    }
})