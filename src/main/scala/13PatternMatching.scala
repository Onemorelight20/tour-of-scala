import scala.util.Random

object PatternMatching extends App :
  // Pattern matching is a mechanism for checking a value against a pattern.
  // A successful match can also deconstruct a value into its constituent
  // parts. It is a more powerful version of the switch statement in Java
  // and it can likewise be used in place of a series of if/else statements.


  // A match expression has a value, the match keyword, and at least one case clause.
  val x: Int = Random.nextInt(10)
  val strNum = x match
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  println(strNum)

  def matchTest(x: Int): String = x match
    case 1 => "one"
    case 2 => "two"
    case _ => "other"


  // Matching on case classes
  sealed trait Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification

  def showNotification(notification: Notification): String =
    notification match
      case Email(sender, title, _) =>
        s"You got an email from $sender with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"You received a Voice Recording from $name! Click the link to hear it: $link"

  val someSms = SMS("12345", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
  // prints You got an SMS from 12345! Message: Are you there?
  println(showNotification(someSms))
  // prints You received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
  println(showNotification(someVoiceRecording))


  // Pattern guards
  // Pattern guards are boolean expressions which are used to
  // make cases more specific. Just add if <boolean expression>
  // after the pattern.
  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String =
    notification match
      case Email(sender, _, _) if importantPeopleInfo.contains(sender) =>
        "You got an email from special someone!"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "You got an SMS from special someone!"
      case other =>
        showNotification(other) // nothing special, delegate to our original showNotification function

  val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

  val someSms1 = SMS("123-4567", "Are you there?")
  val someVoiceRecording1 = VoiceRecording("Tom", "voicerecording.org/id/123")
  val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
  val importantSms = SMS("867-5309", "I'm here! Where are you?")

  // prints You got an SMS from 123-4567! Message: Are you there?
  println(showImportantNotification(someSms1, importantPeopleInfo))
  // prints You received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
  println(showImportantNotification(someVoiceRecording1, importantPeopleInfo))
  // prints You got an email from special someone!
  println(showImportantNotification(importantEmail, importantPeopleInfo))
  // prints You got an SMS from special someone!
  println(showImportantNotification(importantSms, importantPeopleInfo))

  // Matching on type only
  sealed trait Device
  case class Phone(model: String) extends Device:
    def screenOff = "Turning screen off"

  case class Computer(model: String) extends Device:
    def screenSaverOn = "Turning screen saver on..."

  def goIdle(device: Device): String = device match
    case p: Phone => p.screenOff
    case c: Computer => c.screenSaverOn


  // Sealed types
  // This provides extra safety because the compiler checks
  // that the cases of a match expression are exhaustive when
  // the base type is sealed.
  // On the flip side, exhaustivity checking requires you to define
  // all the subtypes of the base type in the same file as the base
  // type (otherwise, the compiler would not know what are all the
  // possible cases). For instance, if you try to define a new type
  // of Notification outside of the file that defines the sealed trait
  // Notfication, it will produce a compilation error:
  """
    |case class Telepathy(message: String) extends Notification
    |           ^
    |        Cannot extend sealed trait Notification in a different source file
    |""".stripMargin
end PatternMatching

