package gladiator

class Character(val name: String,
                val alignment: Character.Alignment = Character.Alignment.Neutral,
                val armorClass:Int = 10,
                val hitPoints: Int = 5) {

  def attack(defender: Character, roll: Int): (Character, Character, Attack) = {
    (this, defender, Attack(this, defender, roll))
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