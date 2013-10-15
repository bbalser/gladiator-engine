package gladiator

class Attack(val attacker: Character, val defender: Character, val roll: Int) {

  def isHit: Boolean = {
    if (roll == Attack.NATURAL_TWENTY) true else determineAttackRoll >= determineArmorClass
  }

  def damage: Int = {
    def criticalModifier = if (roll == Attack.NATURAL_TWENTY) 2 else 1
    (criticalModifier * baseDamage).max(1)
  }

  private def baseDamage: Int = {
    Attack.DEFAULT_DAMAGE + attacker.ability(Ability.Strength).modifier
  }

  private def determineAttackRoll: Int = {
    roll + attacker.ability(Ability.Strength).modifier
  }

  private def determineArmorClass: Int = {
    defender.armorClass + defender.ability(Ability.Dexterity).modifier
  }

}

object Attack {

  val DEFAULT_DAMAGE = 1
  val NATURAL_TWENTY = 20

  def apply(attacker: Character, defender: Character, roll: Int) = new Attack(attacker, defender, roll)
}
