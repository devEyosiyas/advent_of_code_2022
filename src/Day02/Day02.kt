package Day02

import println
import readInput

const val ROCK = 1
const val PAPER = 2
const val SCISSORS = 3
const val WINNER_POINT = 6

fun main() {
    fun point(c: Char): Int = when (c) {
        'A', 'X' -> ROCK
        'B', 'Y' -> PAPER
        'C', 'Z' -> SCISSORS
        else -> 0
    }

    fun computeWinner(a: Int, b: Int): Char = when {
        a == b -> 'T'
        a == ROCK && b == SCISSORS -> 'A'
        a == PAPER && b == ROCK -> 'A'
        a == SCISSORS && b == PAPER -> 'A'
        else -> 'B'
    }

    fun swapMove(moveA : Int, c: Char): Int = when (c) {
        'Y' -> moveA
        'X' -> when (moveA) {
            ROCK -> SCISSORS
            PAPER -> ROCK
            else -> PAPER
        }
        else -> when (moveA) {
            ROCK -> PAPER
            PAPER -> SCISSORS
            else -> ROCK
        }
    }

    fun awardPoint(moveA: Char, moveB: Char, swap: Boolean): Int {
        val pointA = point(moveA)
        val pointB = if (swap) swapMove(pointA, moveB) else point(moveB)
        return when (computeWinner(pointA, pointB)) {
            'A' -> pointB
            'B' -> WINNER_POINT + pointB
            'T' -> (WINNER_POINT / 2) + pointB
            else -> 0
        }
    }

    fun solve(input: List<String>, swap:Boolean): Int {
        var score = 0
        input.forEach {
            it.split(" ").let { (a, b) ->
                score += awardPoint(a.toCharArray().first(), b.toCharArray().first(), swap)
            }
        }
        return score
    }

    val testInput = readInput("Day02", true)
    val input = readInput("Day02")

    solve(testInput, true).println()
    solve(input, true).println()
}
