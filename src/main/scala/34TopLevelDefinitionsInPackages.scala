object TopLevelDefinitionsInPackages extends App
// Often, it is convenient to have definitions accessible
// across an entire package, and not need to invent a name
// for a wrapper object to contain them.

// In Scala 3, any kind of definition can be declared at the top
// level of a package. For example, classes, enums, methods and variables.
// Any definitions placed at the top level of a package are considered
// members of the package itself.
// In Scala 2 top-level method, type and variable definitions had
// to be wrapped in a package object.


// in file gardening/fruits/Fruit.scala
package gardening.fruits:

  case class Fruit(name: String, color: String)

// Now assume you want to place a variable planted and a method
// showFruit directly into package gardening.fruits. Here’s how this is done:

import gardening.fruits.Fruit

object Apple extends Fruit("Apple", "green")

object Plum extends Fruit("Plum", "blue")

object Banana extends Fruit("Banana", "yellow")

val planted: Seq[Fruit] = List(Apple, Plum, Banana)
def showFruit(fruit: Fruit): Unit =
  println(s"${fruit.name}s are ${fruit.color}")

// As an example of how to use this, the following program imports planted
// and showFruit in exactly the same way it imports class Fruit, using
// a wildcard import on package gardening.fruits:
@main def printPlanted(): Unit =
  for fruit <- planted do
    showFruit(fruit)


// Aggregating Several Definitions at the Package Level
// Often, your project may have several reusable definitions defined in
// various modules, that you wish to aggregate at the top level of a package.

//For example, some helper methods in the trait FruitHelpers and some term/type
// aliases in trait FruitAliases. Here is how you can put all their definitions
// at the level of the fruit package:

// In Scala 3, it is preferred to use export to compose members from several
// objects into a single scope. Here we define private objects that mix in
// the helper traits, then export their members at the top level:

package gardening.fruits:
  private object FruitAliases
  private object FruitHelpers
  export FruitHelpers.*, FruitAliases.*