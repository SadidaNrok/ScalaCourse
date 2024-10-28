package lectures.week2oop

object TestAccountType extends App {
  val baseAccount = BaseAccount(
    accountId = 1,
    accountType = FreeAccount,
    settings = Settings.AccountSettings(
      email = "test@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"))

  baseAccount.info()
  println(baseAccount.accountId)

  val baseAccount2 = BaseAccount(
    accountId = 1,
    accountType = PaidAccount,
    settings = Settings.AccountSettings(
      email = "test@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"))

  println(baseAccount == baseAccount2)

  val set1 = Settings.AccountSettings(
    email = "test@mail.com",
    password = "abr#45&",
    picture = "link/to/some/pic")

  val set2 = Settings.AccountSettings(
    email = "test@mail.com",
    password = "abr#45&",
    picture = "link/to/some/pic")

  println(set1 == set2)
  println(baseAccount)

  val privilegedAccount = PrivilegedAccount(
    accountId = 1,
    accountType = PaidAccount,
    settings = Settings.AccountSettings(
      email = "test@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"),
    Unsubscribed
  )

  println(privilegedAccount)
  privilegedAccount.info()
  println(FreeAccount)
  println(PaidAccount)

  baseAccount.subscribe(1)
}


sealed class AccountType
case object FreeAccount extends AccountType
case object PaidAccount extends AccountType


sealed class SubscriptionStatus
case object Subscribed extends SubscriptionStatus
case object Unsubscribed extends SubscriptionStatus
case object SubscriptionMissingData extends SubscriptionStatus


object Settings {
  case class AccountSettings(email: String, password: String, picture: String)
  case class SubscriptionSettings(paymentMethod: String, notifications: String, expiration: String)
}

trait Unsubscriber {
  def unsubscribe(accountId: Int): Unit = println(s"$accountId unsubscribed")
}

abstract class Account(accountId: Int, accountType: AccountType, settings: Settings.AccountSettings) {

  def info(): Unit = println(s"Account Type: $accountType")

  def performAction(): Unit
}

case class BaseAccount (accountId: Int, accountType: AccountType, settings: Settings.AccountSettings)
  extends Account(accountId = accountId, accountType = FreeAccount, settings = settings) {
  def subscribe(accountId: Int): Unit = println(s"$accountId ${settings.email} subscribed")
  def performAction(): Unit = {
    subscribe(this.accountId)
  }
}

object BaseAccount {
  def apply(accountId: Int, accountType: AccountType, settings: Settings.AccountSettings): BaseAccount = {
    new BaseAccount(accountId = accountId, accountType = accountType, settings = settings)
  }
}

case class PrivilegedAccount(accountId: Int, accountType: AccountType, settings: Settings.AccountSettings, subscriptionStatus: SubscriptionStatus)
  extends Account(accountId = accountId, accountType = accountType, settings = settings) with Unsubscriber {

  override def performAction(): Unit = {
    unsubscribe(this.accountId)
  }

  override def info(): Unit = {
    super.info()
    println(s"Subscription Status: $subscriptionStatus")
  }
}

object PrivilegedAccount {
  def apply(accountId: Int, accountType: AccountType, settings: Settings.AccountSettings, subscriptionStatus: SubscriptionStatus): PrivilegedAccount = {
    new PrivilegedAccount(accountId = accountId, accountType = accountType, settings = settings, subscriptionStatus = subscriptionStatus)
  }
}






trait Degree {
  def info(): Unit
}

trait Bachelor extends Degree {
  def info(): Unit = println("Bachelor Degree")
}

trait Master extends Degree {
  override def info(): Unit = println("Master Degree")
}

trait Doctorate extends Degree {
  override def info(): Unit = println("Bachelor Degree")
}

trait BachelorOfArts extends Bachelor {
  override def info(): Unit = {
    super.info()
    println("Bachelor of Arts")
  }
}

trait BachelorOfScience extends Bachelor {
  override def info(): Unit = {
    super.info()
    println("Bachelor of Science")
  }
}

trait MasterOfLaw extends Master {
  override def info(): Unit = {
    super.info()
    println("Master of Law")
  }
}

class CombinedDegree extends BachelorOfArts with MasterOfLaw {
  override def info(): Unit = println("Combined Degree")
}

object TestBachelor extends App {
  val combinedDegree1 = new BachelorOfScience with MasterOfLaw
  val combinedDegree2 = new CombinedDegree

  combinedDegree1.info()
  combinedDegree2.info()


}

