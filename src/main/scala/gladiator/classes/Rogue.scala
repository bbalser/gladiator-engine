package gladiator.classes

import gladiator.{Ability, Character}
import gladiator.modifiers.{AttackRollModifier, ArmorClassModifier}

object Rogue extends Character.Class with ArmorClassModifier with AttackRollModifier {

  override def criticalHitModifier(defender: Character): Int = 3

  def armorClassModifier(defender: Character): Int = - defender.ability(Ability.Dexterity).modifier.max(0)

  def attackRollModifier(attacker: Character, defender: Character): Int = {
    attacker.ability(Ability.Dexterity).modifier - attacker.ability(Ability.Strength).modifier
  }

}
