object Operators extends App {
  // In Scala, operators are methods. Any method with a single parameter
  // can be used as an infix operator. For example, + can be called with
  // dot-notation:
  println(10.+(34))
  // However, it’s easier to read as an infix operator: 10 + 34


  // Defining and using operators
  // You can use any legal identifier as an operator. This includes a name
  // like add or a symbol(s) like +.
  case class Vec(x: Double, y: Double):
    def +(that: Vec) = Vec(this.x + that.x, this.y + that.y)

  val vector1 = Vec(6.0, 1.0)
  val vector2 = Vec(2.0, 2.0)
  val vector3 = vector1 + vector2
  println(vector3)

  // The class Vec has a method + which we used to add vector1 and vector2.
  // Using parentheses, you can build up complex expressions with readable
  // syntax. Here is the definition of class MyBool which includes methods
  // and and or:
  case class MyBool(x: Boolean):
    def and(that: MyBool): MyBool = if x then that else this
    def or(that: MyBool): MyBool = if x then this else that
    def negate: MyBool = MyBool(!x)

  // It is now possible to use and and or as infix operators:
  def not(x: MyBool) = x.negate
  def xor(x: MyBool, y: MyBool) = (x or y) and not(x and y)
  println((not(MyBool(false)) and MyBool(true)) or MyBool(true))


  // Precedence
  // When an expression uses multiple operators, the operators are evaluated
  // based on the priority of the first character:
  """
    |(characters not shown below)
    |* / %
    |+ -
    |:
    |< >
    |= !
    |&
    |^
    ||
    |(all letters, $, _)
    |""".stripMargin

  // This applies to functions you define. For example, the following expression:
  // a + b ^? c ?^ d less a ==> b | c
  // Is equivalent to
  // ((a + b) ^? (c ?^ d)) less ((a ==> b) | c)

  // ?^ has the highest precedence because it starts with the character ?.
  // + has the second highest precedence, followed by ==>, ^?, |, and less.
}