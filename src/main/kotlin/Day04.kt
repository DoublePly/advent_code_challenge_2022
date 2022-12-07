fun main() {
    dayFourSetup()
    campCleanupPartOne()
    campCleanupPartTwo()
}

var sectionAssignmentsInput = mutableListOf<Pair<String, String>>()

fun dayFourSetup() {
    readFile("src/main/input/Day04Input.txt").forEachLine { line ->
        sectionAssignmentsInput.add(Pair(line.substringBefore(","), line.substringAfter(",")))
    }
}

fun campCleanupPartOne() {
    var overlappingAssignmentPairs = 0
    sectionAssignmentsInput.forEach {
        val firstPair = Pair(it.first.substringBefore("-").toInt(), it.first.substringAfter("-").toInt())
        val secondPair = Pair(it.second.substringBefore("-").toInt(), it.second.substringAfter("-").toInt())
        if (isFirstPairWithinSecond(
                firstPair.first,
                firstPair.second,
                secondPair.first,
                secondPair.second
            ) || isSecondPairWithinFirst(
                firstPair.first,
                firstPair.second,
                secondPair.first,
                secondPair.second
            )
        ) {
            overlappingAssignmentPairs++
        }
    }
    println(overlappingAssignmentPairs)
}

fun campCleanupPartTwo() {
    var overlappingAssignmentPairs = 0
    sectionAssignmentsInput.forEach {
        val firstPair = Pair(it.first.substringBefore("-").toInt(), it.first.substringAfter("-").toInt())
        val secondPair = Pair(it.second.substringBefore("-").toInt(), it.second.substringAfter("-").toInt())
        if (doPairsOverlap(firstPair.first, firstPair.second, secondPair.first, secondPair.second)) {
            overlappingAssignmentPairs++
        }
    }
    println(overlappingAssignmentPairs)
}

fun isFirstPairWithinSecond(firstPairStart: Int, firstPairEnd: Int, secondPairStart: Int, secondPairEnd: Int) =
    firstPairStart >= secondPairStart && firstPairEnd <= secondPairEnd

fun isSecondPairWithinFirst(firstPairStart: Int, firstPairEnd: Int, secondPairStart: Int, secondPairEnd: Int) =
    secondPairStart >= firstPairStart && secondPairEnd <= firstPairEnd

fun doPairsOverlap(firstPairStart: Int, firstPairEnd: Int, secondPairStart: Int, secondPairEnd: Int) =
    (secondPairStart in firstPairStart..firstPairEnd) || (secondPairEnd in firstPairStart..firstPairEnd) ||
            (firstPairStart in secondPairStart..secondPairEnd) || (firstPairEnd in secondPairStart..secondPairEnd)

