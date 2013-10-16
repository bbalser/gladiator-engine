package gladiator

import scala.math.sqrt

class Character(val name: String,
                val alignment: Character.Alignment,
                val armorClass: Int,
                val hitPoints: Int,
                val abilities: Map[Ability.Category, Ability],
                val experiencePoints: Int) {

  def attack(defender: Character, roll: Int): (Character, Character, Attack) = {
    val attack = Attack(this, defender, roll)
    (this, defender.applyDamage(attack), attack)
  }

  def isAlive: Boolean = {
    hitPoints > 0
  }

  def ability(category: Ability.Category): Ability = Character.ability(abilities, category)

  def level: Int = Character.determineLevel(experiencePoints)

  def maxHitPoints: Int = Character.maxHitPoints(abilities, experiencePoints)

  def addExperiencePoints(points: Int): Character = {
    copy(experiencePoints = experiencePoints + points)
  }

  def setExperiencePoints(points: Int): Character = {
    copy(experiencePoints = points)
  }

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
                   abilities: Map[Ability.Category, Ability] = this.abilities,
                   experiencePoints: Int = this.experiencePoints): Character = {
    new Character(name, alignment, armorClass, hitPoints, abilities, experiencePoints)
  }

}

object Character {

  def apply(name: String,
            alignment: Character.Alignment = Character.Alignment.Neutral,
            armorClass: Int = 10,
            hitPoints: Option[Int] = None,
            abilities: Map[Ability.Category, Ability] = Map(),
            experiencePoints: Int = 0): Character = {
    new Character(name, alignment, armorClass, hitPoints.getOrElse(maxHitPoints(abilities, experiencePoints)), abilities, experiencePoints)
  }

  private def maxHitPoints(abilities: Map[Ability.Category, Ability], experiencePoints: Int): Int = {
    ((5 + ability(abilities, Ability.Constitution).modifier) * determineLevel(experiencePoints)).max(1)
  }

  private def determineLevel(experiencePoints: Int) = ((1 + sqrt(experiencePoints/125.0 + 1.0)) / 2.0).floor.toInt.min(20)

  private def ability(abilities: Map[Ability.Category, Ability], category: Ability.Category): Ability = abilities.getOrElse(category, Ability())

  val Levels = Map( (1 -> 0), (2, 1000), (3 -> 3000), (4 -> 6000), (5 -> 10000), (6 -> 15000), (7 -> 21000), (8 -> 28000), (9 -> 36000), (10 -> 45000),
    (11 -> 55000), (12 -> 66000), (13 -> 78000), (14 -> 91000), (15 -> 105000), (16 -> 120000), (17 -> 136000), (18 -> 153000), (19 -> 171000), (20 -> 190000))

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}