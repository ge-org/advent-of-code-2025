fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readLines("Day01_test")
    part1(testInput).check(3)

    val input = readLines("Day01")
    part1(input).println()
    part2(input).println()
}
