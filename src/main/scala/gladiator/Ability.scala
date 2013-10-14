package gladiator

case class Ability(val score: Int = Ability.DEFAULT_SCORE) {
  require(score > 0 && score < 21, "Ability can only be 1 to 20")

  def modifier: Int = (score / 2) - 5
}

object Ability {
  val DEFAULT_SCORE = 10

  sealed abstract class Category

  case object Strength extends Category
  case object Dexterity extends Category
  case object Wisdom extends Category
  case object Constitution extends Category
  case object Intelligence extends Category
  case object Charisma extends Category

}

