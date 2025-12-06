fun main() {
    fun part1(input: List<String>): Int = input.fold(emptyList<Int>()) { joltages, batteries ->
        var highestJoltage = 0
        val digits = batteries.map { it.digitToInt() }
        for ((idx, i) in digits.withIndex()) {
            for (j in digits.subList(idx + 1, digits.size)) {
                val candidate = "$i$j".toInt()
                if (candidate > highestJoltage) {
                    highestJoltage = candidate
                }
            }
        }
        joltages + highestJoltage
    }.sum()

    fun part2(input: List<String>): Long {
        TODO()
    }

    val testInput = readLines("Day03_test")
    part1(testInput).check(357)
//    part2(testInput)

    val input = readLines("Day03")
    part1(input).println().check(17766)
//    part2(input).println()
}
