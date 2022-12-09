package day07

import FileReader

fun main() {
    val input = FileReader().readFile("day07/input.txt")

    val treeRoot = TreeNode("/")
    var currentNode = treeRoot

    input.split("\n").forEach { line ->
        when {
            Regex("\\$ cd /").matches(line) -> {
                // do nothing
            }

            Regex("\\$ cd ..").matches(line) -> {
                currentNode = currentNode.parent!!
            }

            Regex("\\$ cd .*").matches(line) -> {
                currentNode = currentNode.getChildWithValue(line.split(" ")[2])
            }

            Regex("dir .*").matches(line) -> {
                val directoryName = line.split(" ")[1]
                currentNode.add(TreeNode(directoryName))
            }

            Regex("\\d+ .*").matches(line) -> {
                val fileSize = line.split(" ")[0].toInt()
                val fileName = line.split(" ")[1]
                currentNode.add(TreeNode(fileName, fileSize))
            }
        }
    }

    calculateNodeSizes(treeRoot)

    val result = findSmallestDirectoryToRemove(treeRoot)
    println(result) // 549173
}

private fun findSmallestDirectoryToRemove(treeRoot: TreeNode<String>): Int {
    val nodes = mutableListOf<TreeNode<String>>()
    traverseSubTree(treeRoot, nodes)
    return nodes.map { it.size }.filter { 40528671 - it <= 40000000 }.min()
}
