package lectures.week2oop

class Listener(eventName: String,event: Event) {
  def register(newEvent: Event): Listener = {
    new Listener(eventName, newEvent)
  }

  def sendNotification() {
    event.trigger(eventName)
  }
}

object TestListener extends App {
  val notification: Listener = new Listener("mousedown", null)
  notification
    .register(
      new Event {
        override def trigger(eventName: String): Unit = println(s"trigger -${eventName}- event")
      }
    )
    .sendNotification()

  val t = Int.MaxValue
  println(t)
  println(t+t)
  println(t+1 > Int.MaxValue)
  println(Int.MinValue)
}