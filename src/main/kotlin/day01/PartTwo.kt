package day01

import FileReader

fun main() {
    val input = FileReader().readFile("day01/input.txt")

    val calorieSumSorted = input.split("\n\n").map { elfFood ->
        Elf(elfFood.split("\n")
                .map { it.toInt() })
                .calculateCalorieSum()
    }.sortedDescending()

    println(calorieSumSorted[0] + calorieSumSorted[1] + calorieSumSorted[2]) // 193697
}
