package gladiator.modifiers

import gladiator.Character

object Modifiers {

  def attackRollModifiers(objects : List[Any], attacker: Character, defender: Character) = {
    sumModifiers(objects, { x: AttackRollModifier => x.attackRollModifier(attacker, defender) })
  }

  def hitPointModifiers(objects: List[Any], character: Character) = {
    sumModifiers(objects, { x: HitPointModifier => x.hitPointModifier(character) } )
  }

  def armorClassModifiers(objects: List[Any], defender: Character) = {
    sumModifiers(objects, { x: ArmorClassModifier => x.armorClassModifier(defender) } )
  }

  def sumModifiers[T : Manifest](objects: List[Any], f: (T => Int)): Int = {
    objects.collect { case x: T => f(x) }.sum
  }

}
