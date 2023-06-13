package parking

import kotlin.system.exitProcess

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

fun main() {

    val listOfCreatedSpots = mutableListOf<ParkingLot>()

    for (i in 0 until 20) {
        val spot = ParkingLot().createSpot(true)
        listOfCreatedSpots.add(spot)
    }

    fun checkIsFull(): Boolean {
        var occupiedSpots = 0
        for (i in 0 until listOfCreatedSpots.size) {
            if (!listOfCreatedSpots[i].isFree) {
                occupiedSpots ++
            }
        }
        return occupiedSpots == listOfCreatedSpots.size
    }

    while (true) {
        val inputString = readln()
        when {
            inputString.startsWith("park") -> {
                if (checkIsFull()) {
                    println("Sorry, the parking lot is full.")
                } else {
                    for (i in 0 until listOfCreatedSpots.size) {
                        if (!listOfCreatedSpots[i].isFree) {
                            continue
                        } else {
                            listOfCreatedSpots[i].park(inputString, i + 1)
                            break
                        }
                    }
                }

            }
            inputString.startsWith("leave") -> {
                val numberOfSpot = inputString.split(" ")[1].toInt()
                listOfCreatedSpots[numberOfSpot - 1].leave(numberOfSpot)
            }
            inputString == "exit" -> {
                exitProcess(1)
            }
        }
    }
}

