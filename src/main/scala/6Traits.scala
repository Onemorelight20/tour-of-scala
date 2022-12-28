
object Traits extends App{
  // Traits are used to share interfaces and fields between classes.
  // They are similar to Java 8’s interfaces. Classes and objects can
  // extend traits, but traits cannot be instantiated and therefore
  // have no parameters.
  trait HairColor

  // Use the extends keyword to extend a trait. Then
  // implement any abstract members of the trait using
  // the override keyword:
  trait Iterator[A]:
    def hasNext: Boolean
    def next(): A

  class IntIterator(to: Int) extends Iterator[Int]:
    private var current = 0
    override def hasNext: Boolean = current < to
    override def next(): Int =
      if hasNext then
        val t = current
        current += 1
        t
      else
        0
  end IntIterator

  val iterator = new IntIterator(10)
  iterator.next()  // returns 0
  iterator.next()  // returns 1
  println(iterator.next())

  // Subtyping
  // Where a given trait is required, a subtype
  // of the trait can be used instead.
  import scala.collection.mutable.ArrayBuffer

  trait Pet:
    val name: String

  class Cat(val name: String) extends Pet
  class Dog(val name: String) extends Pet

  val dog = Dog("Harry")
  val cat = Cat("Sally")

  val animals = ArrayBuffer.empty[Pet]
  animals.append(dog)
  animals.append(cat)
  animals.foreach(pet => println(pet.name))  // Prints Harry Sally
}
