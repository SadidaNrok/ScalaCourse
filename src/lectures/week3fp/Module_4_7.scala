package lectures.week3fp

object Module_4_7 extends App {
  def divide(a: Int, b: Int): Either[String, Int] = {
    if (b == 0)
      Left("You can't divide by zero") else
      Right(a / b)
  }


  val result = for {
    res1 <- divide(4, 2)
    res2 <- divide(9, 3)
  } yield (res1 * res2)


  println(result)

  val result2 = divide(4, 2).map(res1 =>
    divide(9, 3).map(res2 => res1 * res2)
  )

  println(result2)

  val result3 = divide(4, 2).flatMap(res1 =>
    divide(9, 3).map(res2 => res1 * res2)
  )

  println(result3)

  case class Course(id: Int, title: String)
  case class Error(id: Int, message: String)

  def getEnrolledCourses(): List[Either[Error, Course]] = {
    List(Right(Course(2,"Scala For Beginners")), Right(Course(3,"Learn Scala")), Right(Course(4,"Learn")))
  }

  getEnrolledCourses()
    .filter(n => n.isRight && n.toOption.get.title.contains("Scala"))
    .flatMap(n => n.toOption)
    .foreach(println(_))

  trait Position
  case object Account extends Position
  case object Marketing extends  Position

  case class Employee(name: String, position: Position)
  def loadFromDb(): List[Employee] = {
    println("Loading From DB...")
    List(Employee("Alice", Account), Employee("Bob", Marketing))
  }

  def display(employee: List[Employee]): Unit = {
    //lazy val res = employee
    println(s"First Print: $employee")
    println(s"Second Print: $employee")
  }

  display(loadFromDb())
}
