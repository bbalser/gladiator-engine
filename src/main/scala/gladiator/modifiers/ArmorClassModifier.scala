package gladiator.modifiers

import gladiator.Character

trait ArmorClassModifier {

  def armorClassModifier(defender: Character): Int

}
