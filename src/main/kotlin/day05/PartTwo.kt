package day05

import FileReader

fun main() {
    val input = FileReader().readFile("day05/input.txt")

    val fullyContainedAssignmentPairs = input.split("\n").map { line ->
        val firstElfSection = line.split(",")[0]
        val secondElfSection = line.split(",")[1]
        doSectionsOverlap(firstElfSection, secondElfSection)
    }.count { result -> result }

    println(fullyContainedAssignmentPairs) // 931
}

private fun doSectionsOverlap(firstElfSection: String, secondElfSection: String): Boolean {
    val firstElfSectionLow = firstElfSection.split("-")[0].toInt()
    val firstElfSectionHigh = firstElfSection.split("-")[1].toInt()
    val secondElfSectionLow = secondElfSection.split("-")[0].toInt()
    val secondElfSectionHigh = secondElfSection.split("-")[1].toInt()

    return if (firstElfSectionHigh < secondElfSectionLow) {
        false
    } else secondElfSectionHigh >= firstElfSectionLow
}
