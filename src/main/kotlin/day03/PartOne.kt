package day03

import FileReader

fun main() {
    val input = FileReader().readFile("day03/input.txt")

    val commonCompartmentCharacters = input.split("\n").map { line ->
        val mid = line.length / 2
        val firstCompartment = line.substring(0, mid)
        val secondCompartment = line.substring(mid)

        val firstCompartmentCharacters = firstCompartment.toCharArray().toSet()
        val secondCompartmentCharacters = secondCompartment.toCharArray().toSet()

        firstCompartmentCharacters.intersect(secondCompartmentCharacters).first()
    }

    val prioritySum = calculateItemPriorities(commonCompartmentCharacters)

    println(prioritySum) // 7878
}

fun calculateItemPriorities(items: List<Char>): Int {
    var prioritySum = 0
    for (character in items) {
        prioritySum += if (character.isUpperCase()) {
            character.code - 'A'.code + 27
        } else {
            character.code - 'a'.code + 1
        }
    }
    return prioritySum
}
