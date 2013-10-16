package gladiator.classes

import gladiator.Character
import gladiator.modifiers.{HitPointModifier, AttackRollModifier}

object Fighter extends Character.Class with AttackRollModifier with HitPointModifier {

  def attackRollModifier(character: Character, defender: Character): Int = (character.level+1)/2

  def hitPointModifier(character: Character): Int = 5 * character.level

}
