package day02

import FileReader
import java.lang.IllegalArgumentException

fun main() {
    val input = FileReader().readFile("day02/input.txt")

    val totalScore = input.split("\n").sumOf { line ->
        val shapes = line.split(" ")

        val opponentShape = ShapeFactory.createShape(shapes[0].first())
        val myShape = ShapeFactory.createShape(shapes[1].first())

        myShape.fight(opponentShape).score + myShape.getShapeScore()
    }

    println(totalScore) // 14297
}

class ShapeFactory {
    companion object {
        fun createShape(char: Char): Shape {
            return when (char) {
                'A', 'X' -> Rock(0)
                'B', 'Y' -> Paper(0)
                'C', 'Z' -> Scissors(0)
                else -> throw IllegalArgumentException("Unrecognized character to create shape!")
            }
        }
    }
}

interface Shape {
    fun getShapeScore() : Int
    fun getBeatingShape() : Shape
    fun getLosingShape() : Shape

    fun deduceExpectedShapeForOutcome(roundOutcome : RoundOutcome) : Shape {
        return when (roundOutcome) {
            RoundOutcome.DRAW -> this
            RoundOutcome.LOSS -> getLosingShape()
            RoundOutcome.WIN -> getBeatingShape()
        }
    }

    fun fight(opponentShape: Shape) : RoundOutcome {
        if (opponentShape == this) {
            return RoundOutcome.DRAW
        }
        if (opponentShape == getBeatingShape()) {
            return RoundOutcome.LOSS
        }
        return RoundOutcome.WIN
    }
}

enum class RoundOutcome(val score: Int) {
    WIN(6), DRAW(3), LOSS(0)
}

// FIXME: passing dummyArg is just wrong, but I don't know Kotlin enough and have little time today
data class Rock(val dummyArg: Int) : Shape {

    override fun getShapeScore(): Int {
        return 1
    }

    override fun getBeatingShape(): Shape {
        return Paper(0)
    }

    override fun getLosingShape(): Shape {
        return Scissors(0)
    }
}
data class Paper(val dummyArg: Int) : Shape {

    override fun getShapeScore(): Int {
        return 2
    }

    override fun getBeatingShape(): Shape {
        return Scissors(0)
    }

    override fun getLosingShape(): Shape {
        return Rock(0)
    }
}
data class Scissors(val dummyArg: Int) : Shape {

    override fun getShapeScore(): Int {
        return 3
    }

    override fun getBeatingShape(): Shape {
        return Rock(0)
    }

    override fun getLosingShape(): Shape {
        return Paper(0)
    }
}
