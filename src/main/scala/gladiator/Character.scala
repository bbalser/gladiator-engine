package gladiator

class Character(val name: String,
                val alignment: Character.Alignment = Character.Alignment.Neutral,
                val armorClass:Int = 10,
                val hitPoints: Int = 5,
                val abilities: Map[Ability.Category, Ability] = Map()) {

  def attack(defender: Character, roll: Int): (Character, Character, Attack) = {
    val attack = Attack(this, defender, roll)
    (this, defender.applyDamage(attack), attack)
  }

  def isAlive: Boolean = {
    hitPoints > 0
  }

  def ability(category: Ability.Category): Ability = abilities.getOrElse(category, Ability())

  private def applyDamage(attack: Attack): Character = {
    if (attack.isHit) {
      copy(hitPoints = hitPoints - attack.damage)
    } else {
      this
    }
  }

  private def copy(name: String = this.name,
                   alignment: Character.Alignment = this.alignment,
                   armorClass: Int = this.armorClass,
                   hitPoints: Int = this.hitPoints,
                   abilities: Map[Ability.Category, Ability] = this.abilities): Character = {
    new Character(name, alignment, armorClass, hitPoints, abilities)
  }

}

object Character {

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}