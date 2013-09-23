package gladiator

class Character(val name: String, val alignment: Character.Alignment = Character.Alignment.Neutral, val armorClass:Int = 10) {

}

object Character {

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}