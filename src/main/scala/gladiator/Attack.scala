package gladiator

import gladiator.modifiers.{Modifiers, AttackRollModifier}

class Attack(val attacker: Character, val defender: Character, val roll: Int) {

  def isHit: Boolean = {
    if (roll == Attack.NATURAL_TWENTY) true else attackRoll >= armorClass
  }

  def damage: Int = {
    def criticalModifier = if (roll == Attack.NATURAL_TWENTY) attacker.characterClass.criticalHitModifier(defender) else 1
    (criticalModifier * baseDamage).max(1)
  }

  private def baseDamage: Int = {
    attacker.characterClass.defaultDamage + attacker.ability(Ability.Strength).modifier +
      Modifiers.damageModifiers(List(attacker.characterClass), attacker, defender)
  }

  def attackRoll: Int = {
    roll + attacker.ability(Ability.Strength).modifier + (attacker.level/2) + attackRollModifiers
  }

  def armorClass: Int = {
    defender.armorClass + Modifiers.armorClassModifiers(List(attacker.characterClass), defender)
  }


  private def attackRollModifiers: Int = {
    Modifiers.attackRollModifiers(List(attacker.characterClass, attacker.race), attacker, defender)
  }

}

object Attack {
  val NATURAL_TWENTY = 20

  def apply(attacker: Character, defender: Character, roll: Int) = new Attack(attacker, defender, roll)
}
