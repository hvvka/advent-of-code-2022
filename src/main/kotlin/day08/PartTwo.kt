package day08

import FileReader

fun main() {
    val input = FileReader().readFile("day08/input.txt")

    val trees = input.split("\n").map { line -> line.toCharArray().map { it.digitToInt() }.toList() }.toList()

    var highestScenicScore = 0

    for (treeLineIndex in trees.indices) {
        for (treeIndex in trees[treeLineIndex].indices) {
            var scenicScore = 1

            val treeHeight = trees[treeLineIndex][treeIndex]

            val treesLeft = trees[treeLineIndex].subList(0, treeIndex).reversed()
            scenicScore *= sumOneDirectionScenicScore(treesLeft, treeHeight)

            val treesRight = trees[treeLineIndex].subList(treeIndex + 1, trees[treeLineIndex].size)
            scenicScore *= sumOneDirectionScenicScore(treesRight, treeHeight)

            val treesUp = trees.filterIndexed{ lineIndex, _ -> lineIndex < treeLineIndex }.map { it[treeIndex] }.reversed()
            scenicScore *= sumOneDirectionScenicScore(treesUp, treeHeight)

            val treesDown = trees.filterIndexed{ lineIndex, _ -> lineIndex > treeLineIndex }.map { it[treeIndex] }
            scenicScore *= sumOneDirectionScenicScore(treesDown, treeHeight)

            if (scenicScore > highestScenicScore) {
                highestScenicScore = scenicScore
            }
        }
    }

    println(highestScenicScore) // 345744
}

private fun sumOneDirectionScenicScore(trees: List<Int>, treeHeight: Int): Int {
    var scenicScore = 0
    for (tree in trees) {
        if (tree < treeHeight) {
            scenicScore++
        }
        if (tree >= treeHeight) {
            scenicScore++
            break
        }
    }
    return scenicScore
}
