import java.util.Vector

class Movie(val title: String, var priceCode: Int) {
  companion object {
    const val CHILDRENS = 2
    const val REGULAR = 0
    const val NEW_RELEASE = 1
  }
}

class Rental(val movie: Movie, val daysRented: Int)

class Customer(val name: String, private val rentals: Vector<Rental>) {

  fun addRental(arg: Rental) {
    rentals.add(arg)
  }

  fun statement(): String {
    var totalAmount = 0.0;
    var frequentRenterPoints = 0;
    var result = "Rental Record for $name \n"

    for (each in rentals) {
      var thisAmount = 0.0

      when (each.movie.priceCode) {
        Movie.REGULAR -> {
          thisAmount += 2
          if (each.daysRented > 2) {
            thisAmount += (each.daysRented - 2) * 15
          }
        }
        Movie.NEW_RELEASE -> {
          // 新片
          thisAmount += each.daysRented * 3
        }
        Movie.CHILDRENS -> {
          thisAmount += 1.5
          if (each.daysRented > 3) {
            thisAmount += (each.daysRented - 3) * 1.5
          }
        }
      }

      // add frequent renter points (累加 常客積點)
      frequentRenterPoints++

      // add bonus for a two day new release rental
      if (each.movie.priceCode == Movie.NEW_RELEASE && each.daysRented > 1) {
        frequentRenterPoints++
      }

      result += "\t ${each.movie.title}\t$thisAmount\n"
      totalAmount += thisAmount
    }

    // add footer lines
    result += "Amount owed is $totalAmount \n"
    result += "You earned $frequentRenterPoints frequent renter pniots"
    return result
  }
}

fun main(args: Array<String>) {

  println("Hello, world! Kotlin")
}