package lectures.week4trees

import scala.annotation.tailrec
import scala.util.Try

object Module_5_1 extends App {

  abstract class BinaryTree[+T] {
    def value: T // значение узла
    def leftChild: BinaryTree[T] // левый потомок
    def rightChild: BinaryTree[T] // правый потомок
    def isEmpty: Boolean

    def isLeaf: Boolean

    def collectLeaves: List[BinaryTree[T]]

    def countLeaves: Int

    def nodesAtLevel(level: Int): List[BinaryTree[T]]

    def collectNodes(): List[T]
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {
    override def isEmpty: Boolean = false

    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

    override def collectLeaves: List[BinaryTree[T]] = {
      isLeaf match {
        case true => List(this)
        case _ => leftChild.collectLeaves ++ rightChild.collectLeaves
      }
    }

    override def countLeaves: Int = {
      isLeaf match {
        case true => 1
        case _ => leftChild.countLeaves + rightChild.countLeaves
      }
    }

    override def nodesAtLevel(level: Int): List[BinaryTree[T]] = {
      @tailrec
      def loop(level: Int, acc: List[BinaryTree[T]]): List[BinaryTree[T]] = {
        if (level == 0) {
          acc.filterNot(_.isEmpty)
        }
        else if (level < 0) {
          List()
        } else {
          loop(level - 1, acc.flatMap(n => List(n.leftChild, n.rightChild)).filterNot(_.isEmpty))
        }
      }

      loop(level, List(this))
    }

    override def collectNodes(): List[T] = {
      List(this.value) ++ (leftChild.collectNodes ++ rightChild.collectNodes)
    }
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException

    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def isLeaf: Boolean = false

    override def collectLeaves: List[BinaryTree[Nothing]] = List()

    override def countLeaves: Int = 0

    override def nodesAtLevel(level: Int): List[BinaryTree[Nothing]] = List()

    override def collectNodes(): List[Nothing] = List()
  }

  val tree = Node(1,
    Node(2,
      Node(4,
        TreeEnd,
        TreeEnd),
      Node(5,
        TreeEnd,
        Node(8,
          TreeEnd,
          TreeEnd))),
    Node(3,
      Node(6,
        TreeEnd,
        TreeEnd),
      Node(7,
        TreeEnd,
        TreeEnd)))

  println(tree.collectLeaves.map(_.value).sorted)
  println(tree.countLeaves)

  val tree2 = Node(3, TreeEnd, TreeEnd)
  println(tree2.countLeaves)
  println(tree.nodesAtLevel(3))
  println(tree.collectNodes())

  def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
    @tailrec
    def loop(acc: List[(BinaryTree[Int], Int)]): Boolean = {
      if (acc.exists(n => !n._1.isLeaf && !n._1.isEmpty)) {
        loop(acc.filter(!_._1.isEmpty).flatMap(n => n._1.isLeaf match {
          case false => List((n._1.leftChild, n._2 + Try(n._1.leftChild.value).getOrElse(0)),
            (n._1.rightChild, n._2 + Try(n._1.rightChild.value).getOrElse(0)))
          case true => List(n)
        }))
      } else {
        acc.filter(!_._1.isEmpty).exists(_._2 == target)
      }
    }

    loop(List((tree, Try(tree.value).getOrElse(0))))
  }

  println(hasPath(tree, 7))

  def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = {
    @tailrec
    def loop(acc: List[List[BinaryTree[String]]]): List[List[String]] = {
      if (acc.exists(n => !n.last.isLeaf && !n.last.isEmpty)) {
        loop(acc.flatMap(n => {
          if (!n.last.isLeaf && !n.last.isEmpty) {
            List(n ++ List(n.last.leftChild)) ++ List(n ++ List(n.last.rightChild))
          } else {
            List(n)
          }
        }))
      } else {
        acc.filter(n => n.last.isLeaf)
          .filter(n => n.foldLeft(0)((r, a) => r + a.value.toInt).toString.compare(target) == 0)
          .map(n => n.map(m => m.value))
      }
    }

    loop(List(List(tree)))
  }

  val tree3 = Node("1",
    Node("2",
      Node("4",
        TreeEnd,
        TreeEnd),
      Node("5",
        TreeEnd,
        Node("8",
          TreeEnd,
          TreeEnd))),
    Node("3",
      Node("6",
        TreeEnd,
        TreeEnd),
      Node("7",
        TreeEnd,
        TreeEnd))
  )

  println(findAllPaths(tree3, "7"))
}

object Module_5_1_2 extends App {
  class Modifier[A](var modifier: A) {
    def get: A = modifier

    def set(value: A): Unit = modifier = value
  }

  val aModifier = new Modifier(4)
  aModifier.modifier = 6
  println(aModifier.modifier)
}