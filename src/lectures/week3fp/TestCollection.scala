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
}
