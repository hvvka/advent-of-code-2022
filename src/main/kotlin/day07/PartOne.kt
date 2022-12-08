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

    val result = sumSmallDirectorySizes(treeRoot)
    println(result) // 1367870
}

private fun calculateNodeSizes(treeRoot: TreeNode<String>) {
    treeRoot.children.forEach { node ->
        if (node.children.isNotEmpty()) {
            calculateNodeSizes(node)
        }
        node.parent!!.increaseSize(node.size)
    }
}


private fun sumSmallDirectorySizes(treeRoot: TreeNode<String>): Int {
    val nodes = mutableListOf<TreeNode<String>>()
    traverseSubTree(treeRoot, nodes)
    return nodes.filter { it.size <= 100000 }.sumOf { it.size }
}

private fun traverseSubTree(node: TreeNode<String>, nodes: MutableList<TreeNode<String>>) {
    node.children.map { child ->
        if (child.children.isNotEmpty()) {
            nodes.add(child)
        }
        traverseSubTree(child, nodes)
    }
}

data class TreeNode<T>(internal val name: T, internal var size: Int = 0) {
    internal var parent: TreeNode<T>? = null
    internal val children: MutableList<TreeNode<T>> = mutableListOf()

    fun increaseSize(by: Int) {
        size += by
    }

    fun add(child: TreeNode<T>) {
        children.add(child)
        child.parent = this
    }

    fun getChildWithValue(value: T): TreeNode<T> {
        return children.find { it.name == value }!!
    }

    override fun toString(): String {
        var result = "$name(size=$size)"
        if (children.isNotEmpty()) {
            result += " {" + children.map { it.toString() } + "}"
        }
        return result
    }
}
