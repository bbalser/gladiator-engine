package gladiator.modifiers

import gladiator.Character

trait HitPointModifier {

  def hitPointModifier(character: Character): Int

}
