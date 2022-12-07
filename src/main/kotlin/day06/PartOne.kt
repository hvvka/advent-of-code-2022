package day06

import FileReader

fun main() {
    val input = FileReader().readFile("day06/input.txt")

    for (index in input.indices) {
        if (setOf(input[index], input[index + 1], input[index + 2], input[index + 3]).size == 4) {
            println(index + 4) // 1300
            break
        }
    }
}
