fun main() {
    dayTwoSetup()
    rockPaperScissorsPartOne()
    rockPaperScissorsPartTwo()
}

var rockPaperScissorsInput = mutableListOf<Pair<String, String>>()
var rockPaperScissorsScoreTotal = 0
var rockPaperScissorsRevisedStrategyScoreTotal = 0

fun dayTwoSetup() {
    readFile("src/main/input/Day02Input.txt").forEachLine {
        rockPaperScissorsInput.add(Pair(it.substringBefore(" ").trim(), convertYourPlay(it.substringAfter(" ").trim())))
    }
}

fun convertYourPlay(yourMove: String): String {
    return when (yourMove) {
        "X" -> "A"
        "Y" -> "B"
        "Z" -> "C"
        else -> ""
    }
}

fun rockPaperScissorsPartOne() {
    rockPaperScissorsInput.forEach {
        rockPaperScissorsScoreTotal += calculateTotalScore(it.first, it.second)
    }
    println(rockPaperScissorsScoreTotal)
}

fun rockPaperScissorsPartTwo() {
    rockPaperScissorsInput.forEach {
        rockPaperScissorsRevisedStrategyScoreTotal += calculateRevisedTotalScore(it.first, it.second)
    }
    println(rockPaperScissorsRevisedStrategyScoreTotal)
}

fun calculateRevisedTotalScore(opponentMove: String, yourMove: String): Int {
    var score = 0
    if (yourMove == "B") {
        score = 3 + calculateShapeScore(opponentMove)
    }
    if (yourMove == "A") {
        score = when (opponentMove) {
            "A" -> calculateShapeScore("C")
            "B" -> calculateShapeScore("A")
            "C" -> calculateShapeScore("B")
            else -> 0
        }
    }
    if (yourMove == "C") {
        score = 6
        score += when (opponentMove) {
            "A" -> calculateShapeScore("B")
            "B" -> calculateShapeScore("C")
            "C" -> calculateShapeScore("A")
            else -> 0
        }
    }
    return score
}

fun calculateTotalScore(opponentMove: String, yourMove: String): Int {
    if (opponentMove == yourMove) {
        return 3 + calculateShapeScore(yourMove)
    }
    var score = when (opponentMove) {
        "A" -> {
            if (yourMove == "C") {
                0
            } else {
                6
            }
        }
        "B" -> {
            if (yourMove == "A") {
                0
            } else {
                6
            }
        }
        "C" -> {
            if (yourMove == "B") {
                0
            } else {
                6
            }
        }
        else -> 0
    }
    return score + calculateShapeScore(yourMove)
}

fun calculateShapeScore(yourMove: String): Int {
    return when (yourMove) {
        "A" -> 1
        "B" -> 2
        "C" -> 3
        else -> 0
    }
}
