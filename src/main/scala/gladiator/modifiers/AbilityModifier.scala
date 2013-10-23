package gladiator.modifiers

import gladiator.Ability

trait AbilityModifier {

  def abilityModifier(category: Ability.Category): Int

}
