import scala.annotation.tailrec

object MultipleParameterLists extends App {
  // Methods may have multiple parameter lists.
  def abc(a: String)(b: String)(c: String): Unit =
    println(s"""first parameter list contains $a, second $b and third $c""")

  abc("Alphabet")("Boo")("Catch")

  """
    |trait Iterable[A]:
    |  ...
    |  def foldLeft[B](z: B)(op: (B, A) => B): B
    |  ...
    |""".stripMargin
  // foldLeft applies a two-parameter function op
  // to an initial value z and all elements of this
  // collection, going left to right. Shown below is
  // an example of its usage.
  val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val res = numbers.foldLeft(0)((m, n) => m + n)
  println(res) // 55

  // Use cases for multiple parameter lists include:
  // - Drive type inference:
  //   It so happens that in Scala, type inference proceeds
  //   one parameter list at a time. Say you have the following
  //   method:
  @tailrec
  def foldLeft1[A, B](as: List[A], b0: B, op: (B, A) => B): B =
    if as.isEmpty then b0
    else foldLeft1(as.tail, op(b0, as.head), op)
  // Then you’d like to call it in the following way, but will
  // find that it doesn't compile:
  def notPossible = foldLeft1(numbers, 0, _ + _)
  println(notPossible) // COMPILES (?!?!?)
  // you will have to call it like one of the below ways:
  def firstWay = foldLeft1[Int, Int](numbers, 0, _ + _)
  def secondWay = foldLeft1(numbers, 0, (a: Int, b: Int) => a + b)
  // great solution is gonna look like this
  def foldLeft2[A, B](as: List[A], b0: B)(op: (B, A) => B) = ???
  def possible = foldLeft2(numbers, 0)(_ + _)

  // - Implicit parameters
  // To specify only certain parameters as implicit,
  // they must be placed in their own implicit parameter list.
  def execute(arg: Int)(using ec: scala.concurrent.ExecutionContext) = ???

  // - Partial application
  // When a method is called with a fewer number of parameter
  // lists, then this will yield a function taking the missing
  // parameter lists as its arguments. This is formally known
  // as partial application.
  val numberFunc = numbers.foldLeft(List[Int]()) _
  val squares = numberFunc((xs, x) => xs :+ x*x)
  println(squares) // List(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
  val cubes = numberFunc((xs, x) => xs :+ x*x*x)
  println(cubes)  // List(1, 8, 27, 64, 125, 216, 343, 512, 729, 1000)

  // Comparison with “currying”
  // Currying is the technique of converting a function that takes
  // multiple arguments into a sequence of functions that each takes
  // a single argument

  // We discourage the use of the word “curry” in reference to
  // Scala’s multiple parameter lists, for two reasons:
  //1) In Scala, multiple parameters and multiple parameter lists
  // are specified and implemented directly, as part of the
  // language, rather being derived from single-parameter functions.
  //2) There is danger of confusion with the Scala standard library’s
  // curried and uncurried methods, which don’t involve multiple
  // parameter lists at all.

  //Regardless, there are certainly similarities to be found
  // between multiple parameter lists and currying. Though they
  // are different at the definition site, the call site might
  // nonetheless look identical, as in this example:

  // version with multiple parameter lists
  def addMultiple(n1: Int)(n2: Int) = n1 + n2
  // two different ways of arriving at a curried version instead
  def add(n1: Int, n2: Int) = n1 + n2
  val addCurried1 = (add _).curried
  val addCurried2 = (n1: Int) => (n2: Int) => n1 + n2
  // regardless, all three call sites are identical
  addMultiple(3)(4)  // 7
  addCurried1(3)(4)  // 7
  addCurried2(3)(4)  // 7
}
