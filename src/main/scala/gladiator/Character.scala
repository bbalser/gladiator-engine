package gladiator

class Character(val name: String,
                val alignment: Character.Alignment = Character.Alignment.Neutral,
                val armorClass:Int = 10,
                val hitPoints: Int = 5) {

  def attack(defender: Character): Attack = {
    Attack(this, defender)
  }


}

object Character {

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}