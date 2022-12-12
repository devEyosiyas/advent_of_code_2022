package day01

import println
import readInput

fun main() {
    fun solve(input: List<String>, selectCount: Int): Int {
        var tmp = 0
        var pos = 0
        val set = mutableMapOf<Int, Int>()
        input.forEach {
            if (it == "") {
                pos += 1
                tmp = 0
            } else {
                tmp += it.toInt()
                set[pos + 1] = tmp
            }
        }
        return set.toList().sortedBy { it.second }.reversed().take(selectCount).sumOf { it.second }
    }

    val testInput = readInput("day01", true)
    val input = readInput("day01")

    // part one
    solve(testInput, 1).println()
    solve(input, 1).println()

    // part two
    solve(testInput, 3).println()
    solve(input, 3).println()
}
