package day04

import println
import readInput

fun main() {
    fun processSections(input: List<String>): MutableList<List<Pair<Int, Int>>> {
        val sections = mutableListOf<List<Pair<Int, Int>>>()
        input.forEach { pairs ->
            val pair = mutableListOf<Pair<Int, Int>>()
            pairs.split(",").forEach { section ->
                val range = section.split("-").map { it.toInt() }
                pair.add(Pair(range[0], range[1]))
            }
            sections.add(pair)
        }
        return sections
    }

    fun partOne(input: List<String>): Int {
        val sections = processSections(input)
        var counter = 0
        sections.forEach { section ->
            section.chunked(4).forEach { pair ->
                if (pair[0].first <= pair[1].first && pair[0].second >= pair[1].second || pair[1].first <= pair[0].first && pair[1].second >= pair[0].second) counter++
            }
        }
        return counter
    }

    fun partTwo(input: List<String>): Int {
        val sections = processSections(input)
        var overlap = 0
        sections.forEach { section ->
            section.chunked(2).forEach { pair ->
                if (pair[1].first >= pair[0].first && pair[1].first <= pair[0].second || pair[0].first >= pair[1].first && pair[0].first <= pair[1].second) overlap++
            }
        }
        return overlap
    }

    val testInput = readInput("day04", true)
    val input = readInput("day04")

    // part one
    partOne(testInput).println()
    partOne(input).println()

    // part two
    partTwo(testInput).println()
    partTwo(input).println()
}