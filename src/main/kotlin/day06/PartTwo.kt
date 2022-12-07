package day06

import FileReader

private const val START_OF_MESSAGE_MARKER = 14

fun main() {
    val input = FileReader().readFile("day06/input.txt")

    for (index in 0..input.length - START_OF_MESSAGE_MARKER) {
        if (input.substring(index, index + START_OF_MESSAGE_MARKER).toSet().size == START_OF_MESSAGE_MARKER) {
            println(index + START_OF_MESSAGE_MARKER) // 3986
            break
        }
    }
}
