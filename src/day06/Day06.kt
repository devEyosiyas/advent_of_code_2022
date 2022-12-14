package day06

import println
import readInput

fun main() {
    fun solve(input: List<String>, size: Int): Int {
        for ((index, value) in input[0].windowed(size).withIndex()) {
            if (value.toSet().size == size) return size + index
        }
        return 0
    }

    val testInput = readInput("day06", true)
    val input = readInput("day06")

    // part one
    solve(testInput, 4).println()
    solve(input, 4).println()

    // part two
    solve(testInput, 14).println()
    solve(input, 14).println()
}