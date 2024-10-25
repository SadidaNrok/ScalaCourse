package lectures.week2oop

abstract class Event {
  def trigger(eventName: String): Unit
}
