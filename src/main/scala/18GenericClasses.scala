
object GenericClasses extends App:
  // Generic classes are classes which take a type as a parameter.
  // They are particularly useful for collection classes.
  class Stack[A]:
    private var elements: List[A] = Nil
    def push(x: A): Unit =
      elements = x :: elements
    def peek: A = elements.head
    def pop(): A =
      val currentTop = peek
      elements = elements.tail
      currentTop


  // Usage
  val stack = Stack[Int]
  stack.push(1)
  stack.push(2)
  println(stack.pop())  // prints 2
  println(stack.pop())  // prints 1

  // The instance stack can only take Ints. However, if the type
  // argument had subtypes, those could be passed in:
  class Fruit
  class Apple extends Fruit
  class Banana extends Fruit

  val stack1 = Stack[Fruit]
  val apple = Apple()
  val banana = Banana()

  stack1.push(apple)
  stack1.push(banana)
  // Note: subtyping of generic types is *invariant*. This means that
  // if we have a stack of characters of type Stack[Char] then it
  // cannot be used as an integer stack of type Stack[Int]. This would
  // be unsound because it would enable us to enter true integers into
  // the character stack. To conclude, Stack[A] is only a subtype of
  // Stack[B] if and only if B = A. Since this can be quite restrictive,
  // Scala offers a type parameter annotation mechanism to control the
  // subtyping behavior of generic types.
end GenericClasses
