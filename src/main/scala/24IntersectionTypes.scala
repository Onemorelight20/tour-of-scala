object IntersectionTypesAkaCompoundTypes extends App {
  // Sometimes it is necessary to express that the type of an
  // object is a subtype of several other types.

  // In Scala this can be expressed with the help of intersection
  // types, (or compound types in Scala 2) which are types that
  // behave like any part of the intersection.
  trait Cloneable extends java.lang.Cloneable:
    override def clone(): Cloneable =  // makes clone public
      super.clone().asInstanceOf[Cloneable]
  trait Resetable:
    def reset: Unit

  // Now suppose we want to write a function cloneAndReset which
  // takes an object, clones it and resets the original object:
  """
    |  def cloneAndReset(obj: ?): Cloneable =
    |    val cloned = obj.clone()
    |    obj.reset
    |    cloned
    |""".stripMargin

  // The question arises what the type of the parameter obj is. If
  // it’s Cloneable then the object can be cloned, but not reset;
  // if it’s Resetable we can reset it, but there is no clone operation.
  // To avoid type casts in such a situation, we can specify the type
  // of obj to be both Cloneable and Resetable.
  def cloneAndReset(obj: Cloneable & Resetable): Cloneable =
    val cloned = obj.clone()
    obj.reset
    cloned
  // Note that you can have more than two types: A & B & C & ....
  // And & is associative, so parentheses can be added around any
  // part without changing the meaning.
}