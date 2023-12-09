fun main() {

    fun predict(line: String): Int {
        val numbers = line.findAllNumbers()
        val hierarchy = mutableListOf(numbers)

        while (hierarchy.last().any { it != 0 } && hierarchy.last().size > 1) {
            val current = hierarchy.last()
            val next = current.drop(1).zip(current.dropLast(1))
                    .map { (a, b) -> a - b }
            hierarchy.add(next)
        }


        var addedPreviousLine = 0
        for (hierarchyLine in hierarchy.dropLast(1).reversed()) {
            addedPreviousLine += hierarchyLine.lastOrNull() ?: 0
        }

        return addedPreviousLine
    }

    fun part1(input: List<String>): Int =
            input.sumOf {
                predict(it)
            }

    fun part2(input: List<String>): Int =
            input.sumOf {
                it.length
            }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test").filter(String::isNotBlank)
    val testOutput = part1(testInput)
    val expectedTestOutput = 114
    check(testOutput == expectedTestOutput) {
        "Part 1 Tests: Expected $expectedTestOutput, got $testOutput"
    }
    println("Part 1 Tests passed")

    predict("-1 -2 -4 -8").println()

    val input = readInput("Day09").filter(String::isNotBlank)
    val output = part1(input)
    check(output != 1725996962) {
        error("Part 1 too high ($output)")
    }
    output.println()

//    val testInput2 = readInput("Day09_test2").filter(String::isNotBlank)
//    val testOutput2 = part2(testInput2)
//    val expectedTestOutput2 = 1
//    check(testOutput2 == expectedTestOutput2) {
//        "Part 2 Tests: Expected $expectedTestOutput2, got $testOutput2"
//    }
//
//    val input2 = readInput("Day09_2").filter(String::isNotBlank)
//    part2(input2).println()
}