package gladiator.races

import gladiator.{Ability, Character}
import gladiator.modifiers.{ArmorClassModifier, AbilityModifier}
import gladiator.Ability.Category

object Orc extends Character.Race with AbilityModifier with ArmorClassModifier {

  def abilityModifier(category: Category): Int = category match {
    case Ability.Strength => 4
    case Ability.Intelligence => -2
    case Ability.Wisdom => -2
    case Ability.Charisma => -2
    case _ => 0
  }

  def armorClassModifier(defender: Character): Int = 2
}
