object ClassCompositionWithMixins extends App {
  // Mixins are traits which are used to compose a class.
  abstract class A:
    val message: String

  class B extends A :
    val message = "I'm an instance of class B"

  trait C extends A :
    def loudMessage: String = message.toUpperCase()

  class D extends B, C

  // Class D has a superclass B and a mixin C.
  // Classes can only have one superclass but
  // many mixins (using the keyword extends and
  // the separator , respectively). The mixins
  // and the superclass may have the same supertype.

  val d = D()
  println(d.message)
  println(d.loudMessage)

  abstract class AbsIterator:
    type T

    def hasNext: Boolean

    def next(): T

  class StringIterator(s: String) extends AbsIterator :
    type T = Char
    private var i = 0

    def hasNext: Boolean = i < s.length

    override def next(): Char =
      val ch = s charAt i
      i += 1
      ch

  trait RichIterator extends AbsIterator:
    def foreach(f: T => Unit): Unit = while hasNext do f(next())

  // This trait implements foreach by continually calling
  // the provided function f: T => Unit on the next element
  // (next()) as long as there are further elements (while hasNext).
  // Because RichIterator is a trait, it doesn’t need to implement
  // the abstract members of AbsIterator.


  // We would like to combine the functionality of StringIterator and RichIterator into a single class.
  class RichStringIter extends StringIterator("Scala"), RichIterator
  val richStringIter = RichStringIter()
  richStringIter.foreach(println)

  // The new class RichStringIter has StringIterator as a superclass and RichIterator as a MIXIN.
  // With single inheritance we would not be able to achieve this level of flexibility.
}
