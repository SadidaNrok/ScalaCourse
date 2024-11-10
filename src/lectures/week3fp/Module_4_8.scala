package lectures.week3fp

import scala.collection.Iterable
import scala.util.{Failure, Try, Success}

object Module_4_8 extends App {
  case class User(id: Int, email: String)

  val user = User(1, "mail@example.com")

  val User(userId, userEmail) = user

  println(s"id: $userId") // 1
  println(s"email: $userEmail") // email: mail@example.com


  val input: String = "John Doe"
  case class Person(name: String)

  object Person {
    def unapply(name: String): Some[String] = {
      Option(name) match {
        case Some(v) =>
          Some(v.split(" ")
            .map(_.capitalize.head.toString.concat("."))
            .mkString(" ")
          )
        case None => Some("Failed to get initials")
      }
    }
  }

  val Person(initials) = "John Doe"
  println(s"$initials")

  def compare(v1: String, v2: String) = {
    val res = v1.split('.').map(_.toInt)
      .zipAll(v2.split('.').map(_.toInt), 0, 0)
      .foldLeft((0, 0))((r, n) => (r._1 * 10 + n._1, r._2 * 10 + n._2))

    res._1.compare(res._2)
  }

  println(compare("4.0.1", "4.0.0.1"))

  def duplicate[T](someList: Iterable[T], numDups: Int) = {
    someList.flatMap(Iterable.fill(numDups)(_)).toList
  }

  println(duplicate(Seq(1,2,3), 3))

  def countChars(s: String): Map[Char, Int] = {
    s.foldLeft(Map[Char, Int]().withDefaultValue(0))((r, n) => r + (n.toLower -> (r(n.toLower) + 1)))
  }

  println(countChars("sSt"))
}

object Module_4_8_2 extends App {
  var network: Map[String, Set[String]] = Map().withDefaultValue(Set())

  def add(
           network: Map[String, Set[String]],
           location: String)
  : Map[String, Set[String]] = {
    network + (location -> Set())
  }
  def connect(
               network: Map[String, Set[String]],
               locationA: String,
               locationB: String)
  : Map[String, Set[String]] = {
    network + (locationA -> (network(locationA) + locationB)) +
      (locationB -> (network(locationB) + locationA))
  }
  def disconnect(
                  network: Map[String, Set[String]],
                  locationA: String,
                  locationB: String)
  : Map[String, Set[String]] = {
    network + (locationA -> (network(locationA) - locationB)) +
      (locationB -> (network(locationB) - locationA))
  }
  def remove(
              network: Map[String, Set[String]],
              location: String)
  : Map[String, Set[String]] = {
    (network - location).map(n => (n._1 -> (n._2 - location)))
  }
  def flightCount(
                   network: Map[String, Set[String]],
                   location: String)
  : Int = {
    network(location).size
  }
  def mostFlights(
                   network: Map[String, Set[String]])
  : String = {
    network.toList.maxBy(_._2.size)._1
  }
  def numLocationsWithNoFlights(
                                 network: Map[String, Set[String]])
  : Int = {
    network.count(n => !network.flatMap(_._2).exists(_.compare(n._1) == 0))
  }
  def isConnected(
                   network: Map[String, Set[String]],
                   locationA: String,
                   locationB: String)
  : Boolean = {
    network(locationA).exists(n => n.compare(locationB) == 0 || isConnected(remove(network, locationA), n, locationB))
  }

  network =
    add(
      add(
        add(
          add(
            add(
              network, "Moscow"
            ), "Sevastopol"
          ), "Samara"
        ), "Kazan"
      ), "Saint-Petersburg"
    )

  network =
    connect(
      connect(
        connect(
          connect(
            network, "Saint-Petersburg", "Moscow"
          ), "Saint-Petersburg", "Sevastopol"
        ), "Kazan", "Samara"
      ), "Saint-Petersburg", "Samara"
    )

  println(network)
  println(flightCount(network, "Samara"))
  println(numLocationsWithNoFlights(network))
  println(mostFlights(network))
  println(isConnected(network, "Sevastopol", "Kazan"))
  network = connect(network, "Belgorod", "Tver")
  println(isConnected(network, "Moscow", "Tver"))
}

object Module_4_8_3 extends App {
  object LoginService {
    def login(): Platform = {
      new Platform
      throw new Exception("Failed to login")
    }
  }
  class Platform {
    def enroll(): String = "Hello"
  }
  object NotificationService {
    def notify(course: String): Unit = println("1")
  }

  Try(LoginService.login()) match {
    case Success(platform) => Try(platform.enroll()) match {
      case Success(str) => NotificationService.notify(str)
      case Failure(ex) => println(ex.getMessage)
    }
    case Failure(ex) => println(ex)
  }

}