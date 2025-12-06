fun main() {
    fun part1(input: List<String>): Int {
        var accessibleRolls = 0
        for (row in 0..input.lastIndex) {
            for (column in 0..input[row].lastIndex) {
                if (input[row][column] == '.') continue
                val leftTop = input.isRoll(row - 1, column - 1)
                val left = input.isRoll(row, column - 1)
                val leftBottom = input.isRoll(row + 1, column - 1)
                val rightTop = input.isRoll(row - 1, column + 1)
                val right = input.isRoll(row, column + 1)
                val rightBottom = input.isRoll(row + 1, column + 1)
                val top = input.isRoll(row - 1, column)
                val bottom = input.isRoll(row + 1, column)
                val surroundingRolls = listOf(leftTop, left, leftBottom, rightTop, right, rightBottom, top, bottom).count { it }
                if (surroundingRolls < 4) {
                    accessibleRolls += 1
                }
            }
        }

        return accessibleRolls
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    val testInput = readLines("Day04_test")
    part1(testInput).check(13)
//    part2(testInput)

    val input = readLines("Day04")
    part1(input).println()
//    part2(input).println()
}

private fun List<String>.isRoll(row: Int, column: Int): Boolean = getOrNull(row)?.getOrNull(column) == '@'
