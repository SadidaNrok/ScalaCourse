package lectures.week2oop

class RoundedButton(label: String) extends Button(label) {
  override def click(): String = s"rounded ${super.click()}"
}
