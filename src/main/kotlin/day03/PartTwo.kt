package day03

import FileReader
import java.lang.IllegalArgumentException

fun main() {
    val input = FileReader().readFile("day03/input.txt")

    val lines = input.split("\n")

    val badgeItemTypes = (lines.indices step 3)
            .map { index ->
                val firstRucksack = lines[index].toCharArray().toSet()
                val secondRucksack = lines[index + 1].toCharArray().toSet()
                val thirdRucksack = lines[index + 2].toCharArray().toSet()

                firstRucksack.intersect(secondRucksack).intersect(thirdRucksack).first()
            }

    val prioritySum = calculateItemPriorities(badgeItemTypes)

    println(prioritySum) // 2760
}
