package gladiator

class Character(val name: String,
                val alignment: Character.Alignment,
                val armorClass: Int,
                val hitPoints: Int,
                val abilities: Map[Ability.Category, Ability]) {

  def attack(defender: Character, roll: Int): (Character, Character, Attack) = {
    val attack = Attack(this, defender, roll)
    (this, defender.applyDamage(attack), attack)
  }

  def isAlive: Boolean = {
    hitPoints > 0
  }

  def ability(category: Ability.Category): Ability = abilities.getOrElse(category, Ability())

  def maxHitPoints: Int = Character.maxHitPoints(abilities)


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

  def apply(name: String,
            alignment: Character.Alignment = Character.Alignment.Neutral,
            armorClass: Int = 10,
            hitPoints: Option[Int] = None,
            abilities: Map[Ability.Category, Ability] = Map()): Character = {
    new Character(name, alignment, armorClass, hitPoints.getOrElse(maxHitPoints(abilities)), abilities)
  }

  private def maxHitPoints(abilities: Map[Ability.Category, Ability]): Int = {
    (5 + abilities.getOrElse(Ability.Constitution, Ability()).modifier).max(1)
  }

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}