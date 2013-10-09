package gladiator

class Attack(val attacker: Character, val defender: Character, val roll: Int) {

  def isHit: Boolean = {
    roll >= defender.armorClass
  }

  def damage: Int = {
    if (roll == Attack.NATURAL_TWENTY) {
      Attack.DEFAULT_DAMAGE * 2
    } else {
      Attack.DEFAULT_DAMAGE
    }
  }

}

object Attack {

  val DEFAULT_DAMAGE = 1
  val NATURAL_TWENTY = 20

  def apply(attacker: Character, defender: Character, roll: Int) = new Attack(attacker, defender, roll)
}
