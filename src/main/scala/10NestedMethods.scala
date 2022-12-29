import scala.annotation.tailrec

object NestedMethods extends App:
  // In Scala it is possible to nest method definitions.
  // The following object provides a factorial method for
  // computing the factorial of a given number:
  def factorial(x: Int): Int =
    @tailrec
    def fact(x: Int, acc: Int): Int =
      if x <= 1 then acc
      else fact(x - 1, acc * x)

    fact(x, 1)
  end factorial


  println("Factorial of 2: " + factorial(2))
  println("Factorial of 3: " + factorial(3))
end NestedMethods
