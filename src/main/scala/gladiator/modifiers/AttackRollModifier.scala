package gladiator.modifiers

import gladiator.Character

trait AttackRollModifier {

  def attackRollModifier(attacker: Character, defender: Character): Int

}
