fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        val regex = Regex("[^\\d+]")
        for (item in input) {
            val digitsOnly = item.replace(regex, "")
            total += ("" + digitsOnly[0] + digitsOnly[digitsOnly.lastIndex]).toInt()
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        val digits = mapOf(
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine",
            0 to "zero"
        )
        val indices = mutableMapOf<Int, String>()
        for (item in input) {
            var stringsOnly = item
            for (i in 0..9) {
                stringsOnly = stringsOnly.replace(i.toString(), digits.getValue(i))
            }
            for (value in digits.values) {
                val i = stringsOnly.indexOf(value)
                val j = stringsOnly.lastIndexOf(value)
                if (i >= 0 || j >= 0) {
                    indices[i] = value
                    indices[j] = value
                }
            }
            val first = digits.filterValues { it == indices.getValue(indices.keys.min()) }.keys.first()
            val last = digits.filterValues { it == indices.getValue(indices.keys.max()) }.keys.first()
            indices.clear()
            total += ("" + first + last).toInt()
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val testInput2 = readInput("Day01_test_part2")
    check(part1(testInput) == 142)
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
