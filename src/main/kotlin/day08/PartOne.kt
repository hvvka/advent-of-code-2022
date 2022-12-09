package day08

import FileReader

fun main() {
    val input = FileReader().readFile("day08/test.txt")

    val trees = input.split("\n").map { line -> line.toCharArray().map { it.digitToInt() }.toList() }.toList()

    var visibleTrees = 0
    for (treeLineIndex in trees.indices) {
        for (treeIndex in trees[treeLineIndex].indices) {
            val treeHeight = trees[treeLineIndex][treeIndex]
            val higherTreesLeft = trees[treeLineIndex].filterIndexed { index, tree -> index < treeIndex && tree >= treeHeight }.count()
            val higherTreesRight = trees[treeLineIndex].filterIndexed { index, tree -> index > treeIndex && tree >= treeHeight }.count()
            val higherTreesUp = trees.filterIndexed { index, treeLine -> index < treeLineIndex && treeLine[treeIndex] >= treeHeight }.count()
            val higherTreesDown = trees.filterIndexed { index, treeLine -> index > treeLineIndex && treeLine[treeIndex] >= treeHeight }.count()
            if (higherTreesLeft == 0 || higherTreesRight == 0 || higherTreesUp == 0 || higherTreesDown == 0) {
                visibleTrees++
            }
        }
    }

    println(visibleTrees) // 1533
}
