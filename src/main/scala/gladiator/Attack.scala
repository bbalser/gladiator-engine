package gladiator

import gladiator.modifiers.{Modifiers, AttackRollModifier}

class Attack(val attacker: Character, val defender: Character, val roll: Int) {

  def isHit: Boolean = {
    if (roll == Attack.NATURAL_TWENTY) true else attackRoll >= armorClass
  }

  def damage: Int = {
    def criticalModifier = if (roll == Attack.NATURAL_TWENTY) 2 else 1
    (criticalModifier * baseDamage).max(1)
  }

  private def baseDamage: Int = {
    Attack.DEFAULT_DAMAGE + attacker.ability(Ability.Strength).modifier
  }

  def attackRoll: Int = {
    roll + attacker.ability(Ability.Strength).modifier + (attacker.level/2) + attackRollModifiers
  }

  def armorClass: Int = {
    defender.armorClass + defender.ability(Ability.Dexterity).modifier
  }


  private def attackRollModifiers: Int = {
    Modifiers.attackRollModifiers(List(attacker.characterClass), attacker, defender)
  }


}

object Attack {

  val DEFAULT_DAMAGE = 1
  val NATURAL_TWENTY = 20

  def apply(attacker: Character, defender: Character, roll: Int) = new Attack(attacker, defender, roll)
}
