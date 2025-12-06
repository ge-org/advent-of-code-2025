fun main() {
    fun part1(input: List<String>): Int {
        var accessibleRolls = 0
        for (row in 0..input.lastIndex) {
            for (column in 0..input[row].lastIndex) {
                if (input[row][column] == '.') continue
                val surroundingRolls = input.getSurroundingRollCount(row, column)
                if (surroundingRolls < 4) {
                    accessibleRolls += 1
                }
            }
        }
        return accessibleRolls
    }

    fun part2(input: List<String>): Int {
        tailrec fun removeRolls(input: List<String>): List<String> {
            val removableCoordinates = mutableListOf<Pair<Int, Int>>()
            for (row in 0..input.lastIndex) {
                for (column in 0..input[row].lastIndex) {
                    if (input[row][column] == '.') continue
                    val surroundingRolls = input.getSurroundingRollCount(row, column)
                    if (surroundingRolls < 4) {
                        removableCoordinates.add(row to column)
                    }
                }
            }

            return if (removableCoordinates.isNotEmpty()) {
                val mutableInput = input.toMutableList()
                removableCoordinates.forEach {
                    mutableInput[it.first] = mutableInput[it.first].toMutableList().apply { set(it.second, '.') }.joinToString("")

                }
                removeRolls(mutableInput)
            } else {
                input
            }
        }

        val originalRollCount = input.sumOf { it.count { it == '@' } }
        val updatedRollCount = removeRolls(input).sumOf { it.count { it == '@' } }
        return originalRollCount - updatedRollCount
    }

    val testInput = readLines("Day04_test")
    part1(testInput).check(13)
    part2(testInput).check(43)

    val input = readLines("Day04")
    part1(input).println().check(1395)
    part2(input).println()
}

private fun List<String>.getSurroundingRollCount(row: Int, column: Int): Int {
    val leftTop = isRoll(row - 1, column - 1)
    val left = isRoll(row, column - 1)
    val leftBottom = isRoll(row + 1, column - 1)
    val rightTop = isRoll(row - 1, column + 1)
    val right = isRoll(row, column + 1)
    val rightBottom = isRoll(row + 1, column + 1)
    val top = isRoll(row - 1, column)
    val bottom = isRoll(row + 1, column)
    return listOf(leftTop, left, leftBottom, rightTop, right, rightBottom, top, bottom).count { it }
}

private fun List<String>.isRoll(row: Int, column: Int): Boolean = getOrNull(row)?.getOrNull(column) == '@'
