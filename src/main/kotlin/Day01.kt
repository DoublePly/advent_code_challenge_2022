
fun main () {
    dayOneSetup()
    calorieCountingPartOne()
    calorieCountingPartTwo()
}

var calorieCountInputAsList = mutableListOf<String>()
var caloriePerElfCalculatedList = mutableListOf<Int>()

fun dayOneSetup() {
    readFile("src/main/input/Day01Input.txt").forEachLine {
        calorieCountInputAsList.add(it)
    }
    calculateElves(calorieCountInputAsList)
}

fun calorieCountingPartOne() {
    println(caloriePerElfCalculatedList.maxOrNull())
}

fun calorieCountingPartTwo() {
    var topThreeCalorieNumbers = 0
    repeat (3) {
        caloriePerElfCalculatedList.maxOrNull().let {
            topThreeCalorieNumbers += it ?: 0
            caloriePerElfCalculatedList.remove(it)
        }
    }
    println(topThreeCalorieNumbers)
}

fun calculateElves(caloriePerElfList: List<String>) {
    var totalCalorie: Int = 0
    caloriePerElfList.forEachIndexed { index, calorie ->
        if (calorie.isEmpty() || index == caloriePerElfList.lastIndex) {
            caloriePerElfCalculatedList.add(totalCalorie)
            totalCalorie = 0
        }
        else {
            totalCalorie += calorie.toInt()
        }
    }
}
