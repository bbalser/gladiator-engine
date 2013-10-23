package gladiator.classes

import gladiator.Character
import gladiator.modifiers.{DamageModifier, AttackRollModifier, HitPointModifier}

object Paladin extends Character.Class with HitPointModifier with AttackRollModifier with DamageModifier {

  def hitPointModifier(character: Character): Int = 3 * character.level

  def attackRollModifier(attacker: Character, defender: Character): Int = {
    ((attacker.level + 1) / 2) + ifEvil(defender, 2).getOrElse(0)
  }

  def damageModifier(attacker: Character, defender: Character): Int = ifEvil(defender, 2).getOrElse(0)

  override def criticalHitModifier(defender: Character): Int = ifEvil(defender, 3).getOrElse(2)

  private def ifEvil(defender: Character, evilValue: Int): Option[Int] = defender.alignment match {
    case Character.Alignment.Evil => Some(evilValue)
    case _ => None
  }


}
