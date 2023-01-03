import scala.annotation.tailrec

object Annotations extends App {
  // Annotations associate meta-information with definitions.
  // For example, the annotation @deprecated before a method
  // causes the compiler to print a warning if the method is used.
  @deprecated("deprecation message", "release # which deprecates method")
  def hello = "hola"

  println(hello)
  // This will compile but the compiler will print a warning:
  // “there was one deprecation warning”.


  // An annotation clause applies to the first definition or declaration
  // following it. More than one annotation clause may precede a definition
  // and declaration. The order in which these clauses are given does not matter.


  // Annotations that ensure correctness of encodings
  // Certain annotations will actually cause compilation to fail if a condition(s)
  // is not met. For example, the annotation @tailrec ensures that a method is
  // tail-recursive. Tail-recursion can keep memory requirements constant. Here’s
  // how it’s used in a method which calculates the factorial:
  def factorial(x: Int): Int =
    @tailrec
    def factorialHelper(x: Int, accumulator: Int): Int =
      if x == 1 then accumulator else factorialHelper(x - 1, accumulator * x)
    factorialHelper(x, 1)

  // The factorialHelper method has the @tailrec which ensures the method is
  // indeed tail-recursive. If we were to change the implementation of
  // factorialHelper to the following, it would fail:
  def factorialNotTailRec(x: Int): Int =
    // @tailrec <<
    def factorialHelper(x: Int): Int =
      if x == 1 then 1 else x * factorialHelper(x - 1)
    factorialHelper(x)
  // We would get the message “Recursive call not in tail position”.


  // Annotations affecting code generation
  // Some annotations like @main affect the generated code (i.e. your jar
  // file might have different bytes than if you hadn’t used the annotation).
  // A @main annotation on a method generates an executable program that
  // calls the method as an entry point.

  // Java Annotations
  // When writing Scala code which interoperates with Java, there are a few
  // differences in annotation syntax to note. Note: Make sure you use the
  // -target:jvm-1.8 option with Java annotations.

  // Java has user-defined metadata in the form of annotations. A key feature
  // of annotations is that they rely on specifying name-value pairs to
  // initialize their elements. For instance, if we need an annotation to track
  // the source of some class we might define it as
  """
    |@interface Source {
    |  public String url();
    |  public String mail();
    |}
    |""".stripMargin
  // And then apply it as follows
  """
    |@Source(url = "https://coders.com/",
    |        mail = "support@coders.com")
    |public class MyJavaClass extends TheirClass ...
    |""".stripMargin

  // An annotation application in Scala looks like a constructor invocation,
  // for instantiating a Java annotation one has to use named arguments:
  """
    |@Source(url = "https://coders.com/",
    |        mail = "support@coders.com")
    |class MyScalaClass ...
    |""".stripMargin

  // This syntax is quite tedious if the annotation contains only one element
  // (without default value) so, by convention, if the name is specified as
  // value it can be applied in Java using a constructor-like syntax:
  """
    |@interface SourceURL {
    |    public String value();
    |    public String mail() default "";
    |}
    |""".stripMargin
  // And then apply it as follows:
  """
    |@SourceURL("https://coders.com/")
    |public class MyJavaClass extends TheirClass ...
    |""".stripMargin
  // In this case, Scala provides the same possibility:
  """
    |@SourceURL("https://coders.com/")
    |class MyScalaClass ...
    |""".stripMargin
}