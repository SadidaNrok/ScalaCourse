package lectures.week2oop

class Button(label: String = "test") {
  def click(): String = s"button -$label- has been clicked"
}

class TestButton extends Button {
  override def click(): String = s"test ${super.click()}"
}