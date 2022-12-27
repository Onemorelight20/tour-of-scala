/*
Classes in Scala are blueprints for creating objects.
They can contain methods, values, variables, types, objects,
traits, and classes which are collectively called members.
*/

class User
class Point(var x: Int = 0, var y: Int = 0):
  def mode(dx: Int, dy: Int): Unit =
    x += dx
    y += dy

  override def toString: String = s"($x, $y)"

end Point

object Classes {
  def main(args: Array[String]): Unit = {
    val user1 = User()

    val origin = Point()
    val point1 = Point(2, 8)
    println(point1)

    val point2 = Point(y = 3)
    println(point2)
  }
}

class FancyPoint:
  private var _x = 0
  private var _y = 0
  private val bound = 100

  def x: Int = _x
  def x_=(newValue: Int): Unit =
    if newValue < bound then
      _x = newValue
    else
      printWarning()

  def y: Int = _y
  def y_=(newValue: Int): Unit =
    if newValue < bound then
      _y = newValue
    else
      printWarning()

  private def printWarning(): Unit =
    println("WARNING: Out of bounds")
end FancyPoint