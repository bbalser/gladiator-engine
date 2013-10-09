package gladiator

class Attack(val attacker: Character, val defender: Character, val roll: Int) {

  def isHit: Boolean = {
    roll >= defender.armorClass
  }

  def damage: Int = {
    1
  }

}

object Attack {
  def apply(attacker: Character, defender: Character, roll: Int) = new Attack(attacker, defender, roll)
}
