fun main() {
    rucksackReorganizationPartOne()
    rucksackReorganizationPartTwo()
}

//A list that contains the item types that are found in both the first and second compartments.
var rucksackReorganizationInput = mutableListOf<String>()

//A list that contains the item types of the badges shared by the group of three elves.
var rucksackReorginazationBadgeInput = mutableListOf<String>()

val priority = listOf(
    "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
    "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

fun rucksackReorganizationPartOne() {
    readFile("src/main/input/Day03Input.txt").forEachLine { line ->
        val halfwayPoint = line.length / 2
        val firstCompartment = line.substring(0, halfwayPoint)
        val secondCompartment = line.substring(halfwayPoint, line.length)
        firstCompartment.forEach { char ->
            if (secondCompartment.contains(char, false)) {
                rucksackReorganizationInput.add(char.toString())
                return@forEachLine
            }
        }
    }
    calculatePriority(rucksackReorganizationInput)
}

fun rucksackReorganizationPartTwo() {
    readFile("src/main/input/Day03Input.txt").forEachLine { line ->
        rucksackReorginazationBadgeInput.add(line)
    }
    var rucksackReorginzationBadgeTypes = mutableListOf<String>()
    var i = 0
    while (i < rucksackReorginazationBadgeInput.size) {
        val firstRucksack = rucksackReorginazationBadgeInput[i]
        val secondRucksack = rucksackReorginazationBadgeInput[i + 1]
        val thirdRucksack = rucksackReorginazationBadgeInput[i + 2]
        var badgeType = ""
        firstRucksack.forEach {
            if (secondRucksack.contains(it, false) && thirdRucksack.contains(it, false)) {
                badgeType = it.toString()
            }
        }
        rucksackReorginzationBadgeTypes.add(badgeType)
        i += 3
    }
    calculatePriority(rucksackReorginzationBadgeTypes)
}

fun calculatePriority(itemTypes: List<String>) {
    var totalPriority = 0
    itemTypes.forEach { item ->
        totalPriority += priority.indexOf(item) + 1
    }
    println(totalPriority)
}
