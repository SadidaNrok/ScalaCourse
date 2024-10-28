package lectures.week3fp

class FunctionsFP {

}

object TestFunctionsFP extends App {
  val double: Int => Int = v1 => v1 * 2
  val double2 = { (v1: Int) =>
    v1 * 2
  }

  val double3 = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  val double4: Int => Int = _ * 2
  val double5 = (v1: Int) => v1 * 2


  val sum = (a: Int, b: Int, c: Int) => a + b + c
  var sum2 = (a: Int, b: Int, c: Int) => a + b + c
  //val sum3 = (a, b, c) => a + b + c
  //val sum4: (Int, Int, Int => Int) = (a, b, c) => a + b + c
  val sum5: (Int, Int, Int) => Int = (a, b, c) => a + b + c

  val strlen = (a: String) => a.length

  println(strlen("Hello World"))
  def multiply(x: Int) = (y: Int) => x * y
  val result = multiply(2)(3)
  println(result) // 6

  val multiplyByTwo: Int => Int = multiply(2) // передали только один параметр

  val result1 = multiplyByTwo(3) // передали оставшийся параметр
  val result2 = multiplyByTwo(4) // передали оставшийся параметр

  println(result1) // 6
  println(result2) // 8

  //def createUrl(baseUrl: String, path: String) = s"https://${baseUrl}/${path}"
  def createUrl(baseUrl: String) = (path: String) => s"https://${baseUrl}/${path}"

  /*val stepikLogin = createUrl("stepik.org", "login" )
  val stepikContact = createUrl("stepik.org", "contact" )
  val mailLogin = createUrl("mail.google.com", "login")*/

  val stepikUrl = createUrl("stepik.org")
  val mailUrl = createUrl("mail.google.com")

  val stepikLogin = stepikUrl("login")
  val stepikContact = stepikUrl("contact")
  val mailLogin = mailUrl("login")

  println(stepikLogin)
  println(stepikContact)
  println(mailLogin)

  def someFuncOld: Int => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  def someFunc: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  def someFunc2: Int => (Int => Int) = (x) => (y) => x + y
  def someFunc3 = (x: Int) => (y: Int) => x + y
  def someFunc4: Int => ((Int) => Int) = (x: Int) => (y: Int) => x + y
  println(someFunc4(2)(4))
}