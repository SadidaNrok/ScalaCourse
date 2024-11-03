package lectures.week3fp



object TestCollection extends App {
  val collection1: List[Int] = List(1, 2, 3)
  val collection2: List[String] = List("a", "b", "c")
  val collection3: List[Int] = 1 :: 2 :: 3 :: Nil // List(1, 2, 3)


  val collection4 = List.fill(3)("a") // List(a, a, a)
  val collection5 = Array.fill(3)("b")

  val collection6 = List.tabulate(3)(i => s"element $i") // List(element 0, element 1, element 2)

  val collection7 = Map(
    "A" -> 1,
    "B" -> 2,
    "C" -> 3
  )

  val progLanguages2 = List("java", "scala", "python")
  val result2: List[(String, Int)] = progLanguages2.map(n => (n, n.length))
  println(result2)

  val progLanguages = List("java", "scala", "python")
  val lngAbbrev = List("JA", "SCA", "PY")

  println("java".toUpperCase().contains("JA"))

  val result = progLanguages
    .flatMap(lng => lngAbbrev
      .map(abrv => (abrv, lng)))
      .filter(n => n._2.startsWith(n._1.toLowerCase()))

  println(result)

  val r = Map[Char, Int]().withDefaultValue(0)
  r

  def countNumbers(s: String) = {
    s.filter(letter => letter != '-').foldLeft(Map[Char, Int]().withDefaultValue(0)){
      (acc, letter)  => acc + (letter -> (1 + acc(letter)))
    }
  }

  println(countNumbers("9-876-543-21-09"))

  def countSum(s: String) = {
    s.foldLeft(0)((sum, ch) => sum + ch.toString.toInt)
  }

  println(countSum("12345000"))

  def getList(s: String) = {
    s.foldLeft(List[Char]()){
      (acc, ch) => acc :+ ch
    }
  }

  println(getList("ghfds"))

  trait WeekDay
  case object Mon extends WeekDay
  case object Tue extends WeekDay
  case object Wed extends WeekDay
  case object Thu extends WeekDay
  case object Fri extends WeekDay
  case object Sat extends WeekDay
  case object Sun extends WeekDay
  case object Unk extends WeekDay

  def dayOfWeek(dayNumber: Int): WeekDay = {
    dayNumber match {
      case 1 => Mon
      case 2 => Tue
      case 3 => Wed
      case 4 => Thu
      case 5 => Fri
      case 6 => Sat
      case 7 => Sun
      case _ => Unk
    }
  }

  println(dayOfWeek(5))

  def getType(collection: Any): String = {
    val t = collection match {
      case v: Iterable[_] => v.head
      case v2: Array[_] => v2(0)
      case _ => null
    }

    val t2 = t match {
      case (v, _) => v
      case _ => t
    }

    t2 match {
      case _: String => "Collection Of Strings"
      case _: Int => "Collection Of Ints"
      case _: Double => "Collection Of Doubles"
      case _ => "Unknown type"
    }
  }

  println(getType(Map((2.1, 4.2), (4.4, 3.3))))
  println(getType(Seq(1.5,2.2)))
  println(getType(Array(3,212,3)))
}
