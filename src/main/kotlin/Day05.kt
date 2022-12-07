
fun main() {
    dayFiveSetup()
    //supplyStacksPartOne()
    supplyStacksPartTwo()
}

var supplyStacksLayoutRaw = mutableListOf<String>()
var supplyStacksLayout = mutableListOf<MutableList<String>>()
var supplyStacksInstructions = mutableListOf<String>()
var supplyStacksFinishedLoading = false

fun dayFiveSetup() {
    readFile("src/main/input/Day05Input.txt").forEachLine {
        parseData(it)
    }
    createSupplyStacksLayoutFromRawData()
}

fun supplyStacksPartOne() {
    supplyStacksInstructions.forEach {
        parseInstructionsForCrateMoverNineThousand(it)
    }
    supplyStacksLayout.forEach {
        println(it.first())
    }
}

fun supplyStacksPartTwo() {
    supplyStacksInstructions.forEach {
        parseInstructionsForCrateMoverNineThousandAndOne(it)
    }
    supplyStacksLayout.forEach {
        println(it.first())
    }
}

fun parseData(data: String) {
    if (data.isEmpty()) {
        supplyStacksFinishedLoading = true
    }
    if (!supplyStacksFinishedLoading) {
        var i = 0
        while (i < data.length) {
            supplyStacksLayoutRaw.add(data.substring(i..i + 2).trim())
            i += if (i + 4 > data.length) {
                3
            } else {
                4
            }
        }
    } else if (data.isNotEmpty()) {
        supplyStacksInstructions.add(data)
    }
}

fun createSupplyStacksLayoutFromRawData() {
    var amountOfStacks = supplyStacksLayoutRaw.last().toInt()
    var supplyStackLayoutTemplate = mutableListOf<String>()
    for (j in 0 until amountOfStacks) {
        for (i in j until supplyStacksLayoutRaw.size step amountOfStacks) {
            if (supplyStacksLayoutRaw[i].isNotEmpty()) {
                supplyStackLayoutTemplate.add(supplyStacksLayoutRaw[i])
            }
        }
        supplyStacksLayout.add(supplyStackLayoutTemplate)
        supplyStackLayoutTemplate = mutableListOf()
    }
}

fun parseInstructionsForCrateMoverNineThousand(instruction: String) {
    val amountToMove: Int = instruction.substringBefore("from").let {
        it.substringAfter("move").trim()
    }.toInt()
    val moveFrom = instruction.substringAfter("from").let {
     it.substringBefore("to").trim().toInt()
    }.toInt()
    val moveTo = instruction.substringAfter("to").trim().toInt()
    for (i in 0 until amountToMove) {
        var removedCrate = supplyStacksLayout[moveFrom - 1].removeAt(0)
        supplyStacksLayout[moveTo - 1].add(0, removedCrate)
    }
}

fun parseInstructionsForCrateMoverNineThousandAndOne(instruction: String) {
    val amountToMove: Int = instruction.substringBefore("from").let {
        it.substringAfter("move").trim()
    }.toInt()
    val moveFrom = instruction.substringAfter("from").let {
        it.substringBefore("to").trim().toInt()
    }.toInt()
    val moveTo = instruction.substringAfter("to").trim().toInt()
    var removedCrates = mutableListOf<String>()
    for (i in 0 until amountToMove) {
        removedCrates.add(supplyStacksLayout[moveFrom - 1].removeAt(0))
    }
    removedCrates.reverse()
    removedCrates.forEach {
        supplyStacksLayout[moveTo - 1].add(0, it)
    }
}