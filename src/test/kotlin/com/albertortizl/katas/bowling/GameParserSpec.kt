package com.albertortizl.katas.bowling

import org.amshove.kluent.`should be`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GameParserSpec : Spek({

    given("a game parser") {

        val asGame = defaultGameParser

        on("parse") {
            it("should return an empty game when an empty string is provided") {
                val game: Game = asGame("")
                game.frames.isEmpty() `should be` true
            }
        }

    }
})