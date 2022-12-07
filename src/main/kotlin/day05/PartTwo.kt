package day05

import FileReader

fun main() {
    val input = FileReader().readFile("day05/input.txt")
    val instructions = input.split("\n\n")

    val stacks = mutableListOf<ArrayDeque<Char>>(
            ArrayDeque(), ArrayDeque(), ArrayDeque(),
            ArrayDeque(), ArrayDeque(), ArrayDeque(),
            ArrayDeque(), ArrayDeque(), ArrayDeque())

    instructions[0].split("\n").map { line ->
        line.mapIndexed { index, c ->
            if (c == '[') {
                stacks[index / 4].addLast(line[index + 1])
            }
        }
    }

    val moveInstructions = instructions[1].split("\n").map { line ->
        val moveInstructionNumbers = Regex("\\d+").findAll(line).map { it.value.toInt() }.toList()
        MoveInstruction(moveInstructionNumbers[0], moveInstructionNumbers[1], moveInstructionNumbers[2])
    }

    val cratesSimulator = CratesSimulator(stacks)
    cratesSimulator.simulateCrateMover9001(moveInstructions)

    println(cratesSimulator.findTopCrates()) // CNSCZWLVT
}

fun CratesSimulator.simulateCrateMover9001(moveInstructions: List<MoveInstruction>) {
    for (moveInstruction in moveInstructions) {
        stacks[moveInstruction.fromStack - 1].subList(0, moveInstruction.cratesToMove).reversed().forEach { crate ->
            stacks[moveInstruction.toStack - 1].addFirst(crate)
        }

        var repetitions = moveInstruction.cratesToMove
        while (repetitions-- > 0) {
            stacks[moveInstruction.fromStack - 1].removeFirst()
        }
    }
}
