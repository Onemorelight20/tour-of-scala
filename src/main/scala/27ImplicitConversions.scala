object ImplicitConversions extends App {
  // Implicit conversions are a powerful Scala feature that enable two common use cases:
  // - allow users to supply an argument of one type, as if it were another type,
  // to avoid boilerplate.
  // - in Scala 2, to provide additional members to closed classes (replaced by
  // extension methods in Scala 3).

  // In Scala 3, an implicit conversion from type S to type T is defined by a given
  // instance which has type scala.Conversion[S, T]. For compatibility with Scala 2,
  // they can also be defined by an implicit method (read more in the Scala 2 tab).

  // Implicit conversions are applied in two situations:
  // 1. If an expression e is of type S, and S does not conform to the expression’s expected type T.
  // 2. In a selection e.m with e of type S, if the selector m does not denote a member of S.
  // In the first case, a conversion c is searched for, which is applicable to e and
  // whose result type conforms to T.

  //An example is to pass a scala.Int, e.g. x, to a method that expects scala.Long.
  // In this case, the implicit conversion Int.int2long(x) is inserted.
  //
  //In the second case, a conversion c is searched for, which is applicable to e
  // and whose result contains a member named m.
  //
  //An example is to compare two strings "foo" < "bar". In this case, String has
  // no member <, so the implicit conversion Predef.augmentString("foo") < "bar"
  // is inserted. (scala.Predef is automatically imported into all Scala programs.)

  // Beware the power of implicit conversions
  // Because implicit conversions can have pitfalls if used indiscriminately
  // the compiler warns in two situations:
  // - when compiling a Scala 2 style implicit conversion definition.
  // - at the call site where a given instance of scala.Conversion is inserted as a conversion.

  // To turn off the warnings take either of these actions:
  // * Import scala.language.implicitConversions into the scope of:
  //  - a Scala 2 style implicit conversion definition
  //  - call sites where a given instance of scala.Conversion is inserted as a conversion.
  // * Invoke the compiler with -language:implicitConversions
}