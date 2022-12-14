package day05

import println
import readInput
import kotlin.collections.ArrayDeque


fun main() {
    fun generateInput(
        input: List<String>,
        stack: Int,
    ): Pair<List<String>, MutableList<ArrayDeque<String>>> {
        val split = input.joinToString("\n").split("\n\n")
        val stacksList = split[0].split("\n").map { it.replace(Regex("\\[|\\]"), "") }.dropLast(1)
        val instructions = split[1].split("\n")
        val stacks = mutableListOf<ArrayDeque<String>>()

        for (i in 0 until stack) {
            stacks.add(ArrayDeque())
        }

        for (element in stacksList) {
            val crates = element.replace("   ", "").split(" ")
            for (i in crates.indices) {
                if (crates[i].isNotEmpty()) stacks[i].add(crates[i])
            }
        }
        return Pair(instructions, stacks)
    }

    fun partOne(input: List<String>, stack: Int = 3): String {
        val (instructions, stacks) = generateInput(input, stack)
        instructions.forEach {
            val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
            val match = regex.find(it)
            if (match != null) {
                val (move, from, to) = match.destructured
                for (j in 0 until move.toInt()) {
                    val tmp = stacks[from.toInt() - 1].first()
                    stacks[from.toInt() - 1].remove(tmp)
                    stacks[to.toInt() - 1].addFirst(tmp)
                }
            }
        }
        return stacks.joinToString("") { it.first() }
    }

    fun partTwo(input: List<String>, stack: Int = 3): String {
        val (instructions, stacks) = generateInput(input, stack)
        instructions.forEach {
            val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
            val match = regex.find(it)
            if (match != null) {
                val (move, from, to) = match.destructured
                val tempList = ArrayDeque<String>()
                for (i in 0 until move.toInt()) {
                    val temp = stacks[from.toInt() - 1].first()
                    tempList.add(temp)
                    stacks[from.toInt() - 1].remove(temp)
                }
                stacks[to.toInt() - 1].addAll(0, tempList)
            }
        }
        return stacks.joinToString("") { it.first() }
    }

    val testInput = readInput("day05", true)
    val input = readInput("day05")

    // part one
    partOne(testInput).println()
    partOne(input, 9).println()

    // part two
    partTwo(testInput).println()
    partTwo(input, 9).println()
}