package gladiator.classes

import gladiator.{Ability, Character}
import gladiator.modifiers.{AttackRollModifier, ArmorClassModifier, HitPointModifier}

object Monk extends Character.Class with HitPointModifier with ArmorClassModifier with AttackRollModifier {

  def hitPointModifier(character: Character): Int = character.level

  def armorClassModifier(defender: Character): Int = defender.ability(Ability.Wisdom).modifier.max(0)

  def attackRollModifier(attacker: Character, defender: Character): Int = attacker.level/3

  override def defaultDamage: Int = 3

}
