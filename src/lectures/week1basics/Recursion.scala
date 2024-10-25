package lectures.week1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1)
  }

  println(factorial(3)) // выводит 6

  def factorialWithTailRecursion(n: Int): Long = {
    @tailrec
    def loop(x: Int, accumulator: Long): Long = {
      if (x <= 1) accumulator
      else loop(x-1, x*accumulator)
    }

    loop(n, 1)
  }

  println(factorialWithTailRecursion(5)) // выводит 6

  def repeatWord(word: String, n: Int): String = {
    @tailrec
    def loop(i: Int, acc: String = word): String = {
      if (i <= 1) acc
      else loop(i - 1, s"$word $acc")
    }

    loop(n)
  }

  println(repeatWord("hello", 3))

  def powerOfTwo(n: Int): BigInt = {
    @tailrec
    def loop(i: Int, acc: BigInt): BigInt = {
      if (i <= 1) acc
      else loop(i - 1, 2 * acc)
    }

    loop(n, 2)
  }

  println(powerOfTwo(33))

  def increment(x: Int, y: Int, n: Int): Int = {
    @tailrec
    def loop(i: Int, acc: Int): Int = {
      if (i <= 0) acc
      else loop(i - 1, acc + y)
    }

    loop(n, x)
  }

  val res = increment(10, 1, 5)

  def getRes(v: String): String = {
    @tailrec
    def loop(i: Int, acc: String): String = {
      if (i <= 1) s"$acc is the result"
      else loop(i - 1, s"$v $acc")
    }

    loop(v.length, v)
  }

  val ff = "12".toInt
  println(getRes(res.toString))

  val input = "I like Scala Yes It     is"

  def reverse(v: String): String = {
    @tailrec
    val arr = v.split(' ')
    println(arr.length)

    def loop(i: Int, acc: String) : String = {
      val v = arr(i)
      if (v.isEmpty)
        if (i > 0) loop(i - 1, acc)
        else acc
      else
        if (i == 0) s"$acc $v"
        else loop(i - 1, s"$acc $v")
    }

    loop(arr.length - 2, arr(arr.length - 1))
  }

  println(reverse(input))
}
