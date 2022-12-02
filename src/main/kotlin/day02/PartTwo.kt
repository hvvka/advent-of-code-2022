package day02

import FileReader
import java.lang.IllegalArgumentException

fun main() {
    val input = FileReader().readFile("day02/input.txt")

    val totalScore = input.split("\n").sumOf { line ->
        val shapes = line.split(" ")

        val opponentShape = ShapeFactory.createShape(shapes[0].first())
        val expectedRoundOutcome = RoundOutcomeFactory.createRoundOutcome(shapes[1].first())

        val myShape = opponentShape.deduceExpectedShapeForOutcome(expectedRoundOutcome)
        expectedRoundOutcome.score + myShape.getShapeScore()
    }

    println(totalScore) // 10498
}

class RoundOutcomeFactory {
    companion object {
        fun createRoundOutcome(char: Char): RoundOutcome {
            return when (char) {
                'X' -> RoundOutcome.LOSS
                'Y' -> RoundOutcome.DRAW
                'Z' -> RoundOutcome.WIN
                else -> throw IllegalArgumentException("Unrecognized character to create round outcome!")
            }
        }
    }
}


