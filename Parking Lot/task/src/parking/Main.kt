package parking

fun main() {

    class ParkingLot(var carParkingNow: String = "empty") {

        fun justParked(car: String) {
            carParkingNow = car
            println("$car car just parked here.")
        }

        fun leftParking() {
            println("$carParkingNow car left the parking lot.")
            carParkingNow = "empty"
        }

        fun parkingNow() {
            println("$carParkingNow car has parked.")
        }
    }

    val lot1 = ParkingLot("White")
    val lot2 = ParkingLot("Yellow")
    val lot3 = ParkingLot()

    lot1.parkingNow()
    lot2.leftParking()
    lot3.justParked("Green")
}
