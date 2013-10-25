package gladiator.races

import gladiator.{Ability, Character}
import gladiator.modifiers.{AttackRollModifier, HitPointModifier, AbilityModifier}
import gladiator.Ability.Category

object Dwarf extends Character.Race with AbilityModifier with HitPointModifier with AttackRollModifier {

  def abilityModifier(category: Category): Int = category match {
    case Ability.Constitution => 2
    case Ability.Charisma => -2
    case _ => 0
  }

  def hitPointModifier(character: Character): Int = (character.ability(Ability.Constitution).modifier * character.level).max(0)

  def attackRollModifier(attacker: Character, defender: Character): Int = defender.race match {
    case Orc => 2
    case _ => 0
  }

}
