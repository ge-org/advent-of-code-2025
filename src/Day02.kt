fun main() {
    fun part1(input: String): Long {
        val invalid = mutableListOf<Long>()
        getCandidates(input).forEach { candidate ->
            val text = candidate.toString()
            if (text.length % 2 == 0) {
                val chunks = text.chunked(text.length / 2)
                if (chunks.all { it == chunks.first() }) {
                    invalid.add(candidate)
                }
            }
        }
        return invalid.sum()
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    val testInput = readFile("Day02_test")
    part1(testInput).check(1227775554L)
//    part2(testInput)

    val input = readFile("Day02")
    part1(input).println().check(18952700150L)
//    part2(input).println().check(6254)
}

private fun getCandidates(input: String): List<Long> = input
    .split(',')
    .flatMap { it.split('-').let { it[0].toLong()..it[1].toLong() } }
