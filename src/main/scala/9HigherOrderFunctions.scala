object HigherOrderFunctions extends App :
  // Higher order functions take other functions as parameters
  // or return a function as a result. This is possible because
  // functions are first-class values in Scala. The terminology
  // can get a bit confusing at this point, and we use the phrase
  // “higher order function” for both methods and functions that
  // take functions as parameters or that return a function.

  val salaries = Seq(20_000, 70_000, 40_000)
  val doubleSalary = (x: Int) => x * 2
  val newSalaries = salaries.map(doubleSalary) // List(40000, 140000, 80000)

  // To shrink the code, we could make the function
  // anonymous and pass it directly as an argument to map:
  val newSalariesShrunk = salaries.map(x => x * 2)

  // Notice how x is not declared as an Int in the above example.
  // That’s because the compiler can infer the type based on the
  // type of function map expects (see Currying). An even more
  // idiomatic way to write the same piece of code would be:
  val newSalariesEvenMoreShrunk = salaries.map(_ * 2)

  // Coercing methods into functions
  // It is also possible to pass methods as arguments to
  // higher-order functions because the Scala compiler will
  // coerce the method into a function.
  case class WeeklyWeatherForecast(temperatures: Seq[Double]):
    private def convertCtoF(temp: Double) = temp * 1.8 + 32

    def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF) // <-- passing the method convertCtoF
  end WeeklyWeatherForecast

  // Functions that accept functions
  // One reason to use higher-order functions is to reduce
  // redundant code. Let’s say you wanted some methods that
  // could raise someone’s salaries by various factors. Without
  // creating a higher-order function, it might look something
  // like this:
  object SalaryRaiser:
    def smallPromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * 1.1)

    def greatPromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * math.log(salary))

    def hugePromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * salary)
  end SalaryRaiser

  // Notice how each of the three methods vary only by the
  // multiplication factor. To simplify, you can extract
  // the repeated code into a higher-order function like so:
  object SalaryRaiserGeneralised:
    private def promotion(salaries: List[Double], promotionFunction: Double => Double): List[Double] =
      salaries.map(promotionFunction)

    def smallPromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * 1.1)

    def greatPromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * math.log(salary))

    def hugePromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * salary)
  end SalaryRaiserGeneralised

  // Functions that return functions
  def urlBuilder(ssl: Boolean, domainName: String): (String, String) => String =
    val schema = if ssl then "https://" else "http://"
    (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"

  val domainName = "www.example.com"
  def getURL = urlBuilder(ssl=true, domainName)
  val endpoint = "users"
  val query = "id=1"
  val url = getURL(endpoint, query) // "https://www.example.com/users?id=1": String
  println(url)
end HigherOrderFunctions
