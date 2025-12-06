fun main() {
    fun part1(input: List<String>): Int {
        val operations = input.map {
            val point = it.substring(1).toInt()
            if (it.startsWith('L')) {
                Operation.Left(point)
            } else {
                Operation.Right(point)
            }
        }

        val dialPositions = operations.fold(listOf(50)) { allPositions, operation ->
            val newPosition = when (operation) {
                is Operation.Left -> allPositions.last() - operation.value
                is Operation.Right -> allPositions.last() + operation.value
            }.floorMod(100)
            allPositions + newPosition
        }

        return dialPositions.count { it == 0 }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readLines("Day01_test")
    part1(testInput).check(3)

    val input = readLines("Day01")
    part1(input).println().check(1074)
//    part2(input).println()
}

private sealed class Operation {
    data class Left(val value: Int) : Operation()

    data class Right(val value: Int) : Operation()
}

private fun Int.floorMod(n: Int): Int {
    val r = this % n
    return if (r >= 0) r else r + n
}
