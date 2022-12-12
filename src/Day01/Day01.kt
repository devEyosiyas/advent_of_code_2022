package Day01

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
        val sorted = set.toList().sortedBy { it.second }.reversed()
        var sum = 0
        for (i in 0 until selectCount) sum += sorted[i].second
        return sum
    }

    val testInput = readInput("Day01", true)
    val input = readInput("Day01")

    solve(testInput, 3).println()
    solve(input, 3).println()
}
