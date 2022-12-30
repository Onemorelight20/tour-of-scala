import scala.math.{Pi, pow}

object SingletonObjects extends App :
  // An object is a class that has exactly one instance.
  // It is created lazily when it is referenced, like a lazy val.
  // As a top-level value, an object is a singleton.
  // As a member of an enclosing class or as a local value, it
  // behaves exactly like a lazy val.

  object Box

  object Logger:
    // The method info can be imported from anywhere in the program.
    // Creating utility methods like this is a common use case for
    // singleton objects.
    def info(message: String): Unit = println(s"INFO: $message")

  Logger.info("Execution of main")

  // Note: If an object is not top-level but is nested in another
  // class or object, then the object is “path-dependent” like any
  // other member. This means that given two kinds of beverages,
  // class Milk and class OrangeJuice, a class member object NutritionInfo
  // “depends” on the enclosing instance, either milk or orange juice.
  // milk.NutritionInfo is entirely distinct from oj.NutritionInfo.


  // Companion object
  // An object with the same name as a class is called a companion object.
  //  A companion class or object can access the private members of its
  //  companion. Use a companion object for methods and values which are
  //  not specific to instances of the companion class.
  case class Circle(radius: Double):

    import Circle.*

    def area: Double = calculateArea(radius)

  object Circle:
    private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)

  val circle1 = Circle(5.0)
  println(circle1.area)

  // The companion object can also contain factory methods:
  class Email(val username: String, val domainName: String)

  object Email:
    def fromString(emailString: String): Option[Email] =
      emailString.split('@') match
        case Array(a, b) => Some(Email(a, b))
        case _ => None

  val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
  scalaCenterEmail match
    case Some(email) => println(
      s"""Registered an email
         |Username: ${email.username}
         |Domain name: ${email.domainName}
       """.stripMargin)
    case None => println("Error: could not parse email")

  // Note: If a class or object has a companion, both must be
  // defined in the same file. To define companions in the REPL,
  // either define them on the same line or enter :paste mode.


  // Notes for Java programmers
  // static members in Java are modeled as ordinary members of
  // a companion object in Scala.
  // When using a companion object from Java code, the members
  // will be defined in a companion class with a static modifier.
  // This is called static forwarding. It occurs even if you haven’t
  // defined a companion class yourself.
end SingletonObjects
