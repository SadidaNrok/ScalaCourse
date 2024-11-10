package lectures.week3fp

object Sandbox extends App {
  trait CanSpeak {
    def speak: String
  }

  object Person {
    implicit val canSpeak: CanSpeak = new CanSpeak {
      def speak: String = "I can speak!"
    }
  }

  def speakSomething(implicit speaker: CanSpeak): String = speaker.speak
  // Импортируем имплиситное значение, чтобы оно стало доступным
  import Person._
  println(speakSomething)  // Вывод: I can speak!
}
