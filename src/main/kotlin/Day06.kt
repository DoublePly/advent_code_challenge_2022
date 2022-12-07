fun main() {
    daySixSetup()
    tuningTrouble(14)
}

var dataStreamBuffer: String = ""
var firstMarker: String = ""
var firstMarkerCounter = 0

fun daySixSetup() {
    readFile("src/main/input/Day06Input.txt").forEachLine {
        dataStreamBuffer = it
    }
}

fun tuningTrouble(packetSize: Int) {
    var marker = ""
    var counter = 0
    dataStreamBuffer.forEach {
        counter += 1
        if (marker.contains(it, true)) {
            var charPosition = marker.indexOf(it, 0, true)
            marker = marker.drop(charPosition + 1)
            marker = marker.plus(it)
        } else {
            marker = marker.plus(it)
        }
        if (marker.length == packetSize) {
            firstMarker = marker
            firstMarkerCounter = counter
            println("For tuning number $packetSize is: $firstMarker and appeared after the $firstMarkerCounter character(s)")
            return
        }
    }
}