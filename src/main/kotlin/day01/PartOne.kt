package day01

import FileReader

fun main() {
    val input = FileReader().readFile("day01/input.txt")

    val maxCalorieSum = input.split("\n\n").maxOfOrNull { elfFood ->
        Elf(elfFood.split("\n")
                .map { it.toInt() })
                .calculateCalorieSum()
    }

    println(maxCalorieSum) // 64929
}

data class Elf(val foodCalories: List<Int>) {

    fun calculateCalorieSum(): Int {
        return foodCalories.sum()
    }
}