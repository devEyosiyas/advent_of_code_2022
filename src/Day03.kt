fun main() {
    fun priority(c: Char): Int = when (val n = c.code) {
        in 65..90 -> n - 65 + 27
        in 97..122 -> n - 96
        else -> n
    }

    fun processRucksack(rucksack: String, threeRucksacks: Boolean = false): Int {
        val length = rucksack.length
        val intersection: Set<Char>
        if (threeRucksacks) rucksack.split("\n").let { (a, b, c) ->
            intersection = a.toCharArray().toSet().intersect(b.toCharArray().toSet()).intersect(c.toCharArray().toSet())
        } else {
            val firstPart = rucksack.substring(0, length / 2).toCharArray().toSet()
            val secondPart = rucksack.substring(length / 2, length).toCharArray().toSet()
            intersection = firstPart.intersect(secondPart)
        }
        return intersection.sumOf { priority(it) }
    }

    fun rucksacks(rucksacks: List<String>): Int {
        var sum = 0
        rucksacks.forEach { rucksack ->
            sum += processRucksack(rucksack)
        }
        return sum
    }

    fun threeRucksacks(rucksacks: List<String>): Int {
        var sum = 0
        for (i in rucksacks.indices step 3) {
            val input = "${rucksacks[i]}\n${rucksacks[i + 1]}\n${rucksacks[i + 2]}"
            sum += processRucksack(input, true)
        }
        return sum
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    rucksacks(testInput).println()
    rucksacks(input).println()

    threeRucksacks(testInput).println()
    threeRucksacks(input).println()
}