object Basics {
  def main(args: Array[String]): Unit = {
    // Expressions are computable statements
    println(1) // 1
    println(1 + 1) // 2
    println("Hello!") // Hello!
    println("Hello," + " world!") // Hello, world!

    val x: Int = 1 + 1
    val y: Int = 1.+(1)
    //    x = 3  Does not compile

    var z = 1 + 1
    z = 3 // This compiles because "x" is declared with the "var" keyword.
    println(z * z) // 9

    // Blocks
    println({
      z = z * 10
      z
    }) // 30

    // Functions
    val sum1: Function2[Int, Int, Int] = new Function2[Int, Int, Int] :
      override def apply(v1: Int, v2: Int): Int = v1 + v2

    // syntactic sugar
    val sum2: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2
    val sum3: (Int, Int) => Int = _ + _
    println(sum2(15, 14))
    println(sum3(9999, 1))

    // Classes
    val greeter = new Greeter("Hello, ", "!")
    greeter.greet("Scala")

    // Case classes
    val point = Point(13, 3)
    println(point)

    IdFactory.create()
    IdFactory.create()
    IdFactory.create()
    val id: Int = IdFactory.create()
    println(id)
  }

  //  Methods look and behave very similar to functions,
  //  but there are a few key differences between them.
  //  Methods are defined with the def keyword. def is
  //  followed by a name, parameter list(s), a return type, and a body:

  def add(x: Int, y: Int): Int = {
    x + y
  }

  // A method can take multiple parameter lists:
  def addAndMultiplyAndDivide(x: Int, y: Int)(multiplyBy: Int)(divideBy: Int): Int = {
    (x + y) * multiplyBy / divideBy
  }
  
  case class Point(x: Int, y: Int)

}


class Greeter(prefix: String, suffix: String) {
  def greet(name: String): Unit =
    println(prefix + name + suffix)
}


/*
Scala has a special type of class called a “case” class. By default, instances of case classes are immutable,
and they are compared by value (unlike classes, whose instances are compared by reference). This makes them
additionally useful for pattern matching.
*/


// Objects are single instances of their own definitions. You can think of them as singletons of their own classes.
object IdFactory {
  private var counter = 0
  def create(): Int = {
    counter += 1
    counter
  }
}

/*
Traits are abstract data types containing certain fields and methods.
In Scala inheritance, a class can only extend one other class, but it
can extend multiple traits.
 */

trait Greetable
trait Compilable
abstract class BasicGreeter
class Greeter2 extends BasicGreeter, Compilable, Greetable
class Greeter3 extends BasicGreeter with Greetable with Compilable
