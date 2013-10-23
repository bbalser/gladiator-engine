package gladiator

import scala.math.sqrt
import gladiator.modifiers.{Modifiers, HitPointModifier}
import gladiator.races.Human

class Character(val name: String,
                val alignment: Character.Alignment,
                val hitPoints: Option[Int],
                val abilities: Map[Ability.Category, Ability],
                val experiencePoints: Int,
                val characterClass: Character.Class,
                val race: Character.Race) {

  def currentHitPoints: Int = hitPoints.getOrElse(maxHitPoints)

  def armorClass: Int = 10 + ability(Ability.Dexterity).modifier +
    Modifiers.armorClassModifiers(List(characterClass, race), this)

  def attack(defender: Character, roll: Int): (Character, Character, Attack) = {
    val attack = Attack(this, defender, roll)
    (this, defender.applyDamage(attack), attack)
  }

  def isAlive: Boolean = {
    currentHitPoints > 0
  }

  def ability(category: Ability.Category): Ability = Ability(abilities.getOrElse(category, Ability()).score +
    Modifiers.abilityModifiers(List(race), category))

  def level: Int = ((1 + sqrt(experiencePoints/125.0 + 1.0)) / 2.0).floor.toInt.min(20)

  def maxHitPoints: Int = {
    ( ((5 + ability(Ability.Constitution).modifier) * level) + hitPointModifiers ).max(1)
  }

  def addExperiencePoints(points: Int): Character = {
    copy(experiencePoints = experiencePoints + points)
  }

  def setExperiencePoints(points: Int): Character = {
    copy(experiencePoints = points)
  }

  private def hitPointModifiers: Int = {
    Modifiers.hitPointModifiers(List(characterClass), this)
  }

  private def applyDamage(attack: Attack): Character = {
    if (attack.isHit) {
      copy(hitPoints = currentHitPoints - attack.damage)
    } else {
      this
    }
  }

  private def copy(name: String = this.name,
                   alignment: Character.Alignment = this.alignment,
                   hitPoints: Int = this.currentHitPoints,
                   abilities: Map[Ability.Category, Ability] = this.abilities,
                   experiencePoints: Int = this.experiencePoints): Character = {
    new Character(name, alignment, Some(hitPoints), abilities, experiencePoints, this.characterClass, this.race)
  }

}

object Character {

  def apply(name: String,
            alignment: Character.Alignment = Character.Alignment.Neutral,
            hitPoints: Option[Int] = None,
            abilities: Map[Ability.Category, Ability] = Map(),
            experiencePoints: Int = 0,
            characterClass: Character.Class = DefaultClass,
            race: Character.Race = Human): Character = {
    new Character(name, alignment, hitPoints, abilities, experiencePoints, characterClass, race)
  }

  val Levels = Map( (1 -> 0), (2, 1000), (3 -> 3000), (4 -> 6000), (5 -> 10000), (6 -> 15000), (7 -> 21000), (8 -> 28000), (9 -> 36000), (10 -> 45000),
    (11 -> 55000), (12 -> 66000), (13 -> 78000), (14 -> 91000), (15 -> 105000), (16 -> 120000), (17 -> 136000), (18 -> 153000), (19 -> 171000), (20 -> 190000))

  abstract class Class {
    def criticalHitModifier(defender: Character): Int = 2
    def defaultDamage: Int = 1
  }
  private object DefaultClass extends Class {
  }

  abstract class Race

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}