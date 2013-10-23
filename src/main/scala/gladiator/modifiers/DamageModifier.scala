package gladiator.modifiers

import gladiator.Character

trait DamageModifier {

  def damageModifier(attacker: Character, defender: Character): Int

}
