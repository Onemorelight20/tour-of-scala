object NamedArguments {
  def main(args: Array[String]): Unit = {
    printName("V", "Z")
    printName(first = "V", last = "Zelensky")
    printName(last = "Zelensky", first = "V")

    // However, if some arguments are named and others are not,
    // the unnamed arguments must come first and in the order
    // of their parameters in the method signature.

    //    printName(last = "Smith", "john") <- error
  }

  def printName(first: String, last: String): Unit =
    println(s"$first $last")
}
