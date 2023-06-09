package parking

fun main() {

    class ParkingLot(
        var isFree: Boolean = true,
        var regNumber: String = "",
        var colorOfTheCar: String = "",
    ) {
        fun createSpot(isFree: Boolean): ParkingLot {
            return ParkingLot(isFree)
        }

        fun park(newCar: String, spot: Int) {
            val inputString = newCar.split(" ")
            isFree = false
            regNumber = inputString[1]
            colorOfTheCar = inputString[2]
            println("$colorOfTheCar car parked in spot $spot.")
        }

        fun leave(spotNumber: Int) {
            if (!isFree) {
                isFree = true
                regNumber = ""
                colorOfTheCar = ""
                println("Spot $spotNumber is free.")
            } else {
                println("There is no car in spot $spotNumber.")
            }

        }
    }

    val listOfCreatedSpots = mutableListOf<ParkingLot>()
    val spot1 = ParkingLot().createSpot(false)
    listOfCreatedSpots.add(spot1)
    val spot2 = ParkingLot().createSpot(true)
    listOfCreatedSpots.add(spot2)

    val inputString = readln()
    when {
        inputString.startsWith("park") -> {
            for (i in 0 until listOfCreatedSpots.size) {
                if (!listOfCreatedSpots[i].isFree) {
                    continue
                } else {
                    listOfCreatedSpots[i].park(inputString, i + 1)
                    break
                }
            }
        }
        inputString.startsWith("leave") -> {
            val numberOfSpot = inputString.split(" ")[1].toInt()
            listOfCreatedSpots[numberOfSpot - 1].leave(numberOfSpot)
        }
    }
}
