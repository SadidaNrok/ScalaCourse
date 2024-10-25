package lectures.week2oop

class DescriptionProvider[T] {
  def describe(value: T): Unit = println(s"${value.getClass.getSimpleName} value $value")
}

abstract class Customer {
  def isVegetarian: Boolean
}

case class RegularCustomer(isVegetarian: Boolean) extends Customer
case class NewCustomer(isVegetarian: Boolean)  extends Customer


class Order[T <: Customer](val person: T) {
  def showMenu: String = if (person.isVegetarian) "vegetarian menu" else "ordinary menu"
}

abstract class MusicInstrument {
  val productionYear: Int
}

object MusicInstrument {
  def apply(productionYear: Int) = new MusicInstrument {
    override val productionYear: Int = productionYear
  }
}

case class Guitar(productionYear: Int) extends MusicInstrument
case class Piano(productionYear: Int) extends MusicInstrument

object Test22 extends App {
  val guitar = Guitar.apply(2024)
  println(guitar)
}

trait Fruit {

  val code: String

  override def toString: String = s"$code"
}

class Apple(val code: String) extends Fruit
class GalaApple(code: String) extends Apple(code)
class GreenApple(code: String) extends Apple(code)


class Store[-T] {
  def sell[S <: Apple](fruit: S): Unit = println(s"sell $fruit")
}

object Test33 extends App {
  val store: Store[GalaApple] = new Store[Apple]

  store.sell(new Apple("Apple-4135"))
  store.sell(new GalaApple("GalaApple-4133"))
  store.sell(new GreenApple("GreenApple-3344"))
}