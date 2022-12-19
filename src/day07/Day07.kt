package day07

import println
import readInput

fun main() {
    fun String.isDigit() = all { it in '0'..'9' }

    fun processFileSystem(input: List<String>): Map<String, Long> {
        val sizes = mutableMapOf<String, Long>()
        var path = mutableListOf<String>()
        input.forEach {
            val split = it.split(" ")
            if (split[0] == "$" && split[1] == "cd") {
                if (split[2] == "..") path = path.dropLast(1).toMutableList()
                else path.add(split[2])
            } else if (split[0].isDigit()) {
                path.map { p ->
                    var dir = path.subList(0, path.indexOf(p) + 1).joinToString("/")
                    if (dir.startsWith("//")) dir.drop(1).also { d -> dir = d }
                    if (sizes[dir] == null) sizes[dir] = 0
                    sizes[dir] = sizes[dir]!! + split[0].toLong()
                }
            }

        }
        return sizes
    }

    fun partOne(input: List<String>, limit: Long): Long {
        return processFileSystem(input).map { it.value }.filter { it <= limit }.sum()
    }

    fun partTwo(input: List<String>, updateSize: Long): Long {
        val requiredSpace = updateSize - (70_000_000 - processFileSystem(input)["/"]!!)
        return processFileSystem(input).toList().sortedBy { it.second }.first { it.second >= requiredSpace }.second
    }

    val testInput = readInput("day07", true)
    val input = readInput("day07")

    // part one
    partOne(testInput, 100_000).println()
    partOne(input, 100_000).println()

    // part two
    partTwo(testInput, 30_000_000).println()
    partTwo(input, 30_000_000).println()
}