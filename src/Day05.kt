fun main() {
    fun part1(input: String): Int {
        val parts = input.split("\n\n")
        val ranges = parts.first().lines().map { it.split('-').let { it[0].toLong()..it[1].toLong() } }
        val ids = parts[1].lines().map { it.toLong() }
        return ids.fold(emptyList<Long>()) { fresh, id ->
            if (ranges.any { it.contains(id) }) {
                fresh + id
            } else {
                fresh
            }
        }.size
    }

    fun part2(input: String): Int {
        TODO()
    }

    val testInput = readFile("Day05_test")
    part1(testInput).check(3)
//    part2(testInput)

    val input = readFile("Day05")
    part1(input).println()
//    part2(input).println()
}
