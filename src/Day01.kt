fun main() {
    fun part1(input: List<String>): Int {
        val operations = getOperations(input)
        val dialPositions = operations.fold(listOf(50)) { allPositions, operation ->
            val newPosition = when (operation) {
                is Operation.Left -> allPositions.last() - operation.value
                is Operation.Right -> allPositions.last() + operation.value
            }.floorMod(100)
            allPositions + newPosition
        }

        return dialPositions.count { it == 0 }
    }

    fun part2(input: List<String>): Int = getOperations(input).fold(
        DialTracker(50, List(100) { 0 }),
    ) { (initialDialPosition, initialCounts), operation ->
        var dialPosition = initialDialPosition
        val counts = initialCounts.toMutableList()
        when (operation) {
            is Operation.Left -> repeat(operation.value) {
                counts[dialPosition] += 1
                dialPosition = if (dialPosition == 0) counts.lastIndex else dialPosition - 1
            }
            is Operation.Right -> repeat(operation.value) {
                counts[dialPosition] += 1
                dialPosition = if (dialPosition == counts.lastIndex) 0 else dialPosition + 1
            }
        }
        DialTracker(dialPosition, counts)
    }.counts[0]

    val testInput = readLines("Day01_test")
    part1(testInput).check(3)
    part2(testInput).check(6)

    val input = readLines("Day01")
    part1(input).println().check(1074)
    part2(input).println().check(6254)
}

private fun getOperations(input: List<String>): List<Operation> = input.map { encoded ->
    val point = encoded.substring(1).toInt()
    when {
        encoded.startsWith('L') -> Operation.Left(point)
        else -> Operation.Right(point)
    }
}

private sealed class Operation {
    data class Left(val value: Int) : Operation()
    data class Right(val value: Int) : Operation()
}

private data class DialTracker(
    val position: Int,
    val counts: List<Int>,
)

private fun Int.floorMod(n: Int): Int {
    val r = this % n
    return if (r >= 0) r else r + n
}
