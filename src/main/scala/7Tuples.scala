object Tuples extends App {
  // In Scala, a tuple is a value that contains a fixed
  // number of elements, each with its own type. Tuples
  // are immutable.

  // Tuples are especially handy for returning multiple
  // values from a method.

  // A tuple with two elements can be created as follows:
  val ingredient1: (String, Int) = Tuple2("sugar", 25)
  val ingredient2: (String, Int) = ("salt", 24)

  println(ingredient1(0))
  println(ingredient2(1))

  // Pattern matching on tuples
  val (name, quantity) = ingredient1
  println(s"name is $name")     // Sugar
  println(s"quantity is $quantity") // 25

  val planets =
    List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6),
      ("Mars", 227.9), ("Jupiter", 778.3))
  planets.foreach {
    case ("Earth", distance) =>
      println(s"Our planet is $distance million kilometers from the sun")
    case _ =>
  }


  val numPairs = List((2, 5), (3, -7), (20, 56))
  for (a, b) <- numPairs do
    println(a * b)

  // Tuples and case classes
  // Users may sometimes find it hard to choose
  // between tuples and case classes. Case classes
  // have named elements. The names can improve the
  // readability of some kinds of code. In the planet
  // example above, we might define
  // case class Planet(name: String, distance: Double)
  // rather than using tuples.
}
