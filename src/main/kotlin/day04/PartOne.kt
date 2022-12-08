package day04

import FileReader

fun main() {
    val input = FileReader().readFile("day04/input.txt")

    val fullyContainedAssignmentPairs = input.split("\n").map { line ->
        val firstElfSection = line.split(",")[0]
        val secondElfSection = line.split(",")[1]
        doesOneFullyContainsOther(firstElfSection, secondElfSection)
    }.count { it }

    println(fullyContainedAssignmentPairs) // 550
}

private fun doesOneFullyContainsOther(firstElfSection: String, secondElfSection: String): Boolean {
    val firstElfSectionLow = firstElfSection.split("-")[0].toInt()
    val firstElfSectionHigh = firstElfSection.split("-")[1].toInt()
    val secondElfSectionLow = secondElfSection.split("-")[0].toInt()
    val secondElfSectionHigh = secondElfSection.split("-")[1].toInt()

    return if (firstElfSectionLow == secondElfSectionLow) {
        true
    } else if (firstElfSectionLow < secondElfSectionLow) {
        firstElfSectionHigh >= secondElfSectionHigh
    } else {
        firstElfSectionHigh <= secondElfSectionHigh
    }
}
