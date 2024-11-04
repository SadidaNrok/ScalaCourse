package lectures.week3fp

import scala.util.{Try, Success, Failure}

object TestImplicits extends App {
  implicit class Course(title: String) {
    def displayInfo(): Unit = println(s"This is $title course")
    def displayPlatform(implicit name: String) = println(s"Course platform: $name")
  }

  //implicit def toCourse(title: String): Course = Course(title)
  implicit val defaultPlatform = "Stepik"

  "Scala".displayInfo() // This is Scala course
  "Scala".displayPlatform // Course platform: Stepik
}

object TestImplicits2 extends App {
  case class Course(id: Int, title: String, studentCount: Int)

  val courses = Seq(
    Course(0, "Scala", 100),
    Course(1, "Advanced Scala", 200),
    Course(4, "Spark", 300),
    Course(3, "Cats", 400)
  )

  object Course {
    implicit val defaultOrdering: Ordering[Course] =
      Ordering.fromLessThan((a, b) => a.studentCount < b.studentCount)
  }

  object OrderingById {
    implicit val orderById: Ordering[Course] =
      Ordering.fromLessThan((a, b) => a.id < b.id)
  }

  object OrderingByTitle {
    implicit val orderByTitle: Ordering[Course] =
      Ordering.fromLessThan((a, b) => a.title.compareTo(b.title) < 0)
  }

  //import OrderingById._
  println(courses.sorted)
  println(courses.sorted(OrderingById.orderById))
  println(courses.sorted(Ordering.by((course: Course) => course.title)))
  println(courses.sortBy(_.title))
}

object TestImplicits3 extends App {
  case class Course(id: Int, title: String, studentCount: Int)

  val courses = List(
    Course(0, "Scala", 10),
    Course(3, "Advanced Scala", 20),
    Course(2, "Spark", 4),
    Course(1, "Cats", 10)
  )

  implicit val userOrdering: Ordering[Course] =
    Ordering.by(cource => (cource.studentCount, cource.id))

  println(courses.sorted)
}

object TestImplicits4 extends App {
  val age: String = "34"

  case class Employee(age: Int) {
    def displayAge(): Unit = println(s"Employee's age is $age")
  }

  object Employee {
    implicit def createEmployee(age: String): Employee = Employee(age.toInt)
  }

  object Helper {
    implicit def createInt(implicit value: String): Int = value.toInt
  }


  import Employee.createEmployee
  age.displayAge()

  def getA(): String = null

  def getB(): Option[String] = Option("value B")

  val result = Option(getA()).orElse(getB())


  def divide(a: Int, b: Int): Try[Int] = Try(a / b)

  val result2: Try[Int] = divide(6, 0)

  result2 match {
    case Success(v) =>
      println("Result is: " + v)
    case Failure(e) =>
      println("Failed to  divide: " + e.getMessage)
  }

  def firstMethod() = Success(new RuntimeException("Exception"))
  def secondMethod(): Try[String] = Try("Success")

  println(firstMethod().orElse(secondMethod()))

  def divide2(a: Int, b: Int): Either[String, Int] = {
    if (b == 0)
      Left("You can't divide by zero")
    else
      Right(a / b)
  }

  val result3: Either[String, Int] = divide2(4, 0)

  println(result3.getOrElse(0))

  result3 match {
    case Left(msg) => println(s"Failed to divide: $msg")
    case Right(res) => println(s"Result is: $res")
  }

  println(result3.isRight) // false
  println(result3.isLeft) // true

  case class Course(
                     id: Int,
                     title: String,
                     level: String)

  val courses = Seq(
    Course(
      id = 1,
      title = "Programming in Scala",
      level = "Beginner"),
    Course(
      id = 2,
      title = "English",
      level = "Advanced"),
    Course(
      id = 3,
      title = "Programming in Java",
      level = "Beginner"),
  )

  def findCourses(
                   courses: Seq[Course],
                   lookupFunction: Course => Boolean)
  : Seq[Course] = {

    courses.filter(lookupFunction)
  }

  val hasTitle: Seq[String] => Course => Boolean = {
    titles => course => titles.contains(course.title)
  }

  val hasDifficultyLevel: String => Course => Boolean = {
    level => course => course.level.equals(level)
  }

  val searchResult: Seq[Course] = findCourses(courses, hasDifficultyLevel("Beginner"))

  searchResult.foreach(println)

  val searchResult2: Seq[Course] =
    findCourses(courses, hasTitle(Seq("Programming", "English")))

  searchResult2.foreach(println)

  def someFunc(aList: List[Int]): Int =
    aList.foldLeft(0)((a, _) => a + 1)

  println(someFunc(List(1,2,3,4)))



  def double(aList: List[Int], func: Int => Int = {n => n * 2}): List[Int] = {
    aList.map(func)
  }

  println(double(List(1,2,3,4)))

  def f(aList: List[Int]): Int = {
    val res = aList.foldLeft((0, 1))((r, c) =>
      (r._1 + c, r._2 * c)
    )

    Try(res._2 / res._1).orElse(Try(0)).get
  }

  println(f(List(1, -1)))

  def doSomething(
                   condition: Boolean,
                   ifAction: Unit,
                   elseAction: Unit): Unit = {
    if (condition)
      ifAction
    else elseAction
  }

  doSomething(
    2 == 4,
    println("ifAction"),
    println("elseAction"))

  def callByName(n: => Int): Int = {
    lazy val t = n
    t * t * t
  }

  def double(n: Int): Int = {
    println(s"double $n")
    n * 2
  }



  println(callByName(double(1)))
}