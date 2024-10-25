package lectures.week2oop

class Course(courseId: Int, title: String, val releaseYear: String, instructor: Instructor) {
  def getId(): String = {
    courseId.toString + instructor.instructorId
  }

  def isTaughtBy(instructor: Instructor): Boolean = {
    instructor.instructorId == this.instructor.instructorId &&
      instructor.fullName() == this.instructor.fullName()
  }

  def copyCourse(newReleaseYear: String): Course = {
    new Course(this.courseId, this.title, newReleaseYear, this.instructor)
  }
}

object Course {
  val obj = 30
  def Create(courseId: Int,
             title: String,
             releaseYear: String,
             instructorId: Int,
             instructorName: String,
             instructorSurname: String
            ): Course = {
    new Course(courseId, title, releaseYear,
      new Instructor(instructorId, instructorName, instructorSurname))
  }
}