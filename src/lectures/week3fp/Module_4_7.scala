package lectures.week3fp

import scala.math.BigDecimal.int2bigDecimal

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


  val title: String = "Company-A"
  val employeeCount: Int = 100
  val retiredCount: Int = 10

  case class Company(title: String, employeeCount: Int) {

  }

  object Company {
    implicit class Manager(company: Company) {
      def -(peopleCount: Int): Company = company.copy(employeeCount = employeeCount - peopleCount)
    }
  }

  val company = Company("ggg", 22)
  val company2 = company.copy(title = "sdfsdf")

  println(Company(title, employeeCount) - retiredCount)

  trait SeniorityLevel

  object SeniorityLevel {
    case object Junior extends SeniorityLevel
    case object Middle extends SeniorityLevel
    case object Senior extends SeniorityLevel
  }

  case class Developer(
                        name: String,
                        level: SeniorityLevel,
                        progLanguage: List[String]
                      )

  val developers: List[Developer] = {
    List(
      Developer("Sam", SeniorityLevel.Junior, List("Scala", "Java", "SQL")),
      Developer("Pam", SeniorityLevel.Middle, List("Scala", "Java", "SQL")),
      Developer("Rom", SeniorityLevel.Middle, List("Scala", "Java")),
      Developer("Go", SeniorityLevel.Senior, List("Scala", "Java", "SQL", "C++")),
      Developer("Howard", SeniorityLevel.Senior, List("SQL", "C++"))
    )
  }

  val foundDevs: List[String] =
    developers
      .filter(n => n.level == SeniorityLevel.Middle || n.level == SeniorityLevel.Senior)
      .filter(n => n.progLanguage.length >= 3)
      .map(n => n.name)

  println(foundDevs)

  val capacity = 1000000000
  val power = 4
  //val powerOfTwo = LazyList.fill(capacity)(2.toBigInt).scanLeft(2.toBigInt)(_ * _).take(power).#::(1).toList.last
  val powerOfTwo = LazyList
    .fill(capacity)(2.toBigInt)
    .take(power)
    .foldLeft(1.toBigInt)((r, c) => r * c)

  println(powerOfTwo)

}
