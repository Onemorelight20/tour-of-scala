import scala.util.matching.Regex

object RegularExpressionPatterns {
  def main(args: Array[String]): Unit = {
    // Any string can be converted to a regular expression using the .r method.
    val numberPattern: Regex = "[0-9]".r

    numberPattern.findFirstMatchIn("awesomepassword") match
      case Some(_) => println("Password OK")
      case None => println("Password must contain a number")


    val keyValPattern: Regex = "([0-9a-zA-Z- ]+): ([0-9a-zA-Z-#()/. ]+)".r
    val input: String =
      """background-color: #A03300;
        |background-image: url(img/header100.png);
        |background-position: top center;
        |background-repeat: repeat-x;
        |background-size: 2160px 108px;
        |margin: 0;
        |height: 108px;
        |width: 100%;""".stripMargin
    for patternMatch <- keyValPattern.findAllMatchIn(input) do
      println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")


    // Moreover, regular expressions can be used as patterns
    // (in match expressions) to conveniently extract
    // the matched groups:

    def saveContactInfomation(contact: String): Unit =
      import scala.util.matching.Regex
      val emailPattern: Regex = """^(\w+)@(\w+(.\w+)+)$""".r
      val phonePattern: Regex = """^(\d{3}-\d{3}-\d{4})$""".r
      contact match
        case emailPattern(localPart, domainName, _) =>
          println(s"Hi $localPart, we have saved your email address.")
        case phonePattern(phoneNumber) =>
          println(s"Hi, we have saved your phone number $phoneNumber.")
        case _ =>
          println("Invalid contact information, neither an email address nor phone number.")

    saveContactInfomation("123-456-7890")
    saveContactInfomation("JohnSmith@sample.domain.com")
    saveContactInfomation("2 Franklin St, Mars, Milky Way")
  }
}
