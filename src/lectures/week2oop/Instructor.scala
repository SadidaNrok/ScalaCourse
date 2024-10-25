package lectures.week2oop

class Instructor(val instructorId: Int, name: String, surname: String) {
  def fullName(): String = {
    s"${name.capitalize} ${surname.capitalize}"
  }
}

object Test extends App {
  var instructor1: Instructor = new Instructor(12, "max", "John")
  println(instructor1.fullName())
  println(instructor1.instructorId)
  var course: Course = new Course(32, "New course", "2012", instructor1)
  println(course.getId())
  println(course.isTaughtBy(instructor1))
  var instructor2: Instructor = new Instructor(12, "Max", "john")
  println(course.isTaughtBy(instructor2))
  var course2: Course = course.copyCourse("2022")
  println(course2.releaseYear)
  println(course.releaseYear)

  val course3 = Course.Create(53, "super course", "2030", 51, "malik", "italik")
  println(course3.releaseYear)
  println(Course.obj)
}