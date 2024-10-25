package lectures.week2oop

trait Saver {
  def save(value: Int): Unit = println(s"saved value $value")
}

trait Reader {
  def readValue: Int = 4
}

abstract class Operation extends Saver with Reader {
  def performOperation(num: Int): Unit
}

object TestOperation extends App {
  // использование анонимного класса
  val addition = new Operation {
    private def addTwo(num: Int) = num + 2
    override def performOperation(num: Int): Unit = println(addTwo(num))
  }

  addition.performOperation(5)
  addition.save(77)
  println(addition.readValue)

}