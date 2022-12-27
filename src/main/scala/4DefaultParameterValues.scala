
object DefaultParameterValues extends App {
  def log(message: String, level: String = "INFO"): Unit = println(s"$level: $message")

  log("main method execution")

  // Scala doesn’t allow having two methods with default parameters
  // and with the same name (overloaded). An important reason why is
  // to avoid the ambiguity that can be caused due to the existence of
  // default parameters. To illustrate the problem, let’s consider the
  // method declarations provided below:
  object A:
    def func(x: Int = 34): Unit = println(x)
  //    def func(y: String = "abc"): Unit = println(y)

  A.func() // ???
}
