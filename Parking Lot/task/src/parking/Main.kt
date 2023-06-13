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

    fun createParkingLot(input: String) {
        listOfCreatedSpots.clear()
        val spotsNumber = input.split(" ")[1].toInt()
        for (i in 0 until spotsNumber) {
            val spot = ParkingLot().createSpot(true)
            listOfCreatedSpots.add(spot)
        }
        println("Created a parking lot with $spotsNumber spots.")
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

    fun checkIsEmpty(): Boolean {
        var emptySpots = 0
        for (i in 0 until listOfCreatedSpots.size) {
            if (listOfCreatedSpots[i].isFree) {
                emptySpots ++
            }
        }
        return emptySpots == listOfCreatedSpots.size
    }

    fun park(input: String) {
        if (listOfCreatedSpots.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
        } else {
            if (checkIsFull()) {
                println("Sorry, the parking lot is full.")
            } else {
                for (i in 0 until listOfCreatedSpots.size) {
                    if (!listOfCreatedSpots[i].isFree) {
                        continue
                    } else {
                        listOfCreatedSpots[i].park(input, i + 1)
                        break
                    }
                }
            }
        }
    }

    fun leave(input: String) {
        if (listOfCreatedSpots.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
        } else {
            val numberOfSpot = input.split(" ")[1].toInt()
            listOfCreatedSpots[numberOfSpot - 1].leave(numberOfSpot)
        }
    }

    fun status() {
        if (listOfCreatedSpots.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
        } else {
            if (checkIsEmpty()) {
                println("Parking lot is empty.")
            } else {
                for (i in 0 until listOfCreatedSpots.size) {
                    if (listOfCreatedSpots[i].isFree) continue
                    println("${i + 1} ${listOfCreatedSpots[i].regNumber} ${listOfCreatedSpots[i].colorOfTheCar}")
                }
            }
        }
    }

    fun exit() {
        exitProcess(1)
    }

    while (true) {
        val inputString = readln()
        when {
            inputString.startsWith("create") -> createParkingLot(inputString)
            inputString.startsWith("park") -> park(inputString)
            inputString.startsWith("leave") -> leave(inputString)
            inputString == "status" -> status()
            inputString == "exit" -> exit()
        }
    }
}


